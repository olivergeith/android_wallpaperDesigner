package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.AnkerPath;
import de.geithonline.wallpaperdesigner.shapes.BlitzPath;
import de.geithonline.wallpaperdesigner.shapes.BubbleCirclePath;
import de.geithonline.wallpaperdesigner.shapes.CloudPath;
import de.geithonline.wallpaperdesigner.shapes.DandelionPath;
import de.geithonline.wallpaperdesigner.shapes.DotSpiralPath;
import de.geithonline.wallpaperdesigner.shapes.FlowerPath;
import de.geithonline.wallpaperdesigner.shapes.GearPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath;
import de.geithonline.wallpaperdesigner.shapes.LighthousePath;
import de.geithonline.wallpaperdesigner.shapes.LuftschlangenPath;
import de.geithonline.wallpaperdesigner.shapes.NiceFlowerPath;
import de.geithonline.wallpaperdesigner.shapes.PacmanPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath;
import de.geithonline.wallpaperdesigner.shapes.RandomPath;
import de.geithonline.wallpaperdesigner.shapes.RingPath;
import de.geithonline.wallpaperdesigner.shapes.RocketPath;
import de.geithonline.wallpaperdesigner.shapes.RosePath;
import de.geithonline.wallpaperdesigner.shapes.SailboatPath;
import de.geithonline.wallpaperdesigner.shapes.SailboatPath2;
import de.geithonline.wallpaperdesigner.shapes.SawPath;
import de.geithonline.wallpaperdesigner.shapes.SmileyPath;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.UfoPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath2;
import de.geithonline.wallpaperdesigner.shapes.VirusPath3;
import de.geithonline.wallpaperdesigner.shapes.VirusPath4;
import de.geithonline.wallpaperdesigner.shapes.VirusPath5;
import de.geithonline.wallpaperdesigner.shapes.VirusPath6;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
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
				if (i == blurLevel1) {
					bitmap = BitmapBlurrer.doBlur(bitmap, 7, true);
				}
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
		case "Letters":
			drawLetters(x, y, paint, radius);
			break;
		case "Custom Text":
			final String text = Settings.getText();
			drawText(x, y, paint, radius, text);
			break;
		case "Numbers":
			final String number = String.format(Locale.GERMANY, "%04d", index);
			drawText(x, y, paint, radius * 2, number);
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
		case "Triangles":
			drawTriangle(x, y, paint, radius);
			break;
		case "Squares":
			drawSquares(x, y, paint, radius);
			break;
		case "Rectangles":
			drawRectangles(x, y, paint, radius);
			break;
		case "Rectangles (rounded)":
			drawRoundedRectangles(x, y, paint, radius);
			break;
		case "Pentagon":
			drawPentagon(x, y, paint, radius);
			break;
		case "Hexagon":
			drawHexagon(x, y, paint, radius);
			break;
		case "Octagon":
			drawOctagon(x, y, paint, radius);
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
		case "Spirals":
			drawSpiral(x, y, paint, radius);
			break;
		case "Crop Circles":
			drawDotSpiral(x, y, paint, radius);
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
		case "Pillows":
			drawPillow(x, y, paint, radius);
			break;
		case "Roses":
			drawRose(x, y, paint, radius);
			break;
		case "Flowers":
			drawFlower(x, y, paint, radius);
			break;
		case "Bubble Flowers":
			drawFlowerV2(x, y, paint, radius);
			break;
		case "FlowersV3":
			drawFlowerV3(x, y, paint, radius);
			break;
		case "FlowersV4":
			drawFlowerV4(x, y, paint, radius);
			break;
		case "FlowersV5":
			drawFlowerV5(x, y, paint, radius);
			break;
		case "Flower Bucket":
			drawFlowerBucket(x, y, paint, radius);
			break;
		case "Rings":
			drawRing(x, y, paint, radius);
			break;
		case "Skyline":
			final RectF rect = new RectF(x - radius, y, x + radius, bHeight);
			bitmapCanvas.drawRect(rect, paint);
			break;
		case "Crickle Crackle":
			drawCrickleCrackle(x, y, paint, radius);
			break;
		case "Clouds":
			drawCloud(x, y, paint, radius);
			break;
		case "Maze":
			drawMaze(x, y, paint, radius);
			break;
		case "Blitz":
			drawBlitz(x, y, paint, radius);
			break;
		case "Dandelion":
			drawDandelion(x, y, paint, radius);
			break;
		case "Sailboat":
			drawSailboat(x, y, paint, radius);
			break;
		case "Sailboat2":
			drawSailboat2(x, y, paint, radius);
			break;
		case "Anchor":
			drawAnker(x, y, paint, radius);
			break;
		case "Lighthouse":
			drawLighthouse(x, y, paint, radius);
			break;
		case "Marina":
			drawMarina(x, y, paint, radius);
			break;
		case "Ufo":
			drawUfo(x, y, paint, radius);
			break;
		case "Rocket":
			drawRocket(x, y, paint, radius);
			break;
		case "Space":
			drawSpace(x, y, paint, radius);
			break;
		default:
		case "Virus Attack":
			drawVirus(x, y, paint, radius);
			break;
		case "Virus Attack V2":
			drawVirusV2(x, y, paint, radius);
			break;
		case "Virus Attack V3":
			drawVirusV3(x, y, paint, radius);
			break;
		case "Virus Attack V4":
			drawVirusV4(x, y, paint, radius);
			break;
		case "Virus Attack V5":
			drawVirusV5(x, y, paint, radius);
			break;
		case "Virus Attack V6":
			drawVirusV6(x, y, paint, radius);
			break;
		case "Streamers":
			drawStreamer(x, y, paint, radius);
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

	public void drawRing(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean());

		bitmapCanvas.drawPath(path, paint);
		// Outline
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

	private void drawMaze(final int x, final int y, final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		bitmapCanvas.drawPath(new RandomPath(new Point(x, y), bWidth, bHeight, getRandomInt(10, 40), radius, true), paint);
	}

	private void drawBlitz(final int x, final int y, final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 12);
		bitmapCanvas.drawPath(new BlitzPath(new Point(x, y), getRandomInt(5, 30), radius, getFilledBoolean()), paint);
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

		// paint.setStyle(Style.FILL_AND_STROKE);
		// paint.setStrokeWidth(radius / 20);
		// bitmapCanvas.drawPath(new DandelionPath(new Point(x, y), radius), paint);
	}

	private void drawAnker(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new AnkerPath(new Point(x, y), radius * 1.5f);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSailboat(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new SailboatPath(new Point(x, y), radius * 1.5f);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawLighthouse(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new LighthousePath(new Point(x, y), radius * 1.2f);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawMarina(final int x, final int y, final Paint paint, final int radius) {
		final int p = getRandomInt(-1, 3);
		switch (p) {
		default:
		case 0:
			drawLighthouse(x, y, paint, radius);
			break;
		case 1:
			drawSailboat(x, y, paint, radius);
			break;
		case 2:
			drawSailboat2(x, y, paint, radius);
			break;
		case 3:
			drawAnker(x, y, paint, radius);
			break;
		}
	}

	private void drawFlowerBucket(final int x, final int y, final Paint paint, final int radius) {
		final int p = getRandomInt(-1, 2);
		switch (p) {
		default:
		case 0:
			drawFlowerV3(x, y, paint, radius);
			break;
		case 1:
			drawFlowerV4(x, y, paint, radius);
			break;
		case 2:
			drawFlowerV5(x, y, paint, radius);
			break;
		}
	}

	private void drawSailboat2(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new SailboatPath2(new Point(x, y), radius * 1.2f);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawUfo(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new UfoPath(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}

		// Iwant to believe text
		if (getFilledBoolean()) {
			setupPaintForDarkerFont(paint, radius);
			paint.setTypeface(Typeface.DEFAULT);
			paint.setTextSize(radius * 0.2f);
			paint.setTextAlign(Align.CENTER);
			bitmapCanvas.drawText("...I want to believe...", x, y + 0.7f * radius / 6, paint);
		}

	}

	private void drawRocket(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new RocketPath(new Point(x, y), radius, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			// paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawSpace(final int x, final int y, final Paint paint, final int radius) {
		final int p = getRandomInt(-1, 1);
		switch (p) {
		default:
		case 0:
			drawUfo(x, y, paint, radius);
			break;
		case 1:
			drawRocket(x, y, paint, radius);
			break;
		}
	}

	private void drawVirus(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirusV2(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath2(new Point(x, y), radius, 13);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirusV3(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath3(new Point(x, y), radius, 17, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirusV4(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath4(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirusV5(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath5(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirusV6(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 4));
		}

		final Path path = new VirusPath6(new Point(x, y), radius, rotate);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlower(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new FlowerPath(new Point(x, y), radius, Settings.getAnzahlFlowerLeafs(), 5);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlowerV2(final int x, final int y, final Paint paint, final int radius) {
		// final Path path = new FlowerPath(new Point(x, y), radius, 6, 5);
		final Path path = new BubbleCirclePath(6, new Point(x, y), radius, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlowerV3(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 5));
		}
		final Path path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(), new Point(x, y), radius, getFilledBoolean(), 1.0f, rotate);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlowerV4(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 5));
		}
		final Path path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(), new Point(x, y), radius, getFilledBoolean(), 0.8f, rotate);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlowerV5(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 5));
		}
		final Path path = new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(), new Point(x, y), radius, getFilledBoolean(), rotate);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawCrickleCrackle(final int x, final int y, final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		bitmapCanvas.drawPath(new RandomPath(new Point(x, y), bWidth, bHeight, getRandomInt(5, 30), radius, false), paint);
	}

	private void drawSpiral(final int x, final int y, final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		bitmapCanvas.drawPath(new SpiralPath(getRandomInt(2, 5), new Point(x, y), radius, getRandomBoolean()), paint);
	}

	private void drawDotSpiral(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI * 2));
		}
		final Path path = new DotSpiralPath(3, new Point(x, y), radius, rotate);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawPacMan(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new PacmanPath(new Point(x, y), radius, getFilledBoolean());
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
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawStreamer(final int x, final int y, final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		bitmapCanvas.drawPath(new LuftschlangenPath(getRandomInt(5, 7), new Point(x, y), radius, getRandomBoolean()), paint);
	}

	private void drawLetters(final int x, final int y, final Paint paint, final int radius) {
		final int letterindex = getRandomInt(0, letters.length() - 1);
		final char c = letters.charAt(letterindex);
		if (getRandomBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextSize(radius * 2.5f);
		paint.setTextAlign(Align.CENTER);
		bitmapCanvas.drawText("" + c, x, y, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawText("" + c, x, y, paint);
		}

	}

	private void drawText(final int x, final int y, final Paint paint, final int radius, final String text) {

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
		if (getRandomBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextSize(radius);
		paint.setTextAlign(Align.CENTER);
		bitmapCanvas.drawText(text, x, y, paint);
	}

	private void drawTextCircle(final int x, final int y, final Paint paint, final int radius, final String text) {
		if (getRandomBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextSize(radius);
		paint.setTextAlign(Align.CENTER);
		final Path mArc = new Path();
		final RectF oval = getRectForRadius(x, y, radius * 2);
		mArc.addArc(oval, getRandomInt(0, 360), 355);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
	}

	private void drawTextAngled(final int x, final int y, final Paint paint, final int radius, final String text) {
		if (getRandomBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextSize(radius);
		paint.setTextAlign(Align.LEFT);
		final Path mArc = new Path();
		mArc.moveTo(x, y);
		final int y2 = getRandomInt(-bHeight, bHeight);
		final int x2 = getRandomInt(-bWidth, bWidth);
		mArc.lineTo(x2, y2);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
	}

	protected RectF getRectForRadius(final int x, final int y, final int radius) {
		return new RectF(x - radius, y - radius, x + radius, y + radius);
	}

	private void drawStar(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 2));
		}
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotate), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotate), paint);
		}
	}

	private void drawSquares(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 2));
		}
		final Path path = new XEckPath(4, new Point(x, y), radius, rotate, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawRectangles(final int x, final int y, final Paint paint, final int radius) {
		final Rect r = new Rect();
		final int height = (int) (radius / 2 * getRandomFloat(0.1f, 1f));
		boolean horizontal = true;

		if (Settings.isRandomRotate()) {
			horizontal = getRandomBoolean();
		}
		if (horizontal == false) {
			r.left = x - radius;
			r.right = x + radius;
			r.top = y - height;
			r.bottom = y + height;
		} else {
			r.left = x - height;
			r.right = x + height;
			r.top = y - radius;
			r.bottom = y + radius;
		}

		bitmapCanvas.drawRect(r, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawRect(r, paint);
		}
	}

	private void drawRoundedRectangles(final int x, final int y, final Paint paint, final int radius) {
		final RectF r = new RectF();
		final int height = (int) (radius * 0.8f * getRandomFloat(0.1f, 1f));
		boolean horizontal = true;
		final float cornerRad = radius * 0.3f;

		if (Settings.isRandomRotate()) {
			horizontal = getRandomBoolean();
		}
		if (horizontal == false) {
			r.left = x - radius;
			r.right = x + radius;
			r.top = y - height;
			r.bottom = y + height;
		} else {
			r.left = x - height;
			r.right = x + height;
			r.top = y - radius;
			r.bottom = y + radius;
		}

		bitmapCanvas.drawRoundRect(r, cornerRad, cornerRad, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawRoundRect(r, cornerRad, cornerRad, paint);
		}
	}

	private void drawTriangle(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 2));
		}
		final Path path = new XEckPath(3, new Point(x, y), radius, rotate, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawPentagon(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (2 * Math.PI));
		}
		final Path path = new XEckPath(5, new Point(x, y), radius, rotate, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawHexagon(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (2 * Math.PI));
		}
		final Path path = new XEckPath(6, new Point(x, y), radius, rotate, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawOctagon(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (2 * Math.PI));
		}
		final Path path = new XEckPath(8, new Point(x, y), radius, rotate, getFilledBoolean());
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawCloud(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new CloudPath(new Point(x, y), radius);
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
		bitmapCanvas.drawPath(new PillowPath(new Point(x, y), radius), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new PillowPath(new Point(x, y), radius), paint);
		}
	}

	private void drawHeart(final int x, final int y, final Paint paint, final int radius) {
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
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
		final Path path = new PacmanPath(new Point(x, y), radius, getFilledBoolean());
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
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		paint.setShadowLayer(0, 0, 0, 0);
		// Heart
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x - radius * 2 / 3, y - radius * 2 / 3, x, y, whitehalftransparent, transparent, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
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
		float rotation = 0;
		if (Settings.isRandomRotate()) {
			rotation = getRandomFloat(0, (float) (Math.PI / 2));
		}
		// Star
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
		paint.setShadowLayer(0, 0, 0, 0);
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
		// Glossy glow
		final int colors[] = { whitehalftransparent, whitequartertransparent, transparent };
		final float dists[] = { 0f, 0.35f, 0.355f };
		paint.setShader(new LinearGradient(x - radius * 1 / 2, y - radius * 1 / 2, x + radius, y + radius, colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
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
		int strokewidth = radius / 20;
		if (strokewidth < 2) {
			strokewidth = 2;
		}
		paint.setStrokeWidth(strokewidth);
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

}
