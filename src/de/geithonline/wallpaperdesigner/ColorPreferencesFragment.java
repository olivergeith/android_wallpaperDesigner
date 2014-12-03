package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import de.geithonline.android.basics.preferences.colorpicker.ColorPickerPreference;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class ColorPreferencesFragment extends PreferenceFragment {

	private ListPreference gradientDirection;
	private ListPreference anzColors;
	private ColorPickerPreference color1;
	private ColorPickerPreference color2;
	private ColorPickerPreference color3;
	private ColorPickerPreference color4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_color);

		color1 = (ColorPickerPreference) findPreference("color_plain_bgrnd");
		color2 = (ColorPickerPreference) findPreference("color2_plain_bgrnd");
		color3 = (ColorPickerPreference) findPreference("color3_plain_bgrnd");
		color4 = (ColorPickerPreference) findPreference("color4_plain_bgrnd");

		color1.setHexValueEnabled(Settings.isHexValueEnabled());
		color2.setHexValueEnabled(Settings.isHexValueEnabled());
		color3.setHexValueEnabled(Settings.isHexValueEnabled());
		color4.setHexValueEnabled(Settings.isHexValueEnabled());

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
