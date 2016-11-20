package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class GeometricRaster extends AbstractRaster {

	private final int height;
	private final int width;

	public GeometricRaster(final int width, final int height, final int patternRadius, final float overlap, final RasterPositioning positioning,
			final boolean upsidedown) {
		super(patternRadius, overlap);
		this.width = width;
		this.height = height;
		setPositioning(positioning);
		final int abstand = Math.round(patternRadius * 2 * overlap);

		final int anzW = width / abstand + 2 * WIDE_CANVAS_LIMIT + 1;
		final int anzH = height / abstand + 2 * WIDE_CANVAS_LIMIT + 1;

		if (!upsidedown) {
			for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
				for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstand;
					final int y = h * abstand;
					final Point p = new Point(x, y);
					addPoint2List(width, height, p);
				}
			}
		} else {
			for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
				for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
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
		switch (getPositioning()) {
		case RANDOM:
			return drawRandomPoint();
		default:
		case BOOK:
			return drawNextBookPoint();
		case BOOK_REVERSE:
			return drawNextBookPointReverse();
		case INNER:
			return drawPointNearestToGeometricCenter(width, height);
		case OUTER:
			return drawPointFarmostToGeometricCenter(width, height);
		case CENTER:
			return drawNextCenterPoint();
		case DUO_CENTER:
			return drawDuoCenterPoint();
		case TOWER:
			return drawNextTowerPoint();
		case TRISTEP:
			return drawTriStepPoint();
		case QUADSTEP:
			return drawQuadStepPoint();
		}
	}

}
