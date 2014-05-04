package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class HeartPath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param rOuter
	 *            Radius von 1f bis 5f....
	 */
	public HeartPath(final Point center, final float rOuter) {
		// x(t)=12sin(t)-4sin(3t)
		// y(t)=13cos(t)-5cos(2t)-2cos(3t)-cos(4t)
		super();

		final int ecken = 100;
		final float angle = (float) (2 * Math.PI / ecken);

		for (int i = 0; i <= ecken; i++) {
			final PointF p = new PointF();

			final float t = i * angle;
			p.x = (float) (rOuter * (12 * Math.sin(t) - 4 * Math.sin(3 * t)));
			p.y = (float) (rOuter * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));

			p.x = p.x + center.x;
			p.y = -p.y + center.y;
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		close();
	}
}
