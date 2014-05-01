package de.geithonline.android.basics.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class ServiceHelper {
	public static boolean isMyServiceRunning(final Activity activity, final String className) {
		final ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
		for (final RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			// Log.i("GEITH", "Sevice = " + service.service.getClassName());
			if (className.equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
