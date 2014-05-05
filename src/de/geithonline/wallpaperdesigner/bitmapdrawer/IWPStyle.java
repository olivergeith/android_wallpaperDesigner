package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.content.Context;
import android.graphics.Bitmap;

public interface IWPStyle {

	public Bitmap drawBitmap();

	public void recycleBitmap();

	public void save(Context context);

}