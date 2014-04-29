package de.geithonline.wallpaperdesigner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import de.geithonline.android.basics.utils.BitmapHelper;
import de.geithonline.android.basics.utils.Toaster;
import de.geithonline.android.basics.utils.URIHelper;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBitmapDrawer;
import de.geithonline.wallpaperdesigner.settings.DrawerManager;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class BattPreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
	private final int PICK_LOGO = 3;
	public static final String STYLE_PICKER_KEY = "batt_style";
	private ListPreference stylePref;
	private Preference logoPicker;
	private Preference maskPicker;
	private Preference hueSlider;
	private Preference bgBrightnessSlider;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_style);

		Settings.prefs.registerOnSharedPreferenceChangeListener(this);

		// initializing Members
		stylePref = (ListPreference) findPreference(STYLE_PICKER_KEY);
		logoPicker = findPreference("logoPicker");
		maskPicker = findPreference("maskList");
		hueSlider = findPreference("logo_hue");
		bgBrightnessSlider = findPreference("logo_background_brightness");

		logoPicker.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Select Logo Picture"), PICK_LOGO);
				return true;
			}
		});

		setMaskPickerData();
		setLogoPickerData();
		enableSettingsForStyle(Settings.getStyle());
		enableProFeatures();
	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode, final Intent resultData) {
		super.onActivityResult(requestCode, resultCode, resultData);
		if (resultData == null) {
			Log.e(this.getClass().getSimpleName(), "onActivityResult: Data Recieved was null !!");
			return;
		}
		Log.i(this.getClass().getSimpleName(), "onActivityResult: Data Recieved: " + resultData.toString());

		if (resultCode != Activity.RESULT_OK) {
			Log.i(this.getClass().getSimpleName(), "No ImagePath Received -> Cancel");
			return;
		}
		if (requestCode != PICK_LOGO) {
			Log.i(this.getClass().getSimpleName(), "No ImagePath Received -> RequestCode wrong...: " + requestCode);
			return;
		}

		final Uri selectedImage = resultData.getData();

		// Pfad zum Image suchen
		final String filePath = URIHelper.getPath(getActivity().getApplicationContext(), selectedImage);
		Log.i(this.getClass().getSimpleName(), "ImagePath Received via URIHelper! " + filePath);

		// und in die SharedPreferences schreiben
		final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

		if (sharedPref == null) {
			Log.e(this.getClass().getSimpleName(), "SharedPreferences were null!!");
			Toaster.showErrorToast(
					getActivity(),
					"Could not save imagepath "
							+ filePath
							+ "Sharedfreferences not found!!! (null). Make sure you set the Wallpaper at least once before editing preferences of it (SystemSettings->Display->Wallpaper->LiveWallpaper->Choose BatteryLWP and set it!");
			return;
		}
		final SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString("logoPicker", filePath);
		Log.i(this.getClass().getSimpleName(), "ImagePath written to preferences: " + filePath);
		editor.commit();
		if (Settings.isDebuggingMessages()) {
			Toaster.showInfoToast(getActivity(), "SetBG to " + filePath);
		}

		// Summaries usw updaten
		setLogoPickerData();
	}

	private void enableProFeatures() {
		final Preference bgBrightness = findPreference("logo_background_brightness");
		bgBrightness.setEnabled(Settings.isPremium());
		if (!Settings.isPremium()) {
			Settings.prefs.edit().putFloat("logo_background_brightness", 1.0f).commit();
		}
	}

	private void enableSettingsForStyle(final String style) {
		final Bitmap b = DrawerManager.getIconForDrawer(style, Settings.getIconSize());
		// Find a Drawer for this Style
		final IBitmapDrawer drawer = DrawerManager.getDrawer(style);
		final Preference zeiger = findPreference("show_zeiger");
		final Preference rand = findPreference("show_rand");
		final Preference colorZeiger = findPreference("color_zeiger");
		final Preference customLogo = findPreference("customLogo");
		if (b != null) {
			stylePref.setIcon(BitmapHelper.bitmapToIcon(b));
		}
		customLogo.setEnabled(drawer.supportsLogo());
		logoPicker.setEnabled(drawer.supportsLogo());
		zeiger.setEnabled(drawer.supportsShowPointer());
		rand.setEnabled(drawer.supportsShowRand());
		colorZeiger.setEnabled(drawer.supportsPointerColor());
		stylePref.setSummary("Current style: " + style);
	}

	private void setLogoPickerData() {
		final Bitmap b = Settings.getCustomLogoSampled(128, 128);
		if (b != null) {
			final int hue = Math.round(Settings.getLogoHue() * 360) - 180;
			final Bitmap huedBitmap = BitmapHelper.getColorFilteredBitmap(b, 0, 0, 0, hue);
			final int brightness = Math.round(Settings.getLogoBackgroundBrightness() * 200) - 200;
			final Bitmap grayBitmap = BitmapHelper.getColorFilteredBitmap(b, brightness, 0, -100, 0);
			final Drawable orgdr = BitmapHelper.resizeToIcon128(b);
			final Drawable huedr = BitmapHelper.resizeToIcon128(huedBitmap);
			final Drawable graydr = BitmapHelper.resizeToIcon128(grayBitmap);
			logoPicker.setIcon(orgdr);
			hueSlider.setIcon(huedr);
			bgBrightnessSlider.setIcon(graydr);
		}
	}

	private void setMaskPickerData() {
		final String maske = Settings.getMaskName();
		if (maske != null) {
			final Bitmap b = Settings.getLogoMaskIconCached(maske, 128, 128);
			final Drawable dr = BitmapHelper.resizeToIcon128(b);
			maskPicker.setSummary("Mask: " + maske);
			maskPicker.setIcon(dr);
		}
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		switch (key) {
		case "logo_background_brightness":
		case "customLogo":
		case "logo_hue":
			setLogoPickerData();
			break;
		case "maskList":
			setMaskPickerData();
			break;
		case "batt_style":
			enableSettingsForStyle(Settings.getStyle());
			break;
		default:
			break;
		}

	}
}
