package de.geithonline.wallpaperdesigner.utils;

import java.io.File;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

public class MediaScannerHelper {

	public static void rescanMedia(final Context context, final File imageFile) {
		MediaScannerConnection.scanFile(context, new String[] { imageFile.getPath() }, null,
				new MediaScannerConnection.OnScanCompletedListener() {
					@Override
					public void onScanCompleted(final String path, final Uri uri) {
						Log.i("MediaScannerHelper", "Mediascanner scanned " + path);
					}
				});
	}

}
