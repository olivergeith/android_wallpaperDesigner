package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class DotSpiralPath extends Path {

	public DotSpiralPath(final int circles, final Point center, final float rOuter, final float rotate) {
		super();

		drawDotSpiral(circles, center, rOuter, rotate);
	}

	private void drawDotSpiral(final int circles, final Point center, final float rOuter, final float rotate) {
		final int ecken = circles * 20;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);

		for (int i = 0; i <= ecken; i++) {
			final float r = rOuter * i / ecken;

			final PointF p = new PointF();
			p.x = (float) (center.x + Math.cos(i * angle + rotate) * r);
			p.y = (float) (center.y + Math.sin(i * angle + rotate) * r);
			addCircle(p.x, p.y, r * 0.15f, Direction.CCW);
		}

	}
}
