package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.base.OvalPath;

public class FootprintPath extends Path {

	/**
	 * @param center
	 *            center Point
	 * @param rOuter
	 *            Radius von 1f bis 5f....
	 */
	public FootprintPath(final Point center, final float rOuter, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawFootV1(center, rOuter);
			break;
		}
	}

	private void drawFootV1(final Point center, final float radius) {
		final float raster = radius / 13;

		moveTo(center.x - 2 * raster, center.y - 7 * raster);
		quadTo(center.x - 7 * raster, center.y - 6 * raster, // controllpoint
				center.x - 4 * raster, center.y - 0 * raster); // Zielpunkt
		quadTo(center.x - 0 * raster, center.y + 4 * raster, // controllpoint
				center.x - 2 * raster, center.y + 7 * raster); // Zielpunkt
		quadTo(center.x - 5 * raster, center.y + 9 * raster, // controllpoint
				center.x - 5 * raster, center.y + 12 * raster); // Zielpunkt
		quadTo(center.x - 4 * raster, center.y + 15 * raster, // controllpoint
				center.x - 1 * raster, center.y + 15 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y + 15 * raster, // controllpoint
				center.x + 3 * raster, center.y + 11 * raster); // Zielpunkt
		quadTo(center.x + 3 * raster, center.y + 8 * raster, // controllpoint
				center.x + 5 * raster, center.y + 5 * raster); // Zielpunkt
		quadTo(center.x + 6 * raster, center.y + 3 * raster, // controllpoint
				center.x + 6 * raster, center.y + 0 * raster); // Zielpunkt
		quadTo(center.x + 5 * raster, center.y - 5 * raster, // controllpoint
				center.x - 2 * raster, center.y - 7 * raster); // Zielpunkt
		close();

		addPath(new OvalPath(new PointF(center.x - 5 * raster, center.y - 11 * raster), raster * 2, raster * 3, Direction.CCW));
		addPath(new OvalPath(new PointF(center.x - 1 * raster, center.y - 10.3f * raster), raster * 1.2f, raster * 2.2f, Direction.CCW));
		addPath(new OvalPath(new PointF(center.x + 2 * raster, center.y - 9.2f * raster), raster * 1, raster * 1.9f, Direction.CCW));
		addPath(new OvalPath(new PointF(center.x + 4 * raster, center.y - 7.8f * raster), raster * 0.8f, raster * 1.3f, Direction.CCW));
		addPath(new OvalPath(new PointF(center.x + 5.5f * raster, center.y - 6f * raster), raster * 0.5f, raster * 1.0f, Direction.CCW));

	}

}
