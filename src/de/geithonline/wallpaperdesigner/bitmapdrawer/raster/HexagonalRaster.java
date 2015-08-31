package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class HexagonalRaster implements IRaster {
	protected enum HEX_POSITIONING {
		RANDOM, BOOK, BOOK_REVERSE, TOWER, CENTER;
	}

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;
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
					points.add(p);
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
					points.add(p);
				}
			}
		}
		anzahlPatterns = points.size();
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
				return drawNextBookPointBackward();
			case TOWER:
				return drawNextTowerPoint();
			case CENTER:
				return drawNextCenterPoint();
		}
	}

	@Override
	public int getAnzahlPatterns() {
		return anzahlPatterns;
	}

	private Point drawRandomPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Randomizer.getRandomInt(-1, size - 1);
		final Point p = points.remove(location);
		return p;
	}

	private Point drawNextBookPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = 0;
		final Point p = points.remove(location);
		return p;
	}

	private Point drawNextBookPointBackward() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = size - 1;
		final Point p = points.remove(location);
		return p;
	}

	private boolean top = true;

	private Point drawNextTowerPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		if (top) {
			location = size - 1;
		}
		final Point p = points.remove(location);
		top = !top;
		return p;
	}

	private Point drawNextCenterPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Math.round(size / 2); // aus der mitte nehmen
		final Point p = points.remove(location);
		return p;
	}

}
