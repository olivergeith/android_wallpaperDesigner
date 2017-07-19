
package de.geithonline.wallpaperdesigner.settings;

import de.geithonline.wallpaperdesigner.shapes.composed.ESinusObjectsSizingType;
import de.geithonline.wallpaperdesigner.shapes.composed.ESinusObjectsType;

public class TailOptionsBubbles extends TailOptions {
    public int percentOfBubblesToDraw = 100;
    public float bubbleRadius = 0.2f; // a multiple of radius
    public ESinusObjectsSizingType sinusObjectsSizingType = ESinusObjectsSizingType.decreasing;
    public ESinusObjectsType sinusObjectType = ESinusObjectsType.bubble;
}
