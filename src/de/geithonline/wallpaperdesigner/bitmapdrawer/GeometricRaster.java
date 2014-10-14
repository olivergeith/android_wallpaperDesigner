package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Point;

public class GeometricRaster {
	private final int width;
	private final int height;
	private final int radius;

	private final Point currentPos = new Point(0, 0);
	private final int anzahlPattern;
	private final int abstand;

	public GeometricRaster(final int width, final int height, final int radius) {
		this.width = width;
		this.height = height;
		this.radius = radius;
		final float overlap = 0.5f;
		abstand = (int) Math.abs(radius * 2 * overlap);

		final int anzW = width / abstand + 2;
		final int anzH = height / abstand + 1;
		anzahlPattern = anzW * anzH;
	}

	public int getAnzahlPattern() {
		return anzahlPattern;
	}

	public Point getNextPoint() {
		final Point p = currentPos;
		currentPos.x = currentPos.x + abstand;
		if (currentPos.x > width + 1) {
			currentPos.x = 0;
			currentPos.y = currentPos.y + abstand;
		}
		if (currentPos.y > height) {
			currentPos.y = height - 1;
		}
		return p;
	}

}
