
package de.geithonline.wallpaperdesigner.shapes.composed;

import de.geithonline.wallpaperdesigner.settings.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;

public class QualleTopviewPreset04Heart {

    public static final TailOptionsLine lineOptions = getTailOptionsLine();
    public static final TailOptionsBubbles bubbleOptions = getTailOptionsBubbles();

    private static TailOptionsLine getTailOptionsLine() {
        final TailOptionsLine lineOptions = new TailOptionsLine();
        lineOptions.anzTails = 0;
        lineOptions.tailRotationType = TailRotationType.Even;
        lineOptions.sinusAmplitudeType = SinusAmplitudeType.decreasingAmplitude;

        lineOptions.randomFlip = false;
        lineOptions.outline = true;
        lineOptions.colorful = false;

        lineOptions.minAmplitude = 0.2f;
        lineOptions.maxAmplitude = 0.2f;

        lineOptions.minSinusRepeats = 15;
        lineOptions.maxSinusRepeats = 15;

        lineOptions.minLength = 2.5f;
        lineOptions.maxLength = 2.5f;
        lineOptions.inset = 1.1f;
        return lineOptions;
    }

    public static TailOptionsBubbles getTailOptionsBubbles() {
        final TailOptionsBubbles bubbleOptions = new TailOptionsBubbles();

        bubbleOptions.anzTails = 25;
        bubbleOptions.tailRotationType = TailRotationType.Heart;

        bubbleOptions.sinusAmplitudeType = SinusAmplitudeType.decreasingAmplitude;
        bubbleOptions.sinusObjectsSizingType = ESinusObjectsSizingType.decreasing_withbigEnd;
        // Log.i("SizingType", "=" + options.sinusObjectsSizingType);
        bubbleOptions.randomFlip = false;
        bubbleOptions.outline = false;
        bubbleOptions.colorful = false;

        bubbleOptions.minAmplitude = 0.3f;
        bubbleOptions.maxAmplitude = 0.3f;

        bubbleOptions.minSinusRepeats = 3;
        bubbleOptions.maxSinusRepeats = 3;

        bubbleOptions.minLength = 0.5f;
        bubbleOptions.maxLength = 2.5f;

        bubbleOptions.bubbleRadius = 0.1f;

        bubbleOptions.percentOfBubblesToDraw = 100;
        return bubbleOptions;
    }

}
