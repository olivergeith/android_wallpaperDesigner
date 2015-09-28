package de.geithonline.wallpaperdesigner.utils;

import android.graphics.Color;
import de.geithonline.wallpaperdesigner.settings.Settings.COLOR_RANDOMIZING_TYPE;

public class Randomizer {

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

	public static int randomizeColor(final int color, final int range) {
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

	public static int randomizeColor(final int color, final int range, final COLOR_RANDOMIZING_TYPE type) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		int offR = 0;
		int offG = 0;
		int offB = 0;
		switch (type) {
			default:
			case FULL_RGB:
				offR = getRandomInt(-range, range);
				offG = getRandomInt(-range, range);
				offB = getRandomInt(-range, range);
				break;
			case ONLY_RED:
				offR = getRandomInt(-range, range);
				break;
			case ONLY_GREEN:
				offG = getRandomInt(-range, range);
				break;
			case ONLY_BLUE:
				offB = getRandomInt(-range, range);
				break;
		}
		r = validateColor(r + offR);
		g = validateColor(g + offG);
		b = validateColor(b + offB);
		return Color.rgb(r, g, b);
	}

	public static int randomizeColorBrightness(final int color, final int range) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		final int off = getRandomInt(-range, range);
		r = validateColor(r + off);
		g = validateColor(g + off);
		b = validateColor(b + off);
		return Color.rgb(r, g, b);
	}

	private static int validateColor(int col) {
		if (col < 0) {
			col = 0;
		}
		if (col > 255) {
			col = 255;
		}
		return col;
	}

}
