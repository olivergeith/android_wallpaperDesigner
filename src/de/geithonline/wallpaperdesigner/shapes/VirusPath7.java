package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class VirusPath7 extends Path {

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath7(final Point center, final float radius) {
		final int arms = 12;
		final float angle = (float) (2 * Math.PI / (arms));
		final float cpRadius = radius * 0.3f;
		for (int i = 0; i <= arms; i++) {
			final Point cp = new Point();
			final Point p = new Point();
			cp.x = (int) (center.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (int) (center.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			p.x = (int) (center.x + Math.cos((i) * angle) * radius * 1.3f);
			p.y = (int) (center.y + Math.sin((i) * angle) * radius * 1.3f);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		close();

		// final float innerR = radius * 0.5f;
		// for (int i = 0; i <= arms; i++) {
		// final Point cp = new Point();
		// final Point p = new Point();
		// cp.x = (int) (center.x + Math.cos((i - 0.5f) * -angle) * radius);
		// cp.y = (int) (center.y + Math.sin((i - 0.5f) * -angle) * radius);
		// p.x = (int) (center.x + Math.cos((i) * -angle) * innerR);
		// p.y = (int) (center.y + Math.sin((i) * -angle) * innerR);
		// if (i == 0) {
		// moveTo(p.x, p.y);
		// } else {
		// quadTo(cp.x, cp.y, p.x, p.y);
		// }
		// }
		// close();

		final float innerRadius = radius * 0.5f;
		addCircle(center.x, center.y, innerRadius, Direction.CCW);
		// addCircle(center.x, center.y, innerRadius / 2, Direction.CW);

	}
}
