package de.geithonline.wallpaperdesigner.shapes.base;

import android.graphics.Path;
import android.graphics.PointF;

public class MinusPath extends Path {

	public MinusPath(final PointF center, final float radius, final Direction dir, final boolean filled) {
		drawCross(center, radius, dir, filled);
	}

	private void drawCross(final PointF center, final float radius, final Direction dir, final boolean filled) {
		final float raster = radius / 3;
		if (dir.equals(Direction.CW)) {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			}
			addRect(center.x - 3 * raster, center.y - 1 * raster, center.x + 3 * raster, center.y + 1 * raster, Direction.CW);
		} else {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CW);
			}
			addRect(center.x - 3 * raster, center.y - 1 * raster, center.x + 3 * raster, center.y + 1 * raster, Direction.CCW);
		}

	}
}
