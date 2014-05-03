package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class StarPath extends Path {

	public StarPath(final int arms, final Point center, final float rOuter, final float rInner, final boolean filled, final boolean inverted) {
		super();
		final float angle = (float) (Math.PI / arms);
		final float rInner2 = rInner * 0.75f;

		for (int i = 0; i <= 2 * arms; i++) {
			float r;
			if (!inverted) {
				r = (i & 1) == 0 ? rInner : rOuter;
			} else {
				r = (i & 1) == 0 ? rOuter : rInner;
			}
			// final float r = rInner;

			final Point p = new Point();
			p.x = (int) (center.x + Math.cos(i * angle) * r);
			p.y = (int) (center.y + Math.sin(i * angle) * r);
			// Log.i("Point", "Point " + i + " p=" + p.x + " " + p.y);
			if (i == 0) {
				moveTo(p.x, p.y);
				// addCircle(p.x, p.y, 10, Direction.CCW);
			} else {
				lineTo(p.x, p.y);
				// addCircle(p.x, p.y, 5, Direction.CCW);
			}
		}
		if (!filled) {
			addCircle(center.x, center.y, rInner2, Direction.CCW);
		}

		close();
	}
}
