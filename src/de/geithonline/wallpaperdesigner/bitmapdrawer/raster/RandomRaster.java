package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RandomRaster implements IRaster {

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;

	public RandomRaster(final int width, final int height, final int patternRadius) {

		anzahlPatterns = Settings.getAnzahlPatterns();
		for (int w = 0; w < anzahlPatterns; w++) {
			final int x = Randomizer.getRandomInt(0, width - 1);
			final int y = Randomizer.getRandomInt(0, height - 1);
			final Point p = new Point(x, y);
			points.add(p);
		}
	}

	@Override
	public Point drawNextPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = 0;
		final Point p = points.remove(location);
		return p;
	}

	@Override
	public int getAnzahlPatterns() {
		return anzahlPatterns;
	}
}
