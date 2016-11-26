package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.MainActivity.BitmapWorkerTask;
import de.geithonline.wallpaperdesigner.settings.DesignIO;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.MediaScannerHelper;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public abstract class WPStyle implements IWPStyle {

	protected Canvas bitmapCanvas;
	protected Bitmap bitmap;

	@Override
	public Bitmap getBitmap() {
		return bitmap;
	}

	@Override
	public abstract Bitmap drawBitmap(BitmapWorkerTask bitmapWorkerTask);

	@SuppressLint("SimpleDateFormat")
	@Override
	public synchronized void save(final Context context, final boolean saveSettings) {
		final String timeStamp = FileIOHelper.getTimeStampForFile();
		saveBigImage(context, timeStamp);
		if (saveSettings) {
			saveSmallImageAndSettings(context);
		} else {

		}
	}

	@Override
	public synchronized void saveSmallImageAndSettings(final Context context) {
		final String timeStamp = FileIOHelper.getTimeStampForFile();
		final int w = bitmap.getWidth();
		final int h = bitmap.getHeight();

		final int dw = 800;
		final int dh = h * dw / w;

		final Bitmap small = Bitmap.createScaledBitmap(bitmap, dw, dh, true);

		// Generate Filename
		final String pattern = Settings.getSelectedPattern() + "_" + Settings.getSelectedPatternVariant();
		final String layout = Settings.getSelectedMainLayout() + " (" + Settings.getSelectedMainLayoutVariante() + ")";
		final String jpgFilename = pattern + " " + layout + DesignIO.MARKER + timeStamp + DesignIO.EXTENSION_JPG;

		final File smallJpgFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(small, StorageHelper.getDesignsDir(), jpgFilename, 80);
		MediaScannerHelper.rescanMedia(context, smallJpgFile);
		small.recycle();
		// Saving corresponding Settings
		final String prefFileName = FileIOHelper.replaceExtension(smallJpgFile.getAbsolutePath(), DesignIO.EXTENSION_JPG, DesignIO.EXTENSION_PREF);
		final File settingsFile = new File(prefFileName);
		DesignIO.savePreferences(Settings.prefs, settingsFile);
	}

	public synchronized void saveBigImage(final Context context, final String timeStamp) {
		String filename;
		File imageFile;
		switch (Settings.getImageOutputFormat()) {
		default:
		case PNG:
			filename = "WallpaperDesigner_" + timeStamp + DesignIO.EXTENSION_PNG;
			imageFile = BitmapFileIO.saveBitmap2ExternalStorage(bitmap, StorageHelper.getWallpaperImagesDir(), filename);
			break;
		case JPG:
			filename = "WallpaperDesigner_" + timeStamp + DesignIO.EXTENSION_JPG;
			imageFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(bitmap, StorageHelper.getWallpaperImagesDir(), filename, Settings.getJpgCompression());
			break;
		}
		MediaScannerHelper.rescanMedia(context, imageFile);
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
