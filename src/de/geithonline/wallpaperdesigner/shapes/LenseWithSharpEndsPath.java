package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class LenseWithSharpEndsPath extends Path {

	public LenseWithSharpEndsPath(final PointF center, final float radiusX, final float radiusY) {
		drawDrop(center, radiusX, radiusY);
	}

	private void drawDrop(final PointF center, final float radiusX, final float radiusY) {

		moveTo(center.x - radiusX, center.y);

		cubicTo(center.x - radiusX / 2, center.y, // CP1
				center.x - radiusX / 2, center.y - radiusY, // CP2
				center.x, center.y - radiusY);
		cubicTo(center.x + radiusX / 2, center.y - radiusY, // CP1
				center.x + radiusX / 2, center.y, // CP2
				center.x + radiusX, center.y);

		cubicTo(center.x + radiusX / 2, center.y, // CP1
				center.x + radiusX / 2, center.y + radiusY, // CP2
				center.x, center.y + radiusY);

		cubicTo(center.x - radiusX / 2, center.y + radiusY, // CP1
				center.x - radiusX / 2, center.y, // CP2
				center.x - radiusX, center.y);

		close();

	}

}
