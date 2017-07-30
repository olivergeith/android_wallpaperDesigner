
package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import de.geithonline.android.basics.preferences.InlineSeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.PathGetter;
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.PatternPropertyStore;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.Settings.DROP_SHADOW_TYPE;
import de.geithonline.wallpaperdesigner.utils.BitmapHelper;

/**
 * This fragment shows the preferences for the first header.
 */
public class StylePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference patternSelection;
	private ListPreference patternVariantSelection;
	private PreferenceScreen jellyfishOptions;
	private PreferenceScreen jellyfishTopviewOptions;
	private PreferenceScreen sceneRainOptions;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style_10_pattern);
		addPreferencesFromResource(R.xml.preferences_style_10_10_jellyfish);
		addPreferencesFromResource(R.xml.preferences_style_10_10_jellyfish_topview);
		addPreferencesFromResource(R.xml.preferences_style_10_11_scene_rain);
		addPreferencesFromResource(R.xml.preferences_style_20_dropshadow);
		addPreferencesFromResource(R.xml.preferences_style_30_glossy);
		addPreferencesFromResource(R.xml.preferences_style_40_rotating);
		addPreferencesFromResource(R.xml.preferences_style_50_outline);
		addPreferencesFromResource(R.xml.preferences_style_60_assorted);
		addPreferencesFromResource(R.xml.preferences_style_70_size);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		patternSelection = (ListPreference) findPreference(Settings.KEY_PATTERN_PATTERN_PICKER);
		patternSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handlePatternSelect((String) newValue);
				return true;
			}
		});
		patternVariantSelection = (ListPreference) findPreference(Settings.KEY_PATTERN_PATTERN_VARIANT_PICKER);
		patternVariantSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handlePatternVariantSelect((String) newValue);
				return true;
			}
		});

		jellyfishOptions = (PreferenceScreen) findPreference("jellyfishOptions");
		jellyfishTopviewOptions = (PreferenceScreen) findPreference("jellyfishOptions2");
		sceneRainOptions = (PreferenceScreen) findPreference("sceneRainOptions");

		handlePatternSelect(Settings.getSelectedPattern());
		handlePatternVariantSelect(Settings.getSelectedPatternVariant());
		handleDropShadowTypeSelection(Settings.getDropShadowType());
		handleRotatingStyleSelected(Settings.getRotationStyle());
		setIcon(Settings.getSelectedPattern(), Settings.getSelectedPatternVariant());
		enableProFeatures();
	}

	private void handleRotatingStyleSelected(final String newValue) {
		final InlineSeekBarPreference rotationDegrees = (InlineSeekBarPreference) findPreference("rotationDegrees");
		final InlineSeekBarPreference randomRange = (InlineSeekBarPreference) findPreference("randomRange");
		final InlineSeekBarPreference rotationCenterPointX = (InlineSeekBarPreference) findPreference("rotationCenterPointX");
		final InlineSeekBarPreference rotationCenterPointY = (InlineSeekBarPreference) findPreference("rotationCenterPointY");

		rotationDegrees.setEnabled(!newValue.equals("Random"));
		randomRange.setEnabled(newValue.contains("(Range)"));
		rotationCenterPointX.setEnabled(newValue.equals("Around Adjustable Center"));
		rotationCenterPointY.setEnabled(newValue.equals("Around Adjustable Center"));
	}

	public void handleDropShadowTypeSelection(final DROP_SHADOW_TYPE type) {
		final Preference dropShadowColor = findPreference(Settings.KEY_PATTERN_DROPSHADOW_COLOR);
		dropShadowColor.setEnabled(type.equals(DROP_SHADOW_TYPE.SELECT));
		final Preference dropShadowDarkness = findPreference(Settings.KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST);
		dropShadowDarkness.setEnabled(type.equals(DROP_SHADOW_TYPE.DARKER));
		final InlineSeekBarPreference dropShadowRadiusAdjustment = (InlineSeekBarPreference) findPreference("dropShadowRadiusAdjustment");
		dropShadowRadiusAdjustment.setEnabled(!type.equals(DROP_SHADOW_TYPE.NO));
		final InlineSeekBarPreference dropShadowOffsetX = (InlineSeekBarPreference) findPreference(Settings.KEY_DROP_SHADOW_OFFSET_X);
		dropShadowOffsetX.setEnabled(!type.equals(DROP_SHADOW_TYPE.NO));
		final InlineSeekBarPreference dropShadowOffsetY = (InlineSeekBarPreference) findPreference(Settings.KEY_DROP_SHADOW_OFFSET_Y);
		dropShadowOffsetY.setEnabled(!type.equals(DROP_SHADOW_TYPE.NO));

	}

	private void handlePatternSelect(final String newPattern) {
		final ListPreference textDrawStyle = (ListPreference) findPreference(Settings.KEY_PATTERN_TEXT_DRAW_STYLE);
		final ListPreference filledOption = (ListPreference) findPreference(Settings.KEY_PATTERN_FILLED_OPTION);
		final InlineSeekBarPreference numberOfLeafs = (InlineSeekBarPreference) findPreference(Settings.KEY_NUMBER_OF_LEAFS);
		final CheckBoxPreference randomLeafCount = (CheckBoxPreference) findPreference(Settings.KEY_RANDOM_LEAF_COUNT);
		final EditTextPreference textPattern = (EditTextPreference) findPreference(Settings.KEY_PATTERN_TEXT);

		filledOption.setEnabled(PatternPropertyStore.hasPatternFilledOption(newPattern));
		textPattern.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
		textDrawStyle.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
		numberOfLeafs.setEnabled(PatternPropertyStore.hasNumberOfLeafsOption(newPattern));
		randomLeafCount.setEnabled(PatternPropertyStore.hasNumberOfLeafsOption(newPattern));

		patternSelection.setSummary(newPattern);

		// Pattern Variants
		patternVariantSelection.setEnabled(PatternPropertyStore.hasPatternVariants(newPattern));

		if (PatternPropertyStore.hasPatternVariants(newPattern)) {
			final CharSequence[] patternVariants = PatternPropertyStore.getPatternVariants(newPattern);
			// for (final CharSequence charSequence : patternVariants) {
			// Log.i("GEITH", "Setting Pattern..." + newPattern + " variants = " + charSequence);
			// }
			patternVariantSelection.setEntries(patternVariants);
			patternVariantSelection.setEntryValues(patternVariants);
			if (!newPattern.equals(patternSelection.getValue())) {
				patternVariantSelection.setValueIndex(0);
				final String variant = patternVariantSelection.getValue();
				Log.i("GEITH", "Setting PatternVariant on new Pattern..." + variant);
				patternVariantSelection.setDefaultValue(variant);
				Settings.prefs.edit().putString(patternVariantSelection.getKey(), variant).commit();
			}
			final String variant = patternVariantSelection.getValue();
			Log.i("GEITH", "Setting PatternVariant..." + variant);
			patternVariantSelection.setSummary(variant);

		} else {
			patternVariantSelection.setEntries(null);
			patternVariantSelection.setEntryValues(null);
			patternVariantSelection.setSummary("not available");
		}

		// Screens enablen oder disablen
		final PreferenceScreen glossyScreen = (PreferenceScreen) findPreference("glossyScreen");
		glossyScreen.setEnabled(PatternPropertyStore.hasPatternGlossyEffect(newPattern));
		final PreferenceScreen ratatingScreen = (PreferenceScreen) findPreference("ratatingScreen");
		ratatingScreen.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
		final PreferenceScreen outlineScreen = (PreferenceScreen) findPreference("outlineScreen");
		outlineScreen.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
		final PreferenceScreen assortedScreen = (PreferenceScreen) findPreference("assortedScreen");
		assortedScreen.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern) //
				|| PatternPropertyStore.hasPatternFilledOption(newPattern)//
				|| PatternPropertyStore.hasNumberOfLeafsOption(newPattern)//
		);

		// Special Pattern Settings enabel or disable
		addSpecialPreferences(newPattern, patternVariantSelection.getValue());
	}

	private void addSpecialPreferences(final String pattern, final String variant) {
		Log.i("GEITH", "addSpecialPreferences..." + pattern + "-" + variant);

		// Special Pattern Settings enabel or disable
		if (pattern.equalsIgnoreCase("Jellyfish")) {
			getPreferenceScreen().addPreference(jellyfishOptions);
		} else {
			getPreferenceScreen().removePreference(jellyfishOptions);
		}
		if (pattern.equalsIgnoreCase("Jellyfish Topview") && variant.equals("Fully customizable")) {
			getPreferenceScreen().addPreference(jellyfishTopviewOptions);
		} else {
			getPreferenceScreen().removePreference(jellyfishTopviewOptions);
		}
		if (pattern.equalsIgnoreCase("Scenes") && variant.equals("Rain")) {
			getPreferenceScreen().addPreference(sceneRainOptions);
		} else {
			getPreferenceScreen().removePreference(sceneRainOptions);
		}
		// // some precausions
		// if (pattern.equalsIgnoreCase("Jellyfish Topview") && Settings.getAnzahlPatterns() > 300) {
		// Settings.prefs.edit().putInt(Settings.KEY_PATTERN_ANZAHL_PATTERNS, 300).commit();
		// }

	}

	protected void handlePatternVariantSelect(final String newVariant) {
		Log.i("GEITH", "Setting PatternVariant..." + newVariant);
		patternVariantSelection.setSummary(newVariant);
		addSpecialPreferences(Settings.getSelectedPattern(), newVariant);

		// final Bitmap drawIcon = bmpRenderer.drawIcon(128);
		// patternVariantSelection.setIcon(BitmapHelper.bitmapToIcon(drawIcon));
	}

	private void enableProFeatures() {
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		if (key == null) {
			return;
		}
		switch (key) {
		default:
			break;
		case Settings.KEY_PATTERN_DROPSHADOW_TYPE:
			handleDropShadowTypeSelection(Settings.getDropShadowType());
			break;
		case Settings.KEY_ROTATING_STYLE:
			handleRotatingStyleSelected(Settings.getRotationStyle());
			break;
		case Settings.KEY_PATTERN_PATTERN_PICKER:
		case Settings.KEY_PATTERN_PATTERN_VARIANT_PICKER:
			setIcon(Settings.getSelectedPattern(), Settings.getSelectedPatternVariant());
			break;
		}

	}

	private void setIcon(final String pattern, final String variant) {

		final Bitmap drawIcon = PathGetter.drawIconBitmap(128, pattern, variant);
		patternVariantSelection.setIcon(BitmapHelper.bitmapToIcon(drawIcon));

	}

}
