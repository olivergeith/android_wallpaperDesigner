package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class DropPath extends Path {

	public DropPath(final Point center, final float radius) {
		drawDrop(center, radius);
	}

	private void drawDrop(final Point center, final float radius) {
		final float raster = radius / 2;
		final RectF oval = new RectF();
		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y - 0 * raster;
		oval.bottom = center.y + 2 * raster;

		moveTo(center.x + 0 * raster, center.y - 2 * raster);
		cubicTo(center.x + 0 * raster, center.y - 0 * raster, // CP1
				center.x + 1 * raster, center.y - 0 * raster, // CP2
				center.x + 1 * raster, center.y + 1 * raster);
		arcTo(oval, 0, 180);
		cubicTo(center.x - 1 * raster, center.y - 0 * raster, // CP1
				center.x + 0 * raster, center.y - 0 * raster, // CP2
				center.x + 0 * raster, center.y - 2 * raster);
		close();

	}

	public DropPath(final Point center, final float radius, final int height, final boolean filled) {
		drawLongDrop(center, radius, height, filled);
	}

	private void drawLongDrop(final Point center, final float radius, final int height, final boolean filled) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - 2 * radius;
		oval.bottom = center.y;

		moveTo(center.x, center.y - height);
		cubicTo(center.x, center.y - height + 4 * radius, // CP1
				center.x + radius, center.y - 2 * radius, // CP2
				center.x + radius, center.y - radius);
		arcTo(oval, 0, 180);
		cubicTo(center.x - radius, center.y - 2 * radius, // CP1
				center.x, center.y - height + 4 * radius, // CP2
				center.x, center.y - height);
		close();

		if (!filled) {
			oval.left = center.x - radius / 2;
			oval.right = center.x + radius / 2;
			oval.top = center.y - 2 * radius + radius / 2;
			oval.bottom = center.y - radius / 2;
			addCircle(center.x, center.y - radius, radius * 2 / 3, Direction.CCW);
		}

	}

}
