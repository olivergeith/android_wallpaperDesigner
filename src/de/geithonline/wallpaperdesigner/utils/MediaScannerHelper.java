
package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

public class MediaScannerHelper {

    public static void rescanMedia(final Context context, final File imageFile) {
        rescanMedia(context, imageFile.getPath());
    }

    public static void rescanMedia(final Context context, final String imageFile) {
        MediaScannerConnection.scanFile(context, new String[] { imageFile }, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(final String path, final Uri uri) {
                Log.i("MediaScannerHelper", "Mediascanner scanned " + path);
            }
        });
    }

    public static void rescanFolder(final Context context, final File folder) {
        final List<File> fileList = FileIOHelper.getFileList(folder);
        if (!fileList.isEmpty()) {
            final String[] strings = new String[fileList.size()];

            for (int index = 0; index < fileList.size(); index++) {
                strings[index] = fileList.get(index).getPath();
            }
            MediaScannerConnection.scanFile(context, strings, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(final String path, final Uri uri) {
                    Log.i("MediaScannerHelper", "Mediascanner scanned Folder " + path);
                }
            });
        }
    }

    // public static void rescanFolder(final Context context, final File dir) {
    // // if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
    // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + dir)));
    // // } else {
    // // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + dir)));
    // // }
    // }

}
