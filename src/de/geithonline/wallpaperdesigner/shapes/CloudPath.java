package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class CloudPath extends Path {

	/**
	 * @param ecken
	 * @param center
	 * @param radius
	 * @param rotate
	 *            0-2pi
	 */
	public CloudPath(final Point center, final float radius, final boolean filled) {
		super();

		final float x = center.x + radius * 3 / 4;
		final float y = center.y;
		moveTo(x, y);

		final RectF oval = new RectF();
		oval.left = center.x + radius * 1 / 2;
		oval.right = center.x + radius;
		oval.top = center.y - radius * 1 / 2;
		oval.bottom = center.y;
		arcTo(oval, 90, -180);

		oval.left = center.x - radius * 1 / 4;
		oval.right = center.x + radius * 3 / 4;
		oval.top = center.y - radius;
		oval.bottom = center.y;
		arcTo(oval, 0, -180);

		oval.left = center.x - radius * 3 / 4;
		oval.right = center.x - radius * 1 / 4;
		oval.top = center.y - radius * 3 / 4;
		oval.bottom = center.y - radius * 1 / 4;
		arcTo(oval, 0, -180);

		oval.left = center.x - radius;
		oval.right = center.x - radius * 1 / 2;
		oval.top = center.y - radius * 1 / 2;
		oval.bottom = center.y;
		arcTo(oval, 270, -180);
		// lineTo(x, y);
		close();
		if (filled) {
			final PointF p = new PointF(center.x, center.y + radius * 0.25f);
			addPath(getBlitz(p, radius / 2));
		}
	}

	private Path getBlitz(final PointF center, final float radius) {
		final Path path = new Path();
		final float raster = radius / 4;
		path.moveTo(center.x + 0 * raster, center.y - 5 * raster);
		path.lineTo(center.x - 1 * raster, center.y - 1 * raster);
		path.lineTo(center.x + 1 * raster, center.y - 1 * raster);
		path.lineTo(center.x - 1 * raster, center.y + 4 * raster);
		path.lineTo(center.x - 0 * raster, center.y + 0 * raster);
		path.lineTo(center.x - 2 * raster, center.y + 0 * raster);
		path.close();
		return path;
	}

}
