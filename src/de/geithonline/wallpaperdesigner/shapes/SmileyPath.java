package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class SmileyPath extends Path {

	public SmileyPath(final Point center, final float radius, final boolean sad) {
		super();
		// Kopf
		addCircle(center.x, center.y, radius, Direction.CW);
		// Augen
		float alx = center.x - radius * 0.35f;
		float arx = center.x + radius * 0.35f;
		float ay = center.y - radius * 0.20f;
		final float alxi = center.x - radius * 0.3f;
		final float arxi = center.x + radius * 0.4f;
		addCircle(alx, ay, radius * 0.25f, Direction.CCW);
		addCircle(arx, ay, radius * 0.25f, Direction.CCW);
		// inneres der Augen
		ay = center.y - radius * 0.15f;
		addCircle(alxi, ay, radius * 0.12f, Direction.CW);
		addCircle(arxi, ay, radius * 0.12f, Direction.CW);
		// Mund
		alx = center.x - radius * 0.55f;
		arx = center.x + radius * 0.55f;
		if (!sad) {
			final float my = center.y + radius * 0.40f;
			moveTo(alx, my);
			quadTo(center.x, center.y + radius * 0.95f, arx, my);
			quadTo(center.x, center.y + radius * 0.8f, alx, my);
			close();
		} else {
			final float my = center.y + radius * 0.50f;
			moveTo(alx, my);
			quadTo(center.x, center.y + radius * 0.3f, arx, my);
			quadTo(center.x, center.y + radius * 0.1f, alx, my);
			close();
		}
	}
}
