package de.geithonline.wallpaperdesigner.shapes.base;

import android.graphics.Path;
import android.graphics.PointF;

public class CrossPath extends Path {

	public CrossPath(final PointF center, final float radius, final Direction dir, final boolean filled) {
		drawCross(center, radius, dir, filled);
	}

	private void drawCross(final PointF center, final float radius, final Direction dir, final boolean filled) {
		final float raster = radius / 3;
		if (dir.equals(Direction.CW)) {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CCW);
			}
			moveTo(center.x + 1 * raster, center.y - 3 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			lineTo(center.x + 3 * raster, center.y - 1 * raster);
			lineTo(center.x + 3 * raster, center.y + 1 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x + 1 * raster, center.y + 3 * raster);
			lineTo(center.x - 1 * raster, center.y + 3 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 3 * raster, center.y + 1 * raster);
			lineTo(center.x - 3 * raster, center.y - 1 * raster);
			lineTo(center.x - 1 * raster, center.y - 1 * raster);
			lineTo(center.x - 1 * raster, center.y - 3 * raster);
			close();
		} else {
			if (!filled) {
				addCircle(center.x, center.y, radius * 1.3f, Direction.CW);
			}
			moveTo(center.x + 1 * raster, center.y - 3 * raster);
			lineTo(center.x - 1 * raster, center.y - 3 * raster);
			lineTo(center.x - 1 * raster, center.y - 1 * raster);
			lineTo(center.x - 3 * raster, center.y - 1 * raster);
			lineTo(center.x - 3 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 3 * raster);
			lineTo(center.x + 1 * raster, center.y + 3 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x + 3 * raster, center.y + 1 * raster);
			lineTo(center.x + 3 * raster, center.y - 1 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			close();
		}

	}
}
