package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.Log;
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
					draw4ColorTornado(canvas, 2);
					break;
				case "4-Color Tornado +":
					draw4ColorTornado(canvas, 3);
					break;
				case "4-Color Tornado ++":
					draw4ColorTornado(canvas, 4);
					break;
				case "4-Color Tornado +++":
					draw4ColorTornado(canvas, 5);
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

		// drawWhiteRoundReflection(canvas, cWidth, cHeight, r);
		// drawSunBeams(canvas, cWidth, cHeight, r);
	}

	// private static void drawWhiteRoundReflection(final Canvas canvas, final int cWidth, final int cHeight, final
	// RectF r) {
	// final Paint reflectionsPaint = new Paint();
	// reflectionsPaint.setAntiAlias(true);
	// final int whitehalftransparent = 0x88FFFFFF;
	// final int transparent = 0x00FFFFFF;
	// final int colors[] = { whitehalftransparent, transparent, transparent, whitehalftransparent };
	// final float distances[] = { 0.0f, 0.3f, 0.95f, 1.0f };
	// reflectionsPaint.setShader(new RadialGradient(-cWidth / 2, cHeight, cWidth / 7, colors, distances,
	// Shader.TileMode.REPEAT));
	// // reflectionsPaint.setShader(new LinearGradient(-cWidth / 10, 0, cWidth / 10, 0, colors, distances,
	// // Shader.TileMode.MIRROR));
	// canvas.drawRect(r, reflectionsPaint);
	// }
	//
	// private static void drawSunBeams(final Canvas canvas, final int cWidth, final int cHeight, final RectF r) {
	// final int whitehalftransparent = 0x88FFFFFF;
	// final int transparent = 0x44FFFFFF;
	// final Paint paint = new Paint();
	// paint.setAntiAlias(true);
	// paint.setStrokeWidth(cWidth / 25);
	// final int colors[] = { whitehalftransparent, transparent };
	// final float distances[] = { 0.0f, 1.0f };
	// paint.setShader(new LinearGradient(0, 0, 0, cHeight, colors, distances, Shader.TileMode.MIRROR));
	//
	// final int breiteOben = cWidth * 5 / 10;
	// final int breiteUnten = cWidth * 9 / 10;
	// final int rays = 8;
	//
	// for (int i = 0; i <= rays; i++) {
	// final int xOben = (cWidth / 2 - breiteOben / 2) + i * breiteOben / rays;
	// final int xUnten = (cWidth / 2 - breiteUnten / 2) + i * breiteUnten / rays;
	// canvas.drawLine(xOben, 0, xUnten, cHeight, paint);
	// }
	// }

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

	/**
	 * @param canvas
	 * @param off
	 *            staerke des Tornados 1-4 sinnnvoll
	 */
	private static void draw4ColorTornado(final Canvas canvas, final int off) {
		buildTornadoColors();
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final int abstand = cWidth / 30;
		final int maximumRadius = (int) Math.sqrt(cWidth * cWidth / 4 + cHeight * cHeight / 4) + abstand;
		final int radiusStep = Math.round(abstand);
		final int anzRinge = maximumRadius / radiusStep;
		final Point center = new Point(cWidth / 2, cHeight / 2);

		int index = 0;
		for (int ring = 0; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = tornadoColors.length + off;// (int) (Math.PI * 2 * r) / radiusStep + Math.max(anzRinge -
															// ring *
															// ring, 0);
			final float winkelProEcke = (float) (Math.PI * 2 / (ecken));
			for (int ecke = 0; ecke < ecken; ecke++) {
				final float rp = r + radiusStep * ecke / ecken;
				final Point p = new Point();
				p.x = (int) (center.x + Math.cos(ecke * winkelProEcke) * rp);
				p.y = (int) (center.y + Math.sin(ecke * winkelProEcke) * rp);
				final Paint paint = new Paint();
				final int color = tornadoColors[index % tornadoColors.length];
				index++;
				paint.setColor(color);
				canvas.drawCircle(p.x, p.y, abstand * 1.5f, paint);
			}
		}
	}

	private static int getColor(final int number) {
		switch (number) {
			default:
			case 1:
				return Settings.getPatternColor1();
			case 2:
				return Settings.getPatternColor2();
			case 3:
				return Settings.getPatternColor3();
			case 4:
				return Settings.getPatternColor4();
			case 5:
				return Settings.getPatternColor1();
		}
	}

	private static int tornadoColors[];

	private static void buildTornadoColors() {
		final int steps = 10;
		tornadoColors = new int[steps * 4];
		int index = 0;
		for (int color = 1; color < 5; color++) {
			for (int i = 0; i < steps; i++) {

				final int col = getRadiantColor(getColor(color), getColor(color + 1), i, 0, steps - 1);
				tornadoColors[index] = col;
				index++;
			}
		}
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

	public static void drawMandelBrot(final Canvas canvas) {
		final int width = canvas.getWidth();
		final int height = canvas.getHeight();

		final int max = 100;
		for (int row = 0; row < height; row = row + 4) {
			Log.i("Factal", "row=" + row);
			for (int col = 0; col < width; col = col + 4) {
				final double c_re = (col - width / 2.0) * 4.0 / width;
				final double c_im = (row - height / 2.0) * 4.0 / width;
				double x = 0, y = 0;
				int iteration = 0;
				while (x * x + y * y <= 4 && iteration < max) {
					final double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iteration++;
				}

				// final Rect r = new Rect(); // x * sqW, y * sqH, x * sqW + sqW, y
				// // * sqH + sqH);
				// r.left = Math.round(col);
				// r.top = Math.round(row);
				// r.right = Math.round(col+1);
				// r.bottom = Math.round(row+1);
				// canvas.drawRect(r, paint);

				final Paint paint = new Paint();
				if (iteration < max) {
					paint.setColor(Color.WHITE);
				} else {
					paint.setColor(Color.BLACK);
				}
				canvas.drawRect(col, row, col + 4, row + 4, paint);// Point(col, row, paint);

			}
		}

	}

}
