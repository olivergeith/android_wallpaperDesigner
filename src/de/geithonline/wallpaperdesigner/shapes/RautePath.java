package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class RautePath extends Path {

	public RautePath(final PointF center, final float radius, final int height, final boolean filled) {
		addPath(drawRaute(center, radius, height, Direction.CW));
		if (!filled) {
			addPath(drawRaute(new PointF(center.x, center.y - radius * 2), radius / 2, height / 2, Direction.CCW));
		}

	}

	private Path drawRaute(final PointF center, final float radius, final int height, final Direction dir) {
		final Path p = new Path();
		if (dir.equals(Direction.CW)) {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x - radius, center.y - height / 2);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x + radius, center.y - height / 2);
			p.close();
		} else {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x + radius, center.y - height / 2);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x - radius, center.y - height / 2);
			p.close();
		}
		return p;
	}
}
