package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class XEckPath extends Path {

	/**
	 * @param ecken
	 * @param center
	 * @param rOuter
	 * @param rotate
	 *            0-2pi
	 */
	public XEckPath(final int ecken, final Point center, final float rOuter, final float rotate) {
		super();
		final float angle = (float) (Math.PI / ecken) * 2;

		for (int i = 0; i < ecken; i++) {
			final float r = rOuter;
			// final float r = rInner;

			final Point p = new Point();
			p.x = (int) (center.x + Math.cos(i * angle + rotate) * r);
			p.y = (int) (center.y + Math.sin(i * angle + rotate) * r);
			// Log.i("Point", "Point " + i + " p=" + p.x + " " + p.y);
			if (i == 0) {
				moveTo(p.x, p.y);
				// addCircle(p.x, p.y, 10, Direction.CCW);
			} else {
				lineTo(p.x, p.y);
				// addCircle(p.x, p.y, 5, Direction.CCW);
			}
		}
		close();
	}
}
