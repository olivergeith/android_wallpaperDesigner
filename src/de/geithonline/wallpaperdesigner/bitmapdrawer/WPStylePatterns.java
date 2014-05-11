package de.geithonline.wallpaperdesigner.bitmapdrawer;

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
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.BubbleCirclePath;
import de.geithonline.wallpaperdesigner.shapes.FlowerPath;
import de.geithonline.wallpaperdesigner.shapes.GearPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath;
import de.geithonline.wallpaperdesigner.shapes.LuftschlangenPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath;
import de.geithonline.wallpaperdesigner.shapes.RandomPath;
import de.geithonline.wallpaperdesigner.shapes.RingPath;
import de.geithonline.wallpaperdesigner.shapes.RosePath;
import de.geithonline.wallpaperdesigner.shapes.SawPath;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
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
		// bitmapCanvas.drawRect(sizesRect, Settings.getBackgroundPaint(bWidth,
		// bHeight));

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

			if (Settings.isRandomizeColors()) {
				pcolor = randomizeColor(pcolor, Settings.getRandomizeColorRange());
			}
			paint.setColor(pcolor);
			if (Settings.isRandomizeAlpha()) {
				paint.setAlpha(getRandomInt(255 - Settings.getRandomizeAlphaRange(), 255));
			} else {
				paint.setAlpha(255);
			}
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
				paint.setShadowLayer(dropShadowRadius, 0, 0, ColorHelper.darker(pcolor, Settings.getDropShadowDarkness()));
				break;
			case "Select":
				paint.setShadowLayer(dropShadowRadius, 0, 0, Settings.getDropShadowColor());
				break;
			}
			drawPattern(x, y, paint, radius);
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

	private void drawPattern(final int x, final int y, final Paint paint, final int radius) {
		switch (Settings.getSelectedPattern()) {
		default:
		case "Letters":
			drawLetters(x, y, paint, radius);
			break;
		case "Custom Text":
			drawText(x, y, paint, radius);
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
		case "Pentagon":
			drawPentagon(x, y, paint, radius);
			break;
		case "Hexagon":
			drawHexagon(x, y, paint, radius);
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
		case "Maze":
			drawMaze(x, y, paint, radius);
			break;
		case "Virus Attack":
			drawVirus(x, y, paint, radius);
			break;
		case "Virus Attack V2":
			drawVirusV2(x, y, paint, radius);
			break;
		case "Streamers":
			drawStreamer(x, y, paint, radius);
			break;
		}
	}

	public void drawRose(final int x, final int y, final Paint paint, final int radius) {
		bitmapCanvas.drawPath(new RosePath(new Point(x, y), radius), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			bitmapCanvas.drawPath(new RosePath(new Point(x, y), radius), paint);
		}
	}

	public void drawRing(final int x, final int y, final Paint paint, final int radius) {
		bitmapCanvas.drawPath(new RingPath(new Point(x, y), radius, radius / 2), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new RingPath(new Point(x, y), radius, radius / 2), paint);
		}
	}

	private void drawSaw(final int x, final int y, final Paint paint, final int radius) {
		final int zaehne = 20;
		final boolean filled = getFilledBoolean();
		final boolean flip = getRandomBoolean();
		bitmapCanvas.drawPath(new SawPath(zaehne, new Point(x, y), radius, filled, flip), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			bitmapCanvas.drawPath(new SawPath(zaehne, new Point(x, y), radius, filled, flip), paint);
		}
	}

	private void drawGear(final int x, final int y, final Paint paint, final int radius) {
		final int zaehne = 15;
		final boolean filled = getFilledBoolean();
		bitmapCanvas.drawPath(new GearPath(zaehne, new Point(x, y), radius, filled), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
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

	private void drawVirus(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawVirusV2(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new VirusPath(new Point(x, y), radius, 13);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private void drawFlower(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new FlowerPath(new Point(x, y), radius, 6, 5);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
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
			setupPaintForOutline(paint, radius / 2);
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
	}

	private void drawText(final int x, final int y, final Paint paint, final int radius) {

		switch (Settings.getTextDrawStyle()) {
		default:
		case "Round":
			drawTextCircle(x, y, paint, radius);
			break;
		case "Normal":
			drawTextStraight(x, y, paint, radius);
			break;
		case "Angled":
			drawTextAngled(x, y, paint, radius);
			break;
		case "Random":
			final int i = getRandomInt(0, 3);
			switch (i) {
			default:
			case 1:
				drawTextCircle(x, y, paint, radius);
				break;
			case 2:
				drawTextStraight(x, y, paint, radius);
				break;
			case 3:
				drawTextAngled(x, y, paint, radius);
				break;
			}
			break;
		}
	}

	private void drawTextStraight(final int x, final int y, final Paint paint, final int radius) {
		if (getRandomBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextSize(radius);
		paint.setTextAlign(Align.CENTER);
		bitmapCanvas.drawText(Settings.getText(), x, y, paint);
	}

	private void drawTextCircle(final int x, final int y, final Paint paint, final int radius) {
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

		bitmapCanvas.drawTextOnPath(Settings.getText(), mArc, 0, 0, paint);
	}

	private void drawTextAngled(final int x, final int y, final Paint paint, final int radius) {
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

		bitmapCanvas.drawTextOnPath(Settings.getText(), mArc, 0, 0, paint);
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
		bitmapCanvas.drawPath(new XEckPath(4, new Point(x, y), radius, rotate), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new XEckPath(4, new Point(x, y), radius, rotate), paint);
		}
	}

	private void drawTriangle(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (Math.PI / 2));
		}
		bitmapCanvas.drawPath(new XEckPath(3, new Point(x, y), radius, rotate), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new XEckPath(3, new Point(x, y), radius, rotate), paint);
		}
	}

	private void drawPentagon(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (2 * Math.PI));
		}
		bitmapCanvas.drawPath(new XEckPath(5, new Point(x, y), radius, rotate), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new XEckPath(5, new Point(x, y), radius, rotate), paint);
		}
	}

	private void drawHexagon(final int x, final int y, final Paint paint, final int radius) {
		float rotate = 0;
		if (Settings.isRandomRotate()) {
			rotate = getRandomFloat(0, (float) (2 * Math.PI));
		}
		bitmapCanvas.drawPath(new XEckPath(6, new Point(x, y), radius, rotate), paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new XEckPath(6, new Point(x, y), radius, rotate), paint);
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
		final int colordarker = ColorHelper.darker(color);
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int whitehalftransparent = 0xCCFFFFFF;
		final int transparent = 0x00FFFFFF;
		// Bubble
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, color, Shader.TileMode.MIRROR));
		bitmapCanvas.drawCircle(x, y, radius, paint);
		// Ring
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		paint.setShader(null);
		paint.setShadowLayer(0, 0, 0, Settings.getDropShadowColor());
		paint.setColor(colordarker);
		bitmapCanvas.drawCircle(x, y, radius, paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x, y - radius, x, y, whitehalftransparent, transparent, Shader.TileMode.MIRROR));
		paint.setStyle(Style.FILL);
		final RectF oval = new RectF(x - radius * 3 / 4, y - radius, x + radius * 3 / 4, y);
		bitmapCanvas.drawOval(oval, paint);
	}

	private void drawGlossyHeart(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colordarker = ColorHelper.darker(color);
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int whitehalftransparent = 0xBBFFFFFF;
		final int transparent = 0x00FFFFFF;
		// Heart für dropshadow
		// bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		// Heart
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, color, Shader.TileMode.MIRROR));
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		// Ring
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(radius / 10);
		paint.setShader(null);
		paint.setShadowLayer(0, 0, 0, Settings.getDropShadowColor());
		paint.setColor(colordarker);
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
		// Glossy glow
		paint.setShader(new LinearGradient(x - radius * 2 / 3, y - radius * 2 / 3, x, y, whitehalftransparent, transparent, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius), paint);
	}

	private void drawGlossyStar(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colordarker = ColorHelper.darker(color);
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int whitehalftransparent = 0xAAFFFFFF;
		final int whitequartertransparent = 0x22FFFFFF;
		final int transparent = 0x00FFFFFF;
		float rotation = 0;
		if (Settings.isRandomRotate()) {
			rotation = getRandomFloat(0, (float) (Math.PI / 2));
		}
		final float strokewidth = radius / 10;
		// Star
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, color, Shader.TileMode.MIRROR));
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
		// Glossy glow
		final int colors[] = { whitehalftransparent, whitequartertransparent, transparent };
		final float dists[] = { 0f, 0.35f, 0.355f };
		// final int colors[] = { whitehalftransparent, transparent, whitequartertransparent, transparent };
		// final float dists[] = { 0f, 0.48f, 0.5f, 0.52f };
		paint.setShader(new LinearGradient(x - radius * 1 / 2, y - radius * 1 / 2, x + radius, y + radius, colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
		// Outline
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(strokewidth);
		paint.setShader(null);
		paint.setShadowLayer(0, 0, 0, Settings.getDropShadowColor());
		paint.setColor(colordarker);
		bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, rotation), paint);
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
		int strokewidth = radius / 10;
		if (strokewidth < 2) {
			strokewidth = 2;
		}
		paint.setStrokeWidth(strokewidth);
		paint.setShader(null);
		if (Settings.isOutlineNeverTransparent()) {
			paint.setAlpha(255);
		}
		// paint.setShadowLayer(0, 0, 0, Settings.getDropShadowColor());
		if (Settings.isCustomOutlineColor()) {
			paint.setColor(Settings.getCustomOutlineColor());
		} else {
			paint.setColor(ColorHelper.darker(paint.getColor(), Settings.getOutlineDarkness()));
		}
	}

}
