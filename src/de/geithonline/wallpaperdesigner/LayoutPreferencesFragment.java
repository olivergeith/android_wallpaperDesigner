package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.util.Log;
import de.geithonline.android.basics.preferences.SeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.layout.LayoutManagerV2;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class LayoutPreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference mainlayouts;
	private ListPreference mainlayoutVariants;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_layout);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		mainlayouts = (ListPreference) findPreference("mainlayouts");
		mainlayoutVariants = (ListPreference) findPreference("mainlayoutVariants");

		mainlayouts.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleMainLayoutSelect((String) newValue);
				return true;
			}
		});
		mainlayoutVariants.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleMainLayoutVariantSelect((String) newValue);
				return true;
			}
		});

		handleMainLayoutSelect(Settings.getSelectedMainLayout());
		handleMainLayoutVariantSelect(Settings.getSelectedMainLayoutVariante());
	}

	private void handleMainLayoutSelect(final String selectedLayout) {
		mainlayouts.setSummary(selectedLayout);
		final SeekBarPreference overlapping = (SeekBarPreference) findPreference("overlapping");
		final SeekBarPreference anzahlPatterns = (SeekBarPreference) findPreference("anzahlPatterns");
		final CheckBoxPreference blurring = (CheckBoxPreference) findPreference("blurPatterns");
		final CheckBoxPreference upsideDown = (CheckBoxPreference) findPreference("upsideDown");
		overlapping.setEnabled(LayoutManagerV2.hasLayoutOverlap(selectedLayout));
		anzahlPatterns.setEnabled(LayoutManagerV2.hasLayoutAnzahlPattern(selectedLayout));
		blurring.setEnabled(LayoutManagerV2.hasLayoutBlurring(selectedLayout));
		upsideDown.setEnabled(LayoutManagerV2.hasLayoutUpsideDown(selectedLayout));

		// Pattern Variants
		mainlayoutVariants.setEnabled(LayoutManagerV2.hasLayoutVariants(selectedLayout));

		if (LayoutManagerV2.hasLayoutVariants(selectedLayout)) {
			Log.i("GEITH", "Setting Pattern...");
			final CharSequence[] variants = LayoutManagerV2.getLayoutVariants(selectedLayout);
			mainlayoutVariants.setEntries(variants);
			mainlayoutVariants.setEntryValues(variants);
			if (!selectedLayout.equals(mainlayouts.getValue())) {
				mainlayoutVariants.setValueIndex(0);
				mainlayoutVariants.setDefaultValue(mainlayoutVariants.getValue());
			}
			// patternVariantSelection.setValueIndex(0);
			// patternVariantSelection.setDefaultValue(patternVariantSelection.getValue());
			mainlayoutVariants.setSummary(mainlayoutVariants.getValue());

		} else {
			mainlayoutVariants.setEntries(null);
			mainlayoutVariants.setEntryValues(null);
			mainlayoutVariants.setSummary("not available");
		}

	}

	private void handleMainLayoutVariantSelect(final String variante) {
		mainlayoutVariants.setSummary(variante);
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		switch (key) {
		default:
			break;
		}

	}
}
