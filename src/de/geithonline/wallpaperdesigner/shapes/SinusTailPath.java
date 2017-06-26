
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinusTailPath extends Path {

	/**
	 * @param center
	 * @param radius
	 * @param numberofTails
	 * @param offset
	 *            0== alllt ails begin at the same point
	 */
	public SinusTailPath(final PointF center, final float radius, final int numberofTails, final float offset) {
		super();
		drawSinusTail(center, radius, numberofTails, offset);
	}

	public void drawSinusTail(final PointF center, final float radius, final int numberofTails, final float offset) {
		final float x = center.x;
		final float y = center.y;
		for (int i = 0; i < numberofTails; i++) {
			final float length = radius * Randomizer.getRandomFloat(0.4f, 2f);
			final int repeats = 1 + Randomizer.getRandomInt(0, 3);
			final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
			final PointF c = new PointF();
			c.x = x + length;
			c.y = y + Randomizer.getRandomFloat(-offset, offset);
			final Path sinus = new SinusPath(c, length, repeats, amplitude);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			addPath(sinus);
		}
	}

}
