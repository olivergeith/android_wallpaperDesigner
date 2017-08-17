package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;

public class D3BrickPath extends D3Path {

	public D3BrickPath(final PointF center, final float radius, final float height) {
		draw(center, radius, height);
	}

	private void draw(final PointF center, final float radius, final float height) {
		// calculating points
		final PointF c = new PointF(center.x, center.y - height);
		final List<PointF> points = new ArrayList<>();
		for (int i = 30; i < 360; i = i + 60) {
			final float winkel = (float) (-i / 360f * 2f * Math.PI);
			final PointF p = new PointF();
			p.x = (float) (c.x + Math.cos(winkel) * radius);
			p.y = (float) (c.y + Math.sin(winkel) * radius);
			// Log.i("Point", "Point = " + p);
			points.add(p);
		}

		final PointF links = new PointF(c.x - 2 * (c.x - points.get(2).x), c.y);

		seite0.moveTo(points.get(3).x, points.get(3).y);
		seite0.lineTo(points.get(0).x, points.get(0).y);
		seite0.lineTo(points.get(1).x, points.get(1).y);
		seite0.lineTo(links.x, links.y);
		seite0.close();
		addPath(seite0);

		seite1.moveTo(points.get(3).x, points.get(3).y);
		seite1.lineTo(points.get(0).x, points.get(0).y);
		seite1.lineTo(points.get(0).x, points.get(0).y + height);
		seite1.lineTo(points.get(3).x, points.get(3).y + height);
		seite1.close();
		addPath(seite1);

		seite2.moveTo(points.get(3).x, points.get(3).y);
		seite2.lineTo(links.x, links.y);
		seite2.lineTo(links.x, links.y + height);
		seite2.lineTo(points.get(3).x, points.get(3).y + height);
		seite2.close();
		addPath(seite2);

	}
}
