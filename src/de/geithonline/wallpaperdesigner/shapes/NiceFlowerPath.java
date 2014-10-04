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
	public NiceFlowerPath(final int arms, final Point center, final float radius, final boolean filled, final float leafFactor, final String variante) {
		super();
		switch (variante) {
		default:
		case "Circle Filling":
			drawVariante1(arms, center, radius, filled, leafFactor);
			break;
		case "Inner Flower":
			drawVariante2(arms, center, radius, filled, leafFactor);
			break;
		case "V3":
			drawVariante3(arms, center, radius, filled);
			break;
		}
	}

	public void drawVariante2(final int arms, final Point center, final float radius, final boolean filled, final float leafFactor) {
		drawFlower(arms, leafFactor, center, radius, Direction.CW);
		if (!filled) {
			drawFlower(arms * 2, leafFactor, center, radius / 2, Direction.CCW);
			addCircle(center.x, center.y, radius * 0.17f, Direction.CW);
		}
	}

	public void drawVariante1(final int arms, final Point center, final float radius, final boolean filled, final float leafFactor) {
		drawFlower(arms, leafFactor, center, radius, Direction.CW);
		if (!filled) {
			addCircle(center.x, center.y, radius * 0.30f, Direction.CCW);
		}
	}

	public void drawVariante3(final int arms, final Point center, final float radius, final boolean filled) {
		final float angle = (float) (Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final Point cp1 = new Point();
			final Point p1 = new Point();
			final Point p2 = new Point();
			final Point cp2 = new Point();
			final double cpRadius = radius * 0.8f;
			cp1.x = (int) (center.x + Math.cos((2 * i - 1) * angle) * cpRadius);
			cp1.y = (int) (center.y + Math.sin((2 * i - 1) * angle) * cpRadius);
			cp2.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * cpRadius);
			cp2.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * cpRadius);
			p1.x = (int) (center.x + Math.cos((2 * i) * angle) * radius);
			p1.y = (int) (center.y + Math.sin((2 * i) * angle) * radius);
			p2.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * radius * 0.2f);
			p2.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * radius * 0.2f);
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

	private void drawFlower(final int arms, final float leafFactor, final Point center, final float radius, final Direction dir) {
		int d = 1;
		if (dir.equals(Direction.CCW)) {
			d = -1;
		}
		final float angle = (float) (d * Math.PI / (arms));
		for (int i = 0; i <= arms; i++) {
			final Point cp1 = new Point();
			final Point p1 = new Point();
			final Point p2 = new Point();
			final Point cp2 = new Point();
			final double cpRadius = radius * 1.1f * leafFactor;
			cp1.x = (int) (center.x + Math.cos((2 * i - 1) * angle) * cpRadius);
			cp1.y = (int) (center.y + Math.sin((2 * i - 1) * angle) * cpRadius);
			cp2.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * cpRadius);
			cp2.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * cpRadius);
			p1.x = (int) (center.x + Math.cos((2 * i) * angle) * radius);
			p1.y = (int) (center.y + Math.sin((2 * i) * angle) * radius);
			p2.x = (int) (center.x + Math.cos((2 * i + 1) * angle) * radius * 0.45f);
			p2.y = (int) (center.y + Math.sin((2 * i + 1) * angle) * radius * 0.45f);
			if (i == 0) {
				moveTo(p2.x, p2.y);
			} else {
				quadTo(cp1.x, cp1.y, p1.x, p1.y);
				quadTo(cp2.x, cp2.y, p2.x, p2.y);
			}
		}
		close();
	}

}
