package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class PacmanPath extends Path {

	public PacmanPath(final Point center, final float radius) {
		super();

		final float sinradius = radius * 0.1f;
		final float l = center.x - radius * 0.9f;
		final float r = center.x + radius * 0.9f;
		final float u = center.y + radius - sinradius;
		final float o = center.y - radius;
		final RectF oval = new RectF();
		oval.left = l;
		oval.right = r;
		oval.top = o;
		oval.bottom = u;

		moveTo(r, u);
		lineTo(r, center.y);

		addArc(oval, 0, -180);
		lineTo(l, u);
		final int points = 30;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * 7);
			final float y = u + (float) (sinradius * Math.sin(angle));
			lineTo(x, y);
		}
		close();
		// Augen
		final float alx = center.x - radius * 0.25f;
		final float arx = center.x + radius * 0.45f;
		final float ay = center.y - radius * 0.20f;
		final float alxi = center.x - radius * 0.15f;
		final float arxi = center.x + radius * 0.55f;
		addCircle(alx, ay, radius * 0.25f, Direction.CW);
		addCircle(arx, ay, radius * 0.25f, Direction.CW);
		// inneres der Augen
		addCircle(alxi, ay, radius * 0.12f, Direction.CCW);
		addCircle(arxi, ay, radius * 0.12f, Direction.CCW);
	}

	public PacmanPath(final Point center, final float radius, final boolean pacman) {
		super();

		if (pacman) {
			final float l = center.x - radius;
			final float r = center.x + radius;
			final float u = center.y + radius;
			final float o = center.y - radius;
			final RectF oval = new RectF();
			oval.left = l;
			oval.right = r;
			oval.top = o;
			oval.bottom = u;

			moveTo(center.x, center.y);
			final float mx = (float) (center.x + (radius * Math.sin(2 * Math.PI * 35 / 360)));
			final float my = (float) (center.y - (radius * Math.cos(2 * Math.PI * 35 / 360)));
			// lineTo(mx, my);

			addArc(oval, -35, -290);
			lineTo(center.x, center.y);
			close();

			// Auge
			final float ax = center.x;
			final float ay = center.y - radius * 0.5f;
			final float axi = center.x + radius * 0.05f;
			final float ayi = center.y - radius * 0.5f;
			addCircle(ax, ay, radius * 0.2f, Direction.CW);
			// inneres der Augen
			addCircle(axi, ayi, radius * 0.12f, Direction.CCW);
		} else { // / Ghost
			final float sinradius = radius * 0.1f;
			final float l = center.x - radius * 0.9f;
			final float r = center.x + radius * 0.9f;
			final float u = center.y + radius - sinradius;
			final float o = center.y - radius;
			final RectF oval = new RectF();
			oval.left = l;
			oval.right = r;
			oval.top = o;
			oval.bottom = u;

			moveTo(r, u);
			lineTo(r, center.y);

			addArc(oval, 0, -180);
			lineTo(l, u);
			final int points = 30;
			for (int i = 1; i <= points; i++) {
				final float x = l + i * (r - l) / points;
				final float angle = (float) ((float) i / points * Math.PI * 7);
				final float y = u + (float) (sinradius * Math.sin(angle));
				lineTo(x, y);
			}
			close();
			// Augen
			final float alx = center.x - radius * 0.25f;
			final float arx = center.x + radius * 0.45f;
			final float ay = center.y - radius * 0.20f;
			final float alxi = center.x - radius * 0.15f;
			final float arxi = center.x + radius * 0.55f;
			addCircle(alx, ay, radius * 0.25f, Direction.CW);
			addCircle(arx, ay, radius * 0.25f, Direction.CW);
			// inneres der Augen
			addCircle(alxi, ay, radius * 0.12f, Direction.CCW);
			addCircle(arxi, ay, radius * 0.12f, Direction.CCW);
		}
	}
}
