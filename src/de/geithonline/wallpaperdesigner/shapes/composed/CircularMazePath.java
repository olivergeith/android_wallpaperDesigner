package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.LevelArcPath;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularMazePath extends ComposedPath {

	public enum MazeType {
		normal, segmented;
	}

	public CircularMazePath(final PointF center, final float radius, final MazeType type) {
		switch (type) {
		default:
		case normal:
			drawCircularMaze(center, radius);
			break;
		case segmented:
			drawSegmentedCircularMaze(center, radius);
			break;

		}
	}

	private void drawCircularMaze(final PointF center, final float radius) {
		for (int i = 0; i < 15; i++) {
			final float sweep = Randomizer.getRandomFloat(0, 180);
			final float startingAngle = Randomizer.getRandomFloat(0, 360);
			final float dicke = radius * Randomizer.getRandomFloat(0.02f, 0.2f);
			final float innerRad = Randomizer.getRandomFloat(radius * 0.02f, (radius - dicke) * 1f);
			final Path arc = new LevelArcPath(center, innerRad + dicke, innerRad, startingAngle, sweep);
			addPath(arc);
		}
	}

	private void drawSegmentedCircularMaze(final PointF center, final float radius) {
		for (int i = 0; i < 15; i++) {
			final float sweep = Randomizer.getRandomFloat(90, 270);
			final float startingAngle = Randomizer.getRandomFloat(0, 360);
			final float dicke = radius * Randomizer.getRandomFloat(0.02f, 0.2f);
			final float innerRad = Randomizer.getRandomFloat(radius * 0.02f, (radius - dicke) * 1f);
			final int anzSegmente = Randomizer.getRandomInt(5, 15);
			final ComposedPath bogen = new ComposedPath();
			for (int a = 0; a < anzSegmente; a++) {
				final float sweepProSegment = sweep / (anzSegmente + 2);
				final float winkelProSegment = sweep / anzSegmente;
				final float segmentStartingAngle = startingAngle + a * winkelProSegment;
				final Path arc = new LevelArcPath(center, innerRad + dicke, innerRad, segmentStartingAngle, sweepProSegment);
				bogen.addPath(arc);
			}
			addPath(bogen);
		}
	}

}
