package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;

public class NiceFlowerPath extends Path {

	/**
	 * @param arms
	 * @param center
	 * @param radius
	 * @param filled
	 * @param leafFactor
	 *            Values between 0.5f and 1.5f
	 */
	public NiceFlowerPath(final int arms, final Point center, final float radius, final boolean filled, final float leafFactor, final float rotate) {
		super();
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final Point cp1 = new Point();
			final Point p1 = new Point();
			final Point p2 = new Point();
			final Point cp2 = new Point();
			final double cpRadius = radius * 1.1f * leafFactor;
			cp1.x = (int) (center.x + Math.cos(rotate + (2 * i - 1) * angle) * cpRadius);
			cp1.y = (int) (center.y + Math.sin(rotate + (2 * i - 1) * angle) * cpRadius);
			cp2.x = (int) (center.x + Math.cos(rotate + (2 * i + 1) * angle) * cpRadius);
			cp2.y = (int) (center.y + Math.sin(rotate + (2 * i + 1) * angle) * cpRadius);
			p1.x = (int) (center.x + Math.cos(rotate + (2 * i) * angle) * radius);
			p1.y = (int) (center.y + Math.sin(rotate + (2 * i) * angle) * radius);
			p2.x = (int) (center.x + Math.cos(rotate + (2 * i + 1) * angle) * radius * 0.45f);
			p2.y = (int) (center.y + Math.sin(rotate + (2 * i + 1) * angle) * radius * 0.45f);
			if (i == 0) {
				moveTo(p2.x, p2.y);
			} else {
				quadTo(cp1.x, cp1.y, p1.x, p1.y);
				quadTo(cp2.x, cp2.y, p2.x, p2.y);
			}
		}
		close();

		if (!filled) {
			addCircle(center.x, center.y, radius * 0.30f, Direction.CCW);
		}

	}

	public NiceFlowerPath(final int arms, final Point center, final float radius, final boolean filled, final float rotate) {
		super();
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final Point cp1 = new Point();
			final Point p1 = new Point();
			final Point p2 = new Point();
			final Point cp2 = new Point();
			final double cpRadius = radius * 0.8f;
			cp1.x = (int) (center.x + Math.cos(rotate + (2 * i - 1) * angle) * cpRadius);
			cp1.y = (int) (center.y + Math.sin(rotate + (2 * i - 1) * angle) * cpRadius);
			cp2.x = (int) (center.x + Math.cos(rotate + (2 * i + 1) * angle) * cpRadius);
			cp2.y = (int) (center.y + Math.sin(rotate + (2 * i + 1) * angle) * cpRadius);
			p1.x = (int) (center.x + Math.cos(rotate + (2 * i) * angle) * radius);
			p1.y = (int) (center.y + Math.sin(rotate + (2 * i) * angle) * radius);
			p2.x = (int) (center.x + Math.cos(rotate + (2 * i + 1) * angle) * radius * 0.2f);
			p2.y = (int) (center.y + Math.sin(rotate + (2 * i + 1) * angle) * radius * 0.2f);
			if (i == 0) {
				moveTo(p2.x, p2.y);
			} else {
				quadTo(cp1.x, cp1.y, p1.x, p1.y);
				quadTo(cp2.x, cp2.y, p2.x, p2.y);
			}
		}
		close();

		if (!filled) {
			addCircle(center.x, center.y, radius * 0.15f, Direction.CCW);
		}

	}

}
