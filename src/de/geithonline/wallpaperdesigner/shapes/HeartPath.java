package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class HeartPath extends Path {

	public enum HEART_SHAPE {
		Curvy, Straigth, Round, Peek, Lovely, Asymetric;
	}

	/**
	 * @param center
	 *            center Point
	 * @param rOuter
	 *            Radius von 1f bis 5f....
	 */
	public HeartPath(final PointF center, final float rOuter, final boolean inverted, final HEART_SHAPE variante) {
		super();
		switch (variante) {
		default:
		case Curvy:
			drawHeartCurvy(center, rOuter, inverted);
			break;
		case Straigth:
			drawHeartStraight(center, rOuter, inverted);
			break;
		case Round:
			drawHeartRound(center, rOuter, inverted);
			break;
		case Peek:
			drawHeartPeek(center, rOuter, inverted);
			break;
		case Lovely:
			drawHeartLovely(center, rOuter, inverted);
			break;
		case Asymetric:
			drawHeartAsymetric(center, rOuter, inverted);
			break;
		}
	}

	private void drawHeartCurvy(final PointF center, final float rOuter, final boolean inverted) {

		if (inverted) {
			addCircle(center.x, center.y, rOuter * 1.4f, Direction.CCW);
		}
		final int radiusfactor = 15;
		final int ecken = 100;
		final float angle = (float) (2 * Math.PI / ecken);

		for (int i = 0; i <= ecken; i++) {
			final PointF p = new PointF();

			final float t = i * angle;
			p.x = (float) (rOuter * (12 * Math.sin(t) - 4 * Math.sin(3 * t)) / radiusfactor);
			p.y = (float) (rOuter * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)) / radiusfactor);

			p.x = p.x + center.x;
			p.y = -p.y + center.y;
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		close();
	}

	private void drawHeartStraight(final PointF center, final float radius, final boolean inverted) {
		final float raster = radius / 2;
		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x - 0 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		arcTo(oval, -225, 225);

		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		arcTo(oval, -180, 225);

		lineTo(center.x - 0 * raster, center.y + 2.5f * raster);
		close();
		if (inverted) {
			addCircle(center.x, center.y + radius * 0.25f, radius * 1.3f, Direction.CCW);
			close();
		}
	}

	private void drawHeartRound(final PointF center, final float radius, final boolean inverted) {
		final float raster = radius / 2;
		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x - 0 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		arcTo(oval, -180, 180);

		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		arcTo(oval, -180, 180);

		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, 0, 180);
		close();
		if (inverted) {
			addCircle(center.x, center.y + radius * 0.25f, radius * 1.3f, Direction.CCW);
			close();
		}
	}

	private void drawHeartPeek(final PointF center, final float radius, final boolean inverted) {
		final float raster = radius / 2;
		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x - 0 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -180, 180);

		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -180, 180);

		cubicTo(center.x + 2 * raster, center.y + 0 * raster, // CP1
				center.x + 0 * raster, center.y + 0.5f * raster, // CP2
				center.x + 0 * raster, center.y + 2 * raster);
		cubicTo(center.x - 0 * raster, center.y + 0.5f * raster, // CP1
				center.x - 2 * raster, center.y + 0 * raster, // CP2
				center.x - 2 * raster, center.y - 1 * raster);
		close();
		if (inverted) {
			addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			close();
		}
	}

	private void drawHeartLovely(final PointF center, final float radius, final boolean inverted) {
		final float raster = radius / 2;
		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x - 0 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -180, 180);

		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -180, 180);

		cubicTo(center.x + 2 * raster, center.y + 0 * raster, // CP1
				center.x + 1 * raster, center.y + 1.0f * raster, // CP2
				center.x + 0 * raster, center.y + 1.5f * raster);
		cubicTo(center.x - 1 * raster, center.y + 1.0f * raster, // CP1
				center.x - 2 * raster, center.y + 0 * raster, // CP2
				center.x - 2 * raster, center.y - 1 * raster);
		close();
		if (inverted) {
			addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			close();
		}
	}

	private void drawHeartAsymetric(final PointF center, final float radius, final boolean inverted) {
		final float raster = radius / 2;
		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x - 0 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -180, 180);

		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -180, 180);

		cubicTo(center.x + 2 * raster, center.y + 0 * raster, // CP1
				center.x - 0 * raster, center.y + 0.5f * raster, // CP2
				center.x - 0.5f * raster, center.y + 2 * raster);
		cubicTo(center.x - 0 * raster, center.y + 0.5f * raster, // CP1
				center.x - 2 * raster, center.y + 0 * raster, // CP2
				center.x - 2 * raster, center.y - 1 * raster);
		close();
		if (inverted) {
			addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			close();
		}
	}

}
