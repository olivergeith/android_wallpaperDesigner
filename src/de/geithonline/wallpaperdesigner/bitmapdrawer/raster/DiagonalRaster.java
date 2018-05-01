package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class DiagonalRaster extends AbstractRaster {

	public DiagonalRaster(final int width, final int height, final int radius, final float overlap, final ELayoutVariant positioning,
			final ELayoutSubVariant subVariant) {
		super(radius, overlap, width, height);
		final int abstandX = Math.round(radius * 2 * overlap);
		final int abstandY = abstandX / 2;
		setPositioning(positioning);
		setSubVariant(subVariant);

		final int anzW = width / abstandX + 2 * WIDE_CANVAS_LIMIT + 1;
		final int anzH = height / abstandY + 2 * WIDE_CANVAS_LIMIT + 1;

		for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
			for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
				// random koordinate an der gemalt werden soll
				final int x = w * abstandX + (h % 2) * abstandX / 2;
				final int y = h * abstandY;
				final Point p = new Point(x, y);
				addPoint2List(width, height, p);
			}
		}
	}

}
