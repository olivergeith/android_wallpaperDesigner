package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class GearPath extends Path {

	public GearPath(final int arms, final Point center, final float radius, final boolean filled) {
		super();

		final float rInner = radius * 0.8f;
		final float rInner2 = radius * 0.5f;
		final float angle = (float) (2 * Math.PI / (arms * 4));

		int c = 0;
		for (int i = 0; i <= arms; i++) {
			for (int j = 0; j < 4; j++) {
				float r;
				switch (j) {
				default:
				case 0:
					r = radius;
					break;
				case 1:
					r = radius;
					break;
				case 2:
					r = rInner;
					break;
				case 3:
					r = rInner;
					break;
				}

				final Point p = new Point();
				p.x = (int) (center.x + Math.cos(c * angle) * r);
				p.y = (int) (center.y + Math.sin(c * angle) * r);
				// Log.i("Point", "Point " + i + " p=" + p.x + " " + p.y);
				if (c == 0) {
					moveTo(p.x, p.y);
					// addCircle(p.x, p.y, 10, Direction.CCW);
				} else {
					lineTo(p.x, p.y);
				}
				c++;
			}
		}
		if (!filled) {
			addCircle(center.x, center.y, rInner2, Direction.CCW);
		}
		close();
	}
}
