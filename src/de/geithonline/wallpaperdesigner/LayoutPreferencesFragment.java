package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.util.Log;
import de.geithonline.android.basics.preferences.SeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class LayoutPreferencesFragment extends PreferenceFragment {

	private ListPreference mainlayouts;
	private ListPreference mainlayoutVariants;
	private ListPreference limit2Canvas;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_layout);
		mainlayouts = (ListPreference) findPreference("mainlayouts");
		mainlayoutVariants = (ListPreference) findPreference("mainlayoutVariants");
		limit2Canvas = (ListPreference) findPreference("limit2Canvas");

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

		limit2Canvas.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleLimit2Canvas((String) newValue);
				return true;
			}
		});

		handleLimit2Canvas(Settings.getCanvasLimit());
		handleMainLayoutSelect(Settings.getSelectedMainLayout());
		handleMainLayoutVariantSelect(Settings.getSelectedMainLayoutVariante());
	}

	protected void handleLimit2Canvas(final String newValue) {
		limit2Canvas.setSummary(newValue);

	}

	private void handleMainLayoutSelect(final String selectedLayout) {
		mainlayouts.setSummary(selectedLayout);
		final SeekBarPreference overlapping = (SeekBarPreference) findPreference("overlapping");
		final SeekBarPreference centerPointX = (SeekBarPreference) findPreference("centerPointX");
		final SeekBarPreference centerPointY = (SeekBarPreference) findPreference("centerPointY");
		final SeekBarPreference anzahlPatterns = (SeekBarPreference) findPreference("anzahlPatterns");
		final CheckBoxPreference blurring = (CheckBoxPreference) findPreference("blurPatterns");
		final CheckBoxPreference upsideDown = (CheckBoxPreference) findPreference("upsideDown");
		final CheckBoxPreference randomStartWinkel = (CheckBoxPreference) findPreference("ramdomStartWinkel");
		overlapping.setEnabled(RasterFactory.hasLayoutOverlap(selectedLayout));
		anzahlPatterns.setEnabled(RasterFactory.hasLayoutAnzahlPattern(selectedLayout));
		blurring.setEnabled(RasterFactory.hasLayoutBlurring(selectedLayout));
		upsideDown.setEnabled(RasterFactory.hasLayoutUpsideDown(selectedLayout));
		randomStartWinkel.setEnabled(RasterFactory.hasLayoutRandomStartwinkel(selectedLayout));

		centerPointX.setEnabled(RasterFactory.hasLayoutAdjustableCenter(selectedLayout));
		centerPointY.setEnabled(RasterFactory.hasLayoutAdjustableCenter(selectedLayout));

		// Pattern Variants
		mainlayoutVariants.setEnabled(RasterFactory.hasLayoutVariants(selectedLayout));

		if (RasterFactory.hasLayoutVariants(selectedLayout)) {
			Log.i("Layout", "Setting Layout Variants...");
			final CharSequence[] variants = RasterFactory.getLayoutVariants(selectedLayout);
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
			Log.i("Layout", "Setting Layout Variants to not avialable...");
			mainlayoutVariants.setEntries(null);
			mainlayoutVariants.setEntryValues(null);
			mainlayoutVariants.setSummary("not available");
		}

	}

	private void handleMainLayoutVariantSelect(final String variante) {
		mainlayoutVariants.setSummary(variante);
	}

}
