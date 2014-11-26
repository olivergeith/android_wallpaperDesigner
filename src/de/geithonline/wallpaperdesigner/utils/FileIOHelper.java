package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import android.util.Log;

public class FileIOHelper {

	public enum SORT_ORDER {
		ALPHA, LAST_MODIFIED, LAST_MODIFIED_DESCENDING, FILENAME_TIMESTAMP;
	}

	public static void sortFileArray(final SORT_ORDER sortOrder, final File[] fileArray) {
		switch (sortOrder) {
		default:
		case LAST_MODIFIED:
			// Sort by Modyfied
			Arrays.sort(fileArray, new Comparator<File>() {
				@Override
				public int compare(final File f1, final File f2) {
					return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
				}
			});
			break;
		case LAST_MODIFIED_DESCENDING:
			// Sort by Modyfied
			Arrays.sort(fileArray, new Comparator<File>() {
				@Override
				public int compare(final File f1, final File f2) {
					return (int) (f2.lastModified() - f1.lastModified());
				}
			});
			break;
		case ALPHA:
			// Sort by Name
			Arrays.sort(fileArray, new Comparator<File>() {
				@Override
				public int compare(final File f1, final File f2) {
					return f1.getName().compareTo(f2.getName());
				}
			});
			break;
		case FILENAME_TIMESTAMP:
			// Sort by Name
			Arrays.sort(fileArray, new Comparator<File>() {
				@Override
				public int compare(final File f1, final File f2) {
					final String t1 = getTimestampInFileName(f1.getName());
					final String t2 = getTimestampInFileName(f2.getName());

					return -t1.compareTo(t2);
				}
			});
			break;
		}
	}

	public static final String MARKER = " (+++)_";

	public static String getTimestampInFileName(final String filename) {
		String out;
		final int pos = filename.indexOf(MARKER);
		if (pos == -1) {
			// EXtension nicht gefunden
			out = filename;
		} else {
			out = filename.substring(pos);
		}
		return out;

	}

	/**
	 * Get a list of all preference filenames
	 * 
	 * @param activity
	 * @return
	 */
	public static List<File> getFileList(final File dir, final String extension, final SORT_ORDER sortOrder) {
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
						return name.endsWith(extension);
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

	public static String replaceExtension(final String filename, final String extension, final String newExtension) {
		String bmpFilename;
		final int pos = filename.indexOf(extension);
		if (pos == -1) {
			// EXtension nicht gefunden
			bmpFilename = filename + newExtension;
		} else {
			bmpFilename = filename.substring(0, pos) + newExtension;
		}
		return bmpFilename;
	}

}
