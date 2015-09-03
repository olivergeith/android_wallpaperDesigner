package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.POSITIONING_CIRCLE;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.DiagonalRaster.DIAGONAL_POSITIONING;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.GeometricRaster.POSITIONING;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.HexagonalRaster.HEX_POSITIONING;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.MaterialRaster.MATERIAL_POSITIONING;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class RasterFactory {

	public enum RasterPositioning {

		GEOMETRIC_RANDOM, GEOMETRIC_BOOK, GEOMETRIC_BOOK_REVERSE, GEOMETRIC_TOWER, GEOMETRIC_CENTER, //
		HEX_RANDOM, HEX_BOOK, HEX_BOOK_REVERSE, HEX_TOWER, HEX_CENTER, //
		MATERIAL_RANDOM, MATERIAL_BOOK, MATERIAL_BOOK_REVERSE, MATERIAL_TOWER, MATERIAL_CENTER, //
		CIRCULAR_RANDOM, CIRCULAR_INNER, CIRCULAR_OUTER, //
		HALF_CIRCULAR_RANDOM, HALF_CIRCULAR_INNER, HALF_CIRCULAR_OUTER, SPIRAL_RANDOM, SPIRAL_INNER, SPIRAL_OUTER, //
		DIAGONAL_BOOK, DIAGONAL_BOOK_REVERSE, DIAGONAL_TOWER, DIAGONAL_CENTER, DIAGONAL_RANDOM;
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
			case GEOMETRIC_CENTER:
				return new GeometricRaster(width, height, patternRadius, overlap, POSITIONING.CENTER, Settings.isUpsideDown());

			case HEX_RANDOM:
				return new HexagonalRaster(width, height, patternRadius, overlap, HEX_POSITIONING.RANDOM, Settings.isUpsideDown());
			case HEX_BOOK:
				return new HexagonalRaster(width, height, patternRadius, overlap, HEX_POSITIONING.BOOK, Settings.isUpsideDown());
			case HEX_BOOK_REVERSE:
				return new HexagonalRaster(width, height, patternRadius, overlap, HEX_POSITIONING.BOOK_REVERSE, Settings.isUpsideDown());
			case HEX_TOWER:
				return new HexagonalRaster(width, height, patternRadius, overlap, HEX_POSITIONING.TOWER, Settings.isUpsideDown());
			case HEX_CENTER:
				return new HexagonalRaster(width, height, patternRadius, overlap, HEX_POSITIONING.CENTER, Settings.isUpsideDown());

			case DIAGONAL_RANDOM:
				return new DiagonalRaster(width, height, patternRadius, overlap, DIAGONAL_POSITIONING.RANDOM, Settings.isUpsideDown());
			case DIAGONAL_BOOK:
				return new DiagonalRaster(width, height, patternRadius, overlap, DIAGONAL_POSITIONING.BOOK, Settings.isUpsideDown());
			case DIAGONAL_BOOK_REVERSE:
				return new DiagonalRaster(width, height, patternRadius, overlap, DIAGONAL_POSITIONING.BOOK_REVERSE, Settings.isUpsideDown());
			case DIAGONAL_TOWER:
				return new DiagonalRaster(width, height, patternRadius, overlap, DIAGONAL_POSITIONING.TOWER, Settings.isUpsideDown());
			case DIAGONAL_CENTER:
				return new DiagonalRaster(width, height, patternRadius, overlap, DIAGONAL_POSITIONING.CENTER, Settings.isUpsideDown());

			case SPIRAL_RANDOM:
				return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.RANDOM, CIRCLE_TYPE.SPIRAL);
			case SPIRAL_INNER:
				return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.INNER, CIRCLE_TYPE.SPIRAL);
			case SPIRAL_OUTER:
				return new CircularRaster(width, height, patternRadius, overlap, POSITIONING_CIRCLE.OUTER, CIRCLE_TYPE.SPIRAL);

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

			case MATERIAL_BOOK:
				return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.BOOK);
			case MATERIAL_BOOK_REVERSE:
				return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.BOOK_REVERSE);
			case MATERIAL_RANDOM:
				return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.RANDOM);
			case MATERIAL_TOWER:
				return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.TOWER);
			case MATERIAL_CENTER:
				return new MaterialRaster(width, height, patternRadius, overlap, MATERIAL_POSITIONING.CENTER);

		}
	}
}
