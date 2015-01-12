package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class VirusPath extends Path {

	public VirusPath(final Point center, float radius, final boolean filled) {
		super();
		radius = radius * 2;
		final List<Point> circles = new ArrayList<Point>();
		Point firstPoint = new Point();
		final int arms = Randomizer.getRandomInt(8, 12);
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius / 10;
		final float circleRadius = radius / 15;
		for (int i = 0; i <= arms; i++) {
			final float r2 = Randomizer.getRandomFloat(radius / 3, radius);
			final Point cp1 = new Point();
			Point p2 = new Point();
			final Point circleCenter = new Point();
			cp1.x = (int) (center.x + Math.cos(i * angle) * innerR);
			cp1.y = (int) (center.y + Math.sin(i * angle) * innerR);
			p2.x = (int) (center.x + Math.cos((i + 1) * angle) * r2);
			p2.y = (int) (center.y + Math.sin((i + 1) * angle) * r2);
			circleCenter.x = (int) (center.x + Math.cos((i + 1) * angle) * (r2 + circleRadius));
			circleCenter.y = (int) (center.y + Math.sin((i + 1) * angle) * (r2 + circleRadius));
			if (i < arms) {
				circles.add(circleCenter);
			}
			if (i == 0) {
				moveTo(p2.x, p2.y);
				firstPoint = p2;
			} else {
				if (i == arms) {
					p2 = firstPoint;
				}

				quadTo(cp1.x, cp1.y, p2.x, p2.y);
			}
		}
		close();
		if (!filled) {
			for (final Point p : circles) {
				addCircle(p.x, p.y, circleRadius, Direction.CW);
			}
		}

	}
}
