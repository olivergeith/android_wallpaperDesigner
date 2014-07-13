package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class CloudPath extends Path {

	/**
	 * @param ecken
	 * @param center
	 * @param radius
	 * @param rotate
	 *            0-2pi
	 */
	public CloudPath(final Point center, final float radius) {
		super();
		final int ecken = 100;
		final float angle = (float) (2f * Math.PI / ecken);

		for (int i = 0; i < ecken; i++) {
			final float rh = radius;
			final float rv = radius / 2;
			// final float r = rInner;

			final Point p = new Point();
			p.x = (int) (center.x + rh * (Math.cos(i * angle) + 0.4f * Math.cos(3 * i * angle) + 0.2f * Math.cos(6 * i * angle)));
			p.y = (int) (center.y + rv * (Math.sin(i * angle) + 0.4f * Math.sin(3 * i * angle) + 0.2f * Math.sin(6 * i * angle)));
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		close();
	}

	public CloudPath(final Point center, final float radius, final int leafs, final int zackenPerLeaf) {
		super();
		final int arms = 200;
		final float angle = (float) (2 * Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final float r = radius + (radius / 4) * (float) Math.sin(leafs * i * angle) + (radius / 8) * (float) Math.sin(leafs * zackenPerLeaf * i * angle);
			final Point p1 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * r);
			p1.y = (int) (center.y + Math.sin(i * angle) * r / 2);
			if (i == 0) {
				moveTo(p1.x, p1.y);
			} else {
				lineTo(p1.x, p1.y);
			}
		}
		close();
	}

}
