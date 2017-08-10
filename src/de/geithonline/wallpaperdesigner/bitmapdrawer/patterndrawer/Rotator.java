
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class Rotator {

	private final int bWidth;
	private final int bHeight;

	public Rotator(final int bWidth, final int bHeight) {
		this.bWidth = bWidth;
		this.bHeight = bHeight;
	}

	public float getRotationDegrees(final int randomMin, final int randomMax, final Point center) {
		switch (Settings.getRotationStyle()) {
		default:
		case "Fixed":
			return Settings.getFixedRotationDegrees() + getRandom90Degrees();
		case "Random":
			return Randomizer.getRandomFloat(randomMin - 1, randomMax);
		case "Random (Range)":
			return Randomizer.getRandomFloat(Settings.getFixedRotationDegrees() - Settings.getrandomRangeDegrees(), //
					Settings.getFixedRotationDegrees() + Settings.getrandomRangeDegrees()) + getRandom90Degrees();

		case "Around Adjustable Point":
		case "Around Adjustable Center": {
			final float distTCenterX = bWidth * Settings.getRotationCenterPointX() - center.x;
			final float distTCenterY = bHeight * Settings.getRotationCenterPointY() - center.y;
			final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
			float winkel = (float) (alpha * 180 / Math.PI);
			// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
			if (center.x <= bWidth * Settings.getRotationCenterPointX()) {
				winkel = winkel + 180;
			}
			return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees();
		}
		case "Around Center":
		case "Around Point": {
			final float distTCenterX = bWidth / 2 - center.x;
			final float distTCenterY = bHeight / 2 - center.y;
			final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
			float winkel = (float) (alpha * 180 / Math.PI);
			// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
			if (center.x <= bWidth / 2) {
				winkel = winkel + 180;
			}
			return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees();
		}
		case "Around Point (Range)": {
			final float distTCenterX = bWidth / 2 - center.x;
			final float distTCenterY = bHeight / 2 - center.y;
			final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
			float winkel = (float) (alpha * 180 / Math.PI);
			// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
			if (center.x <= bWidth / 2) {
				winkel = winkel + 180;
			}
			final float rand = Randomizer.getRandomFloat(-Settings.getrandomRangeDegrees(), //
					+Settings.getrandomRangeDegrees());
			return winkel + 90 + Settings.getFixedRotationDegrees() + rand + getRandom90Degrees();
		}
		case "Around Bottom": {
			final float distTCenterX = bWidth / 2 - center.x;
			final float distTCenterY = bHeight - center.y;
			final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
			float winkel = (float) (alpha * 180 / Math.PI);
			// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
			if (center.x <= bWidth / 2) {
				winkel = winkel + 180;
			}
			return winkel + 90 + Settings.getFixedRotationDegrees() + getRandom90Degrees();
		}
		}
	}

	public int getRandom90Degrees() {
		if (Settings.isRandomDegreesAdding()) {
			if (Randomizer.getRandomBoolean()) {
				return Settings.getRandomDegreesAddingAmount();
			}
		}
		return 0;
	}

	/**
	 * Returns the Winkel (0-360) between the two points
	 *
	 * @param center
	 * @param location
	 * @return
	 */
	public static float getDegreesToCenter(final PointF center, final PointF location) {
		final float distTCenterX = center.x - location.x;
		final float distTCenterY = center.y - location.y;
		final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
		float winkel = (float) (alpha * 180 / Math.PI);
		// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
		if (location.x <= center.x) {
			winkel = winkel + 180;
		}
		return winkel;
	}

}
