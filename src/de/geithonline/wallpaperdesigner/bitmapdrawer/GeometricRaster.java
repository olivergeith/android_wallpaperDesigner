package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class GeometricRaster {
	public enum POSITIONING {
		RANDOM, BOOK, BOOK_REVERSE, TOWER;
	}

	private final int width;
	private final int height;
	private final int radius;

	private final Point currentPos = new Point(0, 0);
	private final int abstand;

	private final int anzW;
	private final int anzH;

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;
	private final POSITIONING positioning;

	public GeometricRaster(final int width, final int height, final int radius, final float overlap, final POSITIONING positioning) {

		this.positioning = positioning;
		this.width = width;
		this.height = height;
		this.radius = radius;
		abstand = Math.round(radius * 2 * overlap);

		anzW = width / abstand + 2;
		anzH = height / abstand + 2;

		for (int w = 0; w < getAnzW(); w++) {
			for (int h = 0; h < getAnzH(); h++) {

				// random koordinate an der gemalt werden soll
				final int x = w * getAbstand();
				final int y = h * getAbstand();
				final Point p = new Point(x, y);
				points.add(p);
			}
		}
		anzahlPatterns = points.size();
	}

	public int getAnzW() {
		return anzW;
	}

	public int getAnzH() {
		return anzH;
	}

	public int getAbstand() {
		return abstand;
	}

	public Point drawPoint() {
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
		}
	}

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

}
