
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.StarPath.STAR_TYPE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class TrailStarPath extends Path {

	public enum TRAIL_TYPE {
		Stars, Lines, StarsGettingBigger, Sinus;
	}

	public Path trailPath = new Path();

	public TrailStarPath(final PointF center, final float radius, final boolean filled, final TRAIL_TYPE type) {
		super();

		addPath(new StarPath(5, center, radius, STAR_TYPE.NORMAL, true));
		// addPath(new KochSnowFlakePath(center, radius, true, 3));
		if (filled) {
			// if (Randomizer.getRandomInt(0, 5) == 1) {
			switch (type) {
			default:
			case Stars:
				createTrailOfStars(center, radius, filled);
				break;
			case Sinus:
				createSinusTrailOfStars(center, radius, filled);
				break;
			case StarsGettingBigger:
				createTrailOfStarsGettingBigger(center, radius, filled);
				break;
			case Lines:
				createTrailOfLines(center, radius, filled);
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
			if (Randomizer.getRandomInt(0, 5) != 1) {
				trailPath.addPath(new StarPath(5, pos, randRadius, STAR_TYPE.NORMAL, true));
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
			if (Randomizer.getRandomInt(0, 5) != 1) {
				trailPath.addPath(new StarPath(5, pos, randRadius, STAR_TYPE.NORMAL, true));
			}
		}
	}

	private void createTrailOfStarsGettingBigger(final PointF center, final float radius, final boolean filled) {

		// Trail ony when filled
		final float distanceToTop = center.y - 5;
		final float trailRadius = radius / 5;
		final float distance = 2.0f;
		final int numberOfTrailStars = (int) (distanceToTop / trailRadius / distance);
		for (int i = 0; i < numberOfTrailStars - 1; i++) {
			final PointF pos = new PointF();
			pos.x = center.x + Randomizer.getRandomFloat(-trailRadius * 1.5f, +trailRadius * 1.5f);
			pos.y = 5 + distance * trailRadius * i;
			final float starRadius = trailRadius * 0.3f //
					+ (float) i / (float) numberOfTrailStars * 0.7f * trailRadius//
					+ Randomizer.getRandomFloat(0, 0.7f * trailRadius);

			if (Randomizer.getRandomInt(0, 6) != 1) {
				trailPath.addPath(new StarPath(5, pos, starRadius, STAR_TYPE.NORMAL, true));
			}
		}
	}

	private void createTrailOfLines(final PointF center, final float radius, final boolean filled) {

		// Trail ony when filled
		final float distanceToTop = center.y - 5;
		final float trailRadius = radius / 3;
		final float distance = 0.9f;
		final int numberOfTrailStars = (int) (distanceToTop / trailRadius / distance);
		for (int i = 0; i < numberOfTrailStars - 3; i++) {
			final PointF pos = new PointF();
			// final float angle = (float) ((float) i / numberOfTrailStars * Math.PI * sinRepeats);
			// pos.x = center.x + (float) (trailRadius * 2 * Math.sin(angle));// Randomizer.getRandomFloat(-trailRadius / 2, +trailRadius / 2);
			pos.x = center.x + Randomizer.getRandomFloat(-trailRadius * 0.75f, +trailRadius * 0.75f);
			pos.y = 5 + distance * trailRadius * i;
			final float randY = Randomizer.getRandomFloat(trailRadius * 2, 4 * trailRadius);
			trailPath.addPath(new OvalPath(pos, radius / 30, randY, Direction.CW));
		}
	}

}
