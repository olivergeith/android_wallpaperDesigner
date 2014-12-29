package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.MainActivity.BitmapWorkerTask;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.IRaster;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory.RasterPositioning;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class WPStyleRasteredPatterns extends WPStylePattern {

	private final RasterPositioning positioning;
	private BitmapWorkerTask task;

	public WPStyleRasteredPatterns(final RasterPositioning positioning) {
		this.positioning = positioning;
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

		final IRaster raster = RasterFactory
				.getRaster(positioning, width, height, maxRadius, Settings.getOverlapping());

		task.settingMax(raster.getAnzahlPatterns());

		if (Settings.getSelectedPattern().equalsIgnoreCase("Material")) {
			MaterialPath.initFlippy();
		}
		// Zeichnen
		for (int i = 0; i < raster.getAnzahlPatterns(); i++) {
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
				paint.setShadowLayer(dropShadowRadius, 0, 0,
						getColorFromBitmap(bitmap, refbitmap, bWidth - 1 - x, bHeight - 1 - y));
				break;
			case "Darker":
				paint.setShadowLayer(dropShadowRadius, 0, 0,
						ColorHelper.changeBrightness(pcolor, Settings.getDropShadowDarkness()));
				break;
			case "Select":
				paint.setShadowLayer(dropShadowRadius, 0, 0, Settings.getDropShadowColor());
				break;
			}
			drawPattern(x, y, paint, radius, i);
		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern() + "/" + Settings.getSelectedPatternVariant());
		refbitmap.recycle();
		return bitmap;
	}

}
