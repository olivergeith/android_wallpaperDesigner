package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class CircularRaster extends AbstractRaster {

	protected enum CIRCLE_TYPE {
		CIRCLE_CENTER_BOTTOM, CIRCLE, SPIRAL, CIRCLE_ADJUSTABLE_CENTER, SPIRAL_ADJUSTABLE_CENTER;
	}

	public CircularRaster(final int width, final int height, final int radius, final float overlap, final ELayoutVariant positioning,
			final CIRCLE_TYPE circleType) {
		super(radius, overlap, width, height);

		setPositioning(positioning);

		switch (circleType) {
			default:
			case CIRCLE_CENTER_BOTTOM: {
				final PointF center = new PointF(width / 2, height);
				calculateRings(width, height, radius, overlap, center);
				break;
			}
			case CIRCLE_ADJUSTABLE_CENTER: {
				final PointF center = new PointF(width * Settings.getCenterPointX(), height * Settings.getCenterPointY());
				calculateRings(width, height, radius, overlap, center);
				break;
			}
			case CIRCLE: {
				final PointF center = new PointF(width / 2, height / 2);
				calculateRings(width, height, radius, overlap, center);
				break;
			}
			case SPIRAL: {
				final PointF center = new PointF(width / 2, height / 2);
				calculateSpiral(width, height, radius, overlap, center);
				break;
			}
			case SPIRAL_ADJUSTABLE_CENTER: {
				final PointF center = new PointF(width * Settings.getCenterPointX(), height * Settings.getCenterPointY());
				calculateSpiral(width, height, radius, overlap, center);
				break;
			}
		}
	}

	private float calculateMaxRadius(final int width, final int height, final PointF center) {
		float maximumRadius = 0;
		float max = GeometrieHelper.calcDistance(center, new PointF(0f, 0f)) + WIDE_CANVAS_LIMIT * getAbstand();
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		max = GeometrieHelper.calcDistance(center, new PointF(width, 0f)) + WIDE_CANVAS_LIMIT * getAbstand();
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		max = GeometrieHelper.calcDistance(center, new PointF(0f, height)) + WIDE_CANVAS_LIMIT * getAbstand();
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		max = GeometrieHelper.calcDistance(center, new PointF(width, height)) + WIDE_CANVAS_LIMIT * getAbstand();
		if (max > maximumRadius) {
			maximumRadius = max;
		}
		return maximumRadius;
	}

	private void calculateRings(final int width, final int height, final int radius, final float overlap, final PointF center) {
		final float maximumRadius = calculateMaxRadius(width, height, center);

		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = (int) (maximumRadius / radiusStep + 2);

		points.add(new Point((int) center.x, (int) center.y));
		// Log.i("Half Ring RASTER", "Anzahl Ringe = " + anzRinge);
		for (int ring = 1; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r) / radiusStep;
			float winkelProEcke = (float) (Math.PI / ecken) * 2;
			if (Settings.isCounterClockwise()) {
				winkelProEcke = winkelProEcke * -1f;
			}
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

	private void calculateSpiral(final int width, final int height, final int radius, final float overlap, final PointF center) {
		final float maximumRadius = calculateMaxRadius(width, height, center);
		final int radiusStep = Math.round(radius * 2 * overlap);
		final int anzRinge = (int) (maximumRadius / radiusStep + 2);

		points.add(new Point((int) center.x, (int) center.y));
		// Log.i("CIRCULAR RASTER", "Anzahl Ringe = " + anzRinge);

		float startWinkel = 0f;
		if (Settings.isRandomStartwinkel()) {
			startWinkel = Randomizer.getRandomFloat(0, (float) Math.PI * 2);
		}

		for (int ring = 0; ring <= anzRinge; ring++) {
			final float r = ring * radiusStep;
			final int ecken = (int) (Math.PI * 2 * r) / radiusStep + Math.max(anzRinge - ring * ring, 0);
			float winkelProEcke = (float) (Math.PI * 2 / (ecken));
			if (Settings.isCounterClockwise()) {
				winkelProEcke = winkelProEcke * -1f;
			}
			// Log.i("CIRCULAR RASTER", "Anzahl Punkte = " + ecken);
			for (int ecke = 0; ecke < ecken; ecke++) {
				final float rp = r + radiusStep * ecke / ecken;
				final Point p = new Point();
				p.x = (int) (center.x + Math.cos(ecke * winkelProEcke + startWinkel) * rp);
				p.y = (int) (center.y + Math.sin(ecke * winkelProEcke + startWinkel) * rp);
				// Limitieren auf punkte innerhalb des Canvas
				addPoint2List(width, height, p);
			}
		}
	}

	@Override
	public Point drawNextPoint() {
		switch (getPositioning()) {
			default:
				return super.drawNextPoint();
			case INNER:
				return drawFirstPoint();
			case OUTER:
				return drawLastPoint();
		}
	}

}
