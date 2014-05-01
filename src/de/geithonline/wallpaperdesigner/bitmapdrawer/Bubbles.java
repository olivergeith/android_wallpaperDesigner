package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import de.geithonline.android.basics.grafics.shapes.StarPath;
import de.geithonline.android.basics.grafics.shapes.StarPathInvert;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class Bubbles extends Drawer {

	protected int bWidth = 2560;
	protected int bHeight = 1600;
	protected Rect sizesRect = new Rect(0, 0, bWidth, bHeight);

	@Override
	public Bitmap drawBitmap() {
		if (bitmap != null) {
			bitmap.recycle();
		}
		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		bitmapCanvas.drawRect(sizesRect, Settings.getWallpaperBackgroundPaint(bWidth, bHeight));

		for (int i = 0; i < 1000; i++) {
			final int x = getRandomInt(0, bWidth - 1);
			final int y = getRandomInt(0, bHeight - 1);
			// Log.i("Geith", "x=" + x);
			// Log.i("Geith", "y=" + y);
			final int pcolor = bitmap.getPixel(x, y);

			final int radius = getRandomInt(10, 100);
			final Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(pcolor);
			if (Settings.isRandomizeAlpha()) {
				paint.setAlpha(getRandomInt(0, 255));
			} else {
				paint.setAlpha(255);
			}
			if (getRandomBoolean()) {
				paint.setStyle(Style.FILL_AND_STROKE);
				paint.setStrokeWidth(5);
			} else {
				paint.setStyle(Style.FILL);
			}
			if (Settings.isDropShadow()) {
				final int sx = getRandomInt(0, bWidth - 1);
				final int sy = getRandomInt(0, bHeight - 1);
				final int scolor = bitmap.getPixel(sx, sy);
				paint.setShadowLayer(15, 0, 0, scolor);
			}
			// bitmapCanvas.drawCircle(x, y, radius, paint);
			if (getRandomBoolean()) {
				bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2), paint);
			} else {
				bitmapCanvas.drawPath(new StarPathInvert(5, new Point(x, y), radius, radius / 2), paint);
			}

		}

		return bitmap;
	}

}
