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
	}

	public void drawTail(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final int anzTails = 4 + Randomizer.getRandomInt(0, 3);
		for (int i = 0; i < anzTails; i++) {
			final float length = radius * Randomizer.getRandomFloat(0.4f, 2f);
			final int repeats = 1 + Randomizer.getRandomInt(0, 3);
			final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
			final PointF c = new PointF();
			c.x = x - radius / 2 + length;
			c.y = y + Randomizer.getRandomFloat(-radius * 0.25f, radius * 0.25f);
			final Path sinus = new SinusPath(c, length, repeats, amplitude);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			addPath(sinus);
		}
	}

}
