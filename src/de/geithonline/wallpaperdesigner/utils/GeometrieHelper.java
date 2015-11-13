package de.geithonline.wallpaperdesigner.utils;

import android.graphics.PointF;

public class GeometrieHelper {
	public static float calcDistance(final PointF p1, final PointF p2) {
		final float dx = p2.x - p1.x;
		final float dy = p2.y - p1.y;

		return (float) Math.sqrt(dx * dx + dy * dy);
	}
}
