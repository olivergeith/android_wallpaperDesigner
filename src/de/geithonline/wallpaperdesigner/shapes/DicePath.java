package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class DicePath extends Path {

	private static final float DOT_RADIUS = 0.4f;

	public DicePath(final PointF center, final float radius) {
		super();
		drawDice(center, radius);
	}

	public void drawDice(final PointF center, final float radius) {
		addPath(new SquarePath(center, radius, true, SQUARE_STYLE.ROUNDED, Direction.CW));
		final float raster = radius / 1.8f;

		final int number = Randomizer.getRandomInt(0, 6);
		switch (number) {
			default:
			case 1:
				addCircle(center.x + 0 * raster, center.y + 0 * raster, raster * DOT_RADIUS, Direction.CCW);
				break;
			case 2:
				addCircle(center.x - 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				break;
			case 3:
				addCircle(center.x + 0 * raster, center.y + 0 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x - 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				break;
			case 4:
				addCircle(center.x - 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x - 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				break;
			case 5:
				addCircle(center.x + 0 * raster, center.y + 0 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x - 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x - 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				break;
			case 6:
				addCircle(center.x - 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x - 1 * raster, center.y + 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y - 1 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x - 1 * raster, center.y + 0 * raster, raster * DOT_RADIUS, Direction.CCW);
				addCircle(center.x + 1 * raster, center.y - 0 * raster, raster * DOT_RADIUS, Direction.CCW);
				break;
		}
	}
}
