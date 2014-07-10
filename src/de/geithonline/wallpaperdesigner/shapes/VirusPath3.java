package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class VirusPath3 extends Path {
	/**
	 * More smooth and round no random peeks....and bubbles on arms
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath3(final Point center, float radius, final int arms,
			final boolean filled) {
		super();
		radius = radius * 1.2f;
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius * 1 / 10;
		final float bubbleRadius = radius * 0.12f;

		for (int i = 0; i <= arms; i++) {
			final float r2 = radius; // Randomizer.getRandomFloat(radius * 2 /
										// 3, radius);
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * innerR);
			p1.y = (int) (center.y + Math.sin(i * angle) * innerR);
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

		for (int i = 0; i <= arms; i++) {
			final float r2 = radius; // Randomizer.getRandomFloat(radius * 2 /
										// 3, radius);
			final Point p3 = new Point();
			p3.x = (int) (center.x + Math.cos((i + 1) * angle)
					* (r2 + bubbleRadius));
			p3.y = (int) (center.y + Math.sin((i + 1) * angle)
					* (r2 + bubbleRadius));
			if (i == 0) {
			} else {
				// quadTo(p2.x, p2.y, p1.x, p1.y);
				addCircle(p3.x, p3.y, bubbleRadius, Direction.CCW);
			}
		}
		// cutout inner circle
		if (!filled) {
			addCircle(center.x, center.y, radius * 2 / 10, Direction.CCW);
		}
	}

}
