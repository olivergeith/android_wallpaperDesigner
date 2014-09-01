package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SkullPath extends Path {

	public SkullPath(final Point center, final float radius) {
		super();

		final float raster = radius / 5;

		final float sinradius = 1.3f * raster;
		float l = center.x - 4.2f * raster;
		float r = center.x + 4.2f * raster;
		float u = center.y + 4.2f * raster;
		final float o = center.y - 4.2f * raster;

		final RectF oval = new RectF();
		oval.left = l;
		oval.right = r;
		oval.top = o;
		oval.bottom = u;

		moveTo(center.x + 3 * raster, center.y + 5 * raster);
		lineTo(center.x + 3 * raster, center.y + 3 * raster);
		arcTo(oval, 45, -270, false);
		lineTo(center.x - 3 * raster, center.y + 3 * raster);
		lineTo(center.x - 3 * raster, center.y + 5 * raster);

		l = center.x - 3 * raster;
		r = center.x + 3 * raster;
		u = center.y + 5 * raster;

		final int points = 30;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * 7);
			final float y = u + (float) (sinradius * Randomizer.getRandomFloat(0.9f, 1.1f) * Math.sin(angle));
			lineTo(x, y);
		}
		close();

		// Augen
		addCircle(center.x - 2 * raster, center.y - 0 * raster, 1.5f * raster, Direction.CW);
		addCircle(center.x + 2 * raster, center.y - 0 * raster, 1.5f * raster, Direction.CW);

		// nase
		moveTo(center.x + 0 * raster, center.y + 1 * raster);
		lineTo(center.x + 1 * raster, center.y + 3 * raster);
		lineTo(center.x - 1 * raster, center.y + 3 * raster);
		close();
	}

}
