package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class PlanePath extends Path {

	public PlanePath(final Point center, final float radius, final int variante) {
		super();

		switch (variante) {
		default:
		case 1:
			drawPlaneV1(center, radius);
			break;
		}
	}

	private void drawPlaneV1(final Point center, final float radius) {
		final float raster = radius / 6;

		// Spitze von da clockwise
		moveTo(center.x - 0 * raster, center.y - 5 * raster);
		lineTo(center.x + 0.3f * raster, center.y - 4 * raster);
		lineTo(center.x + 0.8f * raster, center.y - 4 * raster);
		lineTo(center.x + 1.0f * raster, center.y - 3.8f * raster);
		lineTo(center.x + 1.0f * raster, center.y - 2 * raster);
		// Flügel
		lineTo(center.x + 7.0f * raster, center.y - 2 * raster);
		lineTo(center.x + 7.3f * raster, center.y - 0.5f * raster);
		// lineTo(center.x + 6.0f * raster, center.y - 0.0f * raster);
		lineTo(center.x + 1.0f * raster, center.y - 0.0f * raster);
		// rumpf hinten
		lineTo(center.x + 1.0f * raster, center.y + 1.0f * raster);
		lineTo(center.x + 0.3f * raster, center.y + 5.0f * raster);
		// flügel hinten
		lineTo(center.x + 2.0f * raster, center.y + 5.3f * raster);
		lineTo(center.x + 2.0f * raster, center.y + 6.0f * raster);
		lineTo(center.x + 0.15f * raster, center.y + 6.0f * raster);
		// spitze hinten
		lineTo(center.x + 0.0f * raster, center.y + 7.0f * raster);
		lineTo(center.x - 0.15f * raster, center.y + 6.0f * raster);
		// flügel hinten
		lineTo(center.x - 2.0f * raster, center.y + 6.0f * raster);
		lineTo(center.x - 2.0f * raster, center.y + 5.3f * raster);
		lineTo(center.x - 0.3f * raster, center.y + 5.0f * raster);
		// rumpf hinten
		lineTo(center.x - 1.0f * raster, center.y + 1.0f * raster);
		lineTo(center.x - 1.0f * raster, center.y + 0.0f * raster);
		// Flügel
		// lineTo(center.x - 6.0f * raster, center.y - 0.0f * raster);
		lineTo(center.x - 7.3f * raster, center.y - 0.5f * raster);
		lineTo(center.x - 7.0f * raster, center.y - 2 * raster);
		lineTo(center.x - 1.0f * raster, center.y - 2 * raster);
		// Schnauze
		lineTo(center.x - 1.0f * raster, center.y - 3.8f * raster);
		lineTo(center.x - 0.8f * raster, center.y - 4 * raster);
		lineTo(center.x - 0.3f * raster, center.y - 4 * raster);
		lineTo(center.x - 0 * raster, center.y - 5 * raster);
		close();
		// Propeller
		addRect(center.x - 2.5f * raster, center.y - 4.7f * raster, center.x - 0.4f * raster, center.y - 4.4f * raster, Direction.CW);
		addRect(center.x + 0.4f * raster, center.y - 4.7f * raster, center.x + 2.5f * raster, center.y - 4.4f * raster, Direction.CW);

	}
}
