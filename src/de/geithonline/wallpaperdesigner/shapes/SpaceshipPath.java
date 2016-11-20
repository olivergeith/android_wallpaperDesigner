package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;

public class SpaceshipPath extends Path {

	public SpaceshipPath(final PointF center, final float radius, final boolean filled, final float height) {
		draw(center, radius, filled, height);
	}

	public SpaceshipPath(final PointF center, final float radius, final boolean filled) {
		draw(center, radius, filled, 0);
	}

	private void draw(final PointF center, final float radius, final boolean filled, final float height) {
		final float raster = radius / 6;

		final float r15 = 1.5f * raster;
		final float r2 = 2 * raster;
		final float r4 = 4 * raster;

		RectF oval = new RectF();
		PointF c = new PointF();

		moveTo(center.x, center.y - r4);

		c = new PointF(center.x + r2, center.y - r4);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, 180, -270);

		c = new PointF(center.x + r2, center.y - r2);
		oval = GeometrieHelper.getCircle(c, r4);
		arcTo(oval, -90, 180);

		c = new PointF(center.x, center.y + r2);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, 0, -180);

		c = new PointF(center.x - r2, center.y - r2);
		oval = GeometrieHelper.getCircle(c, r4);
		arcTo(oval, 90, 180);

		c = new PointF(center.x - r2, center.y - r4);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, -90, -270);

		close();

		moveTo(center.x - r15, center.y + r2);
		c = new PointF(center.x, center.y + r2);
		oval = GeometrieHelper.getCircle(c, r15);
		arcTo(oval, -180, 180);
		lineTo(center.x, center.y + radius + height);
		close();

		if (!filled) {
			addCircle(center.x, center.y + r2, raster, Direction.CCW);
			addCircle(center.x, center.y - r15, raster, Direction.CCW);
		}
	}
}
