package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class MaterialRaster extends IRaster {
	protected enum MATERIAL_POSITIONING {
		RANDOM, TOWER, CENTER, BOOK, BOOK_REVERSE;
	}

	private final MATERIAL_POSITIONING positioning;

	public MaterialRaster(final int width, final int height, final int patternRadius, final float overlap, final MATERIAL_POSITIONING positioning) {

		this.positioning = positioning;
		final int abstand = Math.round(patternRadius * 2 * overlap);

		final int anzW = width / abstand + 2;
		final int anzH = height / abstand + 2;

		for (int h = 0; h < anzH; h++) {
			for (int w = 0; w < anzW; w++) {
				if (h > anzH / 2 - 2 && h < anzH / 2 + 2) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstand;
					final int y = h * abstand;
					final Point p = new Point(x, y);
					points.add(p);
				}
			}
		}
	}

	@Override
	public Point drawNextPoint() {
		switch (positioning) {
			case RANDOM:
				return drawRandomPoint();
			default:
			case TOWER:
				return drawNextTowerPoint();
			case CENTER:
				return drawNextCenterPoint();
			case BOOK:
				return drawNextBookPoint();
			case BOOK_REVERSE:
				return drawNextBookPointReverse();
		}
	}

}
