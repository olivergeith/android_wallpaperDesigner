
package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import de.geithonline.android.basics.preferences.InlineSeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.PatternGetter;
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
	private PreferenceScreen cubeOptions;
	private PreferenceScreen textOptions;
	private PreferenceScreen leafOptions;
	private PreferenceScreen cMazeOptions;
	private PreferenceScreen filledOptions;
	private PreferenceScreen outlineScreen;
	private PreferenceScreen glossyScreen;
	private PreferenceScreen ratatingScreen;
	private Preference dropShadowColor;
	private Preference dropShadowDarkness;
	private InlineSeekBarPreference dropShadowRadiusAdjustment;
	private InlineSeekBarPreference dropShadowOffsetX;
	private InlineSeekBarPreference dropShadowOffsetY;
	private InlineSeekBarPreference rotationDegrees;
	private InlineSeekBarPreference randomRange;
	private InlineSeekBarPreference rotationCenterPointX;
	private InlineSeekBarPreference rotationCenterPointY;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style_10_pattern);
		addPreferencesFromResource(R.xml.preferences_style_10_10_jellyfish);
		addPreferencesFromResource(R.xml.preferences_style_10_10_jellyfish_topview);
		addPreferencesFromResource(R.xml.preferences_style_10_11_scene_rain);
		addPreferencesFromResource(R.xml.preferences_style_10_12_textoptions);
		addPreferencesFromResource(R.xml.preferences_style_10_13_3d_cube);
		addPreferencesFromResource(R.xml.preferences_style_10_14_circular_maze);
		addPreferencesFromResource(R.xml.preferences_style_20_dropshadow);
		addPreferencesFromResource(R.xml.preferences_style_30_glossy);
		addPreferencesFromResource(R.xml.preferences_style_40_rotating);
		addPreferencesFromResource(R.xml.preferences_style_50_outline);
		addPreferencesFromResource(R.xml.preferences_style_60_assorted_leafs);
		addPreferencesFromResource(R.xml.preferences_style_60_assorted_filled);
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
		cubeOptions = (PreferenceScreen) findPreference("cubeOptions");
		textOptions = (PreferenceScreen) findPreference("textOptions");
		leafOptions = (PreferenceScreen) findPreference("leafOptions");
		cMazeOptions = (PreferenceScreen) findPreference("cMazeOptions");
		filledOptions = (PreferenceScreen) findPreference("filledOptions");
		outlineScreen = (PreferenceScreen) findPreference("outlineScreen");
		glossyScreen = (PreferenceScreen) findPreference("glossyScreen");
		ratatingScreen = (PreferenceScreen) findPreference("ratatingScreen");
		dropShadowColor = findPreference(Settings.KEY_PATTERN_DROPSHADOW_COLOR);
		dropShadowDarkness = findPreference(Settings.KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST);
		dropShadowRadiusAdjustment = (InlineSeekBarPreference) findPreference("dropShadowRadiusAdjustment");
		dropShadowOffsetX = (InlineSeekBarPreference) findPreference(Settings.KEY_DROP_SHADOW_OFFSET_X);
		dropShadowOffsetY = (InlineSeekBarPreference) findPreference(Settings.KEY_DROP_SHADOW_OFFSET_Y);
		rotationDegrees = (InlineSeekBarPreference) findPreference("rotationDegrees");
		randomRange = (InlineSeekBarPreference) findPreference("randomRange");
		rotationCenterPointX = (InlineSeekBarPreference) findPreference("rotationCenterPointX");
		rotationCenterPointY = (InlineSeekBarPreference) findPreference("rotationCenterPointY");

		handlePatternSelect(Settings.getSelectedPattern());
		handlePatternVariantSelect(Settings.getSelectedPatternVariant());
		handleDropShadowTypeSelection(Settings.getDropShadowType());
		handleRotatingStyleSelected(Settings.getRotationStyle());
		setIcon(Settings.getSelectedPattern(), Settings.getSelectedPatternVariant());
		enableProFeatures();
	}

	private void handleRotatingStyleSelected(final String newValue) {
		rotationDegrees.setEnabled(!newValue.equals("Random"));
		randomRange.setEnabled(newValue.contains("(Range)"));
		rotationCenterPointX.setEnabled(newValue.equals("Around Adjustable Center"));
		rotationCenterPointY.setEnabled(newValue.equals("Around Adjustable Center"));
	}

	public void handleDropShadowTypeSelection(final DROP_SHADOW_TYPE type) {
		dropShadowColor.setEnabled(type.equals(DROP_SHADOW_TYPE.SELECT));
		dropShadowDarkness.setEnabled(type.equals(DROP_SHADOW_TYPE.DARKER));
		dropShadowRadiusAdjustment.setEnabled(!type.equals(DROP_SHADOW_TYPE.NO));
		dropShadowOffsetX.setEnabled(!type.equals(DROP_SHADOW_TYPE.NO));
		dropShadowOffsetY.setEnabled(!type.equals(DROP_SHADOW_TYPE.NO));

	}

	private void handlePatternSelect(final String newPattern) {
		patternSelection.setSummary(newPattern);
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

		// Special Pattern Settings enabel or disable
		addSpecialPreferences(newPattern, patternVariantSelection.getValue());
	}

	private void addSpecialPreferences(final String pattern, final String variant) {
		Log.i("GEITH", "addSpecialPreferences..." + pattern + "-" + variant);

		// Special Pattern Settings enabel or disable
		addOrRemoveScreen(jellyfishOptions, //
				pattern.equalsIgnoreCase("Jellyfish"));

		addOrRemoveScreen(jellyfishTopviewOptions, //
				pattern.equalsIgnoreCase("Jellyfish Topview") && variant.equals("Fully customizable"));

		addOrRemoveScreen(sceneRainOptions, //
				pattern.equalsIgnoreCase("Rain"));

		addOrRemoveScreen(cubeOptions, //
				pattern.equalsIgnoreCase("3D Objects"));

		addOrRemoveScreen(textOptions, //
				pattern.equalsIgnoreCase("Text"));

		// removing unused stuff
		addOrRemoveScreen(cMazeOptions, //
				pattern.equalsIgnoreCase("Circular Maze"));

		addOrRemoveScreen(leafOptions, //
				PatternPropertyStore.hasNumberOfLeafsOption(pattern));
		// removing unused stuff
		addOrRemoveScreen(filledOptions, //
				PatternPropertyStore.hasPatternFilledOption(pattern));

		addOrRemoveScreen(outlineScreen, //
				PatternPropertyStore.hasPatternOutlineEffect(pattern));

		addOrRemoveScreen(glossyScreen, //
				PatternPropertyStore.hasPatternGlossyEffect(pattern));
		addOrRemoveScreen(ratatingScreen, //
				PatternPropertyStore.hasPatternRandomRotate(pattern));

		// // some precausions
		// if (pattern.equalsIgnoreCase("Jellyfish Topview") && Settings.getAnzahlPatterns() > 300) {
		// Settings.prefs.edit().putInt(Settings.KEY_PATTERN_ANZAHL_PATTERNS, 300).commit();
		// }

	}

	private void addOrRemoveScreen(final Preference preference, final boolean visible) {
		if (visible) {
			getPreferenceScreen().addPreference(preference);
		} else {
			getPreferenceScreen().removePreference(preference);
		}
	}

	protected void handlePatternVariantSelect(final String newVariant) {
		Log.i("GEITH", "Setting PatternVariant..." + newVariant);
		patternVariantSelection.setSummary(newVariant);
		addSpecialPreferences(Settings.getSelectedPattern(), newVariant);
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
		final Bitmap drawIcon = PatternGetter.drawIconBitmap(128, pattern, variant);
		patternVariantSelection.setIcon(BitmapHelper.bitmapToIcon(drawIcon));
	}

}
