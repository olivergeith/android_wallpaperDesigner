package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class LogoPathPeace extends Path {

	public enum LOGO_STYLE_PEACE {
		V1
	}

	public LogoPathPeace(final PointF center, final float radius, final LOGO_STYLE_PEACE variante) {
		super();
		switch (variante) {
		default:
		case V1:
			drawPeaceV1(center, radius);
			break;
		}

	}

	private void drawPeaceV1(final PointF center, final float radius) {
		final float raster = radius / 8;

		addCircle(center.x, center.y, radius, Direction.CCW);
		final RectF oval = new RectF();
		oval.left = center.x - 6 * raster;
		oval.right = center.x + 6 * raster;
		oval.top = center.y - 6 * raster;
		oval.bottom = center.y + 6 * raster;

		// erste torte
		moveTo(center.x + 1 * raster, center.y - 1 * raster);
		arcTo(oval, -80, 115);
		lineTo(center.x + 1 * raster, center.y - 1 * raster);
		close();

		moveTo(center.x - 1 * raster, center.y - 1 * raster);
		arcTo(oval, -215, 115);
		lineTo(center.x - 1 * raster, center.y - 1 * raster);
		close();

		moveTo(center.x + 1 * raster, center.y + 1 * raster);
		arcTo(oval, 48, 32);
		lineTo(center.x + 1 * raster, center.y + 1 * raster);
		close();

		moveTo(center.x - 1 * raster, center.y + 1 * raster);
		arcTo(oval, 100, 32);
		lineTo(center.x - 1 * raster, center.y + 1 * raster);
		close();

	}

}
