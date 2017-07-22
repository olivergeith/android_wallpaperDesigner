
package de.geithonline.wallpaperdesigner.shapes.composed;

import de.geithonline.wallpaperdesigner.settings.EyeOptions;
import de.geithonline.wallpaperdesigner.settings.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;

public class QualleTopviewPreset02 {

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
		options.anzTails = 20;
		options.tailRotationType = TailRotationType.Random;
		options.sinusAmplitudeType = SinusAmplitudeType.normal;

		options.randomFlip = false;
		options.outline = true;
		options.colorful = false;

		options.minAmplitude = 0.2f;
		options.maxAmplitude = 0.2f;

		options.minSinusRepeats = 3;
		options.maxSinusRepeats = 3;

		options.minLength = 3.0f;
		options.maxLength = 6.5f;
		options.inset = 1.0f;
		options.minBrightness = 40;
		options.maxBrightness = 90;

		return options;
	}

	public static TailOptionsBubbles getTailOptionsBubbles() {
		final TailOptionsBubbles options = new TailOptionsBubbles();

		options.anzTails = 15;
		options.tailRotationType = TailRotationType.Even;

		options.sinusAmplitudeType = SinusAmplitudeType.normal;
		options.sinusObjectsSizingType = ESinusObjectsSizingType.decreasing;
		// Log.i("SizingType", "=" + options.sinusObjectsSizingType);
		options.randomFlip = false;
		options.outline = false;
		options.colorful = false;

		options.minAmplitude = 0.1f;
		options.maxAmplitude = 0.4f;

		options.minSinusRepeats = 3;
		options.maxSinusRepeats = 3;

		options.minLength = 2.0f;
		options.maxLength = 5.5f;
		options.minBrightness = 40;
		options.maxBrightness = 90;

		options.bubbleRadius = 0.15f;

		options.percentOfBubblesToDraw = 100;
		return options;
	}

}
