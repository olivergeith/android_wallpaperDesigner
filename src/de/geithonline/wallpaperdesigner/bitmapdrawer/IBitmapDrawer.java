package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface IBitmapDrawer {
	public void draw(final int level, final Canvas canvas, boolean forcedraw);

	public Bitmap drawIcon(final int level, final int size);

	public boolean supportsShowPointer();

	public boolean supportsPointerColor();

	public boolean supportsShowRand();

	public boolean supportsLogo();

	public boolean supportsFlip();
}
