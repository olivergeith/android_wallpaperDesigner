
package de.geithonline.wallpaperdesigner.utils;

public class Randomizer {

	public static float getRandomFloat(final float min, final float max) {
		final float mRandom = (float) (min + Math.random() * (max - min));
		return mRandom;
	}

	public static float getRandomFloatAlternating(final float min, final float max) {
		final float mRandom = (float) (min + Math.random() * (max - min));
		if (getRandomBoolean()) {
			return mRandom;
		} else {
			return -mRandom;
		}
	}

	public static int getRandomInt(final int min, final int max) {
		final int mRandom = min + (int) Math.round(Math.random() * (max - min));
		return mRandom;
	}

	public static boolean getRandomBoolean() {
		final int mRandom = (int) Math.round(Math.random() * 1);
		if (mRandom == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @param percent
	 *            1-99
	 * @return true in percent of cases!
	 */
	public static boolean getRandomBooleanTrueInPercentOfCases(final int percent) {
		final int prozent = validateInt(percent, 1, 99);
		if (percent == 100) {
			return true;
		}
		if (percent == 0) {
			return false;
		}
		if (getRandomInt(1, 99) <= prozent) {
			return true;
		}
		return false;
	}

	public static int validateInt(final int value, final int min, final int max) {
		if (value > max) {
			return max;
		}
		if (value < min) {
			return min;
		}
		return value;
	}

}
