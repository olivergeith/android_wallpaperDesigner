package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class DeathstarPath extends Path {

	public DeathstarPath(final Point center, final float radius) {
		super();

		if (Randomizer.getRandomBoolean()) {
			drawDeathStar(center, radius);
		} else {
			drawR2D2(center, radius);
		}

	}

	private void drawR2D2(final Point center, final float radius) {
		final float raster = radius / 7;
		final RectF oval = new RectF();

		// Kopf
		oval.left = center.x - 3 * raster;
		oval.right = center.x + 3 * raster;
		oval.top = center.y - 7 * raster;
		oval.bottom = center.y - 1 * raster;
		moveTo(center.x - 3 * raster, center.y - 3 * raster);
		lineTo(center.x - 3 * raster, center.y - 4 * raster);
		arcTo(oval, 180, 180, false);
		lineTo(center.x + 3 * raster, center.y - 3 * raster);
		close();
		// Augen
		addCircle(center.x - 0 * raster, center.y - 5.5f * raster, 1.0f * raster, Direction.CCW);
		addCircle(center.x + 2 * raster, center.y - 4f * raster, 0.5f * raster, Direction.CCW);

		// Koerper
		moveTo(center.x - 3 * raster, center.y - 2.5f * raster);
		lineTo(center.x - 3 * raster, center.y + 4f * raster);
		lineTo(center.x - 2 * raster, center.y + 5f * raster);
		lineTo(center.x + 2 * raster, center.y + 5f * raster);
		lineTo(center.x + 3 * raster, center.y + 4f * raster);
		lineTo(center.x + 3 * raster, center.y - 2.5f * raster);
		close();
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 1.5f * raster;
		oval.bottom = center.y - 0.5f * raster;
		addRoundRect(oval, raster / 2, raster / 2, Direction.CW);
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y + 0.5f * raster;
		oval.bottom = center.y + 1.5f * raster;
		addRoundRect(oval, raster / 2, raster / 2, Direction.CW);
		// Beiun Links
		moveTo(center.x - 3.5f * raster, center.y - 2.5f * raster);
		lineTo(center.x - 4 * raster, center.y - 2.5f * raster);
		lineTo(center.x - 4 * raster, center.y - 2.5f * raster);
		lineTo(center.x - 5 * raster, center.y - 1.5f * raster);
		lineTo(center.x - 5 * raster, center.y + 4 * raster);
		lineTo(center.x - 5 * raster, center.y + 4 * raster);
		lineTo(center.x - 6 * raster, center.y + 6 * raster);
		lineTo(center.x - 2.5f * raster, center.y + 6 * raster);
		lineTo(center.x - 3.5f * raster, center.y + 4 * raster);
		close();
		// Beiun Links
		moveTo(center.x + 3.5f * raster, center.y - 2.5f * raster);
		lineTo(center.x + 4 * raster, center.y - 2.5f * raster);
		lineTo(center.x + 4 * raster, center.y - 2.5f * raster);
		lineTo(center.x + 5 * raster, center.y - 1.5f * raster);
		lineTo(center.x + 5 * raster, center.y + 4 * raster);
		lineTo(center.x + 5 * raster, center.y + 4 * raster);
		lineTo(center.x + 6 * raster, center.y + 6 * raster);
		lineTo(center.x + 2.5f * raster, center.y + 6 * raster);
		lineTo(center.x + 3.5f * raster, center.y + 4 * raster);
		close();
	}

	private void drawDeathStar(final Point center, final float radius) {
		final float raster = radius / 13;
		final RectF oval = new RectF();
		oval.left = center.x - 13 * raster;
		oval.right = center.x + 13 * raster;
		oval.top = center.y - 13 * raster;
		oval.bottom = center.y + 13 * raster;

		// obere Hälfte
		moveTo(center.x + 13 * raster, center.y - 0.5f * raster);
		lineTo(center.x - 13 * raster, center.y - 0.5f * raster);
		arcTo(oval, 181, 116, false);

		lineTo(center.x + 6 * raster, center.y - 10 * raster);
		lineTo(center.x + 5 * raster, center.y - 10 * raster);
		lineTo(center.x + 5 * raster, center.y - 9 * raster);
		lineTo(center.x + 7 * raster, center.y - 9 * raster);
		lineTo(center.x + 7 * raster, center.y - 8 * raster);
		lineTo(center.x + 6 * raster, center.y - 8 * raster);
		lineTo(center.x + 6 * raster, center.y - 7 * raster);
		lineTo(center.x + 8 * raster, center.y - 7 * raster);
		lineTo(center.x + 8 * raster, center.y - 6 * raster);
		lineTo(center.x + 9 * raster, center.y - 6 * raster);
		lineTo(center.x + 9 * raster, center.y - 5 * raster);
		lineTo(center.x + 6 * raster, center.y - 5 * raster);
		lineTo(center.x + 6 * raster, center.y - 4 * raster);
		lineTo(center.x + 10 * raster, center.y - 4 * raster);
		lineTo(center.x + 10 * raster, center.y - 3 * raster);
		lineTo(center.x + 8 * raster, center.y - 3 * raster);
		lineTo(center.x + 8 * raster, center.y - 2 * raster);
		lineTo(center.x + 12 * raster, center.y - 2 * raster);
		arcTo(oval, 355, 4, false);
		close();

		// Untere Hälfte
		moveTo(center.x + 13 * raster, center.y + 0.5f * raster);
		arcTo(oval, 1, 7, false);
		lineTo(center.x + 9 * raster, center.y + 2 * raster);
		lineTo(center.x + 9 * raster, center.y + 3 * raster);
		lineTo(center.x + 11 * raster, center.y + 3 * raster);
		lineTo(center.x + 11 * raster, center.y + 4 * raster);
		lineTo(center.x + 7 * raster, center.y + 4 * raster);
		lineTo(center.x + 7 * raster, center.y + 5 * raster);
		lineTo(center.x + 2 * raster, center.y + 5 * raster);
		lineTo(center.x + 2 * raster, center.y + 6 * raster);
		lineTo(center.x + 4 * raster, center.y + 6 * raster);
		lineTo(center.x + 4 * raster, center.y + 7 * raster);
		lineTo(center.x + 10 * raster, center.y + 7 * raster);
		lineTo(center.x + 10 * raster, center.y + 8 * raster);
		lineTo(center.x + 9 * raster, center.y + 8 * raster);
		lineTo(center.x + 9 * raster, center.y + 9 * raster);
		lineTo(center.x + 6 * raster, center.y + 9 * raster);
		lineTo(center.x + 6 * raster, center.y + 10 * raster);
		lineTo(center.x + 5 * raster, center.y + 10 * raster);
		lineTo(center.x + 5 * raster, center.y + 11 * raster);
		lineTo(center.x + 2 * raster, center.y + 11 * raster);
		lineTo(center.x + 2 * raster, center.y + 12 * raster);
		lineTo(center.x + 4 * raster, center.y + 12 * raster);
		arcTo(oval, 80, 179 - 80, false);
		lineTo(center.x - 13 * raster, center.y + 0.5f * raster);
		lineTo(center.x + 13 * raster, center.y + 0.5f * raster);
		close();

		// Augen
		addCircle(center.x - 4 * raster, center.y - 6 * raster, 3.5f * raster, Direction.CW);
	}

}
