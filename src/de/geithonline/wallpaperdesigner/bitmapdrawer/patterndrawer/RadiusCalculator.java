
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.Settings.RADIUS_TYPE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RadiusCalculator {

    private final int anzPattern;
    private final int minRadius;
    private final int maxRadius;
    private final int delta;
    private final int blurLevel1;
    private final int blurLevel2;
    private final int blurLevel3;
    private final int maxRadiusBelowBlurStage1;
    private final int maxRadiusBelowBlurStage2;
    private final int maxRadiusBelowBlurStage3;
    private final RADIUS_TYPE radiusType;

    public RadiusCalculator(final int anzPattern, final int minRadius, final int maxRadius,
            final RADIUS_TYPE radiusType) {
        this.anzPattern = anzPattern;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.radiusType = radiusType;
        delta = maxRadius - minRadius;
        blurLevel1 = anzPattern * Settings.getBlurrStage1() / 100;
        blurLevel2 = anzPattern * Settings.getBlurrStage2() / 100;
        blurLevel3 = anzPattern * Settings.getBlurrStage3() / 100;
        maxRadiusBelowBlurStage1 = minRadius + delta * Settings.getBlurrStage1() / 100;
        maxRadiusBelowBlurStage2 = minRadius + delta * Settings.getBlurrStage2() / 100;
        maxRadiusBelowBlurStage3 = minRadius + delta * Settings.getBlurrStage3() / 100;
    }

    public int getRadius(final int index) {
        switch (radiusType) {
            default:
            case random:
                return Randomizer.getRandomInt(minRadius, maxRadius);
            case increasing:
                return minRadius + (delta * index) / anzPattern;
            case decreasing:
                return maxRadius - (delta * index) / anzPattern;
            case dependingOnBlurrStage_increasing:
                if (index < blurLevel1) {
                    return Randomizer.getRandomInt(minRadius, maxRadiusBelowBlurStage1);
                } else if (index < blurLevel2) {
                    return Randomizer.getRandomInt(maxRadiusBelowBlurStage1, maxRadiusBelowBlurStage2);
                } else if (index < blurLevel3) {
                    return Randomizer.getRandomInt(maxRadiusBelowBlurStage2, maxRadiusBelowBlurStage3);
                } else {
                    return Randomizer.getRandomInt(maxRadiusBelowBlurStage3, maxRadius);
                }
            case dependingOnBlurrStage_decreasing:
                if (index < blurLevel1) {
                    return Randomizer.getRandomInt(maxRadiusBelowBlurStage3, maxRadius);
                } else if (index < blurLevel2) {
                    return Randomizer.getRandomInt(maxRadiusBelowBlurStage2, maxRadiusBelowBlurStage3);
                } else if (index < blurLevel3) {
                    return Randomizer.getRandomInt(maxRadiusBelowBlurStage1, maxRadiusBelowBlurStage2);
                } else {
                    return Randomizer.getRandomInt(minRadius, maxRadiusBelowBlurStage1);
                }
        }
    }

}
