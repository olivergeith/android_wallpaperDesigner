package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class ShellV6Path extends Path {

	public ShellV6Path(final int circles, final int abschnitte, final Point center, final float rOuter) {
		super();
		drawSchnecke(circles, abschnitte, center, rOuter);
	}

	private void drawSchnecke(final int circles, final int abschnitte, final Point center, final float rOuter) {

		final int ecken = circles * abschnitte;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);

		for (int i = 0; i <= ecken; i++) {
			// innere Punkte
			final float ri = (float) (rOuter * Math.pow(((float) i / ecken), 3f));
			final PointF pi1 = new PointF();
			pi1.x = (float) (center.x + Math.cos(i * angle) * ri);
			pi1.y = (float) (center.y + Math.sin(i * angle) * ri);
			final PointF pi2 = new PointF();
			pi2.x = (float) (center.x + Math.cos((i + 1) * angle) * ri);
			pi2.y = (float) (center.y + Math.sin((i + 1) * angle) * ri);
			// äusere Punkte
			final float ra = (float) (rOuter * Math.pow((((float) i + abschnitte) / ecken), 3f));
			final PointF pa1 = new PointF();
			pa1.x = (float) (center.x + Math.cos(i * angle) * ra);
			pa1.y = (float) (center.y + Math.sin(i * angle) * ra);
			final PointF pa2 = new PointF();
			pa2.x = (float) (center.x + Math.cos((i + 1) * angle) * ra);
			pa2.y = (float) (center.y + Math.sin((i + 1) * angle) * ra);
			// Mittelere
			final float rm = (float) (rOuter * Math.pow(((i + abschnitte * 0.5f) / ecken), 3f));
			final PointF pm2 = new PointF();
			pm2.x = (float) (center.x + Math.cos((i + 0.8f) * angle) * rm);
			pm2.y = (float) (center.y + Math.sin((i + 0.8f) * angle) * rm);

			// Control Points
			final PointF cpa1 = new PointF();
			final PointF cpi1 = new PointF();
			final PointF cpa2 = new PointF();
			final float rc = (float) (rOuter * Math.pow(((i + abschnitte * 0.5f) / ecken), 3f));
			final float rmc = (float) (rOuter * Math.pow(((i + abschnitte * 0.25f) / ecken), 3f));
			final float rci = (float) (rOuter * Math.pow(((i + abschnitte * 0.45f) / ecken), 3f));
			cpa1.x = (float) (center.x + Math.cos((i + 0.2f) * angle) * rmc);
			cpa1.y = (float) (center.y + Math.sin((i + 0.2f) * angle) * rmc);
			cpa2.x = (float) (center.x + Math.cos((i + 0.5f) * angle) * rc);
			cpa2.y = (float) (center.y + Math.sin((i + 0.5f) * angle) * rc);
			cpi1.x = (float) (center.x + Math.cos((i + 0.5f) * angle) * rci);
			cpi1.y = (float) (center.y + Math.sin((i + 0.5f) * angle) * rci);

			final PointF cpm2 = new PointF();
			final float rm2 = (float) (rOuter * Math.pow(((i + abschnitte * 0.75f) / ecken), 3f));
			cpm2.x = (float) (center.x + Math.cos((i + 1.2f) * angle) * rm2);
			cpm2.y = (float) (center.y + Math.sin((i + 1.2f) * angle) * rm2);

			moveTo(pi1.x, pi1.y);
			quadTo(cpa2.x, cpa2.y, pa1.x, pa1.y);
			quadTo(cpi1.x, cpi1.y, pa2.x, pa2.y);
			quadTo(cpm2.x, cpm2.y, pm2.x, pm2.y);
			quadTo(cpa1.x, cpa1.y, pi1.x, pi1.y);
			// quadTo(cpi1.x, cpi1.y, pi1.x, pi1.y);

			// moveTo(pi1.x, pi1.y);
			// quadTo(cpa2.x, cpa2.y, pa1.x, pa1.y);
			// // quadTo(cpa2.x, cpa2.y, pa2.x, pa2.y);
			// quadTo(cpa1.x, cpa1.y, pi2.x, pi2.y);
			// quadTo(cpi1.x, cpi1.y, pi1.x, pi1.y);

			close();
		}
	}

}
