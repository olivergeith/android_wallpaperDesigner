package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RectanglePath extends Path {

	public RectanglePath(final Point center, final float radius, final boolean filled, final String variant) {
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
		if (filled) {
			rect.left = center.x - radius / 2;
			rect.right = center.x + radius / 2;
			rect.top = center.y - height / 2;
			rect.bottom = center.y + height / 2;

			if (!rounded) {
				addRect(rect, Direction.CCW);
			} else {
				final float cornerRad = radius * 0.3f;
				addRoundRect(rect, cornerRad, cornerRad, Direction.CCW);
			}

		}

	}

	public RectanglePath(final Point center, final float width, final float height, final boolean filled, final String variant) {
		super();

		boolean rounded = false;
		if (variant.equalsIgnoreCase("Rounded")) {
			rounded = true;
		} else if (variant.equalsIgnoreCase("Mixed")) {
			rounded = Randomizer.getRandomBoolean();
		}

		final RectF rect = new RectF();
		rect.left = center.x - width;
		rect.right = center.x + width;
		rect.top = center.y - height;
		rect.bottom = center.y + height;

		if (!rounded) {
			addRect(rect, Direction.CW);
		} else {
			final float cornerRad = width * 0.3f;
			addRoundRect(rect, cornerRad, cornerRad, Direction.CW);
		}
		if (filled) {
			rect.left = center.x - width / 2;
			rect.right = center.x + width / 2;
			rect.top = center.y - height / 2;
			rect.bottom = center.y + height / 2;

			if (!rounded) {
				addRect(rect, Direction.CCW);
			} else {
				final float cornerRad = width * 0.3f;
				addRoundRect(rect, cornerRad, cornerRad, Direction.CCW);
			}

		}

	}

}
