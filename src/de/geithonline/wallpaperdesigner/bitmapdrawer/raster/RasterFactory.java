package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.POSITIONING_CIRCLE;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.GeometricRaster.POSITIONING;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.MaterialRaster.MATERIAL_POSITIONING;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class RasterFactory {

	public enum RasterPositioning {

		GEOMETRIC_RANDOM, GEOMETRIC_BOOK, GEOMETRIC_BOOK_REVERSE, GEOMETRIC_TOWER, //
		MATERIAL_RANDOM, MATERIAL_BOOK, MATERIAL_BOOK_REVERSE, MATERIAL_TOWER, //
		CIRCULAR_RANDOM, CIRCULAR_INNER, CIRCULAR_OUTER, //
		HALF_CIRCULAR_RANDOM, HALF_CIRCULAR_INNER, HALF_CIRCULAR_OUTER;
	}

	public static IRaster getRaster(final RasterPositioning positioning, final int width, final int height, final int patternRadius, final float overlap) {
		switch (positioning) {
		default:
		case GEOMETRIC_RANDOM:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.RANDOM, Settings.isUpsideDown());
		case GEOMETRIC_BOOK:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.BOOK, Settings.isUpsideDown());
		case GEOMETRIC_BOOK_REVERSE:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.BOOK_REVERSE, Settings.isUpsideDown());
		case GEOMETRIC_TOWER:
			return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.TOWER, Settings.isUpsideDown());
		case CIRCULAR_RANDOM:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.RANDOM, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case CIRCULAR_INNER:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.INNER, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case CIRCULAR_OUTER:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.OUTER, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case HALF_CIRCULAR_RANDOM:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.RANDOM, CIRCLE_TYPE.HALF);
		case HALF_CIRCULAR_INNER:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.INNER, CIRCLE_TYPE.HALF);
		case HALF_CIRCULAR_OUTER:
			return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.OUTER, CIRCLE_TYPE.HALF);

		case MATERIAL_RANDOM:
			return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.RANDOM);
		case MATERIAL_TOWER:
			return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.TOWER);

		}
	}
}
