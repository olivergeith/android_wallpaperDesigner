package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RadiusCalculator {

    private final int anzPattern;
    private final int minRadius;
    private final int maxRadius;
    private final int delta;
    private final int blurLevel1;
    private final int blurLevel2;
    private final int blurLevel3;

    private enum RADIUS_TYPE {
        random, rising, dependingOnBlurrStage;

        public static RADIUS_TYPE enumForName(final String name) {
            switch (name) {
            default:
            case "random":
                return RADIUS_TYPE.random;
            case "rising":
                return RADIUS_TYPE.rising;
            case "dependingOnBlurrStage":
                return RADIUS_TYPE.dependingOnBlurrStage;
            }
        }

    }

    public RadiusCalculator(final int anzPattern, final int minRadius, final int maxRadius) {
        this.anzPattern = anzPattern;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        delta = maxRadius - minRadius;
        blurLevel1 = anzPattern * Settings.getBlurrStage1() / 100;
        blurLevel2 = anzPattern * Settings.getBlurrStage2() / 100;
        blurLevel3 = anzPattern * Settings.getBlurrStage3() / 100;
        final int size1 = minRadius;
        final int size2 = 0;
    }

    public int getSize(final int index) {
        switch (getSizingType()) {
        default:
        case random:
            return Randomizer.getRandomInt(minRadius, maxRadius);
        case rising:
            return minRadius + delta * index / anzPattern;
        case dependingOnBlurrStage:
            // if(index < 1)
            return Randomizer.getRandomInt(minRadius, maxRadius);
        }
    }

    private RADIUS_TYPE getSizingType() {
        return RADIUS_TYPE.enumForName("random"); // todo von Settings lesen
    }

}
