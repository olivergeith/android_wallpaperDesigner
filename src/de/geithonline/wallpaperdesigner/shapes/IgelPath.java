package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class IgelPath extends Path {

	public IgelPath(final Point center, final float radius) {
		super();
		final float raster = radius / 6;
		final int arms = 30;
		final float angle = -(float) (2 * Math.PI / (arms));
		final float cpRadius = radius * 0.5f;
		for (int i = 0; i <= 11; i++) {
			final Point cp1 = new Point();
			final Point p = new Point();
			cp1.x = (int) (center.x + Math.cos((i - 1) * angle) * cpRadius);
			cp1.y = (int) (center.y + Math.sin((i - 1) * angle) * cpRadius);
			p.x = (int) (center.x + Math.cos((i) * angle) * radius);
			p.y = (int) (center.y + Math.sin((i) * angle) * radius);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				quadTo(cp1.x, cp1.y, p.x, p.y);
			}
		}

		final PointF ende = new PointF();
		ende.x = center.x - 3f * raster;
		ende.y = center.y - 3f * raster;

		quadTo(center.x - 3.0f * raster, center.y - 4.0f * raster, // controllpoint
				ende.x, ende.y); // Zielpunkt

		quadTo(center.x - 0 * raster, center.y - 3 * raster, // controllpoint
				center.x - 1 * raster, center.y - 0 * raster); // Zielpunkt
		close();

		moveTo(ende.x, ende.y);
		quadTo(center.x - 0 * raster, center.y - 3 * raster, // controllpoint
				center.x - 1 * raster, center.y - 0 * raster); // Zielpunkt
		lineTo(center.x - 5f * raster, center.y);
		quadTo(center.x - 3f * raster, center.y - 1.0f * raster, // controllpoint
				ende.x, ende.y); // Zielpunkt
		close();

		addCircle(center.x - 2 * raster, center.y - 2 * raster, raster * 0.6f, Direction.CCW);
		addCircle(center.x - 1.9f * raster, center.y - 1.9f * raster, raster * 0.25f, Direction.CW);

	}
}