
package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;
import de.geithonline.android.basics.preferences.InlineSeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class LayoutPreferencesFragment extends AbstractPreferenceFragment {

	private ListPreference mainlayouts;
	private ListPreference mainlayoutVariants;
	private ListPreference limit2Canvas;
	private InlineSeekBarPreference overlapping;
	private InlineSeekBarPreference centerPointX;
	private InlineSeekBarPreference centerPointY;
	private InlineSeekBarPreference anzahlPatterns;
	private CheckBoxPreference blurring;
	private CheckBoxPreference counterClockwise;
	private CheckBoxPreference randomStartWinkel;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_layout);
		addPreferencesFromResource(R.xml.preferences_layout_blurring);
		mainlayouts = (ListPreference) findPreference("mainlayouts");
		mainlayoutVariants = (ListPreference) findPreference("mainlayoutVariants");
		limit2Canvas = (ListPreference) findPreference("limit2Canvas");
		overlapping = (InlineSeekBarPreference) findPreference("overlapping");
		centerPointX = (InlineSeekBarPreference) findPreference("centerPointX");
		centerPointY = (InlineSeekBarPreference) findPreference("centerPointY");
		anzahlPatterns = (InlineSeekBarPreference) findPreference("anzahlPatterns");
		blurring = (CheckBoxPreference) findPreference("blurPatterns");
		counterClockwise = (CheckBoxPreference) findPreference(Settings.KEY_COUNTER_CLOCKWISE);
		randomStartWinkel = (CheckBoxPreference) findPreference("ramdomStartWinkel");

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

		handleLimit2Canvas(Settings.getCanvasLimitString());
		handleMainLayoutSelect(Settings.getSelectedMainLayout());
		handleMainLayoutVariantSelect(Settings.getSelectedMainLayoutVariante());
	}

	protected void handleLimit2Canvas(final String newValue) {
		limit2Canvas.setSummary(newValue);

	}

	private void handleMainLayoutSelect(final String selectedLayout) {
		mainlayouts.setSummary(selectedLayout);

		addOrRemoveFromMainScreen(overlapping, RasterFactory.hasLayoutOverlap(selectedLayout));
		addOrRemoveFromMainScreen(anzahlPatterns, RasterFactory.hasLayoutAnzahlPattern(selectedLayout));
		addOrRemoveFromMainScreen(blurring, RasterFactory.hasLayoutBlurring(selectedLayout));
		addOrRemoveFromMainScreen(counterClockwise, RasterFactory.hasCounterClockwise(selectedLayout));
		addOrRemoveFromMainScreen(randomStartWinkel, RasterFactory.hasLayoutRandomStartwinkel(selectedLayout));
		addOrRemoveFromMainScreen(centerPointX, RasterFactory.hasLayoutAdjustableCenter(selectedLayout));
		addOrRemoveFromMainScreen(centerPointY, RasterFactory.hasLayoutAdjustableCenter(selectedLayout));
		addOrRemoveFromMainScreen(mainlayoutVariants, RasterFactory.hasLayoutVariants(selectedLayout));

		if (RasterFactory.hasLayoutVariants(selectedLayout)) {
			Log.i("Layout", "Setting Layout Variants...");
			final CharSequence[] variants = RasterFactory.getLayoutVariants(selectedLayout);
			mainlayoutVariants.setEntries(variants);
			mainlayoutVariants.setEntryValues(variants);
			if (!selectedLayout.equals(mainlayouts.getValue())) {
				mainlayoutVariants.setValueIndex(0);
				mainlayoutVariants.setDefaultValue(mainlayoutVariants.getValue());
			}
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
