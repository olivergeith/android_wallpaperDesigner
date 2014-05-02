package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;
import de.geithonline.android.basics.utils.BitmapHelper;

public abstract class Drawer extends ColorProvider implements IDrawer {

	protected Canvas bitmapCanvas;
	protected Bitmap bitmap;

	@Override
	public abstract Bitmap drawBitmap();

	@SuppressLint("SimpleDateFormat")
	@Override
	public void save(final Context context) {
		final Date date = new Date();
		final SimpleDateFormat dt = new SimpleDateFormat("yyyymmdd_hhmmss");
		final String timeStamp = dt.format(date);
		final File imageFile = BitmapHelper.saveBitmap2ExternalStorage(bitmap, "WallpaperDesigner", "WallpaperDesigner_" + timeStamp + ".png");
		MediaScannerConnection.scanFile(context, new String[] { imageFile.getPath() }, null, new MediaScannerConnection.OnScanCompletedListener() {
			@Override
			public void onScanCompleted(final String path, final Uri uri) {
				Log.i("FILESAVE", "Scanned " + path);
			}
		});
	}

}
