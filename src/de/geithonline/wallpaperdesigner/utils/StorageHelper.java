package de.geithonline.wallpaperdesigner.utils;

import java.io.File;

import android.os.Environment;

public class StorageHelper {

	public static String getExternalStorage() {
		return Environment.getExternalStorageDirectory().toString();
	}

	public static String getExternalStorageImages() {
		return Environment.getExternalStorageDirectory().toString() //
				+ File.separator + "Pictures" + File.separator + "WallpaperDesigner" + File.separator;
	}

	public static String getExternalStorageSettings() {
		return Environment.getExternalStorageDirectory().toString() //
				+ File.separator + "Pictures" + File.separator + "WallpaperDesigner" + File.separator + "previews";
	}

}
