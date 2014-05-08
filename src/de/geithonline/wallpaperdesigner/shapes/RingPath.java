package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class RingPath extends Path {

	public RingPath(final Point center, final float rOuter, final float rInner) {
		super();
		addCircle(center.x, center.y, rOuter, Direction.CCW);
		addCircle(center.x, center.y, rInner, Direction.CW);
	}
}
