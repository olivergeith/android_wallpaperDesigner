package de.geithonline.wallpaperdesigner.bitmapdrawer.backgrounddrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;

public class BackgroundDrawer {

	public static void drawBackground(final Canvas canvas, final boolean sameAsPatternGradient) {

		if (sameAsPatternGradient) {
			switch (Settings.getGradientDirection()) {
			default:
				drawGradientBackground(canvas);
				break;

			case "4-Color Gradient from corners":
				BackgroundDrawer4ColorBackground.draw4ColorCornerGradientBackground(canvas, Settings.getCornerGradientLevels());
				break;
			case "4-Colors in corners":
				BackgroundDrawer4ColorBackground.draw4ColorBackground(canvas, Settings.getCornerRepeats());
				break;
			case "4-Color Tornado": {
				final PointF center = new PointF(canvas.getWidth() * Settings.getTornadoCenterPointX(), canvas.getHeight() * Settings.getTornadoCenterPointY());
				BackgroundDrawerTornado.draw4ColorTornado(canvas, Settings.getTornadoRings(), Settings.getTornadoArms(), center);
				break;
			}
			case "Custom Image":
				BackgroundDrawerCustomImage.draw(canvas);
				break;
			}
		} else {
			drawSimpleBackground(canvas);
		}

	}

	private static void drawGradientBackground(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final RectF r = new RectF(0, 0, cWidth, cHeight);
		canvas.drawRect(r, getBackgroundPaint(cWidth, cHeight));

	}

	public static void drawSimpleBackground(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final RectF r = new RectF(0, 0, cWidth, cHeight);
		final Paint paint = new Paint();
		final int colors[] = { Settings.getBackGrndColor1(), Settings.getBackGrndColor2() };
		final float distances[] = { 0.0f, 1.0f };
		paint.setShader(new LinearGradient(0, 0, 0, cHeight, colors, distances, Shader.TileMode.MIRROR));
		canvas.drawRect(r, paint);
	}

	private static Paint getBackgroundPaint(final int width, final int height) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		int radius = (int) (Math.sqrt(width * width + height * height) / 2);

		switch (Settings.getGradientDirection()) {
		default:
		case "Linear Gradient bottom-top": // deprecated
			initSettingsForDeprecatedGradients(1, true);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Linear Gradient top-bottom").commit();
		case "Linear Gradient top-bottom":
			paint.setShader(new LinearGradient(0, 0, 0, height, GradientColorManager.getColors(), null, Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient right-left": // deprecated
			initSettingsForDeprecatedGradients(1, true);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Linear Gradient left-right").commit();
		case "Linear Gradient left-right":
			paint.setShader(new LinearGradient(0, 0, width, 0, GradientColorManager.getColors(), null, Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient bottomright-topleft": // deprecated
			initSettingsForDeprecatedGradients(1, true);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Linear Gradient topleft-bottomright").commit();
		case "Linear Gradient topleft-bottomright":
			paint.setShader(new LinearGradient(0, 0, width, height, GradientColorManager.getColors(), null, Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient bottomleft-topright": // deprecated
			initSettingsForDeprecatedGradients(1, true);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Linear Gradient topright-bottomleft").commit();
		case "Linear Gradient topright-bottomleft":
			paint.setShader(new LinearGradient(width, 0, 0, height, GradientColorManager.getColors(), null, Shader.TileMode.MIRROR));
			break;
		case "Radial Gradient":
			paint.setShader(new RadialGradient(width / 2, height / 2, radius, GradientColorManager.getColors(), null, Shader.TileMode.MIRROR));
			break;
		case "Radial Gradient (Half Arch)":
			radius = (int) Math.sqrt(width / 2 * width / 2 + height * height);
			paint.setShader(new RadialGradient(width / 2, height, radius, GradientColorManager.getColors(), null, Shader.TileMode.MIRROR));
			break;

		case "4 Color Sweep Gradient": // deprecated
			initSettingsForDeprecatedGradients(1, false);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (++)").commit();
			initSweepPaint(width, height, paint);
			break;
		case "4 Color Sweep Gradient (2x)": // deprecated
			initSettingsForDeprecatedGradients(2, false);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (++)").commit();
			initSweepPaint(width, height, paint);
			break;
		case "4 Color Sweep Gradient (3x)": // deprecated
			initSettingsForDeprecatedGradients(3, false);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (++)").commit();
			initSweepPaint(width, height, paint);
			break;
		case "Sweep Gradient (++)":
			initSweepPaint(width, height, paint);
			break;

		case "4 Color Sweep Gradient (Half Arch)": // deprecated
			initSettingsForDeprecatedGradients(1, false);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (Half Arch)").commit();
		case "Sweep Gradient (Half Arch)":
			initSweepPaintHalfArc(width, height, paint);
			break;

		case "Sweep Gradient from corner": // deprecated
			initSettingsForDeprecatedGradients(1, false);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (from corner)").commit();
		case "Sweep Gradient (from corner)":
			initSweepGradientPaintFromCorner(width, height, paint);
			break;

		case "4 Color Sweep Gradient (Half Arch)(Mirror)":// deprecated
			initSettingsForDeprecatedGradients(1, false);
			Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (Half Arch - Mirrored)").commit();
		case "Sweep Gradient (Half Arch - Mirrored)":
			initSweepPaintHalfArcMirrored(width, height, paint);
			break;
		}
		return paint;
	}

	private static void initSweepPaintHalfArc(final int width, final int height, final Paint paint) {
		final int colors[] = GradientColorManager.getColors();
		paint.setShader(new SweepGradient(width / 2, height, colors, GradientColorManager.getDistancesSweepArc(colors.length, 0.5f, 1.0f)));
	}

	private static void initSweepPaintHalfArcMirrored(final int width, final int height, final Paint paint) {
		final int colorsSweep2[] = GradientColorManager.getColorsMirrored();
		paint.setShader(new SweepGradient(width / 2, height, colorsSweep2, GradientColorManager.getDistancesSweepArc(colorsSweep2.length, 0.5f, 1.0f)));
	}

	private static void initSweepPaint(final int width, final int height, final Paint paint) {
		paint.setShader(new SweepGradient(width / 2, height / 2, GradientColorManager.getColorsSweep(), null));
	}

	private static void initSweepGradientPaintFromCorner(final int width, final int height, final Paint paint) {
		final int colors[] = GradientColorManager.getColors();
		paint.setShader(new SweepGradient(0, 0, colors, GradientColorManager.getDistancesSweepArc(colors.length, 0f, 0.25f)));
	}

	private static void initSettingsForDeprecatedGradients(final int repeats, final boolean reverse) {
		Settings.prefs.edit().putBoolean(Settings.KEY_REVERSE_COLORS, reverse).commit();
		Settings.prefs.edit().putInt(Settings.KEY_COLOR_REPEATS, repeats).commit();
	}

	// private static void draw4ColorCornerGradientBackgroundV2(final Canvas canvas) {
	// final int cWidth = canvas.getWidth();
	// final int cHeight = canvas.getHeight();
	// final int c1 = Settings.getPatternColor1();
	// final int c2 = Settings.getPatternColor2();
	// final int c3 = Settings.getPatternColor3();
	// final int c4 = Settings.getPatternColor4();
	// final Paint paint = new Paint();
	// paint.setAntiAlias(true);
	// paint.setColor(c1);
	// final Rect r = new Rect();
	// r.left = 0;
	// r.top = 0;
	// r.right = cWidth;
	// r.bottom = cHeight;
	// final int radius = (int) (Math.sqrt(cWidth * cWidth + cHeight * cHeight));
	// // if (cWidth < cHeight) {
	// // radius = cHeight;
	// // } else {
	// // radius = cWidth;
	// // }
	//
	// final int offset = cWidth / 15;
	// setSpotlightShader(offset, offset, c1, paint, radius);
	// canvas.drawRect(r, paint);
	// setSpotlightShader(cWidth - offset, offset, c2, paint, radius);
	// canvas.drawRect(r, paint);
	// setSpotlightShader(cWidth - offset, cHeight - offset, c3, paint, radius);
	// canvas.drawRect(r, paint);
	// setSpotlightShader(offset, cHeight - offset, c4, paint, radius);
	// canvas.drawRect(r, paint);
	// }
	//
	// private static void setSpotlightShader(final int x, final int y, final int color, final Paint paint, final int radius) {
	// final int transparent = Color.argb(0, Color.red(color), Color.green(color), Color.blue(color));
	// paint.setShader(new RadialGradient(x, y, radius, color, transparent, TileMode.CLAMP));
	//
	// }

	public static Bitmap blurrIfNessesary(Bitmap bitmap) {
		if (Settings.getGradientDirection().startsWith("4-Color Tornado")) {
			bitmap = BitmapBlurrer.doBlur(bitmap, 40, true);
			// refbitmap = BitmapBlurrer.doBlur(refbitmap, 40, true);
		}
		return bitmap;
	}

}
