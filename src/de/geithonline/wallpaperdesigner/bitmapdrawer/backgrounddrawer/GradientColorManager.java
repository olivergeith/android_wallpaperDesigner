package de.geithonline.wallpaperdesigner.bitmapdrawer.backgrounddrawer;

import de.geithonline.wallpaperdesigner.settings.Settings;

public class GradientColorManager {

	public static int[] getColors() {
		final boolean reverse = Settings.isReverseColors();
		final int rounds = Settings.getColorRepeats();
		final int anzahl = Settings.getAnzahlGradientColors();
		final int colors[] = new int[anzahl * rounds];
		int index = 0;
		for (int r = 1; r <= rounds; r++) {
			for (int i = 1; i <= anzahl; i++) {
				colors[index] = getColor(i);
				index++;
			}
		}
		if (reverse) {
			reverse(colors);
		}
		return colors;
	}

	public static int[] getColorsMirrored() {
		final int colors[] = getColors();
		final int newanz = colors.length * 2 - 1;
		final int[] outcolors = new int[newanz];
		int index = 0;
		// erstmal alle Farben eins zu eins kopieren
		for (int i = 0; i < colors.length; i++) {
			outcolors[index] = colors[i];
			index++;
		}
		// dann rückwärts anhangen, aber die letzte auslassen
		for (int i = colors.length - 2; i >= 0; i--) {
			outcolors[index] = colors[i];
			index++;
		}
		return outcolors;
	}

	public static int[] getColorsSweep() {
		final boolean reverse = Settings.isReverseColors();
		final int rounds = Settings.getColorRepeats();
		final int anzahl = Settings.getAnzahlGradientColors();
		final int colors[] = new int[anzahl * rounds + 1];
		int index = 0;
		for (int r = 1; r <= rounds; r++) {
			for (int i = 1; i <= anzahl; i++) {
				colors[index] = getColor(i);
				index++;
			}
		}
		colors[index] = getColor(1);
		if (reverse) {
			reverse(colors);
		}
		return colors;
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

	public static float[] getDistancesSweepArc(final int anz, final float min, final float max) {
		final float distancesSweep2[] = new float[anz];
		final float diff = max - min;
		final float step = diff / (anz - 1);

		for (int i = 0; i < anz; i++) {
			distancesSweep2[i] = min + i * step;
		}
		return distancesSweep2;
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

}
