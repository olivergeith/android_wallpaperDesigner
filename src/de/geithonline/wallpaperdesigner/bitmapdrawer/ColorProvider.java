package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;

public class ColorProvider {

	public static float getRandomFloat(final float min, final float max) {
		final float mRandom = (float) (min + Math.random() * (max - min));
		return mRandom;
	}

	public static int getRandomInt(final int min, final int max) {
		final int mRandom = min + (int) Math.ceil(Math.random() * (max - min));
		return mRandom;
	}

	public static boolean getRandomBoolean() {
		final int mRandom = (int) Math.round(Math.random() * 1);
		if (mRandom == 1) {
			return true;
		}
		return false;
	}

	protected int randomizeColor(final int color, final int range) {
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		final int offR = getRandomInt(-range, range);
		final int offG = getRandomInt(-range, range);
		final int offB = getRandomInt(-range, range);
		r = validateColor(r + offR);
		g = validateColor(g + offG);
		b = validateColor(b + offB);
		return Color.rgb(r, g, b);
	}

	protected int validateColor(int col) {
		if (col < 0) {
			col = 0;
		}
		if (col > 255) {
			col = 255;
		}
		return col;
	}

	public Paint getTextPaint(final int fontSize, final Align align, final boolean bold) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setAlpha(255);
		paint.setAntiAlias(true);
		paint.setTextSize(fontSize);
		paint.setFakeBoldText(true);
		if (bold) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(Typeface.DEFAULT);
		}
		paint.setTextAlign(align);
		return paint;
	}

}
