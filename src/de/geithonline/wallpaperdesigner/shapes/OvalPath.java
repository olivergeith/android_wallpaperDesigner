package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class OvalPath extends Path {

	public enum OVAL_TYPE {
		NORMAL, RANDOM_WIDTH, RANDOM;
	}

	private RectF oval;

	public RectF getOval() {
		return oval;
	}

	public OvalPath(final PointF center, final float radiusW, final float radiusH) {
		drawOval(center, radiusW, radiusH, Direction.CW);
	}

	public OvalPath(final PointF center, final float radiusW, final float radiusH, final Direction dir) {
		drawOval(center, radiusW, radiusH, dir);
	}

	public OvalPath(final RectF oval, final Direction dir) {
		this.oval = oval;
		addOval(oval, dir);
	}

	public OvalPath(final PointF center, final float radiusW, final float radiusH, final Direction dir, final OVAL_TYPE type) {
		switch (type) {
		default:
		case NORMAL:
			drawOval(center, radiusW, radiusH, dir);
			break;
		case RANDOM_WIDTH:
			drawOvalRandomWidth(center, radiusW, radiusH, dir);
			break;
		case RANDOM:
			drawOvalRandom(center, radiusW, radiusH, dir);
			break;
		}
	}

	private void drawOval(final PointF center, final float radiusW, final float radiusH, final Direction dir) {
		oval = new RectF();
		oval.left = center.x - radiusW;
		oval.right = center.x + radiusW;
		oval.top = center.y - radiusH;
		oval.bottom = center.y + radiusH;
		addOval(oval, dir);
	}

	private void drawOvalRandomWidth(final PointF center, final float radiusW, final float radiusH, final Direction dir) {
		oval = new RectF();
		final float rw = Randomizer.getRandomFloat(radiusW / 10, radiusW);
		oval.left = center.x - rw;
		oval.right = center.x + rw;
		oval.top = center.y - radiusH;
		oval.bottom = center.y + radiusH;
		addOval(oval, dir);
	}

	private void drawOvalRandom(final PointF center, final float radiusW, final float radiusH, final Direction dir) {
		oval = new RectF();
		float rw = radiusW;
		float rh = radiusH;
		// entweder die breite oder die Höhe randomizen
		if (Randomizer.getRandomBoolean()) {
			rw = Randomizer.getRandomFloat(radiusW / 10, radiusW);
		} else {
			rh = Randomizer.getRandomFloat(radiusH / 10, radiusH);
		}
		oval.left = center.x - rw;
		oval.right = center.x + rw;
		oval.top = center.y - rh;
		oval.bottom = center.y + rh;
		addOval(oval, dir);
	}

}
