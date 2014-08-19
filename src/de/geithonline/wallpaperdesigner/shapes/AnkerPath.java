package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class AnkerPath extends Path {

	public AnkerPath(final Point center, final float radius) {
		super();

		final float raster = radius / 7;

		moveTo(center.x - 0.75f * raster, center.y - 4 * raster);
		lineTo(center.x - 0.75f * raster, center.y - 3 * raster);
		lineTo(center.x - 3 * raster, center.y - 3 * raster);
		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		lineTo(center.x - 0.75f * raster, center.y - 2 * raster);
		lineTo(center.x - 0.75f * raster, center.y + 4 * raster);

		quadTo(center.x - 0.75f * raster, center.y + 7 * raster, // controllpoint
				center.x - 5 * raster, center.y + 2 * raster); // Zielpunkt

		lineTo(center.x - 4.5f * raster, center.y + 1.5f * raster);
		lineTo(center.x - 6 * raster, center.y + 1 * raster);
		lineTo(center.x - 6 * raster, center.y + 3 * raster);
		lineTo(center.x - 5.5f * raster, center.y + 2.5f * raster);

		quadTo(center.x - 4 * raster, center.y + 6 * raster, // controllpoint
				center.x - 0 * raster, center.y + 7 * raster); // Zielpunkt

		// nun nach links
		quadTo(center.x + 4 * raster, center.y + 6 * raster, // controllpoint
				center.x + 5.5f * raster, center.y + 2.5f * raster); // Zielpunkt

		lineTo(center.x + 6 * raster, center.y + 3 * raster);
		lineTo(center.x + 6 * raster, center.y + 1 * raster);
		lineTo(center.x + 4.5f * raster, center.y + 1.5f * raster);
		lineTo(center.x + 5 * raster, center.y + 2 * raster);

		quadTo(center.x + 0.75f * raster, center.y + 7 * raster, // controllpoint
				center.x + 0.75f * raster, center.y + 4 * raster); // Zielpunkt

		lineTo(center.x + 0.75f * raster, center.y - 2 * raster);
		lineTo(center.x + 3 * raster, center.y - 2 * raster);
		lineTo(center.x + 3 * raster, center.y - 3 * raster);
		lineTo(center.x + 0.75f * raster, center.y - 3 * raster);
		lineTo(center.x + 0.75f * raster, center.y - 4 * raster);

		final RectF oval = new RectF();
		oval.top = center.y - 7.5f * raster;
		oval.bottom = center.y - 3.5f * raster;
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;

		arcTo(oval, 45, -270, false);
		close();

		addCircle(center.x, center.y - 5.5f * raster, raster, Direction.CW);
		// addCircle(arxi, ay, radius * 0.12f, Direction.CCW);
	}

}
