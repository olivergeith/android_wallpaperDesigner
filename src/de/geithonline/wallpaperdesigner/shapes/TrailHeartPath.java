
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.HeartPath.HEART_SHAPE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class TrailHeartPath extends Path {

	public enum HEART_TRAIL_TYPE {
		Hearts, Sinus;
	}

	public Path trailPath = new Path();

	public TrailHeartPath(final PointF center, final float radius, final boolean filled, final HEART_TRAIL_TYPE type) {
		super();

		addPath(new HeartPath(center, radius, false, HEART_SHAPE.Lovely));
		// addPath(new KochSnowFlakePath(center, radius, true, 3));
		if (filled) {
			switch (type) {
			default:
			case Hearts:
				createTrailOfStars(center, radius, filled);
				break;
			case Sinus:
				createSinusTrailOfStars(center, radius, filled);
				break;
			}
		}

	}

	private void createTrailOfStars(final PointF center, final float radius, final boolean filled) {

		// Trail ony when filled
		final float distanceToTop = center.y - 5;
		final float trailRadius = radius / 5;
		final float distance = 2f;
		final int numberOfTrailStars = (int) (distanceToTop / trailRadius / distance);
		for (int i = 0; i < numberOfTrailStars - 1; i++) {
			final PointF pos = new PointF();
			pos.x = center.x + Randomizer.getRandomFloat(-trailRadius / 2, +trailRadius / 2);
			pos.y = 5 + distance * trailRadius * i;
			final float randRadius = Randomizer.getRandomFloat(trailRadius / 2, trailRadius * 2);
			if (Randomizer.getRandomInt(1, 5) != 1) {
				final Path p = new HeartPath(pos, randRadius, false, HEART_SHAPE.Lovely);
				PathHelper.rotatePath(pos.x, pos.y, p, Randomizer.getRandomFloat(-19, +19));
				trailPath.addPath(p);
			}
		}
	}

	private void createSinusTrailOfStars(final PointF center, final float radius, final boolean filled) {

		// Trail ony when filled
		final float distanceToTop = center.y - 5;
		final float trailRadius = radius / 5;
		final float distance = 2f;
		final int numberOfTrailStars = (int) (distanceToTop / trailRadius / distance);
		final int sinRepeats = numberOfTrailStars / 5;
		for (int i = 0; i < numberOfTrailStars - 1; i++) {
			final PointF pos = new PointF();
			final float angle = (float) ((float) i / numberOfTrailStars * Math.PI * sinRepeats);
			pos.x = center.x + (float) (trailRadius * 2 * Math.sin(angle));// Randomizer.getRandomFloat(-trailRadius / 2, +trailRadius / 2);
			pos.y = 5 + distance * trailRadius * i;
			final float randRadius = Randomizer.getRandomFloat(trailRadius / 2, trailRadius * 2);
			if (Randomizer.getRandomInt(1, 5) != 1) {
				final Path p = new HeartPath(pos, randRadius, false, HEART_SHAPE.Lovely);
				PathHelper.rotatePath(pos.x, pos.y, p, Randomizer.getRandomFloat(-19, +19));
				trailPath.addPath(p);
			}
		}
	}
}
