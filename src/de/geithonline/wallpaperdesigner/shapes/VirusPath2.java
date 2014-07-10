package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class VirusPath2 extends Path {

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath2(final Point center, float radius, final int arms) {
		super();
		radius = radius * 2;
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius / 5;
		for (int i = 0; i <= arms; i++) {
			final float r2 = radius; // Randomizer.getRandomFloat(radius * 2 /
										// 3, radius);
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle + Math.PI) * innerR);
			p1.y = (int) (center.y + Math.sin(i * angle + Math.PI) * innerR);
			p2.x = (int) (center.x + Math.cos((i + 1) * angle) * r2);
			p2.y = (int) (center.y + Math.sin((i + 1) * angle) * r2);
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
