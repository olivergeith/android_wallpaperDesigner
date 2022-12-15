
package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import de.geithonline.wallpaperdesigner.settings.DesignIO;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.tasks.AsyncTaskWithProgress;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;
import de.geithonline.wallpaperdesigner.utils.MediaScannerHelper;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public abstract class BaseBmpRenderer implements IBmpRenderer {

	protected Bitmap bitmap;

	@Override
	public abstract Bitmap drawBitmap(AsyncTaskWithProgress bitmapWorkerTask);

	@Override
	public Bitmap getBitmap() {
		return bitmap;
	}

	@Override
	public void recycleBitmap() {
		if (bitmap != null) {
			bitmap.recycle();
		}
	}

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

	public synchronized void saveBigImage(final Context context, final String timeStamp) {
		String filename;
		File imageFile;
		switch (Settings.getImageOutputFormat()) {
		default:
		case PNG:
			filename = "WallpaperDesigner_" + timeStamp + DesignIO.EXTENSION_PNG;
			imageFile = BitmapFileIO.saveBitmap2ExternalStorage(bitmap, StorageHelper.getWallpaperImagesDir(),
					filename);
			break;
		case JPG:
			filename = "WallpaperDesigner_" + timeStamp + DesignIO.EXTENSION_JPG;
			imageFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(bitmap, StorageHelper.getWallpaperImagesDir(),
					filename, Settings.getJpgCompression());
			break;
		}
		MediaScannerHelper.rescanMedia(context, imageFile);
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

		final File smallJpgFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(small, StorageHelper.getDesignsDir(),
				jpgFilename, 80);
		MediaScannerHelper.rescanMedia(context, smallJpgFile);
		small.recycle();
		// Saving corresponding Settings
		final String prefFileName = FileIOHelper.replaceExtension(smallJpgFile.getAbsolutePath(),
				DesignIO.EXTENSION_JPG, DesignIO.EXTENSION_PREF);
		final File settingsFile = new File(prefFileName);
		DesignIO.savePreferences(Settings.prefs, settingsFile);
	}

}
