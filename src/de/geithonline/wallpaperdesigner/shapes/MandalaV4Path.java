package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class MandalaV4Path extends Path {

	public MandalaV4Path(final int circles, final Point center, final float rOuter) {
		super();
		drawSchnecke(circles, 24, center, rOuter);
	}

	private void drawSchnecke(final int circles, final int abschnitte, final Point center, float rOuter) {

		rOuter = rOuter * 0.66f;
		final int ecken = circles * abschnitte;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);

		boolean dir = false;
		for (int i = 0; i <= ecken; i++) {
			final float ri = (float) (rOuter * Math.pow(((float) i / ecken), 3f));
			final float ra = (float) (rOuter * Math.pow((((float) i + abschnitte) / ecken), 3f));
			// Punkte
			final PointF pi1 = new PointF();
			final float rad = (ra - ri) * 0.5f;
			final float radmin = rad + (ra - ri) * 0.2f;
			final float radbub = (ra - ri) * 0.2f;
			pi1.x = (float) (center.x + Math.cos(i * angle) * (ra + radmin));
			pi1.y = (float) (center.y + Math.sin(i * angle) * (ra + radmin));
			final PointF pa1 = new PointF();
			pa1.x = (float) (center.x + Math.cos(i * angle) * ra);
			pa1.y = (float) (center.y + Math.sin(i * angle) * ra);

			if (dir == false) {
				addCircle(pa1.x, pa1.y, rad, Direction.CW);
				addCircle(pi1.x, pi1.y, radbub * 1.6f, Direction.CCW);
			} else {
				addCircle(pa1.x, pa1.y, rad, Direction.CCW);
				addCircle(pi1.x, pi1.y, radbub * 1.6f, Direction.CW);
			}
			dir = !dir;
			close();
		}
	}

}
