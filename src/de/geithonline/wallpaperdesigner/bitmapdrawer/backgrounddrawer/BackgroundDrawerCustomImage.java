package de.geithonline.wallpaperdesigner.bitmapdrawer.backgrounddrawer;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import de.geithonline.android.basics.utils.BitmapHelper;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.WallpaperDesigner;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class BackgroundDrawerCustomImage {

	public static void draw(final Canvas canvas) {
		final String filePath = Settings.getCustomBackgroundFilePath();
		final File bgF = new File(filePath);
		if (bgF.exists()) {
			Log.i("Loading Custom Image", "Loading CustomBG: " + filePath);
			final Bitmap bgInput = BitmapHelper.getCustomImageSampled(filePath, Math.round(canvas.getWidth()), canvas.getHeight());
			final Bitmap bitmap = Bitmap.createScaledBitmap(bgInput, canvas.getWidth(), canvas.getHeight(), true);
			if (!bitmap.equals(bgInput)) {
				bgInput.recycle();
			}
			canvas.drawBitmap(bitmap, 0, 0, null); // null = no Paint()
		} else {
			drawBitmapFromResource(canvas);
		}

	}

	private static void drawBitmapFromResource(final Canvas canvas) {
		final Bitmap bgInput = BitmapFactory.decodeResource(WallpaperDesigner.getContext().getResources(), R.drawable.background);
		final Bitmap bitmap = Bitmap.createScaledBitmap(bgInput, canvas.getWidth(), canvas.getHeight(), true);
		if (!bitmap.equals(bgInput)) {
			bgInput.recycle();
		}
		canvas.drawBitmap(bitmap, 0, 0, null); // null = no Paint()
	}

}
