package de.geithonline.wallpaperdesigner.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayHelper {

	public static int getDisplayHeight(final Context activity) {
		final DisplayMetrics metrics = getDisplayMetrics(activity);
		final int pixels = metrics.heightPixels;
		return pixels;
	}

	public static int getDisplayWidth(final Context activity) {
		final DisplayMetrics metrics = getDisplayMetrics(activity);
		final int pixels = metrics.widthPixels;
		return pixels;
	}

	public static DisplayMetrics getDisplayMetrics(final Context activity) {
		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

}
