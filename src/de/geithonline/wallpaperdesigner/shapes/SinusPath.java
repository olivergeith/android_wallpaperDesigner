
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinusPath extends Path {

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow/Halbwelle, 2 == a complete sinus
	 * @param amplitude
	 */
	public SinusPath(final PointF center, final float radius, final int sinRepeats, final float amplitude) {
		super();
		drawSinus(center, radius, sinRepeats, amplitude);
	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow, 2 == a complete sinus
	 * @param amplitude
	 */
	private void drawSinus(final PointF center, final float radius, final int sinRepeats, final float amplitude) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 100;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
			final float y = mitteY + (float) (amplitude * Math.sin(angle));
			lineTo(x, y);
		}
	}

	public void drawSinusTail(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final int anzTails = 4 + Randomizer.getRandomInt(0, 3);
		for (int i = 0; i < anzTails; i++) {
			final float length = radius * Randomizer.getRandomFloat(0.4f, 2f);
			final int repeats = 1 + Randomizer.getRandomInt(0, 3);
			final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
			final PointF c = new PointF();
			c.x = x;
			c.y = y; // + Randomizer.getRandomFloat(-radius * 0.25f, radius * 0.25f);
			final Path sinus = new SinusPath(c, length, repeats, amplitude);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			addPath(sinus);
		}
	}

}
