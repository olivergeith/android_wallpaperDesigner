package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class XEckPath extends Path {

	/**
	 * @param ecken
	 * @param center
	 * @param rOuter
	 * @param rotate
	 *            0-2pi
	 */
	public XEckPath(final int ecken, final PointF center, final float rOuter, final float rotate, final boolean filled) {
		super();
		final float angle = (float) (Math.PI / ecken) * 2;

		for (int i = 0; i < ecken; i++) {
			final float r = rOuter;
			final PointF p = new PointF();
			p.x = (float) (center.x + Math.cos(i * angle + rotate) * r);
			p.y = (float) (center.y + Math.sin(i * angle + rotate) * r);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				lineTo(p.x, p.y);
			}
		}
		close();
		if (!filled) {
			for (int i = 0; i < ecken; i++) {
				final float r = rOuter / 2;
				final PointF p = new PointF();
				p.x = (float) (center.x + Math.cos(-i * angle + rotate) * r);
				p.y = (float) (center.y + Math.sin(-i * angle + rotate) * r);
				if (i == 0) {
					moveTo(p.x, p.y);
				} else {
					lineTo(p.x, p.y);
				}
			}
			close();
		}
	}
}
