package de.geithonline.wallpaperdesigner.utils;

import java.util.Locale;

import android.os.Debug;

public class DebugHelper {

	public static String getMemoryInfo() {
		final long totalMemory = Runtime.getRuntime().totalMemory();
		final long freeMemory = Runtime.getRuntime().freeMemory();
		final long maxMemory = Runtime.getRuntime().maxMemory();
		final long nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
		final String text = "totalMemory=" + humanReadableByteCount(totalMemory, true) //
				+ "\nfreeMemory=" + humanReadableByteCount(freeMemory, true) //
				+ "\nmaxMemory=" + humanReadableByteCount(maxMemory, true) //
				+ "\nnativeHeapAllocatedSize=" + humanReadableByteCount(nativeHeapAllocatedSize, true);
		return text;
	}

	public static String humanReadableByteCount(final long bytes, final boolean si) {
		final int unit = si ? 1000 : 1024;
		if (bytes < unit) {
			return bytes + " B";
		}
		final int exp = (int) (Math.log(bytes) / Math.log(unit));
		final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format(Locale.GERMANY, "%.2f %sB", bytes / Math.pow(unit, exp), pre);
	}
}
