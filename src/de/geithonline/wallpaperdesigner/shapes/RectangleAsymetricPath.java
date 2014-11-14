package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.shapes.RectanglePath.RECT_ROUNDED;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RectangleAsymetricPath extends Path {

	public RectangleAsymetricPath(final Point center, final float radius, final int height, final boolean filled, final RECT_ROUNDED round) {
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

		final RectF rect = new RectF();

		rect.left = center.x - radius;
		rect.right = center.x + radius;
		rect.top = center.y - height;
		rect.bottom = center.y;

		if (!rounded) {
			addRect(rect, Direction.CW);
		} else {
			final float cornerRad = radius * 0.3f;
			addRoundRect(rect, cornerRad, cornerRad, Direction.CW);
		}
		if (!filled) {
			rect.left = center.x - radius / 2;
			rect.right = center.x + radius / 2;
			rect.top = center.y - height * 3 / 4;
			rect.bottom = center.y - height / 4;
			if (!rounded) {
				addRect(rect, Direction.CCW);
			} else {
				final float cornerRad = radius * 0.3f;
				addRoundRect(rect, cornerRad, cornerRad, Direction.CCW);
			}
		}

	}

}
