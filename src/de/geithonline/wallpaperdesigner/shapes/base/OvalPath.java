package de.geithonline.wallpaperdesigner.shapes.base;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class OvalPath extends Path {

	public OvalPath(final PointF center, final float radiusW, final float radiusH, final Direction dir) {
		drawOval(center, radiusW, radiusH, dir);
	}

	private void drawOval(final PointF center, final float radiusW, final float radiusH, final Direction dir) {
		final RectF oval = new RectF();
		oval.left = center.x - radiusW;
		oval.right = center.x + radiusW;
		oval.top = center.y - radiusH;
		oval.bottom = center.y + radiusH;
		addOval(oval, dir);
	}

}
