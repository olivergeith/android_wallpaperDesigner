package de.geithonline.wallpaperdesigner.shapes.base;

import android.graphics.Path;
import android.graphics.PointF;

public class CrossPath extends Path {

	public CrossPath(final PointF center, final float radius, final Direction dir) {
		drawCross(center, radius, dir);
	}

	private void drawCross(final PointF center, final float radius, final Direction dir) {
		final float raster = radius / 3;
		if (dir.equals(Direction.CW)) {
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
