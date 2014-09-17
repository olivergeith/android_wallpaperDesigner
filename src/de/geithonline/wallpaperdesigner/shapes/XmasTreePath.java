package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class XmasTreePath extends Path {

	public XmasTreePath(final Point center, final float radius, final boolean filled) {
		super();
		drawTree(center, radius, filled);
	}

	private void drawTree(final Point center, final float radius, final boolean filled) {
		final float raster = radius / 5;

		// obere Hälfte
		moveTo(center.x - 1 * raster, center.y + 5 * raster);
		lineTo(center.x - 1 * raster, center.y + 4 * raster);
		lineTo(center.x - 5 * raster, center.y + 4 * raster);
		lineTo(center.x - 3 * raster, center.y + 2 * raster);
		lineTo(center.x - 4 * raster, center.y + 2 * raster);
		lineTo(center.x - 2 * raster, center.y + 0 * raster);
		lineTo(center.x - 3 * raster, center.y + 0 * raster);
		lineTo(center.x - 1 * raster, center.y - 2 * raster);
		lineTo(center.x - 2 * raster, center.y - 2 * raster);
		lineTo(center.x - 0 * raster, center.y - 4 * raster);

		lineTo(center.x + 2 * raster, center.y - 2 * raster);
		lineTo(center.x + 1 * raster, center.y - 2 * raster);
		lineTo(center.x + 3 * raster, center.y + 0 * raster);
		lineTo(center.x + 2 * raster, center.y + 0 * raster);
		lineTo(center.x + 4 * raster, center.y + 2 * raster);
		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		lineTo(center.x + 5 * raster, center.y + 4 * raster);
		lineTo(center.x + 1 * raster, center.y + 4 * raster);
		lineTo(center.x + 1 * raster, center.y + 5 * raster);
		close();

		final PointF c = new PointF();
		c.x = center.x;
		c.y = center.y - 5.4f * raster;
		addPath(new StarPath(6, c, raster, raster / 2, true, 0f));

		if (!filled) {
			addCircle(center.x - 2 * raster, center.y + 2.5f * raster, raster / 2, Direction.CCW);
			addCircle(center.x + 2 * raster, center.y + 2.5f * raster, raster / 2, Direction.CCW);
			addCircle(center.x - 1 * raster, center.y + 0.5f * raster, raster / 2, Direction.CCW);
			addCircle(center.x + 1 * raster, center.y + 0.5f * raster, raster / 2, Direction.CCW);
			addCircle(center.x + 0 * raster, center.y - 1.5f * raster, raster / 2, Direction.CCW);
		}
	}
}
