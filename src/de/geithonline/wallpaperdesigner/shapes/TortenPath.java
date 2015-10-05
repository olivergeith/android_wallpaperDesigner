package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class TortenPath extends Path {
	public TortenPath(final PointF center, final float radius, final int startingAngle, final float sweep) {
		final Path p = new Path();
		p.moveTo(center.x, center.y);
		p.lineTo(center.x + radius, center.y);
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - radius;
		oval.bottom = center.y + radius;
		p.arcTo(oval, 0, sweep);
		p.close();
		PathHelper.rotatePath(center.x, center.y, p, startingAngle);
		addPath(p);
	}
}
