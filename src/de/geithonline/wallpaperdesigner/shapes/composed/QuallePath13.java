
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.shapes.SinusPath.SinusType;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath13 extends ComposedPath {

	public QuallePath13(final PointF center, final float radius, final int arms, final EQualleType type) {
		switch (type) {
		default:
		case qualle:
			drawQualle(center, radius);
			break;
		case inner_qualle:
			drawInnerQualle(center, radius);
			break;
		case tail:
			drawTail(center, radius, arms);
			break;
		case bubbletail:
			drawBubbleTail(center, radius, arms);
			break;
		}
	}

	private void drawQualle(final PointF center, final float radius) {
		final Path qualle = new CirclePath(center, radius);
		addPath(qualle);
	}

	private void drawInnerQualle(final PointF center, final float radius) {
		final int anz = Randomizer.getRandomInt(3, 5);
		for (int i = 0; i < anz; i++) {
			final PointF c1 = new PointF();
			final PointF c2 = new PointF();
			final float raster = radius / 2;
			final float r1 = raster * 0.5f;
			final float r2 = raster * 0.35f;
			Path p1;
			Path p2;
			c1.x = center.x + raster;
			c1.y = center.y;
			c2.x = center.x + raster * 0.8f;
			c2.y = center.y;
			p1 = new CirclePath(c1, r1);
			p2 = new CirclePath(c2, r2);
			p1.op(p2, Op.DIFFERENCE);
			PathHelper.rotatePath(center, p1, i * 360 / anz);
			addPath(p1);
		}
	}

	private void drawBubbleTail(final PointF center, final float radius, final int arms) {
		final boolean flip = Randomizer.getRandomBoolean();
		final int anz = 2 + arms;
		for (int i = 0; i < anz; i++) {
			final int repeats = 3; // Randomizer.getRandomInt(1, 4);
			final float amplitude = radius * 0.5f;
			final float length = radius * 4.5f;
			final PointF c = new PointF();
			c.x = center.x + radius * 0.9f + length;
			c.y = center.y;
			final float maxBubbleRadius = radius * 0.3f;
			final Path s = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, 100, ESinusObjectsSizingType.decreasing,
					ESinusObjectsType.bubble);
			if (flip) {
				PathHelper.mirrorPathUpDown(c.x, c.y, s);
			}
			PathHelper.rotatePath(center, s, i * 360 / anz);
			addPath(s);
		}
	}

	public void drawTail(final PointF center, final float radius, final int arms) {
		final boolean flip = Randomizer.getRandomBoolean();
		final int anz = 20 + arms;
		for (int i = 0; i < anz; i++) {
			final int repeats = 2 + Randomizer.getRandomInt(1, 4);
			final float amplitude = radius * 0.3f;
			final float length = radius * Randomizer.getRandomFloat(2.5f, 4.5f);
			final PointF c = new PointF();
			c.x = center.x + radius * 1f + length;
			c.y = center.y;
			final Path s = new SinusPath(c, length, repeats, amplitude, SinusType.decreasingAmplitude);
			if (flip) {
				PathHelper.mirrorPathUpDown(c.x, c.y, s);
			}
			PathHelper.rotatePath(center, s, i * 360 / anz);
			addPath(s);
		}
	}

}
