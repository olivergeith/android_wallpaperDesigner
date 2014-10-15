package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Point;

public class GeometricRaster {
	private final int width;
	private final int height;
	private final int radius;

	private final Point currentPos = new Point(0, 0);
	private final int abstand;

	private final int anzW;
	private final int anzH;

	public GeometricRaster(final int width, final int height, final int radius, final float overlap) {
		this.width = width;
		this.height = height;
		this.radius = radius;
		abstand = (int) Math.abs(radius * 2 * overlap);

		anzW = width / abstand + 1;
		anzH = height / abstand + 1;
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

}
