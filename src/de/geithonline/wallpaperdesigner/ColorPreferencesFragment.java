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

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_color);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		gradientDirection = (ListPreference) findPreference("gradientDirection");
		gradientDirection.setSummary(Settings.getGradientDirection());
		gradientDirection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				gradientDirection.setSummary((String) newValue);
				return true;
			}
		});

		enableProFeatures();
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
