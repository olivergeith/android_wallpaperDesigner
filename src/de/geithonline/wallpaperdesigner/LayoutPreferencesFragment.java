
package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;
import de.geithonline.android.basics.preferences.InlineSeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.ELayout;
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
		handleMainLayoutSelect(Settings.getSelectedMainLayoutString());
		handleMainLayoutVariantSelect(Settings.getSelectedMainLayoutVarianteString());
	}

	protected void handleLimit2Canvas(final String newValue) {
		limit2Canvas.setSummary(newValue);

	}

	private void handleMainLayoutSelect(final String string) {
		mainlayouts.setSummary(string);
		final ELayout enumForName = ELayout.getEnumForName(string);

		addOrRemoveFromMainScreen(overlapping, enumForName.hasOverlap());
		addOrRemoveFromMainScreen(anzahlPatterns, enumForName.hasAnzahlPatterns());
		addOrRemoveFromMainScreen(blurring, enumForName.hasBlurring());
		addOrRemoveFromMainScreen(counterClockwise, enumForName.hasCounterClockwise());
		addOrRemoveFromMainScreen(randomStartWinkel, enumForName.hasRandomStartWinkel());
		addOrRemoveFromMainScreen(centerPointX, enumForName.hasAdjustableCenter());
		addOrRemoveFromMainScreen(centerPointY, enumForName.hasAdjustableCenter());
		addOrRemoveFromMainScreen(mainlayoutVariants, enumForName.hasVariants());

		if (enumForName.hasVariants()) {
			Log.i("Layout", "Setting Layout Variants...");
			final CharSequence[] variants = enumForName.getVariants();
			mainlayoutVariants.setEntries(variants);
			mainlayoutVariants.setEntryValues(variants);
			if (!string.equals(mainlayouts.getValue())) {
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

	private void handleMainLayoutVariantSelect(final String string) {
		mainlayoutVariants.setSummary(string);
	}

}
