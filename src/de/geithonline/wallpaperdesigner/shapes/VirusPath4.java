package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class VirusPath4 extends Path {

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath4(final Point center, final float radius) {
		super();
		final int arms = 13; // Randomizer.getRandomInt(7, 11);
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final float r2 = radius;
			float innerR = Randomizer.getRandomFloat(0, radius * 3 / 4);
			if (Randomizer.getRandomBoolean() == true) {
				innerR = 2 * radius - innerR;
			}
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(2 * i * angle) * innerR);
			p1.y = (int) (center.y + Math.sin(2 * i * angle) * innerR);
			p2.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * r2);
			p2.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * r2);
			if (i == 0) {
				moveTo(p2.x, p2.y);
			} else {
				// quadTo(p2.x, p2.y, p1.x, p1.y);
				quadTo(p1.x, p1.y, p2.x, p2.y);
			}
		}
		close();
	}

}
