package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class PillowPath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param rOuter
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final Point center, final float rOuter) {
		super();

		final int ecken = 100;
		final float angle = (float) (6 * Math.PI / ecken);

		final float k = 4f / 3f; // 5 durch 6-7-8-9 machen sinn!

		for (int i = 0; i <= ecken; i++) {
			final PointF p = new PointF();

			final float t = i * angle;
			p.x = (float) (rOuter * ((k - 1) * Math.cos(t) + Math.cos((k - 1) * t)));
			p.y = (float) (rOuter * ((k - 1) * Math.sin(t) - Math.sin((k - 1) * t)));
			;

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
