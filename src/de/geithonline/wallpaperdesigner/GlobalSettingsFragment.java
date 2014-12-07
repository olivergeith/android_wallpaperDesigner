package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class GlobalSettingsFragment extends PreferenceFragment {

	private ListPreference sortOrder;
	private Preference imageFormat;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_globalsettings);

		sortOrder = (ListPreference) findPreference("sortOrder");
		sortOrder.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleSortOrder((String) newValue);
				return true;
			}

		});
		imageFormat = findPreference("imageFormat");
		imageFormat.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleImageFormat((String) newValue);
				return true;
			}

		});
		handleImageFormat(Settings.getImageFormat());
		handleSortOrder(Settings.getSortOrder());
	}

	private void handleSortOrder(final String newValue) {
		sortOrder.setSummary(newValue);
	}

	private void handleImageFormat(final String newValue) {
		imageFormat.setSummary(newValue);
	}
}
