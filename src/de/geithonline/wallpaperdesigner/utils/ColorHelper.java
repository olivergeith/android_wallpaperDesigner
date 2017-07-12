package de.geithonline.wallpaperdesigner.utils;

import android.graphics.Color;

public class ColorHelper {

	/**
	 * @param color
	 *            the color to change
	 * @param amount
	 *            the brigtnes offset (-255 to 255)
	 * @return
	 */
	public static int changeBrightness(final int color, final int amount) {
		final int a = Color.alpha(color);
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);

		r += amount;
		if (r > 255) {
			r = 255;
		}
		if (r < 0) {
			r = 0;
		}
		g += amount;
		if (g > 255) {
			g = 255;
		}
		if (g < 0) {
			g = 0;
		}
		b += amount;
		if (b > 255) {
			b = 255;
		}
		if (b < 0) {
			b = 0;
		}
		return Color.argb(a, r, g, b);
	}

	/**
	 * @param col1
	 *            Farbe 1
	 * @param col2
	 *            Farbe 2
	 * @param level
	 *            stufe zwischen den farben (0-10)
	 * @param min
	 *            minimale stufe (0)
	 * @param max
	 *            maximale stufe (10)
	 * @return
	 */
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

	/**
	 * @param color
	 * @param dHue
	 *            delta Hue
	 * @param dSatuarion
	 *            delta Saturation
	 * @param dBrightness
	 *            delta Brightness
	 * @return
	 */
	public static int setSaturation(final int color, final float sat) {
		final int a = Color.alpha(color);

		final float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		hsv[1] = validateSaturation(sat);
		return Color.HSVToColor(a, hsv);
	}

	/**
	 * @param color
	 * @param dHue
	 *            delta Hue
	 * @param dSatuarion
	 *            delta Saturation
	 * @param dBrightness
	 *            delta Brightness
	 * @return
	 */
	public static int adjustHSV(final int color, final float dHue, final float dSatuarion, final float dBrightness) {
		final int a = Color.alpha(color);

		final float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		float hue = hsv[0]; // 0-360
		float sat = hsv[1]; // 0-1
		float b = hsv[2]; // 0-1 Brightness

		hue = hue + dHue;
		hsv[0] = validateHue(hue);

		sat = sat + dSatuarion;
		hsv[1] = validateSaturation(sat);

		b = b + dBrightness;
		hsv[2] = validateBrightness(b);

		return Color.HSVToColor(a, hsv);
	}

	public static int changeColor(final int color, final int offR, final int offG, final int offB) {
		final int a = Color.alpha(color);
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		r = validateColor(r + offR);
		g = validateColor(g + offG);
		b = validateColor(b + offB);
		return Color.argb(a, r, g, b);
	}

	private static int validateColor(int col) {
		if (col < 0) {
			col = 0;
		}
		if (col > 255) {
			col = 255;
		}
		return col;
	}

	private static float validateSaturation(final float sat) {
		if (sat > 1) {
			return 1f;
		}
		while (sat < 0) {
			return 0f;
		}
		return sat;
	}

	private static float validateBrightness(final float b) {
		if (b > 1) {
			return 1f;
		}
		while (b < 0) {
			return 0f;
		}
		return b;
	}

	private static float validateHue(float hue) {
		while (hue > 360) {
			hue = hue - 360;
		}
		while (hue < 0) {
			hue = hue + 360;
		}
		return hue;
	}

	private static int validateAlpha(int alpha) {
		if (alpha > 255) {
			alpha = 255;
		}
		if (alpha < 0) {
			alpha = 0;
		}
		return alpha;
	}

	public static int adjustColorBrightness(final int color, final int amout) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		final int a = Color.alpha(color);
		r = validateColor(r + amout);
		g = validateColor(g + amout);
		b = validateColor(b + amout);
		return Color.argb(a, r, g, b);
	}

	public static int setColorAlpha(final int color, final int alpha) {
		final int r = Color.red(color);
		final int g = Color.green(color);
		final int b = Color.blue(color);
		return Color.argb(validateAlpha(alpha), r, g, b);
	}

}
