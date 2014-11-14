package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.Locale;

import android.graphics.Bitmap;
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
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CloudPath;
import de.geithonline.wallpaperdesigner.shapes.DandelionPath;
import de.geithonline.wallpaperdesigner.shapes.DeathstarPath;
import de.geithonline.wallpaperdesigner.shapes.DicePath;
import de.geithonline.wallpaperdesigner.shapes.DotSpiralPath;
import de.geithonline.wallpaperdesigner.shapes.DropPath;
import de.geithonline.wallpaperdesigner.shapes.FishPath;
import de.geithonline.wallpaperdesigner.shapes.FlowerPath;
import de.geithonline.wallpaperdesigner.shapes.FlowerV2Path;
import de.geithonline.wallpaperdesigner.shapes.FootprintPath;
import de.geithonline.wallpaperdesigner.shapes.FourSailsPath;
import de.geithonline.wallpaperdesigner.shapes.GearPath;
import de.geithonline.wallpaperdesigner.shapes.GhostPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath.HEART_SHAPE;
import de.geithonline.wallpaperdesigner.shapes.IgelPath;
import de.geithonline.wallpaperdesigner.shapes.InvertablePath;
import de.geithonline.wallpaperdesigner.shapes.LighthousePath;
import de.geithonline.wallpaperdesigner.shapes.LuftschlangenPath;
import de.geithonline.wallpaperdesigner.shapes.MandalaV1Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV2Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV3Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV4Path;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath.MATERIAL_TYPE;
import de.geithonline.wallpaperdesigner.shapes.NiceFlowerPath;
import de.geithonline.wallpaperdesigner.shapes.OvalAsymetricPath;
import de.geithonline.wallpaperdesigner.shapes.OwlPath;
import de.geithonline.wallpaperdesigner.shapes.PacmanPath;
import de.geithonline.wallpaperdesigner.shapes.PentagramPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath;
import de.geithonline.wallpaperdesigner.shapes.PlanePath;
import de.geithonline.wallpaperdesigner.shapes.RandomPath;
import de.geithonline.wallpaperdesigner.shapes.RautePath;
import de.geithonline.wallpaperdesigner.shapes.RectangleAsymetricPath;
import de.geithonline.wallpaperdesigner.shapes.RectanglePath;
import de.geithonline.wallpaperdesigner.shapes.RectanglePath.RECT_ASPECT;
import de.geithonline.wallpaperdesigner.shapes.RectanglePath.RECT_ROUNDED;
import de.geithonline.wallpaperdesigner.shapes.RingPath;
import de.geithonline.wallpaperdesigner.shapes.RocketPath;
import de.geithonline.wallpaperdesigner.shapes.RosePath;
import de.geithonline.wallpaperdesigner.shapes.SailboatPath;
import de.geithonline.wallpaperdesigner.shapes.SailboatPath2;
import de.geithonline.wallpaperdesigner.shapes.SatelitePath;
import de.geithonline.wallpaperdesigner.shapes.SawPath;
import de.geithonline.wallpaperdesigner.shapes.SchachbrettPath;
import de.geithonline.wallpaperdesigner.shapes.SchachbrettPath.BRETT_SHAPE;
import de.geithonline.wallpaperdesigner.shapes.ShellV1Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV2Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV3Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV4Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV5Path;
import de.geithonline.wallpaperdesigner.shapes.ShellV6Path;
import de.geithonline.wallpaperdesigner.shapes.SkullPath;
import de.geithonline.wallpaperdesigner.shapes.SmileyPath;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.SquarePath;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.StarCirclePath;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath.STAR_TYPE;
import de.geithonline.wallpaperdesigner.shapes.SunPath;
import de.geithonline.wallpaperdesigner.shapes.TriangleAsymetricPath;
import de.geithonline.wallpaperdesigner.shapes.UfoPath;
import de.geithonline.wallpaperdesigner.shapes.UfoPath.UFO_TYPE;
import de.geithonline.wallpaperdesigner.shapes.VirusPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath2;
import de.geithonline.wallpaperdesigner.shapes.VirusPath3;
import de.geithonline.wallpaperdesigner.shapes.VirusPath4;
import de.geithonline.wallpaperdesigner.shapes.VirusPath5;
import de.geithonline.wallpaperdesigner.shapes.VirusPath6;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
import de.geithonline.wallpaperdesigner.shapes.XmasTreePath;
import de.geithonline.wallpaperdesigner.shapes.YingYangPath;
import de.geithonline.wallpaperdesigner.shapes.ZitronePath;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public abstract class WPStylePattern extends WPStyle {

	protected int bWidth = 2560;
	protected int bHeight = 1600;
	protected final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	protected void drawPattern(final int x, final int y, final Paint paint, final int radius, final int index) {
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
		case "Assorted Shapes":
			drawAssorted(x, y, paint, radius);
			break;
		case "Chess":
			drawChess(x, y, paint, radius);
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
		case "Geometrical (long) Shapes":
			drawGeometricMore(x, y, paint, radius);
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
		case "Invertable Shapes":
			drawInvertable(x, y, paint, radius);
			break;
		case "Lines":
			drawLines(x, y, paint, radius);
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
		case "Fish":
			drawFisch(x, y, paint, radius);
			break;
		case "Flowers":
			drawFlower(x, y, paint, radius);
			break;
		case "Mandala":
			drawMandala(x, y, paint, radius);
			break;
		case "Dandelion":
			drawDandelion(x, y, paint, radius);
			break;
		case "Maritim":
			drawMaritim(x, y, paint, radius);
			break;
		case "Material":
			drawMaterial(x, y, paint, radius);
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
		case "Weather":
			drawWeather(x, y, paint, radius);
			break;
		case "Planes":
			drawPlane(x, y, paint, radius);
			break;
		}
	}

	public void drawIgel(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new IgelPath(new Point(x, y + radius / 2), radius * 1.3f);
		if (getFilledBoolean()) {
			mirrorPath(x, y, path);
		}
		rotatePath(x, y + radius / 2, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));

		bitmapCanvas.drawPath(path, paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawSaw(final int x, final int y, final Paint paint, final int radius) {
		final int zaehne = 20;
		final boolean filled = getFilledBoolean();
		final boolean flip = getRandomBoolean();
		bitmapCanvas.drawPath(new SawPath(zaehne, new Point(x, y), radius, filled, flip), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new SawPath(zaehne, new Point(x, y), radius, filled, flip), paint);
		}
	}

	protected void drawGear(final int x, final int y, final Paint paint, final int radius) {
		final int zaehne = 15;
		final boolean filled = getFilledBoolean();
		bitmapCanvas.drawPath(new GearPath(zaehne, new Point(x, y), radius, filled), paint);
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(new GearPath(zaehne, new Point(x, y), radius, filled), paint);
		}
	}

	protected boolean getFilledBoolean() {
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

	protected void drawDandelion(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new DandelionPath(new Point(x, y), radius);
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawMaritim(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variante = "V" + nr;
		}
		drawMaritim(x, y, paint, radius, variante);
	}

	protected void drawMaritim(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Sailboat V1":
			path = new SailboatPath(new Point(x, y), radius);
			break;
		case "V2":
		case "Sailboat V2":
			path = new SailboatPath2(new Point(x, y), radius);
			break;
		case "V3":
		case "Lighthouse":
			path = new LighthousePath(new Point(x, y), radius);
			break;
		case "V4":
		case "Anchor":
			path = new AnkerPath(new Point(x, y), radius);
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawGeometric(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed (with Circle)")) {
			final int nr = getRandomInt(0, 7);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Square (Mixed)")) {
			variant = "Square (Mixed)";
		}
		drawGeometric(x, y, paint, radius, variant);
	}

	protected void drawGeometric(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Triangle":
			path = new XEckPath(3, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V2":
		case "Square":
			path = new SquarePath(new PointF(x, y), radius * 0.8f, getFilledBoolean(), SQUARE_STYLE.NORMAL);
			break;
		case "V3":
		case "Square (rounded)":
			path = new SquarePath(new PointF(x, y), radius * 0.8f, getFilledBoolean(), SQUARE_STYLE.ROUNDED);
			break;
		case "V4":
		case "Pentagon":
			path = new XEckPath(5, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V5":
		case "Hexagon":
			path = new XEckPath(6, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V6":
		case "Octagon":
			path = new XEckPath(8, new Point(x, y), radius, 0, getFilledBoolean());
			break;
		case "V7":
		case "Circle":
			path = new CirclePath(new PointF(x, y), radius, radius / 2, getFilledBoolean());
			break;
		case "V8":
		case "Square (Mixed)":
			path = new SquarePath(new PointF(x, y), radius * 0.8f, getFilledBoolean(), SQUARE_STYLE.MIXED);
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawGeometricMore(final int x, final int y, final Paint paint, final int radius) {
		final String variant = Settings.getSelectedPatternVariant();
		drawGeometricMore(x, y, paint, radius, variant);
	}

	protected void drawGeometricMore(final int x, final int y, final Paint paint, final int radius, final String variante) {
		if (x == bWidth / 2 && y == bHeight / 2) {
			return;
		}
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Rectangle":
			path = new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean(), RECT_ROUNDED.NORMAL);
			break;
		case "V2":
		case "Rectangle (rounded)":
			path = new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean(), RECT_ROUNDED.ROUNDED);
			break;
		case "V3":
		case "Rectangle (Mixed)":
			path = new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean(), RECT_ROUNDED.MIXED);
			break;
		case "V4":
		case "Triangle":
			path = new TriangleAsymetricPath(new PointF(x, y), radius, getFilledBoolean());
			break;
		case "V5":
		case "Oval":
			path = new OvalAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean());
			break;
		case "V6":
		case "Diamond":
			path = new RautePath(new PointF(x, y), radius, radius * 6, getFilledBoolean());
			break;
		case "V7":
		case "Drop":
			path = new DropPath(new Point(x, y), radius, radius * 6, getFilledBoolean());
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawInvertable(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 9);
			variante = "V" + nr;
		} else if (variante.equalsIgnoreCase("Mixed Plus-Minus")) {
			final int nr = getRandomInt(4, 6);
			variante = "V" + nr;
		}

		drawInvertable(x, y, paint, radius, variante);
	}

	protected void drawInvertable(final int x, final int y, final Paint paint, final int radius, final String variante) {

		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Heart V1":
			path = new HeartPath(new PointF(x, y), radius, getFilledBoolean(), HEART_SHAPE.Curvy);
			break;
		case "V2":
		case "Heart V2":
			path = new HeartPath(new PointF(x, y), radius, getFilledBoolean(), HEART_SHAPE.Straigth);
			break;
		case "V3":
		case "Arrow":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Arrow");
			break;
		case "V4":
		case "Arrow (round)":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Arrow (round)");
			break;
		case "V5":
		case "Plus":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Plus");
			break;
		case "V6":
		case "Minus":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Minus");
			break;
		case "V7":
		case "Star":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Star");
			break;
		case "V8":
		case "Gear":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Gear");
			break;
		case "V9":
		case "Crown":
			path = new InvertablePath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "Crown");
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawRing(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawRing(x, y, paint, radius, variant);
	}

	protected void drawRing(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Rings V1 (Flange)":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V1");
			break;
		case "V2":
		case "Rings V2 (Asymetric)":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V2");
			break;
		case "V3":
		case "Rings V3 (Concentric)":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V3");
			break;
		case "V4":
		case "Rings V4 (Dizzy)":
			path = new RingPath(new Point(x, y), radius, radius / 2, getFilledBoolean(), "V4");
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawSpace(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 9);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Rockets")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}
		drawSpace(x, y, paint, radius, variant);
	}

	protected void drawSpace(final int x, final int y, final Paint paint, final int radius, final String variante) {
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
		case "Rocket V6":
			path = new RocketPath(new Point(x, y), radius, getFilledBoolean(), "Rocket V6");
			break;
		case "V7":
		case "Ufo V1":
			path = new UfoPath(new Point(x, y), radius, UFO_TYPE.UfoV1, getFilledBoolean());
			break;
		case "V8":
		case "Ufo V2":
			path = new UfoPath(new Point(x, y), radius, UFO_TYPE.UfoV2, getFilledBoolean());
			break;
		case "V9":
		case "Satellite":
			path = new SatelitePath(new Point(x, y), radius, getFilledBoolean(), "Satellite V1");
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(-45, 45, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawDeathstar(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new DeathstarPath(new Point(x, y), radius);
		rotatePath(x, y, path, getRotationDegrees(-15, 15, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			// paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawVirus(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}

		drawVirus(x, y, paint, radius, variant);
	}

	protected void drawVirus(final int x, final int y, final Paint paint, final int radius, final String variant) {
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
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			// paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawWeather(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 2);
			variant = "V" + nr;
		}
		drawWeather(x, y, paint, radius, variant);
	}

	protected void drawWeather(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Sun":
			path = new SunPath(5 + Settings.getAnzahlFlowerLeafs(3, 10), new Point(x, y), radius, getFilledBoolean());
			break;
		case "V2":
		case "Cloud":
			path = new CloudPath(new Point(x, y), radius, getFilledBoolean());
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawPlane(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 3);
			variant = "V" + nr;
		}
		drawPlane(x, y, paint, radius, variant);
	}

	protected void drawPlane(final int x, final int y, final Paint paint, final int radius, final String variant) {
		final Path path = new PlanePath(new Point(x, y), radius, variant);
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawFlower(final int x, final int y, final Paint paint, final int radius) {
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

	protected void drawFlower(final int x, final int y, final Paint paint, final int radius, final String variant) {

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
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawLines(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}
		drawLines(x, y, paint, radius, variant);

	}

	protected void drawLines(final int x, final int y, final Paint paint, final int radius, final String variant) {
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

	protected void drawShell(final int x, final int y, final Paint paint, final int radius) {
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
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
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

	protected void drawMandala(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawMandala(x, y, paint, radius, variant);
	}

	protected void drawMandala(final int x, final int y, final Paint paint, final int radius, final String variant) {
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
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
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

	protected void drawPacMan(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 2);
			variant = "V" + nr;
		}
		drawPacMan(x, y, paint, radius, variant);
	}

	protected void drawPacMan(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final Path path = new PacmanPath(new Point(x, y), radius, variante);
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawSmiley(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new SmileyPath(new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawFisch(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawFisch(x, y, paint, radius, variant);
	}

	protected void drawFisch(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final Path path = new FishPath(new Point(x, y), radius, variante);

		rotatePath(x, y, path, getRotationDegrees(-45, 45, bWidth, bHeight, new Point(x, y)));
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

	protected void drawText(final int x, final int y, final Paint paint, final int radius, final int index) {
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

	protected void drawCustomText(final int x, final int y, final Paint paint, final int radius, final String text) {

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

	protected void drawTextStraight(final int x, final int y, final Paint paint, final int radius, final String text) {
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

	protected void drawTextCircle(final int x, final int y, final Paint paint, final int radius, final String text) {
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

	protected void drawTextAngled(final int x, final int y, final Paint paint, final int radius, final String text) {
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

	protected void drawStar(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 3);
			variante = "V" + nr;
		}
		drawStar(x, y, paint, radius, variante);
	}

	protected void drawStar(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final int arms = Settings.getAnzahlFlowerLeafs(5, 10);
		final Path path = getStarPath(x, y, radius, variante, arms);
		rotatePath(x, y, path, getRotationDegrees(0, 360 / arms, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private Path getStarPath(final int x, final int y, final int radius, final String variante, final int arms) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Normal":
			path = new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.NORMAL);
			break;
		case "V2":
		case "Outline":
			path = new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.OUTLINE);
			break;
		case "V3":
		case "Spikey":
			path = new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.SPIKEY);
			break;
		}
		return path;
	}

	private void drawMaterial(final int x, final int y, final Paint paint, final int radius) {
		final String variante = Settings.getSelectedPatternVariant();
		drawMaterial(x, y, paint, radius, variante);
	}

	private void drawMaterial(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Stripe":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE);
			break;
		case "V2":
		case "Arc 1":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC1);
			break;
		case "V3":
		case "Arc 2":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC2);
			break;
		case "V4":
		case "Skyline":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.SKYLINE);
			break;
		case "V5":
		case "Edgy Bars":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.EDGY_BARS);
			break;
		case "V6":
		case "Rotating Bars":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_BARS);
			break;
		case "V7":
		case "Rotating Triangles":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_TRIANGLES);
			break;
		case "V8":
		case "Half Stripe":
			path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE);
			break;
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawRect(final int x, final int y, final Paint paint, final int radius) {
		final String variante = Settings.getSelectedPatternVariant();
		drawRect(x, y, paint, radius, variante);
	}

	protected void drawRect(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Random Ratio":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.RANDOM);
			break;
		case "V2":
		case "Random Ratio (Rounded)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.RANDOM);
			break;
		case "V3":
		case "Random Ratio (Mixed)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.RANDOM);
			break;
		case "V4":
		case "4-3 Ratio":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.ASPECT_3_4);
			break;
		case "V5":
		case "4-3 Ratio (Rounded)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.ASPECT_3_4);
			break;
		case "V6":
		case "4-3 Ratio (Mixed)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.ASPECT_3_4);
			break;
		case "V7":
		case "1-2 Ratio":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.ASPECT_1_2);
			break;
		case "V8":
		case "1-2 Ratio (Rounded)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.ASPECT_1_2);
			break;
		case "V9":
		case "1-2 Ratio (Mixed)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.ASPECT_1_2);
			break;
		case "V10":
		case "Golden Ratio":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.ASPECT_GOLDEN_CUT);
			break;
		case "V11":
		case "Golden Ratio (Rounded)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.ASPECT_GOLDEN_CUT);
			break;
		case "V12":
		case "Golden Ratio (Mixed)":
			path = new RectanglePath(new Point(x, y), radius, getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.ASPECT_GOLDEN_CUT);
			break;
		}

		if (Settings.isRandomRotate()) {
			rotatePath(x, y, path, getRotationDegrees(0, 180, bWidth, bHeight, new Point(x, y)));
		} else {
			int degr = getRotationDegrees(0, 180, bWidth, bHeight, new Point(x, y));
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

	protected void drawSpooky(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 11);
			variante = "V" + nr;
		} else if (variante.equalsIgnoreCase("Mixed Bats")) {
			final int nr = getRandomInt(1, 5);
			variante = "V" + nr;
		} else if (variante.equalsIgnoreCase("Mixed Ghosts")) {
			final int nr = getRandomInt(5, 7);
			variante = "V" + nr;
		} else if (variante.equalsIgnoreCase("Mixed Owls")) {
			final int nr = getRandomInt(7, 11);
			variante = "V" + nr;
		}
		drawSpooky(x, y, paint, radius, variante);
	}

	protected void drawSpooky(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Skull":
			path = new SkullPath(new Point(x, y), radius);
			break;
		case "V2":
		case "Bat V1 (Aarons Cute Design)":
			path = new BatPath(new Point(x, y), radius, "V1");
			break;
		case "V3":
		case "Bat V2":
			path = new BatPath(new Point(x, y), radius, "V2");
			break;
		case "V4":
		case "Bat V3":
			path = new BatPath(new Point(x, y), radius, "V3");
			break;
		case "V5":
		case "Bat V4":
			path = new BatPath(new Point(x, y), radius, "V4");
			break;
		case "V6":
		case "Ghost V1":
			path = new GhostPath(new Point(x, y), radius, "V1");
			break;
		case "V7":
		case "Ghost V2":
			path = new GhostPath(new Point(x, y), radius, "V2");
			break;
		case "V8":
		case "Owl V1":
			path = new OwlPath(new Point(x, y), radius, "V1");
			break;
		case "V9":
		case "Owl V2":
			path = new OwlPath(new Point(x, y), radius, "V2");
			break;
		case "V10":
		case "Owl V3":
			path = new OwlPath(new Point(x, y), radius, "V3");
			break;
		case "V11":
		case "Owl V4":
			path = new OwlPath(new Point(x, y), radius, "V4");
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawAssorted(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 11);
			variant = "V" + nr;
		}
		drawAssorted(x, y, paint, radius, variant);
	}

	protected void drawAssorted(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Lemon":
			path = new ZitronePath(new Point(x, y), radius);
			break;
		case "V2":
		case "YingYang":
			path = new YingYangPath(new Point(x, y), radius);
			if (Settings.isRandomRotate() && getRandomBoolean()) {
				mirrorPath(x, y, path);
			}
			break;
		case "V3":
		case "Crop Circles":
			path = new DotSpiralPath(3, new Point(x, y), radius, 0);
			if (Settings.isRandomRotate() && getRandomBoolean()) {
				mirrorPath(x, y, path);
			}
			break;
		case "V4":
		case "Roses":
			path = new RosePath(new Point(x, y), radius);
			break;
		case "V5":
		case "Pillows":
			path = new PillowPath(new PointF(x, y), radius);
			break;
		case "V6":
		case "Android":
			path = new AndroidPath(new Point(x, y), radius);
			break;
		case "V7":
		case "Footprint":
			path = new FootprintPath(new Point(x, y), radius, "V1");
			if (getRandomBoolean()) {
				mirrorPath(x, y, path);
			}
			break;
		case "V8":
		case "Pentagram":
			path = new PentagramPath(new PointF(x, y), radius);
			break;
		case "V9":
		case "4Sails":
			path = new FourSailsPath(new Point(x, y), radius, "V1");
			break;
		case "V10":
		case "Dice":
			path = new DicePath(new PointF(x, y), radius * 0.8f);
			break;
		case "V11":
		case "Drop":
			path = new DropPath(new Point(x, y), radius);
			break;
		}
		rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawChess(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 7);
			variant = "V" + nr;
		}
		drawChess(x, y, paint, radius, variant);
	}

	protected void drawChess(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "V1":
		case "Square":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Square);
			break;
		case "V2":
		case "Star":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Star);
			break;
		case "V3":
		case "Circle":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Circle);
			break;
		case "V4":
		case "Pillow":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Pillow);
			break;
		case "V5":
		case "Heart":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Heart);
			break;
		case "V6":
		case "Arrow":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Arrow);
			break;
		case "V7":
		case "Triangle":
			path = new SchachbrettPath(new PointF(x, y), radius, getRandomInt(0, 4), BRETT_SHAPE.Triangle);
			break;
		}
		if (Settings.isRandomRotate()) {
			rotatePath(x, y, path, 90 * getRandomInt(-1, 4));
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawXmasTree(final int x, final int y, final Paint paint, final int radius) {
		// final Path path = new ZitronePath(new Point(x, y), radius);
		final Path path = new XmasTreePath(new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawStarCircles(final int x, final int y, final Paint paint, final int radius) {
		final Path path = new StarCirclePath(new PointF(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawCircle(final int x, final int y, final Paint paint, final int radius) {
		bitmapCanvas.drawCircle(x, y, radius, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawCircle(x, y, radius, paint);
		}
	}

	protected void drawHeart(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variante = "V" + nr;
		}
		drawHeart(x, y, paint, radius, variante);
	}

	protected void drawHeart(final int x, final int y, final Paint paint, final int radius, final String variante) {

		final Path path = getHeartPath(x, y, radius, variante);
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	private Path getHeartPath(final int x, final int y, final int radius, final String variante) {
		Path path;
		switch (variante) {
		default:
		case "Curvy":
		case "V1":
			path = new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Curvy);
			break;
		case "Straigth":
		case "V2":
			path = new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Straigth);
			break;
		case "Round":
		case "V3":
			path = new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Round);
			break;
		case "Peek":
		case "V4":
			path = new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Peek);
			break;
		case "Lovely":
		case "V5":
			path = new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Lovely);
			break;
		case "Asymetric":
		case "V6":
			path = new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Asymetric);
			break;
		}
		return path;
	}

	protected void drawGlossyBubble(final int x, final int y, final Paint paint, final int radius) {
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

	protected void drawGlossySmiley(final int x, final int y, final Paint paint, final int radius) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = color;
		final int whitehalftransparent = 0xAAFFFFFF;
		final int transparent = 0x00FFFFFF;
		final Path path = new SmileyPath(new Point(x, y), radius, getFilledBoolean());
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
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

	protected void drawGlossyPacman(final int x, final int y, final Paint paint, final int radius) {
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
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
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

	protected void drawGlossyHeart(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variante = "V" + nr;
		}
		drawGlossyHeart(x, y, paint, radius, variante);
	}

	protected void drawGlossyHeart(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = ColorHelper.darker(color);
		final int whitehalftransparent = 0x88FFFFFF;
		final int transparent = 0x00FFFFFF;
		// Heart für dropshadow
		final Path path = getHeartPath(x, y, radius, variante);
		rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
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

	protected void drawGlossyStar(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 3);
			variante = "V" + nr;
		}
		drawGlossyStar(x, y, paint, radius, variante);
	}

	protected void drawGlossyStar(final int x, final int y, final Paint paint, final int radius, final String variante) {
		final int color = paint.getColor();
		final int colorBrighter = ColorHelper.brighter2times(color);
		final int colorDarker = ColorHelper.darker(color);
		final int whitehalftransparent = 0x88FFFFFF;
		final int whitequartertransparent = 0x22FFFFFF;
		final int transparent = 0x00FFFFFF;
		// Star
		final int arms = Settings.getAnzahlFlowerLeafs(5, 10);
		final Path path = getStarPath(x, y, radius, variante, arms);
		rotatePath(x, y, path, getRotationDegrees(0, 360 / arms, bWidth, bHeight, new Point(x, y)));

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

	protected int getColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {

		int xx = Math.min(x, bWidth - 1);
		int yy = Math.min(y, bHeight - 1);
		xx = Math.max(xx, 0);
		yy = Math.max(yy, 0);
		if (Settings.isDynamicColoring()) {
			return bmp.getPixel(xx, yy);
		} else {
			return refbmp.getPixel(xx, yy);
		}

	}

	protected void setupPaintForOutline(final Paint paint, final int radius) {
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

	// protected void setUpPaintForRadialGradient(final int x, final int y, final Paint paint, final int radius) {
	// final int color = paint.getColor();
	// final int colorDarker = ColorHelper.darker(color);
	// final int colorBrighter = ColorHelper.brighter2times(color);
	// paint.setShadowLayer(5, 0, 0, 0);
	// paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
	// }
	//
	// protected void setupPaintForDarkerFont(final Paint paint, final int radius) {
	// paint.setStyle(Style.FILL);
	// paint.setShader(null);
	// paint.setShadowLayer(0, 0, 0, 0);
	// if (Settings.isCustomOutlineColor()) {
	// paint.setColor(Settings.getCustomOutlineColor());
	// } else {
	// paint.setColor(ColorHelper.changeBrightness(paint.getColor(), Settings.getOutlineDarkness()));
	// }
	// }

	protected void rotatePath(final int x, final int y, final Path path, final int rotate) {
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postRotate(rotate, x, y);
		path.transform(mMatrix);
	}

	protected void mirrorPath(final int x, final int y, final Path path) {
		final Matrix mMatrix = new Matrix();
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		mMatrix.postScale(-1f, 1f, x, y);
		path.transform(mMatrix);
	}

	protected int getDropShadowRadius() {
		int dropShadowRadius = Math.round(bWidth * 0.01f * Settings.getDropShadowRadiusAdjustment());
		if (dropShadowRadius < 5) {
			dropShadowRadius = 5;
		}
		return dropShadowRadius;
	}

	public static int getRotationDegrees(final int randomMin, final int randomMax, final int bWidth, final int bHeight, final Point center) {
		switch (Settings.getRotationStyle()) {
		default:
		case "Fixed":
			return Settings.getFixedRotationDegrees();
		case "Random":
			return Randomizer.getRandomInt(randomMin - 1, randomMax);
		case "Around Point":
			final float distTCenterX = bWidth / 2 - center.x;
			final float distTCenterY = bHeight / 2 - center.y;
			final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
			int winkel = (int) (alpha * 180 / Math.PI);
			// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
			if (center.x < bWidth / 2) {
				winkel = winkel + 180;
			}
			return winkel + 90 + Settings.getFixedRotationDegrees();
		}
	}
}
