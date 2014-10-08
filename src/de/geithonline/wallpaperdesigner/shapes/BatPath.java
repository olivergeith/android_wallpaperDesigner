package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class BatPath extends Path {

	public BatPath(final Point center, final float radius, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawBatV1(center, radius);
			break;
		}

	}

	private void drawBatV1(final Point center, final float radius) {
		final float raster = radius / 3;

		final float kopfrad = (float) Math.sqrt(2f * raster * raster);

		moveTo(center.x + 1 * raster, center.y - 1 * raster);
		final RectF oval = new RectF();
		oval.left = center.x - kopfrad;
		oval.right = center.x + kopfrad;
		oval.top = center.y - 2 * raster - kopfrad;
		oval.bottom = center.y - 2 * raster + kopfrad;

		arcTo(oval, 45, -270, false);

		quadTo(center.x - 2.5f * raster, center.y - 0 * raster, // controllpoint
				center.x - 4 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x - 5.0f * raster, center.y - 0 * raster, // controllpoint
				center.x - 5 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x - 4.0f * raster, center.y + 0.5f * raster, // controllpoint
				center.x - 3 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x - 2.0f * raster, center.y + 0.5f * raster, // controllpoint
				center.x - 1 * raster, center.y + 1 * raster); // Zielpunkt
		lineTo(center.x - 0.5f * raster, center.y + 3 * raster);
		lineTo(center.x - 0.0f * raster, center.y + 2 * raster);
		lineTo(center.x + 0.5f * raster, center.y + 3 * raster);
		lineTo(center.x + 1 * raster, center.y + 1 * raster);
		quadTo(center.x + 2.0f * raster, center.y + 0.5f * raster, // controllpoint
				center.x + 3 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x + 4.0f * raster, center.y + 0.5f * raster, // controllpoint
				center.x + 5 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x + 5.0f * raster, center.y + 0 * raster, // controllpoint
				center.x + 4 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x + 2.5f * raster, center.y + 0 * raster, // controllpoint
				center.x + 1 * raster, center.y - 1 * raster); // Zielpunkt
		close();
		// Augen
		addCircle(center.x - 0.5f * raster, center.y - 2.5f * raster, raster * 0.35f, Direction.CW);
		addCircle(center.x + 0.5f * raster, center.y - 2.5f * raster, raster * 0.35f, Direction.CW);

		moveTo(center.x - 0.7f * raster, center.y - 1.5f * raster);
		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x + 0.7f * raster, center.y - 1.5f * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y - 0.5f * raster, // controllpoint
				center.x - 0.7f * raster, center.y - 1.5f * raster); // Zielpunkt
		close();

	}

}
