
package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;
import de.geithonline.wallpaperdesigner.utils.RandomizerColor;

public class PaintManager {

	private final int bWidth;
	private final int bHeight;
	public final Paint paint;

	public PaintManager(final int bWidth, final int bHeight) {
		this.bWidth = bWidth;
		this.bHeight = bHeight;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
	}

	public void initPaintForPattern(int pcolor) {
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		if (Settings.isRandomizeBrightness()) {
			final int range = Settings.getRandomizeColorBrighnessRange();
			final int adjust = Randomizer.getRandomInt(-range, range);
			pcolor = ColorHelper.adjustColorBrightness(pcolor, adjust);
		}
		if (Settings.isRandomizeSaturation()) {
			final int range = Settings.getRandomizeSaturationRange();
			final float dSaturation = Randomizer.getRandomFloat(-range, range) / 100;
			pcolor = ColorHelper.adjustHSV(pcolor, 0, dSaturation, 0);
		}
		if (Settings.isRandomizeColors()) {
			pcolor = RandomizerColor.randomizeColor(pcolor, Settings.getRandomizeColorRange(), Settings.getColorRandomizingType());
		}

		paint.setColor(pcolor);
		paint.setAlpha(Randomizer.getRandomInt(Settings.getMinOpacity(), Settings.getMaxOpacity()));
	}

	public void setupDropShadowForPattern(final int color, final int oppositeColor, final int randomColor) {
		final int dropShadowRadius = getDropShadowRadius();
		final int dX = Settings.getDropShadowOffsetX();
		final int dY = Settings.getDropShadowOffsetY();
		switch (Settings.getDropShadowType()) {
		default:
		case "No":
			paint.setShadowLayer(0, 0, 0, Color.BLACK);
			break;
		case "Random":
			paint.setShadowLayer(dropShadowRadius, dX, dY, randomColor);
			break;
		case "Opposite":
			paint.setShadowLayer(dropShadowRadius, dX, dY, oppositeColor);
			break;
		case "Darker":
			paint.setShadowLayer(dropShadowRadius, dX, dY, ColorHelper.changeBrightness(color, Settings.getDropShadowDarkness()));
			break;
		case "Select":
			final int shd = Settings.getDropShadowColor();
			final int alpha = Color.alpha(color);
			final int dc = Color.argb(alpha, Color.red(shd), Color.green(shd), Color.blue(shd));
			paint.setShadowLayer(dropShadowRadius, dX, dY, dc);
			break;
		}
	}

	private int getDropShadowRadius() {
		int dropShadowRadius = Math.round(bWidth * 0.01f * Settings.getDropShadowRadiusAdjustment());
		if (dropShadowRadius < 5) {
			dropShadowRadius = 5;
		}
		return dropShadowRadius;
	}

	public int getbWidth() {
		return bWidth;
	}

	public int getbHeight() {
		return bHeight;
	}

	public Paint getPaint() {
		return paint;
	}

}