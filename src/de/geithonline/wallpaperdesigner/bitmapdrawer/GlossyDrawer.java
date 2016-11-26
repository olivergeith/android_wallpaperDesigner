package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_REFLECTIONS_STYLE;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class GlossyDrawer {
	private final Canvas bitmapCanvas;

	public GlossyDrawer(final Canvas bitmapCanvas) {
		this.bitmapCanvas = bitmapCanvas;
	}

	public void draw(final int x, final int y, final Paint paint, final int radius, final Path path) {
		draw(x, y, paint, radius, path, Settings.getGlossyReflectionStyle());
	}

	public void draw(final int x, final int y, final Paint paint, final int radius, final Path path, final GLOSSY_REFLECTIONS_STYLE reflectionStyle) {
		if (!Settings.isGlossy()) {
			return;
		}

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

	private void setupPaintShaderForTopGlow(final int x, final int y, final Paint paint, final int radius) {
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

	private void setupPaintShaderForBottomGlow(final int x, final int y, final Paint paint, final int radius) {
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

}
