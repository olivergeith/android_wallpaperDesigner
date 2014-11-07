package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularRaster implements IRaster {
	protected enum POSITIONING_CIRCLE {
		RANDOM, INNER, OUTER;
	}

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;
	private final POSITIONING_CIRCLE positioning;

	public CircularRaster(final int width, final int height, final int radius, final float overlap, final POSITIONING_CIRCLE positioning) {

		this.positioning = positioning;

		calculateCenteredRings(width, height, radius, overlap);

		anzahlPatterns = points.size();
		Log.i("CIRCULAR RASTER", "Anzahl Points = " + anzahlPatterns);
	}

	private void calculateCenteredRings(final int width, final int height, final int radius, final float overlap) {
		final int maximumRadius = (int) Math.sqrt(width * width / 4 + height * height / 4);

		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = maximumRadius / radiusStep;

		final Point center = new Point(width / 2, height / 2);
		points.add(center);
		Log.i("CIRCULAR RASTER", "Anzahl Ringe = " + anzRinge);
		for (int ring = 1; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r) / radiusStep;
			final float winkelProEcke = (float) (Math.PI / ecken) * 2;
			Log.i("CIRCULAR RASTER", "Anzahl Rcken = " + ecken);
			for (int ecke = 0; ecke < ecken; ecke++) {

				final Point p = new Point();

				p.x = (int) (center.x + Math.cos(ecke * winkelProEcke) * r);
				p.y = (int) (center.y + Math.sin(ecke * winkelProEcke) * r);
				points.add(p);
			}
		}
	}

	// private void calculateRingsBottomLeft(final int width, final int height, final int radius, final float overlap) {
	// final int maximumRadius = (int) Math.sqrt(width * width + height * height);
	//
	// final int radiusStep = Math.round(radius * 2 * overlap);
	// final int anzRinge = maximumRadius / radiusStep;
	//
	// final Point center = new Point(0, 0);
	// points.add(center);
	// Log.i("CIRCULAR RASTER", "Anzahl Ringe = " + anzRinge);
	// for (int ring = 1; ring <= anzRinge; ring++) {
	// final double roundtrip = Math.PI * 2;
	// final float r = ring * radiusStep;
	// final float d = 2 * r;
	// final float umfang = (float) (Math.PI * d) / 4; // weil nur 1/4 Kreis
	//
	// final int ecken = (int) umfang / radiusStep;
	// final float winkelProEcke = (float) (roundtrip / 4 / ecken);
	// Log.i("CIRCULAR RASTER", "Anzahl Rcken = " + ecken);
	// for (int ecke = 0; ecke < ecken; ecke++) {
	//
	// final Point p = new Point();
	//
	// p.x = (int) (center.x + Math.cos(ecke * winkelProEcke) * r);
	// p.y = (int) (center.y + Math.sin(ecke * winkelProEcke) * r);
	// points.add(p);
	// }
	// }
	// }

	@Override
	public Point drawNextPoint() {
		switch (positioning) {
		case RANDOM:
			return drawRandomPoint();
		default:
		case INNER:
			return drawPoint();
		case OUTER:
			return drawNextPointBackward();
		}
	}

	@Override
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

	private Point drawPoint() {
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

}
