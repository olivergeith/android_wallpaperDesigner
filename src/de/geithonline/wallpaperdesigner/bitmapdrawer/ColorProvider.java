package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import de.geithonline.android.basics.utils.ColorFilterGenerator;
import de.geithonline.android.basics.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class ColorProvider {
	// #####################################################################################
	// LevelColors
	// #####################################################################################
	public int getColorForLevel(final int level) {
		if (Settings.isCharging && Settings.isUseChargeColors()) {
			return Settings.getChargeColor();
		}
		if (level > Settings.getMidThreshold()) {
			if (Settings.isGradientColors()) {
				return getGradientColorForLevel(level);
			} else {
				return Settings.getBattColor();
			}
		} else {
			if (level < Settings.getLowThreshold()) {
				return Settings.getBattColorLow();
			} else {
				if (Settings.isGradientColorsMid()) {
					return getGradientColorForLevel(level);
				} else {
					return Settings.getBattColorMid();
				}
			}
		}
	}

	public int getGradientColorForLevel(final int level) {

		if (level > Settings.getMidThreshold()) {
			return ColorHelper.getRadiantColor(Settings.getBattColor(), Settings.getBattColorMid(), level, 100, Settings.getMidThreshold());
		} else {
			if (level < Settings.getLowThreshold()) {
				return Settings.getBattColorLow();
			} else {
				return ColorHelper.getRadiantColor(Settings.getBattColorLow(), Settings.getBattColorMid(), level, Settings.getLowThreshold(),
						Settings.getMidThreshold());
			}
		}
	}

	// #####################################################################################
	// Different Paints
	// #####################################################################################
	public Paint getErasurePaint() {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.TRANSPARENT);
		final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.CLEAR);
		paint.setXfermode(xfermode);
		paint.setStyle(Style.FILL);
		return paint;
	}

	public Paint getNumberPaint(final int level, final int fontSize) {
		return getNumberPaint(level, fontSize, Align.CENTER, true, false);
	}

	public Paint getNumberPaint(final int level, final int fontSize, final Align align, final boolean bold, final boolean erase) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		if (Settings.isColoredNumber()) {
			paint.setColor(getColorForLevel(level));
		} else {
			paint.setColor(Settings.getNumberColor());
		}
		paint.setAlpha(255);
		final int fSize = adjustFontSize(level, fontSize);
		paint.setTextSize(fSize);
		paint.setFakeBoldText(true);
		if (bold) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextAlign(align);
		if (erase) {
			final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.CLEAR);
			paint.setXfermode(xfermode);
		}
		return paint;
	}

	public Paint getTextPaint(final int level, final int fontSize) {
		return getTextPaint(level, fontSize, Align.LEFT, true, false);
	}

	public Paint getTextPaint(final int level, final int fontSize, final Align align, final boolean bold, final boolean erase) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		if (Settings.isColoredNumber()) {
			paint.setColor(getColorForLevel(level));
		} else {
			paint.setColor(Settings.getBattColor());
		}
		paint.setAlpha(255);
		paint.setAntiAlias(true);
		paint.setTextSize(fontSize);
		paint.setFakeBoldText(true);
		if (bold) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextAlign(align);
		if (erase) {
			final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.CLEAR);
			paint.setXfermode(xfermode);
		}
		return paint;
	}

	public Paint getChargeStatusPaint(final int level, final int fontSize, final Align align, final boolean bold, final boolean erase, final boolean dropShadow) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Settings.getChargeStatusColor());
		paint.setAlpha(255);
		paint.setAntiAlias(true);
		paint.setTextSize(fontSize);
		paint.setFakeBoldText(true);
		if (bold) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextAlign(align);
		if (erase) {
			final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.CLEAR);
			paint.setXfermode(xfermode);
		}
		if (dropShadow) {
			paint.setShadowLayer(10, 0, 0, Color.BLACK);
		}

		return paint;
	}

	private int adjustFontSize(final int level, final int fontSize) {
		int fSize = fontSize;
		if (level == 100) {
			fSize = Math.round(fontSize * Settings.getFontSize100() / 100f);
		}
		// generelle fontadjust einbeziehen
		fSize = Math.round(fSize * Settings.getFontSize() / 100f);
		return fSize;
	}

	public Paint getBatteryPaint(final int level) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(getColorForLevel(level));
		paint.setAlpha(255);
		paint.setStyle(Style.FILL);
		return paint;
	}

	public Paint getZeigerPaint(final int level) {
		final Paint paint = getZeigerPaint(level, true);
		return paint;
	}

	public Paint getZeigerPaint(final int level, final boolean dropShadow) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Settings.getZeigerColor());
		paint.setAlpha(255);
		paint.setStyle(Style.FILL);
		if (dropShadow) {
			paint.setShadowLayer(10, 0, 0, Color.BLACK);
		}
		if (Settings.isZeigerSRCIN()) {
			final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.SRC_IN);
			paint.setXfermode(xfermode);
		}
		return paint;
	}

	public Paint getTextScalePaint(final int fontSize, final Align align, final boolean bold) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Settings.getScaleColor());
		paint.setAlpha(255);
		paint.setAntiAlias(true);
		paint.setTextSize(fontSize);
		paint.setFakeBoldText(true);
		if (bold) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextAlign(align);
		if (Settings.isScaleTransparent()) {
			final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.CLEAR);
			paint.setXfermode(xfermode);
		}
		return paint;
	}

	public Paint getTextBattStatusPaint(final int fontSize, final Align align, final boolean bold, final boolean dropShadow) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Settings.getBattStatusColor());
		paint.setAlpha(255);
		paint.setAntiAlias(true);
		paint.setTextSize(fontSize);
		paint.setFakeBoldText(true);
		if (bold) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		if (dropShadow) {
			paint.setShadowLayer(10, 0, 0, Color.BLACK);
		}

		paint.setTextAlign(align);
		return paint;
	}

	// ####################################################################
	// all for painting with bitmaps
	// ####################################################################
	protected Paint getGrayscalePaint() {
		final Paint bgPaint = new Paint();

		final int brightness = Math.round(Settings.getLogoBackgroundBrightness() * 200) - 200;
		bgPaint.setColorFilter(ColorFilterGenerator.adjustColor(brightness, 0, -100, 0));

		return bgPaint;
	}

	protected Paint getBitmapPaint(final int level, final Bitmap bitmap) {
		final Paint paint = new Paint();
		final BitmapShader fillBMPshader = new BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT);
		paint.setStyle(Paint.Style.FILL);
		paint.setShader(fillBMPshader);

		final int hue = Math.round(Settings.getLogoHue() * 360) - 180;
		if (hue != 0) {
			paint.setColorFilter(ColorFilterGenerator.adjustHue(hue));
		}

		if (Settings.isReColorBitmap()) {
			final int color = getColorForLevel(level);
			final PorterDuffColorFilter cf = new PorterDuffColorFilter(color, Mode.MULTIPLY);
			paint.setColorFilter(cf);
		}
		// if (dropShadow) {
		paint.setShadowLayer(10, 0, 0, Color.BLACK);
		// }
		return paint;
	}
}
