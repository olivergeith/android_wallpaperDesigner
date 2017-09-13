package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.specialoptions.CircularMazeOptions;
import de.geithonline.wallpaperdesigner.shapes.LevelArcPath;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularArcsRotatedPath extends ComposedPath {

	private final CircularMazeOptions options;

	public CircularArcsRotatedPath(final PointF center, final float radius, final CircularMazeOptions options) {

		this.options = options;
		draw(center, radius);
	}

	private void draw(final PointF center, final float radius) {
		for (int i = 0; i < options.anzArcs; i++) {
			final float segmentStartingAngle = Randomizer.getRandomFloat(0f, 360f);
			final float sweepProSegment = Randomizer.getRandomFloat(options.minSweep, options.maxSweep);
			final float dicke = radius * Randomizer.getRandomFloat(0.02f, 1f);
			final float innerRad = 0.02f * radius;
			final int anzSegmente = Randomizer.getRandomInt(options.minSegments, options.maxSegments);
			final Path arc = getBogen(center, segmentStartingAngle, sweepProSegment, dicke, innerRad, anzSegmente);
			addPath(arc);
		}
	}

	private ComposedPath getBogen(final PointF center, final float segmentStartingAngle, final float sweep, final float dicke, final float innerRad,
			final int anzSegmente) {
		final ComposedPath bogen = new ComposedPath();
		if (anzSegmente > 1) {
			final float abstandProSegment = dicke / anzSegmente;
			final float dickeProSegment = abstandProSegment * 0.9f;
			final float maxStartingAngle = (Randomizer.getRandomFloat(options.minThickness, options.maxThickness) - 0.5f) * 180f;
			for (int a = 0; a < anzSegmente; a++) {
				final float starting = segmentStartingAngle + a * maxStartingAngle / anzSegmente;
				final float inner = innerRad + a * abstandProSegment;
				bogen.addPath(new LevelArcPath(center, inner + dickeProSegment, inner, starting, sweep));
			}
		} else {

			bogen.addPath(new LevelArcPath(center, innerRad + dicke, innerRad, segmentStartingAngle, sweep));
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
