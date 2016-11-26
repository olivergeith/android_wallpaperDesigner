package de.geithonline.wallpaperdesigner.bitmapdrawer.backgrounddrawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ColorHelper;

public class BackgroundDrawer4ColorBackground {

	public static void draw4ColorCornerGradientBackground(final Canvas canvas, final int levels) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final int c1 = Settings.getPatternColor1();
		final int c2 = Settings.getPatternColor2();
		final int c3 = Settings.getPatternColor3();
		final int c4 = Settings.getPatternColor4();
		final float sqW = (float) cWidth / (float) levels;
		final float sqH = (float) cHeight / (float) levels;
		for (int x = 0; x < levels; x++) {
			final int colXOben = ColorHelper.getRadiantColor(c1, c2, x, 0, levels - 1);
			final int colXUnten = ColorHelper.getRadiantColor(c4, c3, x, 0, levels - 1);
			for (int y = 0; y < levels; y++) {
				final int col = ColorHelper.getRadiantColor(colXOben, colXUnten, y, 0, levels - 1);
				final Paint paint = new Paint();
				paint.setAntiAlias(true);
				paint.setColor(col);
				final Rect r = new Rect(); // x * sqW, y * sqH, x * sqW + sqW, y
											// * sqH + sqH);
				r.left = Math.round(x * sqW);
				r.top = Math.round(y * sqH);
				r.right = Math.round((x + 1) * sqW);
				r.bottom = Math.round((y + 1) * sqH);
				canvas.drawRect(r, paint);
			}
		}
	}

	public static void draw4ColorBackground(final Canvas canvas, final int repeats) {
		final int cWidth = canvas.getWidth();
		final int cHeight = canvas.getHeight();
		final int c1 = Settings.getPatternColor1();
		final int c2 = Settings.getPatternColor2();
		final int c3 = Settings.getPatternColor3();
		final int c4 = Settings.getPatternColor4();
		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		final int blockW = cWidth / (2 * repeats);
		final int blockH = cHeight / (2 * repeats);

		for (int y = 0; y < 2 * repeats; y += 2) {
			for (int x = 0; x < 2 * repeats; x += 2) {
				paint.setColor(c1);
				Rect r = new Rect((x + 0) * blockW, (y + 0) * blockH, (x + 1) * blockW, (y + 1) * blockH);
				canvas.drawRect(r, paint);

				paint.setColor(c2);
				r = new Rect((x + 1) * blockW, (y + 0) * blockH, (x + 2) * blockW, (y + 1) * blockH);
				canvas.drawRect(r, paint);

				paint.setColor(c3);
				r = new Rect((x + 0) * blockW, (y + 1) * blockH, (x + 1) * blockW, (y + 2) * blockH);
				canvas.drawRect(r, paint);

				paint.setColor(c4);
				r = new Rect((x + 1) * blockW, (y + 1) * blockH, (x + 2) * blockW, (y + 2) * blockH);
				canvas.drawRect(r, paint);
			}
		}
	}

}
