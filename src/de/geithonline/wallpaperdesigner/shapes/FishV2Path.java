package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class FishV2Path extends Path {

	public FishV2Path(final Point center, final float radius) {
		super();

		drawFisch(center, radius);

		// drawFisch02(center, radius);
		// drawShark(center, radius);

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
}
