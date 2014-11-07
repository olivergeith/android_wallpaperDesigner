package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;

public interface IRaster {
	public Point drawNextPoint();

	public int getAnzahlPatterns();
}
