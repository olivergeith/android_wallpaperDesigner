package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

public class SonicPath extends Path {

	public enum SONICTYPE {
		NORMAL, DOUBLE, SPECIAL;
	}

	public enum ARC_TYPE {
		UNTEN, OBEN;
	}

	public SonicPath(final PointF center, final float radius, final SONICTYPE type, final int rounds, final boolean filled) {
		switch (type) {
			default:
			case NORMAL:
				drawSonicNormal(center, radius, rounds);
				break;
			case DOUBLE:
				drawSonicDouble(center, radius, rounds);
				break;
			case SPECIAL:
				drawSonicSpecial(center, radius, rounds);
				break;
		}
	}

	private void drawSonicNormal(final PointF center, final float radius, final int rounds) {
		final float raster = radius / rounds;

		moveTo(center.x - 1 * raster, center.y);
		drawSonicUnten(center, rounds, raster);

		final Float rad = (1 + rounds) / 2 * raster;
		Log.i("Geith", "Rounds=" + rounds + " rad=" + rad);
		final PointF ovalCenter = new PointF();
		ovalCenter.x = center.x + ((1 + rounds) / 2 - 1) * raster;
		ovalCenter.y = center.y;
		final RectF oval = getOval(ovalCenter, rad);
		arc180to(oval, ARC_TYPE.OBEN, Direction.CCW);
		close();
	}

	private void drawSonicDouble(final PointF center, final float radius, final int rounds) {
		final float raster = radius / rounds;

		moveTo(center.x - 1 * raster, center.y);
		drawSonicUnten(center, rounds, raster);
		drawSonicOben(center, rounds, raster);
		close();
	}

	private void drawSonicSpecial(final PointF center, final float radius, final int rounds) {
		final float raster = radius / rounds;

		moveTo(center.x - 1 * raster, center.y);
		drawSonicUnten(center, rounds, raster);
		final PointF ovalCenter = new PointF();
		ovalCenter.x = center.x + ((1 + rounds) / 2 - 1) * raster;
		ovalCenter.y = center.y;

		drawSonicOben(ovalCenter, (rounds + 1) / 2, raster);
		close();
	}

	private void drawSonicUnten(final PointF center, final int rounds, final float raster) {
		final PointF ovalCenter = new PointF();
		for (int round = 0; round < rounds; round++) {
			final int mod = round % 2;
			final int r = 1 + round;
			Log.i("Geith", "Round=" + round + " Mod=" + mod + " r=" + r);
			ovalCenter.x = center.x - mod * raster;
			ovalCenter.y = center.y;
			final Float rad = r * raster;
			final RectF oval = getOval(ovalCenter, rad);
			if (mod == 0) {
				arc180to(oval, ARC_TYPE.UNTEN, Direction.CCW);
			} else {
				arc180to(oval, ARC_TYPE.UNTEN, Direction.CW);
			}
		}
	}

	private void drawSonicOben(final PointF center, final int rounds, final float raster) {
		final PointF ovalCenter = new PointF();

		for (int round = rounds - 1; round > -1; round--) {
			final int mod = round % 2;
			final int r = 1 + round;
			Log.i("Geith", "Round=" + round + " Mod=" + mod + " r=" + r);
			ovalCenter.x = center.x - mod * raster;
			ovalCenter.y = center.y;
			final Float rad = r * raster;
			final RectF oval = getOval(ovalCenter, rad);
			if (mod == 0) {
				arc180to(oval, ARC_TYPE.OBEN, Direction.CCW);
			} else {
				arc180to(oval, ARC_TYPE.OBEN, Direction.CW);
			}
		}
	}

	private void arc180to(final RectF oval, final ARC_TYPE arctype, final Direction dir) {
		switch (arctype) {
			case OBEN:
				if (dir.equals(Direction.CW)) {
					arcTo(oval, -180, 180);
				} else {
					arcTo(oval, 0, -180);
				}
				break;
			case UNTEN:
				if (dir.equals(Direction.CW)) {
					arcTo(oval, 0, 180);
				} else {
					arcTo(oval, -180, -180);
				}
				break;
		}
	}

	private static RectF getOval(final PointF center, final float radius) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - radius;
		oval.bottom = center.y + radius;
		return oval;
	}

}
