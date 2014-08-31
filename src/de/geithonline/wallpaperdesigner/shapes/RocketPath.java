package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class RocketPath extends Path {

	public RocketPath(final Point center, final float radius, final boolean filled) {
		super();

		final float raster = radius / 6;

		// Kabine
		// addRect(center.x - 2 * raster, center.y - 4 * raster, center.x + 2 * raster, center.y - 2 * raster, Direction.CW);
		// addCircle(center.x, center.y - 3 * raster, raster * 0.4f, Direction.CCW);
		// addCircle(center.x - 1.30f * raster, center.y - 3 * raster, raster * 0.4f, Direction.CCW);
		// addCircle(center.x + 1.30f * raster, center.y - 3 * raster, raster * 0.4f, Direction.CCW);

		// Rumpf
		moveTo(center.x - 0 * raster, center.y - 7 * raster);
		quadTo(center.x - 3 * raster, center.y - 3 * raster, // controllpoint
				center.x - 2 * raster, center.y + 1 * raster); // Zielpunkt
		lineTo(center.x - 1 * raster, center.y + 4 * raster);
		lineTo(center.x + 1 * raster, center.y + 4 * raster);
		lineTo(center.x + 2 * raster, center.y + 1 * raster);
		quadTo(center.x + 3 * raster, center.y - 3 * raster, // controllpoint
				center.x + 0 * raster, center.y - 7 * raster); // Zielpunkt
		close();

		// Flügel links
		moveTo(center.x - 2 * raster, center.y + 1 * raster);
		quadTo(center.x - 4 * raster, center.y + 3 * raster, // controllpoint
				center.x - 3 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x - 3 * raster, center.y + 4 * raster, // controllpoint
				center.x - 1 * raster, center.y + 4 * raster); // Zielpunkt
		close();

		// Flügel rechts
		moveTo(center.x + 2 * raster, center.y + 1 * raster);
		quadTo(center.x + 4 * raster, center.y + 3 * raster, // controllpoint
				center.x + 3 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 3 * raster, center.y + 4 * raster, // controllpoint
				center.x + 1 * raster, center.y + 4 * raster); // Zielpunkt
		close();

		// Flügel Mitte
		addRect(center.x - 0.2f * raster, center.y + 1 * raster, center.x + 0.2f * raster, center.y + 6 * raster, Direction.CW);

		// Fenster
		addCircle(center.x, center.y - 1 * raster, raster * 0.7f, Direction.CW);
		addCircle(center.x, center.y - 3 * raster, raster * 0.7f, Direction.CW);

		if (filled) {
			// Linien
			addRect(center.x - 0.06f * raster, center.y + 6.5f * raster, center.x + 0.06f * raster, center.y + 13 * raster, Direction.CW);
			addRect(center.x - 3.06f * raster, center.y + 6.5f * raster, center.x - 2.94f * raster, center.y + 9 * raster, Direction.CW);
			addRect(center.x + 2.94f * raster, center.y + 6.5f * raster, center.x + 3.06f * raster, center.y + 9 * raster, Direction.CW);
		}
	}
}
