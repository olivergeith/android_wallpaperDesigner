package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class FishV3Path extends Path {

	public FishV3Path(final Point center, final float radius) {
		super();

		drawShark(center, radius);

	}

	private void drawShark(final Point center, final float radius) {
		final float raster = radius / 4;

		moveTo(center.x - 10 * raster, center.y + 0 * raster);

		quadTo(center.x - 6 * raster, center.y - 4 * raster, // controllpoint
				center.x + 0 * raster, center.y - 4 * raster); // Zielpunkt
		// flosse oben
		quadTo(center.x - 0 * raster, center.y - 6 * raster, // controllpoint
				center.x - 1 * raster, center.y - 7 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 7 * raster, // controllpoint
				center.x + 2 * raster, center.y - 4 * raster); // Zielpunkt
		// weiter zur nase
		quadTo(center.x + 6 * raster, center.y - 4 * raster, // controllpoint
				center.x + 9 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 12 * raster, center.y - 0.5f * raster, // controllpoint
				center.x + 9 * raster, center.y + 1 * raster); // Zielpunkt
		// maul
		quadTo(center.x + 7 * raster, center.y - 1 * raster, // controllpoint
				center.x + 5 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x + 5 * raster, center.y + 1 * raster, // controllpoint
				center.x + 6 * raster, center.y + 3 * raster); // Zielpunkt
		// bauch bis flosse unten
		quadTo(center.x + 4 * raster, center.y + 4 * raster, // controllpoint
				center.x + 2 * raster, center.y + 4 * raster); // Zielpunkt
		// flosse unten
		quadTo(center.x + 2 * raster, center.y + 5 * raster, // controllpoint
				center.x + 0 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y + 5 * raster, // controllpoint
				center.x + 0 * raster, center.y + 4 * raster); // Zielpunkt
		// bauch hinten
		quadTo(center.x - 6 * raster, center.y + 4 * raster, // controllpoint
				center.x - 10 * raster, center.y + 1 * raster); // Zielpunkt
		// flosse hinten
		quadTo(center.x - 11.5f * raster, center.y + 2.5f * raster, // controllpoint
				center.x - 12 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 10 * raster, center.y + 0 * raster, // controllpoint
				center.x - 12 * raster, center.y - 4 * raster); // Zielpunkt
		quadTo(center.x - 10 * raster, center.y - 2 * raster, // controllpoint
				center.x - 10 * raster, center.y - 0 * raster); // Zielpunkt
		close();

		// Zähne oben
		moveTo(center.x + 9 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x + 7 * raster, center.y - 1 * raster, // controllpoint
				center.x + 5 * raster, center.y - 1 * raster); // Zielpunkt
		lineTo(center.x + 5.5f * raster, center.y - 0.5f * raster);
		lineTo(center.x + 6.0f * raster, center.y - 0.8f * raster);
		lineTo(center.x + 6.5f * raster, center.y - 0.0f * raster);
		lineTo(center.x + 7.0f * raster, center.y - 0.3f * raster);
		lineTo(center.x + 7.5f * raster, center.y + 0.5f * raster);
		lineTo(center.x + 8.0f * raster, center.y + 0.2f * raster);
		lineTo(center.x + 8.3f * raster, center.y + 0.9f * raster);
		lineTo(center.x + 8.8f * raster, center.y + 0.8f * raster);
		lineTo(center.x + 9 * raster, center.y + 1 * raster); // Zielpunkt

		close();

		moveTo(center.x + 5 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x + 5 * raster, center.y + 1 * raster, // controllpoint
				center.x + 6 * raster, center.y + 3 * raster); // Zielpunkt
		lineTo(center.x + 6.5f * raster, center.y + 2.0f * raster);
		lineTo(center.x + 6.0f * raster, center.y + 2.0f * raster);
		lineTo(center.x + 6.4f * raster, center.y + 1.3f * raster);
		lineTo(center.x + 5.5f * raster, center.y + 1.0f * raster);
		lineTo(center.x + 6.0f * raster, center.y + 0.5f * raster);
		lineTo(center.x + 5.1f * raster, center.y + 0.0f * raster);
		close();

		// auge
		moveTo(center.x + 7 * raster, center.y - 2 * raster);
		lineTo(center.x + 5 * raster, center.y - 3 * raster);
		lineTo(center.x + 5.5f * raster, center.y - 2.0f * raster);
		// lineTo(center.x + 6.5f * raster, center.y - 1.5f * raster);
		close();
		addCircle(center.x + 5.7f * raster, center.y - 2.4f * raster, raster * 0.30f, Direction.CCW);

	}
}
