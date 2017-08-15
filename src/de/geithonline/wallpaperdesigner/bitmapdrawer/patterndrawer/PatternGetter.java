
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.CubeOptions;
import de.geithonline.wallpaperdesigner.settings.EyeOptions;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;
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
import de.geithonline.wallpaperdesigner.shapes.D3CubePath;
import de.geithonline.wallpaperdesigner.shapes.D3ImpossibleTrianglePath;
import de.geithonline.wallpaperdesigner.shapes.D3PyramidePath;
import de.geithonline.wallpaperdesigner.shapes.D3ZylinderPath;
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
import de.geithonline.wallpaperdesigner.shapes.IronCrossPath;
import de.geithonline.wallpaperdesigner.shapes.IronCrossPath.IRONCROSS_TYPE;
import de.geithonline.wallpaperdesigner.shapes.LeafPath;
import de.geithonline.wallpaperdesigner.shapes.LeafPath.LEAF_STYLE;
import de.geithonline.wallpaperdesigner.shapes.LighthousePath;
import de.geithonline.wallpaperdesigner.shapes.LinePath;
import de.geithonline.wallpaperdesigner.shapes.LinePath.LINE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.LogoPathEX;
import de.geithonline.wallpaperdesigner.shapes.LogoPathEX.LOGO_STYLE_EX;
import de.geithonline.wallpaperdesigner.shapes.LogoPathHandys;
import de.geithonline.wallpaperdesigner.shapes.LogoPathHandys.HANDY_STYLE;
import de.geithonline.wallpaperdesigner.shapes.LogoPathPeace;
import de.geithonline.wallpaperdesigner.shapes.LogoPathPeace.LOGO_STYLE_PEACE;
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
import de.geithonline.wallpaperdesigner.shapes.PumpkinPath;
import de.geithonline.wallpaperdesigner.shapes.PumpkinPath.PUMPKIN_TYP;
import de.geithonline.wallpaperdesigner.shapes.PuzzlePath;
import de.geithonline.wallpaperdesigner.shapes.PuzzlePath.PUZZLE_CONNECTION;
import de.geithonline.wallpaperdesigner.shapes.PuzzlePath.PUZZLE_TYPE;
import de.geithonline.wallpaperdesigner.shapes.QuakePath;
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
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.shapes.SkullPath;
import de.geithonline.wallpaperdesigner.shapes.SmileyPath;
import de.geithonline.wallpaperdesigner.shapes.SmileyPath.SMILEY_TYPE;
import de.geithonline.wallpaperdesigner.shapes.SonicPath;
import de.geithonline.wallpaperdesigner.shapes.SonicPath.SONICTYPE;
import de.geithonline.wallpaperdesigner.shapes.SpaceshipPath;
import de.geithonline.wallpaperdesigner.shapes.SpiralPath;
import de.geithonline.wallpaperdesigner.shapes.SplatterPath;
import de.geithonline.wallpaperdesigner.shapes.SplatterPath.SPLATTER_TYPE;
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
import de.geithonline.wallpaperdesigner.shapes.TextPath;
import de.geithonline.wallpaperdesigner.shapes.TrailObjectPath;
import de.geithonline.wallpaperdesigner.shapes.TrailObjectPath.TRAIL_OBJECT_TYPE;
import de.geithonline.wallpaperdesigner.shapes.TrailObjectPath.TRAIL_TYPE;
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
import de.geithonline.wallpaperdesigner.shapes.WurfSternPath;
import de.geithonline.wallpaperdesigner.shapes.XEckPath;
import de.geithonline.wallpaperdesigner.shapes.XmasTreePath;
import de.geithonline.wallpaperdesigner.shapes.YingYangPath;
import de.geithonline.wallpaperdesigner.shapes.ZitronePath;
import de.geithonline.wallpaperdesigner.shapes.composed.EQualleType;
import de.geithonline.wallpaperdesigner.shapes.composed.MultiSinusLinesPath;
import de.geithonline.wallpaperdesigner.shapes.composed.PenguinPath;
import de.geithonline.wallpaperdesigner.shapes.composed.QuallePath;
import de.geithonline.wallpaperdesigner.shapes.composed.QualleTopviewPath;
import de.geithonline.wallpaperdesigner.shapes.composed.QualleTopviewPreset01;
import de.geithonline.wallpaperdesigner.shapes.composed.QualleTopviewPreset02;
import de.geithonline.wallpaperdesigner.shapes.composed.QualleTopviewPreset03Spiral;
import de.geithonline.wallpaperdesigner.shapes.composed.QualleTopviewPreset04Heart;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class PatternGetter {

	public static Bitmap drawIconBitmap(final int initialSize, final String pattern, final String variant) {
		final float radius = initialSize;
		final Path path = PatternGetter.getPath(initialSize / 2, initialSize, (int) radius, initialSize, initialSize, pattern, variant);
		final RectF bounds = new RectF();
		path.computeBounds(bounds, true);
		Log.i("Bounds", "=" + bounds);
		final float wi = bounds.right - bounds.left;
		final float hi = bounds.bottom - bounds.top;
		Bitmap bitmap;
		if (wi > hi) {
			bitmap = Bitmap.createBitmap((int) wi, (int) wi, Bitmap.Config.ARGB_8888);
		} else {
			bitmap = Bitmap.createBitmap((int) hi, (int) hi, Bitmap.Config.ARGB_8888);
		}
		final Canvas bitmapCanvas = new Canvas(bitmap);
		bitmapCanvas.translate(-bounds.left, -bounds.top);

		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.LTGRAY);
		if (pattern.contains("Lines")) {
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(2f);
		} else {
			paint.setStyle(Style.FILL);
		}
		bitmapCanvas.drawPath(path, paint);
		final Bitmap b = Bitmap.createScaledBitmap(bitmap, initialSize, initialSize, true);
		if (!b.equals(bitmap)) {
			bitmap.recycle();
		}
		return b;
	}

	// private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static int bWidth;
	private static int bHeight;

	public static Path getPath(final int x, final int y, final int radius, final int w, final int h, final String pattern, final String variant) {
		bWidth = w;
		bHeight = h;
		switch (pattern) {

		case "Gears-Saws":
			return drawGearSaw(x, y, radius, variant);
		case "Spooky":
			return drawSpooky(x, y, radius, variant);
		case "Assorted Shapes":
			return drawAssorted(x, y, radius, variant);
		case "Chess":
			return drawChess(x, y, radius, variant);
		case "Flipped":
			return drawFlipped(x, y, radius, variant);
		case "Geometrical Shapes":
			return drawGeometric(x, y, radius, variant);
		case "Geometrical (long) Shapes":
			return drawGeometricLong(x, y, radius, variant);
		case "3D (long) Shapes":
			return draw3DLongShape(x, y, radius, variant);
		case "3D Objects":
		case "3D Cubes":
			return draw3DCubes(x, y, radius, variant);
		case "Rings":
			return drawRing(x, y, radius, variant);
		case "Bubbles":
			return drawBubble(x, y, radius, variant);
		case "Hearts":
			return drawHeart(x, y, radius, variant);
		case "Invertable Shapes":
			return drawInvertable(x, y, radius, variant);
		case "Jellyfish":
			return drawQualle(x, y, radius, variant);
		case "Jellyfish Topview":
			return drawQualleTopview(x, y, radius, variant);
		case "Penguin":
			return drawPenguin(x, y, radius, variant);
		case "Lines":
			return drawLines(x, y, radius, variant);
		case "Lines (Directed)":
			return drawLinesDirected(x, y, radius, variant);
		case "Leafs":
			return drawLeafs(x, y, radius, variant);
		case "Logos":
			return drawLogos(x, y, radius, variant);
		case "PacMan":
			return drawPacman(x, y, radius, variant);
		case "Pillows":
			return drawPillow(x, y, radius, variant);
		case "Puzzle":
			return drawPuzzle(x, y, radius, variant, PUZZLE_CONNECTION.NORMAL);
		case "Puzzle (Circle Connector)":
			return drawPuzzle(x, y, radius, variant, PUZZLE_CONNECTION.CIRCLE);
		case "Puzzle (Square)":
			return drawPuzzle(x, y, radius, variant, PUZZLE_CONNECTION.RECT_NORMAL);
		case "Puzzle (Square Connector)":
			return drawPuzzle(x, y, radius, variant, PUZZLE_CONNECTION.SQUARE);
		case "Smiley":
			return drawSmiley(x, y, radius, variant);
		case "Stars":
			return drawStar(x, y, radius, variant);
		case "Square":
			return drawSquare(x, y, radius, variant);
		case "Text":
			return drawText(x, y, radius, variant);
		case "Fish":
			return drawFisch(x, y, radius, variant);
		case "Flowers":
			return drawFlower(x, y, radius, variant);
		case "Mandala":
			return drawMandala(x, y, radius, variant);
		case "Maritim":
			return drawMaritim(x, y, radius, variant);
		case "Material":
			return drawMaterial(x, y, radius, variant);
		case "Rectangles":
			return drawRect(x, y, radius, variant);
		case "Rain":
			return drawRain(x, y, radius, variant);
		case "Scenes":
			return drawScene(x, y, radius, variant);
		case "Shells":
			return drawShell(x, y, radius, variant);
		case "Sonic":
			return drawSonic(x, y, radius, variant);
		case "Space":
			return drawSpace(x, y, radius, variant);
		case "Splatter":
			return drawSplatter(x, y, radius, variant);
		default:
		case "Virus Attack":
			return drawVirus(x, y, radius, variant);
		case "Weather":
			return drawWeather(x, y, radius, variant);
		case "Planes":
			return drawPlane(x, y, radius, variant);
		case "Xmas":
			return drawXmas(x, y, radius, variant);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private static Path drawGearSaw(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 3);
			variant = "V" + nr;
		}
		switch (variant) {
		default:
		case "V1":
		case "Gear":
			final int zaehne = 10 + Settings.getAnzahlFlowerLeafs(1, 10);
			return new GearPath(zaehne, new Point(x, y), radius, Settings.getFilledBoolean());
		case "V2":
		case "Saw": {
			final int zahn = 15 + Settings.getAnzahlFlowerLeafs(1, 10);
			return new SawPath(zahn, new Point(x, y), radius, Settings.getFilledBoolean(), Randomizer.getRandomBoolean());
		}
		case "V3":
		case "Star Gear": {
			final int zahn = 15 + Settings.getAnzahlFlowerLeafs(1, 10);
			return new StarPath(zahn, new PointF(x, y), radius, radius * 0.8f, Settings.getFilledBoolean());
		}
		}
	}

	private static Path drawMaritim(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}
		switch (variant) {
		default:
		case "V1":
		case "Sailboat V1":
			return new SailboatPath(new Point(x, y), radius);
		case "V2":
		case "Sailboat V2":
			return new SailboatPath2(new Point(x, y), radius);
		case "V3":
		case "Lighthouse":
			return new LighthousePath(new Point(x, y), radius);
		case "V4":
		case "Anchor":
			return new AnkerPath(new Point(x, y), radius);
		}
	}

	private static Path drawFlipped(final int x, final int y, final int radius, final String variant) {

		switch (variant) {
		default:
		case "Triangle":
			return new FlippedPath(new PointF(x, y), radius, Settings.getFilledBoolean(), FLIPPED_STYLE.TRIANGLE);
		case "Triangle V2":
			return new FlippedPath(new PointF(x, y), radius, Settings.getFilledBoolean(), FLIPPED_STYLE.TRIANGLE_V2);
		case "Square":
			return new FlippedPath(new PointF(x, y), radius, Settings.getFilledBoolean(), FLIPPED_STYLE.SQUARE);
		case "Rectangle":
			return new FlippedPath(new PointF(x, y), radius, Settings.getFilledBoolean(), FLIPPED_STYLE.RECTANGLE);
		case "Quarter Arc":
			return new FlippedPath(new PointF(x, y), radius, Settings.getFilledBoolean(), FLIPPED_STYLE.QUARTER_ARC);
		case "Quarter Arc V2":
			return new FlippedPath(new PointF(x, y), radius, Settings.getFilledBoolean(), FLIPPED_STYLE.QUARTER_ARC_V2);

		}
	}

	private static Path drawGeometric(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 6);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed (with Circle)")) {
			final int nr = Randomizer.getRandomInt(1, 9);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Square (Mixed)")) {
			variant = "Square (Mixed)";
		}

		switch (variant) {
		default:
		case "V1":
		case "Triangle":
			return new XEckPath(3, new PointF(x, y), radius, 0, Settings.getFilledBoolean());
		case "V2":
		case "Square":
			return new SquarePath(new PointF(x, y), radius, Settings.getFilledBoolean(), SQUARE_STYLE.NORMAL, Direction.CW);
		case "V3":
		case "Square (rounded)":
			return new SquarePath(new PointF(x, y), radius, Settings.getFilledBoolean(), SQUARE_STYLE.ROUNDED, Direction.CW);
		case "V4":
		case "Pentagon":
			return new XEckPath(5, new PointF(x, y), radius, 0, Settings.getFilledBoolean());
		case "V5":
		case "Hexagon":
			return new XEckPath(6, new PointF(x, y), radius, 0, Settings.getFilledBoolean());
		case "V6":
		case "Octagon":
			return new XEckPath(8, new PointF(x, y), radius, 0, Settings.getFilledBoolean());
		case "V7":
		case "Circle":
			return new CirclePath(new PointF(x, y), radius, radius / 2, Settings.getFilledBoolean(), CIRCLE_STYLE.CIRCLE);
		case "V8":
		case "Oval":
			return new OvalPath(new PointF(x, y), radius / 2, radius, Direction.CW, OVAL_TYPE.NORMAL);
		case "V9":
		case "Oval (random width)":
			return new OvalPath(new PointF(x, y), radius * 0.8f, radius, Direction.CW, OVAL_TYPE.RANDOM_WIDTH);
		case "V10":
		case "Oval (random)":
			return new OvalPath(new PointF(x, y), radius * 0.8f, radius, Direction.CW, OVAL_TYPE.RANDOM);
		case "V11":
		case "Half Circle":
			return new CirclePath(new PointF(x, y), radius, radius / 2, Settings.getFilledBoolean(), CIRCLE_STYLE.HALF_CIRCLE);
		case "V12":
		case "Square (Mixed)":
			return new SquarePath(new PointF(x, y), radius, Settings.getFilledBoolean(), SQUARE_STYLE.MIXED, Direction.CW);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawLeafs(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Maple":
			return new LeafPath(new PointF(x, y), radius, LEAF_STYLE.MAPLE);
		case "V2":
		case "Weed":
			return new LeafPath(new PointF(x, y), radius, LEAF_STYLE.WEED);
		case "V3":
		case "Round Leaf":
			return new LeafPath(new PointF(x, y), radius, LEAF_STYLE.ROUND);
		case "V4":
		case "Finger Maple":
			return new LeafPath(new PointF(x, y), radius, LEAF_STYLE.FINGER_MAPLE);
		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawLogos(final int x, final int y, final int radius, final String variant) {

		switch (variant) {
		default:
		case "Resurrection Remix":
			return new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V1);
		case "Resurrection Remix V2":
			return new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V2);
		case "Resurrection Remix V3":
			return new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V3);
		case "Resurrection Remix V4":
			return new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V4);

		case "Resurrection Remix V5":
			return new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V5);

		case "Resurrection Remix V6":
			return new LogoPathRR(new PointF(x, y), radius, LOGO_STYLE_RR.RR_V6);

		case "ElementalX Kernel":
			return new LogoPathEX(new PointF(x, y), radius, LOGO_STYLE_EX.V1);

		case "Peace Sign":
			return new LogoPathPeace(new PointF(x, y), radius, LOGO_STYLE_PEACE.V1);

		case "Weed Sign":
			return new LogoPathPeace(new PointF(x, y), radius, LOGO_STYLE_PEACE.WEED);

		case "Weed Sign V2":
			return new LogoPathPeace(new PointF(x, y), radius, LOGO_STYLE_PEACE.WEED_V2);

		case "Nexus V1":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.NEXUS_V1);

		case "Nexus V2":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.NEXUS_V2);

		case "Nexus V3":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.NEXUS_V3);

		case "Oneplus One V1":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.ONEPLUSONE_V1);

		case "Oneplus One V2":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.ONEPLUSONE_V2);

		case "LG V1":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.LG_V1);

		case "LG V2":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.LG_V2);

		case "Quake":
			return new QuakePath(new PointF(x, y), radius);

		case "Moto":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.Moto);

		case "Moto (inverted)":
			return new LogoPathHandys(new PointF(x, y), radius, HANDY_STYLE.MotoInvert);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private static Path drawGeometricLong(final int x, final int y, final int radius, final String variant) {

		switch (variant) {
		default:
		case "Rectangle":
			return new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, Settings.getFilledBoolean(), RECT_ROUNDED.NORMAL);

		case "Rectangle (rounded)":
			return new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, Settings.getFilledBoolean(), RECT_ROUNDED.ROUNDED);

		case "Rectangle (Mixed)":
			return new RectangleAsymetricPath(new Point(x, y), radius, radius * 6, Settings.getFilledBoolean(), RECT_ROUNDED.MIXED);

		case "Triangle":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.TRIANGLE);

		case "Oval":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.OVAL);

		case "Diamond":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.RAUTE);

		case "Dragon":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.DRACHEN);

		case "Dragon (upsidedown)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.DRACHEN_UPSIDEDOWN);

		case "Drop":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.DROP);

		case "Drop (upsidedown)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.DROP_REVERSE);

		case "Lense":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.LENSE);

		case "Lense V2":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.LENSE_V2);

		case "Lense V3":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.LENSE_V3);

		case "Tag":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.TAG);

		case "Knife":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.KNIFE);

		case "Knife V2":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.KNIFE_V2);

		case "Knife V3":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.KNIFE_V3);

		case "Cross":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS);

		case "Cross (Sharp)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SHARP);

		case "Cross (Split)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 4, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SPLIT);

		case "Cross (Split 2)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SPLIT2);

		case "Cross (Slim)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 4, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM);

		case "Cross (Slim-Double)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 4, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM_DOUBLE);

		case "Spiky Cross":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.SPIKY_CROSS);

		case "Double Cross":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.DOUBLE_CROSS);

		case "Sperm":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 6, Settings.getFilledBoolean(), ASYMETRIC_STYLE.SPERM);

		case "Virus":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 3, Settings.getFilledBoolean(), ASYMETRIC_STYLE.VIRUS);

		case "Virus V2":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 3, Settings.getFilledBoolean(), ASYMETRIC_STYLE.VIRUS_V2);

		case "Virus V3":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 4, Settings.getFilledBoolean(), ASYMETRIC_STYLE.VIRUS_V3);

		case "Long Heart":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.LONG_HEART);

		case "Square Chain":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.SQUARE_CHAIN);

		case "Circle Chain":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CHAIN_CIRCLE);

		case "Circle Chain (upsidedown)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CHAIN_CIRCLE_UPSIDEDOWN);

		case "Spear":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.SPEAR1);

		case "Iron Cross":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.IRON_CROSS);

		case "Iron Cross (round)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.IRON_CROSS_ROUND);

		case "Bird":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 4, Settings.getFilledBoolean(), ASYMETRIC_STYLE.BIRD);

		case "Bird V2":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 4, Settings.getFilledBoolean(), ASYMETRIC_STYLE.BIRD_V2);

		case "Golf Pin":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.GOLF_PIN);

		case "Pin":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.PIN);

		case "Tulip":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.TULIP_NORMAL);

		case "Plane":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.PLANE);

		case "Arrow":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.ARROW);

		case "Cross (Slim V2)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM_V2);

		case "Cross (Slim V3)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.CROSS_SLIM_V3);

		case "Tulip (Slim)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.TULIP_SLIM);

		case "Tulip (Fat)":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.TULIP_FAT);

		case "Spaceship":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.SPACESHIP);

		case "Spaceship V2":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.SPACESHIP_V2);

		case "Ritual Axe":
			return new AsymetricLongPath(new PointF(x, y), radius, radius * 5, Settings.getFilledBoolean(), ASYMETRIC_STYLE.RITUAL_AXE);

		}
	}
	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path draw3DCubes(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}
		final CubeOptions cubeOptions = Settings.getCubeOptions();

		switch (variant) {
		default:
		case "V1":
		case "Cube":
			return new D3CubePath(new PointF(x, y), radius, radius * Randomizer.getRandomFloat(cubeOptions.minLength, cubeOptions.maxLength));
		case "V2":
		case "Pyramide":
			return new D3PyramidePath(new PointF(x, y), radius, radius * Randomizer.getRandomFloat(cubeOptions.minLength, cubeOptions.maxLength));
		case "V3":
		case "Zylinder":
			return new D3ZylinderPath(new PointF(x, y), radius, radius * Randomizer.getRandomFloat(cubeOptions.minLength, cubeOptions.maxLength));
		case "V4":
		case "Impossible Triangle":
			return new D3ImpossibleTrianglePath(new PointF(x, y), radius);
		}

	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path draw3DLongShape(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 7);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Long Pyramide":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * 4, ASYMETRIC_3D_STYLE.PYRAMIDE);

		case "V2":
		case "Pyramide":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * 2, ASYMETRIC_3D_STYLE.PYRAMIDE);

		case "V3":
		case "Long Cube":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * 4, ASYMETRIC_3D_STYLE.CUBE);

		case "V4":
		case "Cube":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * 2, ASYMETRIC_3D_STYLE.CUBE);

		case "V5":
		case "Long Cone":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * 4, ASYMETRIC_3D_STYLE.CONE);

		case "V6":
		case "Cone":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * 3, ASYMETRIC_3D_STYLE.CONE);

		case "V7":
		case "Cube (random height)":
			return new Asymetric3DPath(new PointF(x, y), radius, radius * Randomizer.getRandomInt(3, 8), ASYMETRIC_3D_STYLE.CUBE);

		}

	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawInvertable(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 9);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Plus-Minus")) {
			final int nr = Randomizer.getRandomInt(5, 6);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Heart V1":
			return new HeartPath(new PointF(x, y), radius, Settings.getFilledBoolean(), HEART_SHAPE.Curvy);

		case "V2":
		case "Heart V2":
			return new HeartPath(new PointF(x, y), radius, Settings.getFilledBoolean(), HEART_SHAPE.Straigth);

		case "V3":
		case "Arrow":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Arrow");

		case "V4":
		case "Arrow (round)":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Arrow (round)");

		case "V5":
		case "Plus":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Plus");

		case "V6":
		case "Minus":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Minus");

		case "V7":
		case "Star":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Star");

		case "V8":
		case "Gear":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Gear");

		case "V9":
		case "Crown":
			return new InvertablePath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "Crown");

		}
	}

	private static Path drawText(final int x, final int y, final int radius, final String variant) {
		return new TextPath(new PointF(x, y), radius);
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawRing(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Rings V1 (Flange)":
			return new RingPath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "V1");

		case "V2":
		case "Rings V2 (Asymetric)":
			return new RingPath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "V2");

		case "V3":
		case "Rings V3 (Concentric)":
			return new RingPath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "V3");

		case "V4":
		case "Rings V4 (Dizzy)":
			return new RingPath(new Point(x, y), radius, radius / 2, Settings.getFilledBoolean(), "V4");

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawSpace(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 10);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Rockets")) {
			final int nr = Randomizer.getRandomInt(0, 6);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Rocket V1":
			return new RocketPath(new Point(x, y), radius, Settings.getFilledBoolean(), "Rocket V1");

		case "V2":
		case "Rocket V2":
			return new RocketPath(new Point(x, y), radius, Settings.getFilledBoolean(), "Rocket V2");

		case "V3":
		case "Rocket V3":
			return new RocketPath(new Point(x, y), radius, Settings.getFilledBoolean(), "Rocket V3");

		case "V4":
		case "Rocket V4":
			return new RocketPath(new Point(x, y), radius, Settings.getFilledBoolean(), "Rocket V4");

		case "V5":
		case "Rocket V5":
			return new RocketPath(new Point(x, y), radius, Settings.getFilledBoolean(), "Rocket V5");

		case "V6":
		case "Rocket V6":
			return new RocketPath(new Point(x, y), radius, Settings.getFilledBoolean(), "Rocket V6");

		case "V7":
		case "Ufo V1":
			return new UfoPath(new Point(x, y), radius, UFO_TYPE.UfoV1, Settings.getFilledBoolean());

		case "V8":
		case "Ufo V2":
			return new UfoPath(new Point(x, y), radius, UFO_TYPE.UfoV2, Settings.getFilledBoolean());

		case "V9":
		case "Satellite":
			return new SatelitePath(new Point(x, y), radius, Settings.getFilledBoolean(), "Satellite V1");

		case "V10":
		case "Spaceship":
			return new SpaceshipPath(new PointF(x, y), radius, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawSplatter(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 6);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Cloud":
			return new SplatterPath(new PointF(x, y), radius, Settings.getFilledBoolean(), SPLATTER_TYPE.Cloud);

		case "V2":
		case "Drop":
			return new SplatterPath(new PointF(x, y), radius, Settings.getFilledBoolean(), SPLATTER_TYPE.Drop);

		case "V3":
		case "Bacteria":
			return new SplatterPath(new PointF(x, y), radius, Settings.getFilledBoolean(), SPLATTER_TYPE.Bacteria);

		case "V4":
		case "Square":
			return new SplatterPath(new PointF(x, y), radius, Settings.getFilledBoolean(), SPLATTER_TYPE.Square);

		case "V5":
		case "Triangle":
			return new SplatterPath(new PointF(x, y), radius, Settings.getFilledBoolean(), SPLATTER_TYPE.Triangle);

		case "V6":
		case "Cloud with Holes":
			return new SplatterPath(new PointF(x, y), radius, Settings.getFilledBoolean(), SPLATTER_TYPE.CloudWithHoles);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawVirus(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 8);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
			return new VirusPath(new PointF(x, y), radius, Settings.getFilledBoolean());

		case "V2":
			return new VirusPath2(new Point(x, y), radius, 13, Settings.getFilledBoolean());

		case "V3":
			return new VirusPath3(new Point(x, y), radius, 17, Settings.getFilledBoolean());

		case "V4":
			return new VirusPath4(new Point(x, y), radius);

		case "V5":
			return new VirusPath5(new Point(x, y), radius);

		case "V6":
			return new VirusPath6(new Point(x, y), radius, Settings.getFilledBoolean());

		case "V7":
			return new VirusPath7(new Point(x, y), radius, Settings.getFilledBoolean());

		case "V8":
			return new VirusPath8(new Point(x, y), radius, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawWeather(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 7);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Sun":
			return new SunPath(5 + Settings.getAnzahlFlowerLeafs(3, 10), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_V1);

		case "V2":
		case "Cloud":
			return new CloudPath(new Point(x, y), radius, Settings.getFilledBoolean());
		// return new CloudPath2(new PointF(x, y), radius, Settings.getFilledBoolean());

		case "V3":
		case "Sun with Flames":
			return new SunPath(15 + Settings.getAnzahlFlowerLeafs(1, 10), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_FLAMES);

		case "V4":
		case "Sun with Flames (Drop Style)":
			return new SunPath(8 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_FLAMES_DROP);

		case "V5":
		case "Sun with Flames (Triangle)":
			return new SunPath(12 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_TRIANGLE);

		case "V6":
		case "Sun with Flames (Arrows)":
			return new SunPath(12 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_ARROW_TRIANGLES);

		case "V7":
		case "Sun with Flames (Arrows V2)":
			return new SunPath(5 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_ARROW_V2);

		case "V8":
		case "Sun with Flames (Sharp Tooth)":
			return new SunPath(5 + Settings.getAnzahlFlowerLeafs(1, 5), new PointF(x, y), radius, Settings.getFilledBoolean(), SUN_TYPE.SUN_SHARP_TOOTH);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawPlane(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 3);
			variant = "V" + nr;
		}
		return new PlanePath(new Point(x, y), radius, variant);
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawFlower(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed V1-V3")) {
			final int nr = Randomizer.getRandomInt(1, 3);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed V4-V5")) {
			final int nr = Randomizer.getRandomInt(4, 5);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
			return new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, Settings.getFilledBoolean(), 1.0f, "Circle Filling");

		case "V2":
			return new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, Settings.getFilledBoolean(), 0.8f, "Circle Filling");

		case "V3":
			return new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, Settings.getFilledBoolean(), 0, "V3");

		case "V4":
			return new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, Settings.getFilledBoolean(), 1.0f, "Inner Flower");

		case "V5":
			return new NiceFlowerPath(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, Settings.getFilledBoolean(), 0.8f, "Inner Flower");

		case "V6":
			return new FlowerPath(new Point(x, y), radius, Settings.getAnzahlFlowerLeafs(5, 10), 5);

		case "V7":
			return new FlowerV2Path(Settings.getAnzahlFlowerLeafs(5, 10), new Point(x, y), radius, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawLines(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 5);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Spirals":
			return new SpiralPath(Randomizer.getRandomInt(3, 5), new Point(x, y), radius, Randomizer.getRandomBoolean());

		case "V2":
		case "Streamers":
			return new LuftschlangenPath(Randomizer.getRandomInt(6, 7), new Point(x, y), radius, Randomizer.getRandomBoolean());

		case "V3":
		case "Maze":
			return new RandomPath(new Point(x, y), bWidth, bHeight, Randomizer.getRandomInt(10, 40), radius, true);

		case "V4":
		case "Crickle Crackle":
			return new RandomPath(new Point(x, y), bWidth, bHeight, Randomizer.getRandomInt(5, 30), radius, false);

		case "V5":
		case "Blitz":
			return new BlitzPath(new Point(x, y), Randomizer.getRandomInt(5, 30), radius, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawLinesDirected(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 8);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Straight Line":
			return new LinePath(new PointF(x, y), radius, LINE_STYLE.straight, Settings.getFilledBoolean());

		case "V2":
		case "Sinus":
			return new SinusPath(new PointF(x, y), radius, 2 + Settings.getAnzahlFlowerLeafs(5, 15), radius * 0.1f);

		case "V3":
		case "Zig-Zag":
			return new LinePath(new PointF(x, y), radius, LINE_STYLE.zigzag, Settings.getFilledBoolean());

		case "V4":
		case "Bow":
			return new LinePath(new PointF(x, y), radius, LINE_STYLE.bow, Settings.getFilledBoolean());

		case "V5":
		case "Blitz":
			return new LinePath(new PointF(x, y), radius, LINE_STYLE.blitz, Settings.getFilledBoolean());

		case "V6":
		case "Sinus (one)":
			return new SinusPath(new PointF(x, y), radius, 2, radius * 0.2f);

		case "V7":
		case "Sinus Tail":
			return new MultiSinusLinesPath(new PointF(x, y), radius, radius * 0.7f, radius * 2, 2 + Settings.getAnzahlFlowerLeafs(5, 15), 0);

		case "V8":
		case "Sound":
			return new LinePath(new PointF(x, y), radius, LINE_STYLE.sound, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------

	public static Path drawRain(final int x, final int y, final int radius, final String variant) {
		switch (variant) {
		default:
		case "Rain":
			// this is just for icon drawing
			return drawBubble(x, y, radius / 3, "V1");
		case "Rectangle Rain":
			// this is just for icon drawing
			return new RectanglePath(new PointF(x, y), radius, radius / 4, true);
		}
	}

	public static Path drawScene(final int x, final int y, final int radius, final String variant) {
		switch (variant) {
		default:
		case "Rain":
			return new TrailObjectPath(new PointF(x, y), radius, Settings.getFilledBoolean(), //
					TRAIL_TYPE.Objects, TRAIL_OBJECT_TYPE.Stars);
		case "Trail Of Stars":
		case "Trail of Stars":
			return new TrailObjectPath(new PointF(x, y), radius, Settings.getFilledBoolean(), //
					TRAIL_TYPE.Objects, TRAIL_OBJECT_TYPE.Stars);
		case "Trail of Hearts":
			return new TrailObjectPath(new PointF(x, y), radius, Settings.getFilledBoolean(), //
					TRAIL_TYPE.Objects, TRAIL_OBJECT_TYPE.Heart);

		case "Sine Trail Of Stars":
		case "Sine Trail of Stars":
			return new TrailObjectPath(new PointF(x, y), radius, Settings.getFilledBoolean(), //
					TRAIL_TYPE.Sinus, TRAIL_OBJECT_TYPE.Stars);

		case "Sine Trail of Hearts":
			return new TrailObjectPath(new PointF(x, y), radius, Settings.getFilledBoolean(), //
					TRAIL_TYPE.Sinus, TRAIL_OBJECT_TYPE.Heart);

		case "Trail Of Stars (getting Bigger)":
		case "Trail of Stars (getting Bigger)":
			return new TrailObjectPath(new PointF(x, y), radius, Settings.getFilledBoolean(), //
					TRAIL_TYPE.ObjectsGettingBigger, TRAIL_OBJECT_TYPE.Stars);
		}
	}

	private static Path drawQualle(final int x, final int y, final int radius, final String variant) {

		switch (variant) {
		default:
		case "V1":
		case "V1 (Half Circle)":
		case "Jellyfish":
			return new QuallePath(new PointF(x, y), radius, EQualleType.v1);
		case "V2":
		case "V2 (UFO)":
		case "Jellyfish 2":
			return new QuallePath(new PointF(x, y), radius, EQualleType.v2);
		case "V3":
		case "V3 (Oval)":
		case "Jellyfish 3":
			return new QuallePath(new PointF(x, y), radius, EQualleType.v3);
		}
	}
	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawQualleTopview(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 5);
			variant = "V" + nr;
		}
		TailOptionsLine tailOptionsLine = Settings.getTailOptionsLine();
		TailOptionsBubbles tailOptionsBubbles = Settings.getTailOptionsBubbles();
		EyeOptions eyeOptions = Settings.getEyeOptions();
		switch (variant) {
		default:
		case "V1":
		case "Topview":
		case "Fully customizable":
			return new QualleTopviewPath(new PointF(x, y), radius, tailOptionsLine, tailOptionsBubbles, eyeOptions);

		case "V2":
		case "Preset 1":
			tailOptionsLine = QualleTopviewPreset01.lineOptions;
			tailOptionsBubbles = QualleTopviewPreset01.bubbleOptions;
			eyeOptions = QualleTopviewPreset01.eyeOptions;
			return new QualleTopviewPath(new PointF(x, y), radius, tailOptionsLine, tailOptionsBubbles, eyeOptions);

		case "V3":
		case "Preset 2":
			tailOptionsLine = QualleTopviewPreset02.lineOptions;
			tailOptionsBubbles = QualleTopviewPreset02.bubbleOptions;
			eyeOptions = QualleTopviewPreset02.eyeOptions;
			return new QualleTopviewPath(new PointF(x, y), radius, tailOptionsLine, tailOptionsBubbles, eyeOptions);

		case "V4":
		case "Spiral Preset":
			tailOptionsLine = QualleTopviewPreset03Spiral.lineOptions;
			tailOptionsBubbles = QualleTopviewPreset03Spiral.bubbleOptions;
			eyeOptions = QualleTopviewPreset03Spiral.eyeOptions;
			return new QualleTopviewPath(new PointF(x, y), radius, tailOptionsLine, tailOptionsBubbles, eyeOptions);

		case "V5":
		case "Heart Preset":
			tailOptionsLine = QualleTopviewPreset04Heart.lineOptions;
			tailOptionsBubbles = QualleTopviewPreset04Heart.bubbleOptions;
			eyeOptions = QualleTopviewPreset04Heart.eyeOptions;
			return new QualleTopviewPath(new PointF(x, y), radius, tailOptionsLine, tailOptionsBubbles, eyeOptions);

		}

	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawPenguin(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 1);
			variant = "V" + nr;
		}
		switch (variant) {
		default:
		case "V1":
			return new PenguinPath(new PointF(x, y), radius);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	public static Path drawShell(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Shells Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 6);
			variant = "Shells V" + nr;
		}

		switch (variant) {
		default:
		case "Shells V1":
			return new ShellV1Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);

		case "Shells V2":
			return new ShellV2Path(15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius * 1.5f, ShellV2Path.VARIANTE_OUTER,
					Settings.getFilledBoolean());

		case "Shells V3":
			return new ShellV3Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);

		case "Shells V4":
			return new ShellV4Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);

		case "Shells V5":
			return new ShellV5Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);

		case "Shells V6":
			return new ShellV6Path(3, 15 + Settings.getAnzahlFlowerLeafs(0, 10), new Point(x, y), radius);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawMandala(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}
		switch (variant) {
		default:
		case "V1":
			return new MandalaV1Path(2 + 2 * Settings.getAnzahlFlowerLeafs(2, 8), new Point(x, y), radius);

		case "V2":
			return new MandalaV2Path(3, 16, new Point(x, y), radius);

		case "V3":
			return new MandalaV3Path(3, 2 + 2 * Settings.getAnzahlFlowerLeafs(4, 10), new Point(x, y), radius);

		case "V4":
			return new MandalaV4Path(3, new Point(x, y), radius);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawFisch(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}
		return new FishPath(new Point(x, y), radius, variant);
	}

	// // #########################################################################################
	// // ----------------
	// // #########################################################################################
	// private static Path drawText(final int x, final int y, final int radius, final int index) {
	// // Some Paint Inits
	// if (Settings.getFilledBoolean()) {
	// paint.setTypeface(Typeface.DEFAULT_BOLD);
	// } else {
	// paint.setTypeface(Typeface.DEFAULT);
	// }
	//
	// String variant = this.variant;
	// switch (variant) {
	// default:
	// case "Numbers":
	// final String number = String.format(Locale.GERMANY, "%04d", index);
	// drawCustomText(x, y, radius * 1, number);
	//
	// case "Custom Text":
	// final String text = Settings.getText();
	// drawCustomText(x, y, radius * 1, text);
	//
	// case "Letters":
	// final int letterindex = Randomizer.getRandomInt(0, letters.length() - 1);
	// final char c = letters.charAt(letterindex);
	// drawCustomText(x, y, radius * 2, "" + c);
	//
	// }
	//
	// }

	// private static Path drawCustomText(final int x, final int y, final int radius, final String text) {
	//
	// switch (Settings.getTextDrawStyle()) {
	// default:
	// case "Round":
	// drawTextCircle(x, y, radius, text);
	//
	// case "Normal":
	// drawTextStraight(x, y, radius, text);
	//
	// case "Angled":
	// drawTextAngled(x, y, radius, text);
	//
	// case "Random":
	// final int i = Randomizer.getRandomInt(1, 3);
	// switch (i) {
	// default:
	// case 1:
	// drawTextCircle(x, y, radius, text);
	//
	// case 2:
	// drawTextStraight(x, y, radius, text);
	//
	// case 3:
	// drawTextAngled(x, y, radius, text);
	//
	// }
	//
	// }
	// }

	// private static Path drawTextStraight(final int x, final int y, final int radius, final String text) {
	// paint.setTextSize(radius);
	// paint.setTextAlign(Align.LEFT);
	// final Path mArc = new Path();
	// final int x2 = bWidth;
	// mArc.moveTo(x - radius / 2, y + radius / 2);
	// mArc.lineTo(x2, y + radius / 2);
	//
	// bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
	// outlineDrawer.drawText(paint, radius, text, mArc);
	//
	// }

	// private static Path drawTextCircle(final int x, final int y, final int radius, final String text) {
	// paint.setTextSize(radius);
	// paint.setTextAlign(Align.CENTER);
	// final Path mArc = new Path();
	// final RectF oval = getRectForRadius(x, y, radius * 2);
	// mArc.addArc(oval, Randomizer.getRandomInt(1, 360), 355);
	//
	// bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
	// outlineDrawer.drawText(paint, radius, text, mArc);
	// }

	// private static Path drawTextAngled(final int x, final int y, final int radius, final String text) {
	// paint.setTextSize(radius);
	// paint.setTextAlign(Align.LEFT);
	// final Path mArc = new Path();
	// mArc.moveTo(x, y);
	// final int y2 = Randomizer.getRandomInt(-bHeight, bHeight);
	// final int x2 = Randomizer.getRandomInt(-bWidth, bWidth);
	// mArc.lineTo(x2, y2);
	//
	// bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
	// outlineDrawer.drawText(paint, radius, text, mArc);
	// }

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawMaterial(final int x, final int y, final int radius, final String variant) {

		switch (variant) {
		default:
		case "Stripe":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE);

		case "Stripe V2":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE_V2);

		case "Stripe V3":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.STIPE_V3);

		case "Arc 1":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC1);

		case "Arc 2":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC2);

		case "Arc 3":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ARC3);

		case "Skyline":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.SKYLINE);

		case "Pyramide Skyline":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.PYRAMIDE_SKYLINE);

		case "Edgy Bars":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.EDGY_BARS);

		case "Rotating Bars":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_BARS);

		case "Rotating Triangles":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_TRIANGLES);

		case "Half Stripe":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE);

		case "Half Stripe V2":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE_V2);

		case "Half Stripe V3":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.HALF_STIPE_V3);

		case "Rotating Arches (random arches)":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_ARCHES_RANDOM_SIZE);

		case "Rotating Arches (quarter arches)":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_QUARTER_ARCHES);

		case "Rotating Arches (half arches)":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_HALF_ARCHES);

		case "Rotating Arches (3-quarter arches)":
			return new MaterialPath(new Point(x, y), radius, Settings.getFilledBoolean(), bWidth, bHeight, MATERIAL_TYPE.ROTATING_THREE_QUARTER_ARCHES);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawRect(final int x, final int y, final int radius, final String variant) {
		switch (variant) {
		default:
		case "V1":
		case "Random Ratio":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.RANDOM);

		case "V2":
		case "Random Ratio (Rounded)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.RANDOM);

		case "V3":
		case "Random Ratio (Mixed)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.RANDOM);

		case "V4":
		case "4-3 Ratio":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.ASPECT_3_4);

		case "V5":
		case "4-3 Ratio (Rounded)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.ASPECT_3_4);

		case "V6":
		case "4-3 Ratio (Mixed)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.ASPECT_3_4);

		case "V7":
		case "1-2 Ratio":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.ASPECT_1_2);

		case "V8":
		case "1-2 Ratio (Rounded)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.ASPECT_1_2);

		case "V9":
		case "1-2 Ratio (Mixed)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.ASPECT_1_2);

		case "V10":
		case "Golden Ratio":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.NORMAL, RECT_ASPECT.ASPECT_GOLDEN_CUT);

		case "V11":
		case "Golden Ratio (Rounded)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.ROUNDED, RECT_ASPECT.ASPECT_GOLDEN_CUT);

		case "V12":
		case "Golden Ratio (Mixed)":
			return new RectanglePath(new PointF(x, y), radius, Settings.getFilledBoolean(), RECT_ROUNDED.MIXED, RECT_ASPECT.ASPECT_GOLDEN_CUT);

		case "V13":
		case "HalfCircle End (random hight)":
			return new RectanglePath(new PointF(x, y), radius, radius * Randomizer.getRandomFloat(0.05f, 0.25f), Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawSpooky(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 14);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Bats")) {
			final int nr = Randomizer.getRandomInt(2, 5);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Ghosts")) {
			final int nr = Randomizer.getRandomInt(6, 7);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Owls")) {
			final int nr = Randomizer.getRandomInt(8, 11);
			variant = "V" + nr;
		} else if (variant.equalsIgnoreCase("Mixed Pumpkins")) {
			variant = "Mixed Pumpkins";
		}

		switch (variant) {
		default:
		case "V1":
		case "Skull":
			return new SkullPath(new Point(x, y), radius);

		case "V2":
		case "Bat V1 (Aarons Cute Design)":
			return new BatPath(new Point(x, y), radius, "V1");

		case "V3":
		case "Bat V2":
			return new BatPath(new Point(x, y), radius, "V2");

		case "V4":
		case "Bat V3":
			return new BatPath(new Point(x, y), radius, "V3");

		case "V5":
		case "Bat V4":
			return new BatPath(new Point(x, y), radius, "V4");

		case "V6":
		case "Ghost V1":
			return new GhostPath(new Point(x, y), radius, "V1");

		case "V7":
		case "Ghost V2":
			return new GhostPath(new Point(x, y), radius, "V2");

		case "V8":
		case "Owl V1":
			return new OwlPath(new Point(x, y), radius, "V1");

		case "V9":
		case "Owl V2":
			return new OwlPath(new Point(x, y), radius, "V2");

		case "V10":
		case "Owl V3":
			return new OwlPath(new Point(x, y), radius, "V3");

		case "V11":
		case "Owl V4":
			return new OwlPath(new Point(x, y), radius, "V4");

		case "V12":
		case "Pumpkin (round eyes)":
			return new PumpkinPath(new PointF(x, y), radius, PUMPKIN_TYP.MUNDZACKIG_AUGERUND);

		case "V13":
		case "Pumpkin (triangel eyes)":
			return new PumpkinPath(new PointF(x, y), radius, PUMPKIN_TYP.MUNDZACKIG_AUGEDREIECKIGV2);

		case "V14":
		case "Pumpkin (round eyes and mouth)":
			return new PumpkinPath(new PointF(x, y), radius, PUMPKIN_TYP.MUNDRUND_AUGERUND);

		case "Mixed Pumpkins":
			return new PumpkinPath(new PointF(x, y), radius, PUMPKIN_TYP.RANDOM);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawPuzzle(final int x, final int y, final int radius, final String variant, final PUZZLE_CONNECTION puzzleConnection) {
		switch (variant) {
		default:
		case "Mixed":
			return new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.RANDOM, puzzleConnection, Settings.getFilledBoolean());

		case "Manneken":
			return new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.MANNEKEN, puzzleConnection, Settings.getFilledBoolean());

		case "Top-Right":
			return new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.OBEN_RECHTS, puzzleConnection, Settings.getFilledBoolean());

		case "Cross":
			return new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.KREUZ, puzzleConnection, Settings.getFilledBoolean());

		case "All":
			return new PuzzlePath(new Point(x, y), radius, PUZZLE_TYPE.ALL, puzzleConnection, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawAssorted(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 18);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Lemon":
			return new ZitronePath(new Point(x, y), radius);

		case "V2":
		case "YingYang":
			return new YingYangPath(new Point(x, y), radius);

		case "V3":
		case "Crop Circles":
			return new DotSpiralPath(3, new Point(x, y), radius, 0);

		case "V4":
		case "Roses":
			return new RosePath(new Point(x, y), radius);

		case "V5":
		case "Pillows":
			return new PillowPath(4, new PointF(x, y), radius);

		case "V6":
		case "Android":
			return new AndroidPath(new Point(x, y), radius, ROBOT_STYLE.ANDROID);

		case "V7":
		case "Footprint":
			return new FootprintPath(new Point(x, y), radius, "V1");

		case "V8":
		case "Pentagram":
			return new PentagramPath(new PointF(x, y), radius);

		case "V9":
		case "4Sails":
			return new FourSailsPath(new Point(x, y), radius, "V1");

		case "V10":
		case "Dice":
			return new DicePath(new PointF(x, y), radius * 0.8f);

		case "V11":
		case "Drop":
			return new DropPath(new PointF(x, y), radius);

		case "V12":
		case "Ikae Robot":
			return new AndroidPath(new Point(x, y), radius, ROBOT_STYLE.IKEA);

		case "V13":
		case "Hedgehog":
			return new IgelPath(new Point(x, y + radius / 2), radius * 1.3f);

		case "V14":
		case "Dandelion":
			return new DandelionPath(new Point(x, y), radius);

		case "V15":
		case "Deathstar":
			return new StarwarsPath(new Point(x, y), radius, STARWARS_TYPE.DEATHSTAR);

		case "V16":
		case "R2D2":
			return new StarwarsPath(new Point(x, y), radius, STARWARS_TYPE.R2D2);

		case "V17":
		case "Iron Cross":
			return new IronCrossPath(new PointF(x, y), radius, Settings.getFilledBoolean(), IRONCROSS_TYPE.SPITZ);

		case "V18":
		case "Iron Cross (round)":
			return new IronCrossPath(new PointF(x, y), radius, Settings.getFilledBoolean(), IRONCROSS_TYPE.RUND);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawSonic(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}
		int leafs = Settings.getAnzahlFlowerLeafs(1, 10);
		if (leafs % 2 == 0) {
			// wir haben ne gerade zahl... und m�ssen ungerade werden... eins drauf
			// addieren
			leafs++;
		}
		final int rounds = 2 + leafs;

		switch (variant) {
		default:
		case "V1":
		case "Normal":
			return new SonicPath(new PointF(x, y), radius, SONICTYPE.NORMAL, rounds, Settings.getFilledBoolean());

		case "V2":
		case "Double":
			return new SonicPath(new PointF(x, y), radius, SONICTYPE.DOUBLE, rounds, Settings.getFilledBoolean());

		case "V3":
		case "Special":
			return new SonicPath(new PointF(x, y), radius, SONICTYPE.SPECIAL, rounds, Settings.getFilledBoolean());

		case "V4":
		case "One Arm":
			return new SonicPath(new PointF(x, y), radius, SONICTYPE.NORMAL, 3, Settings.getFilledBoolean());

		}
	}

	private static Path drawSquare(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Square (round inner corner)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.INNER_ROUND);

		case "V2":
		case "Square (square inner corner)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.INNER_RECT);

		case "V3":
		case "Square (line corner)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.DIAGONAL_LINE);

		case "V4":
		case "Square (round inner corner V2)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.INNER_ROUND2);

		case "V5":
		case "Square (circle corner)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.CIRCLE);

		case "V6":
		case "Square (outer circle corner)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.OUTER_CIRCLE);

		case "V7":
		case "Square (rounded)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.ROUNDED);

		case "V8":
		case "Square (castel)":
			return new SquareCornered(new PointF(x, y), radius, Settings.getFilledBoolean(), CORNERED_STYLE.CASTEL);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawPillow(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 13);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "3 Edge Pillow":
			return new PillowPath(3, new PointF(x, y), radius);

		case "V2":
		case "4 Edge Pillow":
			return new PillowPath(4, new PointF(x, y), radius);

		case "V3":
		case "5 Edge Pillow":
			return new PillowPath(5, new PointF(x, y), radius);

		case "V4":
		case "6 Edge Pillow":
			return new PillowPath(6, new PointF(x, y), radius);

		case "V5":
		case "Plectrum":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.PLEKTRUM);

		case "V6":
		case "Fingernail":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.FINGERNAIL);

		case "V7":
		case "Treky":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.TREKY);

		case "V8":
		case "YingYang":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.YINGYANG);

		case "V9":
		case "Peeek":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.PEEEK);

		case "V10":
		case "Armor":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.ARMOR);

		case "V11":
		case "Messer":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.MESSER);

		case "V12":
		case "Blazon":
			return new PillowPath(new PointF(x, y), radius, PILLOW_TYPE.BLAZON);

		case "V13":
		case "Wurfstern":
			return new WurfSternPath(new PointF(x, y), radius);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawChess(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 7);
			variant = "V" + nr;
		}
		switch (variant) {
		default:
		case "V1":
		case "Square":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Square);

		case "V2":
		case "Star":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Star);

		case "V3":
		case "Circle":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Circle);

		case "V4":
		case "Pillow":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Pillow);

		case "V5":
		case "Heart":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Heart);

		case "V6":
		case "Arrow":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Arrow);

		case "V7":
		case "Triangle":
			return new SchachbrettPath(new PointF(x, y), radius, Randomizer.getRandomInt(1, 4), BRETT_SHAPE.Triangle);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawXmas(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 1);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Tree":
			return new XmasTreePath(new Point(x, y), radius, Settings.getFilledBoolean());

		}
	}

	// #########################################################################################
	// Bubble
	// #########################################################################################

	private static Path drawBubble(final int x, final int y, final int radius, final String variant) {
		// Bubble
		return new CirclePath(new PointF(x, y), radius, radius / 2, true, CIRCLE_STYLE.CIRCLE);
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawSmiley(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 2);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "V1":
		case "Happy":
			return new SmileyPath(new Point(x, y), radius, SMILEY_TYPE.HAPPY);

		case "V2":
		case "Sad":
			return new SmileyPath(new Point(x, y), radius, SMILEY_TYPE.SAD);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private static Path drawPacman(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 2);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "Ghost":
		case "V1":
			return new PacmanPath(new Point(x, y), radius, PACMAN_STYLE.GHOST);

		case "Pacman":
		case "V2":
			return new PacmanPath(new Point(x, y), radius, PACMAN_STYLE.PACMAN);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawHeart(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 6);
			variant = "V" + nr;
		}

		switch (variant) {
		default:
		case "Curvy":
		case "V1":
			return new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Curvy);

		case "Straigth":
		case "V2":
			return new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Straigth);

		case "Round":
		case "V3":
			return new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Round);

		case "Peek":
		case "V4":
			return new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Peek);

		case "Lovely":
		case "V5":
			return new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Lovely);

		case "Asymetric":
		case "V6":
			return new HeartPath(new PointF(x, y), radius, false, HEART_SHAPE.Asymetric);

		}
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################

	private static Path drawStar(final int x, final int y, final int radius, String variant) {
		if (variant.equalsIgnoreCase("Mixed")) {
			final int nr = Randomizer.getRandomInt(1, 4);
			variant = "V" + nr;
		}
		final int arms = Settings.getAnzahlFlowerLeafs(5, 10);
		switch (variant) {
		default:
		case "V1":
		case "Normal":
			return new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.NORMAL, Settings.getFilledBoolean());
		case "V2":
		case "Spikey":
			return new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.SPIKEY, Settings.getFilledBoolean());
		case "V3":
		case "Star Circle":
			return new StarPath(arms, new PointF(x, y), radius, STAR_TYPE.STAR_CIRCLE, Settings.getFilledBoolean());
		}
	}
	// #########################################################################################
	// ----------------
	// #########################################################################################

}
