package de.geithonline.wallpaperdesigner.utils;

import de.geithonline.wallpaperdesigner.settings.ColorRandOptions;

public class RandomizerColor {
	public static int randomizeColor(final int color, final ColorRandOptions options) {
		final int dRed = Randomizer.getRandomInt(options.minColorRange, options.maxColorRange);
		final int dGreen = Randomizer.getRandomInt(options.minColorRange, options.maxColorRange);
		final int dBlue = Randomizer.getRandomInt(options.minColorRange, options.maxColorRange);
		final float dhue = Randomizer.getRandomFloat(options.minColorRange, options.maxColorRange);

		switch (options.randomizingType) {
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
		}
	}

}
