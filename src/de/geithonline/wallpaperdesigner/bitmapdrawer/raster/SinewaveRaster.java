package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinewaveRaster implements IRaster {
	protected enum POSITIONING_SINEWAVE {
		RANDOM, BOOK, BOOK_REVERSE, TOWER, CENTER;
	}

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;
	private final POSITIONING_SINEWAVE positioning;

	public SinewaveRaster(final int width, final int height, final int patternRadius, final float overlap, final POSITIONING_SINEWAVE positioning,
			final int numberOfWaves) {

		this.positioning = positioning;
		for (int n = 0; n < numberOfWaves; n++) {

			final double frequency = Randomizer.getRandomFloat(1, 10); // 1 will bin one sinuswawe from left to right
			final int abstand = (int) Math.round(patternRadius * 2 * overlap / frequency);
			final int anzW = width / abstand + 2;

			int amplitude = 0;
			if (Randomizer.getRandomBoolean()) {
				amplitude = Randomizer.getRandomInt(0, height / 2);
			} else {
				amplitude = -Randomizer.getRandomInt(0, height / 2);
			}

			final int zeroLine = height / 2;

			for (int w = 0; w < anzW; w++) {
				// random koordinate an der gemalt werden soll
				final int x = w * abstand;
				final int y = (int) (-amplitude * Math.sin(frequency * x * 2 * Math.PI / width)) + zeroLine;
				final Point p = new Point(x, y);
				points.add(p);
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
