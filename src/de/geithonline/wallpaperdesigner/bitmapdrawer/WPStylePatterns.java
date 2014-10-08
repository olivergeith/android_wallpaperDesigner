package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.AndroidPath;
import de.geithonline.wallpaperdesigner.shapes.AnkerPath;
import de.geithonline.wallpaperdesigner.shapes.BatPath;
import de.geithonline.wallpaperdesigner.shapes.BlitzPath;
import de.geithonline.wallpaperdesigner.shapes.CloudPath;
import de.geithonline.wallpaperdesigner.shapes.DandelionPath;
import de.geithonline.wallpaperdesigner.shapes.DeathstarPath;
import de.geithonline.wallpaperdesigner.shapes.DotSpiralPath;
import de.geithonline.wallpaperdesigner.shapes.FishPath;
import de.geithonline.wallpaperdesigner.shapes.FlowerPath;
import de.geithonline.wallpaperdesigner.shapes.FlowerV2Path;
import de.geithonline.wallpaperdesigner.shapes.GearPath;
import de.geithonline.wallpaperdesigner.shapes.GhostPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath;
import de.geithonline.wallpaperdesigner.shapes.IgelPath;
import de.geithonline.wallpaperdesigner.shapes.LighthousePath;
import de.geithonline.wallpaperdesigner.shapes.LuftschlangenPath;
import de.geithonline.wallpaperdesigner.shapes.MandalaV1Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV2Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV3Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV4Path;
import de.geithonline.wallpaperdesigner.shapes.NiceFlowerPath;
import de.geithonline.wallpaperdesigner.shapes.PacmanPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath;
import de.geithonline.wallpaperdesigner.shapes.PlanePath;
import de.geithonline.wallpaperdesigner.shapes.RandomPath;
import de.geithonline.wallpaperdesigner.shapes.RectanglePath;
import de.geithonline.wallpaperdesigner.shapes.RingPath;
import de.geithonline.wallpaperdesigner.shapes.RocketPath;
import de.geithonline.wallpaperdesigner.shapes.RosePath;
import de.geithonline.wallpaperdesigner.shapes.SailboatPath;
import de.geithonline.wallpaperdesigner.shapes.SailboatPath2;
import de.geithonline.wallpaperdesigner.shapes.SatelitePath;
import de.geithonline.wallpaperdesigner.shapes.SawPath;
import de.geithonline.wallpaperdesigner.shapes.ShellV1Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV2Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV3Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV4Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV5Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV6Path;
import de.geithonline.wallpaperdesigner.shapes.SkullPath;
import de.geithonline.wallpaperdesigner.shapes.SmileyPath;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.StarCirclePath;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.SunPath;
import de.geithonline.wallpaperdesigner.shapes.UfoPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath2;
import de.geithonline.wallpaperdesigner.shapes.VirusPath3;
import de.geithonline.wallpaperdesigner.shapes.VirusPath4;
import de.geithonline.wallpaperdesigner.shapes.VirusPath5;
import de.geithonline.wallpaperdesigner.shapes.VirusPath6;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
import de.geithonline.wallpaperdesigner.shapes.XmasTreePath;
import de.geithonline.wallpaperdesigner.shapes.ZitronePath;
import de.geithonline.wallpaperdesigner.utils.BitmapBlurrer;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class WPStylePatterns extends WPStyle {

	protected int bWidth = 2560;
	protected int bHeight = 1600;
	private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// private final String letters = "AOKP";

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

		final int anzahlPatterns = Settings.getAnzahlPatterns();

		final int blurLevel1 = anzahlPatterns * 4 / 10;
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

			if (Settings.isBlurPatterns()) {
				// if (i == blurLevel1) {
				// bitmap = BitmapBlurrer.doBlur(bitmap, 7, true);
				// }
				if (i == blurLevel2) {
					bitmap = BitmapBlurrer.doBlur(bitmap, 5, true);
				}
				if (i == blurLevel3) {
					bitmap = BitmapBlurrer.doBlur(bitmap, 3, true);
				}
			}
		}

		drawNonPremiumText(bitmapCanvas, Settings.getSelectedPattern());
		refbitmap.recycle();
		return bitmap;
	}

	private Point getRandomPoint() {
		final int x = getRandomInt(0, bWidth - 1);
		final int y = getRandomInt(0, bHeight - 1);
		return new Point(x, y);
	}

	private void drawPattern(final int x, final int y, final Paint paint, final int radius, final int index) {
		switch (Settings.getSelectedPattern()) {
		case "Text":
			drawText(x, y, paint, radius * 2, index);
			break;

		case "Saw":
			drawSaw(x, y, paint, radius);
			break;

		case "Stars":
			if (Settings.isGlossy()) {
				drawGlossyStar(x, y, paint, radius);
			} else {
				drawStar(x, y, paint, radius);
			}
			break;

		case "Gears":
			drawGear(x, y, paint, radius);
			break;
		case "Spooky":
			drawSpooky(x, y, paint, radius);
			break;
		case "Lemons":
			drawZitrone(x, y, paint, radius);
			break;
		case "XmasTrees":
			drawXmasTree(x, y, paint, radius);
			break;
		case "Star Circles":
			drawStarCircles(x, y, paint, radius);
			break;
		case "Geometrical Shapes":
			drawGeometric(x, y, paint, radius);
			break;
		case "Rings":
			drawRing(x, y, paint, radius);
			break;
		case "Bubbles":
			if (Settings.isGlossy()) {
				drawGlossyBubble(x, y, paint, radius);
			} else {
				drawCircle(x, y, paint, radius);
			}
			break;
		case "Hearts":
			if (Settings.isGlossy()) {
				drawGlossyHeart(x, y, paint, radius);
			} else {
				drawHeart(x, y, paint, radius);
			}
			break;
		case "Lines":
			drawLines(x, y, paint, radius);
			break;
		case "Crop Circles":
			drawDotSpiral(x, y, paint, radius);
			break;
		case "Shells":
			drawShell(x, y, paint, radius);
			break;
		case "Hedgehog":
			drawIgel(x, y, paint, radius);
			break;
		case "PacMan":
			if (Settings.isGlossy()) {
				drawGlossyPacman(x, y, paint, radius);
			} else {
				drawPacMan(x, y, paint, radius);
			}
			break;
		case "Smiley":
			if (Settings.isGlossy()) {
				drawGlossySmiley(x, y, paint, radius);
			} else {
				drawSmiley(x, y, paint, radius);
			}
			break;
		case "Android":
			drawAndroid(x, y, paint, radius);
			break;
		case "Fish":
			drawFisch(x, y, paint, radius);
			break;
		case "Pillows":
			drawPillow(x, y, paint, radius);
			break;
		case "Roses":
			drawRose(x, y, paint, radius);
			break;
		case "Flowers":
			drawFlower(x, y, paint, radius);
			break;
		case "Mandala":
			drawMandala(x, y, paint, radius);
			break;
		case "Skyline":
			final RectF rect = new RectF(x - radius, y, x + radius, bHeight);
			bitmapCanvas.drawRect(rect, paint);
			break;
		case "Clouds":
			drawCloud(x, y, paint, radius);
			break;
		case "Dandelion":
			drawDandelion(x, y, paint, radius);
			break;
		case "Maritim":
			drawMaritim(x, y, paint, radius);
			break;
		case "Rectangles":
			drawRect(x, y, paint, radius);
			break;
		case "Space":
			drawSpace(x, y, paint, radius);
			break;
		case "Deathstars":
			drawDeathstar(x, y, paint, radius);
			break;
		default:
		case "Virus Attack":
			drawVirus(x, y, paint, radius);
			break;
		case "Sun":
			drawSun(x, y, paint, radius);
			break;
		case "Planes":
			drawPlane(x, y, paint, radius);
			break;
		}
	}

	public void drawRose(final int x, final int y, final Paint paint, final int radius) {
		bitmapCanvas.drawPath(new RosePath(new Point(x, y), radius), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new RosePath(new Point(x, y), radius), paint);
		}
	}

	public void drawIgel(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new IgelPath(new Point(x, y + radius / 2), radius * 1.3f);
		if (getFilledBoolean()) {
			mirrorPath(x, y, path);
		}
		rotatePath(x, y + radius / 2, path, Settings.getRotationDegrees(-30, 30));

		bitmapCanvas.drawPath(path, paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSaw(final int x, final int y, final Paint paint, final int radius) {
		final int zaehne = 20;
		final boolean filled = getFilledBoolean();
		final boolean flip = getRandomBoolean();
		bitmapCanvas.drawPath(new SawPath(zaehne, new Point(x, y), radius, filled, flip), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new SawPath(zaehne, new Point(x, y), radius, filled, flip), paint);
		}
	}

	private void drawGear(final int x, final int y, final Paint paint, final int radius) {
		final int zaehne = 15;
		final boolean filled = getFilledBoolean();
		bitmapCanvas.drawPath(new GearPath(zaehne, new Point(x, y), radius, filled), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new GearPath(zaehne, new Point(x, y), radius, filled), paint);
		}
	}

	private boolean getFilledBoolean() {
		boolean filled;
		switch (Settings.getFilledOption()) {
		default:
		case "Not filled":
			filled = false;
			break;
		case "Filled":
			filled = true;
			break;
		case "Randomly mixed":
			filled = getRandomBoolean();
			break;
		}
		return filled;
	}

	private void drawDandelion(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new DandelionPath(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawMaritim(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variante = "V" + nr;
		}
		drawMaritim(x, y, paint, radius, variante);
	}

	private void drawMaritim(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Sailboat V1":
			path = new SailboatPath(new Point(x, y), radius * 1.5f);
			break;
		case "V2":
		case "Sailboat V2":
			path = new SailboatPath2(new Point(x, y), radius * 1.2f);
			break;
		case "V3":
		case "Lighthouse":
			path = new LighthousePath(new Point(x, y), radius * 1.2f);
			break;
		case "V4":
		case "Anchor":
			path = new AnkerPath(new Point(x, y), radius * 1.5f);
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawGeometric(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 5);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed (with Circle)")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}
		drawGeometric(x, y, paint, radius, variant);
	}

	private void drawGeometric(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Triangle":
			path = new XEckPath(3, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V2":
		case "Square":
			path = new XEckPath(4, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V3":
		case "Pentagon":
			path = new XEckPath(5, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V4":
		case "Hexagon":
			path = new XEckPath(6, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V5":
		case "Octagon":
			path = new XEckPath(8, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V6":
		case "Circle":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V3");
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawRing(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawRing(x, y, paint, radius, variant);
	}

	private void drawRing(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Rings V1":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V1");
			break;
		case "V2":
		case "Rings V2":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V2");
			break;
		case "V3":
		case "Rings V3":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V3");
			break;
		case "V4":
		case "Rings V4":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V4");
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSpace(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 8);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Rockets")) {
			final int nr = getRandomInt(0, 5);
			variant = "V" + nr;
		}
		drawSpace(x, y, paint, radius, variant);
	}

	private void drawSpace(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Rocket V1":
			path = new RocketPath(new Point(x, y), radius, getFilledBoolean(), "Rocket V1");
			break;
		case "V2":
		case "Rocket V2":
			path = new RocketPath(new Point(x, y), radius, getFilledBoolean(), "Rocket V2");
			break;
		case "V3":
		case "Rocket V3":
			path = new RocketPath(new Point(x, y), radius, getFilledBoolean(), "Rocket V3");
			break;
		case "V4":
		case "Rocket V4":
			path = new RocketPath(new Point(x, y), radius, getFilledBoolean(), "Rocket V4");
			break;
		case "V5":
		case "Rocket V5":
			path = new RocketPath(new Point(x, y), radius, getFilledBoolean(), "Rocket V5");
			break;
		case "V6":
		case "Ufo V1":
			path = new UfoPath(new Point(x, y), radius, "Ufo V1", getFilledBoolean());
			break;
		case "V7":
		case "Ufo V2":
			path = new UfoPath(new Point(x, y), radius, "Ufo V2", getFilledBoolean());
			break;
		case "V8":
		case "Satellite":
			path = new SatelitePath(new Point(x, y), radius, getFilledBoolean(), "Satellite V1");
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(-45, 45));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawDeathstar(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new DeathstarPath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-15, 15));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			// paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirus(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}

		drawVirus(x, y, paint, radius, variant);
	}

	private void drawVirus(final int x, final int y, final Paint paint, final int radius, final String variant) {
		Path path;
		switch (variant) {
		default:
		case "V1":
			path = new VirusPath(new Point(x, y), radius);
			break;
		case "V2":
			path = new VirusPath2(new Point(x, y), radius, 13, getFilledBoolean());
			break;
		case "V3":
			path = new VirusPath3(new Point(x, y), radius, 17, getFilledBoolean());
			break;
		case "V4":
			path = new VirusPath4(new Point(x, y), radius);
			break;
		case "V5":
			path = new VirusPath5(new Point(x, y), radius);
			break;
		case "V6":
			path = new VirusPath6(new Point(x, y), radius, getFilledBoolean());
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			// paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSun(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new SunPath(5 + Settings.getAnzahlFlowerLeafs(3, 10), new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 36));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawPlane(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 3);
			variant = "V" + nr;
		}
		drawPlane(x, y, paint, radius, variant);
	}

	private void drawPlane(final int x, final int y, final Paint paint, final int radius, final String variant) {
		final Path path = new PlanePath(new Point(x, y), radius, variant);
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlower(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed V1-V3")) {
			final int nr = getRandomInt(0, 3);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed V4-V5")) {
			final int nr = getRandomInt(3, 5);
			variant = "V" + nr;
		}
		drawFlower(x, y, paint, radius, variant);
	}

	private void drawFlower(final int x, final int y, final Paint paint, final int radius, final String variant) {

		Path path;
		switch (variant) {
		default:
		case "V1":
			path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, getFilledBoolean(), 1.0f, "Circle Filling");
			break;
		case "V2":
			path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, getFilledBoolean(), 0.8f, "Circle Filling");
			break;
		case "V3":
			path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, getFilledBoolean(), 0, "V3");
			break;
		case "V4":
			path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, getFilledBoolean(), 1.0f, "Inner Flower");
			break;
		case "V5":
			path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, getFilledBoolean(), 0.8f, "Inner Flower");
			break;
		case "V6":
			path = new FlowerPath(new Point(x, y), radius, Settings.getAnzahlFlowerLeafs(5, 10), 5);
			break;
		case "V7":
			path = new FlowerV2Path(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, getFilledBoolean());
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawLines(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}
		drawLines(x, y, paint, radius, variant);

	}

	private void drawLines(final int x, final int y, final Paint paint, final int radius, final String variant) {
		Path path;
		switch (variant) {
		default:
		case "V1":
		case "Spirals":
			path = new SpiralPath(getRandomInt(2, 5), new Point(x, y), radius, getRandomBoolean());
			break;
		case "V2":
		case "Streamers":
			path = new LuftschlangenPath(getRandomInt(5, 7), new Point(x, y), radius, getRandomBoolean());
			break;
		case "V3":
		case "Maze":
			path = new RandomPath(new Point(x, y), bWidth, bHeight, getRandomInt(10, 40), radius, true);
			break;
		case "V4":
		case "Crickle Crackle":
			path = new RandomPath(new Point(x, y), bWidth, bHeight, getRandomInt(5, 30), radius, false);
			break;
		case "V5":
		case "Blitz":
			path = new BlitzPath(new Point(x, y), getRandomInt(5, 30), radius, getFilledBoolean());
			break;
		}

		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		bitmapCanvas.drawPath(path, paint);
	}

	private void drawDotSpiral(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new DotSpiralPath(3, new Point(x, y), radius, 0);
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			mirrorPath(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawShell(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Shells Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "Shells V" + nr;
		}
		drawShell(x, y, paint, radius, variant);
	}

	public void drawShell(final int x, final int y, final Paint paint, final int radius, final String variant) {
		Path path;
		switch (variant) {
		default:
		case "Shells V1":
			path = new ShellV1Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);
			break;
		case "Shells V2":
			path = new ShellV2Path(15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius * 1.5f, ShellV2Path.VARIANTE_OUTER, getFilledBoolean());
			break;
		case "Shells V3":
			path = new ShellV3Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);
			break;
		case "Shells V4":
			path = new ShellV4Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);
			break;
		case "Shells V5":
			path = new ShellV5Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);
			break;
		case "Shells V6":
			path = new ShellV6Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			mirrorPath(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawMandala(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawMandala(x, y, paint, radius, variant);
	}

	private void drawMandala(final int x, final int y, final Paint paint, final int radius, final String variant) {
		Path path = new ShellV6Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);
		switch (variant) {
		default:
		case "V1":
			path = new MandalaV1Path(2 + 2 * Settings.getAnzahlFlowerLeafs(2, 8), new Point(x, y), radius);
			break;
		case "V2":
			path = new MandalaV2Path(3, 16, new Point(x, y), radius);
			break;
		case "V3":
			path = new MandalaV3Path(3, 2 + 2 * Settings.getAnzahlFlowerLeafs(4, 10), new Point(x, y), radius);
			break;
		case "V4":
			path = new MandalaV4Path(3, new Point(x, y), radius);
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			mirrorPath(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawPacMan(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 2);
			variant = "V" + nr;
		}
		drawPacMan(x, y, paint, radius, variant);
	}

	private void drawPacMan(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final Path path = new PacmanPath(new Point(x, y), radius, variante);
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSmiley(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new SmileyPath(new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawAndroid(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new AndroidPath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-45, 45));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFisch(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawFisch(x, y, paint, radius, variant);
	}

	private void drawFisch(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final Path path = new FishPath(new Point(x, y), radius, variante);

		rotatePath(x, y, path, Settings.getRotationDegrees(-45, 45));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			mirrorPath(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);

		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawText(final int x, final int y, final Paint paint, final int radius, final int index) {
		// Some Paint Inits
		if (getFilledBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}

		final String variant = Settings.getSelectedPatternVariant();
		switch (variant) {
		default:
		case "Numbers":
			final String number = String.format(Locale.GERMANY, "%04d", index);
			drawCustomText(x, y, paint, radius * 1, number);
			break;
		case "Custom Text":
			final String text = Settings.getText();
			drawCustomText(x, y, paint, radius * 1, text);
			break;
		case "Letters":
			final int letterindex = getRandomInt(0, letters.length() - 1);
			final char c = letters.charAt(letterindex);
			drawCustomText(x, y, paint, radius * 2, "" + c);
			break;
		}

	}

	private void drawCustomText(final int x, final int y, final Paint paint, final int radius, final String text) {

		switch (Settings.getTextDrawStyle()) {
		default:
		case "Round":
			drawTextCircle(x, y, paint, radius, text);
			break;
		case "Normal":
			drawTextStraight(x, y, paint, radius, text);
			break;
		case "Angled":
			drawTextAngled(x, y, paint, radius, text);
			break;
		case "Random":
			final int i = getRandomInt(0, 3);
			switch (i) {
			default:
			case 1:
				drawTextCircle(x, y, paint, radius, text);
				break;
			case 2:
				drawTextStraight(x, y, paint, radius, text);
				break;
			case 3:
				drawTextAngled(x, y, paint, radius, text);
				break;
			}
			break;
		}
	}

	private void drawTextStraight(final int x, final int y, final Paint paint, final int radius, final String text) {
		paint.setTextSize(radius);
		paint.setTextAlign(Align.LEFT);
		final Path mArc = new Path();
		final int x2 = bWidth;
		mArc.moveTo(x - radius / 2, y + radius / 2);
		mArc.lineTo(x2, y + radius / 2);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		}

	}

	private void drawTextCircle(final int x, final int y, final Paint paint, final int radius, final String text) {
		paint.setTextSize(radius);
		paint.setTextAlign(Align.CENTER);
		final Path mArc = new Path();
		final RectF oval = getRectForRadius(x, y, radius * 2);
		mArc.addArc(oval, getRandomInt(0, 360), 355);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		}
	}

	private void drawTextAngled(final int x, final int y, final Paint paint, final int radius, final String text) {
		paint.setTextSize(radius);
		paint.setTextAlign(Align.LEFT);
		final Path mArc = new Path();
		mArc.moveTo(x, y);
		final int y2 = getRandomInt(-bHeight, bHeight);
		final int x2 = getRandomInt(-bWidth, bWidth);
		mArc.lineTo(x2, y2);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		}
	}

	protected RectF getRectForRadius(final int x, final int y, final int radius) {
		return new RectF(x - radius, y - radius, x + radius, y + radius);
	}

	private void drawStar(final int x, final int y, final Paint paint, final int radius) {
		final int arms = Settings.getAnzahlFlowerLeafs(5, 10);
		final Path path = new StarPath(arms, new PointF(x, y), radius, radius / 2, true, 0);
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360 / arms));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawRect(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), Settings.getSelectedPatternVariant());

		if (Settings.isRandomRotate()) {
			rotatePath(x, y, path, Settings.getRotationDegrees(0, 180));
		} else {
			int degr = Settings.getRotationDegrees(0, 180);
			if (getRandomBoolean()) {
				degr = degr + 90;
			}
			rotatePath(x, y, path, degr);
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawCloud(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new CloudPath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSpooky(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 5);
			variante = "V" + nr;
		} else if (variante.equalsIgnoreCase("Mixed Bats")) {
			final int nr = getRandomInt(1, 3);
			variante = "V" + nr;
		} else if (variante.equalsIgnoreCase("Mixed Ghosts")) {
			final int nr = getRandomInt(3, 5);
			variante = "V" + nr;
		}
		drawSpooky(x, y, paint, radius, variante);
	}

	private void drawSpooky(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Skull":
			path = new SkullPath(new Point(x, y), radius);
			break;
		case "V2":
		case "Aarons Cute Bat":
			path = new BatPath(new Point(x, y), radius, "V1");
			break;
		case "V3":
		case "Bat":
			path = new BatPath(new Point(x, y), radius, "V2");
			break;
		case "V4":
		case "Ghost V1":
			path = new GhostPath(new Point(x, y), radius, "V1");
			break;
		case "V5":
		case "Ghost V2":
			path = new GhostPath(new Point(x, y), radius, "V2");
			break;
		}
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawZitrone(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new ZitronePath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawXmasTree(final int x, final int y, final Paint paint, final int radius) {
		// final Path path = new ZitronePath(new Point(x, y), radius);
		final Path path = new XmasTreePath(new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawStarCircles(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new StarCirclePath(new PointF(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawCircle(final int x, final int y, final Paint paint, final int radius) {
		bitmapCanvas.drawCircle(x, y, radius, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawCircle(x, y, radius, paint);
		}
	}

	private void drawPillow(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new PillowPath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-45, 45));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawHeart(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new HeartPath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawGlossyBubble(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colorDarker = ColorHelper.darker(color);
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int whitehalftransparent = 0xCCFFFFFF;
		final int transparent = 0x00FFFFFF;
		// Bubble
		bitmapCanvas.drawCircle(x, y, radius, paint);
		paint.setShadowLayer(0, 0, 0, 0);
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		bitmapCanvas.drawCircle(x, y, radius, paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x, y - radius, x, y, whitehalftransparent, transparent, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		final RectF oval = new RectF(x - radius * 3 / 4, y - radius, x + radius * 3 / 4, y);
		bitmapCanvas.drawOval(oval, paint);
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawCircle(x, y, radius, paint);
		}
		paint.setShader(null);
	}

	private void drawGlossySmiley(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = color;
		final int whitehalftransparent = 0xAAFFFFFF;
		final int transparent = 0x00FFFFFF;
		final Path path = new SmileyPath(new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		// Bubble
		bitmapCanvas.drawPath(path, paint);
		paint.setShadowLayer(0, 0, 0, 0);
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		bitmapCanvas.drawPath(path, paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x, y - radius, x, y, whitehalftransparent, transparent, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		final RectF oval = new RectF(x - radius * 3 / 4, y - radius, x + radius * 3 / 4, y);
		bitmapCanvas.drawOval(oval, paint);
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
		paint.setShader(null);
	}

	private void drawGlossyPacman(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = ColorHelper.darker2times(color);
		final int whitehalftransparent = 0x88FFFFFF;
		final int transparent = 0x00FFFFFF;
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 2);
			variant = "V" + nr;
		}
		final Path path = new PacmanPath(new Point(x, y), radius, variant);
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		// Bubble
		bitmapCanvas.drawPath(path, paint);
		paint.setShader(new RadialGradient(x, y, radius * 2, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		paint.setShadowLayer(0, 0, 0, 0);
		bitmapCanvas.drawPath(path, paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x, y - radius * 0.95f, x, y - radius * 0.2f, whitehalftransparent, transparent, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		final RectF oval = new RectF(x - radius * 6 / 10, y - radius * 0.95f, x + radius * 6 / 10, y - radius * 0.2f);
		bitmapCanvas.drawOval(oval, paint);
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
		paint.setShader(null);
	}

	private void drawGlossyHeart(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = ColorHelper.darker(color);
		final int whitehalftransparent = 0x88FFFFFF;
		final int transparent = 0x00FFFFFF;
		// Heart für dropshadow
		final Path path = new HeartPath(new Point(x, y), radius);
		rotatePath(x, y, path, Settings.getRotationDegrees(-30, 30));
		bitmapCanvas.drawPath(path, paint);
		paint.setShadowLayer(0, 0, 0, 0);
		// Heart
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		bitmapCanvas.drawPath(path, paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x - radius * 2 / 3, y - radius * 2 / 3, x, y, whitehalftransparent, transparent, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		bitmapCanvas.drawPath(path, paint);
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
		paint.setShader(null);
	}

	private void drawGlossyStar(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = ColorHelper.darker(color);
		final int whitehalftransparent = 0x88FFFFFF;
		final int whitequartertransparent = 0x22FFFFFF;
		final int transparent = 0x00FFFFFF;
		// Star
		final int arms = Settings.getAnzahlFlowerLeafs(5, 10);
		final Path path = new StarPath(arms, new PointF(x, y), radius, radius / 2, true, 0);
		rotatePath(x, y, path, Settings.getRotationDegrees(0, 360 / arms));

		bitmapCanvas.drawPath(path, paint);
		paint.setShadowLayer(0, 0, 0, 0);
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		bitmapCanvas.drawPath(path, paint);
		// Glossy glow
		final int colors[] = { whitehalftransparent, whitequartertransparent, transparent };
		final float dists[] = { 0f, 0.35f, 0.355f };
		paint.setShader(new LinearGradient(x - radius * 1 / 2, y - radius * 1 / 2, x + radius, y + radius, colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
		paint.setShader(null);
	}

	private int getColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {
		if (Settings.isDynamicColoring()) {
			return bmp.getPixel(x, y);
		} else {
			return refbmp.getPixel(x, y);
		}

	}

	private void setupPaintForOutline(final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		float strokewidth = radius / 15f;
		if (strokewidth > 3.0f) {
			strokewidth = 3;
		}
		if (strokewidth < 1.0f) {
			strokewidth = 0;
		}
		paint.setStrokeWidth(strokewidth);
		// paint.setStrokeWidth(Math.round(strokewidth));
		paint.setShader(null);
		if (Settings.isOutlineNeverTransparent()) {
			paint.setAlpha(255);
		}
		paint.setShadowLayer(0, 0, 0, 0);
		if (Settings.isCustomOutlineColor()) {
			paint.setColor(Settings.getCustomOutlineColor());
		} else {
			paint.setColor(ColorHelper.changeBrightness(paint.getColor(), Settings.getOutlineDarkness()));
		}
	}

	private void setupPaintForDarkerFont(final Paint paint, final int radius) {
		paint.setStyle(Style.FILL);
		paint.setShader(null);
		paint.setShadowLayer(0, 0, 0, 0);
		if (Settings.isCustomOutlineColor()) {
			paint.setColor(Settings.getCustomOutlineColor());
		} else {
			paint.setColor(ColorHelper.changeBrightness(paint.getColor(), Settings.getOutlineDarkness()));
		}
	}

	private void rotatePath(final int x, final int y, final Path path, final int rotate) {
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postRotate(rotate, x, y);
		path.transform(mMatrix);
	}

	private void mirrorPath(final int x, final int y, final Path path) {
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postScale(-1f, 1f, x, y);
		path.transform(mMatrix);
	}
}
