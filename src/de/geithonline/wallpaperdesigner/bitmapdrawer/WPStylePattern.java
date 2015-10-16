package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_REFLECTIONS_STYLE;
import de.geithonline.wallpaperdesigner.shapes.AndroidPath;
import de.geithonline.wallpaperdesigner.shapes.AndroidPath.ROBOT_STYLE;
import de.geithonline.wallpaperdesigner.shapes.AnkerPath;
import de.geithonline.wallpaperdesigner.shapes.Asymetric3DPath;
import de.geithonline.wallpaperdesigner.shapes.Asymetric3DPath.ASYMETRIC_3D_STYLE;
import de.geithonline.wallpaperdesigner.shapes.AsymetricLongPath;
import de.geithonline.wallpaperdesigner.shapes.AsymetricLongPath.ASYMETRIC_STYLE;
import de.geithonline.wallpaperdesigner.shapes.BatPath;
import de.geithonline.wallpaperdesigner.shapes.BlitzPath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.CloudPath;
import de.geithonline.wallpaperdesigner.shapes.DandelionPath;
import de.geithonline.wallpaperdesigner.shapes.DicePath;
import de.geithonline.wallpaperdesigner.shapes.DotSpiralPath;
import de.geithonline.wallpaperdesigner.shapes.DropPath;
import de.geithonline.wallpaperdesigner.shapes.FishPath;
import de.geithonline.wallpaperdesigner.shapes.FlippedPath;
import de.geithonline.wallpaperdesigner.shapes.FlippedPath.FLIPPED_STYLE;
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
import de.geithonline.wallpaperdesigner.shapes.LogoPathEX;
import de.geithonline.wallpaperdesigner.shapes.LogoPathEX.LOGO_STYLE_EX;
import de.geithonline.wallpaperdesigner.shapes.LogoPathRR;
import de.geithonline.wallpaperdesigner.shapes.LogoPathRR.LOGO_STYLE_RR;
import de.geithonline.wallpaperdesigner.shapes.LuftschlangenPath;
import de.geithonline.wallpaperdesigner.shapes.MandalaV1Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV2Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV3Path;
import de.geithonline.wallpaperdesigner.shapes.MandalaV4Path;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath;
import de.geithonline.wallpaperdesigner.shapes.MaterialPath.MATERIAL_TYPE;
import de.geithonline.wallpaperdesigner.shapes.NiceFlowerPath;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.shapes.OvalPath.OVAL_TYPE;
import de.geithonline.wallpaperdesigner.shapes.OwlPath;
import de.geithonline.wallpaperdesigner.shapes.PacmanPath;
import de.geithonline.wallpaperdesigner.shapes.PacmanPath.PACMAN_STYLE;
import de.geithonline.wallpaperdesigner.shapes.PentagramPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath;
import de.geithonline.wallpaperdesigner.shapes.PillowPath.PILLOW_TYPE;
import de.geithonline.wallpaperdesigner.shapes.PlanePath;
import de.geithonline.wallpaperdesigner.shapes.PuzzlePath;
import de.geithonline.wallpaperdesigner.shapes.PuzzlePath.PUZZLE_CONNECTION;
import de.geithonline.wallpaperdesigner.shapes.PuzzlePath.PUZZLE_TYPE;
import de.geithonline.wallpaperdesigner.shapes.RandomPath;
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
import de.geithonline.wallpaperdesigner.shapes.SmileyPath.SMILEY_TYPE;
import de.geithonline.wallpaperdesigner.shapes.SonicPath;
import de.geithonline.wallpaperdesigner.shapes.SonicPath.SONICTYPE;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.SquareCornered;
import de.geithonline.wallpaperdesigner.shapes.SquareCornered.CORNERED_STYLE;
import de.geithonline.wallpaperdesigner.shapes.SquarePath;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath.STAR_TYPE;
import de.geithonline.wallpaperdesigner.shapes.StarwarsPath;
import de.geithonline.wallpaperdesigner.shapes.StarwarsPath.STARWARS_TYPE;
import de.geithonline.wallpaperdesigner.shapes.SunPath;
import de.geithonline.wallpaperdesigner.shapes.SunPath.SUN_TYPE;
import de.geithonline.wallpaperdesigner.shapes.UfoPath;
import de.geithonline.wallpaperdesigner.shapes.UfoPath.UFO_TYPE;
import de.geithonline.wallpaperdesigner.shapes.VirusPath;
import de.geithonline.wallpaperdesigner.shapes.VirusPath2;
import de.geithonline.wallpaperdesigner.shapes.VirusPath3;
import de.geithonline.wallpaperdesigner.shapes.VirusPath4;
import de.geithonline.wallpaperdesigner.shapes.VirusPath5;
import de.geithonline.wallpaperdesigner.shapes.VirusPath6;
import de.geithonline.wallpaperdesigner.shapes.VirusPath7;
import de.geithonline.wallpaperdesigner.shapes.VirusPath8;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
import de.geithonline.wallpaperdesigner.shapes.XmasTreePath;
import de.geithonline.wallpaperdesigner.shapes.YingYangPath;
import de.geithonline.wallpaperdesigner.shapes.ZitronePath;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public abstract class WPStylePattern extends WPStyle {

	protected int bWidth = 2560;
	protected int bHeight = 1600;
	protected final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	protected void drawPattern(final int x, final int y, final Paint paint, final int radius, final int index) {
		switch (Settings.getSelectedPattern()) {

			case "Gears-Saws":
				drawGearSaw(x, y, paint, radius);
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
			case "Flipped":
				drawFlipped(x, y, paint, radius);
				break;
			case "Geometrical Shapes":
				drawGeometric(x, y, paint, radius);
				break;
			case "Geometrical (long) Shapes":
				drawGeometricMore(x, y, paint, radius);
				break;
			case "3D (long) Shapes":
				draw3DLongShape(x, y, paint, radius);
				break;
			case "Rings":
				drawRing(x, y, paint, radius);
				break;
			case "Bubbles":
				drawBubble(x, y, paint, radius);
				break;
			case "Hearts":
				drawHeart(x, y, paint, radius);
				break;
			case "Invertable Shapes":
				drawInvertable(x, y, paint, radius);
				break;
			case "Lines":
				drawLines(x, y, paint, radius);
				break;
			case "Logos":
				drawLogos(x, y, paint, radius);
				break;
			case "PacMan":
				drawPacman(x, y, paint, radius);
				break;
			case "Pillows":
				drawPillow(x, y, paint, radius);
				break;
			case "Puzzle":
				drawPuzzle(x, y, paint, radius, PUZZLE_CONNECTION.NORMAL);
				break;
			case "Puzzle (Circle Connector)":
				drawPuzzle(x, y, paint, radius, PUZZLE_CONNECTION.CIRCLE);
				break;
			case "Puzzle (Square)":
				drawPuzzle(x, y, paint, radius, PUZZLE_CONNECTION.RECT_NORMAL);
				break;
			case "Puzzle (Square Connector)":
				drawPuzzle(x, y, paint, radius, PUZZLE_CONNECTION.SQUARE);
				break;
			case "Smiley":
				drawSmiley(x, y, paint, radius);
				break;
			case "Stars":
				drawStar(x, y, paint, radius);
				break;
			case "Square":
				drawSquare(x, y, paint, radius);
				break;
			case "Text":
				drawText(x, y, paint, radius * 2, index);
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
			case "Maritim":
				drawMaritim(x, y, paint, radius);
				break;
			case "Material":
				drawMaterial(x, y, paint, radius);
				break;
			case "Rectangles":
				drawRect(x, y, paint, radius);
				break;
			case "Shells":
				drawShell(x, y, paint, radius);
				break;
			case "Sonic":
				drawSonic(x, y, paint, radius);
				break;
			case "Space":
				drawSpace(x, y, paint, radius);
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
			case "Xmas":
				drawXmas(x, y, paint, radius);
				break;
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawGearSaw(final int x, final int y, final Paint paint, final int radius) {
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 3);
			variante = "V" + nr;
		}
		drawGearSaw(x, y, paint, radius, variante);
	}

	protected void drawGearSaw(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "V1":
			case "Gear":
				final int zaehne = 10 + Settings.getAnzahlFlowerLeafs(1, 10);
				path = new GearPath(zaehne, new Point(x, y), radius, getFilledBoolean());
				break;
			case "V2":
			case "Saw": {
				final int zahn = 15 + Settings.getAnzahlFlowerLeafs(1, 10);
				path = new SawPath(zahn, new Point(x, y), radius, getFilledBoolean(), getRandomBoolean());
				break;
			}
			case "V3":
			case "Star Gear": {
				final int zahn = 15 + Settings.getAnzahlFlowerLeafs(1, 10);
				path = new StarPath(zahn, new PointF(x, y), radius, radius * 0.8f, getFilledBoolean());
				break;
			}
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawFlipped(final int x, final int y, final Paint paint, final int radius) {
		final String variant = Settings.getSelectedPatternVariant();
		drawFlipped(x, y, paint, radius, variant);
	}

	protected void drawFlipped(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "Triangle":
				path = new FlippedPath(new PointF(x, y), radius, getFilledBoolean(), FLIPPED_STYLE.TRIANGLE);
				break;
			case "Triangle V2":
				path = new FlippedPath(new PointF(x, y), radius, getFilledBoolean(), FLIPPED_STYLE.TRIANGLE_V2);
				break;
			case "Square":
				path = new FlippedPath(new PointF(x, y), radius, getFilledBoolean(), FLIPPED_STYLE.SQUARE);
				break;
			case "Rectangle":
				path = new FlippedPath(new PointF(x, y), radius, getFilledBoolean(), FLIPPED_STYLE.RECTANGLE);
				break;
			case "Quarter Arc":
				path = new FlippedPath(new PointF(x, y), radius, getFilledBoolean(), FLIPPED_STYLE.QUARTER_ARC);
				break;
			case "Quarter Arc V2":
				path = new FlippedPath(new PointF(x, y), radius, getFilledBoolean(), FLIPPED_STYLE.QUARTER_ARC_V2);
				break;

		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawGeometric(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed (with Circle)")) {
			final int nr = getRandomInt(0, 9);
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
				path = new XEckPath(3, new PointF(x, y), radius, 0, getFilledBoolean());
				break;
			case "V2":
			case "Square":
				path = new SquarePath(new PointF(x, y), radius, getFilledBoolean(), SQUARE_STYLE.NORMAL, Direction.CW);
				break;
			case "V3":
			case "Square (rounded)":
				path = new SquarePath(new PointF(x, y), radius, getFilledBoolean(), SQUARE_STYLE.ROUNDED, Direction.CW);
				break;
			case "V4":
			case "Pentagon":
				path = new XEckPath(5, new PointF(x, y), radius, 0, getFilledBoolean());
				break;
			case "V5":
			case "Hexagon":
				path = new XEckPath(6, new PointF(x, y), radius, 0, getFilledBoolean());
				break;
			case "V6":
			case "Octagon":
				path = new XEckPath(8, new PointF(x, y), radius, 0, getFilledBoolean());
				break;
			case "V7":
			case "Circle":
				path = new CirclePath(new PointF(x, y), radius, radius / 2, getFilledBoolean(), CIRCLE_STYLE.CIRCLE);
				break;
			case "V8":
			case "Oval":
				path = new OvalPath(new PointF(x, y), radius / 2, radius, Direction.CW, OVAL_TYPE.NORMAL);
				break;
			case "V9":
			case "Oval (random width)":
				path = new OvalPath(new PointF(x, y), radius * 0.8f, radius, Direction.CW, OVAL_TYPE.RANDOM_WIDTH);
				break;
			case "V10":
			case "Oval (random)":
				path = new OvalPath(new PointF(x, y), radius * 0.8f, radius, Direction.CW, OVAL_TYPE.RANDOM);
				break;
			case "V11":
			case "Half Circle":
				path = new CirclePath(new PointF(x, y), radius, radius / 2, getFilledBoolean(), CIRCLE_STYLE.HALF_CIRCLE);
				break;
			case "V12":
			case "Square (Mixed)":
				path = new SquarePath(new PointF(x, y), radius, getFilledBoolean(), SQUARE_STYLE.MIXED, Direction.CW);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
			// drawGlossyPathCenterGlow(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawLogos(final int x, final int y, final Paint paint, final int radius) {
		final String variant = Settings.getSelectedPatternVariant();
		drawLogos(x, y, paint, radius, variant);
	}

	protected void drawLogos(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "Resurrection Remix":
				path = new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V1);
				break;
			case "Resurrection Remix V2":
				path = new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V2);
				break;
			case "Resurrection Remix V3":
				path = new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V3);
				break;
			case "Resurrection Remix V4":
				path = new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V4);
				break;
			case "Resurrection Remix V5":
				path = new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V5);
				break;
			case "Resurrection Remix V6":
				path = new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V6);
				break;
			case "ElementalX Kernel":
				path = new LogoPathEX(new PointF(x, y), radius, LOGO_STYLE_EX.V1);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
			// drawGlossyPathCenterGlow(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
			case "Rectangle":
				path = new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean(), RECT_ROUNDED.NORMAL);
				break;
			case "Rectangle (rounded)":
				path = new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean(), RECT_ROUNDED.ROUNDED);
				break;
			case "Rectangle (Mixed)":
				path = new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, getFilledBoolean(), RECT_ROUNDED.MIXED);
				break;
			case "Triangle":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.TRIANGLE);
				break;
			case "Oval":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.OVAL);
				break;
			case "Diamond":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.RAUTE);
				break;
			case "Dragon":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.DRACHEN);
				break;
			case "Dragon (upsidedown)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.DRACHEN_UPSIDEDOWN);
				break;
			case "Drop":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.DROP);
				break;
			case "Lense":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.LENSE);
				break;
			case "Lense V2":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.LENSE_V2);
				break;
			case "Lense V3":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.LENSE_V3);
				break;
			case "Tag":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.TAG);
				break;
			case "Knife":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.KNIFE);
				break;
			case "Knife V2":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.KNIFE_V2);
				break;
			case "Knife V3":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.KNIFE_V3);
				break;
			case "Cross":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.CROSS);
				break;
			case "Cross (Slim)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 4, getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM);
				break;
			case "Cross (Slim-Double)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 4, getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM_DOUBLE);
				break;
			case "Spiky Cross":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.SPIKY_CROSS);
				break;
			case "Double Cross":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.DOUBLE_CROSS);
				break;
			case "Sperm":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 6, getFilledBoolean(), ASYMETRIC_STYLE.SPERM);
				break;
			case "Virus":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 4, getFilledBoolean(), ASYMETRIC_STYLE.VIRUS);
				break;
			case "Virus V2":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 4, getFilledBoolean(), ASYMETRIC_STYLE.VIRUS_V2);
				break;
			case "Long Heart":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.LONG_HEART);
				break;
			case "Circle Chain":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.CHAIN_CIRCLE);
				break;
			case "Circle Chain (upsidedown)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.CHAIN_CIRCLE_UPSIDEDOWN);
				break;
			case "Spear":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.SPEAR1);
				break;
			case "Bird":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 4, getFilledBoolean(), ASYMETRIC_STYLE.BIRD);
				break;
			case "Bird V2":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 4, getFilledBoolean(), ASYMETRIC_STYLE.BIRD_V2);
				break;
			case "Golf Pin":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.GOLF_PIN);
				break;
			case "Pin":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.PIN);
				break;
			case "Tulip":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.TULIP_NORMAL);
				break;
			case "Plane":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.PLANE);
				break;
			case "Arrow":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.ARROW);
				break;
			case "Cross (Slim V2)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM_V2);
				break;
			case "Cross (Slim V3)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM_V3);
				break;
			case "Tulip (Slim)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.TULIP_SLIM);
				break;
			case "Tulip (Fat)":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.TULIP_FAT);
				break;
			case "Spaceship":
				path = new AsymetricLongPath(new PointF(x, y), radius, radius * 5, getFilledBoolean(), ASYMETRIC_STYLE.SPACESHIP);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void draw3DLongShape(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variant = "V" + nr;
		}
		draw3DLongShape(x, y, paint, radius, variant);
	}

	protected void draw3DLongShape(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "V1":
			case "Long Pyramide":
				path = new Asymetric3DPath(new PointF(x, y), radius, radius * 4, ASYMETRIC_3D_STYLE.PYRAMIDE);
				break;
			case "V2":
			case "Pyramide":
				path = new Asymetric3DPath(new PointF(x, y), radius, radius * 2, ASYMETRIC_3D_STYLE.PYRAMIDE);
				break;
			case "V3":
			case "Long Cube":
				path = new Asymetric3DPath(new PointF(x, y), radius, radius * 4, ASYMETRIC_3D_STYLE.CUBE);
				break;
			case "V4":
			case "Cube":
				path = new Asymetric3DPath(new PointF(x, y), radius, radius * 2, ASYMETRIC_3D_STYLE.CUBE);
				break;
			case "V5":
			case "Long Cone":
				path = new Asymetric3DPath(new PointF(x, y), radius, radius * 4, ASYMETRIC_3D_STYLE.CONE);
				break;
			case "V6":
			case "Cone":
				path = new Asymetric3DPath(new PointF(x, y), radius, radius * 3, ASYMETRIC_3D_STYLE.CONE);
				break;
		}

		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-45, 45, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawVirus(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 8);
			variant = "V" + nr;
		}

		drawVirus(x, y, paint, radius, variant);
	}

	protected void drawVirus(final int x, final int y, final Paint paint, final int radius, final String variant) {
		Path path;
		switch (variant) {
			default:
			case "V1":
				path = new VirusPath(new Point(x, y), radius, getFilledBoolean());
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
			case "V7":
				path = new VirusPath7(new Point(x, y), radius, getFilledBoolean());
				break;
			case "V8":
				path = new VirusPath8(new Point(x, y), radius, getFilledBoolean());
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}

		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			// paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawWeather(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 7);
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
				path = new SunPath(5 + Settings.getAnzahlFlowerLeafs(3, 10), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_V1);
				break;
			case "V2":
			case "Cloud":
				path = new CloudPath(new Point(x, y), radius, getFilledBoolean());
				break;
			case "V3":
			case "Sun with Flames":
				path = new SunPath(15 + Settings.getAnzahlFlowerLeafs(1, 10), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_FLAMES);
				break;
			case "V4":
			case "Sun with Flames (Drop Style)":
				path = new SunPath(8 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_FLAMES_DROP);
				break;
			case "V5":
			case "Sun with Flames (Triangle)":
				path = new SunPath(12 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_TRIANGLE);
				break;
			case "V6":
			case "Sun with Flames (Arrows)":
				path = new SunPath(12 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_ARROW_TRIANGLES);
				break;
			case "V7":
			case "Sun with Flames (Arrows V2)":
				path = new SunPath(5 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_ARROW_V2);
				break;
			case "V8":
			case "Sun with Flames (Sharp Tooth)":
				path = new SunPath(5 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, getFilledBoolean(), SUN_TYPE.SUN_SHARP_TOOTH);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}

		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
				path = new ShellV2Path(15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius * 1.5f, ShellV2Path.VARIANTE_OUTER,
						getFilledBoolean());
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			PathHelper.mirrorPathLeftRight(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			PathHelper.mirrorPathLeftRight(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius / 2);
			paint.setStrokeCap(Cap.ROUND);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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

		PathHelper.rotatePath(x, y, path, getRotationDegrees(-45, 45, bWidth, bHeight, new Point(x, y)));
		// Mirror only on random rotation
		if (Settings.isRandomRotate() && getRandomBoolean()) {
			PathHelper.mirrorPathLeftRight(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path, GLOSSY_REFLECTIONS_STYLE.TOP_LEFT);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private void drawMaterial(final int x, final int y, final Paint paint, final int radius) {
		final String variante = Settings.getSelectedPatternVariant();
		drawMaterial(x, y, paint, radius, variante);
	}

	private void drawMaterial(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "Stripe":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE);
				break;
			case "Stripe V2":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE_V2);
				break;
			case "Stripe V3":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE_V3);
				break;
			case "Arc 1":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC1);
				break;
			case "Arc 2":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC2);
				break;
			case "Arc 3":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC3);
				break;
			case "Skyline":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.SKYLINE);
				break;
			case "Pyramide Skyline":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.PYRAMIDE_SKYLINE);
				break;
			case "Edgy Bars":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.EDGY_BARS);
				break;
			case "Rotating Bars":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_BARS);
				break;
			case "Rotating Triangles":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_TRIANGLES);
				break;
			case "Half Stripe":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE);
				break;
			case "Half Stripe V2":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE_V2);
				break;
			case "Half Stripe V3":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE_V3);
				break;
			case "Rotating Arches (random arches)":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_ARCHES_RANDOM_SIZE);
				break;
			case "Rotating Arches (quarter arches)":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_QUARTER_ARCHES);
				break;
			case "Rotating Arches (half arches)":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_HALF_ARCHES);
				break;
			case "Rotating Arches (3-quarter arches)":
				path = new MaterialPath(new Point(x, y), radius, getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_THREE_QUARTER_ARCHES);
				break;
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
			PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 180, bWidth, bHeight, new Point(x, y)));
		} else {
			int degr = getRotationDegrees(0, 180, bWidth, bHeight, new Point(x, y));
			if (getRandomBoolean()) {
				degr = degr + 90;
			}
			PathHelper.rotatePath(x, y, path, degr);
		}
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
			// drawGlossyPathCenterGlow(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawPuzzle(final int x, final int y, final Paint paint, final int radius, final PUZZLE_CONNECTION puzzleConnection) {
		final String variant = Settings.getSelectedPatternVariant();
		drawPuzzle(x, y, paint, radius, variant, puzzleConnection);
	}

	protected void drawPuzzle(final int x, final int y, final Paint paint, final int radius, final String variante, final PUZZLE_CONNECTION puzzleConnection) {
		Path path;
		switch (variante) {
			default:
			case "Mixed":
				path = new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.RANDOM, puzzleConnection, getFilledBoolean());
				break;
			case "Manneken":
				path = new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.MANNEKEN, puzzleConnection, getFilledBoolean());
				break;
			case "Top-Right":
				path = new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.OBEN_RECHTS, puzzleConnection, getFilledBoolean());
				break;
			case "Cross":
				path = new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.KREUZ, puzzleConnection, getFilledBoolean());
				break;
			case "All":
				path = new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.ALL, puzzleConnection, getFilledBoolean());
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawAssorted(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 16);
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
					PathHelper.mirrorPathLeftRight(x, y, path);
				}
				break;
			case "V3":
			case "Crop Circles":
				path = new DotSpiralPath(3, new Point(x, y), radius, 0);
				if (Settings.isRandomRotate() && getRandomBoolean()) {
					PathHelper.mirrorPathLeftRight(x, y, path);
				}
				break;
			case "V4":
			case "Roses":
				path = new RosePath(new Point(x, y), radius);
				break;
			case "V5":
			case "Pillows":
				path = new PillowPath(4, new PointF(x, y), radius);
				break;
			case "V6":
			case "Android":
				path = new AndroidPath(new Point(x, y), radius, ROBOT_STYLE.ANDROID);
				break;
			case "V7":
			case "Footprint":
				path = new FootprintPath(new Point(x, y), radius, "V1");
				if (getRandomBoolean()) {
					PathHelper.mirrorPathLeftRight(x, y, path);
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
				path = new DropPath(new PointF(x, y), radius);
				break;
			case "V12":
			case "Ikae Robot":
				path = new AndroidPath(new Point(x, y), radius, ROBOT_STYLE.IKEA);
				break;
			case "V13":
			case "Hedgehog":
				path = new IgelPath(new Point(x, y + radius / 2), radius * 1.3f);
				if (getFilledBoolean()) {
					PathHelper.mirrorPathLeftRight(x, y, path);
				}
				break;
			case "V14":
			case "Dandelion":
				path = new DandelionPath(new Point(x, y), radius);
				break;
			case "V15":
			case "Deathstar":
				path = new StarwarsPath(new Point(x, y), radius, STARWARS_TYPE.DEATHSTAR);
				break;
			case "V16":
			case "R2D2":
				path = new StarwarsPath(new Point(x, y), radius, STARWARS_TYPE.R2D2);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawSonic(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawSonic(x, y, paint, radius, variant);
	}

	protected void drawSonic(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		int leafs = Settings.getAnzahlFlowerLeafs(1, 10);
		if (leafs % 2 == 0) {
			// wir haben ne gerade zahl... und müssen ungerade werden... eins drauf addieren
			leafs++;
		}
		final int rounds = 2 + leafs;

		switch (variante) {
			default:
			case "V1":
			case "Normal":
				path = new SonicPath(new PointF(x, y), radius, SONICTYPE.NORMAL, rounds, getFilledBoolean());
				break;
			case "V2":
			case "Double":
				path = new SonicPath(new PointF(x, y), radius, SONICTYPE.DOUBLE, rounds, getFilledBoolean());
				break;
			case "V3":
			case "Special":
				path = new SonicPath(new PointF(x, y), radius, SONICTYPE.SPECIAL, rounds, getFilledBoolean());
				break;
			case "V4":
			case "One Arm":
				path = new SonicPath(new PointF(x, y), radius, SONICTYPE.NORMAL, 3, getFilledBoolean());
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	protected void drawSquare(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 4);
			variant = "V" + nr;
		}
		drawSquare(x, y, paint, radius, variant);
	}

	protected void drawSquare(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "V1":
			case "Square (round inner corner)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.INNER_ROUND);
				break;
			case "V2":
			case "Square (square inner corner)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.INNER_RECT);
				break;
			case "V3":
			case "Square (line corner)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.DIAGONAL_LINE);
				break;
			case "V4":
			case "Square (round inner corner V2)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.INNER_ROUND2);
				break;
			case "V5":
			case "Square (circle corner)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.CIRCLE);
				break;
			case "V6":
			case "Square (outer circle corner)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.OUTER_CIRCLE);
				break;
			case "V7":
			case "Square (rounded)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.ROUNDED);
				break;
			case "V8":
			case "Square (castel)":
				path = new SquareCornered(new PointF(x, y), radius, getFilledBoolean(), CORNERED_STYLE.CASTEL);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawPillow(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 12);
			variant = "V" + nr;
		}
		drawPillow(x, y, paint, radius, variant);
	}

	protected void drawPillow(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "V1":
			case "3 Edge Pillow":
				path = new PillowPath(3, new PointF(x, y), radius);
				break;
			case "V2":
			case "4 Edge Pillow":
				path = new PillowPath(4, new PointF(x, y), radius);
				break;
			case "V3":
			case "5 Edge Pillow":
				path = new PillowPath(5, new PointF(x, y), radius);
				break;
			case "V4":
			case "6 Edge Pillow":
				path = new PillowPath(6, new PointF(x, y), radius);
				break;
			case "V5":
			case "Plectrum":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.PLEKTRUM);
				break;
			case "V6":
			case "Fingernail":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.FINGERNAIL);
				break;
			case "V7":
			case "Treky":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.TREKY);
				break;
			case "V8":
			case "YingYang":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.YINGYANG);
				break;
			case "V9":
			case "Peeek":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.PEEEK);
				break;
			case "V10":
			case "Armor":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.ARMOR);
				break;
			case "V11":
			case "Messer":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.MESSER);
				break;
			case "V12":
			case "Blazon":
				path = new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.BLAZON);
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
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
			PathHelper.rotatePath(x, y, path, 90 * getRandomInt(-1, 4));
		}
		bitmapCanvas.drawPath(path, paint);
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	protected void drawXmas(final int x, final int y, final Paint paint, final int radius) {
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 1);
			variant = "V" + nr;
		}
		drawXmas(x, y, paint, radius, variant);
	}

	protected void drawXmas(final int x, final int y, final Paint paint, final int radius, final String variante) {
		Path path;
		switch (variante) {
			default:
			case "V1":
			case "Tree":
				path = new XmasTreePath(new Point(x, y), radius, getFilledBoolean());
				break;
		}
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		// Glossy
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// Bubble
	// #########################################################################################

	private void drawBubble(final int x, final int y, final Paint paint, final int radius) {
		// Bubble
		final Path path = new CirclePath(new PointF(x, y), radius, radius / 2, true, CIRCLE_STYLE.CIRCLE);
		bitmapCanvas.drawCircle(x, y, radius, paint);
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path, GLOSSY_REFLECTIONS_STYLE.BIG_OVAL);
		}
		// outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// Smiley
	// #########################################################################################
	private Path getSmileyPath(final int x, final int y, final int radius) {
		final Path path;
		String variant = Settings.getSelectedPatternVariant();
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 2);
			variant = "V" + nr;
		}
		switch (variant) {
			default:
			case "V1":
			case "Happy":
				path = new SmileyPath(new Point(x, y), radius, SMILEY_TYPE.HAPPY);
				break;
			case "V2":
			case "Sad":
				path = new SmileyPath(new Point(x, y), radius, SMILEY_TYPE.SAD);
				break;
		}
		return path;
	}

	private void drawSmiley(final int x, final int y, final Paint paint, final int radius) {
		final Path path = getSmileyPath(x, y, radius);
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);
		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path, GLOSSY_REFLECTIONS_STYLE.BIG_OVAL);
		}
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// Packman
	// #########################################################################################

	private Path getPacmanPath(final int x, final int y, final int radius) {
		Path path;
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 2);
			variante = "V" + nr;
		}
		switch (variante) {
			default:
			case "Ghost":
			case "V1":
				path = new PacmanPath(new Point(x, y), radius, PACMAN_STYLE.GHOST);
				break;
			case "Pacman":
			case "V2":
				path = new PacmanPath(new Point(x, y), radius, PACMAN_STYLE.PACMAN);
				break;
		}
		return path;
	}

	private void drawPacman(final int x, final int y, final Paint paint, final int radius) {
		final Path path = getPacmanPath(x, y, radius);
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);

		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path, GLOSSY_REFLECTIONS_STYLE.SMALL_OVAL);
		}
		// Ring
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// Heart
	// #########################################################################################
	private Path getHeartPath(final int x, final int y, final int radius) {
		Path path;
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 6);
			variante = "V" + nr;
		}
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

	private void drawHeart(final int x, final int y, final Paint paint, final int radius) {
		// Heart für dropshadow
		final Path path = getHeartPath(x, y, radius);
		PathHelper.rotatePath(x, y, path, getRotationDegrees(-30, 30, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);

		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path, GLOSSY_REFLECTIONS_STYLE.TOP_LEFT);
		}
		// outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// Star
	// #########################################################################################
	private Path getStarPath(final int x, final int y, final int radius, final int arms) {
		Path path;
		String variante = Settings.getSelectedPatternVariant();
		if (variante.equalsIgnoreCase("Mixed")) {
			final int nr = getRandomInt(0, 3);
			variante = "V" + nr;
		}
		switch (variante) {
			default:
			case "V1":
			case "Normal":
				path = new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.NORMAL, getFilledBoolean());
				break;
			case "V2":
			case "Spikey":
				path = new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.SPIKEY, getFilledBoolean());
				break;
			case "V3":
			case "Star Circle":
				path = new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.STAR_CIRCLE, getFilledBoolean());
				break;
		}
		return path;
	}

	private void drawStar(final int x, final int y, final Paint paint, final int radius) {
		// Star
		final int arms = Settings.getAnzahlFlowerLeafs(5, 10);
		final Path path = getStarPath(x, y, radius, arms);
		PathHelper.rotatePath(x, y, path, getRotationDegrees(0, 360 / arms, bWidth, bHeight, new Point(x, y)));
		bitmapCanvas.drawPath(path, paint);

		if (Settings.isGlossy()) {
			drawGlossyPath(x, y, paint, radius, path);
		}
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	// #########################################################################################
	// Service methods
	// #########################################################################################
	protected int getColorFromBitmap(final Bitmap bmp, final Bitmap refbmp, final int x, final int y) {

		int xx = Math.min(x, bWidth - 1);
		int yy = Math.min(y, bHeight - 1);
		xx = Math.max(xx, 0);
		yy = Math.max(yy, 0);
		if (Settings.isDynamicColoring() && Settings.isSameGradientAsPatterns()) {
			return bmp.getPixel(xx, yy);
		} else {
			return refbmp.getPixel(xx, yy);
		}

	}

	protected void setupPaintForOutline(final Paint paint, final int radius) {
		paint.setStyle(Style.STROKE);
		float strokewidth = radius / 45f * Settings.getOutlineThicknessAdjustment();
		// Log.i("outline", "Thickness = " + strokewidth);
		if (strokewidth > Settings.getOutlineThicknessLimit()) {
			strokewidth = Settings.getOutlineThicknessLimit();
		}
		if (strokewidth < 1.0f) {
			strokewidth = 0;
		}
		paint.setStrokeWidth(strokewidth);
		// paint.setStrokeWidth(Math.round(strokewidth));
		paint.setShader(null);
		paint.setShadowLayer(0, 0, 0, 0);
		if (Settings.isCustomOutlineColor()) {
			// alpha merken ...die customcoloer ist immer alpha 255
			final int alpha = paint.getAlpha();
			paint.setColor(Settings.getCustomOutlineColor());
			paint.setAlpha(alpha);
		} else {
			paint.setColor(ColorHelper.changeBrightness(paint.getColor(), Settings.getOutlineDarkness()));
		}
		if (Settings.isOutlineNeverTransparent()) {
			paint.setAlpha(255);
		}
	}

	protected int getDropShadowRadius() {
		int dropShadowRadius = Math.round(bWidth * 0.01f * Settings.getDropShadowRadiusAdjustment());
		if (dropShadowRadius < 5) {
			dropShadowRadius = 5;
		}
		return dropShadowRadius;
	}

	private static int getRotationDegrees(final int randomMin, final int randomMax, final int bWidth, final int bHeight, final Point center) {
		switch (Settings.getRotationStyle()) {
			default:
			case "Fixed":
				return Settings.getFixedRotationDegrees();
			case "Random":
				return Randomizer.getRandomInt(randomMin - 1, randomMax);
			case "Around Point": {
				final float distTCenterX = bWidth / 2 - center.x;
				final float distTCenterY = bHeight / 2 - center.y;
				final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
				int winkel = (int) (alpha * 180 / Math.PI);
				// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
				if (center.x <= bWidth / 2) {
					winkel = winkel + 180;
				}
				return winkel + 90 + Settings.getFixedRotationDegrees();
			}
			case "Around Bottom": {
				final float distTCenterX = bWidth / 2 - center.x;
				final float distTCenterY = bHeight - center.y;
				final float alpha = (float) Math.atan(distTCenterY / distTCenterX);
				int winkel = (int) (alpha * 180 / Math.PI);
				// Log.i("Winkel", "Winkel = " + winkel + "(" + alpha + ")");
				if (center.x <= bWidth / 2) {
					winkel = winkel + 180;
				}
				return winkel + 90 + Settings.getFixedRotationDegrees();
			}
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

	// ##########################################################################
	// Glossy drawing
	// ##########################################################################

	private static void setupPaintShaderCenterGlow(final int x, final int y, final Paint paint, final int radius) {
		final int colorBrighter = ColorHelper.changeBrightness(paint.getColor(), Settings.getGlossyCenterGlowBrighness());
		final int colorDarker = ColorHelper.changeBrightness(paint.getColor(), Settings.getGlossyCenterGlowOuterDarkness());
		paint.setShadowLayer(0, 0, 0, 0);
		paint.setShader(new RadialGradient(x, y, radius, colorBrighter, colorDarker, Shader.TileMode.CLAMP));
	}

	private static void setupPaintShaderLinearGlow(final int x, final int y, final Paint paint, final int radius) {
		final int colorBrighter = ColorHelper.changeBrightness(paint.getColor(), Settings.getGlossyCenterGlowBrighness());
		final int colorDarker = ColorHelper.changeBrightness(paint.getColor(), Settings.getGlossyCenterGlowOuterDarkness());
		paint.setShadowLayer(0, 0, 0, 0);
		final int colors[] = { colorDarker, colorBrighter, colorDarker };
		final float dists[] = { 0f, 0.5f, 1f };
		paint.setShader(new LinearGradient(//
				x, //
				y - radius, //
				x, //
				y + radius, //
				colors, dists, Shader.TileMode.CLAMP));
	}

	private void setupPaintShaderDiagonalReflection(final int x, final int y, final Paint paint, final int radius, final boolean flipped) {
		final int white = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { transparent, white, transparent };
		final float dists[] = { 0f, 0.5f, 0.51f };
		if (!flipped) {
			paint.setShader(new LinearGradient(//
					x - radius, //
					y - radius * 2 / 3, //
					x + radius, //
					y + radius * 2 / 3, //
					colors, dists, Shader.TileMode.CLAMP));
		} else {
			paint.setShader(new LinearGradient(//
					x + radius, //
					y + radius * 2 / 3, //
					x - radius, //
					y - radius * 2 / 3, //
					colors, dists, Shader.TileMode.CLAMP));
		}
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderDiagonalReflectionV2(final int x, final int y, final Paint paint, final int radius, final boolean flipped) {
		final int white1 = Color.argb(0, 255, 255, 255);
		final int white2 = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { white1, white2, transparent };
		final float dists[] = { 0f, 0.5f, 0.51f };
		if (!flipped) {
			paint.setShader(new LinearGradient(//
					x - radius, //
					y - radius, //
					x + radius, //
					y + radius, //
					colors, dists, Shader.TileMode.CLAMP));
		} else {
			paint.setShader(new LinearGradient(//
					x + radius, //
					y + radius, //
					x - radius, //
					y - radius, //
					colors, dists, Shader.TileMode.CLAMP));
		}
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderDiagonalCurvedReflection(final int x, final int y, final Paint paint, final int radius) {
		final int white2 = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { transparent, transparent, white2, transparent };
		final float dists[] = { 0f, 0.66f, 0.99f, 1f };
		paint.setShader(new RadialGradient(//
				x - 3.0f * radius, //
				y - 1.5f * radius, //
				radius * 3.4f, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderDiagonalCurvedReflectionV2(final int x, final int y, final Paint paint, final int radius) {
		final int white2 = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { transparent, transparent, white2, transparent };
		final float dists[] = { 0f, 0.66f, 0.661f, 1f };
		paint.setShader(new RadialGradient(//
				x + radius, //
				y + radius, //
				radius * 3f, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderCurvedFromTopReflection(final int x, final int y, final Paint paint, final int radius) {
		final int white2 = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { transparent, transparent, white2, transparent };
		final float dists[] = { 0f, 0.66f, 0.99f, 1.0f };
		paint.setShader(new RadialGradient(//
				x, //
				y - 3.0f * radius, //
				radius * 2.8f, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderForOval(final int x, final int y, final Paint paint, final RectF oval) {
		int brigntness = Settings.getGlossyReflectionBrightness() * 2;
		if (brigntness > 255) {
			brigntness = 255;
		}
		final int white1 = Color.argb(brigntness, 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		paint.setShader(new LinearGradient(x, oval.top, x, oval.bottom, //
				white1, transparent, //
				Shader.TileMode.CLAMP));
		// final PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.SRC_IN);
		// paint.setXfermode(xfermode);
		paint.setStyle(Style.FILL);
	}

	public void setupPaintShaderForTopGlow(final int x, final int y, final Paint paint, final int radius) {
		int brigntness = Settings.getGlossyReflectionBrightness() * 2;
		if (brigntness > 255) {
			brigntness = 255;
		}
		final int white = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int white2 = Color.argb(Settings.getGlossyReflectionBrightness() * 2 / 3, 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { white, white2, transparent };
		final float dists[] = { 0f, 0.5f, 1f };
		paint.setShader(new RadialGradient(//
				x, //
				y - radius * 0.5f, //
				radius * 0.5f, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	public void setupPaintShaderForBottomGlow(final int x, final int y, final Paint paint, final int radius) {
		int brigntness = Settings.getGlossyReflectionBrightness() * 2;
		if (brigntness > 255) {
			brigntness = 255;
		}
		final int white = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		final int white2 = Color.argb(Settings.getGlossyReflectionBrightness() * 2 / 3, 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { white, white2, transparent };
		final float dists[] = { 0f, 0.5f, 1f };
		paint.setShader(new RadialGradient(//
				x, //
				y + radius * 0.8f, //
				radius * 0.5f, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderForTopLeftReflection(final int x, final int y, final Paint paint, final int radius) {
		final int brigntness = Settings.getGlossyReflectionBrightness();
		// final int white1 = Color.argb(brigntness, 255, 255, 255);
		final int white1 = ColorHelper.changeBrightness(paint.getColor(), brigntness);
		final int white2 = Color.argb(brigntness, 255, 255, 255);
		final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { white1, white2, transparent };
		final float dists[] = { 0f, 0.45f, 0.7f };
		paint.setShader(new LinearGradient(//
				x - radius, //
				y - radius, //
				x, //
				y, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	private void setupPaintShaderForTopLeftReflectionV2(final int x, final int y, final Paint paint, final int radius) {
		final int white2 = ColorHelper.changeBrightness(paint.getColor(), Settings.getGlossyReflectionBrightness());
		final int color = paint.getColor();
		final int transparent = Color.argb(0, Color.red(color), Color.green(color), Color.blue(color));
		// final int white2 = Color.argb(Settings.getGlossyReflectionBrightness(), 255, 255, 255);
		// final int transparent = Color.argb(0, 255, 255, 255);
		final int colors[] = { transparent, transparent, white2 };
		final float dists[] = { 0f, 0.55f, 1f };
		paint.setShader(new RadialGradient(//
				x + radius, //
				y + radius * 0.5f, //
				radius * 2.1f, //
				colors, dists, Shader.TileMode.CLAMP));
		paint.setStyle(Style.FILL);
	}

	public void drawGlossyPath(final int x, final int y, final Paint paint, final int radius, final Path path) {
		drawGlossyPath(x, y, paint, radius, path, Settings.getGlossyReflectionStyle());

	}

	public void drawGlossyPath(final int x, final int y, final Paint paint, final int radius, final Path path, final GLOSSY_REFLECTIONS_STYLE reflectionStyle) {
		// Glossy glow
		switch (Settings.getGlossyGlowStyle()) {
			default:
			case CENTER:
				setupPaintShaderCenterGlow(x, y, paint, radius);
				break;
			case HORIZONTAL:
				setupPaintShaderLinearGlow(x, y, paint, radius);
				break;
		}
		bitmapCanvas.drawPath(path, paint);
		// Reflection
		RectF oval;
		switch (reflectionStyle) {
			default:
			case TOP_GLOW:
				setupPaintShaderForTopGlow(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case BOTTOM_GLOW:
				setupPaintShaderForBottomGlow(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case BIG_OVAL:
				oval = new RectF(x - radius * 3 / 4, y - radius, x + radius * 3 / 4, y);
				setupPaintShaderForOval(x, y, paint, oval);
				bitmapCanvas.drawOval(oval, paint);
				// oval = new RectF(x - radius * 4 / 10, y - radius, x + radius * 4 / 10, y - radius * 0.6f);
				// setupPaintShaderForOval(x, y, paint, oval);
				// bitmapCanvas.drawOval(oval, paint);
				break;
			case SMALL_OVAL:
				oval = new RectF(x - radius * 6 / 10, y - radius * 0.95f, x + radius * 6 / 10, y - radius * 0.2f);
				setupPaintShaderForOval(x, y, paint, oval);
				bitmapCanvas.drawOval(oval, paint);
				break;
			case DIAGONAL:
				setupPaintShaderDiagonalReflection(x, y, paint, radius, false);
				bitmapCanvas.drawPath(path, paint);
				break;
			case DIAGONAL_FLIPPED:
				setupPaintShaderDiagonalReflection(x, y, paint, radius, true);
				bitmapCanvas.drawPath(path, paint);
				break;
			case DIAGONAL_45GRAD:
				setupPaintShaderDiagonalReflectionV2(x, y, paint, radius, false);
				bitmapCanvas.drawPath(path, paint);
				break;
			case DIAGONAL_45GRAD_FLIPPED:
				setupPaintShaderDiagonalReflectionV2(x, y, paint, radius, true);
				bitmapCanvas.drawPath(path, paint);
				break;
			case DIAGONAL_CURVED:
				setupPaintShaderDiagonalCurvedReflection(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case DIAGONAL_CURVED_V2:
				setupPaintShaderDiagonalCurvedReflectionV2(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case CURVED_FROM_TOP:
				setupPaintShaderCurvedFromTopReflection(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case TOP_LEFT:
				// Glossy glow von rechts oben
				setupPaintShaderForTopLeftReflection(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case TOP_LEFT_V2:
				// Glossy glow von rechts oben
				setupPaintShaderForTopLeftReflectionV2(x, y, paint, radius);
				bitmapCanvas.drawPath(path, paint);
				break;
			case NONE:
				break;
		}
		paint.setShader(null);
	}

	public void setupDropShadow(final Bitmap refbitmap, final int dropShadowRadius, final Paint paint, final int x, final int y, final int pcolor) {

		final int dX = Settings.getDropShadowOffsetX();
		final int dY = Settings.getDropShadowOffsetY();
		switch (Settings.getDropShadowType()) {
			default:
			case "No":
				paint.setShadowLayer(0, 0, 0, Color.BLACK);
				break;
			case "Random":
				final int sx = getRandomInt(0, bWidth - 1);
				final int sy = getRandomInt(0, bHeight - 1);
				final int scolor = getColorFromBitmap(bitmap, refbitmap, sx, sy);
				paint.setShadowLayer(dropShadowRadius, dX, dY, scolor);
				break;
			case "Opposite":
				paint.setShadowLayer(dropShadowRadius, dX, dY, getColorFromBitmap(bitmap, refbitmap, bWidth - 1 - x, bHeight - 1 - y));
				break;
			case "Darker":
				paint.setShadowLayer(dropShadowRadius, dX, dY, ColorHelper.changeBrightness(pcolor, Settings.getDropShadowDarkness()));
				break;
			case "Select":
				final int shd = Settings.getDropShadowColor();
				final int alpha = Color.alpha(pcolor);
				final int dc = Color.argb(alpha, Color.red(shd), Color.green(shd), Color.blue(shd));
				paint.setShadowLayer(dropShadowRadius, dX, dY, dc);
				break;
		}
	}

}
