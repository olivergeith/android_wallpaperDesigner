package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class TextPath extends Path {

	public TextPath(final PointF center, final float radius) {
		drawCross(center, radius);
	}

	private void drawCross(final PointF center, final float radius) {
		final float raster = radius / 3;
		moveTo(center.x - 3 * raster, center.y - 3 * raster);
		lineTo(center.x + 3 * raster, center.y - 3 * raster);
		lineTo(center.x + 3 * raster, center.y - 1 * raster);
		lineTo(center.x + 1 * raster, center.y - 1 * raster);
		lineTo(center.x + 1 * raster, center.y + 3 * raster);
		lineTo(center.x - 1 * raster, center.y + 3 * raster);
		lineTo(center.x - 1 * raster, center.y - 1 * raster);
		lineTo(center.x - 3 * raster, center.y - 1 * raster);
		close();
	}
}
