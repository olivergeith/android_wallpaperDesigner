package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class MandalaV3Path extends Path {

	public MandalaV3Path(final int circles, final int abschnitte, final Point center, final float rOuter) {
		super();
		drawSchnecke(circles, abschnitte, center, rOuter);
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
			pi1.x = (float) (center.x + Math.cos(i * angle) * ri);
			pi1.y = (float) (center.y + Math.sin(i * angle) * ri);
			final PointF pa1 = new PointF();
			pa1.x = (float) (center.x + Math.cos(i * angle) * ra);
			pa1.y = (float) (center.y + Math.sin(i * angle) * ra);

			final float rad = (ra - ri) * 0.7f;
			if (dir == false) {
				addCircle(pa1.x, pa1.y, rad, Direction.CW);
			} else {
				addCircle(pa1.x, pa1.y, rad, Direction.CCW);
			}
			dir = !dir;
			close();
		}
	}

}
