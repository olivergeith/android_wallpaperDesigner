package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class TriangleAsymetricPath extends Path {

	public TriangleAsymetricPath(final PointF center, final float radius, final boolean filled) {
		addPath(drawTriangle(center, radius, Direction.CW));
		if (!filled) {
			addPath(drawTriangle(new PointF(center.x, center.y - radius * 2), radius / 2, Direction.CCW));
		}

	}

	private Path drawTriangle(final PointF center, final float radius, final Direction dir) {
		final Path p = new Path();
		if (dir.equals(Direction.CW)) {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x - radius, center.y - 6 * radius);
			p.lineTo(center.x + radius, center.y - 6 * radius);
			p.close();
		} else {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x + radius, center.y - 6 * radius);
			p.lineTo(center.x - radius, center.y - 6 * radius);
			p.close();
		}
		return p;
	}
}
