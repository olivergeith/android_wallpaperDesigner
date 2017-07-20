
package de.geithonline.wallpaperdesigner.shapes.composed;

import de.geithonline.wallpaperdesigner.settings.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;

public class QualleTopviewPreset02 {

    public static final TailOptionsLine lineOptions = getTailOptionsLine();
    public static final TailOptionsBubbles bubbleOptions = getTailOptionsBubbles();

    private static TailOptionsLine getTailOptionsLine() {
        final TailOptionsLine lineOptions = new TailOptionsLine();
        lineOptions.anzTails = 20;
        lineOptions.tailRotationType = TailRotationType.Random;
        lineOptions.sinusAmplitudeType = SinusAmplitudeType.normal;

        lineOptions.randomFlip = false;
        lineOptions.outline = true;
        lineOptions.colorful = false;

        lineOptions.minAmplitude = 0.2f;
        lineOptions.maxAmplitude = 0.2f;

        lineOptions.minSinusRepeats = 3;
        lineOptions.maxSinusRepeats = 3;

        lineOptions.minLength = 3.0f;
        lineOptions.maxLength = 6.5f;
        lineOptions.inset = 1.0f;
        return lineOptions;
    }

    public static TailOptionsBubbles getTailOptionsBubbles() {
        final TailOptionsBubbles bubbleOptions = new TailOptionsBubbles();

        bubbleOptions.anzTails = 15;
        bubbleOptions.tailRotationType = TailRotationType.Even;

        bubbleOptions.sinusAmplitudeType = SinusAmplitudeType.normal;
        bubbleOptions.sinusObjectsSizingType = ESinusObjectsSizingType.decreasing;
        // Log.i("SizingType", "=" + options.sinusObjectsSizingType);
        bubbleOptions.randomFlip = false;
        bubbleOptions.outline = false;
        bubbleOptions.colorful = false;

        bubbleOptions.minAmplitude = 0.1f;
        bubbleOptions.maxAmplitude = 0.4f;

        bubbleOptions.minSinusRepeats = 3;
        bubbleOptions.maxSinusRepeats = 3;

        bubbleOptions.minLength = 2.0f;
        bubbleOptions.maxLength = 5.5f;

        bubbleOptions.bubbleRadius = 0.15f;

        bubbleOptions.percentOfBubblesToDraw = 100;
        return bubbleOptions;
    }

}
