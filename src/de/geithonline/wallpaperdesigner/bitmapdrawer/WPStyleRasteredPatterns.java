package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.MainActivity.BitmapWorkerTask;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.AbstractRaster;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class WPStyleRasteredPatterns extends WPStylePattern {

	private BitmapWorkerTask task;
	private final String layout;
	private final String layoutVariante;

	public WPStyleRasteredPatterns(final String layout, final String layoutVariante) {
		this.layout = layout;
		this.layoutVariante = layoutVariante;
	}

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
		if (Settings.getGradientDirection().startsWith("4-Color Tornado")) {
			bitmap = BitmapBlurrer.doBlur(bitmap, 40, true);
			// refbitmap = BitmapBlurrer.doBlur(refbitmap, 40, true);
		}

		// initializing some values depending on BitmapSize
		int maxRadius = Math.round(bWidth * 0.04f * Settings.getPatternSizeFactor());
		if (maxRadius < 10) {
			maxRadius = 10;
		}
		int minRadius = Math.round(maxRadius * Settings.getPatternMinSizeFactor());
		if (minRadius < 5) {
			minRadius = 5;
		}

		final int dropShadowRadius = getDropShadowRadius();

		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		final AbstractRaster raster = RasterFactory.getRaster(layout, layoutVariante, width, height, maxRadius, Settings.getOverlapping());

		task.settingMax(raster.getAnzahlPatterns());

		if (Settings.getSelectedPattern().equalsIgnoreCase("Material")) {
			MaterialPath.initFlippy();
		}

		final int blurLevel1 = raster.getAnzahlPatterns() * Settings.getBlurrStage1() / 100;
		final int blurLevel2 = raster.getAnzahlPatterns() * Settings.getBlurrStage2() / 100;
		final int blurLevel3 = raster.getAnzahlPatterns() * Settings.getBlurrStage3() / 100;
		final int anzahlPatterns = raster.getAnzahlPatterns();
		// Zeichnen
		for (int i = 0; i < anzahlPatterns; i++) {
			if (i % 100 == 0) {
				System.gc();
			}
			task.settingProgress(i);
			paint.setStyle(Style.FILL);
			final int radius = getRandomInt(minRadius, maxRadius);

			// random koordinate an der gemalt werden soll
			final Point p = raster.drawNextPoint();
			final int x = p.x;
			final int y = p.y;
			// davon die aktuelle Farbe
			int pcolor = getColorFromBitmap(bitmap, refbitmap, x, y);

			if (Settings.isRandomizeBrightness()) {
				final int range = Settings.getRandomizeColorBrighnessRange();
				final int adjust = Randomizer.getRandomInt(-range, range);
				pcolor = ColorHelper.adjustColorBrightness(pcolor, adjust);
			}
			if (Settings.isRandomizeSaturation()) {
				final int range = Settings.getRandomizeSaturationRange();
				final float dSaturation = Randomizer.getRandomFloat(-range, range) / 100;
				pcolor = ColorHelper.adjustHSV(pcolor, 0, dSaturation, 0);
			}
			if (Settings.isRandomizeColors()) {
				// pcolor = Randomizer.randomizeHue(pcolor, Settings.getRandomizeColorRange());
				pcolor = Randomizer.randomizeColor(pcolor, Settings.getRandomizeColorRange(), Settings.getColorRandomizingType());
			}

			// pcolor = ColorHelper.setSaturation(pcolor, 0.5f);
			paint.setColor(pcolor);

			paint.setAlpha(getRandomInt(Settings.getMinOpacity(), Settings.getMaxOpacity()));

			setupDropShadow(refbitmap, dropShadowRadius, paint, x, y, pcolor);

			drawPattern(x, y, paint, radius, i);

			if (Settings.isBlurPatterns()) {
				if (i == blurLevel1) {
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, Settings.getBlurrAmount1(), true);
				}
				if (i == blurLevel2) {
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, Settings.getBlurrAmount2(), true);
				}
				if (i == blurLevel3) {
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, Settings.getBlurrAmount3(), true);
				}
			}

		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern() + "/" + Settings.getSelectedPatternVariant());
		refbitmap.recycle();
		return bitmap;
	}
}
