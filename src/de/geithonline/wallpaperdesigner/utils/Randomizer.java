package de.geithonline.wallpaperdesigner.utils;

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

	public static int randomizeColor(final int color, final int range, final COLOR_RANDOMIZING_TYPE type) {
		final int dRed = getRandomInt(-range, range);
		final int dGreen = getRandomInt(-range, range);
		final int dBlue = getRandomInt(-range, range);
		final float dhue = Randomizer.getRandomFloat(-range, range); // ergibt ein max Hue-delta 192 (0-360 ist der
																		// Wertebereich für hue)
		final float dsat = Randomizer.getRandomFloat(-range, range) / 192f; // 192 is max... wir brauchen einen Wert
																			// zwischen 0 und 1
		switch (type) {
		default:
		case FULL_RGB:
			return ColorHelper.changeColor(color, dRed, dGreen, dBlue);
		case ONLY_RED:
			return ColorHelper.changeColor(color, dRed, 0, 0);
		case ONLY_GREEN:
			return ColorHelper.changeColor(color, 0, dGreen, 0);
		case ONLY_BLUE:
			return ColorHelper.changeColor(color, 0, 0, dBlue);
		case HUE:
			return ColorHelper.adjustHSV(color, dhue, 0, 0);
		case SATURATION:
			return ColorHelper.adjustHSV(color, 0, dsat, 0);
		case HUE_AND_SATURATION:
			return ColorHelper.adjustHSV(color, dhue, dsat, 0);
		case PUSH_RED:
			return ColorHelper.changeColor(color, Math.abs(dRed), 0, 0); // push geht nur ins positive
		case PUSH_GREEN:
			return ColorHelper.changeColor(color, 0, Math.abs(dGreen), 0);
		case PUSH_BLUE:
			return ColorHelper.changeColor(color, 0, 0, Math.abs(dBlue));
		}
	}
}
