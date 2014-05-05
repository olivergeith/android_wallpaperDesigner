package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.BitmapHelper;

public abstract class WPStyle extends ColorProvider implements IWPStyle {

	protected Canvas bitmapCanvas;
	protected Bitmap bitmap;

	@Override
	public abstract Bitmap drawBitmap();

	@SuppressLint("SimpleDateFormat")
	@Override
	public synchronized void save(final Context context) {
		final Date date = new Date();
		final SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd_hhmmss");
		final String timeStamp = dt.format(date);
		final File imageFile = BitmapHelper.saveBitmap2ExternalStorage(bitmap, "WallpaperDesigner", "WallpaperDesigner_" + timeStamp + ".png");
		MediaScannerConnection.scanFile(context, new String[] { imageFile.getPath() }, null, new MediaScannerConnection.OnScanCompletedListener() {
			@Override
			public void onScanCompleted(final String path, final Uri uri) {
				Log.i("FILESAVE", "Scanned " + path);
			}
		});
	}

	@Override
	public void recycleBitmap() {
		if (bitmap != null) {
			bitmap.recycle();
		}
	}

	protected void drawNonPremiumText(final Canvas canvas) {
		if (!Settings.isPremium()) {
			final int fontSize = Math.round(canvas.getWidth() * 0.015f);
			int dropShadowRadius = Math.round(canvas.getWidth() * 0.01f);
			if (dropShadowRadius < 3) {
				dropShadowRadius = 3;
			}
			final String text = "Created with 'The Wallpaper Designer' ...please get the Premium Version, to get rid of this text :-)";
			final Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.WHITE);
			paint.setAlpha(96);
			paint.setTextSize(fontSize);
			paint.setTextAlign(Align.CENTER);
			paint.setTypeface(Typeface.DEFAULT);
			paint.setShadowLayer(dropShadowRadius, 3, 3, Color.BLACK);

			final Rect textBounds = new Rect();
			textBounds.left = 10;
			textBounds.top = 3;
			textBounds.right = canvas.getWidth() - 20;
			textBounds.bottom = 9 + fontSize;
			canvas.drawRect(textBounds, paint);
			paint.setShadowLayer(0, 3, 3, Color.BLACK);
			paint.setColor(Color.BLACK);
			paint.setAlpha(128);
			canvas.drawText(text, canvas.getWidth() / 2, 3 + fontSize, paint);
		}
	}

}