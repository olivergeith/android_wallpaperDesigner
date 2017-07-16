
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath extends ComposedPath {

	public QuallePath(final PointF center, final float radius, final EQualleType type) {
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

	protected void drawQualle(final PointF center, final float radius) {
		final Path qualle = new CirclePath(center, radius, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
		PathHelper.rotatePath(center.x, center.y, qualle, -90);
		addPath(qualle);
	}

	protected void drawInnerQualle(final PointF center, final float radius) {
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
			final Path sinus = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, 80, ESinusObjectsSizingType.random,
					ESinusObjectsType.bubble);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			addPath(sinus);
		}
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
			addPath(sinus);
		}
	}
}
