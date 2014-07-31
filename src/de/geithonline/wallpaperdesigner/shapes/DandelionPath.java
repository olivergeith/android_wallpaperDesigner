package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class DandelionPath extends Path {

	public DandelionPath(final Point center, final float radius) {
		super();

		final int arms = 10;
		final float angle = (float) (2 * Math.PI / (arms));

		final float rBubble = radius * 0.4f;
		final float rsmallbubbleCenter = radius * 0.45f;
		final float rsmallbubble = radius * 0.05f;
		final float rinner = radius * 0.5f;
		final float router = radius * 1;

		// ineerer Kreis
		addCircle(center.x, center.y, rBubble, Direction.CCW);

		for (int i = 0; i <= arms; i++) {
			final Point centersmallBubble = new Point();
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * rinner);
			p1.y = (int) (center.y + Math.sin(i * angle) * rinner);
			p2.x = (int) (center.x + Math.cos(i * angle) * router);
			p2.y = (int) (center.y + Math.sin(i * angle) * router);
			centersmallBubble.x = (int) (center.x + Math.cos(i * angle) * rsmallbubbleCenter);
			centersmallBubble.y = (int) (center.y + Math.sin(i * angle) * rsmallbubbleCenter);
			addCircle(centersmallBubble.x, centersmallBubble.y, rsmallbubble, Direction.CCW);
			moveTo(p1.x, p1.y);
			lineTo(p2.x, p2.y);
			drawDandelion(p2, radius * 0.25f);
		}

	}

	private void drawDandelion(final Point center, final float radius) {
		final int arms = 10;
		final float angle = (float) (2 * Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final Point p2 = new Point();
			p2.x = (int) (center.x + Math.cos(i * angle) * radius);
			p2.y = (int) (center.y + Math.sin(i * angle) * radius);
			moveTo(center.x, center.y);
			lineTo(p2.x, p2.y);
		}
	}

	public static float getRandomFloat(final float min, final float max) {
		final float mRandom = (float) (min + Math.random() * (max - min));
		return mRandom;
	}

	public static int getRandomInt(final int min, final int max) {
		final int mRandom = min + (int) Math.ceil(Math.random() * (max - min));
		return mRandom;
	}

	public static boolean getRandomBoolean() {
		final int mRandom = (int) Math.round(Math.random() * 1);
		if (mRandom == 1) {
			return true;
		}
		return false;
	}

}
