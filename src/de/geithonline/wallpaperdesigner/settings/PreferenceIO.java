package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;

public class PreferenceIO {

	private static final String LOG_TAG = "PreferenceIO";
	public static final String MARKER = " (+++)_";
	private static List<String> ignoreKeys = new ArrayList<String>();

	private static List<String> colorKeys = new ArrayList<String>();

	static {
		ignoreKeys.add(Settings.KEY_MUIMERP);
		ignoreKeys.add(Settings.KEY_SORT_ORDER);
		ignoreKeys.add(Settings.KEY_IMAGE_FORMAT);
		ignoreKeys.add(Settings.KEY_JPG_COMPRESSION);
		ignoreKeys.add(Settings.KEY_HEX_VALUES);
		ignoreKeys.add(Settings.KEY_SHOW_SET_WALLPAPER_BUTTON);
		ignoreKeys.add(Settings.KEY_SHARE_SUBJECT);
		ignoreKeys.add(Settings.KEY_SHARE_TEXT);
		ignoreKeys.add(Settings.KEY_APP_THEME);
		ignoreKeys.add("debug");

		colorKeys.add(Settings.KEY_COLOR1);
		colorKeys.add(Settings.KEY_COLOR2);
		colorKeys.add(Settings.KEY_COLOR3);
		colorKeys.add(Settings.KEY_COLOR4);
		colorKeys.add(Settings.KEY_COLOR_GRADIENT_DIRECTION);
		colorKeys.add(Settings.KEY_COLORS_ANZAHL);
		colorKeys.add(Settings.KEY_RANDOMIZE_COLOR_RANGE_INT);
		colorKeys.add(Settings.KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT);
	}

	/**
	 * @param activity
	 * @param prefs
	 * @param filename
	 * @param onlyColors
	 *            when true only colors are loaded
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, ?> loadPreferencesFromFile(final Activity activity, final SharedPreferences prefs, final String filename, final boolean onlyColors) {
		Log.i(LOG_TAG, "Loading Settings from " + filename);
		final File file = new File(getSettingsDir(), filename);
		if (file.exists()) {
			try {
				// Reading file
				final FileInputStream fi = new FileInputStream(file);
				final ObjectInputStream o = new ObjectInputStream(fi);
				final Map<String, ?> settings = (Map<String, ?>) o.readObject();
				o.close();
				fi.close();
				// Looping over Map
				final Set<String> keySet = settings.keySet();

				for (final Map.Entry<String, ?> entry : settings.entrySet()) {
					if (onlyColors) {
						if (colorKeys.contains(entry.getKey())) {
							writeEntry(entry, prefs, keySet);
						}
					} else {
						writeEntry(entry, prefs, keySet);
					}
				}
				// Spezialbehandlung für alte Designs, die diese Keys noch nicht enthalten
				// und auch nur wenn nicht only Colors
				if (!onlyColors) {
					if (!keySet.contains(Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT)) {
						Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT);
						prefs.edit().putBoolean(Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT, true).commit();
					}
					if (!keySet.contains(Settings.KEY_DROP_SHADOW_OFFSET_X)) {
						Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_DROP_SHADOW_OFFSET_X);
						prefs.edit().putInt(Settings.KEY_DROP_SHADOW_OFFSET_X, 0).commit();
						prefs.edit().putInt(Settings.KEY_DROP_SHADOW_OFFSET_Y, 0).commit();
					}
					if (!keySet.contains(Settings.KEY_GLOSSY_REFLECTION_STYLE)) {
						Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_GLOSSY_REFLECTION_STYLE);
						prefs.edit().putString(Settings.KEY_GLOSSY_REFLECTION_STYLE, "Diagonal").commit();
					}
					if (!keySet.contains(Settings.KEY_GLOSSY_GLOW_STYLE)) {
						Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_GLOSSY_GLOW_STYLE);
						prefs.edit().putString(Settings.KEY_GLOSSY_GLOW_STYLE, "Center").commit();
					}
					if (!keySet.contains(Settings.KEY_OUTLINE_THICKNESS_ADJUST)) {
						Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_OUTLINE_THICKNESS_ADJUST);
						prefs.edit().putInt(Settings.KEY_OUTLINE_THICKNESS_ADJUST, 100).commit();
					}
					if (!keySet.contains(Settings.KEY_OUTLINE_THICKNESS_LIMIT)) {
						Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_OUTLINE_THICKNESS_LIMIT);
						prefs.edit().putInt(Settings.KEY_OUTLINE_THICKNESS_LIMIT, 3).commit();
					}
				}
				// Spezialbehandlung für alte Designs, die diese Keys noch nicht enthalten
				if (!keySet.contains(Settings.KEY_COLOR_RANDOMIZING_TYPE)) {
					Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_COLOR_RANDOMIZING_TYPE + " = full RGB");
					prefs.edit().putString(Settings.KEY_COLOR_RANDOMIZING_TYPE, "full RGB").commit();
				}
				if (!keySet.contains(Settings.KEY_LIMIT_2_CANVAS)) {
					Log.i(LOG_TAG, "Key not contained-> setting it to default: " + Settings.KEY_LIMIT_2_CANVAS + " = true");
					prefs.edit().putBoolean(Settings.KEY_LIMIT_2_CANVAS, true).commit();
				}

				Toaster.showInfoToast(activity, "Design/Colors restored from " + stripTimestamp(filename));
				return settings;
			} catch (final IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Log.e(LOG_TAG, "Loading Settings - File not exists! " + filename);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void writeEntry(final Entry<String, ?> entry, final SharedPreferences prefs, final Set<String> keyset) {
		Log.i(LOG_TAG, "Writing back preferences: " + entry.getKey() + " --> " + entry.getValue().toString() + " ("
				+ entry.getValue().getClass().getSimpleName() + ")");
		final String key = entry.getKey();
		// ein paar Keys nicht lesen!
		if (ignoreKeys.contains(key)) {
			Log.i(LOG_TAG, "Ignoring key: " + key);
			return;
		}
		// Spezialbehandlung für alten LayoutPicker
		if (key.equals("layoutPicker") && !keyset.contains(Settings.KEY_MAINLAYOUTS)) {
			final String val = entry.getValue().toString();
			Log.i(LOG_TAG, "layoutPicker found --> " + entry.getValue().toString() + ")");
			final int pos = val.indexOf(" (");
			if (pos > 0) {
				final String mainLayout = val.substring(0, pos);
				if (mainLayout.startsWith("Geo")) {
					prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Geometric Grid").commit();
					Log.i(LOG_TAG, "layoutPicker found - Putting --> Geometric Grid");
				} else if (mainLayout.startsWith("Circular")) {
					prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Circular").commit();
					Log.i(LOG_TAG, "layoutPicker found - Putting --> Circular");
				} else if (mainLayout.startsWith("Half")) {
					prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Half Circle").commit();
					Log.i(LOG_TAG, "layoutPicker found - Putting --> Half Circle");
				}
				final int posEnd = val.indexOf(")");
				final String mainLayoutVariante = val.substring(pos + 2, posEnd);
				prefs.edit().putString(Settings.KEY_MAINLAYOUT_VARIANTS, mainLayoutVariante).commit();
				Log.i(LOG_TAG, "layoutPicker found - Putting Variante --> " + mainLayoutVariante);
			} else {
				prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Random Layout").commit();
				prefs.edit().putString(Settings.KEY_MAINLAYOUT_VARIANTS, "None").commit();
			}
			return;
		}
		// Spezialbehandlung für alten randomRotate
		if (key.equals("randomRotate") && !keyset.contains("rotatingStyle")) {
			final boolean rota = (Boolean) entry.getValue();
			if (rota == true) {
				prefs.edit().putString("rotatingStyle", "Random").commit();
				Log.i(LOG_TAG, "randomRotate found - Putting ---> Random");
			} else {
				prefs.edit().putString("rotatingStyle", "Fixed").commit();
				Log.i(LOG_TAG, "randomRotate found - Putting ---> fixed");
			}
			return;
		}

		final Class cl = entry.getValue().getClass();
		if (cl.getSimpleName().equalsIgnoreCase("Integer")) {
			prefs.edit().putInt(key, (Integer) entry.getValue()).commit();
		} else if (cl.getSimpleName().equalsIgnoreCase("Boolean")) {
			prefs.edit().putBoolean(key, (Boolean) entry.getValue()).commit();
		} else if (cl.getSimpleName().equalsIgnoreCase("String")) {
			prefs.edit().putString(key, (String) entry.getValue()).commit();
		}
	}

	private static File getSettingsDir() {
		// Ordner anlegen fals nicht vorhanden
		final File dir = new File(StorageHelper.getExternalStorageSettings());
		dir.mkdirs();
		return dir;
	}

	public static String stripTimestamp(final String filename) {
		String out;
		final int pos = filename.indexOf(MARKER);
		if (pos == -1) {
			// EXtension nicht gefunden
			out = filename;
		} else {
			out = filename.substring(0, pos);
		}
		return out;
	}

}
