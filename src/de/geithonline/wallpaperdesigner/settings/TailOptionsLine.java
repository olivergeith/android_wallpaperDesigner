
package de.geithonline.wallpaperdesigner.settings;

import de.geithonline.wallpaperdesigner.shapes.SinusPath.SinusType;

public class TailOptionsLine extends TailOptions {
    public enum TailType {
        No, Sinus, Straight;
    }

    public boolean closedSinus = false;
    public TailType tailtype = TailType.Sinus;
    public SinusType sinusType = SinusType.decreasingAmplitude;
}
