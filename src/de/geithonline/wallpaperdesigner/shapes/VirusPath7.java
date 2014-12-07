package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class VirusPath7 extends Path {

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath7(final Point center, final float radius, final boolean filled) {
		super();
		final int arms = 40;
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			float r2 = radius * 0.8f;
			final float innerR = radius * 0.6f;
			if (i % 5 == 0) {
				r2 = radius * 1.0f;
			}
			final Point cp = new Point();
			final Point p = new Point();
			cp.x = (int) (center.x + Math.cos(2 * i * angle) * innerR);
			cp.y = (int) (center.y + Math.sin(2 * i * angle) * innerR);
			p.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * r2);
			p.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * r2);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				// quadTo(p2.x, p2.y, p1.x, p1.y);
				quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		close();
		if (!filled) {
			final float innerRadius = radius * Randomizer.getRandomFloat(0.3f, 0.6f);
			addCircle(center.x, center.y, innerRadius, Direction.CCW);

			for (int i = 0; i <= arms; i++) {
				final float r3 = radius * 1.1f;
				final float bubbleRadius3 = radius * 0.1f;
				if (i % 5 == 0 && i > 0) {
					final Point p4 = new Point();
					p4.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * r3);
					p4.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * r3);
					addCircle(p4.x, p4.y, bubbleRadius3, Direction.CCW);
				}
			}

		}
	}
}
