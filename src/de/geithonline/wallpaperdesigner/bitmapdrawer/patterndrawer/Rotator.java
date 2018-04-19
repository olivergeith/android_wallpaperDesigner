
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class Rotator {

    private final int bWidth;
    private final int bHeight;

    private float incrementingDegrees = 0;

    public Rotator(final int bWidth, final int bHeight) {
        this.bWidth = bWidth;
        this.bHeight = bHeight;
    }

    public float getRotationDegrees(final int randomMin, final int randomMax, final PointF center) {
        final PointF rotationCenter = new PointF(bWidth / 2f, bHeight / 2f);

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
                rotationCenter.x = bWidth * Settings.getRotationCenterPointX();
                rotationCenter.y = bHeight * Settings.getRotationCenterPointY();
                final float winkel = calcWinkel(center, rotationCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Center":
            case "Around Point": {
                final float winkel = calcWinkel(center, rotationCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Point (Range)":
            case "Around Center (Range)": {
                final float winkel = calcWinkel(center, rotationCenter);
                final float rand = Randomizer.getRandomFloat(-Settings.getrandomRangeDegrees(), //
                        +Settings.getrandomRangeDegrees());
                return winkel + 90 + Settings.getFixedRotationDegrees() + rand + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Bottom": {
                rotationCenter.x = bWidth / 2f;
                rotationCenter.y = bHeight;
                final float winkel = calcWinkel(center, rotationCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
            case "Around Corners": {
                if (center.x < bWidth / 2) {
                    if (center.y < bHeight / 2) {
                        rotationCenter.x = 0;
                        rotationCenter.y = 0;
                    } else {
                        rotationCenter.x = 0;
                        rotationCenter.y = bHeight;
                    }
                } else {
                    if (center.y < bHeight / 2) {
                        rotationCenter.x = bWidth;
                        rotationCenter.y = 0;
                    } else {
                        rotationCenter.x = bWidth;
                        rotationCenter.y = bHeight;
                    }
                }
                final float winkel = calcWinkel(center, rotationCenter);
                return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees() + getIncrementingDegrees();
            }
        }
    }

    /**
     * @param point
     *            the point outside the center....
     * @param rotationCenter
     * @return
     */
    public static float calcWinkel(final PointF point, final PointF rotationCenter) {
        final float distTCenterX = rotationCenter.x - point.x;
        final float distTCenterY = rotationCenter.y - point.y;
        final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
        float winkel = (float) (alpha * 180 / Math.PI);
        if (point.x <= rotationCenter.x) {
            winkel = winkel + 180;
        }
        return winkel;
    }

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

    // /**
    // * Returns the Winkel (0-360) between the two points
    // *
    // * @param center
    // * @param location
    // * @return
    // */
    // public static float getDegreesToCenter(final PointF rotationCenter, final PointF location) {
    // final float distTCenterX = rotationCenter.x - location.x;
    // final float distTCenterY = rotationCenter.y - location.y;
    // final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
    // float winkel = (float) (alpha * 180 / Math.PI);
    // // Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
    // if (location.x <= rotationCenter.x) {
    // winkel = winkel + 180;
    // }
    // return winkel;
    // }
}
