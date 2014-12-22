package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class FlippedPath extends Path {

	public enum FLIPPED_STYLE {
		TRIANGLE, SQUARE, RECTANGLE, TRIANGLE_V2;
	}

	public FlippedPath(final PointF center, final float radius, final boolean filled, final FLIPPED_STYLE style) {
		switch (style) {
		default:
		case TRIANGLE:
			drawTriangle(center, radius, filled);
			break;
		case TRIANGLE_V2:
			drawTriangle2(center, radius, filled);
			break;
		case SQUARE:
			drawSquare(center, radius, filled);
			break;
		case RECTANGLE:
			drawRectangle(center, radius, filled);
			break;
		}
	}

	private void drawTriangle(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CCW);
		}
		switch (Randomizer.getRandomInt(0, 4)) {
		default:
		case 1:
			moveTo(center.x - 1 * raster, center.y - 1 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			close();
			break;
		case 2:
			moveTo(center.x + 1 * raster, center.y - 1 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y - 1 * raster);
			close();
			break;
		case 3:
			moveTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			close();
			break;
		case 4:
			moveTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y - 1 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			close();
			break;
		}
	}

	private void drawTriangle2(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CCW);
		}
		switch (Randomizer.getRandomInt(0, 4)) {
		default:
		case 1:
			moveTo(center.x - 0 * raster, center.y - 0 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y - 1 * raster);
			close();
			break;
		case 2:
			moveTo(center.x - 0 * raster, center.y - 0 * raster);
			lineTo(center.x - 1 * raster, center.y - 1 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			close();
			break;
		case 3:
			moveTo(center.x - 0 * raster, center.y - 0 * raster);
			lineTo(center.x + 1 * raster, center.y - 1 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			close();
			break;
		case 4:
			moveTo(center.x - 0 * raster, center.y - 0 * raster);
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			lineTo(center.x - 1 * raster, center.y + 1 * raster);
			close();
			break;
		}
	}

	private void drawSquare(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CCW);
		}
		switch (Randomizer.getRandomInt(0, 4)) {
		default:
		case 1:
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 0 * raster, center.y + 0 * raster,
					Direction.CW);
			break;
		case 2:
			addRect(center.x - 0 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y - 0 * raster,
					Direction.CW);
			break;
		case 3:
			addRect(center.x - 0 * raster, center.y - 0 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CW);
			break;
		case 4:
			addRect(center.x - 1 * raster, center.y - 0 * raster, center.x + 0 * raster, center.y + 1 * raster,
					Direction.CW);
			break;
		}
	}

	private void drawRectangle(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CCW);
		}
		switch (Randomizer.getRandomInt(0, 4)) {
		default:
		case 1:
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 0 * raster,
					Direction.CW);
			break;
		case 2:
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 0 * raster, center.y + 1 * raster,
					Direction.CW);
			break;
		case 3:
			addRect(center.x - 1 * raster, center.y - 0 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CW);
			break;
		case 4:
			addRect(center.x - 0 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster,
					Direction.CW);
			break;
		}
	}

}
