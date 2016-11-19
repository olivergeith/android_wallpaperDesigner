package de.geithonline.wallpaperdesigner.utils;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class GeometrieHelper {
	public static float calcDistance(final PointF p1, final PointF p2) {
		final float dx = p2.x - p1.x;
		final float dy = p2.y - p1.y;

		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public static float calcDistance(final Point p1, final Point p2) {
		final float dx = p2.x - p1.x;
		final float dy = p2.y - p1.y;

		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public static RectF getCircle(final PointF center, final float radius) {
		return getOval(center, radius, radius);
	}

	public static RectF getOval(final PointF center, final float radiusX, final float radiusY) {
		final RectF oval = new RectF();
		oval.left = center.x - radiusX;
		oval.top = center.y - radiusY;
		oval.right = center.x + radiusX;
		oval.bottom = center.y + radiusY;
		return oval;
	}

	public static float calculateMaxDistanceToCorner(final int width, final int height, final PointF center) {
		float maximumRadius = 0;
		float max = GeometrieHelper.calcDistance(center, new PointF(0f, 0f));
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		max = GeometrieHelper.calcDistance(center, new PointF(width, 0f));
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		max = GeometrieHelper.calcDistance(center, new PointF(0f, height));
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		max = GeometrieHelper.calcDistance(center, new PointF(width, height));
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		return maximumRadius;
	}
}
