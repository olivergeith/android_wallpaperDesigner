package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import de.geithonline.android.basics.utils.BitmapHelper;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class BitmapDrawerLogoV2 extends BitmapDrawer {

	private int fontSize = 150;
	private int fontSizeArc = 20;
	private Canvas bitmapCanvas;
	private Bitmap bgBitmap = null;
	private String logoname = null;
	private int myWidth = -99;
	private int myHeight = -99;
	private boolean customLogo;

	public BitmapDrawerLogoV2() {
	}

	@Override
	public boolean supportsPointerColor() {
		return true;
	}

	@Override
	public boolean supportsShowPointer() {
		return true;
	}

	@Override
	public boolean supportsLogo() {
		return true;
	}

	@Override
	public boolean supportsFlip() {
		return true;
	}

	@Override
	public Bitmap drawBitmap(final int level) {
		// welche kante ist schmaler?
		// wir orientieren uns an der schmalsten kante
		// das heist, die Batterie ist immer gleich gross
		if (cWidth < cHeight) {
			// hochkant
			setBitmapSize(cWidth, cWidth, true);
		} else {
			// quer
			setBitmapSize(cHeight, cHeight, false);
		}
		Bitmap bitmap = Bitmap.createBitmap(bWidth, bHeight, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas(bitmap);

		fontSize = Math.round(bWidth * 0.35f);
		fontSizeArc = Math.round(bWidth * 0.05f);
		drawSegmente(level);

		// Zuschnitt auf Maske
		if (Settings.isMaskLogo()) {
			final Bitmap maskBitmap = Settings.getLogoMaskCached(bWidth, bHeight);
			bitmap = BitmapHelper.getMaskedBitmap(bitmap, maskBitmap);
		}
		// zeiger
		drawZeiger(level);

		return bitmap;
	}

	private void drawSegmente(final int level) {
		if (logoname == null || !logoname.equals(Settings.getCustomLogoFilePath()) || myWidth != bWidth || myHeight != bHeight
				|| customLogo != Settings.useCustomLogo()) {
			customLogo = Settings.useCustomLogo();
			logoname = Settings.getCustomLogoFilePath();
			myWidth = bWidth;
			myHeight = bHeight;
			bgBitmap = Settings.getCustomLogoSampled(bWidth, bHeight);
		}

		// paint grayscaled version of it
		bitmapCanvas.drawBitmap(bgBitmap, 0, 0, getGrayscalePaint());
		// overpaint it with colored version
		final Paint battPaint = getBitmapPaint(level, bgBitmap);
		if (Settings.isFlip()) {
			bitmapCanvas.drawRect(new RectF(0, 0, bWidth * level / 100, bHeight), battPaint);
		} else {
			bitmapCanvas.drawRect(new RectF(0, bHeight - bHeight * level / 100, bWidth, bHeight), battPaint);
		}
	}

	private void drawZeiger(final int level) {
		// Zeiger
		if (Settings.isShowZeiger()) {
			final Paint zp = getZeigerPaint(level);

			final float zeigerdicke = 0.005f;
			if (Settings.isFlip()) {
				final float levelX = bWidth * level / 100;
				bitmapCanvas.drawRect(new RectF(levelX - bWidth * zeigerdicke, 0, levelX + bWidth * zeigerdicke, bHeight), zp);
			} else {
				final float levelY = bHeight - bHeight * level / 100;
				bitmapCanvas.drawRect(new RectF(0, levelY - bHeight * zeigerdicke, bWidth, levelY + bHeight * zeigerdicke), zp);
			}
		}
	}

	@Override
	public void drawChargeStatusText(final int level) {
		drawChargeStatusText(bitmapCanvas, level, fontSizeArc);
	}

	@Override
	public void drawLevelNumber(final int level) {
		drawLevelNumberCentered(bitmapCanvas, level, fontSize, true);
	}

	@Override
	public void drawBattStatusText() {
		drawBattStatusTextBottomRound(bitmapCanvas, fontSizeArc, true);
	}

}
