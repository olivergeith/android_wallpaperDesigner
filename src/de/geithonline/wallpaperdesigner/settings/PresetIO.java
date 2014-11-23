package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public class PresetIO {

	public static final String EXTENSION_ZIP = ".zip";
	public static final String PREFIX_SETTINGS = "WPD_settings_";

	/**
	 * Get a list of all zip filenames
	 * 
	 * @param sortOrder
	 * @return
	 */
	private static List<File> getZipFileList(final SORT_ORDER sortOrder) {
		final File dir = StorageHelper.getDownloaddirfile();
		Log.i("Download-DIR", "Dir = " + dir);
		final List<File> prefFileList = getZipFileList(dir, PREFIX_SETTINGS, EXTENSION_ZIP, sortOrder);
		return prefFileList;
	}

	/**
	 * Get a list of all zip filenames
	 * 
	 * @return
	 */
	private static List<File> getZipFileList(final File dir, final String prefix, final String extension, final SORT_ORDER sortOrder) {
		final List<File> fileList = new ArrayList<File>();
		Log.i("FileList", "Dir = " + dir);
		if (dir != null && dir.exists() && dir.isDirectory()) {
			Log.i("FileList", "ScanningDir = " + dir);
			// Extension angegeben...dann filtern...
			File[] files;
			if (extension != null) {
				files = dir.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(final File file, final String name) {
						return name.endsWith(extension) && name.startsWith(prefix);
					}
				});
			} else {
				files = dir.listFiles();
			}

			// Sort Files
			if (files != null && sortOrder != null) {
				FileIOHelper.sortFileArray(sortOrder, files);
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
