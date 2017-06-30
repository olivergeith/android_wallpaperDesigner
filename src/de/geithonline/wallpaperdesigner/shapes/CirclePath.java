package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class CirclePath extends Path {

	public enum CIRCLE_STYLE {
		CIRCLE, HALF_CIRCLE;
	}

	public CirclePath(final PointF center, final float radius) {
		drawRingV2(center, radius, 0, true);

	}

	public CirclePath(final PointF center, final float rOuter, final float rInner, final boolean filled, final CIRCLE_STYLE style) {
		super();
		switch (style) {
		default:
		case CIRCLE:
			drawRingV2(center, rOuter, rInner, filled);
			break;
		case HALF_CIRCLE:
			drawHalfCircle(center, rOuter, rInner, filled);
			break;
		}
	}

	private void drawHalfCircle(final PointF center, final float radius, final float rInner, final boolean filled) {
		final RectF rect = new RectF();
		rect.left = center.x - radius;
		rect.right = center.x + radius;
		rect.top = center.y - radius;
		rect.bottom = center.y + radius;
		addArc(rect, 180, 180);

		if (!filled) {
			rect.left = center.x - rInner;
			rect.right = center.x + rInner;
			rect.top = center.y - rInner;
			rect.bottom = center.y + rInner;
			lineTo(center.x + rInner, center.y);
			addArc(rect, 0, -180);
		}
		lineTo(center.x - radius, center.y);
	}

	public void drawRingV2(final PointF center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);

		if (!filled) {
			addCircle(center.x, center.y, rInner, Direction.CW);
		}
	}
}
