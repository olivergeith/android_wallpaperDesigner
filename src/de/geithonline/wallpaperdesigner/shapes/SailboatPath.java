package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class SailboatPath extends Path {

	public SailboatPath(final Point center, final float radius) {
		super();

		final float raster = radius / 3;

		// segel groß
		moveTo(center.x - 0 * raster, center.y - 3 * raster);
		lineTo(center.x - 0 * raster, center.y + 2 * raster);
		lineTo(center.x - 2 * raster, center.y + 2 * raster);
		close();
		// segel klein
		moveTo(center.x - 0 * raster, center.y - 2 * raster);
		lineTo(center.x - 0 * raster, center.y + 2 * raster);
		lineTo(center.x + 1 * raster, center.y + 2 * raster);
		close();
		// rumpf
		moveTo(center.x - 3 * raster, center.y + 2 * raster);
		lineTo(center.x + 2 * raster, center.y + 2 * raster);
		lineTo(center.x + 0 * raster, center.y + 3 * raster);
		close();

	}

}
