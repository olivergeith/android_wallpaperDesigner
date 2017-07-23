
package de.geithonline.wallpaperdesigner.settings;

public class ColorRandOptions {

	public static final String KEY_COLOR_RANDOMIZING_TYPE = "Color.colorRandomizingType";
	public static final String KEY_COLOR_MIN_COLOR_RANGE = "Color.minColorRange";
	public static final String KEY_COLOR_MAX_COLOR_RANGE = "Color.maxColorRange";
	public static final String KEY_COLOR_MIN_BRIGHTNESS_RANGE = "Color.minBrightnessRange";
	public static final String KEY_COLOR_MAX_BRIGHTNESS_RANGE = "Color.maxBrightnessRange";
	public static final String KEY_COLOR_MIN_SATURATION_RANGE = "Color.minSaturationRange";
	public static final String KEY_COLOR_MAX_SATURATION_RANGE = "Color.maxSaturationRange";

	public enum COLOR_RANDOMIZING_TYPE {
		FULL_RGB, ONLY_RED, ONLY_GREEN, ONLY_BLUE, HUE;

		public static COLOR_RANDOMIZING_TYPE enumForName(final String name) {
			switch (name) {
			default:
			case "full RGB":
				return COLOR_RANDOMIZING_TYPE.FULL_RGB;
			case "only RED":
				return COLOR_RANDOMIZING_TYPE.ONLY_RED;
			case "only GREEN":
				return COLOR_RANDOMIZING_TYPE.ONLY_GREEN;
			case "only BLUE":
				return COLOR_RANDOMIZING_TYPE.ONLY_BLUE;
			case "hue":
				return COLOR_RANDOMIZING_TYPE.HUE;
			}
		}
	}

	public COLOR_RANDOMIZING_TYPE randomizingType = COLOR_RANDOMIZING_TYPE.FULL_RGB;
	public int minColorRange = -32;
	public int maxColorRange = 32;
	public int minBrightnessRange = -32;
	public int maxBrightnessRange = 32;
	public int minSaturationRange = -32;
	public int maxSaturationRange = 32;

	public boolean isRandomizeBrightness() {
		return !(minBrightnessRange == 0 && maxBrightnessRange == 0);
	}

	public boolean isRandomizeSaturation() {
		return !(minSaturationRange == 0 && maxSaturationRange != 0);
	}

	public boolean isRandomizeColor() {
		return !(minColorRange == 0 && maxColorRange == 0);
	}

}
