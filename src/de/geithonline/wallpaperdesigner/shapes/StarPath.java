package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class StarPath extends Path {

	public enum STAR_TYPE {
		NORMAL, SPIKEY, STAR_CIRCLE;
	}

	public StarPath(final int arms, final PointF c, final float radius, final STAR_TYPE type, final boolean filled) {
		super();
		switch (type) {
		default:
		case NORMAL:
			drawStarOutline(arms, c, radius, radius * 0.4f, filled);
			break;
		case SPIKEY:
			drawStarOutline(arms, c, radius, radius * 0.25f, filled);
			break;
		case STAR_CIRCLE:
			drawStarSpiral(c, radius, filled);
			break;

		}
	}

	public StarPath(final int arms, final PointF c, final float rOuter, final float rInner, final boolean filled) {
		super();
		drawStar(arms, c, rOuter, rInner, filled);
	}

	private void drawStar(final int arms, final PointF c, final float rOuter, final float rInner, final boolean filled) {
		final float angle = (float) (Math.PI / arms);
		final float rInner2 = rInner * 0.75f;

		for (int i = 0; i <= 2 * arms; i++) {
			float r;
			r = (i & 1) == 0 ? rInner : rOuter;
			final Point p = new Point();
			p.x = (int) (c.x + Math.cos(i * angle) * r);
			p.y = (int) (c.y + Math.sin(i * angle) * r);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		if (!filled) {
			addCircle(c.x, c.y, rInner2, Direction.CCW);
		}

		close();
	}

	private void drawStarOutline(final int arms, final PointF c, final float rOuter, final float rInner,
			final boolean filled) {
		final float angle = (float) (Math.PI / arms);
		for (int i = 0; i <= 2 * arms; i++) {
			float r;
			r = (i & 1) == 0 ? rInner : rOuter;
			final Point p = new Point();
			p.x = (int) (c.x + Math.cos(i * angle) * r);
			p.y = (int) (c.y + Math.sin(i * angle) * r);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		close();
		if (!filled) {
			for (int i = 0; i <= 2 * arms; i++) {
				float r;
				r = (i & 1) == 0 ? rInner : rOuter;
				final Point p = new Point();
				p.x = (int) (c.x + Math.cos(i * -angle) * r / 2);
				p.y = (int) (c.y + Math.sin(i * -angle) * r / 2);
				if (i == 0) {
					moveTo(p.x, p.y);
				} else {
					lineTo(p.x, p.y);
				}
			}
			close();
		}

	}

	private void drawStarSpiral(final PointF center, final float rOuter, final boolean filled) {
		final int circles = 1;
		final int ecken = circles * 10;
		final float angle;
		angle = (float) (2 * Math.PI * circles / ecken);

		for (int i = 0; i <= ecken; i++) {
			final float r = rOuter;// * i / ecken;

			final PointF p = new PointF();
			p.x = (float) (center.x + Math.cos(i * angle) * r);
			p.y = (float) (center.y + Math.sin(i * angle) * r);
			final float ro = r * 0.35f;
			addPath(new StarPath(5, p, ro, ro / 2, true));
		}
		if (filled) {
			final float ri = rOuter * 0.7f;
			addPath(new StarPath(5, center, ri, ri / 2, true));
		}
	}

}
