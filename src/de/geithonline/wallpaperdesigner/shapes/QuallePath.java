package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath extends Path {

	public enum QUALLE_TYPE {
		qualle, tail;
	}

	public QuallePath(final PointF center, final float radius, final QUALLE_TYPE type) {
		switch (type) {
		default:
		case qualle:
			drawQualle(center, radius);
			break;
		case tail:
			drawTail(center, radius);
			break;
		}
	}

	private void drawQualle(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final PointF c = new PointF(x - radius / 2, y);
		final Path qualle = new CirclePath(c, radius / 2, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
		PathHelper.rotatePath(c.x, c.y, qualle, -90);
		addPath(qualle);

		// Augen
		// final float raster = radius / 8;
		// c.x = center.x - 7 * raster;
		// c.y = center.y - 1 * raster;
		// addPath(new OvalPath(c, raster / 2, raster, Direction.CCW));
		// c.x = center.x - 7 * raster;
		// c.y = center.y + 1 * raster;
		// addPath(new OvalPath(c, raster / 2, raster, Direction.CCW));
		// c.x = center.x - 7 * raster;
		// c.y = center.y;
		// addPath(new OvalPath(c, raster / 2, raster, Direction.CCW));
		// c.x = center.x - 5 * raster;
		// c.y = center.y;
		// addPath(new OvalPath(c, raster / 2, raster, Direction.CCW));
	}

	public void drawTail(final PointF center, final float radius) {
		final PointF c = new PointF();
		// start of tail is radius/2 to the left
		c.x = center.x - radius / 2;
		c.y = center.y;
		addPath(new SinusTailPath(c, radius, 4 + Randomizer.getRandomInt(0, 3), radius * 0.2f));

		// c.x = center.x - radius / 2;
		// c.y = center.y - radius / 2;
		// addPath(new SinusTailPath(c, radius / 3, 3, 0));
		// c.x = center.x - radius / 2;
		// c.y = center.y + radius / 2;
		// addPath(new SinusTailPath(c, radius / 3, 3, 0));

	}

}
