package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import de.geithonline.android.basics.utils.BitmapHelper;
import de.geithonline.wallpaperdesigner.settings.Settings;

public abstract class BitmapDrawer extends ColorProvider implements IBitmapDrawer {
	protected int cHeight = 0;
	protected int cWidth = 0;
	protected int bHeight = 0;
	protected int bWidth = 0;
	protected int level = -99;
	private Bitmap bmp;
	private boolean isDrawIcon = false;

	public abstract Bitmap drawBitmap(final int level);

	public abstract void drawLevelNumber(final int level);

	public abstract void drawChargeStatusText(final int level);

	public abstract void drawBattStatusText();

	public void drawOnCanvas(final Bitmap b, final Canvas canvas) {
		if (Settings.isCenteredBattery()) {
			canvas.drawBitmap(b, cWidth / 2 - bWidth / 2, cHeight / 2 - bHeight / 2, null);
		} else {
			canvas.drawBitmap(b, cWidth / 2 - bWidth / 2, cHeight - bHeight - Settings.getVerticalPositionOffset(isPortrait()), null);
		}
	}

	protected void setBitmapSize(final int w, final int h, final boolean isPortrait) {
		// kein resizen wenn ein icon gemalt wird!
		if (isDrawIcon) {
			bHeight = h;
			bWidth = w;
			return;
		}

		if (isPortrait) {
			// hochkant
			bHeight = Math.round(h * Settings.getPortraitResizeFactor());
			bWidth = Math.round(w * Settings.getPortraitResizeFactor());
		} else {
			// landscape mode
			bHeight = Math.round(h * Settings.getLandscapeResizeFactor());
			bWidth = Math.round(w * Settings.getLandscapeResizeFactor());
		}
	}

	@Override
	public void draw(final int level, final Canvas canvas, final boolean forcedraw) {
		final int h = canvas.getHeight();
		final int w = canvas.getWidth();
		// Bitmap neu berechnen wenn Level sich Ändert oder Canvas dimensions
		if (this.level != level || w != cWidth || h != cHeight || bmp == null || forcedraw) {
			cWidth = w;
			cHeight = h;
			// Memory frei geben für altes bitmap
			if (bmp != null) {
				bmp.recycle();
			}
			// Bitnmap neu berechnen
			bmp = drawBitmap(level);
			if (Settings.isShowNumber()) {
				drawLevelNumber(level);
			}
			if (Settings.isCharging && Settings.isShowChargeState()) {
				drawChargeStatusText(level);
			}
			if (Settings.isShowStatus()) {
				drawBattStatusText();
			}
		}
		// den aktuellen level merken
		this.level = level;
		if (Settings.isDebugging()) {
			BitmapHelper.saveBitmap2ExternalStorage(bmp, getClass().getSimpleName(), "level" + level);
		}
		drawOnCanvas(bmp, canvas);
	}

	@Override
	public Bitmap drawIcon(final int level, final int size) {
		final int h = size;
		final int w = size;
		// Bitmap neu berechnen wenn Level sich Ändert oder Canvas dimensions
		cWidth = w;
		cHeight = h;
		isDrawIcon = true;
		final Bitmap icon = drawBitmap(level);
		isDrawIcon = false;
		drawLevelNumber(level);
		return icon;
	}

	protected void drawLevelNumberCentered(final Canvas canvas, final int level, final int fontSize, final boolean dropShadow) {
		final String text = "" + level;
		final Paint paint = getNumberPaint(level, fontSize, Align.CENTER, true, false);
		if (dropShadow) {
			paint.setShadowLayer(10, 0, 0, Color.BLACK);
		}
		final PointF point = getTextCenterToDraw(new RectF(0, 0, bWidth, bHeight), paint);
		canvas.drawText(text, point.x, point.y, paint);
	}

	protected void drawBattStatusTextBottom(final Canvas canvas, final int fontSize, final boolean dropShadow) {
		final String text = Settings.getBattStatusCompleteShort();
		final Paint paint = getTextBattStatusPaint(fontSize, Align.CENTER, true, true);
		if (dropShadow) {
			paint.setShadowLayer(10, 0, 0, Color.BLACK);
		}
		canvas.drawText(text, bWidth / 2, bHeight - Math.round(bWidth * 0.01f), paint);
	}

	public void drawBattStatusTextBottomRound(final Canvas canvas, final int fontSize, final boolean dropShadow) {
		final Path mArc = new Path();
		final int radius = Math.round((bWidth / 2) * Settings.getBattStatusRadiusFactor());
		final RectF oval = getRectForRadius(radius);
		mArc.addArc(oval, 225, -270);
		final String text = Settings.getBattStatusCompleteShort();
		final Paint p = getTextBattStatusPaint(fontSize, Align.CENTER, true, true);
		canvas.drawTextOnPath(text, mArc, 0, 0, p);
	}

	protected void drawLevelNumberBottom(final Canvas canvas, final int level, final int fontSize) {
		final Paint p = getNumberPaint(level, fontSize, Align.CENTER, true, false);
		canvas.drawText("" + level, bWidth / 2, bHeight - Math.round(bWidth * 0.01f), p);
	}

	private static PointF getTextCenterToDraw(final RectF region, final Paint paint) {
		final Rect textBounds = new Rect();
		paint.getTextBounds("69", 0, 2, textBounds);
		final float x = region.centerX();
		final float y = region.centerY() + textBounds.height() * 0.5f;
		return new PointF(x, y);
	}

	protected void drawChargeStatusText(final Canvas canvas, final int level, final int fontSize) {
		final float startwinkel = 272f + Math.round(level * 3.6f);
		final int radius = Math.round((bWidth / 2 - fontSize) * Settings.getChargeStatusRadiusFactor());
		final Path mArc = new Path();
		final RectF oval = getRectForRadius(radius);
		mArc.addArc(oval, startwinkel, 355);
		final String text = Settings.getChargingText();
		canvas.drawTextOnPath(text, mArc, 0, 0, getChargeStatusPaint(level, fontSize, Align.LEFT, true, false, true));
	}

	protected RectF getRectForRadius(final int radius) {
		final int centerX = bWidth / 2;
		final int centerY = bHeight / 2;
		return new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
	}

	protected RectF getRectForOffset(final int offset) {
		return new RectF(offset, offset, bWidth - offset, bHeight - offset);
	}

	public int getcHeight() {
		return cHeight;
	}

	public void setcHeight(final int cHeight) {
		this.cHeight = cHeight;
	}

	public int getcWidth() {
		return cWidth;
	}

	public void setcWidth(final int cWidth) {
		this.cWidth = cWidth;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	protected boolean isPortrait() {
		return cHeight > cWidth;
	}

	protected boolean isLandscape() {
		return cHeight < cWidth;
	}

	@Override
	public boolean supportsShowPointer() {
		return false;
	}

	@Override
	public boolean supportsPointerColor() {
		return false;
	}

	@Override
	public boolean supportsShowRand() {
		return false;
	}

	@Override
	public boolean supportsLogo() {
		return false;
	}

	@Override
	public boolean supportsFlip() {
		return false;
	}

}
