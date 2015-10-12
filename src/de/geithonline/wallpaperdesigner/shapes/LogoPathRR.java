package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class LogoPathRR extends Path {

	public enum LOGO_STYLE_RR {
		RR_V1, RR_V2, RR_V3, RR_V4, RR_V5
	}

	public LogoPathRR(final PointF center, final float radius, final LOGO_STYLE_RR variante) {
		super();
		switch (variante) {
			default:
			case RR_V1:
				drawResurrectionV1(center, radius);
				break;
			case RR_V2:
				drawResurrectionV2(center, radius);
				break;
			case RR_V3:
				drawResurrectionV3(center, radius);
				break;
			case RR_V4:
				drawResurrectionV4(center, radius);
				break;
			case RR_V5:
				drawResurrectionV5(center, radius);
				break;
		}

	}

	private void drawResurrectionV1(final PointF center, final float radius) {
		draw2Rs(center, radius);
		addCircle(center.x, center.y, radius, Direction.CCW);
		addCircle(center.x, center.y, radius * 0.95f, Direction.CW);
		addCircle(center.x, center.y, radius * 0.88f, Direction.CCW);
	}

	private void drawResurrectionV2(final PointF center, final float radius) {
		draw2Rs(center, radius);
		addCircle(center.x, center.y, radius, Direction.CCW);
		addCircle(center.x, center.y, radius * 0.95f, Direction.CW);
		addPath(new TortenPath(center, radius * 0.88f, 90, -180));
	}

	private void drawResurrectionV3(final PointF center, final float radius) {
		draw2Rs(center, radius);
		addCircle(center.x, center.y, radius, Direction.CCW);
		addCircle(center.x, center.y, radius * 0.95f, Direction.CW);
		addPath(new TortenPath(center, radius * 0.88f, 180, -90));
		addPath(new TortenPath(center, radius * 0.88f, 0, -90));
	}

	private void drawResurrectionV4(final PointF center, final float radius) {
		final float height = 6 * radius;
		draw2Rs(new PointF(center.x, center.y - height), radius);

		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - height - radius;
		oval.bottom = center.y - height + radius;

		moveTo(center.x, center.y);
		cubicTo(center.x, center.y - height + 1.0f * radius, // CP1
				center.x + radius, center.y - height + 1.0f * radius, // CP2
				center.x + radius, center.y - height);
		arcTo(oval, 0, -180);
		cubicTo(center.x - radius, center.y - height + 1.0f * radius, // CP2
				center.x, center.y - height + 1.0f * radius, // CP1
				center.x, center.y);
		close();
	}

	private void drawResurrectionV5(final PointF center, final float radius) {
		final Path p = new SquarePath(center, radius, true, SQUARE_STYLE.ROUNDED, Direction.CCW);
		PathHelper.rotatePath(center.x, center.y, p, 45);
		addPath(p);
		addCircle(center.x, center.y, radius * 0.95f, Direction.CW);
		addCircle(center.x, center.y, radius * 0.8f, Direction.CCW);
		draw2Rs(center, radius * 0.9f);
	}

	private void draw2Rs(final PointF center, final float radius) {
		final float raster = radius / 4;
		final PointF c1 = new PointF(center.x - 3.0f * raster, center.y - 2 * raster);
		final Path r1 = drawOneR(c1, radius * 0.8f);
		final PointF c2 = new PointF(center.x - 2.0f * raster, center.y - 0.9f * raster);
		final Path r2 = drawOneR(c2, radius * 0.7f);
		addPath(r1);
		addPath(r2);
	}

	private Path drawOneR(final PointF center, final float radius) {
		final float raster = radius / 4;
		final Path p = new Path();
		p.moveTo(center.x + 0 * raster, center.y + 4 * raster);
		p.lineTo(center.x + 2 * raster, center.y + 1 * raster);
		p.quadTo(center.x + 2.66f * raster, center.y + 0 * raster, // controllpoint
				center.x + 4 * raster, center.y + 0 * raster); // Zielpunkt
		p.lineTo(center.x + 7 * raster, center.y + 0 * raster);

		p.quadTo(center.x + 6 * raster, center.y + 1 * raster, // controllpoint
				center.x + 5 * raster, center.y + 1 * raster); // Zielpunkt
		p.lineTo(center.x + 4 * raster, center.y + 1 * raster);
		p.quadTo(center.x + 3.33f * raster, center.y + 1 * raster, // controllpoint
				center.x + 2.66f * raster, center.y + 2 * raster); // Zielpunkt
		p.lineTo(center.x + 2 * raster, center.y + 3 * raster);
		p.quadTo(center.x + 1.66f * raster, center.y + 4 * raster, // controllpoint
				center.x + 0 * raster, center.y + 4 * raster); // Zielpunkt
		p.close();
		return p;
	}

}
