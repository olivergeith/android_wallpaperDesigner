package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.geithonline.android.basics.utils.Toaster;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public class SettingsIO {

	public static void loadPreferencesTheFancyWay(final Activity activity, final SharedPreferences prefs) {

		final List<SavedPreference> preferenceList = getSavedPreferencesList();

		if (preferenceList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no backups of preferences to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();

		dialog.setTitle("Restore current preferences");
		dialog.setMessage("Select preferences to be restored");

		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapter adapter = new CustomAdapter(activity, preferenceList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		// listview.setItemChecked(0, true);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final SavedPreference pref = preferenceList.get(position);
					final String filename = pref.getPreferenceFile().getName();
					if (filename != null) {
						SettingsIO.loadPreferencesFromFile(activity, prefs, filename);
					}
				}
				dialog.dismiss();
			}
		});
		dialog.setView(listview);
		dialog.show();

	}

	public static void deletePreferencesTheFancyWay(final Activity activity) {

		final List<SavedPreference> preferenceList = getSavedPreferencesList();

		if (preferenceList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no backups of preferences to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();

		dialog.setTitle("Delete backup of preferences");
		dialog.setMessage("Select preferences to be deleted");

		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapter adapter = new CustomAdapter(activity, preferenceList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		// listview.setItemChecked(0, true);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final SavedPreference pref = preferenceList.get(position);
					SettingsIO.deletePreferencesFile(pref);
				}
				dialog.dismiss();
			}
		});

		dialog.setView(listview);
		dialog.show();

	}

	private static void deletePreferencesFile(final SavedPreference pref) {
		pref.getPreferenceFile().delete();
		if (pref.getBitmap() != null) {
			pref.getBmpFile().delete();
		}
	}

	public static void savePreferences(final SharedPreferences prefs, final File file) {
		try {
			file.createNewFile();
			final FileOutputStream fo = new FileOutputStream(file);
			final ObjectOutputStream o = new ObjectOutputStream(fo);
			o.writeObject(prefs.getAll());
			o.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, ?> loadPreferencesFromFile(final Activity activity, final SharedPreferences prefs, final String filename) {
		Log.i("Loading Settings ", "from " + filename);
		final File file = new File(getSettingsDir(), filename);
		if (file.exists()) {
			try {
				final FileInputStream fi = new FileInputStream(file);
				final ObjectInputStream o = new ObjectInputStream(fi);
				final Map<String, ?> settings = (Map<String, ?>) o.readObject();
				o.close();
				for (final Map.Entry<String, ?> entry : settings.entrySet()) {
					// Log.i("map values", entry.getKey() + ": " + entry.getValue().toString() + ": " + entry.getValue().getClass());
					writeEntry(entry, prefs);
				}
				Toaster.showInfoToast(activity, "Preferences restored from " + filename);
				return settings;
			} catch (final IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void writeEntry(final Entry<String, ?> entry, final SharedPreferences prefs) {
		Log.i("Writing back preferences", entry.getKey() + " --> " + entry.getValue().toString() + " (" + entry.getValue().getClass().getSimpleName() + ")");
		final String key = entry.getKey();
		// nur Premium nicht lesen
		if (key.equalsIgnoreCase("muimerp")) {
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

	/**
	 * Get a list of all preference filenames
	 * 
	 * @param activity
	 * @return
	 */
	private static List<File> getPreferenzFileList() {
		final List<File> names = new ArrayList<File>();
		final File dir = getSettingsDir();
		Log.i("SettingsDIR", "Dir = " + dir);
		if (dir.exists() && dir.isDirectory()) {
			Log.i("SettingsDIR", "ScanningDir = " + dir);
			final File[] prefs = dir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(final File file, final String name) {
					return name.endsWith(".pref");
				}
			});
			for (final File fi : prefs) {
				names.add(fi);
			}
			Log.i("SettingsDIR", "Found = " + names.size());
		}
		return names;
	}

	private static List<SavedPreference> getSavedPreferencesList() {
		final List<SavedPreference> list = new ArrayList<>();
		final List<File> filenames = getPreferenzFileList();
		for (final File fi : filenames) {
			final String absolute = fi.getAbsolutePath();
			final int pos = absolute.indexOf(".pref");
			final String bmpFilename = absolute.substring(0, pos);
			final File bmpFile = new File(bmpFilename);
			Bitmap bitmap = null;
			if (bmpFile.exists()) {
				bitmap = BitmapFileIO.loadBitmap(bmpFilename);
			}
			final SavedPreference pref = new SavedPreference(bitmap, fi, bmpFile);
			list.add(pref);
		}
		return list;
	}

	private static File getSettingsDir() {
		// Ordner anlegen fals nicht vorhanden
		final File dir = new File(StorageHelper.getExternalStorageSettings());
		dir.mkdirs();
		return dir;
	}
}
