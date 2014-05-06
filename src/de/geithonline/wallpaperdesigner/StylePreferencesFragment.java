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
public class StylePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference patternSelection;
	private ListPreference randomizeColorRange;
	private ListPreference randomizeAlphaRange;
	private ListPreference anzahlPatterns;
	private ListPreference dropShadowType;;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		patternSelection = (ListPreference) findPreference(Settings.PATTERN_PATTERN_PICKER);
		patternSelection.setSummary(Settings.getSelectedPattern());
		patternSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				patternSelection.setSummary((String) newValue);
				return true;
			}
		});
		anzahlPatterns = (ListPreference) findPreference(Settings.PATTERN_ANZAHL_PATTERNS);
		anzahlPatterns.setSummary("" + Settings.getAnzahlPatterns());
		anzahlPatterns.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				anzahlPatterns.setSummary((String) newValue);
				return true;
			}
		});

		dropShadowType = (ListPreference) findPreference(Settings.PATTERN_DROPSHADOW_TYPE);
		handleDropShadowTypeSelection(Settings.getDropShadowType());
		dropShadowType.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleDropShadowTypeSelection((String) newValue);
				return true;
			}

		});

		randomizeColorRange = (ListPreference) findPreference("randomizeColorRange");
		randomizeColorRange.setSummary("" + Settings.getRandomizeColorRange());
		randomizeColorRange.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				randomizeColorRange.setSummary((String) newValue);
				return true;
			}
		});
		randomizeAlphaRange = (ListPreference) findPreference("randomizeAlphaRange");
		randomizeAlphaRange.setSummary("" + Settings.getRandomizeAlphaRange());
		randomizeAlphaRange.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				randomizeAlphaRange.setSummary((String) newValue);
				return true;
			}
		});

		enableProFeatures();
	}

	public void handleDropShadowTypeSelection(final String newValue) {
		dropShadowType.setSummary(newValue);
		final Preference dropShadowColor = findPreference(Settings.PATTERN_DROPSHADOW_COLOR);
		dropShadowColor.setEnabled(newValue.equals("Select"));
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
