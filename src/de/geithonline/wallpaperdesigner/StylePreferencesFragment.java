package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
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

	private ListPreference patternSelection;
	private ListPreference randomizeColorRange;
	private ListPreference randomizeAlphaRange;
	private ListPreference dropShadowType;
	private ListPreference filledOption;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		patternSelection = (ListPreference) findPreference(Settings.PATTERN_PATTERN_PICKER);
		filledOption = (ListPreference) findPreference(Settings.PATTERN_FILLED_OPTION);
		dropShadowType = (ListPreference) findPreference(Settings.PATTERN_DROPSHADOW_TYPE);
		randomizeColorRange = (ListPreference) findPreference("randomizeColorRange");
		randomizeAlphaRange = (ListPreference) findPreference("randomizeAlphaRange");

		handlePatternSelect(Settings.getSelectedPattern());
		patternSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handlePatternSelect((String) newValue);
				return true;
			}
		});

		handleFilledOptionSelected(Settings.getFilledOption());
		filledOption.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleFilledOptionSelected((String) newValue);
				return true;
			}
		});

		handleDropShadowTypeSelection(Settings.getDropShadowType());
		dropShadowType.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleDropShadowTypeSelection((String) newValue);
				return true;
			}

		});

		randomizeColorRange.setSummary("" + Settings.getRandomizeColorRange());
		randomizeColorRange.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				randomizeColorRange.setSummary((String) newValue);
				return true;
			}
		});
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

	private void handleFilledOptionSelected(final String value) {
		filledOption.setSummary(value);
	}

	public void handleDropShadowTypeSelection(final String newValue) {
		dropShadowType.setSummary(newValue);
		final Preference dropShadowColor = findPreference(Settings.PATTERN_DROPSHADOW_COLOR);
		dropShadowColor.setEnabled(newValue.equals("Select"));
	}

	private void handlePatternSelect(final String newPattern) {
		patternSelection.setSummary(newPattern);
		final CheckBoxPreference glossy = (CheckBoxPreference) findPreference(Settings.PATTERN_GLOSSY);
		final CheckBoxPreference outline = (CheckBoxPreference) findPreference(Settings.PATTERN_OUTLINE);
		final CheckBoxPreference rotate = (CheckBoxPreference) findPreference(Settings.PATTERN_RANDOM_ROTATE);
		glossy.setEnabled(Settings.hasPatternGlossyEffect(newPattern));
		outline.setEnabled(Settings.hasPatternOutlineEffect(newPattern));
		rotate.setEnabled(Settings.hasPatternRandomRotate(newPattern));
		filledOption.setEnabled(Settings.hasPatternFilledOption(newPattern));
		final PreferenceScreen specialSettings = (PreferenceScreen) findPreference("specialPatternSettings");
		specialSettings.setEnabled(Settings.hasPatternGlossyEffect(newPattern)//
				|| Settings.hasPatternOutlineEffect(newPattern)//
				|| Settings.hasPatternRandomRotate(newPattern)//
				|| Settings.hasPatternFilledOption(newPattern));

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
