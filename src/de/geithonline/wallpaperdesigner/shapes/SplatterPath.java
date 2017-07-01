
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.Rotator;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SplatterPath extends Path {

	public enum SPLATTER_TYPE {
		Cloud, Drop, Bacteria, Square, Triangle, CloudWithHoles;
	}

	/**
	 * @param ecken
	 * @param center
	 * @param radius
	 * @param rotate
	 *            0-2pi
	 */
	public SplatterPath(final PointF center, final float radius, final boolean filled, final SPLATTER_TYPE type) {
		super();

		switch (type) {
		default:
		case Cloud:
			drawCloud(center, radius, false);
			break;
		case CloudWithHoles:
			drawCloud(center, radius, true);
			break;
		case Drop:
			drawDrop(center, radius);
			break;
		case Bacteria:
			drawBacteria(center, radius);
			break;
		case Square:
			drawSquares(center, radius);
			break;
		case Triangle:
			drawTriangle(center, radius);
			break;
		}

	}

	private void drawCloud(final PointF center, final float radius, final boolean withHoles) {
		for (int i = 0; i < 15; i++) {
			final float x = center.x + Randomizer.getRandomFloat(-radius * 0.7f, radius * 0.7f);
			final float y = center.y + Randomizer.getRandomFloat(-radius * 0.7f, radius * 0.7f);
			final float circleRadius = Randomizer.getRandomFloat(radius * 0.05f, radius * 0.5f);
			final Path c = new CirclePath(new PointF(x, y), circleRadius, 0, true, CIRCLE_STYLE.CIRCLE);
			if (withHoles) {
				if (Randomizer.getRandomBooleanInPercentOfCases(80)) {
					op(c, Op.UNION);
				} else {
					op(c, Op.DIFFERENCE);
				}
			} else {
				op(c, Op.UNION);
			}
		}
	}

	private void drawSquares(final PointF center, final float radius) {
		for (int i = 0; i < 8; i++) {
			final float x = center.x + Randomizer.getRandomFloat(-radius * 0.7f, radius * 0.7f);
			final float y = center.y + Randomizer.getRandomFloat(-radius * 0.7f, radius * 0.7f);
			final float circleRadius = Randomizer.getRandomFloat(radius * 0.05f, radius * 0.5f);
			// final Path c = new CirclePath(new PointF(x, y), circleRadius, 0, true,
			// CIRCLE_STYLE.CIRCLE);
			final Path c = new SquarePath(new PointF(x, y), circleRadius, true, SQUARE_STYLE.NORMAL, Direction.CW);
			op(c, Op.UNION);
		}
	}

	private void drawBacteria(final PointF center, final float radius) {
		for (int i = 0; i < 15; i++) {
			final float x = center.x + Randomizer.getRandomFloat(-radius / 2, radius / 2);
			final float y = center.y + Randomizer.getRandomFloat(-radius / 2, radius / 2);
			final float circleRadius = Randomizer.getRandomFloat(radius * 0.05f, radius * 0.5f);
			final Path c;
			if (Randomizer.getRandomBooleanInPercentOfCases(70)) {
				c = new VirusPath(new PointF(x, y), circleRadius, true);
			} else {
				c = new CirclePath(new PointF(x, y), circleRadius, 0, true, CIRCLE_STYLE.CIRCLE);
			}
			op(c, Op.UNION);
		}
	}

	private void drawDrop(final PointF center, final float radius) {
		final Path circle = new VirusPath(center, radius * 0.3f, true);
		op(circle, Op.UNION);
		for (int i = 0; i < 15; i++) {
			final PointF c = new PointF();
			c.x = center.x + Randomizer.getRandomFloat(-radius / 2, radius / 2);
			c.y = center.y + Randomizer.getRandomFloat(-radius / 2, radius / 2);
			final float circleRadius = Randomizer.getRandomFloat(radius * 0.15f, radius * 0.5f);
			final Path path = new DropPath(c, circleRadius);
			final float winkel = Rotator.getDegreesToCenter(center, c) - 90;
			PathHelper.rotatePath(c, path, winkel);
			op(path, Op.UNION);
		}
	}

	private void drawTriangle(final PointF center, final float radius) {
		for (int i = 0; i < 15; i++) {
			final PointF c = new PointF();
			c.x = center.x + Randomizer.getRandomFloat(-radius / 2, radius / 2);
			c.y = center.y + Randomizer.getRandomFloat(-radius / 2, radius / 2);
			final float circleRadius = Randomizer.getRandomFloat(radius * 0.15f, radius * 0.5f);
			final Path path = new XEckPath(3, c, circleRadius, Randomizer.getRandomFloat(0, (float) Math.PI), true);
			op(path, Op.UNION);
		}
	}

}
