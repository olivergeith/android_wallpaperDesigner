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

	public static void drawBackground(final Canvas canvas) {

		switch (Settings.getGradientDirection()) {
			default:
			case "Linear Gradient top-bottom":
			case "Linear Gradient left-right":
			case "Linear Gradient topleft-bottomright":
			case "Linear Gradient topright-bottomleft":
			case "2 Color Radial Gradient":
			case "4 Color Radial Gradient":
			case "4 Color Sweep Gradient":
				drawLinearGradientBackground(canvas);
				break;
			case "4-Color Gradient from corners":
				draw4ColorBackground(canvas);
				break;
		}

	}

	private static Paint getBackgroundPaint(final int width, final int height) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		final int radius = (int) Math.sqrt(width * width + height * height) / 2;

		switch (Settings.getGradientDirection()) {
			default:
			case "Linear Gradient top-bottom":
				paint.setShader(new LinearGradient(0, 0, 0, height, Settings.getBackgroundColor1(), Settings.getBackgroundColor2(), Shader.TileMode.MIRROR));
				break;
			case "Linear Gradient left-right":
				paint.setShader(new LinearGradient(0, 0, width, 0, Settings.getBackgroundColor1(), Settings.getBackgroundColor2(), Shader.TileMode.MIRROR));
				break;
			case "Linear Gradient topleft-bottomright":
				paint.setShader(new LinearGradient(0, 0, width, height, Settings.getBackgroundColor1(), Settings.getBackgroundColor2(), Shader.TileMode.MIRROR));
				break;
			case "Linear Gradient topright-bottomleft":
				paint.setShader(new LinearGradient(width, 0, 0, height, Settings.getBackgroundColor1(), Settings.getBackgroundColor2(), Shader.TileMode.MIRROR));
				break;
			case "2 Color Radial Gradient":
				paint.setShader(new RadialGradient(width / 2, height / 2, height / 2, Settings.getBackgroundColor1(), Settings.getBackgroundColor2(),
						Shader.TileMode.MIRROR));
				break;
			case "4 Color Radial Gradient":
				final int colors[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2(), Settings.getBackgroundColor3(),
						Settings.getBackgroundColor4() };
				final float distances[] = { 0.0f, 0.3f, 0.6f, 0.9f };
				paint.setShader(new RadialGradient(width / 2, height / 2, radius, colors, distances, Shader.TileMode.MIRROR));
				break;
			case "4 Color Sweep Gradient":
				final int colorsSweep[] = { Settings.getBackgroundColor1(), Settings.getBackgroundColor2(), Settings.getBackgroundColor3(),
						Settings.getBackgroundColor4(), Settings.getBackgroundColor1() };
				final float distancesSweep[] = { 0.0f, 0.25f, 0.5f, 0.75f, 1f };
				paint.setShader(new SweepGradient(width / 2, height / 2, colorsSweep, distancesSweep));
				break;
		}
		return paint;
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
			final int colXUnten = getRadiantColor(c3, c4, x, 0, levels - 1);
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
