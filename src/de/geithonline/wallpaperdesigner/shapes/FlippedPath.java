package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class FlippedPath extends Path {

	public enum FLIPPED_STYLE {
		TRIANGLE, SQUARE, RECTANGLE, TRIANGLE_V2, QUARTER_ARC, QUARTER_ARC_V2;
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
		case QUARTER_ARC:
			drawQuarterArch(center, radius, filled);
			break;
		case QUARTER_ARC_V2:
			drawQuarterArch2(center, radius, filled);
			break;
		}
	}

	private void drawTriangle(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CCW);
		}
		final int i = Randomizer.getRandomInt(1, 4);
		Log.i("int", "=" + i);
		switch (i) {
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
			lineTo(center.x + 1 * raster, center.y + 1 * raster);
			close();
			break;
		}
	}

	private void drawTriangle2(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CCW);
		}
		switch (Randomizer.getRandomInt(1, 4)) {
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

	private void drawQuarterArch(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CCW);
		}

		final Path p = new Path();
		p.moveTo(center.x - 1 * raster, center.y - 1 * raster);
		p.lineTo(center.x + 1 * raster, center.y - 1 * raster);
		p.quadTo(center.x + 1 * raster, center.y + 1 * raster, // CP
				center.x - 1 * raster, center.y + 1 * raster);
		p.close();

		final int drehwinkel = Randomizer.getRandomInt(0, 3) * 90;
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
	}

	private void drawQuarterArch2(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CCW);
		}

		final Path p = new Path();
		p.moveTo(center.x - 1 * raster, center.y - 1 * raster);
		p.lineTo(center.x + 1 * raster, center.y - 1 * raster);
		p.quadTo(center.x - 1 * raster, center.y - 1 * raster, // CP
				center.x - 1 * raster, center.y + 1 * raster);
		p.close();

		final int drehwinkel = Randomizer.getRandomInt(0, 3) * 90;
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
	}

	private void drawSquare(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CCW);
		}
		switch (Randomizer.getRandomInt(1, 4)) {
		default:
		case 1:
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 0 * raster, center.y + 0 * raster, Direction.CW);
			break;
		case 2:
			addRect(center.x - 0 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y - 0 * raster, Direction.CW);
			break;
		case 3:
			addRect(center.x - 0 * raster, center.y - 0 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CW);
			break;
		case 4:
			addRect(center.x - 1 * raster, center.y - 0 * raster, center.x + 0 * raster, center.y + 1 * raster, Direction.CW);
			break;
		}
	}

	private void drawRectangle(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 1;

		if (!filled) {
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CCW);
		}
		switch (Randomizer.getRandomInt(1, 4)) {
		default:
		case 1:
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 0 * raster, Direction.CW);
			break;
		case 2:
			addRect(center.x - 1 * raster, center.y - 1 * raster, center.x + 0 * raster, center.y + 1 * raster, Direction.CW);
			break;
		case 3:
			addRect(center.x - 1 * raster, center.y - 0 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CW);
			break;
		case 4:
			addRect(center.x - 0 * raster, center.y - 1 * raster, center.x + 1 * raster, center.y + 1 * raster, Direction.CW);
			break;
		}
	}

}
