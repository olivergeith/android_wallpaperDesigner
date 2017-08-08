package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class HexagonalRaster extends AbstractRaster {

	private final int width;
	private final int height;

	public HexagonalRaster(final int width, final int height, final int patternRadius, final float overlap, final RasterPositioning positioning) {
		super(patternRadius, overlap);
		this.width = width;
		this.height = height;
		boolean upsidedown = true;
		setPositioning(positioning);
		switch (positioning) {
		case BOTTOMMOST:
		case TOPMOST:
			upsidedown = false;
			break;
		default:
			upsidedown = true;
			break;
		}

		final int abstandX = Math.round(patternRadius * 2 * overlap);
		final int abstandY = (int) Math.sqrt(abstandX * abstandX - (abstandX / 2) * (abstandX / 2));

		final int anzW = width / abstandX + 2 * WIDE_CANVAS_LIMIT + 1;
		final int anzH = height / abstandY + 2 * WIDE_CANVAS_LIMIT + 1;

		if (!upsidedown) {
			for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
				for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstandX + (h % 2) * abstandX / 2;
					final int y = h * abstandY;
					final Point p = new Point(x, y);
					addPoint2List(width, height, p);
				}
			}
		} else {
			for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
				for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstandX + (h % 2) * abstandX / 2;
					;
					final int y = h * abstandY;
					final Point p = new Point(x, y);
					addPoint2List(width, height, p);
				}
			}
		}
	}

	@Override
	public Point drawNextPoint() {
		switch (getPositioning()) {
		default:
		case RANDOM:
			return drawRandomPoint();
		// case BOOK:
		// return drawNextBookPoint();
		// case BOOK_REVERSE:
		// return drawNextBookPointReverse();
		case LEFTMOST:
		case TOPMOST:
			return drawNextBookPoint();
		case RIGHTMOST:
		case BOTTOMMOST:
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
