
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class LensePath extends Path {

	public LensePath(final PointF center, final float radius) {
		drawLense(center, radius, radius, Direction.CW);
	}

	public LensePath(final PointF center, final float radius, final float breite, final Direction dir) {
		drawLense(center, radius, breite, dir);
	}

	private void drawLense(final PointF center, final float radius, final float breite, final Direction dir) {
		moveTo(center.x - radius, center.y);
		switch (dir) {
		case CCW:
			quadTo(center.x, center.y + breite, // controllpoint
					center.x + radius, center.y); // Zielpunkt
			quadTo(center.x, center.y - breite, // controllpoint
					center.x - radius, center.y); // Zielpunkt
			break;
		default:
		case CW:
			quadTo(center.x, center.y - breite, // controllpoint
					center.x + radius, center.y); // Zielpunkt
			quadTo(center.x, center.y + breite, // controllpoint
					center.x - radius, center.y); // Zielpunkt
			break;

		}
		close();
	}

}
