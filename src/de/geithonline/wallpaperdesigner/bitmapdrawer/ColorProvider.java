package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class ColorProvider {

	public static float getRandomFloat(final float min, final float max) {
		return Randomizer.getRandomFloat(min, max);
	}

	public static int getRandomInt(final int min, final int max) {
		return Randomizer.getRandomInt(min, max);
	}

	public static boolean getRandomBoolean() {
		return Randomizer.getRandomBoolean();
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
