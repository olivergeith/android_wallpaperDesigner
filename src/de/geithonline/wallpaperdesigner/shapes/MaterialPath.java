package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class MaterialPath extends Path {

	public enum MATERIAL_TYPE {
		SKYLINE, STIPE, HALF_STIPE, ARC1, ARC2, ARC3, EDGY_BARS, ROTATING_BARS, ROTATING_TRIANGLES, //
		ROTATING_ARCHES_RANDOM_SIZE, ROTATING_QUARTER_ARCHES, ROTATING_HALF_ARCHES, ROTATING_THREE_QUARTER_ARCHES, STIPE_V2, STIPE_V3, HALF_STIPE_V2, HALF_STIPE_V3;
	}

	public MaterialPath(final Point center, final float radius, final boolean filled, final int bWidth,
			final int bHeight, final MATERIAL_TYPE type) {
		super();
		switch (type) {
		default:
		case STIPE:
			drawStripe(center, radius, bWidth, bHeight);
			break;
		case STIPE_V2:
			drawStripeV2(center, radius, bWidth, bHeight, 30);
			break;
		case STIPE_V3:
			drawStripeV3(center, radius, bWidth, bHeight, 45);
			break;
		case HALF_STIPE:
			drawHalfStripe(center, radius, bWidth, bHeight);
			break;
		case HALF_STIPE_V2:
			drawHalfStripe2(center, radius, bWidth, bHeight, 45);
			break;
		case HALF_STIPE_V3:
			drawHalfStripe2(center, radius, bWidth, bHeight, 30);
			break;
		case ARC1:
			drawArcV1(center, radius, bWidth, bHeight);
			break;
		case ARC2:
			drawArcV2(center, radius, bWidth, bHeight);
			break;
		case ARC3:
			drawArcV3(center, radius, bWidth, bHeight);
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
		final float rectLength = bWidth * 1.5f;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + rectLength);

		if (flip) {
			drehwinkel = 45;
		} else {
			drehwinkel = -45;
		}

		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
		flip = !flip;
	}

	private void drawStripeV2(final Point center, final float radius, final int bWidth, final int bHeight,
			final int rotation) {
		int drehwinkel = rotation;
		final float rectLength = bWidth * 1.5f;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + rectLength);

		if (flip) {
			drehwinkel = rotation + 90;
		}

		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
		flip = !flip;
	}

	private static int fli = 0;

	private void drawStripeV3(final Point center, final float radius, final int bWidth, final int bHeight,
			final int rotation) {
		int drehwinkel = rotation;
		final float rectLength = bWidth * 1.3f;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + rectLength);

		if (fli == 0 || fli == 1) {
			drehwinkel = rotation + 90;
		}

		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
		fli = fli + 1;
		if (fli == 4) {
			fli = 0;
		}
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
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
		addPath(p);
	}

	private void drawHalfStripe2(final Point center, final float radius, final int bWidth, final int bHeight,
			final int rotation) {
		int drehwinkel = 0;
		final float rectLength = bHeight * 2f;
		final RectF rect = new RectF(center.x - radius, center.y - rectLength, center.x + radius, center.y + 1 * radius);
		switch (flippy) {
		default:
		case 0:
			drehwinkel = 0 + rotation;
			break;
		case 1:
			drehwinkel = 90 + rotation;
			break;
		case 2:
			drehwinkel = 180 + rotation;
			break;
		case 3:
			drehwinkel = 270 + rotation;
			break;
		}

		flippy++;
		if (flippy == 4) {
			flippy = 0;
		}
		final Path p = new Path();
		p.addRect(rect, Direction.CW);
		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
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

		PathHelper.rotatePath(center.x, center.y, p, drehwinkel);
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

		PathHelper.rotatePath(center.x, center.y, p, winkel);
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
			PathHelper.rotatePath(imageCenter.x, imageCenter.y, path, rotateWinkel);
		} else {
			PathHelper.rotatePath(imageCenter.x, imageCenter.y, path, rotateWinkel + 180);
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

		PathHelper.rotatePath(center.x, center.y, p, winkel);
		addPath(p);
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

	private static int flippy = initFlippy();

	public static int initFlippy() {
		final int i = Randomizer.getRandomInt(-1, 3);
		Log.i("Flippy", "init with:" + i);
		return i;
	}

	private void drawArcV3(final Point center, final float radius, final int bWidth, final int bHeight) {
		Point circleCenter = new Point();
		final Point centerUL = new Point(0, bHeight);
		final Point centerUR = new Point(bWidth, bHeight);
		final Point centerOR = new Point(bWidth, 0);
		final Point centerOL = new Point(0, 0);
		int circleRadius = 0;
		switch (flippy) {
		default:
		case 0:
			circleCenter = centerUL;
			circleRadius = center.x;
			break;
		case 1:
			circleCenter = centerUR;
			circleRadius = bWidth - center.x;
			break;
		case 2:
			circleCenter = centerOR;
			circleRadius = center.x;
			break;
		case 3:
			circleCenter = centerOL;
			circleRadius = bWidth - center.x;
			break;
		}

		addCircle(circleCenter.x, circleCenter.y, circleRadius + radius, Direction.CW);
		addCircle(circleCenter.x, circleCenter.y, circleRadius - radius, Direction.CCW);
		flippy++;
		if (flippy == 4) {
			flippy = 0;
		}

	}

}
