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
	public static int adjustHSV(final int color, final float dHue, final float dSatuarion, final float dBrightness) {
		final int a = Color.alpha(color);

		final float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		float hue = hsv[0]; // 0-360
		float sat = hsv[1]; // 0-1
		float b = hsv[2]; // 0-1 Brightness

		hue = hue + dHue;
		while (hue > 360) {
			hue = hue - 360;
		}
		while (hue < 0) {
			hue = hue + 360;
		}
		hsv[0] = hue;

		sat = sat + dSatuarion;
		if (sat > 1) {
			sat = 1;
		}
		while (sat < 0) {
			sat = 0;
		}
		hsv[1] = sat;

		b = b + dBrightness;
		if (b > 1) {
			b = 1;
		}
		while (b < 0) {
			b = 0;
		}
		hsv[2] = b;

		return Color.HSVToColor(a, hsv);
	}

	public static int changeColor(final int color, final int offR, final int offG, final int offB) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		r = validateColor(r + offR);
		g = validateColor(g + offG);
		b = validateColor(b + offB);
		return Color.rgb(r, g, b);
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

	public static int randomizeColorBrightness(final int color, final int range) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		final int off = Randomizer.getRandomInt(-range, range);
		r = validateColor(r + off);
		g = validateColor(g + off);
		b = validateColor(b + off);
		return Color.rgb(r, g, b);
	}

}
