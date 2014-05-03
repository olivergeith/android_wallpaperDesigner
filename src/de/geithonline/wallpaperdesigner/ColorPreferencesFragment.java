package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class ColorPreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference gradientDirection;
	private Preference color3;
	private Preference color4;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_color);
		Settings.prefs.registerOnSharedPreferenceChangeListener(this);

		color3 = findPreference("color3_plain_bgrnd");
		color4 = findPreference("color4_plain_bgrnd");

		gradientDirection = (ListPreference) findPreference("gradientDirection");
		handleGradientSelection(Settings.getGradientDirection());
		gradientDirection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleGradientSelection((String) newValue);
				return true;
			}
		});

		enableProFeatures();
	}

	private void handleGradientSelection(final String selection) {
		gradientDirection.setSummary(selection);
		color3.setEnabled(Settings.is4ColorGradient(selection));
		color4.setEnabled(Settings.is4ColorGradient(selection));
	}

	private void enableProFeatures() {
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		switch (key) {
		default:
			break;
		}

	}

}
