package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import de.geithonline.wallpaperdesigner.settings.Settings;

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
			case "4 Color Sweep Gradient":
				drawLinearGradientBackground(canvas);
				break;

			case "4-Color Gradient from corners":
				draw4ColorCornerGradientBackground(canvas);
				break;
			case "4-Colors in corners":
				draw4ColorBackground(canvas);
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
		final int dark = 32;
		paint.setColor(Color.rgb(dark, dark, dark));
		canvas.drawRect(r, paint);

		// drawWhiteRoundReflection(canvas, cWidth, cHeight, r);
		// drawSunBeams(canvas, cWidth, cHeight, r);
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
			paint.setShader(new LinearGradient(0, 0, 0, height, getColorsReverse(), getDistances(),
					Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient left-right":
			paint.setShader(new LinearGradient(0, 0, width, 0, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient right-left":
			paint.setShader(new LinearGradient(0, 0, width, 0, getColorsReverse(), getDistances(),
					Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient topleft-bottomright":
			paint.setShader(new LinearGradient(0, 0, width, height, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient bottomright-topleft":
			paint.setShader(new LinearGradient(0, 0, width, height, getColorsReverse(), getDistances(),
					Shader.TileMode.MIRROR));
			break;

		case "Linear Gradient topright-bottomleft":
			paint.setShader(new LinearGradient(width, 0, 0, height, getColors(), getDistances(), Shader.TileMode.MIRROR));
			break;
		case "Linear Gradient bottomleft-topright":
			paint.setShader(new LinearGradient(width, 0, 0, height, getColorsReverse(), getDistances(),
					Shader.TileMode.MIRROR));
			break;
		case "Radial Gradient":
			paint.setShader(new RadialGradient(width / 2, height / 2, radius, getColors(), getDistances(),
					Shader.TileMode.MIRROR));
			break;
		case "Radial Gradient (Half Arch)":
			radius = (int) Math.sqrt(width / 2 * width / 2 + height * height);
			paint.setShader(new RadialGradient(width / 2, height, radius, getColors(), getDistances(),
					Shader.TileMode.MIRROR));
			break;
		case "4 Color Sweep Gradient":
			final int colorsSweep[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2(),
					Settings.getBackgroundColor3(), Settings.getBackgroundColor4(), Settings.getBackgroundColor1() };
			final float distancesSweep[] = { 0.0f, 0.25f, 0.5f, 0.75f, 1f };
			paint.setShader(new SweepGradient(width / 2, height / 2, colorsSweep, distancesSweep));
			break;
		case "4 Color Sweep Gradient (Half Arch)":
			final int colorsSweep2[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2(),
					Settings.getBackgroundColor3(), Settings.getBackgroundColor4() };
			final float distancesSweep2[] = { 0.5f, 0.66f, 0.82f, 1.0f };
			paint.setShader(new SweepGradient(width / 2, height, colorsSweep2, distancesSweep2));
			break;
		}
		return paint;
	}

	private static int[] getColors() {
		final int colors2[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2() };
		final int colors3[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2(),
				Settings.getBackgroundColor3() };
		final int colors4[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2(),
				Settings.getBackgroundColor3(), Settings.getBackgroundColor4() };
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
		final int colors2[] = { Settings.getBackgroundColor2(), Settings.getBackgroundColor1() };
		final int colors3[] = { Settings.getBackgroundColor3(), Settings.getBackgroundColor2(),
				Settings.getBackgroundColor1() };
		final int colors4[] = { Settings.getBackgroundColor4(), Settings.getBackgroundColor3(),
				Settings.getBackgroundColor2(), Settings.getBackgroundColor1() };
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

		// drawWhiteRoundReflection(canvas, cWidth, cHeight, r);
		// drawSunBeams(canvas, cWidth, cHeight, r);
	}

	private static void drawWhiteRoundReflection(final Canvas canvas, final int cWidth, final int cHeight, final RectF r) {
		final Paint reflectionsPaint = new Paint();
		reflectionsPaint.setAntiAlias(true);
		final int whitehalftransparent = 0x88FFFFFF;
		final int transparent = 0x00FFFFFF;
		final int colors[] = { whitehalftransparent, transparent, transparent, whitehalftransparent };
		final float distances[] = { 0.0f, 0.3f, 0.95f, 1.0f };
		reflectionsPaint.setShader(new RadialGradient(-cWidth / 2, cHeight, cWidth / 7, colors, distances,
				Shader.TileMode.REPEAT));
		// reflectionsPaint.setShader(new LinearGradient(-cWidth / 10, 0, cWidth / 10, 0, colors, distances,
		// Shader.TileMode.MIRROR));
		canvas.drawRect(r, reflectionsPaint);
	}

	private static void drawSunBeams(final Canvas canvas, final int cWidth, final int cHeight, final RectF r) {
		final int whitehalftransparent = 0x88FFFFFF;
		final int transparent = 0x44FFFFFF;
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(cWidth / 25);
		final int colors[] = { whitehalftransparent, transparent };
		final float distances[] = { 0.0f, 1.0f };
		paint.setShader(new LinearGradient(0, 0, 0, cHeight, colors, distances, Shader.TileMode.MIRROR));

		final int breiteOben = cWidth * 5 / 10;
		final int breiteUnten = cWidth * 9 / 10;
		final int rays = 8;

		for (int i = 0; i <= rays; i++) {
			final int xOben = (cWidth / 2 - breiteOben / 2) + i * breiteOben / rays;
			final int xUnten = (cWidth / 2 - breiteUnten / 2) + i * breiteUnten / rays;
			canvas.drawLine(xOben, 0, xUnten, cHeight, paint);
		}
	}

	public static void draw4ColorBackground(final Canvas canvas) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final int c1 = Settings.getBackgroundColor1();
		final int c2 = Settings.getBackgroundColor2();
		final int c3 = Settings.getBackgroundColor3();
		final int c4 = Settings.getBackgroundColor4();
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
		paint.setShader(new SweepGradient(-50, -50, getColors(), getDistancesCornerSweep()));

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
		final int c1 = Settings.getBackgroundColor1();
		final int c2 = Settings.getBackgroundColor2();
		final int c3 = Settings.getBackgroundColor3();
		final int c4 = Settings.getBackgroundColor4();
		final int levels = 100;
		final float sqW = (float) cWidth / (float) levels;
		final float sqH = (float) cHeight / (float) levels;
		for (int x = 0; x < levels; x++) {
			final int colXOben = getRadiantColor(c1, c2, x, 0, levels - 1);
			final int colXUnten = getRadiantColor(c4, c3, x, 0, levels - 1);
			for (int y = 0; y < levels; y++) {
				final int col = getRadiantColor(colXOben, colXUnten, y, 0, levels - 1);
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

	public static int getRadiantColor(final int col1, final int col2, final int level, final int min, final int max) {
		final int diff = min - max;
		final int diffpercent = min - level;
		final float factor = Math.abs((float) diffpercent / (float) diff);

		final int a = Color.alpha(col1);
		final int diffr = Color.red(col1) - Color.red(col2);
		final int diffg = Color.green(col1) - Color.green(col2);
		final int diffb = Color.blue(col1) - Color.blue(col2);

		final int r = Math.round(Color.red(col1) - diffr * factor);
		final int g = Math.round(Color.green(col1) - diffg * factor);
		final int b = Math.round(Color.blue(col1) - diffb * factor);

		return Color.argb(a, r, g, b);
	}

}
