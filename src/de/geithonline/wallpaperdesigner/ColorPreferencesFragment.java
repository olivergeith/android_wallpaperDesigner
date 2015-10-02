package de.geithonline.wallpaperdesigner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import de.geithonline.android.basics.preferences.SeekBarPreference;
import de.geithonline.android.basics.preferences.colorpicker.ColorPickerPreference;
import de.geithonline.wallpaperdesigner.bitmapdrawer.BackgroundDrawer;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.DesignIO;
import de.geithonline.wallpaperdesigner.utils.BitmapHelper;
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
	// private IconOnlyPreference colorPreview;
	private Bitmap patternColorsBitmap;
	private Bitmap backgroundBitmap;
	private Preference loadColors;
	private SeekBarPreference maxOpacity;
	private CheckBoxPreference sameBackgroundAsPatternGradient;

	private List<String> keys = new ArrayList<String>();
	private ListPreference colorRandomizingType;

	@Override
	public void onDestroy() {
		if (patternColorsBitmap != null) {
			patternColorsBitmap.recycle();
		}
		if (backgroundBitmap != null) {
			backgroundBitmap.recycle();
		}
		super.onDestroy();
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		keys = new ArrayList<String>();
		keys.add(Settings.KEY_BGRND_COLOR1);
		keys.add(Settings.KEY_BGRND_COLOR2);
		keys.add(Settings.KEY_COLOR1);
		keys.add(Settings.KEY_COLOR2);
		keys.add(Settings.KEY_COLOR3);
		keys.add(Settings.KEY_COLOR4);
		keys.add(Settings.KEY_COLOR_GRADIENT_DIRECTION);
		keys.add(Settings.KEY_COLORS_ANZAHL);
		keys.add(Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_color);

		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		prefs.registerOnSharedPreferenceChangeListener(this);

		color1 = (ColorPickerPreference) findPreference(Settings.KEY_COLOR1);
		color2 = (ColorPickerPreference) findPreference(Settings.KEY_COLOR2);
		color3 = (ColorPickerPreference) findPreference(Settings.KEY_COLOR3);
		color4 = (ColorPickerPreference) findPreference(Settings.KEY_COLOR4);

		color1.setHexValueEnabled(Settings.isHexValueEnabled());
		color2.setHexValueEnabled(Settings.isHexValueEnabled());
		color3.setHexValueEnabled(Settings.isHexValueEnabled());
		color4.setHexValueEnabled(Settings.isHexValueEnabled());

		gradientDirection = (ListPreference) findPreference(Settings.KEY_COLOR_GRADIENT_DIRECTION);
		sameBackgroundAsPatternGradient = (CheckBoxPreference) findPreference(Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT);
		// colorPreview = (IconOnlyPreference) findPreference("colorPreview");

		anzColors = (ListPreference) findPreference(Settings.KEY_COLORS_ANZAHL);
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
		loadColors = findPreference("loadColors");
		loadColors.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.loadDesignTheFancyWay(getActivity(), Settings.prefs, true);
				return true;
			}
		});
		colorRandomizingType = (ListPreference) findPreference(Settings.KEY_COLOR_RANDOMIZING_TYPE);
		colorRandomizingType.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleColorRandomizingType((String) newValue);
				return true;
			}
		});

		maxOpacity = (SeekBarPreference) findPreference("maxOpacity");
		maxOpacity.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleMaxOpacityChange((int) newValue);
				return true;
			}

		});

		handleSelection(Settings.getGradientDirection(), Settings.getAnzahlGradientColors());
		drawPreviewImages();
		handleColorRandomizingType(Settings.getColorRandomizing());
	}

	protected void handleColorRandomizingType(final String newValue) {
		colorRandomizingType.setSummary(newValue);

	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		Log.i("ColorPreferenceFragment", "onPreference Change for " + key);
		if (keys.contains(key)) {
			Log.i("ColorPreferenceFragment", "drawing BackgroundIcon ");
			drawPreviewImages();
			handleSelection(Settings.getGradientDirection(), Settings.getAnzahlGradientColors());
		}
	}

	private void drawPreviewImages() {
		final int w = Settings.getWidth();
		final int h = Settings.getHeight();
		final Activity activity = getActivity();
		if (activity != null) {
			final int bWidth = DisplayHelper.getDisplayWidth(activity) / 10;
			final int bHeight = (bWidth * h) / w;
			patternColorsBitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
			final Canvas bitmapCanvas = new Canvas(patternColorsBitmap);
			BackgroundDrawer.drawBackground(bitmapCanvas, true);
			if (patternColorsBitmap != null) {
				gradientDirection.setIcon(BitmapHelper.bitmapToIcon(patternColorsBitmap));
				if (Settings.isSameGradientAsPatterns()) {
					sameBackgroundAsPatternGradient.setIcon(BitmapHelper.bitmapToIcon(patternColorsBitmap));
				} else {
					backgroundBitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
					final Canvas backgroundCanvas = new Canvas(backgroundBitmap);
					BackgroundDrawer.drawSimpleBackground(backgroundCanvas);
					if (backgroundBitmap != null) {
						sameBackgroundAsPatternGradient.setIcon(BitmapHelper.bitmapToIcon(backgroundBitmap));
					} else {
						Log.e("ColorPreferenceFragment", "drawing BackgroundIcon -Bitmap was null!!");
					}
				}
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

	private void handleMaxOpacityChange(final int newValue) {
		Log.i("Geith", "NewVal = " + newValue);
		if (newValue < Settings.prefs.getInt("minOpacity", 128)) {
			Settings.prefs.edit().putInt("minOpacity", newValue).commit();

		}
	}

}
