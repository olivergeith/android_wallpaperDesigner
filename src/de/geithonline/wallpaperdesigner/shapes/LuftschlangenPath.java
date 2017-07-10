package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class LuftschlangenPath extends Path {

	public LuftschlangenPath(final int circles, final Point center, final float rOuter, final boolean flip) {
		super();

		final int stepX = Randomizer.getRandomInt(-2, 2);
		final int stepY = Randomizer.getRandomInt(-2, 2);

		final int ecken = 200;
		final float angle;
		if (!flip) {
			angle = (float) (2 * Math.PI * circles / ecken);
		} else {
			angle = (float) (-2 * Math.PI * circles / ecken);
		}

		for (int i = 0; i <= ecken; i++) {
			final float r = rOuter * i / ecken;

			final Point p = new Point();
			p.x = (int) (center.x + (i * stepX) + Math.cos(i * angle) * r);
			p.y = (int) (center.y + (i * stepY) + Math.sin(i * angle) * r);
			// Log.i("Point", "Point " + i + " p=" + p.x + " " + p.y);
			if (i == 0) {
				moveTo(p.x, p.y);
				// addCircle(p.x, p.y, 10, Direction.CCW);
			} else {
				lineTo(p.x, p.y);
				// addCircle(p.x, p.y, 5, Direction.CCW);
			}
		}
		// close();
	}

}
