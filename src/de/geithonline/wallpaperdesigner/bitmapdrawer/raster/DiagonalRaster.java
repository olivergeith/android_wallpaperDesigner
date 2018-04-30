package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public class DiagonalRaster extends AbstractRaster {

	public DiagonalRaster(final int width, final int height, final int radius, final float overlap, final RasterPositioning positioning) {
		super(radius, overlap, width, height);
		final int abstandX = Math.round(radius * 2 * overlap);
		final int abstandY = abstandX / 2;
		setPositioning(positioning);

		final int anzW = width / abstandX + 2 * WIDE_CANVAS_LIMIT + 1;
		final int anzH = height / abstandY + 2 * WIDE_CANVAS_LIMIT + 1;

		boolean upsidedown = true;
		switch (positioning) {
			case BOTTOMMOST:
			case TOPMOST:
				upsidedown = false;
				break;
			default:
				upsidedown = true;
				break;
		}
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
				return super.drawNextPoint();
			case LEFTMOST:
			case TOPMOST:
				return drawFirstPoint();
			case RIGHTMOST:
			case BOTTOMMOST:
				return drawLastPoint();
		}
	}

}
