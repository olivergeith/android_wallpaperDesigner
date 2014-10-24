package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class UfoPath extends Path {

	public enum UFO_TYPE {
		UfoV1, UfoV2;
	}

	public UfoPath(final Point center, final float radius, final UFO_TYPE variante, final boolean filled) {
		super();

		switch (variante) {
		default:
		case UfoV1:
			drawUFO1(center, radius);
			break;
		case UfoV2:
			drawUFO2(center, radius, filled);
			break;
		}

	}

	private void drawUFO1(final Point center, final float radius) {
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

	private void drawUFO2(final Point center, final float radius, final boolean filled) {
		final float raster = radius / 6;
		// Kabine
		addRect(center.x - 3 * raster, center.y - 4 * raster, center.x + 3 * raster, center.y - 3 * raster, Direction.CW);
		addCircle(center.x - 2.5f * raster, center.y - 3.5f * raster, raster * 0.25f, Direction.CCW);
		addCircle(center.x - 1.5f * raster, center.y - 3.5f * raster, raster * 0.25f, Direction.CCW);
		addCircle(center.x - 0.5f * raster, center.y - 3.5f * raster, raster * 0.25f, Direction.CCW);
		addCircle(center.x + 0.5f * raster, center.y - 3.5f * raster, raster * 0.25f, Direction.CCW);
		addCircle(center.x + 1.5f * raster, center.y - 3.5f * raster, raster * 0.25f, Direction.CCW);
		addCircle(center.x + 2.5f * raster, center.y - 3.5f * raster, raster * 0.25f, Direction.CCW);

		// Rumpf
		moveTo(center.x - 3 * raster, center.y - 3 * raster);
		quadTo(center.x - 5 * raster, center.y - 3 * raster, // controllpoint
				center.x - 6 * raster, center.y - 2 * raster); // Zielpunkt
		lineTo(center.x + 6 * raster, center.y - 2 * raster);
		quadTo(center.x + 5 * raster, center.y - 3 * raster, // controllpoint
				center.x + 3 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		// Deckel
		moveTo(center.x - 3 * raster, center.y - 4 * raster);
		quadTo(center.x + 0 * raster, center.y - 6 * raster, // controllpoint
				center.x + 3 * raster, center.y - 4 * raster); // Zielpunkt
		close();

		// // Bubble unten mitte
		addRect(center.x - 4 * raster, center.y - 2 * raster, center.x + 4 * raster, center.y - 1.5f * raster, Direction.CW);
		addRect(center.x - 3 * raster, center.y - 1.5f * raster, center.x + 3 * raster, center.y - 1.0f * raster, Direction.CW);
		addRect(center.x - 2 * raster, center.y - 1.0f * raster, center.x + 2 * raster, center.y - 0.5f * raster, Direction.CW);

		if (filled) {

			// Beams
			addRect(center.x - 0.05f * raster, center.y - 0 * raster, center.x + 0.05f * raster, center.y + 3.5f * raster, Direction.CW);

			moveTo(center.x - 1 * raster, center.y - 0 * raster);
			lineTo(center.x - 1.5f * raster, center.y + 3 * raster);
			lineTo(center.x - 1.55f * raster, center.y + 3 * raster);
			lineTo(center.x - 1.05f * raster, center.y + 0 * raster);
			close();

			moveTo(center.x - 2 * raster, center.y - 0 * raster);
			lineTo(center.x - 2.7f * raster, center.y + 2 * raster);
			lineTo(center.x - 2.75f * raster, center.y + 2 * raster);
			lineTo(center.x - 2.05f * raster, center.y + 0 * raster);
			close();

			moveTo(center.x + 1 * raster, center.y - 0 * raster);
			lineTo(center.x + 1.5f * raster, center.y + 3 * raster);
			lineTo(center.x + 1.55f * raster, center.y + 3 * raster);
			lineTo(center.x + 1.05f * raster, center.y + 0 * raster);
			close();

			moveTo(center.x + 2 * raster, center.y - 0 * raster);
			lineTo(center.x + 2.7f * raster, center.y + 2 * raster);
			lineTo(center.x + 2.75f * raster, center.y + 2 * raster);
			lineTo(center.x + 2.05f * raster, center.y + 0 * raster);
			close();
		}
	}
}
