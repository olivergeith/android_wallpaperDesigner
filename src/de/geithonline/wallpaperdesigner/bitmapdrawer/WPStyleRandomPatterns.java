package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class WPStyleRandomPatterns extends WPStylePattern {

	@Override
	public synchronized Bitmap drawBitmap() {
		return drawBitmap(Settings.getWidth(), Settings.getHeight());
	}

	@Override
	public synchronized Bitmap drawBitmap(final int width, final int height) {
		bWidth = width;
		bHeight = height;

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

		final int dropShadowRadius = getDropShadowRadius();

		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		int anzahlPatterns = Settings.getAnzahlPatterns();
		if (Settings.getSelectedPatternVariant().equalsIgnoreCase("Stripe")) {
			// reduce Anzahl Patterns to maximum 200
			if (anzahlPatterns > 200) {
				anzahlPatterns = 200;
				Log.i("Patterns", "Anzahl Patterns reduced to 200 because of choosen Stripe Pattern!");
			}
		}

		final int blurLevel2 = anzahlPatterns * 6 / 10;
		final int blurLevel3 = anzahlPatterns * 8 / 10;

		// Zeichnen
		for (int i = 0; i < anzahlPatterns; i++) {
			paint.setStyle(Style.FILL);
			final int radius = getRandomInt(minRadius, maxRadius);

			// random koordinate an der gemalt werden soll
			final Point point = getRandomPoint();
			final int x = point.x;
			final int y = point.y;
			// davon die aktuelle Farbe
			int pcolor = getColorFromBitmap(bitmap, refbitmap, x, y);

			if (Settings.isRandomizeBrightness()) {
				pcolor = Randomizer.randomizeColorBrightness(pcolor, Settings.getRandomizeColorBrighnessRange());
			}
			if (Settings.isRandomizeColors()) {
				pcolor = Randomizer.randomizeColor(pcolor, Settings.getRandomizeColorRange());
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

			if (Settings.isBlurPatterns()) {
				if (i == blurLevel2) {
					bitmap = BitmapBlurrer.doBlur(bitmap, 5, true);
				}
				if (i == blurLevel3) {
					bitmap = BitmapBlurrer.doBlur(bitmap, 3, true);
				}
			}
		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern() + "/" + Settings.getSelectedPatternVariant());
		refbitmap.recycle();
		return bitmap;
	}

	private Point getRandomPoint() {
		final int x = getRandomInt(0, bWidth - 1);
		final int y = getRandomInt(0, bHeight - 1);
		return new Point(x, y);
	}

}
