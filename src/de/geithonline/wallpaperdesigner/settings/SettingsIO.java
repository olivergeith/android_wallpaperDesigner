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
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import de.geithonline.android.basics.utils.Toaster;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public class SettingsIO {

	static String extStorageDirectory = StorageHelper.getExternalStorageSettings();

	// static String extStorageDirectory = Environment.getExternalStorageDirectory().toString() //
	// + File.separator + "data" //
	// + File.separator + "WallpaperDesigner" + File.separator;

	public static void savePreferences(final Activity activity, final SharedPreferences prefs) {
		final AlertDialog.Builder alert = new AlertDialog.Builder(activity);

		alert.setTitle("Backup current preferences");
		alert.setMessage("Enter the name for this preferences");

		// Set an EditText view to get user input
		final EditText input = new EditText(activity);
		input.setMaxLines(1);
		input.setText("Settings.pref");
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int whichButton) {
				String filename = input.getText().toString();
				if (filename != null && filename.length() > 0) {
					if (!filename.endsWith(".pref")) {
						filename = filename + ".pref";
					}
					savePreferencesToFile(activity, prefs, filename);
				}

			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int whichButton) {
				// Canceled.
			}
		});

		alert.show();

	}

	public static void loadPreferences(final Activity activity, final SharedPreferences prefs) {

		final List<String> preferenzFileNameList = getPreferenzFileNameList();

		if (preferenzFileNameList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no backups of preferences to restore!");
			return;
		}

		final AlertDialog.Builder alert = new AlertDialog.Builder(activity);

		alert.setTitle("Restore current preferences");
		alert.setMessage("Select preferences to be restored");

		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getBaseContext(), android.R.layout.simple_list_item_single_choice,
				preferenzFileNameList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		listview.setItemChecked(0, true);
		alert.setView(listview);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int whichButton) {
				final int position = listview.getCheckedItemPosition();
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final String filename = preferenzFileNameList.get(position);
					if (filename != null) {
						SettingsIO.loadPreferencesFromFile(activity, prefs, filename);
					}

				}
			}

		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int whichButton) {
				// Canceled.
			}
		});

		alert.show();

	}

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
		// alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(final DialogInterface dialog, final int whichButton) {
		// final int position = listview.getSelectedItemPosition();
		// Log.i("Loading Settings ", "from " + position);
		// if (position >= 0) {
		// final SavedPreference pref = preferenceList.get(position);
		// final String filename = pref.getPreferenceFile().getName();
		// if (filename != null) {
		// SettingsIO.loadPreferencesFromFile(activity, prefs, filename);
		// }
		//
		// }
		// }
		//
		// });
		//
		// alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(final DialogInterface dialog, final int whichButton) {
		// // Canceled.
		// }
		// });

		dialog.show();

	}

	public static void deletePreferences(final Activity activity) {

		final List<String> preferenzFileNameList = getPreferenzFileNameList();

		if (preferenzFileNameList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no backups of preferences you possibly could delete!");
			return;
		}

		final AlertDialog.Builder alert = new AlertDialog.Builder(activity);

		alert.setTitle("Delete backup of preferences");
		alert.setMessage("Select preferences to be deleted");

		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getBaseContext(), android.R.layout.simple_list_item_single_choice,
				preferenzFileNameList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		listview.setItemChecked(0, true);
		alert.setView(listview);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int whichButton) {
				final int position = listview.getCheckedItemPosition();
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final String filename = preferenzFileNameList.get(position);
					if (filename != null) {
						SettingsIO.deletePreferencesFile(activity, filename);
					}

				}
			}

		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int whichButton) {
				// Canceled.
			}
		});

		alert.show();

	}

	protected static void deletePreferencesFile(final Activity activity, final String filename) {
		final File file = new File(getSettingsDir(), filename);
		file.delete();
		Toaster.showInfoToast(activity, "Deleted " + filename);
	}

	private static void savePreferencesToFile(final Activity activity, final SharedPreferences prefs, final String filename) {
		final File file = new File(getSettingsDir(), filename);
		savePreferences(prefs, file);
		Toaster.showInfoToast(activity, "Preferences backed up as " + file.getName());
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
	public static void writeEntry(final Entry<String, ?> entry, final SharedPreferences prefs) {
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
	public static List<String> getPreferenzFileNameList() {
		final List<String> names = new ArrayList<String>();
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
				names.add(fi.getName());
			}
		}
		return names;
	}

	/**
	 * Get a list of all preference filenames
	 * 
	 * @param activity
	 * @return
	 */
	public static List<File> getPreferenzFileList() {
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

	public static List<SavedPreference> getSavedPreferencesList() {
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
			final SavedPreference pref = new SavedPreference(bitmap, fi);
			list.add(pref);
		}
		return list;
	}

	private static File getSettingsDir() {
		// Ordner anlegen fals nicht vorhanden
		final File dir = new File(extStorageDirectory);
		dir.mkdirs();
		return dir;
	}
}
