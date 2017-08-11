package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import de.geithonline.wallpaperdesigner.shapes.composed.ComposedPath;

public class Asymetric3DCubePath extends ComposedPath {

	public Path deckel = new Path();
	public Path seite1 = new Path();
	public Path seite2 = new Path();

	public Asymetric3DCubePath(final PointF center, final float radius, final float height) {
		drawWuerfel(center, radius, height);
	}

	private void drawWuerfel(final PointF center, final float radius, final float height) {
		// calculating points
		final PointF c = new PointF(center.x, center.y - height);
		final List<PointF> points = new ArrayList<>();
		for (int i = 30; i < 180; i = i + 60) {
			final float winkel = (float) (-i / 360f * 2f * Math.PI);
			final PointF p = new PointF();
			p.x = (float) (c.x + Math.cos(winkel) * radius);
			p.y = (float) (c.y + Math.sin(winkel) * radius);
			Log.i("Point", "Point = " + p);
			points.add(p);
		}

		deckel.moveTo(c.x, c.y);
		deckel.lineTo(points.get(0).x, points.get(0).y);
		deckel.lineTo(points.get(1).x, points.get(1).y);
		deckel.lineTo(points.get(2).x, points.get(2).y);
		deckel.close();
		addPath(deckel);

		seite1.moveTo(c.x, c.y);
		seite1.lineTo(points.get(0).x, points.get(0).y);
		seite1.lineTo(points.get(0).x, points.get(0).y + height);
		seite1.lineTo(c.x, c.y + height);
		seite1.close();
		addPath(seite1);

		seite2.moveTo(c.x, c.y);
		seite2.lineTo(points.get(2).x, points.get(2).y);
		seite2.lineTo(points.get(2).x, points.get(2).y + height);
		seite2.lineTo(c.x, c.y + height);
		seite2.close();
		addPath(seite2);

	}
}
