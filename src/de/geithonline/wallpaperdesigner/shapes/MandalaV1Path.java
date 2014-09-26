package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class MandalaV1Path extends Path {

	public MandalaV1Path(final int abschnitte, final Point center, final float rOuter) {
		super();
		drawCircle(abschnitte, center, rOuter);
	}

	private void drawCircle(final int abschnitte, final Point center, final float rOuter) {

		final int ecken = abschnitte;
		final float angle;
		angle = (float) (2 * Math.PI * 1 / ecken);

		boolean dir = false;
		for (int i = 0; i < ecken; i++) {
			final float ri = rOuter / 2;
			// innere Punkte
			final PointF pi1 = new PointF();
			pi1.x = (float) (center.x + Math.cos(i * angle) * ri);
			pi1.y = (float) (center.y + Math.sin(i * angle) * ri);

			if (dir == false) {
				addCircle(pi1.x, pi1.y, ri, Direction.CW);
			} else {
				addCircle(pi1.x, pi1.y, ri, Direction.CCW);
			}
			dir = !dir;
			close();
		}
	}

}
