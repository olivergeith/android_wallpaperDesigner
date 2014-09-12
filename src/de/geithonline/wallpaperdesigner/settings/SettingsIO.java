package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

public class SettingsIO {

	public static void savePreferencesToFile(final Activity activity, final SharedPreferences prefs, final String filename) {
		final File file = new File(activity.getFilesDir(), filename);
		try {
			file.createNewFile();
			final FileOutputStream fo = new FileOutputStream(file);
			final ObjectOutputStream o = new ObjectOutputStream(fo);
			o.writeObject(prefs.getAll());
			o.close();
			// Toaster.showInfoToast(activity, file.getAbsolutePath());
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, ?> loadPreferencespromFile(final Activity activity, final SharedPreferences prefs, final String filename) {
		final File file = new File(activity.getFilesDir(), filename);
		try {
			final FileInputStream fi = new FileInputStream(file);
			final ObjectInputStream o = new ObjectInputStream(fi);
			final Map<String, ?> settings = (Map<String, ?>) o.readObject();
			o.close();
			for (final Map.Entry<String, ?> entry : settings.entrySet()) {
				Log.i("map values", entry.getKey() + ": " + entry.getValue().toString() + ": " + entry.getValue().getClass());
				writeEntry(entry, prefs);
			}
			return settings;
		} catch (final IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void writeEntry(final Entry<String, ?> entry, final SharedPreferences prefs) {
		final String key = entry.getKey();

		final Class cl = entry.getValue().getClass();
		if (cl.getSimpleName().equalsIgnoreCase("Integer")) {
			prefs.edit().putInt(key, (Integer) entry.getValue()).commit();
		} else if (cl.getSimpleName().equalsIgnoreCase("Boolean")) {
			prefs.edit().putBoolean(key, (Boolean) entry.getValue()).commit();
		} else if (cl.getSimpleName().equalsIgnoreCase("String")) {
			prefs.edit().putString(key, (String) entry.getValue()).commit();
		}

	}

}
