package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class OutlineDrawer {
	private final Canvas bitmapCanvas;

	public OutlineDrawer(final Canvas bitmapCanvas) {
		this.bitmapCanvas = bitmapCanvas;
	}

	public void draw(final Paint paint, final int radius, final Path path) {
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawPath(path, paint);
		}
	}

	public void drawText(final Paint paint, final int radius, final String text, final Path mArc) {
		// Outline
		if (Settings.isOutline()) {
			setupPaintForOutline(paint, radius);
			bitmapCanvas.drawTextOnPath(text, mArc, 0, 0, paint);
		}
	}

	public static void setupPaintForOutline(final Paint paint, final int radius) {
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

}
