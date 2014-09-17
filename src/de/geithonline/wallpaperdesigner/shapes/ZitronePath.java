package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class ZitronePath extends Path {

	private static final float TEILER = 9.4f;

	public ZitronePath(final Point center, final float rOuter) {
		super();
		drawZitrone(center, rOuter);
	}

	private void drawZitrone(final Point center, final float rOuter) {

		final int ecken = 8;
		final float angle;
		angle = (float) (2 * Math.PI / ecken);

		final float ri = rOuter * 0.1f;
		final float ra = rOuter * 0.75f;
		final float rc = rOuter * 0.82f;

		addCircle(center.x, center.y, rOuter, Direction.CCW);
		addCircle(center.x, center.y, rOuter * 0.85f, Direction.CW);

		for (int i = 0; i <= ecken; i++) {
			// innere Punkte
			final PointF pi = new PointF();
			pi.x = (float) (center.x + Math.cos(i * angle) * ri);
			pi.y = (float) (center.y + Math.sin(i * angle) * ri);
			// äusere Punkte
			final PointF pa1 = new PointF();
			pa1.x = (float) (center.x + Math.cos(i * angle + Math.PI / TEILER) * ra);
			pa1.y = (float) (center.y + Math.sin(i * angle + Math.PI / TEILER) * ra);
			final PointF pa2 = new PointF();
			pa2.x = (float) (center.x + Math.cos(i * angle - Math.PI / TEILER) * ra);
			pa2.y = (float) (center.y + Math.sin(i * angle - Math.PI / TEILER) * ra);

			// Control Points
			final PointF cp = new PointF();
			cp.x = (float) (center.x + Math.cos(i * angle) * rc);
			cp.y = (float) (center.y + Math.sin(i * angle) * rc);

			moveTo(pi.x, pi.y);
			lineTo(pa1.x, pa1.y);
			// lineTo(pa2.x, pa2.y);
			quadTo(cp.x, cp.y, pa2.x, pa2.y);
			lineTo(pi.x, pi.y);
			close();
		}
	}

}
