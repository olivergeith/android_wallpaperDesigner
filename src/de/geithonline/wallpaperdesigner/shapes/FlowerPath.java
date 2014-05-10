package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class FlowerPath extends Path {

	public FlowerPath(final Point center, final float radius, final int leafs, final int zackenPerLeaf) {
		super();
		final int arms = 200;
		final float angle = (float) (2 * Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final float r = radius + (radius / 4) * (float) Math.sin(leafs * i * angle) + (radius / 8) * (float) Math.sin(leafs * zackenPerLeaf * i * angle);
			final Point p1 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * r);
			p1.y = (int) (center.y + Math.sin(i * angle) * r);
			if (i == 0) {
				moveTo(p1.x, p1.y);
			} else {
				lineTo(p1.x, p1.y);
			}
		}
		close();
	}
}
