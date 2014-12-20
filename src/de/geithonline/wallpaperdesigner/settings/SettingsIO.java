package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.geithonline.android.basics.utils.Toaster;
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public class SettingsIO {

	public static final String EXTENSION_PNG = ".png";
	public static final String EXTENSION_JPG = ".jpg";
	public static final String EXTENSION_PREF = ".pref";
	public static final String MARKER = " (+++)_";

	private static boolean designListNeedsReload = true;

	public static void loadPreferencesTheFancyWay(final Activity activity, final SharedPreferences prefs) {

		final List<SavedPreference> preferenceList = getSavedPreferencesList();

		if (preferenceList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();

		dialog.setTitle("Restore Design");
		dialog.setMessage("Select Design to be restored");

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
			Toaster.showErrorToast(activity, "There are no Designs to delete!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();

		dialog.setTitle("Delete Design");
		dialog.setMessage("Select Design to be deleted");

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
					Alerter.alertYesNo(activity, "Dou you want really want to delete the Design?", "Delete Design",
							new OnClickListener() {
								@Override
								public void onClick(final DialogInterface dialog, final int which) {
									SettingsIO.deletePreferencesFileAndBitmap(pref);
									setDesignListNeedsReload(true);
								}
							});

				}
				dialog.dismiss();
			}
		});

		dialog.setView(listview);
		dialog.show();

	}

	private static void deletePreferencesFileAndBitmap(final SavedPreference pref) {
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
			fo.close();
			setDesignListNeedsReload(true);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, ?> loadPreferencesFromFile(final Activity activity, final SharedPreferences prefs,
			final String filename) {
		Log.i("Loading Settings ", "from " + filename);
		final File file = new File(getSettingsDir(), filename);
		if (file.exists()) {
			try {
				final FileInputStream fi = new FileInputStream(file);
				final ObjectInputStream o = new ObjectInputStream(fi);
				final Map<String, ?> settings = (Map<String, ?>) o.readObject();
				o.close();
				fi.close();
				for (final Map.Entry<String, ?> entry : settings.entrySet()) {
					// Log.i("map values", entry.getKey() + ": " +
					// entry.getValue().toString() + ": " +
					// entry.getValue().getClass());
					writeEntry(entry, prefs);
				}
				Toaster.showInfoToast(activity, "Design restored from " + stripTimestamp(filename));
				return settings;
			} catch (final IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Log.i("Loading Settings ", "File not exists! " + filename);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void writeEntry(final Entry<String, ?> entry, final SharedPreferences prefs) {
		Log.i("Writing back preferences", entry.getKey() + " --> " + entry.getValue().toString() + " ("
				+ entry.getValue().getClass().getSimpleName() + ")");
		final String key = entry.getKey();
		// ein paar Keys nicht lesen!
		if (key.equalsIgnoreCase("muimerp")) {
			return;
		}
		if (key.equalsIgnoreCase("sortOrder")) {
			return;
		}
		if (key.equalsIgnoreCase("imageFormat")) {
			return;
		}
		if (key.equalsIgnoreCase("jpgCompression")) {
			return;
		}
		if (key.equalsIgnoreCase("hexValues")) {
			return;
		}
		// Spezialbehandlung für alten LayoutPicker
		if (key.equals("layoutPicker")) {
			final String val = entry.getValue().toString();
			Log.i("layoutPicker found", " --> " + entry.getValue().toString() + ")");
			final int pos = val.indexOf(" (");
			if (pos > 0) {
				final String mainLayout = val.substring(0, pos);
				if (mainLayout.startsWith("Geo")) {
					prefs.edit().putString("mainlayouts", "Geometric Grid").commit();
					Log.i("layoutPicker found", " Putting --> Geometric Grid");
				} else if (mainLayout.startsWith("Circular")) {
					prefs.edit().putString("mainlayouts", "Circular").commit();
					Log.i("layoutPicker found", " Putting --> Circular");
				} else if (mainLayout.startsWith("Half")) {
					prefs.edit().putString("mainlayouts", "Half Circle").commit();
					Log.i("layoutPicker found", " Putting --> Half Circle");
				}
				final int posEnd = val.indexOf(")");
				final String mainLayoutVariante = val.substring(pos + 2, posEnd);
				prefs.edit().putString("mainlayoutVariants", mainLayoutVariante).commit();
				Log.i("layoutPicker found", " Putting Variante --> " + mainLayoutVariante);
			} else {
				prefs.edit().putString("mainlayouts", "Random Layout").commit();
				prefs.edit().putString("mainlayoutVariants", "None").commit();
			}
		}
		// Spezialbehandlung für alten randomRotate
		if (key.equals("randomRotate")) {
			final boolean rota = (Boolean) entry.getValue();
			if (rota == true) {
				prefs.edit().putString("rotatingStyle", "Random").commit();
				Log.i("randomRotate found", " Putting ---> Random");
			} else {
				prefs.edit().putString("rotatingStyle", "Fixed").commit();
				Log.i("randomRotate found", " Putting ---> fixed");
			}
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
	 * @param sortOrder
	 * @return
	 */
	private static List<File> getPreferenzFileList(final SORT_ORDER sortOrder) {
		final File dir = getSettingsDir();
		Log.i("SettingsDIR", "Dir = " + dir);
		final List<File> prefFileList = FileIOHelper.getFileList(dir, EXTENSION_PREF, sortOrder);
		return prefFileList;
	}

	/**
	 * Cache
	 */
	private static List<SavedPreference> savedPrefsList = new ArrayList<>();

	/**
	 * Get a List of all {@link SavedPreference}
	 * 
	 * @return
	 */
	public static List<SavedPreference> getSavedPreferencesList() {
		// müssen wir die Liste neu laden?
		final List<File> prefs = getPreferenzFileList(Settings.getSortOrderForSavedSettings());
		if (numberOfPreferenzfilesChanged(prefs) || designListNeedsReload == true) {
			Log.i("PrefList", "Preflist needs to be reloaded");
			savedPrefsList = new ArrayList<>();
			for (final File fi : prefs) {
				final String prefFilename = fi.getAbsolutePath();

				final String pngFilename = FileIOHelper.replaceExtension(prefFilename, EXTENSION_PREF, EXTENSION_PNG);
				final String jpgFilename = FileIOHelper.replaceExtension(prefFilename, EXTENSION_PREF, EXTENSION_JPG);
				final File pngFile = new File(pngFilename);
				final File jpgFile = new File(jpgFilename);
				File imgFile = jpgFile;
				Bitmap bitmap = null;
				if (jpgFile.exists()) {
					bitmap = BitmapFileIO.loadBitmap(jpgFilename);
					imgFile = jpgFile;
				} else if (pngFile.exists()) {
					bitmap = BitmapFileIO.loadBitmap(pngFilename);
					imgFile = pngFile;
				}
				final SavedPreference pref = new SavedPreference(bitmap, fi, imgFile);
				savedPrefsList.add(pref);
			}
		}
		setDesignListNeedsReload(false);
		return savedPrefsList;
	}

	public static boolean numberOfPreferenzfilesChanged() {
		final List<File> prefs = getPreferenzFileList(Settings.getSortOrderForSavedSettings());
		return numberOfPreferenzfilesChanged(prefs);
	}

	public static boolean numberOfPreferenzfilesChanged(final List<File> prefs) {
		final int currentSize = savedPrefsList.size();
		final int aktuallSize = prefs.size();
		if (currentSize != aktuallSize) {
			return true;
		}
		return false;
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

	public static String getTimestampInFileName(final String filename) {
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

	public static boolean isDesignListNeedsReload() {
		return designListNeedsReload;
	}

	public static void setDesignListNeedsReload(final boolean designListIsUnschanged) {
		SettingsIO.designListNeedsReload = designListIsUnschanged;
	}
}
