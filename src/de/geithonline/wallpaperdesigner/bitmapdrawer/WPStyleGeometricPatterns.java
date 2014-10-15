package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class WPStyleGeometricPatterns extends WPStylePattern {

	@Override
	public synchronized Bitmap drawBitmap() {
		bWidth = Settings.getWidth();
		bHeight = Settings.getHeight();

		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		BackgroundDrawer.drawBackground(bitmapCanvas);

		final Bitmap refbitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		final Canvas refbitmapCanvas = new Canvas(refbitmap);
		BackgroundDrawer.drawBackground(refbitmapCanvas);

		// initializing some values depending on BitmapSize
		int maxRadius = Math.round(bWidth * 0.04f * Settings.getPatternSizeFactor());
		if (maxRadius < 30) {
			maxRadius = 30;
		}
		int minRadius = Math.round(maxRadius * Settings.getPatternMinSizeFactor());
		if (minRadius < 5) {
			minRadius = 5;
		}

		int dropShadowRadius = Math.round(bWidth * 0.01f);
		if (dropShadowRadius < 5) {
			dropShadowRadius = 5;
		}

		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		final GeometricRaster raster = new GeometricRaster(bWidth, bHeight, maxRadius, 0.5f);
		int i = 0;
		// Zeichnen
		for (int w = 0; w < raster.getAnzW(); w++) {
			for (int h = 0; h < raster.getAnzH(); h++) {
				paint.setStyle(Style.FILL);
				final int radius = getRandomInt(minRadius, maxRadius);

				// random koordinate an der gemalt werden soll
				final int x = w * raster.getAbstand();
				final int y = h * raster.getAbstand();
				// davon die aktuelle Farbe
				int pcolor = getColorFromBitmap(bitmap, refbitmap, x, y);

				if (Settings.isRandomizeBrightness()) {
					pcolor = randomizeColorBrightness(pcolor, Settings.getRandomizeColorBrighnessRange());
				}
				if (Settings.isRandomizeColors()) {
					pcolor = randomizeColor(pcolor, Settings.getRandomizeColorRange());
				}
				paint.setColor(pcolor);

				paint.setAlpha(getRandomInt(Settings.getMinOpacity(), Settings.getMaxOpacity()));

				switch (Settings.getDropShadowType()) {
				default:
				case "No":
					break;
				case "Random":
					final int sx = getRandomInt(0, bWidth - 1);
					final int sy = getRandomInt(0, bHeight - 1);
					final int scolor = getColorFromBitmap(bitmap, refbitmap, sx, sy);
					paint.setShadowLayer(dropShadowRadius, 0, 0, scolor);
					break;
				case "Opposite":
					paint.setShadowLayer(dropShadowRadius, 0, 0, getColorFromBitmap(bitmap, refbitmap, bWidth - 1 - x, bHeight - 1 - y));
					break;
				case "Darker":
					paint.setShadowLayer(dropShadowRadius, 0, 0, ColorHelper.changeBrightness(pcolor, Settings.getDropShadowDarkness()));
					break;
				case "Select":
					paint.setShadowLayer(dropShadowRadius, 0, 0, Settings.getDropShadowColor());
					break;
				}
				drawPattern(x, y, paint, radius, i);
				i++;
			}
		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern());
		refbitmap.recycle();
		return bitmap;
	}

}
