package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class FourSailsPath extends Path {

	public FourSailsPath(final Point center, final float radius, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawSails(center, radius);
			break;
		}

	}

	private void drawSails(final Point center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x + 0 * raster, center.y - 1 * raster);
		lineTo(center.x + 0 * raster, center.y - 10 * raster); // Zielpunkt
		lineTo(center.x - 3 * raster, center.y - 1 * raster); // Zielpunkt
		close();

		moveTo(center.x - 1 * raster, center.y - 0 * raster);
		lineTo(center.x - 10 * raster, center.y - 0 * raster); // Zielpunkt
		lineTo(center.x - 1 * raster, center.y + 3 * raster); // Zielpunkt
		close();

		moveTo(center.x + 0 * raster, center.y + 1 * raster);
		lineTo(center.x + 0 * raster, center.y + 10 * raster); // Zielpunkt
		lineTo(center.x + 3 * raster, center.y + 1 * raster); // Zielpunkt
		close();

		moveTo(center.x + 1 * raster, center.y - 0 * raster);
		lineTo(center.x + 10 * raster, center.y - 0 * raster); // Zielpunkt
		lineTo(center.x + 1 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		moveTo(center.x + 1 * raster, center.y + 0 * raster);
		lineTo(center.x + 0 * raster, center.y + 1 * raster);
		lineTo(center.x - 1 * raster, center.y + 0 * raster);
		lineTo(center.x - 0 * raster, center.y - 1 * raster);
		close();

	}

}
