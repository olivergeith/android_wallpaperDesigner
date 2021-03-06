package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class MaterialRaster extends AbstractRaster {
	protected enum MATERIAL_POSITIONING {
		RANDOM, TOWER, CENTER, BOOK, BOOK_REVERSE;
	}

	public MaterialRaster(final int width, final int height, final int radius, final float overlap, final ELayoutVariant positioning,
			final ELayoutSubVariant subVariant) {
		super(radius, overlap, width, height);

		setPositioning(positioning);
		setSubVariant(subVariant);

		final int abstand = Math.round(radius * 2 * overlap);

		final int anzW = width / abstand + 2;
		final int anzH = height / abstand + 2;

		for (int h = 0; h < anzH; h++) {
			for (int w = 0; w < anzW; w++) {
				if (h > anzH / 2 - 2 && h < anzH / 2 + 2) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstand;
					final int y = h * abstand;
					final Point p = new Point(x, y);
					addPoint2List(width, height, p);
				}
			}
		}
	}

}
