package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class UfoPath extends Path {

	public UfoPath(final Point center, final float radius) {
		super();

		final float raster = radius / 6;

		// Kabine
		addRect(center.x - 2 * raster, center.y - 4 * raster, center.x + 2 * raster, center.y - 2 * raster, Direction.CW);
		addCircle(center.x, center.y - 3 * raster, raster * 0.4f, Direction.CCW);
		addCircle(center.x - 1.30f * raster, center.y - 3 * raster, raster * 0.4f, Direction.CCW);
		addCircle(center.x + 1.30f * raster, center.y - 3 * raster, raster * 0.4f, Direction.CCW);

		// Rumpf
		moveTo(center.x - 2 * raster, center.y - 2 * raster);
		quadTo(center.x - 5 * raster, center.y - 1 * raster, // controllpoint
				center.x - 6 * raster, center.y + 1 * raster); // Zielpunkt
		lineTo(center.x + 6 * raster, center.y + 1 * raster);
		quadTo(center.x + 5 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 2 * raster); // Zielpunkt
		close();

		// Deckel
		moveTo(center.x - 2 * raster, center.y - 4 * raster);
		quadTo(center.x + 0 * raster, center.y - 6 * raster, // controllpoint
				center.x + 2 * raster, center.y - 4 * raster); // Zielpunkt
		close();

		// Bubble unten mitte
		moveTo(center.x - 2 * raster, center.y + 1 * raster);
		quadTo(center.x + 0 * raster, center.y + 3.0f * raster, // controllpoint
				center.x + 2 * raster, center.y + 1 * raster); // Zielpunkt
		close();

		// Bubble unten rechts
		moveTo(center.x + 2 * raster, center.y + 1 * raster);
		quadTo(center.x + 3.5f * raster, center.y + 2.5f * raster, // controllpoint
				center.x + 5 * raster, center.y + 1 * raster); // Zielpunkt
		close();
		// Bubble unten links
		moveTo(center.x - 2 * raster, center.y + 1 * raster);
		quadTo(center.x - 3.5f * raster, center.y + 2.5f * raster, // controllpoint
				center.x - 5 * raster, center.y + 1 * raster); // Zielpunkt
		close();

	}
}
