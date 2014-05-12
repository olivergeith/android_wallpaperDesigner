package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class ColorPreferencesFragment extends PreferenceFragment {

	private ListPreference gradientDirection;
	private ListPreference anzColors;
	private Preference color3;
	private Preference color4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_color);

		color3 = findPreference("color3_plain_bgrnd");
		color4 = findPreference("color4_plain_bgrnd");
		gradientDirection = (ListPreference) findPreference("gradientDirection");
		anzColors = (ListPreference) findPreference("anzColors");

		anzColors.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				final int anz = Integer.parseInt((String) newValue);
				handleAnzColorsSelection(anz);
				return true;
			}
		});
		gradientDirection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleGradientSelection((String) newValue);
				return true;
			}
		});
		handleGradientSelection(Settings.getGradientDirection());
		handleAnzColorsSelection(Settings.getAnzahlGradientColors());
		enableProFeatures();
	}

	private void handleGradientSelection(final String selection) {
		gradientDirection.setSummary(selection);
		anzColors.setEnabled(!Settings.is4ColorGradient(selection));
		if (Settings.is4ColorGradient(selection)) {
			enableColors(4);
		} else {
			enableColors(Settings.getAnzahlGradientColors());
		}
	}

	private void handleAnzColorsSelection(final int anzahl) {
		anzColors.setSummary("" + anzahl);
		enableColors(anzahl);
	}

	private void enableColors(final int anzahl) {
		switch (anzahl) {

			case 2:
				color3.setEnabled(false);
				color4.setEnabled(false);
				break;
			case 3:
				color3.setEnabled(true);
				color4.setEnabled(false);
				break;
			default:
			case 4:
				color3.setEnabled(true);
				color4.setEnabled(true);
				break;
		}
	}

	private void enableProFeatures() {
	}

}
