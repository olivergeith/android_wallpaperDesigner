package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class RingPath extends Path {

	public RingPath(final Point center, final float rOuter, final float rInner, final boolean filled) {
		super();
		addCircle(center.x, center.y, rOuter, Direction.CCW);
		addCircle(center.x, center.y, rInner, Direction.CW);

		if (!filled) {
			final int bubbles = 10;
			final float angle = (float) (Math.PI / bubbles) * 2;
			final float rBubble = (rOuter - rInner) * 0.3f;
			for (int i = 0; i < bubbles; i = i + 1) {

				final float r = rInner + (rOuter - rInner) / 2;

				final Point p = new Point();
				p.x = (int) (center.x + Math.sin(i * angle) * r);
				p.y = (int) (center.y + Math.cos(i * angle) * r);
				addCircle(p.x, p.y, rBubble, Direction.CW);
			}
		}
	}
}
