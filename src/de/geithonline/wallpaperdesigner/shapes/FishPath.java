package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class FishPath extends Path {

	public FishPath(final Point center, final float radius) {
		super();

		// if (Randomizer.getRandomInt(0, 50) == 40) {
		// drawShark(center, radius);
		// } else {
		// drawFisch02(center, radius);
		// }

		drawFisch02(center, radius);

	}

	private void drawFisch01(final Point center, final float radius) {
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

	private void drawFisch02(final Point center, final float radius) {
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

	private void drawShark(final Point center, final float radius) {
		final float raster = radius / 4;

		moveTo(center.x - 10 * raster, center.y + 0 * raster);

		quadTo(center.x - 6 * raster, center.y - 4 * raster, // controllpoint
				center.x + 0 * raster, center.y - 4 * raster); // Zielpunkt
		// flosse oben
		quadTo(center.x - 0 * raster, center.y - 6 * raster, // controllpoint
				center.x - 1 * raster, center.y - 7 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 7 * raster, // controllpoint
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
				center.x + 6 * raster, center.y + 2 * raster); // Zielpunkt
		// bauch bis flosse unten
		quadTo(center.x + 4 * raster, center.y + 3 * raster, // controllpoint
				center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt
		// flosse unten
		quadTo(center.x + 2 * raster, center.y + 4 * raster, // controllpoint
				center.x + 0 * raster, center.y + 5 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y + 4 * raster, // controllpoint
				center.x + 0 * raster, center.y + 3 * raster); // Zielpunkt
		// bauch hinten
		quadTo(center.x - 6 * raster, center.y + 3 * raster, // controllpoint
				center.x - 10 * raster, center.y + 1 * raster); // Zielpunkt
		// flosse hinten
		quadTo(center.x - 11.5f * raster, center.y + 2.5f * raster, // controllpoint
				center.x - 12 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 10 * raster, center.y + 0 * raster, // controllpoint
				center.x - 12 * raster, center.y - 4 * raster); // Zielpunkt
		quadTo(center.x - 10 * raster, center.y - 2 * raster, // controllpoint
				center.x - 10 * raster, center.y - 0 * raster); // Zielpunkt
		close();

		addCircle(center.x + 6 * raster, center.y - 2.5f * raster, raster * 0.5f, Direction.CCW);

	}
}
