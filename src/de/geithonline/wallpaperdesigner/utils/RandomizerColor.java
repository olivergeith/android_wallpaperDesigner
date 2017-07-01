package de.geithonline.wallpaperdesigner.utils;

import de.geithonline.wallpaperdesigner.settings.Settings.COLOR_RANDOMIZING_TYPE;

public class RandomizerColor {
	public static int randomizeColor(final int color, final int range, final COLOR_RANDOMIZING_TYPE type) {
		final int dRed = Randomizer.getRandomInt(-range, range);
		final int dGreen = Randomizer.getRandomInt(-range, range);
		final int dBlue = Randomizer.getRandomInt(-range, range);
		final float dhue = Randomizer.getRandomFloat(-range, range); // ergibt ein
																		// max
																		// Hue-delta
																		// 192 (0-360
																		// ist der
																		// Wertebereich
																		// f�r hue)
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
		case PUSH_RED:
			return ColorHelper.changeColor(color, Math.abs(dRed), 0, 0); // push geht
																			// nur ins
																			// positive
		case PUSH_GREEN:
			return ColorHelper.changeColor(color, 0, Math.abs(dGreen), 0);
		case PUSH_BLUE:
			return ColorHelper.changeColor(color, 0, 0, Math.abs(dBlue));
		}
	}

}