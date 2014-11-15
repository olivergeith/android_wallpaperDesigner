package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class AndroidPath extends Path {

	public enum ROBOT_STYLE {
		ANDROID, IKEA;
	}

	public AndroidPath(final Point center, final float radius, final ROBOT_STYLE style) {
		super();

		switch (style) {
		default:
		case ANDROID:
			drawAndroid(center, radius);
			break;
		case IKEA:
			drawIkeaRobot(center, radius);
			break;
		}

	}

	private void drawIkeaRobot(final Point center, final float radius) {
		final float raster = radius / 5;
		// oben links und dan im Uhrzeigersinn
		moveTo(center.x - 3 * raster, center.y - 5 * raster);
		lineTo(center.x + 3 * raster, center.y - 5 * raster);
		lineTo(center.x + 3 * raster, center.y - 4 * raster);
		lineTo(center.x + 4 * raster, center.y - 4 * raster);
		lineTo(center.x + 4 * raster, center.y - 3 * raster);
		lineTo(center.x + 5 * raster, center.y - 3 * raster);
		lineTo(center.x + 5 * raster, center.y + 2 * raster);
		lineTo(center.x + 4 * raster, center.y + 2 * raster);
		lineTo(center.x + 4 * raster, center.y - 2 * raster);
		lineTo(center.x + 3 * raster, center.y - 2 * raster);
		lineTo(center.x + 3 * raster, center.y + 3 * raster);
		lineTo(center.x + 2 * raster, center.y + 3 * raster);
		lineTo(center.x + 2 * raster, center.y + 4 * raster);
		lineTo(center.x + 3 * raster, center.y + 4 * raster);
		lineTo(center.x + 3 * raster, center.y + 5 * raster);
		lineTo(center.x + 1 * raster, center.y + 5 * raster);
		lineTo(center.x + 1 * raster, center.y + 3 * raster);
		lineTo(center.x - 1 * raster, center.y + 3 * raster);
		lineTo(center.x - 1 * raster, center.y + 5 * raster);
		lineTo(center.x - 3 * raster, center.y + 5 * raster);
		lineTo(center.x - 3 * raster, center.y + 4 * raster);
		lineTo(center.x - 2 * raster, center.y + 4 * raster);
		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		lineTo(center.x - 3 * raster, center.y + 3 * raster);
		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		lineTo(center.x - 4 * raster, center.y - 2 * raster);
		lineTo(center.x - 4 * raster, center.y + 2 * raster);
		lineTo(center.x - 5 * raster, center.y + 2 * raster);
		lineTo(center.x - 5 * raster, center.y - 3 * raster);
		lineTo(center.x - 4 * raster, center.y - 3 * raster);
		lineTo(center.x - 4 * raster, center.y - 4 * raster);
		lineTo(center.x - 3 * raster, center.y - 4 * raster);
		lineTo(center.x - 3 * raster, center.y - 5 * raster);
		close();

		final RectF oval = new RectF();

		// Kopf
		oval.left = center.x - 2.0f * raster;
		oval.right = center.x - 0.5f * raster;
		oval.top = center.y - 4f * raster;
		oval.bottom = center.y - 2.5f * raster;
		addRect(oval, Direction.CCW);
		// Kopf
		oval.left = center.x + 0.5f * raster;
		oval.right = center.x + 2f * raster;
		oval.top = center.y - 4f * raster;
		oval.bottom = center.y - 2.5f * raster;
		addRect(oval, Direction.CCW);
	}

	private void drawAndroid(final Point center, final float radius) {
		final float raster = radius / 3;
		final RectF oval = new RectF();

		// Kopf
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 3 * raster;
		oval.bottom = center.y + 1 * raster;
		moveTo(center.x - 2 * raster, center.y - 1 * raster);
		arcTo(oval, 180, 180, false);
		close();
		// Augen
		addCircle(center.x - 1 * raster, center.y - 2 * raster, 0.2f * raster, Direction.CCW);
		addCircle(center.x + 1 * raster, center.y - 2 * raster, 0.2f * raster, Direction.CCW);

		// Koerper
		moveTo(center.x - 2 * raster, center.y - 0.8f * raster);
		lineTo(center.x - 2 * raster, center.y + 2.5f * raster);
		lineTo(center.x - 1.5f * raster, center.y + 2.5f * raster);
		// Bein
		lineTo(center.x - 1.5f * raster, center.y + 3.5f * raster);
		oval.left = center.x - 1.5f * raster;
		oval.right = center.x - 0.5f * raster;
		oval.top = center.y + 3 * raster;
		oval.bottom = center.y + 4 * raster;
		arcTo(oval, 180, -180, false);
		lineTo(center.x - 0.5f * raster, center.y + 2.5f * raster);
		lineTo(center.x + 0.5f * raster, center.y + 2.5f * raster);
		// bein
		lineTo(center.x + 0.5f * raster, center.y + 3.5f * raster);
		oval.left = center.x + 0.5f * raster;
		oval.right = center.x + 1.5f * raster;
		oval.top = center.y + 3 * raster;
		oval.bottom = center.y + 4 * raster;
		arcTo(oval, 180, -180, false);
		lineTo(center.x + 1.5f * raster, center.y + 2.5f * raster);
		lineTo(center.x + 2f * raster, center.y + 2.5f * raster);
		lineTo(center.x + 2f * raster, center.y - 0.8f * raster);
		close();

		// Arme
		oval.left = center.x - 3.2f * raster;
		oval.right = center.x - 2.2f * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 2 * raster;
		addRoundRect(oval, raster / 2, raster / 2, Direction.CW);
		oval.left = center.x + 2.2f * raster;
		oval.right = center.x + 3.2f * raster;
		oval.top = center.y - 1 * raster;
		oval.bottom = center.y + 2 * raster;
		addRoundRect(oval, raster / 2, raster / 2, Direction.CW);

	}

}
