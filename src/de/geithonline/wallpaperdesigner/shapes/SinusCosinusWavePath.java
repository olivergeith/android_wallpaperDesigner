package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class SinusCosinusWavePath extends Path {

	public SinusCosinusWavePath(final PointF center, final float radiusX, final float radiusY) {
		drawWave(center, radiusX, radiusY);
	}

	private void drawWave(final PointF center, final float radiusX, final float radiusY) {

		final float l = center.x - radiusX;
		final float r = center.x + radiusX;
		float mitteY = center.y - radiusY / 3;
		// nach links
		final float sinradius = radiusY * 2 / 3;
		final int halfWaves = 3;
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 60;
		for (int i = 0; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI / 2 * halfWaves);
			final float y = mitteY - (float) (sinradius * Math.sin(angle));
			lineTo(x, y);
		}
		mitteY = center.y + radiusY / 3;
		for (int i = points; i >= 0; i--) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI / 2 * halfWaves);
			final float y = mitteY - (float) (sinradius * Math.cos(angle));
			lineTo(x, y);
		}

		close();

	}

}
