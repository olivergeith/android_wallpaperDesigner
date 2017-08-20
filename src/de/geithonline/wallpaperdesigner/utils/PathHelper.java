
package de.geithonline.wallpaperdesigner.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.shapes.composed.ComposedPath;

public class PathHelper {
	/**
	 * @param x
	 *            Position um die gefreht wird
	 * @param y
	 *            Position um die gefreht wird
	 * @param path
	 *            der pfad der gedreht werden soll
	 * @param rotate
	 *            gradzahl 0-360
	 */
	public static void rotatePath(final float x, final float y, final Path path, final float rotate) {
		if (path == null) {
			return;
		}
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postRotate(rotate, x, y);
		path.transform(mMatrix);
	}

	public static void rotatePath(final PointF center, final Path path, final float rotate) {
		rotatePath(center.x, center.y, path, rotate);
	}

	public static void rotateComposedPath(final float x, final float y, final ComposedPath path, final float rotate, final boolean recurse) {
		if (path == null) {
			return;
		}
		rotatePath(x, y, path, rotate);
		// und nun die einzelelemente drehen
		for (final Path p : path.getPathElements()) {
			if (p instanceof ComposedPath && recurse == true) {
				rotateComposedPath(x, y, (ComposedPath) p, rotate, recurse);
			} else {
				rotatePath(x, y, p, rotate);
			}
		}
	}

	public static void rotateComposedPath(final float x, final float y, final ComposedPath path, final float rotate) {
		rotateComposedPath(x, y, path, rotate, true);
	}

	public static void rotateComposedPath(final PointF center, final ComposedPath path, final float rotate) {
		rotateComposedPath(center.x, center.y, path, rotate, true);
	}

	public static void rotateComposedPath(final PointF center, final ComposedPath path, final float rotate, final boolean recurse) {
		rotateComposedPath(center.x, center.y, path, rotate, recurse);
	}

	/**
	 * Spiegel an der vertikalen y-Achse
	 *
	 * @param x
	 *            Position um die gefreht wird
	 * @param y
	 *            Position um die gefreht wird
	 * @param path
	 *            der pfad der gemirrored werden soll
	 */
	public static void mirrorPathLeftRight(final float x, final float y, final Path path) {
		if (path == null) {
			return;
		}
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postScale(-1f, 1f, x, y);
		path.transform(mMatrix);
	}

	/**
	 * Spiegel an der horizontalen x-Achse
	 *
	 * @param x
	 *            Position um die gefreht wird
	 * @param y
	 *            Position um die gefreht wird
	 * @param path
	 *            der pfad der gemirrored werden soll
	 */
	public static void mirrorPathUpDown(final float x, final float y, final Path path) {
		if (path == null) {
			return;
		}
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postScale(1f, -1f, x, y);
		path.transform(mMatrix);
	}

}
