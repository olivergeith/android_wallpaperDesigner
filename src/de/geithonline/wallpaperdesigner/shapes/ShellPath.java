package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class ShellPath extends Path {

	public ShellPath(final int circles, final int abschnitte, final Point center, final float rOuter) {
		super();
		drawSchnecke(circles, abschnitte, center, rOuter);
	}

	private void drawSchnecke(final int circles, final int abschnitte, final Point center, final float rOuter) {

		final int ecken = circles * abschnitte;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);

		for (int i = 0; i <= ecken; i++) {
			final float ri = (float) (rOuter * Math.pow(((float) i / ecken), 3f));
			final float ra = (float) (rOuter * Math.pow((((float) i + abschnitte) / ecken), 3f));
			final float rc = (float) (rOuter * Math.pow(((i + abschnitte * 0.7f) / ecken), 3f));
			// innere Punkte
			final PointF pi1 = new PointF();
			pi1.x = (float) (center.x + Math.cos(i * angle) * ri);
			pi1.y = (float) (center.y + Math.sin(i * angle) * ri);
			final PointF pi2 = new PointF();
			pi2.x = (float) (center.x + Math.cos((i + 1) * angle) * ri);
			pi2.y = (float) (center.y + Math.sin((i + 1) * angle) * ri);
			// äusere Punkte
			final PointF pa1 = new PointF();
			pa1.x = (float) (center.x + Math.cos(i * angle) * ra);
			pa1.y = (float) (center.y + Math.sin(i * angle) * ra);
			final PointF pa2 = new PointF();
			pa2.x = (float) (center.x + Math.cos((i + 1) * angle) * ra);
			pa2.y = (float) (center.y + Math.sin((i + 1) * angle) * ra);

			final PointF cpa1 = new PointF();
			final PointF cpa2 = new PointF();
			cpa1.x = (float) (center.x + Math.cos((i + 1.5f) * angle) * rc);
			cpa1.y = (float) (center.y + Math.sin((i + 1.5f) * angle) * rc);
			cpa2.x = (float) (center.x + Math.cos((i + 0.5f) * angle) * rc);
			cpa2.y = (float) (center.y + Math.sin((i + 0.5f) * angle) * rc);

			moveTo(pi2.x, pi2.y);
			quadTo(cpa1.x, cpa1.y, pa2.x, pa2.y);
			quadTo(cpa2.x, cpa2.y, pa1.x, pa1.y);
			quadTo(cpa2.x, cpa2.y, pi2.x, pi2.y);

			close();
		}
	}

}
