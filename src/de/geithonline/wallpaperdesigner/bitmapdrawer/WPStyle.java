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
import de.geithonline.wallpaperdesigner.settings.SettingsIO;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public abstract class WPStyle extends ColorProvider implements IWPStyle {

	protected Canvas bitmapCanvas;
	protected Bitmap bitmap;

	@Override
	public abstract Bitmap drawBitmap();

	@SuppressLint("SimpleDateFormat")
	@Override
	public synchronized void save(final Context context, final boolean saveSettings) {
		final Date date = new Date();
		final SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd_HHmmss");
		final String timeStamp = dt.format(date);
		saveBigImage(context, timeStamp);
		if (saveSettings) {
			saveSmallImageAndSettings(context);
		} else {

		}
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public synchronized void saveSmallImageAndSettings(final Context context) {
		final Date date = new Date();
		final SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd_HHmmss");
		final String timeStamp = dt.format(date);

		final int w = bitmap.getWidth();
		final int h = bitmap.getHeight();

		final int dw = 256;
		final int dh = h * dw / w;

		final Bitmap small = Bitmap.createScaledBitmap(bitmap, dw, dh, true);

		// Generate Filename
		final String pattern = Settings.getSelectedPattern() + "_" + Settings.getSelectedPatternVariant();
		final String layout = Settings.getSelectedLayout();
		final String pngFilename = pattern + "\n" + layout + SettingsIO.MARKER + timeStamp + SettingsIO.EXTENSION_PNG;

		final File pngFile = BitmapFileIO.saveBitmap2ExternalStorage(small, StorageHelper.getExternalStorageSettings(), pngFilename);
		rescanMedia(context, pngFile);
		small.recycle();
		// Saving corresponding Settings
		final String prefFileName = SettingsIO.replaceExtension(pngFile.getAbsolutePath(), SettingsIO.EXTENSION_PNG, SettingsIO.EXTENSION_PREF);
		final File settingsFile = new File(prefFileName);
		SettingsIO.savePreferences(Settings.prefs, settingsFile);
	}

	public synchronized void saveBigImage(final Context context, final String timeStamp) {
		final String filename = "WallpaperDesigner_" + timeStamp + SettingsIO.EXTENSION_PNG;
		final File imageFile = BitmapFileIO.saveBitmap2ExternalStorage(bitmap, StorageHelper.getExternalStorageImages(), filename);
		rescanMedia(context, imageFile);
	}

	private void rescanMedia(final Context context, final File imageFile) {
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

	protected void drawNonPremiumText(final Canvas canvas, final String patternName) {
		if (!Settings.isPremium()) {
			final int fontSize = Math.round(canvas.getWidth() * 0.015f);
			int dropShadowRadius = Math.round(canvas.getWidth() * 0.01f);
			if (dropShadowRadius < 3) {
				dropShadowRadius = 3;
			}
			final String text = patternName + " - Created with 'The Wallpaper Designer' ...please get the Premium Version, to remove this text :-)";
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
