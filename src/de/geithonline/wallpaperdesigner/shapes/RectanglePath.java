package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RectanglePath extends Path {

	public enum RECT_ROUNDED {
		ROUNDED, NORMAL, MIXED;
	}

	public enum RECT_ASPECT {
		RANDOM, ASPECT_3_4, ASPECT_1_2;
	}

	public RectanglePath(final Point center, final float radius, final boolean filled, final RECT_ROUNDED round, final RECT_ASPECT aspect) {
		super();

		boolean rounded = false;
		switch (round) {
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

		int height = (int) (radius * Randomizer.getRandomFloat(0.1f, 0.8f));
		switch (aspect) {
		default:
		case RANDOM:
			height = (int) (radius * Randomizer.getRandomFloat(0.1f, 0.8f));
			break;
		case ASPECT_3_4:
			height = (int) (radius * 3 / 4);
			break;
		case ASPECT_1_2:
			height = (int) (radius * 1 / 2);
			break;
		}

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
		if (!filled) {
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
