package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RectanglePath extends Path {

	public RectanglePath(final Point center, final float radius, final String variant) {
		super();

		boolean rounded = false;
		if (variant.equalsIgnoreCase("Rounded")) {
			rounded = true;
		} else if (variant.equalsIgnoreCase("Mixed")) {
			rounded = Randomizer.getRandomBoolean();
		}

		final int height = (int) (radius * Randomizer.getRandomFloat(0.1f, 0.8f));
		final RectF rect = new RectF();

		rect.left = center.x - radius;
		rect.right = center.x + radius;
		rect.top = center.y - height;
		rect.bottom = center.y + height;

		if (!rounded) {
			addRect(rect, Direction.CW);
		} else {
			final float cornerRad = radius * 0.3f;
			addRoundRect(rect, cornerRad, cornerRad, Direction.CW);
		}
	}
}
