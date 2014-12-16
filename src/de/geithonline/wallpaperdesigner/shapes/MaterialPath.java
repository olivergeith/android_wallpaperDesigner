package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class MaterialPath extends Path {

	public enum MATERIAL_TYPE {
		SKYLINE, STIPE, HALF_STIPE, ARC1, ARC2, EDGY_BARS, ROTATING_BARS, ROTATING_TRIANGLES, //
		ROTATING_ARCHES_RANDOM_SIZE, ROTATING_QUARTER_ARCHES, ROTATING_HALF_ARCHES, ROTATING_THREE_QUARTER_ARCHES;
	}

	public MaterialPath(final Point center, final float radius, final boolean filled, final int bWidth,
			final int bHeight, final MATERIAL_TYPE type) {
		super();
		switch (type) {
			default:
			case STIPE:
				drawStripe(center, radius, bWidth, bHeight);
				break;
			case HALF_STIPE:
				drawHalfStripe(center, radius, bWidth, bHeight);
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
			case EDGY_BARS:
				drawEdgyBars(center, radius, bWidth, bHeight);
				break;
			case ROTATING_BARS:
				drawRotatingBars(center, radius, bWidth, bHeight);
				break;
			case ROTATING_TRIANGLES:
				drawRotatingTriangles(center, radius, bWidth, bHeight);
				break;
			case ROTATING_ARCHES_RANDOM_SIZE:
			case ROTATING_QUARTER_ARCHES:
			case ROTATING_HALF_ARCHES:
			case ROTATING_THREE_QUARTER_ARCHES:
				drawRotatingArches(center, radius, bWidth, bHeight, type);
				break;
		}
	}

	private void drawSkyline(final Point center, final float radius, final int bWidth, final int bHeight) {
		final RectF rect = new RectF(center.x - radius, center.y, center.x + radius, bHeight);
		addRect(rect, Direction.CW);
	}

	private static boolean flip = true;

	private void drawStripe(final Point center, final float radius, final int bWidth, final int bHeight) {
		int drehwinkel = 0;
		final int rectLength = bWidth;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + rectLength);

		if (flip) {
			drehwinkel = 45;
		} else {
			drehwinkel = -45;
		}

		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
		flip = !flip;
	}

	private void drawNewStripe(final Point center, final float radius, final int bWidth, final int bHeight,
			final int rotation) {
		int drehwinkel = rotation;
		final float rectLength = bWidth * 1.5f;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + rectLength);

		if (flip) {
			drehwinkel = rotation + 90;
		}

		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
		flip = !flip;
	}

	private void drawHalfStripe(final Point center, final float radius, final int bWidth, final int bHeight) {
		int drehwinkel = 0;
		final int rectLength = bHeight;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + 2 * radius);

		if (center.y < bHeight / 2) // oberen
		{
			if (Randomizer.getRandomBoolean()) {
				drehwinkel = 45;
			} else {
				drehwinkel = -45;
			}
		} else // unteren
		{
			if (Randomizer.getRandomBoolean()) {
				drehwinkel = 135;
			} else {
				drehwinkel = -135;
			}
		}

		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
	}

	private void drawEdgyBars(final Point center, final float radius, final int bWidth, final int bHeight) {
		int drehwinkel = 0;
		int rectLength = 0;

		// Quadranten berechnen
		if (center.x < bWidth / 2) {
			if (center.y < bHeight / 2) // obere linke ecke
			{
				rectLength = (int) (Math.max(center.x, center.y) + radius);
				drehwinkel = -45;
			} else // untere linke ecke
			{
				rectLength = (int) (Math.max(center.x, bHeight - center.y) + radius);
				drehwinkel = -135;
			}

		} else {
			if (center.y < bHeight / 2) // obere rechte ecke
			{
				rectLength = (int) (Math.max(bWidth - center.x, center.y) + radius);
				drehwinkel = 45;
			} else // untere rechte ecke
			{
				rectLength = (int) (Math.max(bWidth - center.x, bHeight - center.y) + radius);
				drehwinkel = 135;
			}
		}

		final Path p = new Path();
		rectLength = Math.round(rectLength * 1.5f);
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y);
		p.addRect(rect, Direction.CW);

		rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
	}

	private void drawRotatingBars(final Point center, final float radius, final int bWidth, final int bHeight) {
		if (center.x == bWidth / 2 && center.y == bHeight / 2) {
			return;
		}
		int winkel = 0;
		final int rectLength = Math.round(bWidth * 0.6f);
		final RectF rect;

		final float distTCenterX = bWidth / 2 - center.x;
		final float distTCenterY = bHeight / 2 - center.y;
		final float alpha = (float) Math.atan(distTCenterY / distTCenterX);

		winkel = (int) (alpha * 180 / Math.PI);

		// Log.i("Winkel", "Alpha = " + alpha + " Winkel= " + winkel);
		final Path p = new Path();
		if (center.x > bWidth / 2) {
			rect = new RectF(center.x, center.y - radius, center.x + rectLength, center.y + radius);
		} else {
			rect = new RectF(center.x - rectLength, center.y - radius, center.x, center.y + radius);
		}

		p.addRect(rect, Direction.CW);

		rotatePath(center.x, center.y, p, winkel);
		addPath(p);
	}

	private void drawRotatingArches(final Point center, final float radius, final int bWidth, final int bHeight,
			final MATERIAL_TYPE arctype) {
		if (center.x == bWidth / 2 && center.y == bHeight / 2) {
			return;
		}
		final Path path = new Path();
		int rotateWinkel = 0;
		final float archWinkel;
		switch (arctype) {
			default:
			case ROTATING_ARCHES_RANDOM_SIZE:
				archWinkel = Randomizer.getRandomFloat((float) Math.PI * 0.1f, (float) Math.PI * 0.9f);
				break;
			case ROTATING_QUARTER_ARCHES:
				archWinkel = Randomizer.getRandomFloat((float) Math.PI * 0.15f, (float) Math.PI * 0.33f);
				// archWinkel = (float) Math.PI * 0.25f;
				break;
			case ROTATING_HALF_ARCHES:
				archWinkel = Randomizer.getRandomFloat((float) Math.PI * 0.4f, (float) Math.PI * 0.6f);
				// archWinkel = (float) Math.PI * 0.5f;
				break;
			case ROTATING_THREE_QUARTER_ARCHES:
				archWinkel = Randomizer.getRandomFloat((float) Math.PI * 0.65f, (float) Math.PI * 0.85f);
				// archWinkel = (float) Math.PI * 0.75f;
				break;
		}

		final float archWinkelDeg = (float) (archWinkel * 180 / Math.PI);

		final Point imageCenter = new Point(bWidth / 2, bHeight / 2);
		final float distTCenterX = bWidth / 2 - center.x;
		final float distTCenterY = bHeight / 2 - center.y;
		final float distCenter = (float) Math.sqrt(distTCenterX * distTCenterX + distTCenterY * distTCenterY);
		final float rotateAlpha = (float) Math.atan(distTCenterY / distTCenterX);
		rotateWinkel = (int) (rotateAlpha * 180 / Math.PI);

		final Point p = new Point();
		final Point p2 = new Point();
		final RectF oval = new RectF();

		float rad = distCenter;
		p.x = (int) (imageCenter.x + Math.cos(-archWinkel) * rad);
		p.y = (int) (imageCenter.y + Math.sin(-archWinkel) * rad);
		path.moveTo(p.x, p.y);
		// path.addCircle(p.x, p.y, radius / 10, Direction.CW);
		oval.left = imageCenter.x - rad;
		oval.right = imageCenter.x + rad;
		oval.top = imageCenter.y - rad;
		oval.bottom = imageCenter.y + rad;
		path.arcTo(oval, -archWinkelDeg, 2 * archWinkelDeg);

		rad = distCenter + 2 * radius;
		p2.x = (int) (bWidth / 2 + Math.cos(archWinkel) * rad);
		p2.y = (int) (bHeight / 2 + Math.sin(archWinkel) * rad);
		path.lineTo(p2.x, p2.y);
		oval.left = imageCenter.x - rad;
		oval.right = imageCenter.x + rad;
		oval.top = imageCenter.y - rad;
		oval.bottom = imageCenter.y + rad;
		path.arcTo(oval, archWinkelDeg, -2 * archWinkelDeg);

		path.lineTo(p.x, p.y);
		path.close();
		if (center.x > bWidth / 2) {
			rotatePath(imageCenter.x, imageCenter.y, path, rotateWinkel);
		} else {
			rotatePath(imageCenter.x, imageCenter.y, path, rotateWinkel + 180);
		}

		addPath(path);
	}

	private void drawRotatingTriangles(final Point center, final float radius, final int bWidth, final int bHeight) {
		if (center.x == bWidth / 2 && center.y == bHeight / 2) {
			return;
		}
		int winkel = 0;
		final int rectLength = Math.round(bWidth * 0.6f);

		final float distTCenterX = bWidth / 2 - center.x;
		final float distTCenterY = bHeight / 2 - center.y;
		final float alpha = (float) Math.atan(distTCenterY / distTCenterX);

		winkel = (int) (alpha * 180 / Math.PI);

		// Log.i("Winkel", "Alpha = " + alpha + " Winkel= " + winkel);
		final Path p = new Path();
		if (center.x > bWidth / 2) {
			p.moveTo(center.x, center.y + radius / 3);
			p.lineTo(center.x, center.y - radius / 3);
			p.lineTo(center.x + rectLength, center.y - radius * 2);
			p.lineTo(center.x + rectLength, center.y + radius * 2);
			p.close();
		} else {
			p.moveTo(center.x, center.y + radius / 3);
			p.lineTo(center.x, center.y - radius / 3);
			p.lineTo(center.x - rectLength, center.y - radius * 2);
			p.lineTo(center.x - rectLength, center.y + radius * 2);
			p.close();
		}

		rotatePath(center.x, center.y, p, winkel);
		addPath(p);
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
