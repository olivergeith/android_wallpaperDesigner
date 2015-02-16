package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class SquareCornered extends Path {

	public enum CORNERED_STYLE {
		INNER_ROUND, INNER_ROUND2, INNER_RECT, DIAGONAL_LINE, CIRCLE, ROUNDED, CASTEL, OUTER_CIRCLE;
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
		case INNER_ROUND:
			drawSquareINNERROUND(center, radius, filled);
			break;
		case INNER_ROUND2:
			drawSquareROUND2(center, radius, filled);
			break;
		case CIRCLE:
			drawSquareCIRCLE(center, radius, filled);
			break;
		case OUTER_CIRCLE:
			drawSquareOUTERCIRCLE(center, radius, filled);
			break;
		case INNER_RECT:
			drawSquareRECT(center, radius, filled);
			break;
		case DIAGONAL_LINE:
			drawSquareLINE(center, radius, filled);
			break;
		case ROUNDED:
			drawSquareROUNDED(center, radius, filled);
			break;
		case CASTEL:
			drawSquareCASTEL(center, radius, filled);
			break;
		}
	}

	private void drawSquareINNERROUND(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		final RectF oval = new RectF();

		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		oval.left = center.x + 2 * raster;
		oval.right = center.x + 4 * raster;
		oval.top = center.y - 4 * raster;
		oval.bottom = center.y - 2 * raster;
		arcTo(oval, 180, -90);

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		oval.left = center.x + 2 * raster;
		oval.right = center.x + 4 * raster;
		oval.top = center.y + 2 * raster;
		oval.bottom = center.y + 4 * raster;
		arcTo(oval, 270, -90);

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		oval.left = center.x - 4 * raster;
		oval.right = center.x - 2 * raster;
		oval.top = center.y + 2 * raster;
		oval.bottom = center.y + 4 * raster;
		arcTo(oval, 0, -90);

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		oval.left = center.x - 4 * raster;
		oval.right = center.x - 2 * raster;
		oval.top = center.y - 4 * raster;
		oval.bottom = center.y - 2 * raster;
		arcTo(oval, 90, -90);
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.INNER_ROUND);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

	private void drawSquareROUNDED(final PointF center, final float radius, final boolean filled) {
		final Path p = new SquarePath(center, radius, filled, SQUARE_STYLE.ROUNDED);
		addPath(p);
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
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.INNER_ROUND2);
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
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.DIAGONAL_LINE);
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
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.INNER_RECT);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

	private void drawSquareCASTEL(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 3;
		final float inner = 2.5f;
		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x - 2 * raster, center.y - 2.5f * raster);
		lineTo(center.x + 2 * raster, center.y - 2.5f * raster);
		lineTo(center.x + 2 * raster, center.y - 3f * raster);
		lineTo(center.x + 3 * raster, center.y - 3f * raster);
		lineTo(center.x + 3 * raster, center.y - 2f * raster);
		lineTo(center.x + 2.5f * raster, center.y - 2f * raster);
		lineTo(center.x + 2.5f * raster, center.y + 2f * raster);
		lineTo(center.x + 3f * raster, center.y + 2f * raster);
		lineTo(center.x + 3f * raster, center.y + 3f * raster);
		lineTo(center.x + 2f * raster, center.y + 3f * raster);
		lineTo(center.x + 2f * raster, center.y + 2.5f * raster);
		lineTo(center.x - 2f * raster, center.y + 2.5f * raster);
		lineTo(center.x - 2f * raster, center.y + 3f * raster);
		lineTo(center.x - 3f * raster, center.y + 3f * raster);
		lineTo(center.x - 3f * raster, center.y + 2f * raster);
		lineTo(center.x - 2.5f * raster, center.y + 2f * raster);
		lineTo(center.x - 2.5f * raster, center.y - 2f * raster);
		lineTo(center.x - 3f * raster, center.y - 2f * raster);
		lineTo(center.x - 3f * raster, center.y - 3f * raster);
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.CASTEL);
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

	private void drawSquareOUTERCIRCLE(final PointF center, final float radius, final boolean filled) {
		final float raster = radius / 4;
		final RectF oval = new RectF();

		moveTo(center.x - 2 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 3 * raster);
		oval.left = center.x + 2 * raster;
		oval.right = center.x + 4 * raster;
		oval.top = center.y - 4 * raster;
		oval.bottom = center.y - 2 * raster;
		arcTo(oval, 180, 270);

		lineTo(center.x + 3 * raster, center.y + 2 * raster);
		oval.left = center.x + 2 * raster;
		oval.right = center.x + 4 * raster;
		oval.top = center.y + 2 * raster;
		oval.bottom = center.y + 4 * raster;
		arcTo(oval, 270, 270);

		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		oval.left = center.x - 4 * raster;
		oval.right = center.x - 2 * raster;
		oval.top = center.y + 2 * raster;
		oval.bottom = center.y + 4 * raster;
		arcTo(oval, 0, 270);

		lineTo(center.x - 3 * raster, center.y - 2 * raster);
		oval.left = center.x - 4 * raster;
		oval.right = center.x - 2 * raster;
		oval.top = center.y - 4 * raster;
		oval.bottom = center.y - 2 * raster;
		arcTo(oval, 90, 270);
		close();

		if (!filled) {
			final Path path = new SquareCornered(center, radius / 2, true, CORNERED_STYLE.OUTER_CIRCLE);
			PathHelper.mirrorPathLeftRight(center.x, center.y, path);
			addPath(path);
		}

	}

}
