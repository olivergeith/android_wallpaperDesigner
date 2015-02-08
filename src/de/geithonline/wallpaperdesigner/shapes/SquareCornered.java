package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class SquareCornered extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public SquareCornered(final PointF center, final float radius, final boolean filled) {
		super();
		drawSquare(center, radius, filled);
	}

	private void drawSquare(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		quadTo(center.x + 2 * raster, center.y - 2 * raster, // controllpoint
				center.x + 3 * raster, center.y - 2 * raster); // Zielpunkt

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		quadTo(center.x + 2 * raster, center.y + 2 * raster, // controllpoint
				center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		quadTo(center.x - 2 * raster, center.y + 2 * raster, // controllpoint
				center.x - 3 * raster, center.y + 2 * raster); // Zielpunkt

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		quadTo(center.x - 2 * raster, center.y - 2 * raster, // controllpoint
				center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

}
