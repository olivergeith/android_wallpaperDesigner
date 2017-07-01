
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath3 extends ComposedPath {

	public QuallePath3(final PointF center, final float radius, final EQualleType type) {
		switch (type) {
		default:
		case qualle:
			drawQualle(center, radius);
			break;
		case inner_qualle:
			drawInnerQualle(center, radius);
			break;
		case tail:
			drawTail(center, radius);
			break;
		case bubbletail:
			drawBubbleTail(center, radius);
			break;
		}
	}

	private void drawQualle(final PointF center, final float radius) {
		final Path qualle = new CirclePath(center, radius * 0.5f, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
		PathHelper.rotatePath(center.x, center.y, qualle, -90);
		addPath(qualle);
		op(new OvalPath(center, radius * 0.1f, radius * 0.5f, Direction.CCW), Op.UNION);
	}

	private void drawInnerQualle(final PointF center, final float radius) {
		addPath(new OvalPath(center, radius * 0.1f, radius * 0.5f, Direction.CCW));

		// Augen
		final PointF c = new PointF();
		final float raster = radius / 8;
		c.x = center.x - 3f * raster;
		c.y = center.y - 1 * raster;
		Path oval = new OvalPath(c, raster * 0.4f, raster, Direction.CCW);
		PathHelper.rotatePath(c.x, c.y, oval, 15);
		addPath(oval);
		c.x = center.x - 3f * raster;
		c.y = center.y + 1 * raster;
		oval = new OvalPath(c, raster * 0.4f, raster, Direction.CCW);
		PathHelper.rotatePath(c.x, c.y, oval, -15);
		addPath(oval);

	}

	public void drawBubbleTail(final PointF center, final float radius) {
		addPath(new MultiSinusObjectsPath(center, radius, radius * 1.3f, radius * 2f, 1, 0, ESinusObjectsType.bubble));
	}

	public void drawTail(final PointF center, final float radius) {
		addPath(new MultiSinusLinesPath(center, radius, radius * 0.9f, radius * 2f, 4 + Randomizer.getRandomInt(1, 3), radius * 0.2f));
	}

}