package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class GeometricRaster {
	private final int width;
	private final int height;
	private final int radius;

	private final Point currentPos = new Point(0, 0);
	private final int abstand;

	private final int anzW;
	private final int anzH;

	private final List<Point> points = new ArrayList<Point>();
	private final boolean randomPositioning;
	private final int anzahlPatterns;

	public GeometricRaster(final int width, final int height, final int radius, final float overlap, final boolean randomPositioning) {
		this.randomPositioning = randomPositioning;
		this.width = width;
		this.height = height;
		this.radius = radius;
		abstand = (int) Math.abs(radius * 2 * overlap);

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
		if (randomPositioning) {
			return drawRandomPoint();
		}
		return drawNextPoint();
	}

	public int getAnzahlPatterns() {
		return anzahlPatterns;
	}

	public Point drawRandomPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Randomizer.getRandomInt(-1, size - 1);
		final Point p = points.remove(location);
		return p;
	}

	public Point drawNextPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = 0;
		final Point p = points.remove(location);
		return p;
	}

}
