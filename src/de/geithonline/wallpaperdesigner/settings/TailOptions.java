
package de.geithonline.wallpaperdesigner.settings;

public class TailOptions {

    public enum TailRotationType {
        Random, Even, Spiral, Heart;
        public static TailRotationType enumForName(final String name) {
            switch (name) {
                default:
                case "Even":
                    return TailRotationType.Even;
                case "Spiral":
                    return TailRotationType.Spiral;
                case "Random":
                    return TailRotationType.Random;
                case "Heart":
                    return TailRotationType.Heart;
            }
        }
    }

    public enum SinusAmplitudeType {
        normal, decreasingAmplitude, increasingAmplitude;
        public static SinusAmplitudeType enumForName(final String name) {
            switch (name) {
                default:
                case "Decreasing Amplitude":
                    return SinusAmplitudeType.decreasingAmplitude;
                case "Increasing Amplitude":
                    return SinusAmplitudeType.increasingAmplitude;
                case "Normal":
                    return SinusAmplitudeType.normal;
            }
        }

    }

    public int minSinusRepeats = 1; // Anzahl Halbwellen
    public int maxSinusRepeats = 3; // Anzahl Halbwellen
    public float maxAmplitude = 0.3f; // a multiple of radius
    public float minAmplitude = 0.1f; // a multiple of radius
    public int anzTails = 25; // Anzahl Tails
    public float maxLength = 1.0f; // a multiple of radius
    public float minLength = 3.0f; // a multiple of radius
    public boolean randomFlip = false;
    public boolean outline = true;
    public boolean colorful = true;
    public TailRotationType tailRotationType = TailRotationType.Even;
    public SinusAmplitudeType sinusAmplitudeType = SinusAmplitudeType.decreasingAmplitude;

}
