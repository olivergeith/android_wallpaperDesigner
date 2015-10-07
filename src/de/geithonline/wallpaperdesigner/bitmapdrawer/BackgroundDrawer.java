package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class BackgroundDrawer {

	public static void drawBackground(final Canvas canvas, final boolean sameAsPatternGradient) {

		if (sameAsPatternGradient) {
			switch (Settings.getGradientDirection()) {
				default:
				case "Linear Gradient bottom-top": // deprecated
				case "4 Color Sweep Gradient": // deprecated
				case "4 Color Sweep Gradient (2x)": // deprecated
				case "4 Color Sweep Gradient (3x)": // deprecated
				case "Linear Gradient bottomright-topleft": // deprecated
				case "Linear Gradient right-left": // deprecated
				case "Linear Gradient bottomleft-topright": // deprecated

				case "Linear Gradient top-bottom":
				case "Linear Gradient left-right":
				case "Linear Gradient topleft-bottomright":
				case "Linear Gradient topright-bottomleft":
				case "Radial Gradient":
				case "Radial Gradient (Half Arch)":
				case "4 Color Sweep Gradient (Half Arch)":
				case "4 Color Sweep Gradient (Half Arch)(Mirror)":
				case "Sweep Gradient (++)":
					drawGradientBackground(canvas);
					break;

				case "4-Color Gradient from corners":
					draw4ColorCornerGradientBackground(canvas);
					break;
				case "4-Colors in corners":
					draw4ColorBackground(canvas);
					break;
				case "4-Color Tornado":
					BackgroundDrawerTornado.draw4ColorTornado(canvas, Settings.getTornadoRings(), Settings.getTornadoArms());
					break;
				case "Sweep Gradient from corner":
					drawSweepGradientFromCorner(canvas);
					break;
			}
		} else {
			drawSimpleBackground(canvas);
		}

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
			case "Linear Gradient top-bottom":
				paint.setShader(new LinearGradient(0, 0, 0, height, BackgroundDrawerColorManager.getColors(), null, Shader.TileMode.MIRROR));
				break;

			case "Linear Gradient right-left": // deprecated
				initSettingsForDeprecatedGradients(1, true);
			case "Linear Gradient left-right":
				paint.setShader(new LinearGradient(0, 0, width, 0, BackgroundDrawerColorManager.getColors(), null, Shader.TileMode.MIRROR));
				break;

			case "Linear Gradient bottomright-topleft": // deprecated
				initSettingsForDeprecatedGradients(1, true);
			case "Linear Gradient topleft-bottomright":
				paint.setShader(new LinearGradient(0, 0, width, height, BackgroundDrawerColorManager.getColors(), null, Shader.TileMode.MIRROR));
				break;

			case "Linear Gradient bottomleft-topright": // deprecated
				initSettingsForDeprecatedGradients(1, true);
			case "Linear Gradient topright-bottomleft":
				paint.setShader(new LinearGradient(width, 0, 0, height, BackgroundDrawerColorManager.getColors(), null, Shader.TileMode.MIRROR));
				break;
			case "Radial Gradient":
				paint.setShader(new RadialGradient(width / 2, height / 2, radius, BackgroundDrawerColorManager.getColors(), null, Shader.TileMode.MIRROR));
				break;
			case "Radial Gradient (Half Arch)":
				radius = (int) Math.sqrt(width / 2 * width / 2 + height * height);
				paint.setShader(new RadialGradient(width / 2, height, radius, BackgroundDrawerColorManager.getColors(), null, Shader.TileMode.MIRROR));
				break;

			case "4 Color Sweep Gradient": // deprecated
				initSettingsForDeprecatedGradients(1, false);
				initSettingsForDeprecatedSweepGradients();
				initSweepPaint(width, height, paint);
				break;
			case "4 Color Sweep Gradient (2x)": // deprecated
				initSettingsForDeprecatedGradients(2, false);
				initSettingsForDeprecatedSweepGradients();
				initSweepPaint(width, height, paint);
				break;
			case "4 Color Sweep Gradient (3x)": // deprecated
				initSettingsForDeprecatedGradients(3, false);
				initSettingsForDeprecatedSweepGradients();
				initSweepPaint(width, height, paint);
				break;
			case "Sweep Gradient (++)":
				initSweepPaint(width, height, paint);
				break;

			case "4 Color Sweep Gradient (Half Arch)": {
				final int colorsSweep2[] = { Settings.getPatternColor1(), Settings.getPatternColor2(), Settings.getPatternColor3(),
						Settings.getPatternColor4() };
				final float distancesSweep2[] = { 0.5f, 0.66f, 0.82f, 1.0f };
				paint.setShader(new SweepGradient(width / 2, height, colorsSweep2, distancesSweep2));
			}
				break;
			case "4 Color Sweep Gradient (Half Arch)(Mirror)": {
				final int colorsSweep2[] = { //
						Settings.getPatternColor1(), //
						Settings.getPatternColor2(), //
						Settings.getPatternColor3(), //
						Settings.getPatternColor4(), //
						Settings.getPatternColor3(), //
						Settings.getPatternColor2(), //
						Settings.getPatternColor1() //
				};
				final float distancesSweep2[] = { //
						0.5f, //
						0.5833f, //
						0.6666f, //
						0.75f, //
						0.8333f, //
						0.9156f, //
						1.0f };
				paint.setShader(new SweepGradient(width / 2, height, colorsSweep2, distancesSweep2));
			}
				break;
		}
		return paint;
	}

	private static void initSettingsForDeprecatedSweepGradients() {
		Settings.prefs.edit().putString(Settings.KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (++)").commit();
	}

	private static void initSettingsForDeprecatedGradients(final int repeats, final boolean reverse) {
		Settings.prefs.edit().putBoolean(Settings.KEY_REVERSE_COLORS, reverse).commit();
		Settings.prefs.edit().putInt(Settings.KEY_COLOR_REPEATS, repeats).commit();
	}

	private static void initSweepPaint(final int width, final int height, final Paint paint) {
		paint.setShader(new SweepGradient(width / 2, height / 2, BackgroundDrawerColorManager.getColorsSweep(), null));
	}

	private static float[] getDistancesCornerSweep() {
		final float distances2[] = { 0.0f, 0.25f };
		final float distances3[] = { 0.0f, 0.125f, 0.24f };
		final float distances4[] = { 0.0f, 0.08f, 0.16f, 0.24f };
		final int anzahl = Settings.getAnzahlGradientColors();
		switch (anzahl) {
			default:
			case 2:
				return distances2;
			case 3:
				return distances3;
			case 4:
				return distances4;
		}
	}

	public static void drawGradientBackground(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final RectF r = new RectF(0, 0, cWidth, cHeight);
		canvas.drawRect(r, getBackgroundPaint(cWidth, cHeight));

	}

	public static void draw4ColorBackground(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final int c1 = Settings.getPatternColor1();
		final int c2 = Settings.getPatternColor2();
		final int c3 = Settings.getPatternColor3();
		final int c4 = Settings.getPatternColor4();
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(c1);
		Rect r = new Rect(0, 0, cWidth / 2, cHeight / 2);
		canvas.drawRect(r, paint);

		paint.setColor(c2);
		r = new Rect(cWidth / 2, 0, cWidth, cHeight / 2);
		canvas.drawRect(r, paint);

		paint.setColor(c3);
		r = new Rect(cWidth / 2, cHeight / 2, cWidth, cHeight);
		canvas.drawRect(r, paint);

		paint.setColor(c4);
		r = new Rect(0, cHeight / 2, cWidth / 2, cHeight);
		canvas.drawRect(r, paint);

	}

	private static void drawSweepGradientFromCorner(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final Paint paint = new Paint();
		// paint.setAntiAlias(true);
		paint.setShader(new SweepGradient(0, 0, BackgroundDrawerColorManager.getColors(), getDistancesCornerSweep()));

		final Rect r = new Rect(0, 0, cWidth, cHeight);
		canvas.drawRect(r, paint);

	}

	public static void draw4ColorCornerGradientBackground(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		// final int c1 = Color.RED;
		// final int c2 = Color.GREEN;
		// final int c3 = Color.YELLOW;
		// final int c4 = Color.BLUE;
		final int c1 = Settings.getPatternColor1();
		final int c2 = Settings.getPatternColor2();
		final int c3 = Settings.getPatternColor3();
		final int c4 = Settings.getPatternColor4();
		final int levels = 100;
		final float sqW = (float) cWidth / (float) levels;
		final float sqH = (float) cHeight / (float) levels;
		for (int x = 0; x < levels; x++) {
			final int colXOben = ColorHelper.getRadiantColor(c1, c2, x, 0, levels - 1);
			final int colXUnten = ColorHelper.getRadiantColor(c4, c3, x, 0, levels - 1);
			for (int y = 0; y < levels; y++) {
				final int col = ColorHelper.getRadiantColor(colXOben, colXUnten, y, 0, levels - 1);
				final Paint paint = new Paint();
				paint.setAntiAlias(true);
				paint.setColor(col);
				final Rect r = new Rect(); // x * sqW, y * sqH, x * sqW + sqW, y
											// * sqH + sqH);
				r.left = Math.round(x * sqW);
				r.top = Math.round(y * sqH);
				r.right = Math.round((x + 1) * sqW);
				r.bottom = Math.round((y + 1) * sqH);
				canvas.drawRect(r, paint);
			}
		}

	}

	public static Bitmap blurrIfNessesary(Bitmap bitmap) {
		if (Settings.getGradientDirection().startsWith("4-Color Tornado")) {
			bitmap = BitmapBlurrer.doBlur(bitmap, 40, true);
			// refbitmap = BitmapBlurrer.doBlur(refbitmap, 40, true);
		}
		return bitmap;
	}

}
