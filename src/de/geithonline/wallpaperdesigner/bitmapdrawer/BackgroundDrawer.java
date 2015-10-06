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
			case "Linear Gradient top-bottom":
			case "Linear Gradient bottom-top":

			case "Linear Gradient left-right":
			case "Linear Gradient right-left":

			case "Linear Gradient topleft-bottomright":
			case "Linear Gradient bottomright-topleft":

			case "Linear Gradient topright-bottomleft":
			case "Linear Gradient bottomleft-topright":

			case "Radial Gradient":
			case "Radial Gradient (Half Arch)":
			case "4 Color Sweep Gradient (Half Arch)":
			case "4 Color Sweep Gradient (Half Arch)(Mirror)":
			case "4 Color Sweep Gradient (2x)":
			case "4 Color Sweep Gradient (3x)":
			case "4 Color Sweep Gradient":
				drawLinearGradientBackground(canvas);
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

		int radius = (int) Math.sqrt(width * width + height * height) / 2;

		switch (Settings.getGradientDirection()) {
		default:
		case "Linear Gradient top-bottom":
			paint.setShader(new LinearGradient(0, 0, 0, height, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient bottom-top":
			paint.setShader(new LinearGradient(0, 0, 0, height, getColorsReverse(), getDistances(), Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient left-right":
			paint.setShader(new LinearGradient(0, 0, width, 0, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient right-left":
			paint.setShader(new LinearGradient(0, 0, width, 0, getColorsReverse(), getDistances(), Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient topleft-bottomright":
			paint.setShader(new LinearGradient(0, 0, width, height, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient bottomright-topleft":
			paint.setShader(new LinearGradient(0, 0, width, height, getColorsReverse(), getDistances(), Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient topright-bottomleft":
			paint.setShader(new LinearGradient(width, 0, 0, height, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient bottomleft-topright":
			paint.setShader(new LinearGradient(width, 0, 0, height, getColorsReverse(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Radial Gradient":
			paint.setShader(new RadialGradient(width / 2, height / 2, radius, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Radial Gradient (Half Arch)":
			radius = (int) Math.sqrt(width / 2 * width / 2 + height * height);
			paint.setShader(new RadialGradient(width / 2, height, radius, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "4 Color Sweep Gradient": {
			final int colorsSweep[] = { Settings.getPatternColor1(), Settings.getPatternColor2(), Settings.getPatternColor3(), Settings.getPatternColor4(),
					Settings.getPatternColor1() };
			final float distancesSweep[] = { 0.0f, 0.25f, 0.5f, 0.75f, 1f };
			paint.setShader(new SweepGradient(width / 2, height / 2, colorsSweep, distancesSweep));
		}
			break;

		case "4 Color Sweep Gradient (2x)": {
			final int colorsSweep[] = { //
					Settings.getPatternColor1(), //
					Settings.getPatternColor2(), //
					Settings.getPatternColor3(), //
					Settings.getPatternColor4(), //
					Settings.getPatternColor1(), //
					Settings.getPatternColor2(), //
					Settings.getPatternColor3(), //
					Settings.getPatternColor4(), //
					Settings.getPatternColor1() };
			final float distancesSweep[] = { 0.0f, //
					0.125f, //
					0.25f, //
					0.375f, //
					0.5f, //
					0.625f, //
					0.75f, //
					0.875f, //
					1f };
			paint.setShader(new SweepGradient(width / 2, height / 2, colorsSweep, distancesSweep));
		}
			break;

		case "4 Color Sweep Gradient (3x)": {
			final int colorsSweep[] = { //
					Settings.getPatternColor1(), //
					Settings.getPatternColor2(), //
					Settings.getPatternColor3(), //
					Settings.getPatternColor4(), //
					Settings.getPatternColor1(), //
					Settings.getPatternColor2(), //
					Settings.getPatternColor3(), //
					Settings.getPatternColor4(), //
					Settings.getPatternColor1(), //
					Settings.getPatternColor2(), //
					Settings.getPatternColor3(), //
					Settings.getPatternColor4(), //
					Settings.getPatternColor1() };
			final float distancesSweep[] = { 0.0f, //
					0.0833f, //
					0.1666f, //
					0.25f, //
					0.333f, //
					0.416f, //
					0.5f, //
					0.583f, //
					0.666f, //
					0.75f, //
					0.8333f, //
					0.916f, //
					1f };
			paint.setShader(new SweepGradient(width / 2, height / 2, colorsSweep, distancesSweep));
		}
			break;

		case "4 Color Sweep Gradient (Half Arch)": {
			final int colorsSweep2[] = { Settings.getPatternColor1(), Settings.getPatternColor2(), Settings.getPatternColor3(), Settings.getPatternColor4() };
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
			final float distancesSweep2[] = { 0.5f, //
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

	private static int[] getColors() {
		final int colors2[] = { Settings.getPatternColor1(), Settings.getPatternColor2() };
		final int colors3[] = { Settings.getPatternColor1(), Settings.getPatternColor2(), Settings.getPatternColor3() };
		final int colors4[] = { Settings.getPatternColor1(), Settings.getPatternColor2(), Settings.getPatternColor3(), Settings.getPatternColor4() };
		final int anzahl = Settings.getAnzahlGradientColors();
		switch (anzahl) {
		default:
		case 2:
			return colors2;
		case 3:
			return colors3;
		case 4:
			return colors4;
		}
	}

	private static int[] getColorsReverse() {
		final int colors2[] = { Settings.getPatternColor2(), Settings.getPatternColor1() };
		final int colors3[] = { Settings.getPatternColor3(), Settings.getPatternColor2(), Settings.getPatternColor1() };
		final int colors4[] = { Settings.getPatternColor4(), Settings.getPatternColor3(), Settings.getPatternColor2(), Settings.getPatternColor1() };
		final int anzahl = Settings.getAnzahlGradientColors();
		switch (anzahl) {
		default:
		case 2:
			return colors2;
		case 3:
			return colors3;
		case 4:
			return colors4;
		}
	}

	private static float[] getDistances() {
		final float distances2[] = { 0.0f, 1f };
		final float distances3[] = { 0.0f, 0.50f, 1f };
		final float distances4[] = { 0.05f, 0.30f, 0.60f, 0.95f };
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

	public static void drawLinearGradientBackground(final Canvas canvas) {
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
		paint.setShader(new SweepGradient(0, 0, getColors(), getDistancesCornerSweep()));

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
