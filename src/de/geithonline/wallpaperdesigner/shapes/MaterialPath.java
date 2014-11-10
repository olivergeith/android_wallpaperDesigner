package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class MaterialPath extends Path {

	public enum MATERIAL_TYPE {
		SKYLINE, STIPE, ARC1, ARC2;
	}

	public MaterialPath(final Point center, final float radius, final boolean filled, final int bWidth, final int bHeight, final MATERIAL_TYPE type) {
		super();
		switch (type) {
		default:
		case STIPE:
			drawStripe(center, radius, bWidth, bHeight);
			break;
		case ARC1:
			drawArcV1(center, radius, bWidth, bHeight);
			break;
		case ARC2:
			drawArcV2(center, radius, bWidth, bHeight);
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

	private void drawArcV1(final Point center, final float radius, final int bWidth, final int bHeight) {
		final Point circleCenter = new Point();
		int circleRadius = 0;
		if (flip == true) {
			circleCenter.x = 0;
			circleCenter.y = bHeight;
			circleRadius = center.x;
		} else {
			circleCenter.x = 0; // bWidth;
			circleCenter.y = 0; // bHeight;
			circleRadius = bWidth - center.x;
		}

		addCircle(circleCenter.x, circleCenter.y, circleRadius + radius, Direction.CW);
		addCircle(circleCenter.x, circleCenter.y, circleRadius - radius, Direction.CCW);
		flip = !flip;
	}

	private void drawArcV2(final Point center, final float radius, final int bWidth, final int bHeight) {
		final Point circleCenter = new Point();
		int circleRadius = 0;
		if (flip == true) {
			circleCenter.x = 0;
			circleCenter.y = bHeight;
			circleRadius = center.x;
		} else {
			circleCenter.x = bWidth;
			circleCenter.y = bHeight;
			circleRadius = bWidth - center.x;
		}

		addCircle(circleCenter.x, circleCenter.y, circleRadius + radius, Direction.CW);
		addCircle(circleCenter.x, circleCenter.y, circleRadius - radius, Direction.CCW);
		flip = !flip;
	}

	private int calcDistance(final Point p1, final Point p2) {
		final double sqrt = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
		return (int) Math.round(sqrt);
	}

}
