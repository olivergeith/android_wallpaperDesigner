package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.StarPathInvert;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;

public class Stars extends Drawer {

	protected int bWidth = 2560;
	protected int bHeight = 1600;
	private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Override
	public synchronized Bitmap drawBitmap() {
		bWidth = Settings.getWidth();
		bHeight = Settings.getHeight();
		final Rect sizesRect = new Rect(0, 0, bWidth, bHeight);

		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		bitmapCanvas.drawRect(sizesRect, Settings.getBackgroundPaint(bWidth, bHeight));

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

		// Zeichnen
		for (int i = 0; i < 1000; i++) {

			// random koordinate an der gemalt werden soll
			final int x = getRandomInt(0, bWidth - 1);
			final int y = getRandomInt(0, bHeight - 1);
			// davon die aktuelle Farbe
			int pcolor = bitmap.getPixel(x, y);

			if (Settings.isRandomizeColors()) {
				pcolor = randomizeColor(pcolor, Settings.getRandomizeColorRange());
			}

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
				if (Settings.isRandomizeDropShadowColors()) {
					final int sx = getRandomInt(0, bWidth - 1);
					final int sy = getRandomInt(0, bHeight - 1);
					final int scolor = bitmap.getPixel(sx, sy);
					paint.setShadowLayer(dropShadowRadius, 0, 0, scolor);
				} else {
					paint.setShadowLayer(dropShadowRadius, 0, 0, Settings.getDropShadowColor());
				}
			}
			final int radius = getRandomInt(maxRadius / 10, maxRadius);
			switch (Settings.getSelectedDrawer()) {
			default:
				// case "Text":
				// paint.setTextSize(radius * 4);
				// paint.setTextAlign(Align.CENTER);
				// bitmapCanvas.drawText("Emmylou", x, y, paint);
				// break;
			case "Letters":
				final int letterindex = getRandomInt(0, letters.length() - 1);
				final char c = letters.charAt(letterindex);
				if (getRandomBoolean()) {
					paint.setTypeface(Typeface.DEFAULT_BOLD);
				} else {
					paint.setTypeface(Typeface.DEFAULT);
				}

				paint.setTextSize(radius * 3);
				paint.setTextAlign(Align.CENTER);
				bitmapCanvas.drawText("" + c, x, y, paint);
				break;
			case "5-Stars":
				if (getRandomBoolean()) {
					bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2), paint);
				} else {
					bitmapCanvas.drawPath(new StarPathInvert(5, new Point(x, y), radius, radius / 2), paint);
				}
				break;
			case "Stars":
				final int zacken = getRandomInt(5, 10);
				if (getRandomBoolean()) {
					bitmapCanvas.drawPath(new StarPath(zacken, new Point(x, y), radius, radius / 2), paint);
				} else {
					bitmapCanvas.drawPath(new StarPathInvert(zacken, new Point(x, y), radius, radius / 2), paint);
				}
				break;
			case "XEck":
				bitmapCanvas.drawPath(new XEckPath(6, new Point(x, y), radius), paint);
				break;
			case "Bubbles":
				bitmapCanvas.drawCircle(x, y, radius, paint);
				break;
			}
		}

		drawNonPremiumText(bitmapCanvas, fontSize);
		return bitmap;
	}

}
