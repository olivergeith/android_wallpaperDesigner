
package de.geithonline.wallpaperdesigner.shapes.composed;

import de.geithonline.wallpaperdesigner.settings.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;

public class QualleTopviewPreset01 {

    public static final TailOptionsLine lineOptions = getTailOptionsLine();
    public static final TailOptionsBubbles bubbleOptions = getTailOptionsBubbles();

    private static TailOptionsLine getTailOptionsLine() {
        final TailOptionsLine lineOptions = new TailOptionsLine();
        lineOptions.anzTails = 50;
        lineOptions.tailRotationType = TailRotationType.Random;
        lineOptions.sinusAmplitudeType = SinusAmplitudeType.normal;

        lineOptions.randomFlip = true;
        lineOptions.outline = true;
        lineOptions.colorful = false;

        lineOptions.minAmplitude = 0.1f;
        lineOptions.maxAmplitude = 0.3f;

        lineOptions.minSinusRepeats = 1;
        lineOptions.maxSinusRepeats = 3;

        lineOptions.minLength = 0.5f;
        lineOptions.maxLength = 1.7f;
        lineOptions.inset = 1.0f;
        return lineOptions;
    }

    public static TailOptionsBubbles getTailOptionsBubbles() {
        final TailOptionsBubbles bubbleOptions = new TailOptionsBubbles();

        bubbleOptions.anzTails = 15;
        bubbleOptions.tailRotationType = TailRotationType.Random;

        bubbleOptions.sinusAmplitudeType = SinusAmplitudeType.normal;
        bubbleOptions.sinusObjectsSizingType = ESinusObjectsSizingType.random;
        // Log.i("SizingType", "=" + options.sinusObjectsSizingType);
        bubbleOptions.randomFlip = true;
        bubbleOptions.outline = false;
        bubbleOptions.colorful = false;

        bubbleOptions.minAmplitude = 0.1f;
        bubbleOptions.maxAmplitude = 0.3f;

        bubbleOptions.minSinusRepeats = 1;
        bubbleOptions.maxSinusRepeats = 3;

        bubbleOptions.minLength = 1.5f;
        bubbleOptions.maxLength = 2.5f;

        bubbleOptions.bubbleRadius = 0.1f;

        bubbleOptions.percentOfBubblesToDraw = 95;
        return bubbleOptions;
    }

}
