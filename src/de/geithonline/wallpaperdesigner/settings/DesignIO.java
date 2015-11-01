package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
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
import de.geithonline.wallpaperdesigner.WPDUrls;
import de.geithonline.wallpaperdesigner.settingsdownloader.SettingsUploader;
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.MediaScannerHelper;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;
import de.geithonline.wallpaperdesigner.utils.ZipHelper;

public class DesignIO {

	public static final String EXTENSION_ZIP = ".zip";
	public static final String EXTENSION_PNG = ".png";
	public static final String EXTENSION_JPG = ".jpg";
	public static final String EXTENSION_PREF = ".pref";
	public static final String MARKER = " (+++)_";
	private static boolean designListNeedsReload = true;
	/**
	 * Cache
	 */
	private static List<Design> mDesignList = new ArrayList<>();

	public static void shareDesign(final Activity activity) {
		uploadDesign(activity, DESIGN_SAVING_TYPE.PUBLISH_SHARED);
	}

	public static void publishDesign(final Activity activity) {
		uploadDesign(activity, DESIGN_SAVING_TYPE.PUBLISH_FEATURED);
	}

	public static void publishPremiumDesign(final Activity activity) {
		uploadDesign(activity, DESIGN_SAVING_TYPE.PUBLISH_PREMIUM);
	}

	public static void publishFreeDesign(final Activity activity) {
		uploadDesign(activity, DESIGN_SAVING_TYPE.PUBLISH_FREE);
	}

	private enum DESIGN_SAVING_TYPE {
		PUBLISH_FEATURED, PUBLISH_PREMIUM, PUBLISH_SHARED, PUBLISH_FREE
	}

	private static void uploadDesign(final Activity activity, final DESIGN_SAVING_TYPE savingtype) {

		final List<Design> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to publish!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		switch (savingtype) {
		default:
		case PUBLISH_SHARED:
			dialog.setTitle("Share one Design");
			break;
		case PUBLISH_PREMIUM:
			dialog.setTitle("Publish Premium Design");
			break;
		case PUBLISH_FEATURED:
			dialog.setTitle("Publish Featured Design");
			break;
		case PUBLISH_FREE:
			dialog.setTitle("Publish Free Design");
			break;
		}
		dialog.setMessage("Select Design");

		final ListView listview = createListviewOfAllDesigns(activity, designList);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Zip Design ", "from " + position);
				if (position >= 0) {
					final Design design = designList.get(position);
					switch (savingtype) {
					default:
					case PUBLISH_SHARED:
						shareOneDesign(design, activity);
						break;
					case PUBLISH_FEATURED:
						publishOneDesign(design, activity, WPDUrls.UPLOAD_URL_FEATURED_DESIGNS);
						break;
					case PUBLISH_PREMIUM:
						publishOneDesign(design, activity, WPDUrls.UPLOAD_URL_PREMIUM_DESIGNS);
						break;
					case PUBLISH_FREE:
						publishOneDesign(design, activity, WPDUrls.UPLOAD_URL_FREE_DESIGNS);
						break;
					}
				}
				dialog.dismiss();
			}
		});
		dialog.setView(listview);
		dialog.show();

	}

	private static ListView createListviewOfAllDesigns(final Activity activity, final List<Design> designList) {
		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapter adapter = new CustomAdapter(activity, designList);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setAdapter(adapter);
		return listview;
	}

	public static void loadDesignTheFancyWay(final Activity activity, final SharedPreferences prefs, final boolean onlyColors) {

		final List<Design> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to restore!");
			return;
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		if (onlyColors) {
			dialog.setTitle("Restore Colors from Design");
			dialog.setMessage("Select Design");
		} else {
			dialog.setTitle("Restore Design");
			dialog.setMessage("Select Design");
		}

		final ListView listview = createListviewOfAllDesigns(activity, designList);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final Design design = designList.get(position);
					final String filename = design.getPreferenceFile().getName();
					if (filename != null) {
						PreferenceIO.loadPreferencesFromFile(activity, prefs, filename, onlyColors);
					}
				}
				dialog.dismiss();
			}
		});
		dialog.setView(listview);
		dialog.show();

	}

	public static void deleteDesignTheFancyWay(final Activity activity) {

		final List<Design> designList = getSavedPreferencesList();
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
					final Design design = designList.get(position);
					Alerter.alertYesNo(activity, "Dou you want really want to delete the Design?", "Delete Design", new OnClickListener() {
						@Override
						public void onClick(final DialogInterface dialog, final int which) {
							deletePreferencesFileAndBitmap(design);
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
	public static void saveAllDesignsToOneZip(final Activity activity) {

		final List<Design> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs!");
			return;
		}
		final String timeStamp = FileIOHelper.getTimeStampForFile();
		final File outzip = new File(StorageHelper.getDataDir(), "WPD_Designs_" + timeStamp + ".zip");
		final String message = "Backup all Designs to:\n" + StorageHelper.getDataDir() + "\n" + outzip.getName();
		Alerter.alertYesNo(activity, message, "Backup all Designs", new OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				ZipHelper.zipFileAtPath(getSettingsDir().getAbsolutePath(), outzip.getAbsolutePath());
				MediaScannerHelper.rescanMedia(activity, outzip);
				Toaster.showInfoToast(activity, "Designs saved to " + outzip.getName() + " successfully!!");
			}
		});
	}

	/**
	 * Backups all Designs to Zipfile
	 * 
	 * @param activity
	 */
	public static void saveAllDesignsToManyZips(final Activity activity) {

		final List<Design> designList = getSavedPreferencesList();
		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs!");
			return;
		}
		final String message = "Backup all Designs to:\n" + StorageHelper.getBackupDir();
		Alerter.alertYesNo(activity, message, "Backup all Designs into many zips", new OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				for (final Design design : designList) {
					backupOneDesign(design, activity);
				}
				Toaster.showInfoToast(activity, "All Designs backed up to " + StorageHelper.getBackupDir() + " successfully!!");
			}
		});
	}

	/**
	 * Delete all designs
	 * 
	 * @param activity
	 */
	public static void deleteALLDesigns(final Activity activity) {

		final List<Design> designList = getSavedPreferencesList();

		if (designList.isEmpty()) {
			Toaster.showErrorToast(activity, "There are no Designs to delete!");
			return;
		}
		Alerter.alertYesNoUrgent(activity, "Dou you want really want to delete ALL Designs?", "Attention!!! Delete ALL Designs!!!", new OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				for (final Design design : designList) {
					deletePreferencesFileAndBitmap(design);
				}
				setDesignListNeedsReload(true);
				// MediaScannerHelper.rescanFolder(activity, StorageHelper.getDesignsDirFile());
			}
		});
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
	 * Get a List of all {@link Design}'s
	 * 
	 * @return
	 */
	public static List<Design> getSavedPreferencesList() {
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
				final Design pref = new Design(bitmap, fi, imgFile);
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
		final File dir = new File(StorageHelper.getDesignsDir());
		dir.mkdirs();
		return dir;
	}

	public static boolean isDesignListNeedsReload() {
		return designListNeedsReload;
	}

	public static void setDesignListNeedsReload(final boolean needsReload) {
		DesignIO.designListNeedsReload = needsReload;
	}

	// #########################################################################
	// restoring designs from zip
	// #########################################################################

	public static void restoreDesignsFromZip(final Activity activity) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final AlertDialog dialog = builder.create();
		dialog.setTitle("Restore Designs from Backup");
		dialog.setMessage("Restore Designs from Backup");

		final ListView listview = createListviewOfAllBackupZips(activity);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Restoring zip Settings ", "from " + position);
				if (position >= 0) {
					final File file = (File) parent.getAdapter().getItem(position);
					ZipHelper.unzip(file.getAbsolutePath(), StorageHelper.getDesignsDirFile().getAbsolutePath());
					setDesignListNeedsReload(true);
					Toaster.showInfoToast(activity, "Design " + file.getName() + " restored successfully!!");
				}
				dialog.dismiss();
			}
		});
		dialog.setView(listview);
		dialog.show();

	}

	private static ListView createListviewOfAllBackupZips(final Activity activity) {
		final ListView listview = new ListView(activity);
		/** Declaring an ArrayAdapter to set items to ListView */
		final CustomAdapterFiles adapter = new CustomAdapterFiles(activity, getAllDesignBackupZips());
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
	private static List<File> getAllDesignBackupZips() {
		final File dir = StorageHelper.getBackupDirFile();
		Log.i("BackupDir", "Dir = " + dir);
		final List<File> zipBackups = getZipFileListFromLocalDataDir(dir);
		return zipBackups;
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
					return name.endsWith(EXTENSION_ZIP);// && name.startsWith("WPD_");
				}
			});

			// Sort Files
			if (files != null) {
				FileIOHelper.sortFileArray(SORT_ORDER.ALPHA, files);
			}
			// Putting it into a List
			for (final File fi : files) {
				fileList.add(fi);
			}
			Log.i("FileList", "Found = " + fileList.size());
		}
		return fileList;
	}

	private static void shareOneDesign(final Design design, final Activity activity) {
		prepareOneDesignForUpload(design, activity, WPDUrls.UPLOAD_URL_COMMUNITY_DESIGNS, StorageHelper.getUploadDir());
	}

	private static void publishOneDesign(final Design design, final Activity activity, final String url) {
		prepareOneDesignForUpload(design, activity, url, StorageHelper.getUploadDir());
	}

	private static void backupOneDesign(final Design design, final Activity activity) {
		prepareOneDesignForUpload(design, activity, null, StorageHelper.getBackupDir());
	}

	/**
	 * zips a design and resizes the image to 400w and saves both in upload Dir
	 * 
	 * @param design
	 * @param activity
	 * @param url
	 *            Wenn url == null nur backup
	 */
	private static void prepareOneDesignForUpload(final Design design, final Activity activity, final String url, final String outputDir) {
		// saving Zip to upload dir
		final String zip = zipOneDesign(design, activity, outputDir);
		// rescaling and Saving Bitmap
		final File jpg = createSmallJpg(design, activity, outputDir);
		if (url != null) {
			SettingsUploader.upload(activity, jpg.getAbsolutePath(), url);
			SettingsUploader.upload(activity, zip, url);
		}
	}

	// *******************************************************************
	// Basic stuff to do with a design
	// *******************************************************************

	private static String zipOneDesign(final Design design, final Activity activity, final String dir) {
		Log.i("Zipping Design", design.getPreferenceFile().getName() + " to " + dir);
		final File preferenceFile = design.getPreferenceFile();
		final File bmpFile = design.getBmpFile();
		final String prefFilename = design.getPreferenceFile().getName();
		final String zipFilename = FileIOHelper.replaceExtension(prefFilename, EXTENSION_PREF, EXTENSION_ZIP);
		final File outzip = new File(dir, zipFilename);
		final List<String> files = new ArrayList<>();
		files.add(preferenceFile.getAbsolutePath());
		files.add(bmpFile.getAbsolutePath());
		ZipHelper.zipFiles(files, outzip.getAbsolutePath());
		MediaScannerHelper.rescanMedia(activity, outzip);
		Log.i("Zipping Design", " -> done " + outzip.getAbsolutePath());
		return outzip.getAbsolutePath();
	}

	/**
	 * Delete a singele preference
	 * 
	 * @param design
	 */
	private static void deletePreferencesFileAndBitmap(final Design design) {
		design.getPreferenceFile().delete();
		if (design.getBitmap() != null) {
			design.getBmpFile().delete();
		}
		setDesignListNeedsReload(true);
	}

	private static File createSmallJpg(final Design design, final Activity activity, final String outputDir) {
		final Bitmap bitmap = design.getBitmap();
		final int w = bitmap.getWidth();
		final int h = bitmap.getHeight();
		final int dw = 400;
		final int dh = h * dw / w;
		final Bitmap small = Bitmap.createScaledBitmap(bitmap, dw, dh, true);
		final String jpgFilename = design.getBmpFile().getName();
		final File smallJpgFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(small, outputDir, jpgFilename, 80);
		MediaScannerHelper.rescanMedia(activity, smallJpgFile);
		small.recycle();
		return smallJpgFile;
	}

}
