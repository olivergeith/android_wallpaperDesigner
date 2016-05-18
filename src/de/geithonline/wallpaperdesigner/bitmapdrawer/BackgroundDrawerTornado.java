package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class BackgroundDrawerTornado {

	/**
	 * @param canvas
	 * @param off
	 *            staerke des Tornados 1-4 sinnnvoll
	 */
	public static void draw4ColorTornado(final Canvas canvas, final int off, final int spiralArms) {
		final int tornadoColors[] = buildTornadoColors(10);
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final float abstand = cWidth / 30;
		final float maximumRadius = (float) (Math.sqrt(cWidth * cWidth / 4 + cHeight * cHeight / 4) + abstand);
		final int radiusStep = Math.round(abstand);
		final int anzRinge = (int) (maximumRadius / radiusStep);
		final Point center = new Point(cWidth / 2, cHeight / 2);

		int index = 0;
		for (int ring = 0; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = tornadoColors.length * spiralArms + off * spiralArms;
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
				canvas.drawCircle(p.x, p.y, abstand * (1.0f + ring * 0.1f), paint);
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

	private static int[] buildTornadoColors(final int steps) {
		final int tornadoColors[] = new int[steps * 4];
		int index = 0;
		for (int color = 1; color < 5; color++) {
			for (int i = 0; i < steps; i++) {

				final int col = ColorHelper.getRadiantColor(getColor(color), getColor(color + 1), i, 0, steps - 1);
				tornadoColors[index] = col;
				index++;
			}
		}

		final boolean reverse = Settings.isReverseColors();
		if (reverse) {
			reverse(tornadoColors);
		}
		return tornadoColors;
	}

	private static void reverse(final int[] data) {
		int left = 0;
		int right = data.length - 1;

		while (left < right) {
			// swap the values at the left and right indices
			final int temp = data[left];
			data[left] = data[right];
			data[right] = temp;

			// move the left and right index pointers in toward the center
			left++;
			right--;
		}
	}

}
