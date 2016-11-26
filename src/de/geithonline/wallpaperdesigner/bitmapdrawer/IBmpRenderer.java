package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import de.geithonline.wallpaperdesigner.MainActivity.BitmapWorkerTask;

public interface IBmpRenderer {

	public Bitmap drawBitmap(BitmapWorkerTask bitmapWorkerTask);

	public Bitmap drawBitmap(final int width, final int height);

	public void recycleBitmap();

	public void save(Context context, final boolean withSettings);

	public void saveSmallImageAndSettings(final Context context);

	public Bitmap getBitmap();

}
