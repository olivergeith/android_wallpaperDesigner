package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.POSITIONING_CIRCLE;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.GeometricRaster.POSITIONING;

public class RasterFactory {

	public enum RasterPositioning {

		GEOMETRIC_RANDOM, GEOMETRIC_BOOK, GEOMETRIC_BOOK_REVERSE, GEOMETRIC_TOWER, //
		CIRCULAR_RANDOM, CIRCULAR_INNER, CIRCULAR_OUTER;
	}

	public static IRaster getRaster(final RasterPositioning positioning, final int width, final int height, final int patternRadius, final float overlap) {
		switch (positioning) {
		default:
		case GEOMETRIC_RANDOM:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.RANDOM);
		case GEOMETRIC_BOOK:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.BOOK);
		case GEOMETRIC_BOOK_REVERSE:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.BOOK_REVERSE);
		case GEOMETRIC_TOWER:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.TOWER);
		case CIRCULAR_RANDOM:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.RANDOM);
		case CIRCULAR_INNER:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.INNER);
		case CIRCULAR_OUTER:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.OUTER);
		}
	}
}
