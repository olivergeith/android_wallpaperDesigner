
package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.MainActivity.BitmapWorkerTask;
import de.geithonline.wallpaperdesigner.bitmapdrawer.backgrounddrawer.BackgroundDrawer;
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.PatternDrawer;
import de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer.RadiusCalculator;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.AbstractRaster;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class BmpRenderer extends BaseBmpRenderer {

	private BitmapWorkerTask task;
	private final String layout;
	private final String layoutVariante;
	protected Canvas bitmapCanvas;
	private PatternDrawer patternDrawer;
	private int bWidth;
	private int bHeight;

	public BmpRenderer(final String layout, final String layoutVariante) {
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

		// create new Bitmap and Canvas from size
		bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);
		BackgroundDrawer.drawBackground(bitmapCanvas, Settings.isSameGradientAsPatterns());
		final Bitmap refbitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		final Canvas refbitmapCanvas = new Canvas(refbitmap);
		BackgroundDrawer.drawBackground(refbitmapCanvas, true);
		bitmap = BackgroundDrawer.blurrIfNessesary(bitmap);

		// Pattern Drawer Object bauen
		final PaintManager pm = new PaintManager(bWidth, bHeight);
		patternDrawer = new PatternDrawer(bitmapCanvas, pm);

		// initializing some values depending on BitmapSize
		int maxRadius = Math.round(bWidth * 0.04f * Settings.getPatternSizeFactor());
		if (maxRadius < 10) {
			maxRadius = 10;
		}
		int minRadius = Math.round(maxRadius * Settings.getPatternMinSizeFactor());
		if (minRadius < 5) {
			minRadius = 5;
		}

		final AbstractRaster raster = RasterFactory.getRaster(layout, layoutVariante, width, height, maxRadius, Settings.getOverlapping());

		task.settingMax(raster.getAnzahlPatterns());

		if (Settings.getSelectedPattern().equalsIgnoreCase("Material")) {
			MaterialPath.initFlippy();
		}

		final int blurLevel1 = raster.getAnzahlPatterns() * Settings.getBlurrStage1() / 100;
		final int blurLevel2 = raster.getAnzahlPatterns() * Settings.getBlurrStage2() / 100;
		final int blurLevel3 = raster.getAnzahlPatterns() * Settings.getBlurrStage3() / 100 - 1;
		final int anzahlPatterns = raster.getAnzahlPatterns();

		final RadiusCalculator radiusCalculator = new RadiusCalculator(anzahlPatterns, minRadius, maxRadius, Settings.getRadiusType());

		switch (Settings.getSelectedPattern()) {
		default:
			break;
		case "Scene":
			break;
		}

		// Zeichnen
		for (int i = 0; i < anzahlPatterns; i++) {
			if (task.isCancelled()) {
				// stopping Progress...is task was canceled
				i = anzahlPatterns;
			}
			if (i % 100 == 0) {
				System.gc();
			}
			task.settingProgress(i, bitmap, "Rendering Patterns ...");
			// getting Radius
			final int radius = radiusCalculator.getRadius(i);

			// random koordinate an der gemalt werden soll
			final Point p = raster.drawNextPoint();
			final int x = p.x;
			final int y = p.y;
			// davon die aktuelle Farbe
			final int pcolor = getColorFromBitmap(bitmap, refbitmap, x, y);
			final int oppositeColor = getOppositeColorFromBitmap(bitmap, refbitmap, x, y);
			final int randomColor = getRandomColorFromBitmap(bitmap, refbitmap, x, y);
			// setting up paint
			pm.initPaintForPattern(pcolor);
			pm.setupDropShadowForPattern(pcolor, oppositeColor, randomColor);
			patternDrawer.drawPattern(x, y, radius, i);

			if (Settings.isBlurPatterns()) {
				if (i == blurLevel1 && Settings.getBlurrAmount1() > 0) {
					task.settingProgress(i, bitmap, "Blurring Image (Stage 1) ...");
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, Settings.getBlurrAmount1(), true);
					System.gc();
				}
				if (i == blurLevel2 && Settings.getBlurrAmount2() > 0) {
					task.settingProgress(i, bitmap, "Blurring Image (Stage 2) ...");
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, Settings.getBlurrAmount2(), true);
					System.gc();
				}
				if (i == blurLevel3 && Settings.getBlurrAmount3() > 0) {
					task.settingProgress(i, bitmap, "Blurring Image (Stage 3) ...");
					System.gc();
					bitmap = BitmapBlurrer.doBlur(bitmap, Settings.getBlurrAmount3(), true);
					System.gc();
				}
			}

		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern() + "/" + Settings.getSelectedPatternVariant());
		refbitmap.recycle();
		return bitmap;
	}

	// #########################################################################################
	// Service methods
	// #########################################################################################
	private int getOppositeColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {
		return getColorFromBitmap(bitmap, refbmp, bWidth - 1 - x, bHeight - 1 - y);
	}

	private int getRandomColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {
		final int sx = Randomizer.getRandomInt(0, bWidth - 1);
		final int sy = Randomizer.getRandomInt(0, bHeight - 1);
		final int scolor = getColorFromBitmap(bitmap, refbmp, sx, sy);
		return scolor;
	}

	private int getColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {
		int xx = Math.min(x, bWidth - 1);
		int yy = Math.min(y, bHeight - 1);
		xx = Math.max(xx, 0);
		yy = Math.max(yy, 0);
		return refbmp.getPixel(xx, yy);
	}

}
