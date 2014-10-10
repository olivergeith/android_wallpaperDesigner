package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class HeartPath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param rOuter
	 *            Radius von 1f bis 5f....
	 */
	public HeartPath(final Point center, final float rOuter, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawHeartV1(center, rOuter);
			break;
		case "V2":
			drawHeartV2(center, rOuter);
			break;
		}
	}

	private void drawHeartV1(final Point center, final float rOuter) {
		final int radiusfactor = 15;
		final int ecken = 100;
		final float angle = (float) (2 * Math.PI / ecken);

		for (int i = 0; i <= ecken; i++) {
			final PointF p = new PointF();

			final float t = i * angle;
			p.x = (float) (rOuter * (12 * Math.sin(t) - 4 * Math.sin(3 * t)) / radiusfactor);
			p.y = (float) (rOuter * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)) / radiusfactor);

			p.x = p.x + center.x;
			p.y = -p.y + center.y;
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		close();
	}

	private void drawHeartV2(final Point center, final float radius) {
		final float raster = radius / 2;

		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x - 0 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		arcTo(oval, -225, 225);

		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		arcTo(oval, -180, 225);

		lineTo(center.x - 0 * raster, center.y + 2.5f * raster);

		close();
	}

}
