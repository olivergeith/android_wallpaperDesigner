package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class BubbleCirclePath extends Path {

	public BubbleCirclePath(final int bubbles, final Point center, float radius, final boolean filled) {
		super();
		radius = radius * 2 / 3;
		final float rBubble = radius / 2;
		final float angle = (float) (Math.PI / bubbles) * 2;

		for (int i = 0; i < bubbles; i = i + 1) {

			final float r = radius;

			final Point p = new Point();
			p.x = (int) (center.x + Math.sin(i * angle) * r);
			p.y = (int) (center.y + Math.cos(i * angle) * r);
			// Log.i("Point", "Point " + i + " p=" + p.x + " " + p.y);
			addCircle(p.x, p.y, rBubble, Direction.CCW);
		}
		if (filled) {
			addCircle(center.x, center.y, rBubble, Direction.CCW);
		}
	}
}
