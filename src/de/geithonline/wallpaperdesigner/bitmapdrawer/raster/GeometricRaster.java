package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class GeometricRaster extends IRaster {
	protected enum POSITIONING {
		RANDOM, BOOK, BOOK_REVERSE, TOWER, CENTER;
	}

	private final POSITIONING positioning;

	public GeometricRaster(final int width, final int height, final int patternRadius, final float overlap, final POSITIONING positioning,
			final boolean upsidedown) {

		this.positioning = positioning;
		final int abstand = Math.round(patternRadius * 2 * overlap);

		final int anzW = width / abstand + 10;
		final int anzH = height / abstand + 10;

		if (!upsidedown) {
			for (int h = -5; h < anzH; h++) {
				for (int w = -5; w < anzW; w++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstand;
					final int y = h * abstand;
					final Point p = new Point(x, y);
					addPoint2List(width, height, p);
				}
			}
		} else {
			for (int w = -5; w < anzW; w++) {
				for (int h = -5; h < anzH; h++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstand;
					final int y = h * abstand;
					final Point p = new Point(x, y);
					addPoint2List(width, height, p);
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
			case BOOK:
				return drawNextBookPoint();
			case BOOK_REVERSE:
				return drawNextBookPointReverse();
			case TOWER:
				return drawNextTowerPoint();
			case CENTER:
				return drawNextCenterPoint();
		}
	}

}
