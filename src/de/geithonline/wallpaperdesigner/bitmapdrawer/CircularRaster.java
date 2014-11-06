package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularRaster {
	public enum POSITIONING_CIRCLE {
		RANDOM, INNER, OUTER;
	}

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;
	private final POSITIONING_CIRCLE positioning;

	public CircularRaster(final int width, final int height, final int radius, final float overlap, final POSITIONING_CIRCLE positioning) {

		this.positioning = positioning;

		final int maximumRadius = (int) Math.sqrt(width * width / 4 + height * height / 4);

		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = maximumRadius / radiusStep;

		final Point center = new Point(width / 2, height / 2);
		points.add(center);
		for (int ring = 1; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r);
			final float angle = (float) (Math.PI / ecken) * 2;
			for (int ecke = 0; ecke < ecken; ecke++) {
				final Point p = new Point();

				p.x = (int) (center.x + Math.cos(ecke * angle) * r);
				p.y = (int) (center.y + Math.sin(ecke * angle) * r);
				points.add(p);
			}
		}

		anzahlPatterns = points.size();
	}

	public Point drawPoint() {
		switch (positioning) {
		case RANDOM:
			return drawRandomPoint();
		default:
		case OUTER:
			return drawNextPoint();
		case INNER:
			return drawNextPointBackward();
		}
	}

	public int getAnzahlPatterns() {
		return anzahlPatterns;
	}

	private Point drawRandomPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Randomizer.getRandomInt(-1, size - 1);
		final Point p = points.remove(location);
		return p;
	}

	private Point drawNextPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = 0;
		final Point p = points.remove(location);
		return p;
	}

	private Point drawNextPointBackward() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = size - 1;
		final Point p = points.remove(location);
		return p;
	}

	private boolean top = true;

	private Point drawNextTowerPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		if (top) {
			location = size - 1;
		}
		final Point p = points.remove(location);
		top = !top;
		return p;
	}

}
