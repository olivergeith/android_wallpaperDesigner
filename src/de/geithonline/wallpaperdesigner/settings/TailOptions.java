
package de.geithonline.wallpaperdesigner.settings;

import de.geithonline.wallpaperdesigner.shapes.SinusPath.SinusAmplitudeType;

public class TailOptions {

    public enum TailRotationType {
        Random, Even;
        public static TailRotationType enumForName(final String name) {
            switch (name) {
                default:
                case "Even":
                    return TailRotationType.Even;
                case "Random":
                    return TailRotationType.Random;
            }
        }
    }

    public int minSinusRepeats = 1; // Anzahl Halbwellen
    public int maxSinusRepeats = 3; // Anzahl Halbwellen
    public float maxAmplitude = 0.3f; // a multiple of radius
    public float minAmplitude = 0.1f; // a multiple of radius
    public int anzTails = 50; // Anzahl Tails
    public float maxLength = 1.0f; // a multiple of radius
    public float minLength = 3.0f; // a multiple of radius
    public boolean randomFlip = false;
    public TailRotationType tailRotationType = TailRotationType.Even;
    public SinusAmplitudeType sinusAmplitudeType = SinusAmplitudeType.decreasingAmplitude;

}
