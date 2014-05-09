package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class VirusPath extends Path {

	public VirusPath(final Point center, float radius) {
		super();
		radius = radius * 2;
		final int arms = Randomizer.getRandomInt(8, 12);
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius / 10;
		for (int i = 0; i <= arms; i++) {
			final float r2 = Randomizer.getRandomFloat(radius / 3, radius);
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * innerR);
			p1.y = (int) (center.y + Math.sin(i * angle) * innerR);
			p2.x = (int) (center.x + Math.cos((i + 1) * angle) * r2);
			p2.y = (int) (center.y + Math.sin((i + 1) * angle) * r2);
			if (i == 0) {
				moveTo(p2.x, p2.y);
			} else {
				// quadTo(p2.x, p2.y, p1.x, p1.y);
				quadTo(p1.x, p1.y, p2.x, p2.y);
			}
		}
		close();
	}
}
