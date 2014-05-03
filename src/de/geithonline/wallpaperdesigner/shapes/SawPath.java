package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class SawPath extends Path {

	public SawPath(final int arms, final Point center, final float rOuter, final boolean filled, final boolean inverted) {
		super();
		float angle = (float) (2 * Math.PI / arms);
		if (inverted) {
			angle = (float) -(2 * Math.PI / arms);
		}
		final float rInner = rOuter * 0.8f;
		final float rInner2 = rOuter * 0.7f;

		for (int i = 0; i < arms; i++) {
			// final float r = rInner;

			final Point p = new Point();
			p.x = (int) (center.x + Math.cos(i * angle) * rOuter);
			p.y = (int) (center.y + Math.sin(i * angle) * rOuter);
			final Point p2 = new Point();
			p2.x = (int) (center.x + Math.cos(i * angle) * rInner);
			p2.y = (int) (center.y + Math.sin(i * angle) * rInner);
			// Log.i("Point", "Point " + i + " p=" + p.x + " " + p.y);
			if (i == 0) {
				moveTo(p.x, p.y);
				lineTo(p2.x, p2.y);
				// addCircle(p.x, p.y, 10, Direction.CCW);
			} else {
				lineTo(p.x, p.y);
				lineTo(p2.x, p2.y);
				// addCircle(p.x, p.y, 5, Direction.CCW);
			}
		}
		if (!filled) {
			if (inverted) {
				addCircle(center.x, center.y, rInner2, Direction.CW);
			} else {
				addCircle(center.x, center.y, rInner2, Direction.CCW);
			}
		}

		close();
	}
}
