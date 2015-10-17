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

	private final static String designsDir = DIR_SDCARD + File.separator + "data" + File.separator + "WallpaperDesigner" + File.separator;
	private final static File designsDirFile = new File(designsDir);
	private final static File designsDirNoMedia = new File(designsDir, ".nomedia");

	private final static String downloadDir = DIR_SDCARD + File.separator + "Download" + File.separator;
	private final static File downloadDirFile = new File(downloadDir);

	private final static String backupDir = DIR_SDCARD + File.separator + "data" + File.separator + "WallpaperDesignerBackups" + File.separator;
	private final static File backupDirFile = new File(backupDir);
	private final static File backupDirNomedia = new File(backupDir, ".nomedia");

	private final static String uploadDir = DIR_SDCARD + File.separator + "data" + File.separator + "upload" + File.separator;
	private final static File uploadDirFile = new File(uploadDir);
	private final static File uploadDirNomedia = new File(uploadDir, ".nomedia");

	private final static String dataDir = DIR_SDCARD + File.separator + "data" + File.separator;
	private final static File dataDirFile = new File(dataDir);

	static {
		createDirs(uploadDirFile);
		createDirs(imagesDirFile);
		createDirs(designsDirFile);
		createDirs(downloadDirFile);
		createDirs(backupDirFile);
		// Nomedia anlegen
		createNomedia(designsDirNoMedia);
		createNomedia(backupDirNomedia);
		createNomedia(uploadDirNomedia);
	}

	private static void createDirs(final File dir) {
		if (!dir.exists()) {
			Log.i("StorageHelper", "Creating dirs: " + dir.getAbsolutePath());
			dir.mkdirs();
		}
	}

	private static void createNomedia(final File nomedia) {
		if (!nomedia.exists()) {
			try {
				Log.i("StorageHelper", "Creating .nomedia: " + nomedia.getAbsolutePath());
				nomedia.createNewFile();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getWallpaperImagesDir() {
		return imagesDir;
	}

	// extStorage/data/WallpaperDesigner
	public static String getDesignsDir() {
		return designsDir;
	}

	// extStorage/data
	public static String getDataDir() {
		return dataDir;
	}

	// extStorage/data
	public static String getUploadDir() {
		return uploadDir;
	}

	public static File getDataDirFile() {
		return dataDirFile;
	}

	// DownloadDir
	public static String getDownloadDir() {
		return downloadDir;
	}

	public static File getDownloadDirFile() {
		return downloadDirFile;
	}

	public static String getBackupDir() {
		return backupDir;
	}

}
