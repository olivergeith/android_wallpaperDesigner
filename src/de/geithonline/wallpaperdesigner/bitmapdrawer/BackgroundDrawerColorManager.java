package de.geithonline.wallpaperdesigner.bitmapdrawer;

import de.geithonline.wallpaperdesigner.settings.Settings;

public class BackgroundDrawerColorManager {

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
