package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import de.geithonline.wallpaperdesigner.settings.Settings.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public class SettingsIO {

	public static final String EXTENSION_PNG = ".png";
	public static final String EXTENSION_PREF = ".pref";
	public static final String MARKER = " (+++)_";

	public static void loadPreferencesTheFancyWay(final Activity activity, final SharedPreferences prefs) {

		final List<SavedPreference> preferenceList = getSavedPreferencesList();

		if (preferenceList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no backups of preferences to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();

		dialog.setTitle("Restore Settings");
		dialog.setMessage("Select Settings to be restored");

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
			Toaster.showErrorToast(activity, "There are no Settings to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();

		dialog.setTitle("Delete Settings");
		dialog.setMessage("Select Settings to be deleted");

		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapter adapter = new CustomAdapter(activity, preferenceList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		// listview.setItemChecked(0, true);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Deleting Settings ", "from " + position);
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
				Toaster.showInfoToast(activity, "Settings restored from " + filename);
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
		// ein paar Keys nicht lesen!
		if (key.equalsIgnoreCase("muimerp")) {
			return;
		}
		if (key.equalsIgnoreCase("sortOrder")) {
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
	private static List<File> getPreferenzFileList(final SORT_ORDER sortOrder) {
		final List<File> prefFileList = new ArrayList<File>();
		final File dir = getSettingsDir();
		Log.i("SettingsDIR", "Dir = " + dir);
		if (dir.exists() && dir.isDirectory()) {
			Log.i("SettingsDIR", "ScanningDir = " + dir);
			final File[] prefs = dir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(final File file, final String name) {
					return name.endsWith(EXTENSION_PREF);
				}
			});

			switch (sortOrder) {
			default:
			case LAST_MODYFIED:
				// Sort by Modyfied
				Arrays.sort(prefs, new Comparator<File>() {
					@Override
					public int compare(final File f1, final File f2) {
						return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
					}
				});
				break;
			case ALPHA:
				// Sort by Name
				Arrays.sort(prefs, new Comparator<File>() {
					@Override
					public int compare(final File f1, final File f2) {
						return f1.getName().compareTo(f2.getName());
					}
				});
				break;
			}

			for (final File fi : prefs) {
				prefFileList.add(fi);
			}
			Log.i("SettingsDIR", "Found = " + prefFileList.size());
		}
		return prefFileList;
	}

	private static List<SavedPreference> getSavedPreferencesList() {
		final List<SavedPreference> savedPrefsList = new ArrayList<>();
		final List<File> prefs = getPreferenzFileList(Settings.getSortOrderForSavedSettings());
		for (final File fi : prefs) {
			final String prefFilename = fi.getAbsolutePath();

			final String bmpFilename = replaceExtension(prefFilename, EXTENSION_PREF, EXTENSION_PNG);
			final File bmpFile = new File(bmpFilename);
			Bitmap bitmap = null;

			if (bmpFile.exists()) {
				bitmap = BitmapFileIO.loadBitmap(bmpFilename);
			}
			final SavedPreference pref = new SavedPreference(bitmap, fi, bmpFile);
			savedPrefsList.add(pref);
		}
		return savedPrefsList;
	}

	public static String replaceExtension(final String filename, final String extension, final String newExtension) {
		String bmpFilename;
		final int pos = filename.indexOf(extension);
		if (pos == -1) {
			// EXtension nicht gefunden
			bmpFilename = filename + newExtension;
		} else {
			bmpFilename = filename.substring(0, pos) + newExtension;
		}
		return bmpFilename;
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

	private static File getSettingsDir() {
		// Ordner anlegen fals nicht vorhanden
		final File dir = new File(StorageHelper.getExternalStorageSettings());
		dir.mkdirs();
		return dir;
	}
}
