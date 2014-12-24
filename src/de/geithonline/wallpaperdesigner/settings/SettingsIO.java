package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;

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
						PreferenceIO.loadPreferencesFromFile(activity, prefs, filename);
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
		setDesignListNeedsReload(true);
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

	public static void setDesignListNeedsReload(final boolean needsReload) {
		SettingsIO.designListNeedsReload = needsReload;
	}
}
