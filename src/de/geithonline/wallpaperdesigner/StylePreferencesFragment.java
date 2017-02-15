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
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.PatternPropertyStore;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class StylePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    private ListPreference reflectionStyle;
    private ListPreference patternSelection;
    private ListPreference patternVariantSelection;
    private ListPreference dropShadowType;
    private ListPreference filledOption;
    private EditTextPreference textPattern;
    private ListPreference textDrawStyle;
    private SeekBarPreference numberOfLeafs;
    private SeekBarPreference rotationDegrees;
    private CheckBoxPreference randomLeafCount;
    private ListPreference rotatingStyle;
    private ListPreference glowStyle;
    private SeekBarPreference randomRange;
    private SeekBarPreference rotationCenterPointX;
    private SeekBarPreference rotationCenterPointY;
    private ListPreference radiusType;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_style);

        Settings.prefs.registerOnSharedPreferenceChangeListener(this);
        reflectionStyle = (ListPreference) findPreference(Settings.KEY_GLOSSY_REFLECTION_STYLE);
        glowStyle = (ListPreference) findPreference(Settings.KEY_GLOSSY_GLOW_STYLE);
        patternSelection = (ListPreference) findPreference(Settings.KEY_PATTERN_PATTERN_PICKER);
        patternVariantSelection = (ListPreference) findPreference(Settings.KEY_PATTERN_PATTERN_VARIANT_PICKER);
        filledOption = (ListPreference) findPreference(Settings.KEY_PATTERN_FILLED_OPTION);
        textDrawStyle = (ListPreference) findPreference(Settings.KEY_PATTERN_TEXT_DRAW_STYLE);
        dropShadowType = (ListPreference) findPreference(Settings.KEY_PATTERN_DROPSHADOW_TYPE);
        textPattern = (EditTextPreference) findPreference(Settings.KEY_PATTERN_TEXT);
        radiusType = (ListPreference) findPreference(Settings.KEY_RADIUS_TYPE);

        numberOfLeafs = (SeekBarPreference) findPreference(Settings.KEY_NUMBER_OF_LEAFS);
        randomLeafCount = (CheckBoxPreference) findPreference(Settings.KEY_RANDOM_LEAF_COUNT);
        rotationDegrees = (SeekBarPreference) findPreference("rotationDegrees");
        randomRange = (SeekBarPreference) findPreference("randomRange");
        rotatingStyle = (ListPreference) findPreference("rotatingStyle");
        rotationCenterPointX = (SeekBarPreference) findPreference("rotationCenterPointX");
        rotationCenterPointY = (SeekBarPreference) findPreference("rotationCenterPointY");

        textPattern.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handlePatternTextChanged((String) newValue);
                return true;
            }

        });

        reflectionStyle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handleReflectionStyleSelect((String) newValue);
                return true;
            }
        });

        glowStyle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handleGlowStyleSelect((String) newValue);
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
        radiusType.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handleRadiusTypeSelection((String) newValue);
                return true;
            }

        });

        handleGlowStyleSelect(Settings.getGlossyGlowStyleString());
        handleReflectionStyleSelect(Settings.getGlossyReflectionStyleString());
        handlePatternSelect(Settings.getSelectedPattern());
        handleFilledOptionSelected(Settings.getFilledOption());
        handleDropShadowTypeSelection(Settings.getDropShadowType());
        handleRadiusTypeSelection(Settings.getRadiusTypeString());
        handlePatternTextChanged(Settings.getText());
        handleTextDrawStyleSelected(Settings.getTextDrawStyle());
        handleRotatingStyleSelected(Settings.getRotationStyle());
        enableProFeatures();
    }

    private void handleRotatingStyleSelected(final String newValue) {
        rotatingStyle.setSummary(newValue);
        rotationDegrees.setEnabled(!newValue.equals("Random"));
        randomRange.setEnabled(newValue.contains("(Range)"));
        rotationCenterPointX.setEnabled(newValue.equals("Around Adjustable Center"));
        rotationCenterPointY.setEnabled(newValue.equals("Around Adjustable Center"));
    }

    protected void handleTextDrawStyleSelected(final String newValue) {
        textDrawStyle.setSummary(newValue);
    }

    protected void handleReflectionStyleSelect(final String newValue) {
        reflectionStyle.setSummary(newValue);
    }

    protected void handleGlowStyleSelect(final String newValue) {
        glowStyle.setSummary(newValue);
    }

    private void handlePatternTextChanged(final String newValue) {
        textPattern.setSummary(newValue);
    }

    private void handleFilledOptionSelected(final String value) {
        filledOption.setSummary(value);
    }

    public void handleRadiusTypeSelection(final String newValue) {
        radiusType.setSummary(newValue);
    }

    public void handleDropShadowTypeSelection(final String newValue) {
        dropShadowType.setSummary(newValue);
        final Preference dropShadowColor = findPreference(Settings.KEY_PATTERN_DROPSHADOW_COLOR);
        dropShadowColor.setEnabled(newValue.equals("Select"));
        final Preference dropShadowDarkness = findPreference(Settings.KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST);
        dropShadowDarkness.setEnabled(newValue.equals("Darker"));
        final SeekBarPreference dropShadowRadiusAdjustment = (SeekBarPreference) findPreference(
                        "dropShadowRadiusAdjustment");
        dropShadowRadiusAdjustment.setEnabled(!newValue.equals("No"));
        final SeekBarPreference dropShadowOffsetX = (SeekBarPreference) findPreference(
                        Settings.KEY_DROP_SHADOW_OFFSET_X);
        dropShadowOffsetX.setEnabled(!newValue.equals("No"));
        final SeekBarPreference dropShadowOffsetY = (SeekBarPreference) findPreference(
                        Settings.KEY_DROP_SHADOW_OFFSET_Y);
        dropShadowOffsetY.setEnabled(!newValue.equals("No"));

    }

    private void handlePatternSelect(final String newPattern) {
        patternSelection.setSummary(newPattern);
        // final CheckBoxPreference glossy = (CheckBoxPreference)
        // findPreference(Settings.KEY_PATTERN_GLOSSY);
        // final CheckBoxPreference outline = (CheckBoxPreference)
        // findPreference(Settings.KEY_PATTERN_OUTLINE);
        // final CheckBoxPreference outlineneverTransparent =
        // (CheckBoxPreference)
        // findPreference(Settings.KEY_PATTERN_OUTLINE_NEVER_TRANSPARENT);
        // glossy.setEnabled(PatternPropertyStore.hasPatternGlossyEffect(newPattern));
        // outline.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
        // outlineneverTransparent.setEnabled(PatternPropertyStore.hasPatternOutlineEffect(newPattern));
        // rotationDegrees.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
        // rotationDegrees.setEnabled(Settings.getRotationStyle().equals("Fixed"));
        // rotatingStyle.setEnabled(PatternPropertyStore.hasPatternRandomRotate(newPattern));
        filledOption.setEnabled(PatternPropertyStore.hasPatternFilledOption(newPattern));
        textPattern.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
        textDrawStyle.setEnabled(PatternPropertyStore.hasPatternTextOption(newPattern));
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

}
