package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath2 extends Path {

	public enum QUALLE2_TYPE {
		qualle, tail, inner_qualle;
	}

	public QuallePath2(final PointF center, final float radius, final QUALLE2_TYPE type) {
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
		}
	}

	private void drawQualle(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final PointF c = new PointF(x - radius * 0.75f, y);
		addPath(new OvalPath(c, radius / 4, radius / 2, Direction.CCW));
		// c = new PointF(x - radius * 0.62f, y);
		// addPath(new OvalPath(c, radius / 8, radius / 3, Direction.CW));

	}

	private void drawInnerQualle(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final PointF c = new PointF(x - radius * 0.62f, y);
		addPath(new OvalPath(c, radius / 8, radius / 3, Direction.CCW));

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
			c.x = x - radius * 0.6f + length;
			c.y = y + Randomizer.getRandomFloat(-radius * 0.25f, radius * 0.25f);
			final Path sinus = new SinusPath(c, length, repeats, amplitude);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			addPath(sinus);
		}
	}

}
