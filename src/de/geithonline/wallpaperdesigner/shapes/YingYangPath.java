package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class YingYangPath extends Path {

	/**
	 * @param ecken
	 * @param center
	 * @param radius
	 * @param rotate
	 *            0-2pi
	 */
	public YingYangPath(final Point center, final float radius) {
		super();
		final float raster = radius / 2;

		addCircle(center.x - 0 * raster, center.y - 0 * raster, raster * 2.2f, Direction.CCW);

		moveTo(center.x + 0 * raster, center.y - 2 * raster);
		final RectF oval = new RectF();
		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -90, 180);
		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y + 0 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, -90, -180);
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, 90, 180);
		close();

		addCircle(center.x - 0 * raster, center.y - 1 * raster, raster * 0.35f, Direction.CCW);
		addCircle(center.x - 0 * raster, center.y + 1 * raster, raster * 0.35f, Direction.CW);

	}

}
