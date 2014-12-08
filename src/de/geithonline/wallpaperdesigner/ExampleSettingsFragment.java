package de.geithonline.wallpaperdesigner;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;

public class ExampleSettingsFragment extends PreferenceFragment {

	private Preference deleteSettings;
	private Preference loadSettings;
	private Preference unzipSettings;
	private Preference unzipSettingsPremium;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_example_settings);

		unzipSettings = findPreference("unzipSettings");
		unzipSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsView.class);
				intent.putExtra("Premium", false);
				startActivityForResult(intent, 1);
				return false;
			}
		});
		unzipSettingsPremium = findPreference("unzipSettingsPremium");
		unzipSettingsPremium.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsView.class);
				intent.putExtra("Premium", true);
				startActivityForResult(intent, 1);
				return false;
			}
		});
		deleteSettings = findPreference("deleteSettings");
		deleteSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.deletePreferencesTheFancyWay(getActivity());
				return false;
			}
		});
		loadSettings = findPreference("loadSettings");
		loadSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.loadPreferencesTheFancyWay(getActivity(), Settings.prefs);
				return false;
			}
		});
	}
}
