package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class PlanePath extends Path {

	public PlanePath(final Point center, final float radius, final String variante) {
		super();

		switch (variante) {
		default:
		case "Old Planes":
			drawPlaneV1(center, radius);
			break;
		case "Boing":
			drawPlaneV2(center, radius);
			break;
		case "Stealthbomber":
			drawPlaneV3(center, radius);
			break;
		case "Mixed":
			final int v = Randomizer.getRandomInt(0, 3);
			switch (v) {
			default:
			case 1:
				drawPlaneV1(center, radius);
				break;
			case 2:
				drawPlaneV2(center, radius);
				break;
			case 3:
				drawPlaneV3(center, radius);
				break;
			}
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

	private void drawPlaneV2(final Point center, final float radius) {
		final float raster = radius / 6;

		// Spitze von da clockwise
		moveTo(center.x - 0 * raster, center.y - 8 * raster);
		quadTo(center.x + 1.0f * raster, center.y - 7.5f * raster, // controllpoint
				center.x + 1.0f * raster, center.y - 6.0f * raster); // Zielpunkt

		lineTo(center.x + 1.0f * raster, center.y - 3.0f * raster);
		// Flügel
		lineTo(center.x + 8.0f * raster, center.y - 1.0f * raster);
		lineTo(center.x + 8.0f * raster, center.y - 0.0f * raster);
		lineTo(center.x + 1.0f * raster, center.y - 1.0f * raster);
		// Rumpf hinten
		lineTo(center.x + 1.0f * raster, center.y + 4.0f * raster);
		lineTo(center.x + 0.5f * raster, center.y + 6.0f * raster);
		// Flügel hinten
		lineTo(center.x + 3.0f * raster, center.y + 6.5f * raster);
		lineTo(center.x + 3.0f * raster, center.y + 7.5f * raster);
		lineTo(center.x - 3.0f * raster, center.y + 7.5f * raster);
		lineTo(center.x - 3.0f * raster, center.y + 6.5f * raster);
		lineTo(center.x - 0.5f * raster, center.y + 6.0f * raster);
		lineTo(center.x - 1.0f * raster, center.y + 4.0f * raster);
		// Flügel links
		lineTo(center.x - 1.0f * raster, center.y - 1.0f * raster);
		lineTo(center.x - 8.0f * raster, center.y - 0.0f * raster);
		lineTo(center.x - 8.0f * raster, center.y - 1.0f * raster);
		lineTo(center.x - 1.0f * raster, center.y - 3.0f * raster);
		lineTo(center.x - 1.0f * raster, center.y - 6.0f * raster); // Zielpunkt
		// Spitze
		quadTo(center.x - 1.0f * raster, center.y - 7.5f * raster, // controllpoint
				center.x - 0 * raster, center.y - 8 * raster); // Zielpunkt
		close();
		// Heckflosse
		addRect(center.x - 0.15f * raster, center.y + 6.0f * raster, center.x + 0.15f * raster, center.y + 7.8f * raster, Direction.CCW);

	}

	private void drawPlaneV3(final Point center, final float radius) {
		final float raster = radius / 3;

		// Spitze von da clockwise
		moveTo(center.x - 0 * raster, center.y - 3 * raster);
		lineTo(center.x + 6.0f * raster, center.y + 1.0f * raster);
		lineTo(center.x + 5.0f * raster, center.y + 2.0f * raster);
		lineTo(center.x + 3.0f * raster, center.y + 0.5f * raster);
		lineTo(center.x + 2.0f * raster, center.y + 1.5f * raster);
		lineTo(center.x + 1.0f * raster, center.y + 0.5f * raster);
		lineTo(center.x + 0.0f * raster, center.y + 1.5f * raster);
		lineTo(center.x - 1.0f * raster, center.y + 0.5f * raster);
		lineTo(center.x - 2.0f * raster, center.y + 1.5f * raster);
		lineTo(center.x - 3.0f * raster, center.y + 0.5f * raster);
		lineTo(center.x - 5.0f * raster, center.y + 2.0f * raster);
		lineTo(center.x - 6.0f * raster, center.y + 1.0f * raster);
		lineTo(center.x - 0 * raster, center.y - 3 * raster);
		close();

		// Fenster
		moveTo(center.x - 0 * raster, center.y - 2.5f * raster);
		lineTo(center.x - 0.8f * raster, center.y - 2.0f * raster);
		lineTo(center.x + 0.8f * raster, center.y - 2.0f * raster);
		close();

		// Triebwerke
		addRect(center.x - 2.3f * raster, center.y - 1.0f * raster, center.x - 1.5f * raster, center.y - 1.2f * raster, Direction.CW);
		addRect(center.x + 1.5f * raster, center.y - 1.0f * raster, center.x + 2.3f * raster, center.y - 1.2f * raster, Direction.CW);

	}

}
