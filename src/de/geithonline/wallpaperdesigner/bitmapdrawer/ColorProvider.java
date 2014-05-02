package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Color;

public class ColorProvider {

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

	protected int randomizeColor(final int color, final int range) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		final int offR = getRandomInt(-range, range);
		final int offG = getRandomInt(-range, range);
		final int offB = getRandomInt(-range, range);
		r = validateColor(r + offR);
		g = validateColor(g + offG);
		b = validateColor(b + offB);
		return Color.rgb(r, g, b);
	}

	protected int validateColor(int col) {
		if (col < 0) {
			col = 0;
		}
		if (col > 255) {
			col = 255;
		}
		return col;
	}

}
