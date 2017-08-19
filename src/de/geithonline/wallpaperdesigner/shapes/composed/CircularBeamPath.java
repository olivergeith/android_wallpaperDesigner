package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.specialoptions.CircularMazeOptions;
import de.geithonline.wallpaperdesigner.shapes.LevelArcPath;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularBeamPath extends ComposedPath {

	private final CircularMazeOptions options;

	public CircularBeamPath(final PointF center, final float radius, final CircularMazeOptions options) {

		this.options = options;
		draw(center, radius);
	}

	private void draw(final PointF center, final float radius) {

		final float winkelProSegment = 360 / options.anzArcs;

		for (int i = 0; i < options.anzArcs; i++) {
			final float segmentStartingAngle = i * winkelProSegment;
			final float sweepProSegment = winkelProSegment * Randomizer.getRandomFloat(options.minSweep, options.maxSweep) / 360f;
			final float dicke = radius * Randomizer.getRandomFloat(options.minThickness, options.maxThickness);
			final float innerRad = 0.02f;
			final int anzSegmente = Randomizer.getRandomInt(options.minSegments, options.maxSegments);
			final Path arc = getBogen(center, segmentStartingAngle, sweepProSegment, dicke, innerRad, anzSegmente);
			addPath(arc);
		}
	}

	private ComposedPath getBogen(final PointF center, final float segmentStartingAngle, final float swee, final float dicke, final float innerRad,
			final int anzSegmente) {
		final ComposedPath bogen = new ComposedPath();
		if (anzSegmente > 1) {
			float additional = 1f;
			if (anzSegmente <= 3) {
				additional = 0.5f;
			}
			if (anzSegmente > 5) {
				additional = 2f;
			}
			final float abstandProSegment = dicke / anzSegmente;
			final float dickeProSegment = dicke / (anzSegmente + additional);
			for (int a = 0; a < anzSegmente; a++) {
				final float inner = innerRad + a * abstandProSegment;
				bogen.addPath(new LevelArcPath(center, inner + dickeProSegment, inner, segmentStartingAngle, swee));
			}
		} else {

			bogen.addPath(new LevelArcPath(center, innerRad + dicke, innerRad, segmentStartingAngle, swee));
		}
		return bogen;
	}

	public static CircularMazeOptions getPreset() {
		final CircularMazeOptions options = new CircularMazeOptions();
		options.anzArcs = 40;
		options.minSweep = 330;
		options.maxSweep = 330;
		options.minThickness = 0.2f;
		options.maxThickness = 1f;
		options.minBrightness = -48;
		options.maxBrightness = 48;
		options.minSegments = 1;
		options.maxSegments = 1;
		return options;
	}
}
