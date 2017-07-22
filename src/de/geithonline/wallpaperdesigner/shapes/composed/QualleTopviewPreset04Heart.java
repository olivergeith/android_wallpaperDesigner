
package de.geithonline.wallpaperdesigner.shapes.composed;

import de.geithonline.wallpaperdesigner.settings.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;

public class QualleTopviewPreset04Heart {

	public static final TailOptionsLine lineOptions = getTailOptionsLine();
	public static final TailOptionsBubbles bubbleOptions = getTailOptionsBubbles();

	private static TailOptionsLine getTailOptionsLine() {
		final TailOptionsLine options = new TailOptionsLine();
		options.anzTails = 0;
		options.tailRotationType = TailRotationType.Even;
		options.sinusAmplitudeType = SinusAmplitudeType.decreasingAmplitude;

		options.randomFlip = false;
		options.outline = true;
		options.colorful = false;

		options.minAmplitude = 0.2f;
		options.maxAmplitude = 0.2f;

		options.minSinusRepeats = 15;
		options.maxSinusRepeats = 15;

		options.minLength = 2.5f;
		options.maxLength = 2.5f;
		options.inset = 1.1f;
		options.minBrightness = 40;
		options.maxBrightness = 90;

		return options;
	}

	public static TailOptionsBubbles getTailOptionsBubbles() {
		final TailOptionsBubbles options = new TailOptionsBubbles();

		options.anzTails = 25;
		options.tailRotationType = TailRotationType.Heart;

		options.sinusAmplitudeType = SinusAmplitudeType.decreasingAmplitude;
		options.sinusObjectsSizingType = ESinusObjectsSizingType.decreasing_withbigEnd;
		// Log.i("SizingType", "=" + options.sinusObjectsSizingType);
		options.randomFlip = false;
		options.outline = false;
		options.colorful = false;

		options.minAmplitude = 0.3f;
		options.maxAmplitude = 0.3f;

		options.minSinusRepeats = 3;
		options.maxSinusRepeats = 3;

		options.minLength = 0.5f;
		options.maxLength = 2.5f;

		options.bubbleRadius = 0.1f;
		options.minBrightness = 40;
		options.maxBrightness = 90;

		options.percentOfBubblesToDraw = 100;
		return options;
	}

}
