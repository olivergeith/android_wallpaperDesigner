package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.Log;
import de.geithonline.wallpaperdesigner.MainActivity.BitmapWorkerTask;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class WPStyleRandomPatterns extends WPStylePattern {

	private BitmapWorkerTask task;

	@Override
	public synchronized Bitmap drawBitmap(final BitmapWorkerTask bitmapWorkerTask) {
		task = bitmapWorkerTask;
		return drawBitmap(Settings.getWidth(), Settings.getHeight());
	}

	@Override
	public synchronized Bitmap drawBitmap(final int width, final int height) {
		bWidth = width;
		bHeight = height;

		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		BackgroundDrawer.drawBackground(bitmapCanvas, Settings.isSameGradientAsPatterns());

		final Bitmap refbitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		final Canvas refbitmapCanvas = new Canvas(refbitmap);
		BackgroundDrawer.drawBackground(refbitmapCanvas, true);

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
		if (Settings.getSelectedPattern().equalsIgnoreCase("Material")) {
			MaterialPath.initFlippy();
			// reduce Anzahl Patterns to maximum 200
			if (anzahlPatterns > 300) {
				anzahlPatterns = 300;
				Log.i("Patterns", "Anzahl Patterns reduced to 300 because of choosen Material Pattern!");
			}
		}

		final int blurLevel2 = anzahlPatterns * 6 / 10;
		final int blurLevel3 = anzahlPatterns * 8 / 10;

		task.settingMax(anzahlPatterns);
		// Zeichnen
		for (int i = 0; i < anzahlPatterns; i++) {
			if (i % 100 == 0) {
				System.gc();
			}
			task.settingProgress(i);
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

			doDropShadow(refbitmap, dropShadowRadius, paint, x, y, pcolor);

			drawPattern(x, y, paint, radius, i);

			if (Settings.isBlurPatterns()) {
				if (i == blurLevel2) {
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, 5, true);
				}
				if (i == blurLevel3) {
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, 3, true);
				}
			}
		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern() + "/" + Settings.getSelectedPatternVariant());
		refbitmap.recycle();
		return bitmap;
	}

	public void doDropShadow(final Bitmap refbitmap, final int dropShadowRadius, final Paint paint, final int x,
			final int y, final int pcolor) {

		final int dX = Settings.getDropShadowOffsetX();
		final int dY = Settings.getDropShadowOffsetY();
		switch (Settings.getDropShadowType()) {
		default:
		case "No":
			break;
		case "Random":
			final int sx = getRandomInt(0, bWidth - 1);
			final int sy = getRandomInt(0, bHeight - 1);
			final int scolor = getColorFromBitmap(bitmap, refbitmap, sx, sy);
			paint.setShadowLayer(dropShadowRadius, dX, dY, scolor);
			break;
		case "Opposite":
			paint.setShadowLayer(dropShadowRadius, dX, dY,
					getColorFromBitmap(bitmap, refbitmap, bWidth - 1 - x, bHeight - 1 - y));
			break;
		case "Darker":
			paint.setShadowLayer(dropShadowRadius, dX, dY,
					ColorHelper.changeBrightness(pcolor, Settings.getDropShadowDarkness()));
			break;
		case "Select":
			paint.setShadowLayer(dropShadowRadius, dX, dY, Settings.getDropShadowColor());
			break;
		}
	}

	private Point getRandomPoint() {
		final int x = getRandomInt(0, bWidth - 1);
		final int y = getRandomInt(0, bHeight - 1);
		return new Point(x, y);
	}

}
