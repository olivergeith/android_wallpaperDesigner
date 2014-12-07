package de.geithonline.wallpaperdesigner;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class ExampleSettingsFragment extends PreferenceFragment {

	private Preference unzipSettings;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_example_settings);

		unzipSettings = findPreference("unzipSettings");
		unzipSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), RemoteSettingsView.class);
				startActivityForResult(intent, 1);
				return false;
			}
		});
	}
}
