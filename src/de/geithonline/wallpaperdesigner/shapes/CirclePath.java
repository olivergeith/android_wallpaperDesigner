package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class CirclePath extends Path {

	public CirclePath(final Point center, final float rOuter, final float rInner, final boolean filled) {
		super();
		drawRingV2(center, rOuter, rInner, filled);
	}

	public void drawRingV2(final Point center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);

		if (!filled) {
			addCircle(center.x, center.y, rInner, Direction.CW);
		}
	}
}
