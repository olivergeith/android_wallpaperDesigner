package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;

public class LogoPathEX extends Path {

	public enum LOGO_STYLE_EX {
		V1
	}

	public LogoPathEX(final PointF center, final float radius, final LOGO_STYLE_EX variante) {
		super();
		switch (variante) {
			default:
			case V1:
				drawLogoV1(center, radius);
				break;
		}

	}

	private void drawLogoV1(final PointF center, final float radius) {
		drawE(center, radius);
		addPath(new SquarePath(center, radius, true, SQUARE_STYLE.ROUNDED, Direction.CCW));
	}

	private void drawE(final PointF center, final float radius) {
		final float raster = radius / 11;
		final Path r1 = drawOneELine(new PointF(center.x - 6 * raster, center.y - 5 * raster), radius);
		final Path r2 = drawOneELine(new PointF(center.x - 8 * raster, center.y - 1 * raster), radius);
		final Path r3 = drawOneELine(new PointF(center.x - 10 * raster, center.y + 3 * raster), radius);
		addPath(r1);
		addPath(r2);
		addPath(r3);
		final Path x = drawX(center, radius);
		addPath(x);
	}

	private Path drawX(final PointF center, final float radius) {
		final float raster = radius / 11;
		final Path p = new Path();
		p.moveTo(center.x - 3 * raster, center.y + 5 * raster);
		p.lineTo(center.x + 2.6f * raster, center.y + 0 * raster);
		p.lineTo(center.x + 2 * raster, center.y - 5 * raster);
		p.lineTo(center.x + 4 * raster, center.y - 5 * raster);
		p.lineTo(center.x + 4.4f * raster, center.y - 1.5f * raster);
		p.lineTo(center.x + 8 * raster, center.y - 5 * raster);
		p.lineTo(center.x + 10 * raster, center.y - 5 * raster);
		p.lineTo(center.x + 4.6f * raster, center.y - 0 * raster);
		p.lineTo(center.x + 5 * raster, center.y + 5 * raster);
		p.lineTo(center.x + 3 * raster, center.y + 5 * raster);
		p.lineTo(center.x + 2.8f * raster, center.y + 1.8f * raster);
		p.lineTo(center.x - 1 * raster, center.y + 5 * raster);
		p.lineTo(center.x - 3 * raster, center.y + 5 * raster);
		p.close();
		return p;
	}

	private Path drawOneELine(final PointF center, final float radius) {
		final float raster = radius / 11;
		final Path p = new Path();
		p.moveTo(center.x + 1 * raster, center.y + 0 * raster);
		p.lineTo(center.x + 7 * raster, center.y + 0 * raster);
		p.lineTo(center.x + 6 * raster, center.y + 2 * raster);
		p.lineTo(center.x + 0 * raster, center.y + 2 * raster);
		p.close();
		return p;
	}

}
