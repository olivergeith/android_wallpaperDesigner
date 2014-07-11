package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class VirusPath6 extends Path {

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath6(final Point center, final float radius, final float rotate) {
		super();
		final int arms = 40;
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			float r2 = radius * 0.7f;
			final float innerR = r2 * 0.9f;
			if (i % 5 == 0) {
				r2 = radius * 1.2f;
			}
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(2 * i * angle + rotate) * innerR);
			p1.y = (int) (center.y + Math.sin(2 * i * angle + rotate) * innerR);
			p2.x = (int) (center.x + Math.cos((2 * i + 1) * angle + rotate) * r2);
			p2.y = (int) (center.y + Math.sin((2 * i + 1) * angle + rotate) * r2);
			if (i == 0) {
				moveTo(p2.x, p2.y);
			} else {
				// quadTo(p2.x, p2.y, p1.x, p1.y);
				quadTo(p1.x, p1.y, p2.x, p2.y);
			}
		}
		close();
		for (int i = 0; i <= arms; i++) {
			final float r2 = radius * 0.4f;
			final float r3 = radius * 0.6f;
			final float bubbleRadius = radius * 0.08f;
			final float bubbleRadius3 = radius * 0.04f;
			if (i % 5 == 0 && i > 0) {
				final Point p3 = new Point();
				p3.x = (int) (center.x + Math.cos((2 * i + 1) * angle + rotate) * r2);
				p3.y = (int) (center.y + Math.sin((2 * i + 1) * angle + rotate) * r2);
				addCircle(p3.x, p3.y, bubbleRadius, Direction.CCW);
				final Point p4 = new Point();
				p4.x = (int) (center.x + Math.cos((2 * i + 1) * angle + rotate) * r3);
				p4.y = (int) (center.y + Math.sin((2 * i + 1) * angle + rotate) * r3);
				addCircle(p4.x, p4.y, bubbleRadius3, Direction.CCW);
			}
		}
	}
}
