
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import java.util.Locale;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.bitmapdrawer.PaintManager;
import de.geithonline.wallpaperdesigner.settings.EyeOptions;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_REFLECTIONS_STYLE;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.TrailHeartPath.HEART_TRAIL_TYPE;
import de.geithonline.wallpaperdesigner.shapes.TrailStarPath.TRAIL_TYPE;
import de.geithonline.wallpaperdesigner.shapes.composed.PenguinPath;
import de.geithonline.wallpaperdesigner.shapes.composed.QualleTopviewPath;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class NewPatternDrawer {

	private final Canvas bitmapCanvas;
	private final GlossyDrawer glossyDrawer;
	private final OutlineDrawer outlineDrawer;
	private final SceneDrawer sceneDrawer;
	private final Rotator rotator;
	private final int bWidth;
	private final int bHeight;

	private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final PaintManager pm;
	private final Paint paint;

	private final PointF prevPoint = new PointF(0, 0);

	public NewPatternDrawer(final Canvas bitmapCanvas, final PaintManager pm) {
		this.bitmapCanvas = bitmapCanvas;
		this.pm = pm;
		paint = pm.paint;
		bWidth = bitmapCanvas.getWidth();
		bHeight = bitmapCanvas.getHeight();
		glossyDrawer = new GlossyDrawer(bitmapCanvas);
		outlineDrawer = new OutlineDrawer(bitmapCanvas);
		rotator = new Rotator(bWidth, bHeight);
		sceneDrawer = new SceneDrawer(bitmapCanvas, glossyDrawer, outlineDrawer, rotator);
	}

	public void drawPattern(final int x, final int y, final int radius, final int index) {
		final String pattern = Settings.getSelectedPattern();
		final String variant = Settings.getSelectedPatternVariant();
		drawPattern(x, y, radius, index, pattern, variant);
	}

	public void drawPattern(final int x, final int y, final int radius, final int index, final String pattern, final String variant) {
		switch (pattern) {

		default:
			drawNormalPattern(x, y, radius, pattern, variant);
			break;

		case "Lines":
		case "Lines (Directed)":
			drawLinePattern(index, y, radius, pattern, variant);
			break;

		case "Bubbles":
			drawBubble(x, y, radius);
			break;

		case "Penguin":
			drawPenguin(x, y, radius, pattern, variant);
			break;

		case "Jellyfish Topview":
			drawQualleTopview(x, y, radius, pattern, variant);
			break;
		case "Text":
			drawText(x, y, radius * 2, index);
			break;
		case "Material":
			drawMaterial(x, y, radius, pattern, variant);
			break;
		case "Scenes":
			drawScene(x, y, radius, index);
			break;
		}
		prevPoint.x = x;
		prevPoint.y = y;
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private void drawNormalPattern(final int x, final int y, final int radius, final String pattern, final String variant) {
		final Path path = PathGetter.getPath(x, y, radius, bWidth, bHeight, pattern, variant);
		PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
		if (Settings.isRandomRotate() && Randomizer.getRandomBoolean()) {
			PathHelper.mirrorPathLeftRight(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		if (PatternPropertyStore.hasPatternGlossyEffect(pattern)) {
			glossyDrawer.draw(x, y, paint, radius, path);
		}
		outlineDrawer.draw(paint, radius, path);
	}

	private void drawNormalPatternRandomFlipping(final int x, final int y, final int radius, final String pattern, final String variant) {
		final Path path = PathGetter.getPath(x, y, radius, bWidth, bHeight, pattern, variant);
		PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
		if (Settings.isRandomRotate() && Randomizer.getRandomBoolean()) {
			PathHelper.mirrorPathLeftRight(x, y, path);
		}
		bitmapCanvas.drawPath(path, paint);
		if (PatternPropertyStore.hasPatternGlossyEffect(pattern)) {
			glossyDrawer.draw(x, y, paint, radius, path);
		}
		outlineDrawer.draw(paint, radius, path);
	}

	private void drawLinePattern(final int x, final int y, final int radius, final String pattern, final String variant) {
		final Path path = PathGetter.getPath(x, y, radius, bWidth, bHeight, pattern, variant);
		PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
		OutlineDrawer.setupPaintForOutline(paint, radius);
		bitmapCanvas.drawPath(path, paint);
	}

	private void drawBubble(final int x, final int y, final int radius) {
		// Bubble
		final Path path = new CirclePath(new PointF(x, y), radius, radius / 2, true, CIRCLE_STYLE.CIRCLE);
		bitmapCanvas.drawCircle(x, y, radius, paint);
		glossyDrawer.draw(x, y, paint, radius, path, GLOSSY_REFLECTIONS_STYLE.BIG_OVAL);
		outlineDrawer.draw(paint, radius, path);
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private void drawScene(final int x, final int y, final int radius, final int index) {
		final String variant = Settings.getSelectedPatternVariant();
		drawScene(x, y, radius, variant, index);
	}

	public void drawScene(final int x, final int y, final int radius, final String variant, final int index) {
		switch (variant) {
		default:
		case "Rain":
			if (Randomizer.getRandomBooleanInPercentOfCases(Settings.getScenePercentageOfCircles())) {
				drawBubble(x, y, radius / 3);
			} else {
				// Rain
				drawLinePattern(x, y, radius, "Lines (Directed)", "Straight Line");
			}
			break;
		case "Trail Of Stars":
		case "Trail of Stars":
			sceneDrawer.drawStarWithTrail(x, y, paint, radius, Settings.getFilledBoolean(), TRAIL_TYPE.Stars);
			break;
		case "Trail of Hearts":
			sceneDrawer.drawHeartWithTrail(x, y, paint, radius, Settings.getFilledBoolean(), HEART_TRAIL_TYPE.Hearts);
			break;
		case "Sine Trail Of Stars":
		case "Sine Trail of Stars":
			sceneDrawer.drawStarWithTrail(x, y, paint, radius, Settings.getFilledBoolean(), TRAIL_TYPE.Sinus);
			break;
		case "Sine Trail of Hearts":
			sceneDrawer.drawHeartWithTrail(x, y, paint, radius, Settings.getFilledBoolean(), HEART_TRAIL_TYPE.Sinus);
			break;
		case "Trail Of Stars (getting Bigger)":
		case "Trail of Stars (getting Bigger)":
			sceneDrawer.drawStarWithTrail(x, y, paint, radius, Settings.getFilledBoolean(), TRAIL_TYPE.StarsGettingBigger);
			break;
		case "Experiemental":
			break;
		}
	}

	private void drawQualleTopview(final int x, final int y, final int radius, final String pattern, final String variant) {
		final float rotationDegrees = rotator.getRotationDegrees(0, 360, new Point(x, y));
		final QualleTopviewPath path = (QualleTopviewPath) PathGetter.getPath(x, y, radius, bWidth, bHeight, pattern, variant);
		final TailOptionsBubbles tailOptionsBubbles = path.bubbleOptions;
		final EyeOptions eyeOptions = path.eyeOptions;
		final TailOptionsLine tailOptionsLine = path.tailOptions;
		PathHelper.rotatePath(x, y, path.qualle, rotationDegrees);
		PathHelper.rotatePath(x, y, path.inner, rotationDegrees);
		PathHelper.rotateComposedPath(x, y, path.tail, rotationDegrees);
		PathHelper.rotateComposedPath(x, y, path.bubbletail, rotationDegrees);

		// qualle
		bitmapCanvas.drawPath(path.qualle, paint);
		outlineDrawer.draw(paint, radius, path.qualle);
		glossyDrawer.draw(x, y, paint, radius, path.qualle);

		// inner oval
		pm.initFillPaint();
		if (path.inner != null) {
			reRandomizeColor(eyeOptions.minBrightness, eyeOptions.maxBrightness, false);
			bitmapCanvas.drawPath(path.inner, paint);
		}
		// bubble tail
		if (path.bubbletail != null && path.bubbletail.getPathElements().size() > 0) {
			if (tailOptionsBubbles.colorful) {
				for (final Path p : path.bubbletail.getPathElements()) {
					reRandomizeColor(tailOptionsBubbles.minBrightness, tailOptionsBubbles.maxBrightness, true);
					bitmapCanvas.drawPath(p, paint);
				}
			} else {
				reRandomizeColor(tailOptionsBubbles.minBrightness, tailOptionsBubbles.maxBrightness, true);
				bitmapCanvas.drawPath(path.bubbletail, paint);
			}
		}
		// tail
		if (path.tail != null && path.tail.getPathElements().size() > 0) {
			if (tailOptionsLine.outline) {
				OutlineDrawer.setupPaintForStroke(paint, radius);
			}
			// OutlineDrawer.setupPaintForStroke(paint, radius);
			if (tailOptionsLine.colorful) {
				for (final Path p : path.tail.getPathElements()) {
					reRandomizeColor(tailOptionsLine.minBrightness, tailOptionsLine.maxBrightness, false);
					bitmapCanvas.drawPath(p, paint);
				}
			} else {
				// making tail even brighter
				reRandomizeColor(tailOptionsLine.minBrightness, tailOptionsLine.maxBrightness, false);
				bitmapCanvas.drawPath(path.tail, paint);
			}
		}
	}

	private void reRandomizeColor(final int minBrightness, final int maxBrightness, final boolean withDdropShadow) {
		pm.randomizeColorAccordingToSettings(pm.getInitialColor());
		if (withDdropShadow) {
			pm.setupDropShadowForPatternDark(pm.getCurrentColor());
		}
		pm.setColor(ColorHelper.adjustColorBrightness(pm.getCurrentColor(), //
				Randomizer.getRandomInt(minBrightness, maxBrightness)));
	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private void drawPenguin(final int x, final int y, final int radius, final String pattern, final String variant) {
		final float rotationDegrees = rotator.getRotationDegrees(0, 360, new Point(x, y));
		final PenguinPath path = (PenguinPath) PathGetter.getPath(x, y, radius, bWidth, bHeight, pattern, variant);

		PathHelper.rotatePath(x, y, path.body, rotationDegrees);
		PathHelper.rotatePath(x, y, path.bauch, rotationDegrees);
		PathHelper.rotatePath(x, y, path.augen, rotationDegrees);
		PathHelper.rotatePath(x, y, path.schnabel, rotationDegrees);
		// body
		bitmapCanvas.drawPath(path.body, paint);
		glossyDrawer.draw(x, y, paint, radius, path.body);
		outlineDrawer.draw(paint, radius, path.body);

		// Bauch
		pm.initFillPaint();
		pm.setColor(ColorHelper.adjustColorBrightness(pm.getInitialRandomizedColor(), 32));
		// pm.initPaintForPattern(ColorHelper.adjustColorBrightness(pm.getOldColor(), 0));
		bitmapCanvas.drawPath(path.bauch, paint);

		// Augen
		pm.initFillPaint();
		pm.setColor(ColorHelper.adjustColorBrightness(pm.getInitialRandomizedColor(), 60));
		bitmapCanvas.drawPath(path.augen, paint);
		outlineDrawer.draw(paint, radius, path.augen);
		// Schnabel
		pm.initFillPaint();
		pm.setColor(ColorHelper.adjustColorBrightness(pm.getInitialRandomizedColor(), -32));
		bitmapCanvas.drawPath(path.schnabel, paint);

	}

	// #########################################################################################
	// ----------------
	// #########################################################################################
	private void drawText(final int x, final int y, final int radius, final int index) {
		// Some Paint Inits
		if (Settings.getFilledBoolean()) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}

		final String variant = Settings.getSelectedPatternVariant();
		switch (variant) {
		default:
		case "Numbers":
			final String number = String.format(Locale.GERMANY, "%04d", index);
			drawCustomText(x, y, radius * 1, number);
			break;
		case "Custom Text":
			final String text = Settings.getText();
			drawCustomText(x, y, radius * 1, text);
			break;
		case "Letters":
			final int letterindex = Randomizer.getRandomInt(0, letters.length() - 1);
			final char c = letters.charAt(letterindex);
			drawCustomText(x, y, radius * 2, "" + c);
			break;
		}

	}

	private void drawCustomText(final int x, final int y, final int radius, final String text) {

		switch (Settings.getTextDrawStyle()) {
		default:
		case "Round":
			drawTextCircle(x, y, radius, text);
			break;
		case "Normal":
			drawTextStraight(x, y, radius, text);
			break;
		case "Angled":
			drawTextAngled(x, y, radius, text);
			break;
		case "Random":
			final int i = Randomizer.getRandomInt(1, 3);
			switch (i) {
			default:
			case 1:
				drawTextCircle(x, y, radius, text);
				break;
			case 2:
				drawTextStraight(x, y, radius, text);
				break;
			case 3:
				drawTextAngled(x, y, radius, text);
				break;
			}
			break;
		}
	}

	private void drawTextStraight(final int x, final int y, final int radius, final String text) {
		paint.setTextSize(radius);
		paint.setTextAlign(Align.LEFT);
		final Path mArc = new Path();
		final int x2 = bWidth;
		mArc.moveTo(x - radius / 2, y + radius / 2);
		mArc.lineTo(x2, y + radius / 2);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		outlineDrawer.drawText(paint, radius, text, mArc);

	}

	private void drawTextCircle(final int x, final int y, final int radius, final String text) {
		paint.setTextSize(radius);
		paint.setTextAlign(Align.CENTER);
		final Path mArc = new Path();
		final RectF oval = getRectForRadius(x, y, radius * 2);
		mArc.addArc(oval, Randomizer.getRandomInt(1, 360), 355);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		outlineDrawer.drawText(paint, radius, text, mArc);
	}

	private void drawTextAngled(final int x, final int y, final int radius, final String text) {
		paint.setTextSize(radius);
		paint.setTextAlign(Align.LEFT);
		final Path mArc = new Path();
		mArc.moveTo(x, y);
		final int y2 = Randomizer.getRandomInt(-bHeight, bHeight);
		final int x2 = Randomizer.getRandomInt(-bWidth, bWidth);
		mArc.lineTo(x2, y2);

		bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		outlineDrawer.drawText(paint, radius, text, mArc);
	}

	private RectF getRectForRadius(final int x, final int y, final int radius) {
		return new RectF(x - radius, y - radius, x + radius, y + radius);
	}

	private void drawMaterial(final int x, final int y, final int radius, final String pattern, final String variant) {
		final Path path = PathGetter.getPath(x, y, radius, bWidth, bHeight, pattern, variant);
		bitmapCanvas.drawPath(path, paint);
		outlineDrawer.draw(paint, radius, path);
	}

}