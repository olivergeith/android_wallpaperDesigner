package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.base.CrossPath;

public class RingPath extends Path {

	public RingPath(final Point center, final float rOuter, final float rInner, final boolean filled, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawRingV1(center, rOuter, rInner, filled);
			break;
		case "V2":
			drawRingV2(center, rOuter, rInner, filled);
			break;
		case "V3":
			drawRingV3(center, rOuter, rInner, filled);
			break;
		case "V4":
			drawRingV4(center, rOuter, rInner, filled);
			break;
		case "V5":
			drawRingV5(center, rOuter, rInner, filled);
			break;
		case "V6":
			drawRingV6(center, rOuter, rInner, filled);
			break;
		}
	}

	public void drawRingV1(final Point center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);

		if (!filled) {
			addCircle(center.x, center.y, rInner, Direction.CW);
		}

		final int bubbles = 10;
		final float angle = (float) (Math.PI / bubbles) * 2;
		final float rBubble = (rOuter - rInner) * 0.3f;
		for (int i = 0; i < bubbles; i = i + 1) {

			final float r = rInner + (rOuter - rInner) / 2;

			final Point p = new Point();
			p.x = (int) (center.x + Math.sin(i * angle) * r);
			p.y = (int) (center.y + Math.cos(i * angle) * r);
			addCircle(p.x, p.y, rBubble, Direction.CW);
		}
	}

	public void drawRingV2(final Point center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);

		if (!filled) {
			addCircle(center.x, center.y + 0.9f * rOuter - rInner, rInner, Direction.CW);
		}
	}

	public void drawRingV3(final Point center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);
		if (!filled) {
			boolean dir = false;
			for (int i = 7; i > 0; i--) {
				final float rad = rOuter * i / 8f;
				if (dir == true) {
					addCircle(center.x, center.y, rad, Direction.CCW);
				} else {
					addCircle(center.x, center.y, rad, Direction.CW);
				}
				dir = !dir;
			}
		}
	}

	public void drawRingV4(final Point center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);

		if (!filled) {
			boolean dir = false;
			for (int i = 9; i > 0; i--) {
				final float rad = rOuter * i / 10f;
				if (dir == true) {
					addCircle(center.x, center.y + 0.99f * rOuter - rad, rad, Direction.CCW);
				} else {
					addCircle(center.x, center.y + 0.99f * rOuter - rad, rad, Direction.CW);
				}
				dir = !dir;
			}
		}
	}

	public void drawRingV5(final Point center, final float rOuter, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, rOuter, Direction.CCW);
		if (!filled) {
			addPath(new StarPath(5, new PointF(center.x, center.y), rOuter * 0.8f, rOuter * 0.4f, true, 0));
		}
	}

	public void drawRingV6(final Point center, final float radius, final float rInner, final boolean filled) {
		addCircle(center.x, center.y, radius, Direction.CCW);
		if (!filled) {

			addPath(new CrossPath(new PointF(center.x, center.y), radius * 0.75f, Direction.CW));
		} else {
			addRect(center.x - 0.75f * radius, center.y - 0.25f * radius, center.x + 0.75f * radius, center.y + 0.25f * radius, Direction.CW);
		}
	}

}
