package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class StylePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference styleSelection;
	private ListPreference patternSelection;
	private ListPreference randomizeColorRange;
	private ListPreference randomizeAlphaRange;
	private ListPreference anzahlPatterns;

	private PreferenceScreen patternScreen;
	private PreferenceScreen squaresScreen;

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

		styleSelection = (ListPreference) findPreference("stylePicker");
		styleSelection.setSummary(Settings.getSelectedStyle());
		styleSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				styleSelection.setSummary((String) newValue);
				enableScreens((String) newValue);
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

		patternScreen = (PreferenceScreen) findPreference("patternsScreen");
		squaresScreen = (PreferenceScreen) findPreference("squaresScreen");

		enableScreens(Settings.getSelectedStyle());
		enableProFeatures();
	}

	private void enableScreens(final String style) {
		patternScreen.setEnabled(style.equals("Patterns"));
		squaresScreen.setEnabled(style.equals("Squares"));
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
