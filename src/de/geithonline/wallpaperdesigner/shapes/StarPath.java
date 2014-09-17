package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class StarPath extends Path {

	public StarPath(final int arms, final PointF c, final float rOuter, final float rInner, final boolean filled, final float rotate) {
		super();
		final float angle = (float) (Math.PI / arms);
		final float rInner2 = rInner * 0.75f;

		for (int i = 0; i <= 2 * arms; i++) {
			float r;
			r = (i & 1) == 0 ? rInner : rOuter;
			// final float r = rInner;

			final Point p = new Point();
			p.x = (int) (c.x + Math.cos(i * angle + rotate) * r);
			p.y = (int) (c.y + Math.sin(i * angle + rotate) * r);
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
			addCircle(c.x, c.y, rInner2, Direction.CCW);
		}

		close();
	}
}
