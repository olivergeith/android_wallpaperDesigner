package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class StarPathV2 extends Path {

	public StarPathV2(final PointF center, final float rOuter) {
		super();
		final int arms = 5;
		final float angle = (float) (4 * Math.PI / arms);

		for (int i = 0; i <= arms; i++) {
			final Point p = new Point();
			p.x = (int) (center.x + Math.cos(i * angle) * rOuter);
			p.y = (int) (center.y + Math.sin(i * angle) * rOuter);
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
