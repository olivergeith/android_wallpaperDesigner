package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.base.ArowPath;
import de.geithonline.wallpaperdesigner.shapes.base.CrossPath;
import de.geithonline.wallpaperdesigner.shapes.base.MinusPath;

public class InvertablePath extends Path {

	public InvertablePath(final Point center, final float rOuter, final float rInner, final boolean filled, final String variante) {
		super();
		switch (variante) {
		default:
		case "Star":
			drawStar(center, rOuter, rInner, filled);
			break;
		case "Plus":
			drawPlus(center, rOuter, rInner, filled);
			break;
		case "Arrow":
			drawArrow(center, rOuter, rInner, filled);
			break;
		case "Minus":
			drawMinus(center, rOuter, rInner, filled);
			break;
		case "Gear":
			drawGear(center, rOuter, rInner, filled);
			break;
		case "Crown":
			drawCrown(center, rOuter, rInner, filled);
			break;
		}
	}

	public void drawStar(final Point center, final float rOuter, final float rInner, final boolean filled) {
		if (filled) {
			addCircle(center.x, center.y, rOuter, Direction.CCW);
		}
		addPath(new StarPath(5, new PointF(center.x, center.y), rOuter * 0.85f, rOuter * 0.32f, true, 0));
	}

	public void drawPlus(final Point center, final float radius, final float rInner, final boolean filled) {
		// addCircle(center.x, center.y, radius, Direction.CCW);
		// if (!filled) {

		addPath(new CrossPath(new PointF(center.x, center.y), radius * 0.75f, Direction.CW, filled));
		// } else {
		// addRect(center.x - 0.75f * radius, center.y - 0.25f * radius, center.x + 0.75f * radius, center.y + 0.25f * radius, Direction.CW);
		// }
	}

	public void drawArrow(final Point center, final float radius, final float rInner, final boolean filled) {
		// if (!filled) {
		// addCircle(center.x, center.y, radius, Direction.CCW);
		// }
		addPath(new ArowPath(new PointF(center.x, center.y), radius * 0.75f, Direction.CW, filled));
	}

	public void drawGear(final Point center, final float radius, final float rInner, final boolean filled) {
		if (!filled) {
			addCircle(center.x, center.y, radius, Direction.CCW);
		}
		addPath(new GearPath(15, center, radius * 0.75f, !filled));
	}

	public void drawMinus(final Point center, final float radius, final float rInner, final boolean filled) {
		// addCircle(center.x, center.y, radius, Direction.CCW);
		// if (!filled) {

		addPath(new MinusPath(new PointF(center.x, center.y), radius * 0.75f, Direction.CW, filled));
		// } else {
		// addRect(center.x - 0.75f * radius, center.y - 0.25f * radius, center.x + 0.75f * radius, center.y + 0.25f * radius, Direction.CW);
		// }
	}

	public void drawCrown(final Point center, final float radius, final float rInner, final boolean filled) {
		if (!filled) {
			addCircle(center.x, center.y - radius * 0.2f, radius, Direction.CCW);
		}
		addPath(new CrownPath(center, radius * 0.85f, "V1"));
	}

}
