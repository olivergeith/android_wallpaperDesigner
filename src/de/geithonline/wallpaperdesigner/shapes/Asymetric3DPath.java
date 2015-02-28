package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class Asymetric3DPath extends Path {

	public enum ASYMETRIC_3D_STYLE {
		PYRAMIDE, CUBE, CONE;
	}

	public Asymetric3DPath(final PointF center, final float radius, final int height, final ASYMETRIC_3D_STYLE style) {
		switch (style) {
		default:
		case PYRAMIDE:
			drawPyramide(center, radius, height);
			break;
		case CUBE:
			drawWuerfel(center, radius, height);
			break;
		case CONE:
			drawKegel(center, radius, height);
			break;
		}

	}

	private void drawPyramide(final PointF center, final float radius, final int height) {
		final float raster = radius / 100;
		moveTo(center.x - raster / 2, center.y);
		lineTo(center.x - radius, center.y - height + radius / 2);
		lineTo(center.x - raster / 2, center.y - height + radius);
		close();

		moveTo(center.x + raster / 2, center.y);
		lineTo(center.x + raster / 2, center.y - height + radius);
		lineTo(center.x + radius, center.y - height + radius / 2);
		close();

		moveTo(center.x, center.y - height + radius - raster);
		lineTo(center.x - radius, center.y - height + radius / 2 - raster);
		lineTo(center.x, center.y - height);
		lineTo(center.x + radius, center.y - height + radius / 2 - raster);
		close();
	}

	private void drawWuerfel(final PointF center, final float radius, final int height) {
		final float raster = radius / 100;
		moveTo(center.x - raster / 2, center.y);
		lineTo(center.x - radius, center.y - radius / 2);
		lineTo(center.x - radius, center.y - height + radius / 2);
		lineTo(center.x - raster / 2, center.y - height + radius);
		close();

		moveTo(center.x + raster / 2, center.y);
		lineTo(center.x + raster / 2, center.y - height + radius);
		lineTo(center.x + radius, center.y - height + radius / 2);
		lineTo(center.x + radius, center.y - radius / 2);
		close();

		moveTo(center.x, center.y - height + radius - raster);
		lineTo(center.x - radius, center.y - height + radius / 2 - raster);
		lineTo(center.x, center.y - height);
		lineTo(center.x + radius, center.y - height + radius / 2 - raster);
		close();
	}

	private void drawKegel(final PointF center, final float radius, final int height) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - height;
		oval.bottom = center.y - height + radius;
		moveTo(center.x, center.y);
		lineTo(center.x - radius, center.y - height + radius / 2);
		arcTo(oval, 180, -180);
		close();

		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - height;
		oval.bottom = center.y - height + radius;
		addOval(oval, Direction.CW);
	}

}
