
package de.geithonline.wallpaperdesigner.shapes.composed;

import de.geithonline.wallpaperdesigner.settings.EyeOptions;
import de.geithonline.wallpaperdesigner.settings.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;

public class QualleTopviewPreset01 {

	public static final TailOptionsLine lineOptions = getTailOptionsLine();
	public static final TailOptionsBubbles bubbleOptions = getTailOptionsBubbles();

	public static final EyeOptions eyeOptions = getEyeOptions();

	public static EyeOptions getEyeOptions() {
		final EyeOptions options = new EyeOptions();
		options.minBrightness = 60;
		options.maxBrightness = 90;
		options.minAnzEyes = 4;
		options.maxAnzEyes = 5;
		return options;
	}

	private static TailOptionsLine getTailOptionsLine() {
		final TailOptionsLine options = new TailOptionsLine();
		options.anzTails = 50;
		options.tailRotationType = TailRotationType.Random;
		options.sinusAmplitudeType = SinusAmplitudeType.normal;

		options.randomFlip = true;
		options.outline = true;
		options.colorful = false;

		options.minAmplitude = 0.1f;
		options.maxAmplitude = 0.3f;

		options.minSinusRepeats = 1;
		options.maxSinusRepeats = 3;

		options.minLength = 0.5f;
		options.maxLength = 1.7f;
		options.inset = 1.0f;
		options.minBrightness = 40;
		options.maxBrightness = 90;
		return options;
	}

	public static TailOptionsBubbles getTailOptionsBubbles() {
		final TailOptionsBubbles options = new TailOptionsBubbles();

		options.anzTails = 15;
		options.tailRotationType = TailRotationType.Random;

		options.sinusAmplitudeType = SinusAmplitudeType.normal;
		options.sinusObjectsSizingType = ESinusObjectsSizingType.random;
		// Log.i("SizingType", "=" + options.sinusObjectsSizingType);
		options.randomFlip = true;
		options.outline = false;
		options.colorful = false;

		options.minAmplitude = 0.1f;
		options.maxAmplitude = 0.3f;

		options.minSinusRepeats = 1;
		options.maxSinusRepeats = 3;

		options.minLength = 1.5f;
		options.maxLength = 2.5f;
		options.minBrightness = 40;
		options.maxBrightness = 90;

		options.bubbleRadius = 0.1f;

		options.percentOfBubblesToDraw = 95;
		return options;
	}

}
