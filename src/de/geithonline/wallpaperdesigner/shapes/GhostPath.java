package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class GhostPath extends Path {

	public GhostPath(final Point center, final float radius, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawGhostV1(center, radius);
			break;
		case "V2":
			drawGhostV2(center, radius);
			break;
		}

	}

	private void drawGhostV1(final Point center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x - 2 * raster, center.y - 3 * raster);

		quadTo(center.x - 2 * raster, center.y - 6 * raster, // controllpoint
				center.x + 0.5f * raster, center.y - 6 * raster); // Zielpunkt
		quadTo(center.x + 3 * raster, center.y - 6 * raster, // controllpoint
				center.x + 3 * raster, center.y - 3 * raster); // Zielpunkt

		quadTo(center.x + 3 * raster, center.y + 1 * raster, // controllpoint
				center.x + 5 * raster, center.y + 4 * raster); // Zielpunkt
		quadTo(center.x + 6 * raster, center.y + 7 * raster, // controllpoint
				center.x + 3 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y + 5 * raster, // controllpoint
				center.x + 1 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 7 * raster, // controllpoint
				center.x - 1 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 5 * raster, // controllpoint
				center.x - 3 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x - 7 * raster, center.y + 7 * raster, // controllpoint
				center.x - 4 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 1 * raster, // controllpoint
				center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();
		// Augen
		addCircle(center.x - 0.5f * raster, center.y - 4 * raster, raster * 0.8f, Direction.CCW);
		addCircle(center.x + 1.5f * raster, center.y - 4 * raster, raster * 0.8f, Direction.CCW);
		addCircle(center.x - 0.5f * raster, center.y - 3.8f * raster, raster * 0.3f, Direction.CW);
		addCircle(center.x + 1.5f * raster, center.y - 3.8f * raster, raster * 0.3f, Direction.CW);
		// Mund
		moveTo(center.x - 1 * raster, center.y - 1 * raster);
		quadTo(center.x + 0.5f * raster, center.y - 2 * raster, // controllpoint
				center.x + 2 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x + 0.5f * raster, center.y - 2.5f * raster, // controllpoint
				center.x - 1 * raster, center.y - 1 * raster); // Zielpunkt
		close();

	}

	private void drawGhostV2(final Point center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x - 2 * raster, center.y - 3 * raster);

		quadTo(center.x - 2 * raster, center.y - 6 * raster, // controllpoint
				center.x + 0.5f * raster, center.y - 6 * raster); // Zielpunkt
		quadTo(center.x + 3 * raster, center.y - 6 * raster, // controllpoint
				center.x + 3 * raster, center.y - 3 * raster); // Zielpunkt

		quadTo(center.x + 3 * raster, center.y + 1 * raster, // controllpoint
				center.x + 5 * raster, center.y + 4 * raster); // Zielpunkt
		quadTo(center.x + 6 * raster, center.y + 7 * raster, // controllpoint
				center.x + 3 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y + 5 * raster, // controllpoint
				center.x + 1 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 7 * raster, // controllpoint
				center.x - 1 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 5 * raster, // controllpoint
				center.x - 3 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x - 7 * raster, center.y + 7 * raster, // controllpoint
				center.x - 4 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 1 * raster, // controllpoint
				center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();
		// Augen
		addCircle(center.x - 0.5f * raster, center.y - 4 * raster, raster * 0.8f, Direction.CCW);
		addCircle(center.x + 1.5f * raster, center.y - 4 * raster, raster * 0.8f, Direction.CCW);
		// addCircle(center.x - 0.5f * raster, center.y - 3.8f * raster, raster * 0.3f, Direction.CW);
		// addCircle(center.x + 1.5f * raster, center.y - 3.8f * raster, raster * 0.3f, Direction.CW);
		// Mund

		final RectF oval = new RectF();
		oval.left = center.x - 0.5f * raster;
		oval.right = center.x + 1.5f * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 1 * raster;
		addOval(oval, Direction.CCW);
	}

}
