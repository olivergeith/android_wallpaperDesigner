package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import de.geithonline.android.basics.preferences.SeekBarPreference;
import de.geithonline.wallpaperdesigner.settings.PatternPropertyStore;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class StylePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference patternSelection;
	private ListPreference dropShadowType;
	private ListPreference filledOption;
	private EditTextPreference textPattern;
	private ListPreference textDrawStyle;
	private SeekBarPreference minOpacity;
	private SeekBarPreference maxOpacity;
	private SeekBarPreference numberOfLeafs;
	private SeekBarPreference rotationDegrees;
	private CheckBoxPreference randomLeafCount;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		patternSelection = (ListPreference) findPreference(Settings.PATTERN_PATTERN_PICKER);
		filledOption = (ListPreference) findPreference(Settings.PATTERN_FILLED_OPTION);
		textDrawStyle = (ListPreference) findPreference(Settings.PATTERN_TEXT_DRAW_STYLE);
		dropShadowType = (ListPreference) findPreference(Settings.PATTERN_DROPSHADOW_TYPE);
		textPattern = (EditTextPreference) findPreference(Settings.PATTERN_TEXT);

		minOpacity = (SeekBarPreference) findPreference("minOpacity");
		maxOpacity = (SeekBarPreference) findPreference("maxOpacity");
		numberOfLeafs = (SeekBarPreference) findPreference(Settings.NUMBER_OF_LEAFS);
		randomLeafCount = (CheckBoxPreference) findPreference(Settings.RANDOM_LEAF_COUNT);
		rotationDegrees = (SeekBarPreference) findPreference("rotationDegrees");

		maxOpacity.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleMaxOpacityChange((int) newValue);
				return true;
			}

		});

		textPattern.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handlePatternTextChanged((String) newValue);
				return true;
			}

		});

		patternSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handlePatternSelect((String) newValue);
				return true;
			}
		});

		filledOption.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleFilledOptionSelected((String) newValue);
				return true;
			}
		});

		textDrawStyle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleTextDrawStyleSelected((String) newValue);
				return true;
			}
		});

		dropShadowType.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleDropShadowTypeSelection((String) newValue);
				return true;
			}

		});

		handlePatternSelect(Settings.getSelectedPattern());
		handleFilledOptionSelected(Settings.getFilledOption());
		handleDropShadowTypeSelection(Settings.getDropShadowType());
		handlePatternTextChanged(Settings.getText());
		handleTextDrawStyleSelected(Settings.getTextDrawStyle());
		enableProFeatures();
	}

	protected void handleTextDrawStyleSelected(final String newValue) {
		textDrawStyle.setSummary(newValue);

	}

	private void handlePatternTextChanged(final String newValue) {
		textPattern.setSummary(newValue);
	}

	private void handleFilledOptionSelected(final String value) {
		filledOption.setSummary(value);
	}

	public void handleDropShadowTypeSelection(final String newValue) {
		dropShadowType.setSummary(newValue);
		final Preference dropShadowColor = findPreference(Settings.PATTERN_DROPSHADOW_COLOR);
		dropShadowColor.setEnabled(newValue.equals("Select"));
		final Preference dropShadowDarkness = findPreference(Settings.PATTERN_DROPSHADOW_DARKNESS_ADJUST);
		dropShadowDarkness.setEnabled(newValue.equals("Darker"));
	}

	private void handlePatternSelect(final String newPattern) {
		patternSelection.setSummary(newPattern);
		final CheckBoxPreference glossy = (CheckBoxPreference) findPreference(Settings.PATTERN_GLOSSY);
		final CheckBoxPreference outline = (CheckBoxPreference) findPreference(Settings.PATTERN_OUTLINE);
		final CheckBoxPreference outlineneverTransparent = (CheckBoxPreference) findPreference(Settings.PATTERN_OUTLINE_NEVER_TRANSPARENT);
		final CheckBoxPreference rotate = (CheckBoxPreference) findPreference(Settings.PATTERN_RANDOM_ROTATE);
		glossy.setEnabled(PatternPropertyStore.hasPatternGlossyEffect(newPattern));
		outline.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
		outlineneverTransparent.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
		rotate.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
		rotationDegrees.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
		filledOption.setEnabled(PatternPropertyStore.hasPatternFilledOption(newPattern));
		textPattern.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
		textDrawStyle.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
		final PreferenceScreen specialSettings = (PreferenceScreen) findPreference("specialPatternSettings");
		specialSettings.setEnabled(PatternPropertyStore.hasPatternSpecialSettings(newPattern));
		numberOfLeafs.setEnabled(PatternPropertyStore.hasNumberOfLeafsOption(newPattern));
		randomLeafCount.setEnabled(PatternPropertyStore.hasNumberOfLeafsOption(newPattern));
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

	private void handleMaxOpacityChange(final int newValue) {
		Log.i("Geith", "NewVal = " + newValue);
		if (newValue < Settings.prefs.getInt("minOpacity", 128)) {
			Settings.prefs.edit().putInt("minOpacity", newValue).commit();

		}
	}

}
