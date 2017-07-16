
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class QuallePath3 extends QuallePath {

	public QuallePath3(final PointF center, final float radius, final EQualleType type) {
		super(center, radius, type);
	}

	@Override
	protected void drawQualle(final PointF center, final float radius) {
		final Path qualle = new CirclePath(center, radius, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
		PathHelper.rotatePath(center.x, center.y, qualle, -90);
		addPath(qualle);
		op(new OvalPath(center, radius * 0.2f, radius, Direction.CCW), Op.UNION);
	}

	@Override
	protected void drawInnerQualle(final PointF center, final float radius) {
		addPath(new OvalPath(center, radius * 0.2f, radius, Direction.CCW));
		final PointF c1 = new PointF();
		final PointF c2 = new PointF();
		final float raster = radius / 3;
		final float r1 = raster * 0.75f;
		final float r2 = raster * 0.55f;
		Path p1;
		Path p2;
		c1.x = center.x - 2 * raster;
		c1.y = center.y - raster;
		c2.x = center.x - raster * 2.2f;
		c2.y = center.y - raster * 0.8f;
		p1 = new CirclePath(c1, r1);
		p2 = new CirclePath(c2, r2);
		p1.op(p2, Op.DIFFERENCE);
		addPath(p1);
		c1.x = center.x - 2 * raster;
		c1.y = center.y + raster;
		c2.x = center.x - raster * 2.2f;
		c2.y = center.y + raster * 0.8f;
		p1 = new CirclePath(c1, r1);
		p2 = new CirclePath(c2, r2);
		p1.op(p2, Op.DIFFERENCE);
		addPath(p1);
	}

	@Override
	protected void drawBubbleTail(final PointF center, final float radius) {
		super.drawBubbleTail(center, radius);
	}

	@Override
	protected void drawTail(final PointF center, final float radius) {
		super.drawTail(center, radius);
	}

}
