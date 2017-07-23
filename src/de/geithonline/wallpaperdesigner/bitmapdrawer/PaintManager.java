
package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import de.geithonline.wallpaperdesigner.settings.ColorRandOptions;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.Settings.DROP_SHADOW_TYPE;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;
import de.geithonline.wallpaperdesigner.utils.RandomizerColor;

public class PaintManager {

	private final int bWidth;
	private final int bHeight;
	public final Paint paint;
	private int initialRandomizedColor;
	private int initialColor;

	public int getInitialColor() {
		return initialColor;
	}

	public int getInitialAlpha() {
		return initialAlpha;
	}

	private int initialAlpha;

	public int getInitialRandomizedColor() {
		return initialRandomizedColor;
	}

	public int getCurrentColor() {
		return paint.getColor();
	}

	public PaintManager(final int bWidth, final int bHeight) {
		this.bWidth = bWidth;
		this.bHeight = bHeight;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
	}

	void initPaintForPattern(final int pcolor) {
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		initialColor = pcolor;
		initialAlpha = Randomizer.getRandomInt(Settings.getMinOpacity(), Settings.getMaxOpacity());
		randomizeColorAccordingToSettings(pcolor);
		initialRandomizedColor = paint.getColor();
	}

	public void randomizeColorAccordingToSettings(int pcolor) {
		final ColorRandOptions colorOptions = Settings.getColorRandomizingOptions();
		if (colorOptions.isRandomizeColor()) {
			pcolor = RandomizerColor.randomizeColor(pcolor, colorOptions);
		}
		if (colorOptions.isRandomizeBrightness()) {
			final int adjust = Randomizer.getRandomInt(colorOptions.minBrightnessRange, colorOptions.maxBrightnessRange);
			pcolor = ColorHelper.adjustColorBrightness(pcolor, adjust);
		}
		if (colorOptions.isRandomizeSaturation()) {
			final float dSaturation = Randomizer.getRandomFloat(colorOptions.minSaturationRange, colorOptions.maxSaturationRange) / 100;
			pcolor = ColorHelper.adjustHSV(pcolor, 0, dSaturation, 0);
		}
		paint.setColor(pcolor);
		paint.setAlpha(initialAlpha);
	}

	public void setColorAndAlpha(final int pcolor, final int alpha) {
		paint.setColor(pcolor);
		if (alpha < 0) {
			paint.setAlpha(getInitialAlpha());
		} else {
			paint.setAlpha(alpha);
		}
	}

	public void setColor(final int pcolor) {
		setColorAndAlpha(pcolor, -1);
	}

	public void initFillPaint() {
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
	}

	public void initStrokePaint() {
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
	}

	public void setupDropShadowForPattern(final int color, final int oppositeColor, final int randomColor) {
		setupDropShadowForPattern(color, oppositeColor, randomColor, Settings.getDropShadowType());
	}

	public void setupDropShadowForPatternDark(final int color) {
		setupDropShadowForPattern(color, color, color, DROP_SHADOW_TYPE.DARKER);
	}

	public void setupDropShadowForPattern(final int color, final int oppositeColor, final int randomColor, final DROP_SHADOW_TYPE dropShadowType) {
		final int dropShadowRadius = getDropShadowRadius();
		final int dX = Settings.getDropShadowOffsetX();
		final int dY = Settings.getDropShadowOffsetY();
		switch (dropShadowType) {
		default:
		case NO:
			paint.setShadowLayer(0, 0, 0, Color.BLACK);
			break;
		case RANDOM:
			paint.setShadowLayer(dropShadowRadius, dX, dY, randomColor);
			break;
		case OPPOSITE:
			paint.setShadowLayer(dropShadowRadius, dX, dY, oppositeColor);
			break;
		case DARKER:
			paint.setShadowLayer(dropShadowRadius, dX, dY, ColorHelper.changeBrightness(color, Settings.getDropShadowDarkness()));
			break;
		case SELECT:
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
