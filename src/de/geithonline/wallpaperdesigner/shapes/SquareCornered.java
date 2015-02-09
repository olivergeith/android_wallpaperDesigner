package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class SquareCornered extends Path {

	public enum CORNERED_STYLE {
		ROUND, ROUND2, RECT, LINE, CIRCLE;
	}

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public SquareCornered(final PointF center, final float radius, final boolean filled, final CORNERED_STYLE style) {
		super();
		switch (style) {
		default:
		case ROUND:
			drawSquareROUND(center, radius, filled);
			break;
		case ROUND2:
			drawSquareROUND2(center, radius, filled);
			break;
		case CIRCLE:
			drawSquareCIRCLE(center, radius, filled);
			break;
		case RECT:
			drawSquareRECT(center, radius, filled);
			break;
		case LINE:
			drawSquareLINE(center, radius, filled);
			break;
		}
	}

	private void drawSquareROUND(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		quadTo(center.x + 2 * raster, center.y - 2 * raster, // controllpoint
				center.x + 3 * raster, center.y - 2 * raster); // Zielpunkt

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		quadTo(center.x + 2 * raster, center.y + 2 * raster, // controllpoint
				center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		quadTo(center.x - 2 * raster, center.y + 2 * raster, // controllpoint
				center.x - 3 * raster, center.y + 2 * raster); // Zielpunkt

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		quadTo(center.x - 2 * raster, center.y - 2 * raster, // controllpoint
				center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.ROUND);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

	private void drawSquareROUND2(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		quadTo(center.x + 1 * raster, center.y - 1 * raster, // controllpoint
				center.x + 3 * raster, center.y - 2 * raster); // Zielpunkt

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		quadTo(center.x + 1 * raster, center.y + 1 * raster, // controllpoint
				center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		quadTo(center.x - 1 * raster, center.y + 1 * raster, // controllpoint
				center.x - 3 * raster, center.y + 2 * raster); // Zielpunkt

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		quadTo(center.x - 1 * raster, center.y - 1 * raster, // controllpoint
				center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.ROUND2);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

	private void drawSquareLINE(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 3 * raster, center.y - 2 * raster); // Zielpunkt

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		lineTo(center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		lineTo(center.x - 3 * raster, center.y + 2 * raster); // Zielpunkt

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		lineTo(center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.LINE);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

	private void drawSquareRECT(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 2 * raster); // Zielpunkt
		lineTo(center.x + 3 * raster, center.y - 2 * raster); // Zielpunkt

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		lineTo(center.x + 2 * raster, center.y + 2 * raster); // Zielpunkt
		lineTo(center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		lineTo(center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		lineTo(center.x - 3 * raster, center.y + 2 * raster); // Zielpunkt

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		lineTo(center.x - 2 * raster, center.y - 2 * raster); // Zielpunkt
		lineTo(center.x - 2 * raster, center.y - 3 * raster); // Zielpunkt
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.RECT);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

	private void drawSquareCIRCLE(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		final RectF oval = new RectF();

		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		oval.left = center.x + 1 * raster;
		oval.right = center.x + 3 * raster;
		oval.top = center.y - 3 * raster;
		oval.bottom = center.y - 1 * raster;
		arcTo(oval, 270, -270);

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		oval.left = center.x + 1 * raster;
		oval.right = center.x + 3 * raster;
		oval.top = center.y + 1 * raster;
		oval.bottom = center.y + 3 * raster;
		arcTo(oval, 0, -270);

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		oval.left = center.x - 3 * raster;
		oval.right = center.x - 1 * raster;
		oval.top = center.y + 1 * raster;
		oval.bottom = center.y + 3 * raster;
		arcTo(oval, 90, -270);

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		oval.left = center.x - 3 * raster;
		oval.right = center.x - 1 * raster;
		oval.top = center.y - 3 * raster;
		oval.bottom = center.y - 1 * raster;
		arcTo(oval, 180, -270);
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.CIRCLE);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

}
