package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class FishPath extends Path {

	public FishPath(final Point center, final float radius, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawFisch(center, radius);
			break;
		case "V2":
			drawFatAndSlimFisch(center, radius);
			break;
		case "V3":
		case "Shark V1":
			drawShark(center, radius);
			break;
		case "V4":
		case "Shark V2":
			drawSharkV2(center, radius);
			break;
		}

	}

	private void drawFatAndSlimFisch(final Point center, final float radius) {
		final float raster = radius / 3;

		final float bauch = Randomizer.getRandomFloat(1.5f, 3.0f);

		moveTo(center.x - 2 * raster, center.y + 0 * raster);
		quadTo(center.x + 1.5f * raster, center.y - bauch * raster, // controllpoint
				center.x + 3 * raster, center.y + 0 * raster); // Zielpunkt
		quadTo(center.x + 1.5f * raster, center.y + (bauch - 0.5f) * raster, // controllpoint
				center.x - 2 * raster, center.y + 0 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 0.5f * raster, // controllpoint
				center.x - 3 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y - 0 * raster, // controllpoint
				center.x - 3 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y - 0.5f * raster, // controllpoint
				center.x - 2 * raster, center.y + 0 * raster); // Zielpunkt
		close();

		addCircle(center.x + 2 * raster, center.y - 0.3f * raster, raster * 0.25f, Direction.CCW);

	}

	private void drawFisch(final Point center, final float radius) {
		final float raster = radius / 3;

		moveTo(center.x - 1 * raster, center.y + 0 * raster);
		quadTo(center.x + 1.8f * raster, center.y - 2 * raster, // controllpoint
				center.x + 3 * raster, center.y + 0 * raster); // Zielpunkt
		quadTo(center.x + 1.8f * raster, center.y + 2 * raster, // controllpoint
				center.x - 1 * raster, center.y + 0 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y + 0.5f * raster, // controllpoint
				center.x - 2 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y - 0 * raster, // controllpoint
				center.x - 2 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y - 0.5f * raster, // controllpoint
				center.x - 1 * raster, center.y + 0 * raster); // Zielpunkt
		close();

		addCircle(center.x + 2 * raster, center.y - 0.3f * raster, raster * 0.25f, Direction.CCW);

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

	private void drawSharkV2(final Point center, final float radius) {
		final float raster = radius / 4;

		// Nasenspitze
		moveTo(center.x - 4.5f * raster, center.y + 0 * raster);
		// zur oberen Flosse
		quadTo(center.x - 3.0f * raster, center.y - 1 * raster, // controllpoint
				center.x - 1.5f * raster, center.y - 1 * raster); // Zielpunkt
		// obere Flosse
		quadTo(center.x - 1.0f * raster, center.y - 2 * raster, // controllpoint
				center.x - 0 * raster, center.y - 2.3f * raster); // Zielpunkt
		// Zur Schwanzflosse
		quadTo(center.x - 1.0f * raster, center.y - 0.0f * raster, // controllpoint
				center.x + 4.5f * raster, center.y - 0.5f * raster); // Zielpunkt
		// Schwanzflosse oben
		quadTo(center.x + 4.8f * raster, center.y - 1.5f * raster, // controllpoint
				center.x + 6.0f * raster, center.y - 1.8f * raster); // Zielpunkt
		// Schwanzflosse hinten
		quadTo(center.x + 4.0f * raster, center.y - 0.0f * raster, // controllpoint
				center.x + 5.5f * raster, center.y + 1.0f * raster); // Zielpunkt
		// Schwanzflosse unten
		quadTo(center.x + 4.8f * raster, center.y + 0.7f * raster, // controllpoint
				center.x + 4.3f * raster, center.y - 0.0f * raster); // Zielpunkt
		// Bauch hinten
		quadTo(center.x + 2.5f * raster, center.y + 1.0f * raster, // controllpoint
				center.x - 0.3f * raster, center.y + 0.9f * raster); // Zielpunkt
		// Flosse unten
		lineTo(center.x - 0.0f * raster, center.y + 2.0f * raster); // Zielpunkt)
		quadTo(center.x - 0.6f * raster, center.y + 1.5f * raster, // controllpoint
				center.x - 1.0f * raster, center.y + 1.0f * raster); // Zielpunkt
		// chimen
		lineTo(center.x - 1.0f * raster, center.y + 0.0f * raster); // Zielpunkt)
		lineTo(center.x - 1.2f * raster, center.y + 1.0f * raster); // Zielpunkt)
		lineTo(center.x - 1.2f * raster, center.y + 0.0f * raster); // Zielpunkt)
		lineTo(center.x - 1.4f * raster, center.y + 1.0f * raster); // Zielpunkt)
		lineTo(center.x - 1.4f * raster, center.y + 0.0f * raster); // Zielpunkt)
		lineTo(center.x - 1.6f * raster, center.y + 1.0f * raster); // Zielpunkt)

		// Zur Nase zurück
		quadTo(center.x - 3.3f * raster, center.y + 0.9f * raster, // controllpoint
				center.x - 4.5f * raster, center.y + 0 * raster); // Zielpunkt
		close();
		addCircle(center.x - 3.0f * raster, center.y - 0.4f * raster, raster * 0.15f, Direction.CCW);

	}

}
