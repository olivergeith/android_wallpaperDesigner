package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class BlitzPath extends Path {

	public BlitzPath(final Point center, final int maxsteps, final int maxsteplength, final boolean rectangular) {
		super();

		final Point p = new Point(center);
		moveTo(p.x, p.y);

		final boolean xDirection = getRandomBoolean();
		final boolean yDirection = getRandomBoolean();

		final int maxstepX = getRandomInt(maxsteplength * 3 / 10, maxsteplength);
		final int maxstepY = getRandomInt(maxsteplength * 3 / 10, maxsteplength);

		for (int i = 0; i < maxsteps; i++) {

			int stepX;
			if (xDirection) {
				stepX = getRandomInt(0, maxstepX);
			} else {
				stepX = getRandomInt(-maxstepX, 0);
			}
			int stepY;
			if (yDirection) {
				stepY = getRandomInt(0, maxstepY);
			} else {
				stepY = getRandomInt(-maxstepY, 0);
			}

			p.x = p.x + stepX;
			// if (p.x < 0) {
			// p.x = -p.x;
			// }
			// if (p.x > xmax) {
			// p.x = xmax - (p.x - xmax);
			// }
			if (rectangular) {
				lineTo(p.x, p.y);
			}

			p.y = p.y + stepY;
			// if (p.y < 0) {
			// p.y = -p.y;
			// }
			// if (p.y > ymax) {
			// p.y = ymax - (p.y - ymax);
			// }
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
