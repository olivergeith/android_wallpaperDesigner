package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RandomRaster extends AbstractRaster {

	public RandomRaster(final int width, final int height, final int patternRadius, final float overlap) {
		super(patternRadius, overlap);
		setPositioning(RasterPositioning.RANDOM);

		final int anzahlPatterns = Settings.getAnzahlPatterns();
		for (int w = 0; w < anzahlPatterns; w++) {
			final int x = Randomizer.getRandomInt(0, width - 1);
			final int y = Randomizer.getRandomInt(0, height - 1);
			final Point p = new Point(x, y);
			addPoint2List(width, height, p);
		}
	}

	@Override
	public Point drawNextPoint() {
		return drawNextBookPoint();
	}
}
