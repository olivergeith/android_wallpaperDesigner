package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class HexagonalRaster extends IRaster {
	protected enum HEX_POSITIONING {
		RANDOM, BOOK, BOOK_REVERSE, TOWER, CENTER;
	}

	private final HEX_POSITIONING positioning;

	public HexagonalRaster(final int width, final int height, final int patternRadius, final float overlap, final HEX_POSITIONING random,
			final boolean upsidedown) {

		positioning = random;
		final int abstandX = Math.round(patternRadius * 2 * overlap);
		final int abstandY = (int) Math.sqrt(abstandX * abstandX - (abstandX / 2) * (abstandX / 2));

		final int anzW = width / abstandX + 2;
		final int anzH = height / abstandY + 2;

		if (!upsidedown) {
			for (int h = 0; h < anzH; h++) {
				for (int w = 0; w < anzW; w++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstandX + (h % 2) * abstandX / 2;
					final int y = h * abstandY;
					final Point p = new Point(x, y);
					if (Settings.isLimit2Canvas() && isInsideCanvas(width, height, p)) {
						points.add(p);
					} else {
						points.add(p);
					}
				}
			}
		} else {
			for (int w = 0; w < anzW; w++) {
				for (int h = 0; h < anzH; h++) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstandX + (h % 2) * abstandX / 2;
					;
					final int y = h * abstandY;
					final Point p = new Point(x, y);
					if (Settings.isLimit2Canvas() && isInsideCanvas(width, height, p)) {
						points.add(p);
					} else {
						points.add(p);
					}
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
