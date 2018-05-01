package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class GeometricRaster extends AbstractRaster {

	public GeometricRaster(final int width, final int height, final int radius, final float overlap, final ELayoutVariant positioning,
			final ELayoutSubVariant subVariant) {
		super(radius, overlap, width, height);
		setPositioning(positioning);
		setSubVariant(subVariant);

		final int abstand = Math.round(radius * 2 * overlap);

		final int anzW = width / abstand + 2 * WIDE_CANVAS_LIMIT + 1;
		final int anzH = height / abstand + 2 * WIDE_CANVAS_LIMIT + 1;
		for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
			for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
				// random koordinate an der gemalt werden soll
				final int x = w * abstand;
				final int y = h * abstand;
				final Point p = new Point(x, y);
				addPoint2List(width, height, p);
			}
		}
	}

}
