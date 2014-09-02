package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class DeathstarPath extends Path {

	public DeathstarPath(final Point center, final float radius) {
		super();

		final float raster = radius / 13;
		final RectF oval = new RectF();
		oval.left = center.x - 13 * raster;
		oval.right = center.x + 13 * raster;
		oval.top = center.y - 13 * raster;
		oval.bottom = center.y + 13 * raster;

		// obere Hälfte
		moveTo(center.x + 13 * raster, center.y - 0.5f * raster);
		lineTo(center.x - 13 * raster, center.y - 0.5f * raster);
		arcTo(oval, 181, 116, false);

		lineTo(center.x + 6 * raster, center.y - 10 * raster);
		lineTo(center.x + 5 * raster, center.y - 10 * raster);
		lineTo(center.x + 5 * raster, center.y - 9 * raster);
		lineTo(center.x + 7 * raster, center.y - 9 * raster);
		lineTo(center.x + 7 * raster, center.y - 8 * raster);
		lineTo(center.x + 6 * raster, center.y - 8 * raster);
		lineTo(center.x + 6 * raster, center.y - 7 * raster);
		lineTo(center.x + 8 * raster, center.y - 7 * raster);
		lineTo(center.x + 8 * raster, center.y - 6 * raster);
		lineTo(center.x + 9 * raster, center.y - 6 * raster);
		lineTo(center.x + 9 * raster, center.y - 5 * raster);
		lineTo(center.x + 6 * raster, center.y - 5 * raster);
		lineTo(center.x + 6 * raster, center.y - 4 * raster);
		lineTo(center.x + 10 * raster, center.y - 4 * raster);
		lineTo(center.x + 10 * raster, center.y - 3 * raster);
		lineTo(center.x + 8 * raster, center.y - 3 * raster);
		lineTo(center.x + 8 * raster, center.y - 2 * raster);
		lineTo(center.x + 12 * raster, center.y - 2 * raster);
		arcTo(oval, 355, 4, false);
		close();

		// Untere Hälfte
		moveTo(center.x + 13 * raster, center.y + 0.5f * raster);
		arcTo(oval, 1, 7, false);
		lineTo(center.x + 9 * raster, center.y + 2 * raster);
		lineTo(center.x + 9 * raster, center.y + 3 * raster);
		lineTo(center.x + 11 * raster, center.y + 3 * raster);
		lineTo(center.x + 11 * raster, center.y + 4 * raster);
		lineTo(center.x + 7 * raster, center.y + 4 * raster);
		lineTo(center.x + 7 * raster, center.y + 5 * raster);
		lineTo(center.x + 5 * raster, center.y + 5 * raster);
		lineTo(center.x + 5 * raster, center.y + 6 * raster);
		lineTo(center.x + 8 * raster, center.y + 6 * raster);
		lineTo(center.x + 8 * raster, center.y + 7 * raster);
		lineTo(center.x + 10 * raster, center.y + 7 * raster);
		lineTo(center.x + 10 * raster, center.y + 8 * raster);
		lineTo(center.x + 9 * raster, center.y + 8 * raster);
		lineTo(center.x + 9 * raster, center.y + 9 * raster);
		lineTo(center.x + 6 * raster, center.y + 9 * raster);
		lineTo(center.x + 6 * raster, center.y + 10 * raster);
		lineTo(center.x + 5 * raster, center.y + 10 * raster);
		lineTo(center.x + 5 * raster, center.y + 11 * raster);
		lineTo(center.x + 2 * raster, center.y + 11 * raster);
		lineTo(center.x + 2 * raster, center.y + 12 * raster);
		lineTo(center.x + 4 * raster, center.y + 12 * raster);
		arcTo(oval, 80, 179 - 80, false);
		lineTo(center.x - 13 * raster, center.y + 0.5f * raster);
		lineTo(center.x + 13 * raster, center.y + 0.5f * raster);
		close();

		// Augen
		addCircle(center.x - 4 * raster, center.y - 6 * raster, 3.5f * raster, Direction.CCW);

	}

}
