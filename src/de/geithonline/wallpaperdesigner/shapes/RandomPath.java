package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class RandomPath extends Path {

	public RandomPath(final Point center, final int xmax, final int ymax, final int maxsteps, final int maxsteplength, final boolean rectangular) {
		super();

		final Point p = new Point(center);
		moveTo(p.x, p.y);

		for (int i = 0; i < maxsteps; i++) {
			final int stepX = Randomizer.getRandomInt(-maxsteplength, maxsteplength);
			final int stepY = Randomizer.getRandomInt(-maxsteplength, maxsteplength);
			p.x = p.x + stepX;
			if (p.x < 0) {
				p.x = -p.x;
			}
			if (p.x > xmax) {
				p.x = xmax - (p.x - xmax);
			}
			if (rectangular) {
				lineTo(p.x, p.y);
			}

			p.y = p.y + stepY;
			if (p.y < 0) {
				p.y = -p.y;
			}
			if (p.y > ymax) {
				p.y = ymax - (p.y - ymax);
			}
			lineTo(p.x, p.y);
		}

	}

}
