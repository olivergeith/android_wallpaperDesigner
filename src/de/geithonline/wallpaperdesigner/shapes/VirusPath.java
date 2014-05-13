package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class VirusPath extends Path {

	public VirusPath(final Point center, float radius) {
		super();
		radius = radius * 2;
		final int arms = Randomizer.getRandomInt(8, 12);
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius / 10;
		for (int i = 0; i <= arms; i++) {
			final float r2 = Randomizer.getRandomFloat(radius / 3, radius);
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * innerR);
			p1.y = (int) (center.y + Math.sin(i * angle) * innerR);
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

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath(final Point center, float radius, final int arms) {
		super();
		radius = radius * 2;
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius / 5;
		for (int i = 0; i <= arms; i++) {
			final float r2 = radius; // Randomizer.getRandomFloat(radius * 2 / 3, radius);
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

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public VirusPath(final Point center, float radius, final int arms, final boolean invertCircle) {
		super();
		radius = radius * 1.2f;
		final float angle = (float) (2 * Math.PI / (arms));
		final float innerR = radius * 1 / 10;
		final float bubbleRadius = radius * 0.12f;

		for (int i = 0; i <= arms; i++) {
			final float r2 = radius; // Randomizer.getRandomFloat(radius * 2 / 3, radius);
			final Point p1 = new Point();
			final Point p2 = new Point();
			p1.x = (int) (center.x + Math.cos(i * angle) * innerR);
			p1.y = (int) (center.y + Math.sin(i * angle) * innerR);
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

		for (int i = 0; i <= arms; i++) {
			final float r2 = radius; // Randomizer.getRandomFloat(radius * 2 / 3, radius);
			final Point p3 = new Point();
			p3.x = (int) (center.x + Math.cos((i + 1) * angle) * (r2 + bubbleRadius));
			p3.y = (int) (center.y + Math.sin((i + 1) * angle) * (r2 + bubbleRadius));
			if (i == 0) {
			} else {
				// quadTo(p2.x, p2.y, p1.x, p1.y);
				addCircle(p3.x, p3.y, bubbleRadius, Direction.CCW);
			}
		}

		// if (invertCircle) {
		// addCircle(center.x, center.y, radius, Direction.CCW);
		// }
	}

}
