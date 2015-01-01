package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.EMailHelper;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.MediaScannerHelper;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;
import de.geithonline.wallpaperdesigner.utils.ZipHelper;

public class SettingsIO {

	public static final String EXTENSION_ZIP = ".zip";
	public static final String EXTENSION_PNG = ".png";
	public static final String EXTENSION_JPG = ".jpg";
	public static final String EXTENSION_PREF = ".pref";
	public static final String MARKER = " (+++)_";
	private static boolean designListNeedsReload = true;
	/**
	 * Cache
	 */
	private static List<SavedDesign> mDesignList = new ArrayList<>();

	public static void eMailDesignTheFancyWay(final Activity activity, final SharedPreferences prefs) {

		final List<SavedDesign> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		dialog.setTitle("Email Design");
		dialog.setMessage("Select Design to be emailed");

		final ListView listview = createListviewOfAllDesigns(activity, designList);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Emailing Settings ", "from " + position);
				if (position >= 0) {
					final SavedDesign design = designList.get(position);
					final List<String> filePaths = new ArrayList<String>();
					filePaths.add(design.getPreferenceFile().getAbsolutePath());
					filePaths.add(design.getBmpFile().getAbsolutePath());
					final String toAdress = "";
					final Intent createEMailIntent = EMailHelper.createEMailIntent(toAdress, "", "", "My Design",
							"Mailing Desing :-) To have them in 'The Wallpaper Designer' save both attached files to \n"
									+ getSettingsDir().getAbsolutePath(), filePaths);
					EMailHelper.email(activity, createEMailIntent);
				}
				dialog.dismiss();
			}
		});
		dialog.setView(listview);
		dialog.show();

	}

	private static ListView createListviewOfAllDesigns(final Activity activity, final List<SavedDesign> designList) {
		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapter adapter = new CustomAdapter(activity, designList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		return listview;
	}

	public static void loadDesignTheFancyWay(final Activity activity, final SharedPreferences prefs) {

		final List<SavedDesign> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		dialog.setTitle("Restore Design");
		dialog.setMessage("Select Design to be restored");

		final ListView listview = createListviewOfAllDesigns(activity, designList);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final SavedDesign design = designList.get(position);
					final String filename = design.getPreferenceFile().getName();
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

	public static void deleteDesignTheFancyWay(final Activity activity) {

		final List<SavedDesign> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to delete!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		dialog.setTitle("Delete Design");
		dialog.setMessage("Select Design to be deleted");

		final ListView listview = createListviewOfAllDesigns(activity, designList);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Deleting Settings ", "from " + position);
				if (position >= 0) {
					final SavedDesign design = designList.get(position);
					Alerter.alertYesNo(activity, "Dou you want really want to delete the Design?", "Delete Design",
							new OnClickListener() {
								@Override
								public void onClick(final DialogInterface dialog, final int which) {
									SettingsIO.deletePreferencesFileAndBitmap(design);
								}
							});

				}
				dialog.dismiss();
			}
		});
		dialog.setView(listview);
		dialog.show();
	}

	/**
	 * Backups all Designs to Zipfile
	 * 
	 * @param activity
	 */
	public static void saveAllDesignsToZipAndMail(final Activity activity, final boolean sendmail,
			final boolean toOliver) {

		final List<SavedDesign> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs!");
			return;
		}
		final String timeStamp = getTimeStampForFile();
		final File outzip = new File(StorageHelper.getExtstorageDataDir(), "WPD_Designs_" + timeStamp + ".zip");

		String message = "Backup all Designs to:\n" + StorageHelper.getExtstorageDataDir() + "\n" + outzip.getName();
		if (sendmail) {
			message = "Email designs to someone?";
		}

		Alerter.alertYesNo(activity, message, "Backup/Email all Designs", new OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				ZipHelper.zipFileAtPath(getSettingsDir().getAbsolutePath(), outzip.getAbsolutePath());
				MediaScannerHelper.rescanMedia(activity, outzip);
				if (sendmail) {
					sendFileViaEmail(activity, outzip.getAbsolutePath(), toOliver);
				}
			}
		});
	}

	public static void sendFileViaEmail(final Activity activity, final String filePathAbsolut, final boolean toOliver) {
		final List<String> filePaths = new ArrayList<String>();
		filePaths.add(filePathAbsolut);
		String toAdress = "";
		if (toOliver) {
			toAdress = "oliver.geith@gmail.com";
		}
		final Intent createEMailIntent = EMailHelper.createEMailIntent(toAdress, "", "", "Designs", "Mailing Desings",
				filePaths);
		EMailHelper.email(activity, createEMailIntent);
	}

	/**
	 * Delete all designs
	 * 
	 * @param activity
	 */
	public static void deleteALLDesigns(final Activity activity) {

		final List<SavedDesign> designList = getSavedPreferencesList();

		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to delete!");
			return;
		}
		Alerter.alertYesNoUrgent(activity, "Dou you want really want to delete ALL Designs?",
				"Attention!!! Delete ALL Designs", new OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						for (final SavedDesign design : designList) {
							SettingsIO.deletePreferencesFileAndBitmap(design);
						}
						setDesignListNeedsReload(true);
					}
				});
	}

	/**
	 * Delete a singele preference
	 * 
	 * @param pref
	 */
	private static void deletePreferencesFileAndBitmap(final SavedDesign pref) {
		pref.getPreferenceFile().delete();
		if (pref.getBitmap() != null) {
			pref.getBmpFile().delete();
		}
		setDesignListNeedsReload(true);
	}

	/**
	 * Saving the SharedPreferences by serializing them into a file!
	 * 
	 * @param prefs
	 * @param file
	 */
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
	 * Get a list of all preference filenames *.pref files
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
	 * Get a List of all {@link SavedDesign}'s
	 * 
	 * @return
	 */
	public static List<SavedDesign> getSavedPreferencesList() {
		// müssen wir die Liste neu laden?
		final List<File> prefs = getPreferenzFileList(Settings.getSortOrderForSavedSettings());
		if (numberOfPreferenzfilesChanged(prefs) || designListNeedsReload == true) {
			Log.i("PrefList", "Preflist needs to be reloaded");
			mDesignList = new ArrayList<>();
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
				final SavedDesign pref = new SavedDesign(bitmap, fi, imgFile);
				mDesignList.add(pref);
			}
		}
		setDesignListNeedsReload(false);
		return mDesignList;
	}

	public static boolean numberOfPreferenzfilesChanged() {
		final List<File> designList = getPreferenzFileList(Settings.getSortOrderForSavedSettings());
		return numberOfPreferenzfilesChanged(designList);
	}

	public static boolean numberOfPreferenzfilesChanged(final List<File> designList) {
		final int currentSize = mDesignList.size();
		final int aktuallSize = designList.size();
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

	public static String getTimeStampForFile() {
		final Date date = new Date();
		final SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
		final String timeStamp = dt.format(date);
		return timeStamp;
	}

	// #########################################################################
	// restoring designs from zip
	// #########################################################################

	public static void restoreDesignsFromZip(final Activity activity, final boolean fromDownload) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		if (fromDownload) {
			dialog.setTitle("Restore Designs from shared Zip");
		} else {
			dialog.setTitle("Restore Designs from Backup Zip");
		}
		dialog.setMessage("Restore alle Designs from one of your backup-zips");

		final ListView listview = createListviewOfAllBackupZips(activity, fromDownload);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Restoring zip Settings ", "from " + position);
				if (position >= 0) {
					final File file = (File) parent.getAdapter().getItem(position);
					Alerter.alertYesNo(activity, "Dou you want really want to restore all designs from this Zip?\n"
							+ file.getName(), "Restore Designs from Zip", new OnClickListener() {
						@Override
						public void onClick(final DialogInterface dialog, final int which) {
							// deleteALLDesignsWithoutAsking(activity);
							ZipHelper.unzip(file.getAbsolutePath(), //
									StorageHelper.getExtstorageDataDirFile().getAbsolutePath());
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

	private static ListView createListviewOfAllBackupZips(final Activity activity, final boolean fromDownload) {
		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapterFiles adapter = new CustomAdapterFiles(activity, getAllDesignBackupZips(fromDownload));
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		return listview;
	}

	/**
	 * Get a list of all preference filenames *.pref files
	 * 
	 * @param fromDownload
	 * 
	 * @param sortOrder
	 * @return
	 */
	private static List<File> getAllDesignBackupZips(final boolean fromDownload) {
		File dir;
		if (fromDownload) {
			dir = StorageHelper.getDownloaddirfile();
		} else {
			dir = StorageHelper.getExtstorageDataDirFile();
		}
		Log.i("Data DIR", "Dir = " + dir);
		final List<File> zipBackups = getZipFileListFromLocalDataDir(dir);
		return zipBackups;
	}

	/**
	 * Delete all designs
	 * 
	 * @param activity
	 */
	private static void deleteALLDesignsWithoutAsking(final Activity activity) {

		final List<SavedDesign> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			// Toaster.showErrorToast(activity, "There are no Designs to delete!");
			return;
		}
		for (final SavedDesign design : designList) {
			SettingsIO.deletePreferencesFileAndBitmap(design);
		}
		setDesignListNeedsReload(true);
	}

	/**
	 * Get a list of all preference filenames
	 * 
	 * @param activity
	 * @return
	 */
	private static List<File> getZipFileListFromLocalDataDir(final File dir) {
		final List<File> fileList = new ArrayList<File>();
		Log.i("FileList", "Dir = " + dir);
		if (dir != null && dir.exists() && dir.isDirectory()) {
			Log.i("FileList", "ScanningDir = " + dir);
			// Extension angegeben...dann filtern...
			final File[] files = dir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(final File file, final String name) {
					return name.endsWith(EXTENSION_ZIP) && name.startsWith("WPD_");
				}
			});

			// Sort Files
			if (files != null) {
				FileIOHelper.sortFileArray(SORT_ORDER.LAST_MODIFIED_DESCENDING, files);
			}
			// Putting it into a List
			for (final File fi : files) {
				fileList.add(fi);
			}
			Log.i("FileList", "Found = " + fileList.size());
		}
		return fileList;
	}

}
