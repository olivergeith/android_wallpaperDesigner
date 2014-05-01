package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.content.Context;
import android.graphics.Bitmap;

public interface IDrawer {

	public Bitmap drawBitmap();

	public void save(Context context);

}
