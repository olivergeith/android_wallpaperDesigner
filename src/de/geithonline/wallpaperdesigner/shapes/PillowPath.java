package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class PillowPath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final PointF center, final float radius) {
		super();

		final float raster = radius / 2;
		moveTo(center.x - 2 * raster, center.y - 2 * raster);
		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 0 * raster, // controllpoint
				center.x + 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 1 * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y + 0 * raster, // controllpoint
				center.x - 2 * raster, center.y - 2 * raster); // Zielpunkt
		close();
	}

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final int arms, final PointF center, final float radius) {
		super();
		drawPillow(arms, center, radius);
	}

	private void drawPillow(final int arms, final PointF center, float radius) {
		radius = radius * 1.5f;
		final float angle = (float) (2 * Math.PI / (arms));
		final float cpRadius = radius * (arms - 1) * 0.1f;
		for (int i = 0; i <= arms; i++) {
			final Point cp = new Point();
			final Point p = new Point();
			cp.x = (int) (center.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (int) (center.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			p.x = (int) (center.x + Math.cos((i) * angle) * radius);
			p.y = (int) (center.y + Math.sin((i) * angle) * radius);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		close();
	}
}
