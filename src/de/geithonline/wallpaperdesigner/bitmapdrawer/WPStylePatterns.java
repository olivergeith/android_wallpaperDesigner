package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.shapes.GearPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath;
import de.geithonline.wallpaperdesigner.shapes.RandomPath;
import de.geithonline.wallpaperdesigner.shapes.RosePath;
import de.geithonline.wallpaperdesigner.shapes.SawPath;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

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

		// initializing some values depending on BitmapSize
		int maxRadius = Math.round(bWidth * 0.04f);
		if (maxRadius < 30) {
			maxRadius = 30;
		}
		int dropShadowRadius = Math.round(bWidth * 0.01f);
		if (dropShadowRadius < 10) {
			dropShadowRadius = 10;
		}

		// Zeichnen
		for (int i = 0; i < Settings.getAnzahlPatterns(); i++) {

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
			paint.setStyle(Style.FILL);
			if (Settings.isDropShadow()) {
				if (Settings.isDropShadowColorRandom()) {
					final int sx = getRandomInt(0, bWidth - 1);
					final int sy = getRandomInt(0, bHeight - 1);
					final int scolor = getColorFromBitmap(bitmap, refbitmap, sx, sy);
					paint.setShadowLayer(dropShadowRadius, 0, 0, scolor);
				} else if (Settings.isDropShadowColorOpposite()) {
					final int sx = bWidth - x;
					final int sy = bHeight - y;
					final int scolor = getColorFromBitmap(bitmap, refbitmap, sx, sy);
					paint.setShadowLayer(dropShadowRadius, 0, 0, scolor);
				} else {
					paint.setShadowLayer(dropShadowRadius, 0, 0, Settings.getDropShadowColor());
				}
			}
			final int radius = getRandomInt(10, maxRadius);
			drawPattern(x, y, paint, radius);
		}

		drawNonPremiumText(bitmapCanvas);
		refbitmap.recycle();
		return bitmap;
	}

	private void drawPattern(final int x, final int y, final Paint paint, final int radius) {
		switch (Settings.getSelectedPattern()) {
			default:
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
				bitmapCanvas.drawPath(new StarPath(5, new Point(x, y), radius, radius / 2, true, getRandomFloat(0, (float) (Math.PI / 2))), paint);
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
			case "Squares":
				bitmapCanvas.drawPath(new XEckPath(4, new Point(x, y), radius, 0), paint);
				break;
			case "Squares rotated":
				bitmapCanvas.drawPath(new XEckPath(4, new Point(x, y), radius, getRandomFloat(0, (float) (Math.PI / 2))), paint);
				break;
			case "Pentagon":
				bitmapCanvas.drawPath(new XEckPath(5, new Point(x, y), radius, 0), paint);
				break;
			case "Pentagon rotated":
				bitmapCanvas.drawPath(new XEckPath(5, new Point(x, y), radius, getRandomFloat(0, (float) (2 * Math.PI))), paint);
				break;
			case "Hexagon":
				bitmapCanvas.drawPath(new XEckPath(6, new Point(x, y), radius, 0), paint);
				break;
			case "Glossy Bubbles":
				drawGlossyBubble(x, y, paint, radius);
				break;
			case "Glossy Hearts":
				drawGlossyHeart(x, y, paint, radius);
				break;
			case "Bubbles":
				bitmapCanvas.drawCircle(x, y, radius, paint);
				break;
			case "Hearts":
				bitmapCanvas.drawPath(new HeartPath(new Point(x, y), radius /* getRandomFloat(1f, 5f) */), paint);
				break;
			case "Spirals":
				paint.setStyle(Style.STROKE);
				paint.setStrokeWidth(radius / 10);
				bitmapCanvas.drawPath(new SpiralPath(getRandomInt(2, 5), new Point(x, y), radius, getRandomBoolean()), paint);
				break;
			case "Pillows":
				paint.setStyle(Style.FILL);
				paint.setStrokeWidth(radius / 10);
				bitmapCanvas.drawPath(new PillowPath(new Point(x, y), radius), paint);
				break;
			case "Roses":
				paint.setStyle(Style.FILL);
				paint.setStrokeWidth(radius / 10);
				bitmapCanvas.drawPath(new RosePath(new Point(x, y), radius), paint);
				break;
			case "Skyline":
				final RectF rect = new RectF(x - radius, y, x + radius, bHeight);
				bitmapCanvas.drawRect(rect, paint);
				break;
			case "Crickle Crackle":
				paint.setStyle(Style.STROKE);
				paint.setStrokeWidth(radius / 10);
				bitmapCanvas.drawPath(new RandomPath(new Point(x, y), bWidth, bHeight, getRandomInt(5, 30), radius), paint);
				break;
		}
	}

	public void drawGlossyBubble(final int x, final int y, final Paint paint, final int radius) {
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

	public void drawGlossyHeart(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colordarker = ColorHelper.darker(color);
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int whitehalftransparent = 0xBBFFFFFF;
		final int transparent = 0x00FFFFFF;
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

	private int getColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {
		if (Settings.isDynamicColoring()) {
			return bmp.getPixel(x, y);
		} else {
			return refbmp.getPixel(x, y);
		}

	}

}
