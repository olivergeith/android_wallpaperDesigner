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
import android.util.Log;
import de.geithonline.android.basics.preferences.SeekBarPreference;
import de.geithonline.wallpaperdesigner.settings.PatternPropertyStore;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class StylePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference patternSelection;
	private ListPreference patternVariantSelection;
	private ListPreference dropShadowType;
	private ListPreference filledOption;
	private EditTextPreference textPattern;
	private ListPreference textDrawStyle;
	private SeekBarPreference maxOpacity;
	private SeekBarPreference numberOfLeafs;
	private SeekBarPreference rotationDegrees;
	private CheckBoxPreference randomLeafCount;
	private ListPreference rotatingStyle;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		patternSelection = (ListPreference) findPreference(Settings.KEY_PATTERN_PATTERN_PICKER);
		patternVariantSelection = (ListPreference) findPreference(Settings.KEY_PATTERN_PATTERN_VARIANT_PICKER);
		filledOption = (ListPreference) findPreference(Settings.KEY_PATTERN_FILLED_OPTION);
		textDrawStyle = (ListPreference) findPreference(Settings.KEY_PATTERN_TEXT_DRAW_STYLE);
		dropShadowType = (ListPreference) findPreference(Settings.KEY_PATTERN_DROPSHADOW_TYPE);
		textPattern = (EditTextPreference) findPreference(Settings.KEY_PATTERN_TEXT);

		maxOpacity = (SeekBarPreference) findPreference("maxOpacity");
		numberOfLeafs = (SeekBarPreference) findPreference(Settings.KEY_NUMBER_OF_LEAFS);
		randomLeafCount = (CheckBoxPreference) findPreference(Settings.KEY_RANDOM_LEAF_COUNT);
		rotationDegrees = (SeekBarPreference) findPreference("rotationDegrees");
		rotatingStyle = (ListPreference) findPreference("rotatingStyle");

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

		patternVariantSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handlePatternVariantSelect((String) newValue);
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

		rotatingStyle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleRotatingStyleSelected((String) newValue);
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
		handleRotatingStyleSelected(Settings.getRotationStyle());
		enableProFeatures();
	}

	private void handleRotatingStyleSelected(final String newValue) {
		rotatingStyle.setSummary(newValue);
		rotationDegrees.setEnabled(newValue.equals("Fixed") || newValue.equals("Around Point")
				|| newValue.equals("Around Bottom"));
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
		final Preference dropShadowColor = findPreference(Settings.KEY_PATTERN_DROPSHADOW_COLOR);
		dropShadowColor.setEnabled(newValue.equals("Select"));
		final Preference dropShadowDarkness = findPreference(Settings.KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST);
		dropShadowDarkness.setEnabled(newValue.equals("Darker"));
		final SeekBarPreference dropShadowRadiusAdjustment = (SeekBarPreference) findPreference("dropShadowRadiusAdjustment");
		dropShadowRadiusAdjustment.setEnabled(!newValue.equals("No"));

	}

	private void handlePatternSelect(final String newPattern) {
		patternSelection.setSummary(newPattern);
		final CheckBoxPreference glossy = (CheckBoxPreference) findPreference(Settings.KEY_PATTERN_GLOSSY);
		final CheckBoxPreference outline = (CheckBoxPreference) findPreference(Settings.KEY_PATTERN_OUTLINE);
		final CheckBoxPreference outlineneverTransparent = (CheckBoxPreference) findPreference(Settings.KEY_PATTERN_OUTLINE_NEVER_TRANSPARENT);
		glossy.setEnabled(PatternPropertyStore.hasPatternGlossyEffect(newPattern));
		outline.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
		outlineneverTransparent.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
		rotationDegrees.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
		rotationDegrees.setEnabled(Settings.getRotationStyle().equals("Fixed"));
		rotatingStyle.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
		filledOption.setEnabled(PatternPropertyStore.hasPatternFilledOption(newPattern));
		textPattern.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
		textDrawStyle.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
		// final PreferenceScreen specialSettings = (PreferenceScreen) findPreference("specialPatternSettings");
		// specialSettings.setEnabled(PatternPropertyStore.hasPatternSpecialSettings(newPattern));
		numberOfLeafs.setEnabled(PatternPropertyStore.hasNumberOfLeafsOption(newPattern));
		randomLeafCount.setEnabled(PatternPropertyStore.hasNumberOfLeafsOption(newPattern));

		// Pattern Variants
		patternVariantSelection.setEnabled(PatternPropertyStore.hasPatternVariants(newPattern));

		if (PatternPropertyStore.hasPatternVariants(newPattern)) {
			Log.i("GEITH", "Setting Pattern...");
			final CharSequence[] patternVariants = PatternPropertyStore.getPatternVariants(newPattern);
			patternVariantSelection.setEntries(patternVariants);
			patternVariantSelection.setEntryValues(patternVariants);
			if (!newPattern.equals(patternSelection.getValue())) {
				patternVariantSelection.setValueIndex(0);
				patternVariantSelection.setDefaultValue(patternVariantSelection.getValue());
			}
			// patternVariantSelection.setValueIndex(0);
			// patternVariantSelection.setDefaultValue(patternVariantSelection.getValue());
			patternVariantSelection.setSummary(patternVariantSelection.getValue());

		} else {
			patternVariantSelection.setEntries(null);
			patternVariantSelection.setEntryValues(null);
			patternVariantSelection.setSummary("not available");
		}

	}

	protected void handlePatternVariantSelect(final String newVariant) {
		patternVariantSelection.setSummary(newVariant);
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
