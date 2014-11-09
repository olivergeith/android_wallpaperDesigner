package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import de.geithonline.android.basics.preferences.SeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.LayoutManager;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class LayoutPreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference layoutSelection;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_layout);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		layoutSelection = (ListPreference) findPreference("layoutPicker");

		layoutSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleLayoutSelect((String) newValue);
				return true;
			}
		});

		handleLayoutSelect(Settings.getSelectedLayout());
	}

	private void handleLayoutSelect(final String selectedLayout) {
		layoutSelection.setSummary(selectedLayout);
		final SeekBarPreference overlapping = (SeekBarPreference) findPreference("overlapping");
		final SeekBarPreference anzahlPatterns = (SeekBarPreference) findPreference("anzahlPatterns");
		final CheckBoxPreference blurring = (CheckBoxPreference) findPreference("blurPatterns");
		final CheckBoxPreference upsideDown = (CheckBoxPreference) findPreference("upsideDown");
		overlapping.setEnabled(LayoutManager.supportsOverLay(selectedLayout));
		anzahlPatterns.setEnabled(LayoutManager.supportsAnzahlPatterns(selectedLayout));
		blurring.setEnabled(LayoutManager.supportsBlurring(selectedLayout));
		upsideDown.setEnabled(LayoutManager.supportsUpsideDown(selectedLayout));
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		switch (key) {
		default:
			break;
		}

	}
}
