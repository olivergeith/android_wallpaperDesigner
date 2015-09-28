package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinewaveRaster extends IRaster {
	protected enum POSITIONING_SINEWAVE {
		RANDOM, BOOK, BOOK_REVERSE, TOWER, CENTER;
	}

	private final List<Point> points = new ArrayList<Point>();
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
				if (Settings.isLimit2Canvas() && isInsideCanvas(width, height, p)) {
					points.add(p);
				} else {
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
