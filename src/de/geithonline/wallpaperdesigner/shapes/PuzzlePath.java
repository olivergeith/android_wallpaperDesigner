package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class PuzzlePath extends Path {

	public enum PUZZLE_TYPE {
		RANDOM, MANNEKEN, OBEN_RECHTS, KREUZ, ALL;
	}

	public PuzzlePath(final Point center, final float radius, final PUZZLE_TYPE type) {
		switch (type) {
		default:
		case RANDOM:
			drawPuzzle(center, radius, Randomizer.getRandomBoolean(), Randomizer.getRandomBoolean(), Randomizer.getRandomBoolean(),
					Randomizer.getRandomBoolean());
			break;
		case MANNEKEN:
			drawPuzzle(center, radius, true, false, false, false);
			break;
		case OBEN_RECHTS:
			drawPuzzle(center, radius, true, true, false, false);
			break;
		case KREUZ:
			drawPuzzle(center, radius, false, false, false, false);
			break;
		case ALL:
			drawPuzzle(center, radius, true, true, true, true);
			break;
		}
	}

	private void drawPuzzle(final Point center, final float radius, final boolean obenOut, final boolean rechtsOut, final boolean untenOut,
			final boolean linksOut) {
		final float raster = radius / 2;
		final RectF oval = new RectF();

		moveTo(center.x - 2 * raster, center.y - 2 * raster);
		lineTo(center.x - 1 * raster, center.y - 2 * raster);

		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y - 3 * raster;
		oval.bottom = center.y - 1 * raster;
		if (obenOut) {
			arcTo(oval, -180, 180);
		} else {
			arcTo(oval, -180, -180);
		}
		lineTo(center.x + 2 * raster, center.y - 2 * raster);
		lineTo(center.x + 2 * raster, center.y - 1 * raster);

		oval.left = center.x + 1 * raster;
		oval.right = center.x + 3 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		if (rechtsOut) {
			arcTo(oval, -90, 180);
		} else {
			arcTo(oval, -90, -180);
		}
		lineTo(center.x + 2 * raster, center.y + 2 * raster);
		lineTo(center.x + 1 * raster, center.y + 2 * raster);

		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y + 1 * raster;
		oval.bottom = center.y + 3 * raster;
		if (untenOut) {
			arcTo(oval, 0, 180);
		} else {
			arcTo(oval, 0, -180);
		}
		lineTo(center.x - 2 * raster, center.y + 2 * raster);
		lineTo(center.x - 2 * raster, center.y + 1 * raster);

		oval.left = center.x - 3 * raster;
		oval.right = center.x - 1 * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 1 * raster;
		if (linksOut) {
			arcTo(oval, 90, 180);
		} else {
			arcTo(oval, 90, -180);
		}
		lineTo(center.x - 2 * raster, center.y - 2 * raster);
		// close();
	}

}
