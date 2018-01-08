package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class RectangleRoundEndPath extends Path {

	public enum RECT_ROUNDEDEND_FILL_STYLE {
		CIRCLE, RECT;
	}

	public RectangleRoundEndPath(final PointF center, final float radiusX, final float radiusY, final boolean filled,
			final RECT_ROUNDEDEND_FILL_STYLE fillstyle) {
		final RectF rect = new RectF();
		rect.right = center.x + radiusX;
		rect.left = center.x - radiusX;
		rect.bottom = center.y + radiusY;
		rect.top = center.y - radiusY;
		addRoundRect(rect, radiusY, radiusY, Direction.CW);

		if (!filled) {
			final float factor = 0.65f;
			final float cornerRad = radiusY * factor;
			switch (fillstyle) {
			case CIRCLE:
				addCircle(center.x - radiusX + radiusY, center.y, cornerRad, Direction.CCW);
				addCircle(center.x + radiusX - radiusY, center.y, cornerRad, Direction.CCW);
				break;
			default:
			case RECT:
				rect.right = center.x + radiusX - radiusY + cornerRad;
				rect.left = center.x - radiusX + radiusY - cornerRad;
				rect.bottom = center.y + cornerRad;
				rect.top = center.y - cornerRad;
				addRoundRect(rect, cornerRad, cornerRad, Direction.CCW);
				break;
			}
		}
	}
}
