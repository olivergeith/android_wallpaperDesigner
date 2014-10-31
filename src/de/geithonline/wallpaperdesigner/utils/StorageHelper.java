package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class StorageHelper {

	private final static String imagesDir = Environment.getExternalStorageDirectory().toString() //
			+ File.separator + "Pictures" + File.separator + "WallpaperDesigner" + File.separator;
	private final static File imagesDirFile = new File(imagesDir);

	private final static String settingsDir = Environment.getExternalStorageDirectory().toString() //
			+ File.separator + "data" //
			+ File.separator + "WallpaperDesigner" + File.separator;
	private final static File settingsDirFile = new File(settingsDir);

	static {
		if (!settingsDirFile.exists()) {
			settingsDirFile.mkdirs();
		}
		final File noMedia = new File(settingsDir, ".nomedia");
		if (!noMedia.exists()) {
			try {
				Log.i("StorageHelper", "Creating .NoMedia: " + noMedia.getAbsolutePath());
				noMedia.createNewFile();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		if (!imagesDirFile.exists()) {
			imagesDirFile.mkdirs();
		}

	}

	public static String getExternalStorage() {
		return Environment.getExternalStorageDirectory().toString();
	}

	public static String getExternalStorageImages() {
		return imagesDir;
	}

	public static String getExternalStorageSettings() {

		return settingsDir;
	}

}
