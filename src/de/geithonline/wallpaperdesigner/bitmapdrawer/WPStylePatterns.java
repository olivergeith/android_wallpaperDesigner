package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.GearPath;
import de.geithonline.wallpaperdesigner.shapes.SawPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;

public class WPStylePatterns extends WPStyle {

	protected int bWidth = 2560;
	protected int bHeight = 1600;
	private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Override
	public synchronized Bitmap drawBitmap() {
		bWidth = Settings.getWidth();
		bHeight = Settings.getHeight();

		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		BackgroundDrawer.drawBackground(bitmapCanvas);
		// bitmapCanvas.drawRect(sizesRect, Settings.getBackgroundPaint(bWidth,
		// bHeight));

		final Bitmap refbitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		final Canvas refbitmapCanvas = new Canvas(refbitmap);
		BackgroundDrawer.drawBackground(refbitmapCanvas);
		// refbitmapCanvas.drawRect(sizesRect,
		// Settings.getBackgroundPaint(bWidth, bHeight));

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
			int pcolor = getColorFromBitmap(bitmap, refbitmap, x, y);

			if (Settings.isRandomizeColors()) {
				pcolor = randomizeColor(pcolor, Settings.getRandomizeColorRange());
			}

			final Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(pcolor);
			if (Settings.isRandomizeAlpha()) {
				paint.setAlpha(getRandomInt(255 - Settings.getRandomizeAlphaRange(), 255));
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
					final int scolor = getColorFromBitmap(bitmap, refbitmap, sx, sy);
					paint.setShadowLayer(dropShadowRadius, 0, 0, scolor);
				} else {
					paint.setShadowLayer(dropShadowRadius, 0, 0, Settings.getDropShadowColor());
				}
			}
			final int radius = getRandomInt(10, maxRadius);
			drawPattern(x, y, paint, radius);
		}

		drawNonPremiumText(bitmapCanvas, fontSize);
		refbitmap.recycle();
		return bitmap;
	}

	private void drawPattern(final int x, final int y, final Paint paint, final int radius) {
		switch (Settings.getSelectedPattern()) {
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
		case "Saw":
			bitmapCanvas.drawPath(new SawPath(20, new Point(x, y), radius, false, getRandomBoolean()), paint);
			break;
		case "Saw filled":
			bitmapCanvas.drawPath(new SawPath(20, new Point(x, y), radius, true, getRandomBoolean()), paint);
			break;
		case "Saw mixed":
			bitmapCanvas.drawPath(new SawPath(20, new Point(x, y), radius, getRandomBoolean(), getRandomBoolean()), paint);
			break;
		case "Stars":
			bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, false, getRandomBoolean()), paint);
			break;
		case "Stars filled":
			bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, getRandomBoolean()), paint);
			break;
		case "Stars mixed":
			bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, getRandomBoolean(), getRandomBoolean()), paint);
			break;
		case "Gears":
			final int zaehne = 15;
			bitmapCanvas.drawPath(new GearPath(zaehne, new Point(x, y), radius, false), paint);
			break;
		case "Gears filled":
			final int zf = getRandomInt(12, 20);
			bitmapCanvas.drawPath(new GearPath(zf, new Point(x, y), radius, true), paint);
			break;
		case "Gears mixed":
			final int zm = getRandomInt(12, 20);
			bitmapCanvas.drawPath(new GearPath(zm, new Point(x, y), radius, getRandomBoolean()), paint);
			break;
		case "Pentagon":
			bitmapCanvas.drawPath(new XEckPath(5, new Point(x, y), radius), paint);
			break;
		case "Hexagon":
			bitmapCanvas.drawPath(new XEckPath(6, new Point(x, y), radius), paint);
			break;
		case "Bubbles":
			bitmapCanvas.drawCircle(x, y, radius, paint);
			break;
		}
	}

	private int getColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {
		if (Settings.isDynamicColoring()) {
			return bmp.getPixel(x, y);
		} else {
			return refbmp.getPixel(x, y);
		}

	}

}
