package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class CrownPath extends Path {

	public CrownPath(final Point center, final float radius, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawCrownV1(center, radius);
			break;
		}

	}

	private void drawCrownV1(final Point center, final float radius) {
		final float raster = radius / 7;

		moveTo(center.x - 5 * raster, center.y - 3 * raster);
		quadTo(center.x - 2 * raster, center.y - 2 * raster, // controllpoint
				center.x - 1 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y - 2 * raster, // controllpoint
				center.x - 0 * raster, center.y - 4 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 2 * raster, // controllpoint
				center.x + 1 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 2 * raster, // controllpoint
				center.x + 5 * raster, center.y - 3 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 3 * raster, center.y + 3 * raster); // Zielpunkt
		lineTo(center.x - 3 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y - 1 * raster, // controllpoint
				center.x - 5 * raster, center.y - 3 * raster); // Zielpunkt
		close();
		addRect(center.x - 3 * raster, center.y + 3.5f * raster, center.x + 3 * raster, center.y + 5 * raster, Direction.CW);
		addCircle(center.x - 6 * raster, center.y - 4 * raster, raster * 1.0f, Direction.CW);
		addCircle(center.x + 0 * raster, center.y - 5.3f * raster, raster * 1.0f, Direction.CW);
		addCircle(center.x + 6 * raster, center.y - 4 * raster, raster * 1.0f, Direction.CW);

	}

}
