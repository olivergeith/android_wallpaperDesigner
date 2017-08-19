package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.specialoptions.CircularMazeOptions;
import de.geithonline.wallpaperdesigner.shapes.LevelArcPath;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularMazePath extends ComposedPath {

	private final CircularMazeOptions options;

	public CircularMazePath(final PointF center, final float radius, final CircularMazeOptions options) {

		this.options = options;
		drawSegmentedCircularMaze(center, radius);
	}

	private void drawSegmentedCircularMaze(final PointF center, final float radius) {
		for (int i = 0; i < options.anzArcs; i++) {
			final float sweep = Randomizer.getRandomFloat(options.minSweep, options.maxSweep);
			final float startingAngle = Randomizer.getRandomFloat(0, 360);
			final float dicke = radius * Randomizer.getRandomFloat(options.minThickness, options.maxThickness);
			final float innerRad = Randomizer.getRandomFloat(radius * 0.02f, (radius - dicke) * 1f);
			final int anzSegmente = Randomizer.getRandomInt(options.minSegments, options.maxSegments);
			final ComposedPath bogen = getSegmentedArc(center, sweep, startingAngle, dicke, innerRad, anzSegmente);
			addPath(bogen);
		}
	}

	private ComposedPath getSegmentedArc(final PointF center, //
			final float sweep, final float startingAngle, final float dicke, final float innerRad, //
			final int anzSegmente) {
		final ComposedPath bogen = new ComposedPath();
		if (anzSegmente > 1) {
			int additional = 1;
			if (anzSegmente > 5) {
				additional = 2;
			}
			final float sweepProSegment = sweep / (anzSegmente + additional);
			final float winkelProSegment = sweep / anzSegmente;
			for (int a = 0; a < anzSegmente; a++) {
				final float segmentStartingAngle = startingAngle + a * winkelProSegment;
				final Path arc = new LevelArcPath(center, innerRad + dicke, innerRad, segmentStartingAngle, sweepProSegment);
				bogen.addPath(arc);
			}
		} else {
			bogen.addPath(new LevelArcPath(center, innerRad + dicke, innerRad, startingAngle, sweep));
		}
		return bogen;
	}

	public static CircularMazeOptions getPreset() {
		final CircularMazeOptions options = new CircularMazeOptions();
		options.anzArcs = 32;
		options.minSweep = 30;
		options.maxSweep = 270;
		options.minThickness = 0.01f;
		options.maxThickness = 0.1f;
		options.minBrightness = -48;
		options.maxBrightness = 92;
		options.minSegments = 1;
		options.maxSegments = 1;
		return options;
	}

}
