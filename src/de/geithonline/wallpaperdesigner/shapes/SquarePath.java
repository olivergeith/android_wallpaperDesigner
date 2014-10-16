package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SquarePath extends Path {

	public enum SQUARE_STYLE {
		ROUNDED, NORMAL, MIXED;
	}

	public SquarePath(final Point center, final float radius, final boolean filled, final SQUARE_STYLE variant) {
		super();

		boolean rounded = false;

		switch (variant) {
		case ROUNDED:
			rounded = true;
			break;
		case NORMAL:
			rounded = false;
			break;
		case MIXED:
			rounded = Randomizer.getRandomBoolean();
			break;
		}
		final RectF rect = new RectF();

		rect.left = center.x - radius;
		rect.right = center.x + radius;
		rect.top = center.y - radius;
		rect.bottom = center.y + radius;

		if (!rounded) {
			addRect(rect, Direction.CW);
		} else {
			final float cornerRad = radius * 0.3f;
			addRoundRect(rect, cornerRad, cornerRad, Direction.CW);
		}
		if (!filled) {
			rect.left = center.x - radius / 2;
			rect.right = center.x + radius / 2;
			rect.top = center.y - radius / 2;
			rect.bottom = center.y + radius / 2;

			if (!rounded) {
				addRect(rect, Direction.CCW);
			} else {
				final float cornerRad = radius * 0.3f;
				addRoundRect(rect, cornerRad, cornerRad, Direction.CCW);
			}

		}

	}
}
