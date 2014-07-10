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
		final int arms = 12; // Randomizer.getRandomInt(7, 11);
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final float r2 = Randomizer.getRandomFloat(radius * 0.5f, radius * 1.5f);// radius;
			float innerR = Randomizer.getRandomFloat(0, radius / 4);
			// if (i % 4 == 0) {
			// if (Randomizer.getRandomBoolean() == true) {
			if (Randomizer.getRandomInt(1, 6) == 2) {
				innerR = 2.5f * radius - innerR;
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
