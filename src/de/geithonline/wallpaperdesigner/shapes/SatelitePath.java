package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class SatelitePath extends Path {

	public SatelitePath(final Point center, final float radius, final boolean filled, final String variante) {
		super();

		switch (variante) {
		default:
		case "Satellite V1":
			drawSatelliteV1(center, radius, filled);
			break;
		}
	}

	private void drawSatelliteV1(final Point center, final float radius, final boolean filled) {
		final float raster = radius / 6;

		addRect(center.x - 1 * raster, center.y - 5 * raster, center.x + 1 * raster, center.y + 0 * raster, Direction.CW);
		addRect(center.x - 1.5f * raster, center.y - 3.25f * raster, center.x - 1 * raster, center.y - 2.75f * raster, Direction.CW);
		addRect(center.x + 1.0f * raster, center.y - 3.25f * raster, center.x + 1.5f * raster, center.y - 2.75f * raster, Direction.CW);
		// Flügel
		addRect(center.x - 7 * raster, center.y - 4 * raster, center.x - 1.5f * raster, center.y - 2.0f * raster, Direction.CW);
		addRect(center.x + 1.5f * raster, center.y - 4 * raster, center.x + 7 * raster, center.y - 2.0f * raster, Direction.CW);

		moveTo(center.x - 1 * raster, center.y + 0 * raster);
		quadTo(center.x - 3 * raster, center.y + 0 * raster, // controllpoint
				center.x - 4 * raster, center.y + 2 * raster); // Zielpunkt
		lineTo(center.x + 4 * raster, center.y + 2 * raster);
		quadTo(center.x + 3 * raster, center.y + 0 * raster, // controllpoint
				center.x + 1 * raster, center.y + 0 * raster); // Zielpunkt
		close();
		moveTo(center.x - 1 * raster, center.y + 2 * raster);
		quadTo(center.x + 0 * raster, center.y + 4 * raster, // controllpoint
				center.x + 1 * raster, center.y + 2 * raster); // Zielpunkt
		close();
		addRect(center.x - 0.1f * raster, center.y + 3 * raster, center.x + 0.1f * raster, center.y + 4 * raster, Direction.CW);
		addCircle(center.x - 0 * raster, center.y + 4.5f * raster, raster * 0.5f, Direction.CW);
		if (filled) {

			moveTo(center.x - 1 * raster, center.y + 5 * raster);
			quadTo(center.x + 0 * raster, center.y + 6 * raster, // controllpoint
					center.x + 1 * raster, center.y + 5 * raster); // Zielpunkt
			quadTo(center.x + 0 * raster, center.y + 5.9f * raster, // controllpoint
					center.x - 1 * raster, center.y + 5 * raster); // Zielpunkt
			close();
			moveTo(center.x - 2 * raster, center.y + 5.5f * raster);
			quadTo(center.x + 0 * raster, center.y + 7.4f * raster, // controllpoint
					center.x + 2 * raster, center.y + 5.5f * raster); // Zielpunkt
			quadTo(center.x + 0 * raster, center.y + 7.2f * raster, // controllpoint
					center.x - 2 * raster, center.y + 5.5f * raster); // Zielpunkt
			close();
			// Linien
			// addRect(center.x - 0.06f * raster, center.y + 6.5f * raster, center.x + 0.06f * raster, center.y + 13 * raster, Direction.CW);
			// addRect(center.x - 3.06f * raster, center.y + 6.5f * raster, center.x - 2.94f * raster, center.y + 9 * raster, Direction.CW);
			// addRect(center.x + 2.94f * raster, center.y + 6.5f * raster, center.x + 3.06f * raster, center.y + 9 * raster, Direction.CW);
		}
	}
}
