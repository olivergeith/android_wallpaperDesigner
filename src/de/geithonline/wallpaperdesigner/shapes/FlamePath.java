package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class FlamePath extends Path {

	public enum FLAME_TYPE {
		FLAME, TRIANGLE, ARROW_TRIANGLE, ARROW_V2, SHARP_TOOTH
	}

	public FlamePath(final PointF center, final float radius, final FLAME_TYPE type) {
		super();
		switch (type) {
			default:
			case FLAME:
				drawFlame(center, radius);
				break;
			case TRIANGLE:
				drawTriangle(center, radius);
				break;
			case ARROW_TRIANGLE:
				drawArrowTriangle(center, radius);
				break;
			case ARROW_V2:
				drawArrowV2(center, radius);
				break;
			case SHARP_TOOTH:
				drawSharpTooth(center, radius);
				break;
		}
	}

	private void drawFlame(final PointF center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x, center.y + 1 * raster);
		lineTo(center.x, center.y - 1 * raster);
		cubicTo(center.x + 3 * raster, center.y - 3 * raster, // CP1
				center.x + 3 * raster, center.y - 0 * raster, // CP2
				center.x + 6 * raster, center.y - 1 * raster);

		cubicTo(center.x + 3 * raster, center.y + 1 * raster, // CP1
				center.x + 3 * raster, center.y - 2 * raster, // CP2
				center.x + 0 * raster, center.y + 1 * raster);
		close();
	}

	private void drawTriangle(final PointF center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x, center.y + 1 * raster);
		lineTo(center.x, center.y - 1 * raster);
		lineTo(center.x + 6 * raster, center.y - 0 * raster);
		close();
	}

	private void drawArrowTriangle(final PointF center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x, center.y + 1 * raster);
		lineTo(center.x + 2 * raster, center.y - 0 * raster);
		lineTo(center.x, center.y - 1 * raster);
		lineTo(center.x + 6 * raster, center.y - 0 * raster);
		close();
	}

	private void drawArrowV2(final PointF center, final float radius) {
		final float raster = radius / 6;

		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;

		moveTo(center.x, center.y + 2 * raster);
		quadTo(center.x + 3 * raster, center.y + 0 * raster, center.x, center.y - 2 * raster);

		// arcTo(oval, 90, -180);
		lineTo(center.x + 6 * raster, center.y - 0 * raster);
		close();
	}

	private void drawSharpTooth(final PointF center, final float radius) {
		final float raster = radius / 8;

		final RectF oval = new RectF();

		moveTo(center.x, center.y - 2 * raster);

		oval.left = center.x - 6 * raster;
		oval.right = center.x + 6 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 10 * raster;
		arcTo(oval, -90, 90);
		lineTo(center.x + 6 * raster, center.y + 6 * raster);
		oval.left = center.x - 6 * raster;
		oval.right = center.x + 6 * raster;
		oval.top = center.y + 2 * raster;
		oval.bottom = center.y + 14 * raster;
		arcTo(oval, 0, -90);
		close();
	}

}
