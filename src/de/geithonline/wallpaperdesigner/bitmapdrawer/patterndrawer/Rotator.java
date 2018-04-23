
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import java.util.Arrays;
import java.util.List;

import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class Rotator {

    private final int bWidth;
    private final int bHeight;

    private float incrementingDegrees = 0;

    @SuppressWarnings("unused")
    private int index = 0;
    @SuppressWarnings("unused")
    private int anzahlPatterns;
    private final PointF randomCenter;
    private final PointF randomCenter1;
    private final PointF randomCenter2;
    private final PointF obenLinks;
    private final PointF obenRechts;
    private final PointF untenRechts;
    private final PointF untenLinks;
    private final PointF bildMitte;
    private final PointF untenRandMitte;
    private final PointF adjustableMitte;

    public Rotator(final int w, final int h) {
        bWidth = w;
        bHeight = h;
        randomCenter = new PointF(Randomizer.getRandomInt(0, bWidth), Randomizer.getRandomInt(0, bHeight));
        randomCenter1 = new PointF(Randomizer.getRandomFloat(0, bWidth * 0.45f), Randomizer.getRandomInt(0, bHeight));
        randomCenter2 = new PointF(Randomizer.getRandomFloat(bWidth * 0.55f, bWidth), Randomizer.getRandomInt(0, bHeight));
        obenLinks = new PointF(0, 0);
        obenRechts = new PointF(bWidth, 0);
        untenRechts = new PointF(bWidth, bHeight);
        untenLinks = new PointF(0, bHeight);
        bildMitte = new PointF(bWidth / 2f, bHeight / 2f);
        untenRandMitte = new PointF(bWidth / 2f, bHeight);
        adjustableMitte = new PointF(bWidth * Settings.getRotationCenterPointX(), bHeight * Settings.getRotationCenterPointY());
    }

    public float getRotationDegrees(final int randomMin, final int randomMax, final PointF center) {
        switch (Settings.getRotationStyle()) {
            default:
            case "Fixed":
                return Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            case "Random":
                return Randomizer.getRandomFloat(randomMin - 1, randomMax);
            case "Random (Range)":
                return Randomizer.getRandomFloat(Settings.getFixedRotationDegrees() - Settings.getrandomRangeDegrees(), //
                        Settings.getFixedRotationDegrees() + Settings.getrandomRangeDegrees()) + getRandom90Degrees() + getIncrementingDegrees();

            case "Around Adjustable Point":
            case "Around Adjustable Center": {
                final float winkel = GeometrieHelper.calcWinkel(center, adjustableMitte);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Center":
            case "Around Point": {
                final float winkel = GeometrieHelper.calcWinkel(center, bildMitte);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Point (Range)":
            case "Around Center (Range)": {
                final float winkel = GeometrieHelper.calcWinkel(center, bildMitte);
                final float rand = Randomizer.getRandomFloat(-Settings.getrandomRangeDegrees(), //
                        +Settings.getrandomRangeDegrees());
                return winkel + 90 + Settings.getFixedRotationDegrees() + rand + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Bottom": {
                final float winkel = GeometrieHelper.calcWinkel(center, untenRandMitte);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Corners": {
                final List<PointF> points = Arrays.asList(obenLinks, obenRechts, untenRechts, untenLinks);
                final PointF rotationCenter = GeometrieHelper.findNearestPoint(points, center);
                final float winkel = GeometrieHelper.calcWinkel(center, rotationCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around random Center": {
                final float winkel = GeometrieHelper.calcWinkel(center, randomCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around 2 random Centerpoints": {
                final List<PointF> points = Arrays.asList(randomCenter1, randomCenter2);
                final PointF rotationCenter = GeometrieHelper.findNearestPoint(points, center);
                final float winkel = GeometrieHelper.calcWinkel(center, rotationCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
        }
    }

    // /**
    // * @param point
    // * the point outside the center....
    // * @param rotationCenter
    // * @return
    // */
    // public static float calcWinkel(final PointF point, final PointF rotationCenter) {
    // final float distTCenterX = rotationCenter.x - point.x;
    // final float distTCenterY = rotationCenter.y - point.y;
    // final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
    // float winkel = (float) (alpha * 180 / Math.PI);
    // if (point.x <= rotationCenter.x) {
    // winkel = winkel + 180;
    // }
    // return winkel;
    // }

    public float getIncrementingDegrees() {
        incrementingDegrees += Settings.getIncrementingDegreesAddingAmount();
        if (incrementingDegrees > 180) {
            incrementingDegrees = -180 + (incrementingDegrees - 180);
        }
        if (incrementingDegrees < -180) {
            incrementingDegrees = 180 - (incrementingDegrees + 180);
        }
        return incrementingDegrees;
    }

    public int getRandom90Degrees() {
        if (Settings.isRandomDegreesAdding()) {
            if (Randomizer.getRandomBoolean()) {
                return Settings.getRandomDegreesAddingAmount();
            }
        }
        return 0;
    }

    public void settingProgress(final int i) {
        index = i;
    }

    public void settingMax(final int anzahlPatterns) {
        this.anzahlPatterns = anzahlPatterns;
    }
}
