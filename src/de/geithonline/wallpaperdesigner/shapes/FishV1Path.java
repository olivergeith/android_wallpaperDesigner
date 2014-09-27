package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class FishV1Path extends Path {

	public FishV1Path(final Point center, final float radius) {
		super();

		drawFisch(center, radius);

	}

	private void drawFisch(final Point center, final float radius) {
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

}
