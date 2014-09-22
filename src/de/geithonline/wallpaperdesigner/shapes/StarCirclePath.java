package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class StarCirclePath extends Path {

	public StarCirclePath(final PointF center, final float rOuter, final boolean filled) {
		super();
		drawStarSpiral(center, rOuter, filled);
	}

	private void drawStarSpiral(final PointF center, final float rOuter, final boolean filled) {
		final int circles = 1;
		final int ecken = circles * 10;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);

		for (int i = 0; i <= ecken; i++) {
			final float r = rOuter;// * i / ecken;

			final PointF p = new PointF();
			p.x = (float) (center.x + Math.cos(i * angle) * r);
			p.y = (float) (center.y + Math.sin(i * angle) * r);
			final float ro = r * 0.35f;
			addPath(new StarPath(5, p, ro, ro / 2, true, 0));
		}
		if (filled) {
			final float ri = rOuter * 0.7f;
			addPath(new StarPath(5, center, ri, ri / 2, true, 0));
		}
	}
}
