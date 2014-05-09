package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class RandomPath extends Path {

	// public RandomPath(final Point center, final int xmax, final int ymax, final int maxsteps, final int maxsteplength) {
	// super();
	//
	// final Point p = new Point(center);
	// moveTo(p.x, p.y);
	//
	// for (int i = 0; i < maxsteps; i++) {
	// final int stepX = getRandomInt(-maxsteplength, maxsteplength);
	// final int stepY = getRandomInt(-maxsteplength, maxsteplength);
	// p.x = p.x + stepX;
	// p.y = p.y + stepY;
	// if (p.x < 0) {
	// p.x = -p.x;
	// }
	// if (p.x > xmax) {
	// p.x = xmax - (p.x - xmax);
	// }
	// if (p.y < 0) {
	// p.y = -p.y;
	// }
	// if (p.y > ymax) {
	// p.y = ymax - (p.y - ymax);
	// }
	// lineTo(p.x, p.y);
	// }
	//
	// }

	public RandomPath(final Point center, final int xmax, final int ymax, final int maxsteps, final int maxsteplength, final boolean rectangular) {
		super();

		final Point p = new Point(center);
		moveTo(p.x, p.y);

		for (int i = 0; i < maxsteps; i++) {
			final int stepX = getRandomInt(-maxsteplength, maxsteplength);
			final int stepY = getRandomInt(-maxsteplength, maxsteplength);
			p.x = p.x + stepX;
			if (p.x < 0) {
				p.x = -p.x;
			}
			if (p.x > xmax) {
				p.x = xmax - (p.x - xmax);
			}
			if (rectangular) {
				lineTo(p.x, p.y);
			}

			p.y = p.y + stepY;
			if (p.y < 0) {
				p.y = -p.y;
			}
			if (p.y > ymax) {
				p.y = ymax - (p.y - ymax);
			}
			lineTo(p.x, p.y);
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
