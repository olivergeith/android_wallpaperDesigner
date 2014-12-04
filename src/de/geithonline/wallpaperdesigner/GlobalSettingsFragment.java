package de.geithonline.wallpaperdesigner;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settingsdownloader.SettingsDownloader;

public class GlobalSettingsFragment extends PreferenceFragment {

	private ListPreference sortOrder;
	private Preference unzipSettings;
	private Preference unzipSettingsV2;
	private Preference imageFormat;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_globalsettings);

		unzipSettings = findPreference("unzipSettings");
		unzipSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), RemoteSettingsView.class);
				startActivityForResult(intent, 1);

				// RemoteSettingsBrowser.loadRemoteWebSite(getActivity());
				// SettingsDownloader.startDownloadFile1(getActivity());
				return false;
			}

		});
		unzipSettingsV2 = findPreference("unzipSettingsV2");
		unzipSettingsV2.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsDownloader.startDownloadFile2(getActivity());
				return false;
			}

		});

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
