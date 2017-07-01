
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class MultiSinusObjectsPath extends ComposedPath {

	/**
	 * @param center
	 * @param radius
	 *            the length of the Tail will be up to 4*radius! ....starting at center!
	 * @param numberofTails
	 * @param offset
	 *            0== alllt ails begin at the same point
	 */
	public MultiSinusObjectsPath(final PointF center, final float radius, final float minLength, final float maxLength, final int numberofTails,
			final float offset, final ESinusObjectsType objectType) {
		super();
		drawSinusBubbleTail(center, radius, minLength, maxLength, numberofTails, offset, objectType);
	}

	private void drawSinusBubbleTail(final PointF center, final float radius, final float minLength, final float maxLength, final int numberofTails,
			final float offset, final ESinusObjectsType objectType) {
		final float x = center.x;
		final float y = center.y;
		for (int i = 0; i < numberofTails; i++) {
			final float length = Randomizer.getRandomFloat(minLength, maxLength);
			final int repeats = Randomizer.getRandomInt(1, 3);
			final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
			final PointF c = new PointF();
			c.x = x + length;
			c.y = y + Randomizer.getRandomFloat(-offset, offset);
			final float maxBubbleRadius = radius * 0.05f;
			final Path sinus = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, 80, ESinusObjectsSizingType.random, objectType);
			if (Randomizer.getRandomBoolean()) {
				PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
			}
			addPath(sinus);
		}
	}

}
