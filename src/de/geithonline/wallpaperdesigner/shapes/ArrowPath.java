package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class ArrowPath extends Path {

	public enum ARROW_TYPE {
		STRAIGHT_UP, ROUND, TRIANGLE;
	}

	public ArrowPath(final PointF center, final float radius, final Direction dir, final boolean filled, final ARROW_TYPE variante) {
		switch (variante) {
		default:
		case STRAIGHT_UP:
			draw(center, radius, dir, filled);
			break;
		case ROUND:
			drawRoundArrow(center, radius, dir, filled);
			break;
		case TRIANGLE:
			drawTriangleArrow(center, radius, dir, filled);
			break;
		}
	}

	private void draw(final PointF center, final float radius, final Direction dir, final boolean filled) {
		final float raster = radius / 3;
		if (dir.equals(Direction.CW)) {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			}
			moveTo(center.x + 0 * raster, center.y - 3 * raster);
			lineTo(center.x + 3 * raster, center.y + 1 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x + 1 * raster, center.y + 3 * raster);
			lineTo(center.x - 1 * raster, center.y + 3 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 3 * raster, center.y + 1 * raster);
			close();
		} else {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CW);
			}
			moveTo(center.x + 0 * raster, center.y - 3 * raster);
			lineTo(center.x - 3 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 3 * raster);
			lineTo(center.x + 1 * raster, center.y + 3 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x + 3 * raster, center.y + 1 * raster);
			close();
		}

	}

	private void drawTriangleArrow(final PointF center, final float radius, final Direction dir, final boolean filled) {
		final float raster = radius / 2;
		if (dir.equals(Direction.CW)) {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			}
			moveTo(center.x + 0 * raster, center.y - 2 * raster);
			lineTo(center.x + 2 * raster, center.y + 2 * raster);
			lineTo(center.x - 2 * raster, center.y + 2 * raster);
			close();
		} else {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CW);
			}
			moveTo(center.x + 0 * raster, center.y - 2 * raster);
			lineTo(center.x - 2 * raster, center.y + 2 * raster);
			lineTo(center.x + 2 * raster, center.y + 2 * raster);
			close();
		}

	}

	private void drawRoundArrow(final PointF center, final float radius, final Direction dir, final boolean filled) {
		final float raster = radius / 4.9f;
		if (!filled) {
			addCircle(center.x, center.y, radius, Direction.CCW);
		}
		moveTo(center.x - 1.5f * raster, center.y - 2.5f * raster);
		lineTo(center.x + 0 * raster, center.y - 3.5f * raster);
		lineTo(center.x + 0 * raster, center.y - 3 * raster);
		final RectF oval = new RectF();
		oval.left = center.x - 3 * raster;
		oval.right = center.x + 3 * raster;
		oval.top = center.y - 3 * raster;
		oval.bottom = center.y + 3 * raster;
		arcTo(oval, -90, 270);
		lineTo(center.x - 2 * raster, center.y - 0 * raster);
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, 180, -270);
		lineTo(center.x - 0 * raster, center.y - 1.5f * raster);

		close();

	}
}
