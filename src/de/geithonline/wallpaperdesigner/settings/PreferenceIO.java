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

	static {
		ignoreKeys.add(Settings.KEY_MUIMERP);
		ignoreKeys.add(Settings.KEY_SORT_ORDER);
		ignoreKeys.add(Settings.KEY_IMAGE_FORMAT);
		ignoreKeys.add(Settings.KEY_JPG_COMPRESSION);
		ignoreKeys.add(Settings.KEY_HEX_VALUES);
		ignoreKeys.add(Settings.KEY_SHARE_SUBJECT);
		ignoreKeys.add(Settings.KEY_SHARE_TEXT);
		ignoreKeys.add(Settings.KEY_APP_THEME);
		ignoreKeys.add("debug");
	}

	@SuppressWarnings("unchecked")
	public static Map<String, ?> loadPreferencesFromFile(final Activity activity, final SharedPreferences prefs,
			final String filename) {
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
				for (final Map.Entry<String, ?> entry : settings.entrySet()) {
					writeEntry(entry, prefs, settings.keySet());
				}
				Toaster.showInfoToast(activity, "Design restored from " + stripTimestamp(filename));
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
