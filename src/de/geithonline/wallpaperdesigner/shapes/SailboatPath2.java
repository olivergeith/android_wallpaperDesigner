package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class SailboatPath2 extends Path {

	public SailboatPath2(final Point center, final float radius) {
		super();

		final float raster = radius / 4;

		// segel groß
		moveTo(center.x + 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 1 * raster, center.y + 2 * raster);
		lineTo(center.x - 1 * raster, center.y + 2 * raster);
		quadTo(center.x + 1 * raster, center.y + 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		// segel klein
		moveTo(center.x + 2 * raster, center.y - 3 * raster);
		quadTo(center.x + 4 * raster, center.y + 1 * raster, // controllpoint
				center.x + 1 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x + 7 * raster, center.y + 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		// rumpf
		// moveTo(center.x - 3 * raster, center.y + 2.2f * raster);
		// lineTo(center.x + 4 * raster, center.y + 2.2f * raster);
		// lineTo(center.x + 0 * raster, center.y + 3.5f * raster);
		// close();
		// rumpf
		moveTo(center.x - 1.2f * raster, center.y + 2.2f * raster);
		// quadTo(center.x - 3 * raster, center.y + 5 * raster, // controllpoint
		// center.x - 4 * raster, center.y + 4 * raster); // Zielpunkt
		lineTo(center.x - 3 * raster, center.y + 3.5f * raster);
		lineTo(center.x + 4.5f * raster, center.y + 2.2f * raster);
		close();

	}
}
