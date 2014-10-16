package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class PillowPath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final Point center, final float radius) {
		super();

		final float raster = radius / 2;
		moveTo(center.x - 2 * raster, center.y - 2 * raster);
		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 0 * raster, // controllpoint
				center.x + 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 1 * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y + 0 * raster, // controllpoint
				center.x - 2 * raster, center.y - 2 * raster); // Zielpunkt
		close();
	}
}
