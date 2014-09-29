package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class RocketPath extends Path {

	public RocketPath(final Point center, final float radius, final boolean filled, final int variante) {
		super();

		switch (variante) {
		case 1:
			drawRocketV1(center, radius, filled);
			break;
		case 2:
			drawRocketV2(center, radius, filled);
			break;
		case 3:
			drawRocketV3(center, radius, filled);
			break;
		}
	}

	private void drawRocketV1(final Point center, final float radius, final boolean filled) {
		final float raster = radius / 6;

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

	private void drawRocketV2(final Point center, final float radius, final boolean filled) {
		final float raster = radius / 5;

		// Rumpf
		moveTo(center.x - 1 * raster, center.y + 2 * raster);
		lineTo(center.x - 1 * raster, center.y - 2 * raster);
		lineTo(center.x - 0 * raster, center.y - 6 * raster);
		lineTo(center.x + 1 * raster, center.y - 2 * raster);
		lineTo(center.x + 1 * raster, center.y + 2 * raster);
		lineTo(center.x + 0.5f * raster, center.y + 3 * raster);
		lineTo(center.x - 0.5f * raster, center.y + 3 * raster);
		lineTo(center.x - 1 * raster, center.y + 2 * raster);
		close();
		// Triebwerk
		moveTo(center.x + 0.5f * raster, center.y + 3 * raster);
		lineTo(center.x + 0.7f * raster, center.y + 3.5f * raster);
		lineTo(center.x - 0.7f * raster, center.y + 3.5f * raster);
		lineTo(center.x - 0.5f * raster, center.y + 3 * raster);
		close();

		// Fenster
		addCircle(center.x + 0 * raster, center.y + 0 * raster, raster * 0.5f, Direction.CCW);
		addCircle(center.x + 0 * raster, center.y - 2 * raster, raster * 0.5f, Direction.CCW);
		// Flügel Links
		moveTo(center.x - 1 * raster, center.y + 2 * raster);
		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		lineTo(center.x - 2 * raster, center.y + 1 * raster);
		lineTo(center.x - 1 * raster, center.y + 0 * raster);
		close();
		moveTo(center.x - 2 * raster, center.y + 3 * raster);
		lineTo(center.x - 2.25f * raster, center.y + 4 * raster);
		lineTo(center.x - 2.5f * raster, center.y + 3 * raster);
		lineTo(center.x - 2.5f * raster, center.y + 1 * raster);
		lineTo(center.x - 2.25f * raster, center.y + 0 * raster);
		lineTo(center.x - 2 * raster, center.y + 1 * raster);
		close();

		// Flügel rechts
		moveTo(center.x + 1 * raster, center.y + 2 * raster);
		lineTo(center.x + 2 * raster, center.y + 3 * raster);
		lineTo(center.x + 2 * raster, center.y + 1 * raster);
		lineTo(center.x + 1 * raster, center.y + 0 * raster);
		close();
		moveTo(center.x + 2 * raster, center.y + 3 * raster);
		lineTo(center.x + 2.25f * raster, center.y + 4 * raster);
		lineTo(center.x + 2.5f * raster, center.y + 3 * raster);
		lineTo(center.x + 2.5f * raster, center.y + 1 * raster);
		lineTo(center.x + 2.25f * raster, center.y + 0 * raster);
		lineTo(center.x + 2 * raster, center.y + 1 * raster);
		close();

		if (filled) {
			// Raketenstrahl
			moveTo(center.x - 0.3f * raster, center.y + 4f * raster);
			lineTo(center.x + 0 * raster, center.y + 7f * raster);
			lineTo(center.x + 0.3f * raster, center.y + 4f * raster);
			close();
			moveTo(center.x - 0.2f * raster, center.y + 8f * raster);
			lineTo(center.x + 0 * raster, center.y + 10f * raster);
			lineTo(center.x + 0.2f * raster, center.y + 8f * raster);
			close();
		}
	}

	private void drawRocketV3(final Point center, final float radius, final boolean filled) {
		final float raster = radius / 7;

		// Rumpf
		moveTo(center.x - 1 * raster, center.y + 2 * raster);
		lineTo(center.x - 2 * raster, center.y - 0 * raster);
		lineTo(center.x - 2 * raster, center.y - 2 * raster);
		quadTo(center.x - 2 * raster, center.y - 4.5f * raster, // controllpoint
				center.x - 0 * raster, center.y - 7 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 4.5f * raster, // controllpoint
				center.x + 2 * raster, center.y - 2 * raster); // Zielpunkt
		lineTo(center.x + 2 * raster, center.y - 0 * raster);
		lineTo(center.x + 1 * raster, center.y + 2 * raster);
		close();

		// Fenster
		addCircle(center.x + 0 * raster, center.y + 0 * raster, raster * 0.5f, Direction.CCW);
		addCircle(center.x + 0 * raster, center.y - 3 * raster, raster * 0.75f, Direction.CCW);

		// Flügel Links
		moveTo(center.x - 1 * raster, center.y + 2 * raster);
		lineTo(center.x - 3 * raster, center.y + 4 * raster);
		lineTo(center.x - 3 * raster, center.y + 1 * raster);
		lineTo(center.x - 2 * raster, center.y + 0 * raster);
		close();
		moveTo(center.x - 3 * raster, center.y + 4 * raster);
		lineTo(center.x - 3 * raster, center.y + 5 * raster);

		quadTo(center.x - 3.5f * raster, center.y + 6f * raster, // controllpoint
				center.x - 4 * raster, center.y + 5 * raster); // Zielpunkt

		// lineTo(center.x - 4 * raster, center.y + 5 * raster);

		lineTo(center.x - 4 * raster, center.y + 1 * raster);
		lineTo(center.x - 3.5f * raster, center.y - 1 * raster);
		lineTo(center.x - 3 * raster, center.y + 1 * raster);
		close();

		// Flügel rechts
		moveTo(center.x + 1 * raster, center.y + 2 * raster);
		lineTo(center.x + 3 * raster, center.y + 4 * raster);
		lineTo(center.x + 3 * raster, center.y + 1 * raster);
		lineTo(center.x + 2 * raster, center.y + 0 * raster);
		close();
		moveTo(center.x + 3 * raster, center.y + 4 * raster);
		lineTo(center.x + 3 * raster, center.y + 5 * raster);
		quadTo(center.x + 3.5f * raster, center.y + 6f * raster, // controllpoint
				center.x + 4 * raster, center.y + 5 * raster); // Zielpunkt
		// lineTo(center.x + 4 * raster, center.y + 5 * raster);
		lineTo(center.x + 4 * raster, center.y + 1 * raster);
		lineTo(center.x + 3.5f * raster, center.y - 1 * raster);
		lineTo(center.x + 3 * raster, center.y + 1 * raster);
		close();

		if (filled) {
			// Raketenstrahl
			moveTo(center.x - 3.2f * raster, center.y + 6 * raster);
			lineTo(center.x - 3.5f * raster, center.y + 9 * raster);
			lineTo(center.x - 3.8f * raster, center.y + 6 * raster);
			close();
			moveTo(center.x + 3.2f * raster, center.y + 6 * raster);
			lineTo(center.x + 3.5f * raster, center.y + 9 * raster);
			lineTo(center.x + 3.8f * raster, center.y + 6 * raster);
			close();
		}
	}

}
