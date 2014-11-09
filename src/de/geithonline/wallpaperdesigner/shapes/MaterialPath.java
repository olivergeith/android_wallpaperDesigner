package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class MaterialPath extends Path {

	public enum MATERIAL_TYPE {
		SKYLINE, STIPE;
	}

	public MaterialPath(final Point center, final float radius, final boolean filled, final int bWidth, final int bHeight, final MATERIAL_TYPE type) {
		super();
		switch (type) {
		default:
		case STIPE:
			drawStripe(center, radius, bWidth, bHeight);
			break;
		case SKYLINE:
			drawSkyline(center, radius, bWidth, bHeight);
			break;
		}
	}

	private void drawSkyline(final Point center, final float radius, final int bWidth, final int bHeight) {
		final RectF rect = new RectF(center.x - radius, center.y, center.x + radius, bHeight);
		addRect(rect, Direction.CW);
	}

	private static boolean flip = true;

	private void drawStripe(final Point center, final float radius, final int bWidth, final int bHeight) {
		if (flip == true) {
			final float x = center.x + center.y;
			final float y = center.y + center.x;
			moveTo(x - radius, 0);
			lineTo(x + radius, 0);
			lineTo(0, y + radius);
			lineTo(0, y - radius);
			close();
		} else {
			final float x1 = center.x - center.y;
			final float x2 = center.x + (bHeight - center.y);
			moveTo(x1 - radius, 0);
			lineTo(x1 + radius, 0);
			lineTo(x2 + radius, bHeight);
			lineTo(x2 - radius, bHeight);
			close();
		}
		flip = !flip;
	}

	private void drawStripeV2(final Point center, final float radius, final int bWidth, final int bHeight) {

		final float diagonal = (float) Math.sqrt(bWidth * bWidth + bHeight * bHeight);

		final Path p = new Path();
		final RectF rect = new RectF(center.x - radius * 0.7f, center.y - diagonal, center.x + radius * 0.7f, center.y + diagonal);
		p.addRect(rect, Direction.CW);

		if (flip == true) {
			rotatePath(center.x, center.y, p, 45);
		} else {
			rotatePath(center.x, center.y, p, 135);
		}
		addPath(p);
		flip = !flip;
	}

	protected void rotatePath(final int x, final int y, final Path path, final int rotate) {
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postRotate(rotate, x, y);
		path.transform(mMatrix);
	}

}
