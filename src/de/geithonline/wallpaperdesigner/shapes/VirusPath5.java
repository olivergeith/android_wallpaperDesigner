package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class VirusPath5 extends Path {

	public VirusPath5(final Point center, float radius) {
		super();
		radius = radius * 1.5f;
		final int arms = Randomizer.getRandomInt(8, 12);
		final float angle = (float) (2 * Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final float innerR = Randomizer.getRandomFloat(0, radius * 0.4f);
			final float r2 = radius;
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle + Math.PI) * innerR);
			p1.y = (int) (center.y + Math.sin(i * angle + Math.PI) * innerR);
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
