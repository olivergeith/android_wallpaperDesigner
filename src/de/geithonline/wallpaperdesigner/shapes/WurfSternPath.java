package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class WurfSternPath extends Path {

	public WurfSternPath(final PointF center, final float radius) {
		draw(center, radius);
	}

	private void draw(final PointF center, final float radius) {
		final Path dreieck = new XEckPath(3, center, radius, (float) (-Math.PI / 6), true);
		addPath(dreieck);
		final XEckPath inner = new XEckPath(3, center, radius / 2, (float) (Math.PI / 6), true);
		for (final PointF p : inner.points) {
			op(new CirclePath(p, radius / 3), Op.DIFFERENCE);
		}
	}
}
