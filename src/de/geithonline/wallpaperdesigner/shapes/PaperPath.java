package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class PaperPath extends Path {

	public PaperPath(final Point center, final int width, final int height, final int maxsteps, final int maxsteplength, final boolean rectangular) {
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
			if (p.x > width) {
				p.x = width - (p.x - width);
			}
			if (rectangular) {
				lineTo(p.x, p.y);
			}

			p.y = p.y + stepY;
			if (p.y < 0) {
				p.y = -p.y;
			}
			if (p.y > height) {
				p.y = height - (p.y - height);
			}
			lineTo(p.x, p.y);
		}

	}

	private int calcDistance(final PointF p1, final PointF p2) {
		final float w = p2.x - p1.x;
		final float h = p2.y - p1.y;
		final float dist = (float) Math.sqrt(w * w + h * h);
		return Math.round(dist);
	}

}
