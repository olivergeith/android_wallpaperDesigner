package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularRaster extends AbstractRaster {

	protected enum CIRCLE_TYPE {
		HALF, FULL_RANDOM_STARWINKEL, SPIRAL;
	}

	public CircularRaster(final int width, final int height, final int radius, final float overlap, final RasterPositioning positioning,
			final CIRCLE_TYPE circleType) {
		super(radius, overlap);

		setPositioning(positioning);

		switch (circleType) {
			default:
			case HALF:
				calculateHalfRings(width, height, radius, overlap);
				break;
			case FULL_RANDOM_STARWINKEL:
				calculateCenteredRingsRandomStartWinkel(width, height, radius, overlap);
				break;
			case SPIRAL:
				calculateSpiral(width, height, radius, overlap);
				break;
		}
	}

	private void calculateHalfRings(final int width, final int height, final int radius, final float overlap) {
		final int maximumRadius = (int) Math.sqrt(width * width / 4 + height * height) + WIDE_CANVAS_LIMIT * getAbstand();

		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = maximumRadius / radiusStep + 2;

		final Point center = new Point(width / 2, height);
		points.add(center);
		// Log.i("Half Ring RASTER", "Anzahl Ringe = " + anzRinge);
		for (int ring = 1; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r) / radiusStep;
			final float winkelProEcke = (float) (Math.PI / ecken) * 2;
			// Log.i("Half Ring RASTER", "Anzahl Rcken = " + ecken);
			float startWinkel = (float) (Math.PI / 2);
			if (Settings.isRandomStartwinkel()) {
				startWinkel = Randomizer.getRandomFloat(0, (float) Math.PI * 2);
			}
			// final float startWinkel = (float) (Math.PI / 2);
			for (int ecke = 0; ecke < ecken; ecke++) {
				final Point p = new Point();
				p.x = (int) (center.x + Math.cos(ecke * winkelProEcke + startWinkel) * r);
				p.y = (int) (center.y + Math.sin(ecke * winkelProEcke + startWinkel) * r);
				// Limitieren auf punkte innerhalb des Canvas
				addPoint2List(width, height, p);
			}
		}
	}

	private void calculateCenteredRingsRandomStartWinkel(final int width, final int height, final int radius, final float overlap) {
		final int maximumRadius = (int) Math.sqrt(width * width / 4 + height * height / 4) + WIDE_CANVAS_LIMIT * getAbstand();

		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = maximumRadius / radiusStep + 1;

		final Point center = new Point(width / 2, height / 2);
		points.add(center);
		// Log.i("CIRCULAR RASTER", "Anzahl Ringe = " + anzRinge);
		for (int ring = 1; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r) / radiusStep;
			final float winkelProEcke = (float) (Math.PI / ecken) * 2;
			// Log.i("CIRCULAR RASTER", "Anzahl Ecken = " + ecken);
			float startWinkel = (float) (Math.PI / 2);
			if (Settings.isRandomStartwinkel()) {
				startWinkel = Randomizer.getRandomFloat(0, (float) Math.PI * 2);
			}
			for (int ecke = 0; ecke < ecken; ecke++) {
				final Point p = new Point();
				p.x = (int) (center.x + Math.cos(ecke * winkelProEcke + startWinkel) * r);
				p.y = (int) (center.y + Math.sin(ecke * winkelProEcke + startWinkel) * r);
				// Limitieren auf punkte innerhalb des Canvas
				addPoint2List(width, height, p);
			}
		}
	}

	private void calculateSpiral(final int width, final int height, final int radius, final float overlap) {
		final int maximumRadius = (int) Math.sqrt(width * width / 4 + height * height / 4) + WIDE_CANVAS_LIMIT * getAbstand();
		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = maximumRadius / radiusStep;
		final Point center = new Point(width / 2, height / 2);
		points.add(center);
		// Log.i("CIRCULAR RASTER", "Anzahl Ringe = " + anzRinge);

		for (int ring = 0; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r) / radiusStep + Math.max(anzRinge - ring * ring, 0);
			final float winkelProEcke = (float) (Math.PI * 2 / (ecken));
			// Log.i("CIRCULAR RASTER", "Anzahl Punkte = " + ecken);
			for (int ecke = 0; ecke < ecken; ecke++) {
				final float rp = r + radiusStep * ecke / ecken;
				final Point p = new Point();
				p.x = (int) (center.x + Math.cos(ecke * winkelProEcke) * rp);
				p.y = (int) (center.y + Math.sin(ecke * winkelProEcke) * rp);
				// Limitieren auf punkte innerhalb des Canvas
				addPoint2List(width, height, p);
			}
		}
	}

	@Override
	public Point drawNextPoint() {
		switch (getPositioning()) {
			case RANDOM:
				return drawRandomPoint();
			default:
			case INNER:
				return drawNextBookPoint();
			case OUTER:
				return drawNextBookPointReverse();
		}
	}

}
