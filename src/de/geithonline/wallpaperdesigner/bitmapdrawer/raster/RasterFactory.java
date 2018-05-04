package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;

public class RasterFactory {

	public static AbstractRaster getRaster(final ELayout layout, final ELayoutVariant layoutVariant, final ELayoutSubVariant subVariant, final int width,
			final int height, final int patternRadius, final float overlap) {
		Log.i("Layout", "Layout = " + layout);
		switch (layout) {
			default:
			case RANDOM:
				return new RandomRaster(width, height, patternRadius, overlap, layoutVariant, subVariant);
			case GEOMETRIC_GRID:
				return new GeometricRaster(width, height, patternRadius, overlap, layoutVariant, subVariant);
			case CIRCULAR_GRID:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, subVariant, CIRCLE_TYPE.CIRCLE);
			case CIRCULAR_GRID_ADJUSTABLE_CENTER:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, subVariant, CIRCLE_TYPE.CIRCLE_ADJUSTABLE_CENTER);
			case DIAGONAL_GRID:
				return new DiagonalRaster(width, height, patternRadius, overlap, layoutVariant, subVariant);
			case HALF_CIRCLE_GRID:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, subVariant, CIRCLE_TYPE.CIRCLE_CENTER_BOTTOM);
			case HEX_GRID:
				return new HexagonalRaster(width, height, patternRadius, overlap, layoutVariant, subVariant);
			case MATERIAL_GRID:
				return new MaterialRaster(width, height, patternRadius, overlap, layoutVariant, subVariant);
			case SPIRAL_GRID:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, subVariant, CIRCLE_TYPE.SPIRAL);
			case SPIRAL_GRID_ADJUSTABLE_CENTER:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, subVariant, CIRCLE_TYPE.SPIRAL_ADJUSTABLE_CENTER);
		}
	}
}
