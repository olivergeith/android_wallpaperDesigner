package de.geithonline.wallpaperdesigner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import de.geithonline.android.basics.preferences.IconOnlyPreference;
import de.geithonline.android.basics.preferences.colorpicker.ColorPickerPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.BackgroundDrawer;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;
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
	private Preference loadSettings;

	private List<String> keys = new ArrayList<String>();

	@Override
	public void onDestroy() {
		if (bitmap != null) {
			bitmap.recycle();
		}
		super.onDestroy();
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		keys = new ArrayList<String>();
		keys.add(Settings.KEY_COLOR1);
		keys.add(Settings.KEY_COLOR2);
		keys.add(Settings.KEY_COLOR3);
		keys.add(Settings.KEY_COLOR4);
		keys.add(Settings.KEY_COLOR_GRADIENT_DIRECTION);
		keys.add(Settings.KEY_COLORS_ANZAHL);

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
		loadSettings = findPreference("loadSettings");
		loadSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.loadDesignTheFancyWay(getActivity(), Settings.prefs, true);
				return true;
			}
		});

		handleSelection(Settings.getGradientDirection(), Settings.getAnzahlGradientColors());
		drawBackGroundImage();
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		Log.i("ColorPreferenceFragment", "onPreference Change for " + key);
		if (keys.contains(key)) {
			Log.i("ColorPreferenceFragment", "drawing BackgroundIcon ");
			drawBackGroundImage();
			handleSelection(Settings.getGradientDirection(), Settings.getAnzahlGradientColors());
		}
	}

	private void drawBackGroundImage() {
		final int w = Settings.getBWidth();
		final int h = Settings.getBHeight();
		final Activity activity = getActivity();
		if (activity != null) {
			final int bWidth = DisplayHelper.getDisplayWidth(activity) / 2;
			final int bHeight = (bWidth * h) / w;
			bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
			final Canvas bitmapCanvas = new Canvas(bitmap);
			BackgroundDrawer.drawBackground(bitmapCanvas, true);
			colorPreview.setTitle("Preview");
			// final Drawable icon = BitmapHelper.bitmapToIcon(bitmap);
			if (bitmap != null) {
				colorPreview.setImage(bitmap);
			} else {
				Log.e("ColorPreferenceFragment", "drawing BackgroundIcon -Bitmap was null!!");
			}
		} else {
			Log.w("ColorPreferenceFragment", "not drawing BackgroundIcon --> Activity was null ");
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
		color1.update();
		color2.update();
		color3.update();
		color4.update();
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
