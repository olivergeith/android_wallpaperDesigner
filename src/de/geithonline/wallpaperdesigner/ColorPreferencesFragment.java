package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import de.geithonline.android.basics.preferences.IconOnlyPreference;
import de.geithonline.android.basics.preferences.colorpicker.ColorPickerPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.BackgroundDrawer;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.DisplayHelper;

/**
 * This fragment shows the preferences for the first header.
 */
public class ColorPreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	private ListPreference gradientDirection;
	private ListPreference anzColors;
	private ColorPickerPreference color1;
	private ColorPickerPreference color2;
	private ColorPickerPreference color3;
	private ColorPickerPreference color4;
	private IconOnlyPreference colorPreview;
	private Bitmap bitmap;

	@Override
	public void onDestroy() {
		if (bitmap != null) {
			bitmap.recycle();
		}
		super.onDestroy();
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_color);

		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity()
				.getApplicationContext());
		prefs.registerOnSharedPreferenceChangeListener(this);

		color1 = (ColorPickerPreference) findPreference("color_plain_bgrnd");
		color2 = (ColorPickerPreference) findPreference("color2_plain_bgrnd");
		color3 = (ColorPickerPreference) findPreference("color3_plain_bgrnd");
		color4 = (ColorPickerPreference) findPreference("color4_plain_bgrnd");

		color1.setHexValueEnabled(Settings.isHexValueEnabled());
		color2.setHexValueEnabled(Settings.isHexValueEnabled());
		color3.setHexValueEnabled(Settings.isHexValueEnabled());
		color4.setHexValueEnabled(Settings.isHexValueEnabled());

		gradientDirection = (ListPreference) findPreference("gradientDirection");
		colorPreview = (IconOnlyPreference) findPreference("colorPreview");

		anzColors = (ListPreference) findPreference("anzColors");
		anzColors.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				final int anz = Integer.parseInt((String) newValue);
				handleSelection(Settings.getGradientDirection(), anz);
				return true;
			}
		});
		gradientDirection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleSelection((String) newValue, Settings.getAnzahlGradientColors());
				return true;
			}
		});
		handleSelection(Settings.getGradientDirection(), Settings.getAnzahlGradientColors());
		drawBackGroundImage();
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		Log.i("ColorPreferenceFragment", "onPreference Change for " + key);
		drawBackGroundImage();
	}

	private void drawBackGroundImage() {
		Log.i("ColorPreferenceFragment", "drawing BackgroundIcon ");
		final int w = Settings.getBWidth();
		final int h = Settings.getBHeight();
		final int bWidth = DisplayHelper.getDisplayWidth(getActivity()) / 2;
		final int bHeight = (bWidth * h) / w;
		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		final Canvas bitmapCanvas = new Canvas(bitmap);
		BackgroundDrawer.drawBackground(bitmapCanvas);
		colorPreview.setTitle("Preview");
		// final Drawable icon = BitmapHelper.bitmapToIcon(bitmap);
		if (bitmap != null) {
			colorPreview.setImage(bitmap);
		} else {
			Log.i("ColorPreferenceFragment", "drawing BackgroundIcon -Bitmap was null!!");
		}
	}

	private void handleSelection(final String selection, final int anzahl) {
		gradientDirection.setSummary(selection);
		anzColors.setEnabled(!Settings.is4ColorGradient(selection));
		anzColors.setSummary("" + anzahl);
		if (Settings.is4ColorGradient(selection)) {
			enableColors(4);
			anzColors.setSummary("" + 4);
		} else {
			enableColors(anzahl);
		}
	}

	private void enableColors(final int anzahl) {
		switch (anzahl) {

		case 2:
			color3.setEnabled(false);
			color4.setEnabled(false);
			break;
		case 3:
			color3.setEnabled(true);
			color4.setEnabled(false);
			break;
		default:
		case 4:
			color3.setEnabled(true);
			color4.setEnabled(true);
			break;
		}
	}

}
