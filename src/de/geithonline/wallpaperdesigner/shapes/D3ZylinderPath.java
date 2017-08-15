package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class D3ZylinderPath extends D3Path {

	public D3ZylinderPath(final PointF center, final float radius, final float height) {
		draw(center, radius, height);
	}

	private void draw(final PointF center, final float radius, final float height) {
		// calculating points
		final PointF c = new PointF(center.x, center.y - height);
		final List<PointF> points = new ArrayList<>();
		for (int i = 30; i < 180; i = i + 60) {
			final float winkel = (float) (-i / 360f * 2f * Math.PI);
			final PointF p = new PointF();
			p.x = (float) (c.x + Math.cos(winkel) * radius);
			p.y = (float) (c.y + Math.sin(winkel) * radius);
			// Log.i("Point", "Point = " + p);
			points.add(p);
		}

		final RectF oval = new RectF();

		oval.top = points.get(1).y;
		oval.bottom = c.y;
		oval.left = points.get(2).x;
		oval.right = points.get(0).x;
		seite0.addOval(oval, Direction.CW);
		addPath(seite0);

		// cut rect
		final RectF cutter = new RectF(oval);
		cutter.bottom = oval.bottom + height;
		cutter.left = oval.right - radius / 2;

		final Path unten = new Path();
		final RectF r = new RectF(oval);
		r.top = points.get(0).y;
		r.bottom = points.get(0).y + height;

		final RectF ovalUnten = new RectF(oval);
		ovalUnten.top = oval.top + height;
		ovalUnten.bottom = oval.bottom + height;

		unten.addPath(new RectanglePath(r, 0, 0));
		unten.op(new OvalPath(ovalUnten, Direction.CW), Op.UNION);
		unten.op(new OvalPath(oval, Direction.CW), Op.DIFFERENCE);

		// Nothing
		seite1.addPath(unten);
		seite1.op(new RectanglePath(cutter, 0, 0), Op.DIFFERENCE);
		addPath(seite1);

		cutter.bottom = oval.bottom + height;
		cutter.left = oval.left;
		cutter.right = oval.right - radius / 2;
		seite2.addPath(unten);
		seite2.op(new RectanglePath(cutter, 0, 0), Op.DIFFERENCE);
		addPath(seite2);

	}
}
