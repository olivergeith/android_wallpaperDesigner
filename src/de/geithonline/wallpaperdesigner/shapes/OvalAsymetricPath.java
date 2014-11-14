package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class OvalAsymetricPath extends Path {

	public OvalAsymetricPath(final Point center, final float radius, final int height, final boolean filled) {
		super();

		final RectF rect = new RectF();

		rect.left = center.x - radius;
		rect.right = center.x + radius;
		rect.top = center.y - height;
		rect.bottom = center.y;

		addOval(rect, Direction.CW);
		if (!filled) {
			rect.left = center.x - radius / 2;
			rect.right = center.x + radius / 2;
			rect.top = center.y - height * 3 / 4;
			rect.bottom = center.y - height / 4;
			addOval(rect, Direction.CCW);
		}

	}

}
