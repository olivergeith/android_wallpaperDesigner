package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class ShellV2Path extends Path {

	public static final int VARIANTE_FLOWER = 1;
	public static final int VARIANTE_INNER = 0;
	public static final int VARIANTE_OUTER = 2;

	public ShellV2Path(final int abschnitte, final Point center, final float rOuter, final int variante, final boolean filled) {
		super();
		drawSchnecke2(abschnitte, center, rOuter, variante, filled);
	}

	private void drawSchnecke2(final int abschnitte, final Point center, final float rOuter, final int variante, final boolean filled) {

		final int circles = 1;
		final int ecken = circles * abschnitte;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);
		final float ri = rOuter * 0.15f;

		if (filled) {
			addCircle(center.x, center.y, ri * 0.8f, Direction.CCW);
		}

		for (int i = 0; i <= ecken; i++) {
			final float ra = ri + rOuter * ((float) i / ecken); // * ((float) i / ecken);
			final float rc = ri + ra * 0.6f;

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
			switch (variante) {
			case VARIANTE_INNER:
				cpa1.x = (float) (center.x + Math.cos((i + 0.5f) * angle) * rc);
				cpa1.y = (float) (center.y + Math.sin((i + 0.5f) * angle) * rc);
				cpa2.x = (float) (center.x + Math.cos((i - 0.5f) * angle) * rc);
				cpa2.y = (float) (center.y + Math.sin((i - 0.5f) * angle) * rc);

				moveTo(pi1.x, pi1.y);
				quadTo(cpa1.x, cpa1.y, pa2.x, pa2.y);
				quadTo(cpa1.x, cpa1.y, pa1.x, pa1.y);
				quadTo(cpa2.x, cpa2.y, pi1.x, pi1.y);
				close();
				break;
			case VARIANTE_FLOWER:
				cpa1.x = (float) (center.x + Math.cos((i + 1.5f) * angle) * rc);
				cpa1.y = (float) (center.y + Math.sin((i + 1.5f) * angle) * rc);
				cpa2.x = (float) (center.x + Math.cos((i + 0.5f) * angle) * rc);
				cpa2.y = (float) (center.y + Math.sin((i + 0.5f) * angle) * rc);

				moveTo(pi2.x, pi2.y);
				quadTo(cpa2.x, cpa2.y, pa2.x, pa2.y);
				quadTo(cpa2.x, cpa2.y, pa1.x, pa1.y);
				quadTo(cpa2.x, cpa2.y, pi2.x, pi2.y);

				close();
				break;
			default:
			case VARIANTE_OUTER:
				cpa1.x = (float) (center.x + Math.cos((i + 1.5f) * angle) * rc);
				cpa1.y = (float) (center.y + Math.sin((i + 1.5f) * angle) * rc);
				cpa2.x = (float) (center.x + Math.cos((i + 0.5f) * angle) * rc);
				cpa2.y = (float) (center.y + Math.sin((i + 0.5f) * angle) * rc);

				moveTo(pi2.x, pi2.y);
				quadTo(cpa1.x, cpa1.y, pa2.x, pa2.y);
				quadTo(cpa2.x, cpa2.y, pa1.x, pa1.y);
				quadTo(cpa2.x, cpa2.y, pi2.x, pi2.y);

				close();
				break;
			}
		}
	}
}
