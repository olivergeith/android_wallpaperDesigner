package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;

public class IronCrossPath extends Path {

	public IronCrossPath(final PointF center, final float radius, final boolean filled) {
		drawCross(center, radius, filled);
	}

	private void drawCross(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 8;

		final float r2 = 2 * raster;
		final float r3 = 3 * raster;
		final float r5 = 5 * raster;
		final float r8 = radius;

		RectF oval = new RectF();
		PointF c = new PointF();

		moveTo(center.x + 0 * raster, center.y + r8);

		c = new PointF(center.x - r3, center.y + r8);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 0, -90);

		c = new PointF(center.x - r3, center.y + r3);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, 90, -270);

		c = new PointF(center.x - r8, center.y + r3);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 0, -90);
		// ###
		c = new PointF(center.x - r8, center.y - r3);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 90, -90);

		c = new PointF(center.x - r3, center.y - r3);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, 180, -270);

		c = new PointF(center.x - r3, center.y - r8);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 90, -90);

		// ###
		c = new PointF(center.x + r3, center.y - r8);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 180, -90);

		c = new PointF(center.x + r3, center.y - r3);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, -90, -270);

		c = new PointF(center.x + r8, center.y - r3);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 180, -90);

		// // ###
		c = new PointF(center.x + r8, center.y + r3);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, -90, -90);

		c = new PointF(center.x + r3, center.y + r3);
		oval = GeometrieHelper.getCircle(c, r2);
		arcTo(oval, 0, -270);

		c = new PointF(center.x + r3, center.y + r8);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, -90, -90);

		close();

		if (!filled) {
			addCircle(center.x, center.y, raster, Direction.CCW);
		}
	}
}
