package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class WPStyleSquares extends WPStyle {

	protected int bWidth = 2560;
	protected int bHeight = 1600;

	@Override
	public synchronized Bitmap drawBitmap() {
		bWidth = Settings.getWidth();
		bHeight = Settings.getHeight();

		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		BackgroundDrawer.drawBackground(bitmapCanvas);

		// initializing some values depending on BitmapSize
		final int fontSize = Math.round(bWidth * 0.02f);
		int maxRadius = Math.round(bWidth * 0.04f);
		if (maxRadius < 30) {
			maxRadius = 30;
		}
		int dropShadowRadius = Math.round(bWidth * 0.01f);
		if (dropShadowRadius < 10) {
			dropShadowRadius = 10;
		}

		drawNonPremiumText(bitmapCanvas, fontSize);
		return bitmap;
	}

}
