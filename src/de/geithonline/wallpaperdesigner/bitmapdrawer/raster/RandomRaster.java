package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RandomRaster extends AbstractRaster {

	private final int width;
	private final int height;

	public RandomRaster(final int width, final int height, final int patternRadius, final float overlap, final RasterPositioning positioning) {
		super(patternRadius, overlap);
		this.width = width;
		this.height = height;
		setPositioning(positioning);
		final int anzahlPatterns = Settings.getAnzahlPatterns();
		Log.i("Random Raster", "anz = " + anzahlPatterns);
		for (int w = 0; w < anzahlPatterns; w++) {
			final int x = Randomizer.getRandomInt(0, width - 1);
			final int y = Randomizer.getRandomInt(0, height - 1);
			final Point p = new Point(x, y);
			addPoint2List(width, height, p);
		}
	}

	@Override
	public Point drawNextPoint() {
		switch (getPositioning()) {
		default:
		case RANDOM:
			return drawNextBookPoint();
		case TOPMOST:
			return drawTopmostPoint();
		case BOTTOMMOST:
			return drawBottommostPoint();
		case LEFTMOST:
			return drawLeftmostPoint();
		case RIGHTMOST:
			return drawRightmostPoint();
		case INNER:
			return drawPointNearestToGeometricCenter(width, height);
		case OUTER:
			return drawPointFarmostToGeometricCenter(width, height);

		}
	}
}
