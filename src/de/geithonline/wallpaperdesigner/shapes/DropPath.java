package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class DropPath extends Path {

	public DropPath(final Point center, final float radius) {
		draw(center, radius);
	}

	private void draw(final Point center, final float radius) {
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

}
