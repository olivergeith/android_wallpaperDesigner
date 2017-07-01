
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class LensePath extends Path {

	public LensePath(final PointF center, final float radius) {
		drawOval(center, radius, Direction.CW);
	}

	public LensePath(final PointF center, final float radius, final Direction dir) {
		drawOval(center, radius, dir);
	}

	private void drawOval(final PointF center, final float radius, final Direction dir) {
		moveTo(center.x - radius, center.y);
		switch (dir) {
		case CCW:
			quadTo(center.x, center.y + radius, // controllpoint
					center.x + radius, center.y); // Zielpunkt
			quadTo(center.x, center.y - radius, // controllpoint
					center.x - radius, center.y); // Zielpunkt
			break;
		default:
		case CW:
			quadTo(center.x, center.y - radius, // controllpoint
					center.x + radius, center.y); // Zielpunkt
			quadTo(center.x, center.y + radius, // controllpoint
					center.x - radius, center.y); // Zielpunkt
			break;

		}
		close();
	}

}
