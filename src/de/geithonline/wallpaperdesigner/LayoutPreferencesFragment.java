
package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;
import de.geithonline.android.basics.preferences.InlineSeekBarPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.ELayout;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.ELayoutVariant;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class LayoutPreferencesFragment extends AbstractPreferenceFragment {

	private ListPreference layouts;
	private ListPreference layoutVariants;
	private ListPreference limit2Canvas;
	private InlineSeekBarPreference overlapping;
	private InlineSeekBarPreference centerPointX;
	private InlineSeekBarPreference centerPointY;
	private InlineSeekBarPreference anzahlPatterns;
	private CheckBoxPreference blurring;
	private CheckBoxPreference counterClockwise;
	private CheckBoxPreference randomStartWinkel;
	private ListPreference layoutSubVariants;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_layout);
		addPreferencesFromResource(R.xml.preferences_layout_blurring);
		layouts = (ListPreference) findPreference("mainlayouts");
		layoutVariants = (ListPreference) findPreference("mainlayoutVariants");
		layoutSubVariants = (ListPreference) findPreference("mainlayoutSubVariants");
		limit2Canvas = (ListPreference) findPreference("limit2Canvas");
		overlapping = (InlineSeekBarPreference) findPreference("overlapping");
		centerPointX = (InlineSeekBarPreference) findPreference("centerPointX");
		centerPointY = (InlineSeekBarPreference) findPreference("centerPointY");
		anzahlPatterns = (InlineSeekBarPreference) findPreference("anzahlPatterns");
		blurring = (CheckBoxPreference) findPreference("blurPatterns");
		counterClockwise = (CheckBoxPreference) findPreference(Settings.KEY_COUNTER_CLOCKWISE);
		randomStartWinkel = (CheckBoxPreference) findPreference("ramdomStartWinkel");

		layouts.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleLayoutSelect((String) newValue);
				return true;
			}
		});
		layoutVariants.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleLayoutVariantSelect((String) newValue, false);
				return true;
			}
		});

		layoutSubVariants.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleMainLayoutSubVariantSelect((String) newValue);
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
		handleLayoutSelect(Settings.getSelectedMainLayoutString());
		handleLayoutVariantSelect(Settings.getSelectedMainLayoutVarianteString(), false);
		handleMainLayoutSubVariantSelect(Settings.getSelectedMainLayoutSubVarianteString());
	}

	protected void handleLimit2Canvas(final String newValue) {
		limit2Canvas.setSummary(newValue);

	}

	private void handleLayoutSelect(final String newLayout) {
		layouts.setSummary(newLayout);
		final ELayout enumLayout = ELayout.getEnumForName(newLayout);

		addOrRemoveFromMainScreen(overlapping, enumLayout.hasOverlap());
		addOrRemoveFromMainScreen(anzahlPatterns, enumLayout.hasAnzahlPatterns());
		addOrRemoveFromMainScreen(blurring, enumLayout.hasBlurring());
		addOrRemoveFromMainScreen(counterClockwise, enumLayout.hasCounterClockwise());
		addOrRemoveFromMainScreen(randomStartWinkel, enumLayout.hasRandomStartWinkel());
		addOrRemoveFromMainScreen(centerPointX, enumLayout.hasAdjustableCenter());
		addOrRemoveFromMainScreen(centerPointY, enumLayout.hasAdjustableCenter());
		// addOrRemoveFromMainScreen(layoutVariants, enumForName.hasVariants());

		Log.i("Layout", "Setting Layout Variants...");
		final CharSequence[] variants = enumLayout.getVariants();
		layoutVariants.setEntries(variants);
		layoutVariants.setEntryValues(variants);
		// den ersten Eintrag in den Varianten wählen, wenn ein neues layout (ungleich dem alten) ausgewählt wurde
		if (!newLayout.equals(layouts.getValue())) {
			layoutVariants.setValueIndex(0);
			layoutVariants.setDefaultValue(layoutVariants.getValue());
			handleLayoutVariantSelect(layoutVariants.getValue(), true);
		}
		handleLayoutVariantSelect(layoutVariants.getValue(), false);
	}

	private void handleLayoutVariantSelect(final String newLayoutvariante, final boolean forcereset) {
		layoutVariants.setSummary(newLayoutvariante);

		final ELayoutVariant enumLayoutVariante = ELayoutVariant.getEnumForName(newLayoutvariante);
		if (enumLayoutVariante.hasSubVariants()) {
			Log.i("Layout", "Setting Layout SubVariants...");
			final CharSequence[] subVariants = enumLayoutVariante.getSubVariants();
			layoutSubVariants.setEntries(subVariants);
			layoutSubVariants.setEntryValues(subVariants);
			if (!newLayoutvariante.equals(layoutVariants.getValue()) || forcereset) {
				layoutSubVariants.setValueIndex(0);
				layoutSubVariants.setDefaultValue(layoutSubVariants.getValue());
			}
			handleMainLayoutSubVariantSelect(layoutSubVariants.getValue());
		}
	}

	private void handleMainLayoutSubVariantSelect(final String string) {
		layoutSubVariants.setSummary(string);

	}

}
