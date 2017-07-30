
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath extends ComposedPath {

	public Path qualle;
	public Path inner = new Path();
	public ComposedPath tail = new ComposedPath();
	public ComposedPath bubbletail = new ComposedPath();

	public QuallePath(final PointF center, final float radius, final EQualleType type) {
		drawQualle(center, radius, type);
		drawInnerQualle(center, radius, type);
		drawTail(center, radius);
		drawBubbleTail(center, radius);
	}

	protected void drawQualle(final PointF center, final float radius, final EQualleType type) {
		switch (type) {
		default:
		case v1:
			qualle = new CirclePath(center, radius, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
			PathHelper.rotatePath(center.x, center.y, qualle, -90);
			break;
		case v2:
			final float x = center.x;
			final float y = center.y;
			final PointF c = new PointF(x - radius * 0.5f, y);
			qualle = new OvalPath(c, radius * 0.5f, radius, Direction.CCW);
			break;
		case v3:
			qualle = new CirclePath(center, radius, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
			PathHelper.rotatePath(center.x, center.y, qualle, -90);
			addPath(qualle);
			qualle.op(new OvalPath(center, radius * 0.2f, radius, Direction.CCW), Op.UNION);
			break;
		}
		addPath(qualle);
	}

	protected void drawInnerQualle(final PointF center, final float radius, final EQualleType type) {
		switch (type) {
		default:
		case v1:
			break;
		case v2:
			final float x = center.x;
			final float y = center.y;
			final PointF c = new PointF(x - radius * 0.25f, y);
			inner.addPath(new OvalPath(c, radius * 0.25f, radius * 0.66f, Direction.CCW));
			break;
		case v3:
			inner.addPath(new OvalPath(center, radius * 0.2f, radius, Direction.CCW));
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
			inner.addPath(p1);
			c1.x = center.x - 2 * raster;
			c1.y = center.y + raster;
			c2.x = center.x - raster * 2.2f;
			c2.y = center.y + raster * 0.8f;
			p1 = new CirclePath(c1, r1);
			p2 = new CirclePath(c2, r2);
			p1.op(p2, Op.DIFFERENCE);
			inner.addPath(p1);
			break;
		}
		addPath(inner);
	}

	protected void drawBubbleTail(final PointF center, final float radius) {
		final int numberofTails = 4;
		for (int i = 0; i < numberofTails; i++) {
			final float length = Randomizer.getRandomFloat(radius * 2.5f, radius * 4f);
			final int repeats = Randomizer.getRandomInt(1, 3);
			final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
			final PointF c = new PointF();
			c.x = center.x + length;
			c.y = center.y + radius * Randomizer.getRandomFloat(-0.3f, 0.3f);
			final float maxBubbleRadius = radius * 0.05f;
			final Path s = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, 80, ESinusObjectsSizingType.random, ESinusObjectsType.bubble);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, s);
			}
			bubbletail.addPath(s);
		}
		addPath(bubbletail);
	}

	protected void drawTail(final PointF center, final float radius) {
		final float offset = radius * 0.4f;
		for (int i = 0; i < 4 + Randomizer.getRandomInt(1, 3); i++) {
			final float length = Randomizer.getRandomFloat(radius * 2.0f, radius * 4f);
			final int repeats = Randomizer.getRandomInt(1, 3);
			final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
			final PointF c = new PointF();
			c.x = center.x + length;
			c.y = center.y + Randomizer.getRandomFloat(-offset, offset);
			final Path sinus = new SinusPath(c, length, repeats, amplitude);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			tail.addPath(sinus);
		}
		addPath(tail);
	}
}
