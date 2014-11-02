package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileIOHelper {

	public enum SORT_ORDER {
		ALPHA, LAST_MODIFIED, LAST_MODIFIED_DESCENDING;
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
		}
	}

}
