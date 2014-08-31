package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class LighthousePath extends Path {

	public LighthousePath(final Point center, final float radius) {
		super();

		final float raster = radius / 6;

		// Sockel
		moveTo(center.x - 2 * raster, center.y + 6 * raster);
		lineTo(center.x - 1 * raster, center.y + 0 * raster);
		lineTo(center.x + 1 * raster, center.y + 0 * raster);
		lineTo(center.x + 2 * raster, center.y + 6 * raster);
		close();
		// ballustrade
		addRect(center.x - 2 * raster, center.y - 1 * raster, center.x + 2 * raster, center.y, Direction.CW);
		// licht
		addCircle(center.x, center.y - 2 * raster, raster, Direction.CW);
		// Lichtstrahl links
		moveTo(center.x - 1 * raster, center.y - 2 * raster);
		lineTo(center.x - 6 * raster, center.y - 3 * raster);
		lineTo(center.x - 6 * raster, center.y - 1 * raster);
		close();
		// Lichtstrahl rechts
		moveTo(center.x + 1 * raster, center.y - 2 * raster);
		lineTo(center.x + 6 * raster, center.y - 3 * raster);
		lineTo(center.x + 6 * raster, center.y - 1 * raster);
		close();
		// Dach
		moveTo(center.x + 0 * raster, center.y - 4 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		lineTo(center.x - 2 * raster, center.y - 3 * raster);
		close();

	}

}
