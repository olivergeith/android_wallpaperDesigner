package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class RosePath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param rOuter
	 *            Radius von 1f bis 5f....
	 */
	public RosePath(final Point center, final float rOuter) {
		super();

		final int ecken = 100;
		final float angle = (float) (2 * Math.PI / ecken);

		final float k = 4f / 1f;

		for (int i = 0; i <= ecken; i++) {
			final float t = i * angle;
			final float r = (float) (rOuter * Math.sin(k * t));

			final PointF p = new PointF();

			p.x = (float) (r * Math.sin(t));
			p.y = (float) (r * Math.cos(t));

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
