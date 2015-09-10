package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class StorageHelper {
	public final static String DIR_SDCARD = Environment.getExternalStorageDirectory().toString();
	public final static File PATH_SDCARD = Environment.getExternalStorageDirectory();

	private final static String imagesDir = DIR_SDCARD + File.separator + "Pictures" + File.separator + "WallpaperDesigner" + File.separator;
	private final static File imagesDirFile = new File(imagesDir);
	private final static String settingsDir = DIR_SDCARD + File.separator + "data" + File.separator + "WallpaperDesigner" + File.separator;
	private final static File settingsDirFile = new File(settingsDir);
	private final static File noMedia = new File(settingsDir, ".nomedia");
	private final static String downloadDir = DIR_SDCARD + File.separator + "Download" + File.separator;
	private final static File downloadDirFile = new File(downloadDir);

	private final static String extStorageUploadDir = DIR_SDCARD + File.separator + "data" + File.separator + "upload" + File.separator;
	private final static File extStorageUploadDirFile = new File(extStorageUploadDir);
	private final static String extStorageDataDir = DIR_SDCARD + File.separator + "data" + File.separator;
	private final static File extStorageDataDirFile = new File(extStorageDataDir);

	static {
		if (!extStorageUploadDirFile.exists()) {
			extStorageUploadDirFile.mkdirs();
		}
		if (!imagesDirFile.exists()) {
			imagesDirFile.mkdirs();
		}
		if (!settingsDirFile.exists()) {
			settingsDirFile.mkdirs();
		}
		if (!downloadDirFile.exists()) {
			downloadDirFile.mkdirs();
		}
		// Nomedia anlegen
		if (!noMedia.exists()) {
			try {
				Log.i("StorageHelper", "Creating .nomedia: " + noMedia.getAbsolutePath());
				noMedia.createNewFile();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getExternalStorage() {
		return DIR_SDCARD;
	}

	public static String getExternalStorageImages() {
		return imagesDir;
	}

	// extStorage/data/WallpaperDesigner
	public static String getExternalStorageSettings() {
		return settingsDir;
	}

	// extStorage/data
	public static String getExtstorageDataDir() {
		return extStorageDataDir;
	}

	// extStorage/data
	public static String getExtstorageUploadDir() {
		return extStorageUploadDir;
	}

	public static File getExtstorageDataDirFile() {
		return extStorageDataDirFile;
	}

	// DownloadDir
	public static String getExternalStorageDownload() {
		return downloadDir;
	}

	public static File getDownloaddirfile() {
		return downloadDirFile;
	}

}
