
package de.geithonline.wallpaperdesigner.settings;

public class TailOptions {

    public enum RotationType {
        Random, Even;
    }

    public int minSinusRepeats = 1; // Anzahl Halbwellen
    public int maxSinusRepeats = 3; // Anzahl Halbwellen
    public float maxAmplitude = 0.3f; // a multiple of radius
    public float minAmplitude = 0.1f; // a multiple of radius
    public int anzTails = 50; // Anzahl Tails
    public float maxLength = 1.0f; // a multiple of radius
    public float minLength = 3.0f; // a multiple of radius
    public boolean randomFlip = false;
    public RotationType rotationType = RotationType.Even;

}
