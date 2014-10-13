package de.geithonline.wallpaperdesigner.shapes.base;

import android.graphics.Path;
import android.graphics.PointF;

public class ArowPath extends Path {

	public ArowPath(final PointF center, final float radius, final Direction dir, final boolean filled) {
		draw(center, radius, dir, filled);
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
}
