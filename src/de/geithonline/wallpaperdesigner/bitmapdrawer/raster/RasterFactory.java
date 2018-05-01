package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.Arrays;
import java.util.List;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;

public class RasterFactory {
	static {
		circularVariants = Arrays.asList( //
				ELayoutVariant.LOGICAL_DIRECTED, //
				ELayoutVariant.GEOMETRICAL_DIRECTED, //
				ELayoutVariant.ALTERNATING, //
				ELayoutVariant.RANDOM //
		);
		geoGridVariants = Arrays.asList( //
				ELayoutVariant.RANDOM, //
				ELayoutVariant.LOGICAL_DIRECTED, //
				ELayoutVariant.GEOMETRICAL_DIRECTED, //
				ELayoutVariant.ALTERNATING //
		);
		randomGridVariants = Arrays.asList( //
				ELayoutVariant.RANDOM, //
				ELayoutVariant.GEOMETRICAL_DIRECTED, //
				ELayoutVariant.ALTERNATING //
		);
		materialGridVariants = Arrays.asList( //
				ELayoutVariant.RANDOM, //
				ELayoutVariant.GEOMETRICAL_DIRECTED, //
				ELayoutVariant.LOGICAL_DIRECTED //
		);
		alternateVariants = Arrays.asList( //
				ELayoutSubVariant.ALL_CORNERS, //
				ELayoutSubVariant.ALL_SIDES, //
				ELayoutSubVariant.LEFT_RIGHT, //
				ELayoutSubVariant.TOP_BOTTOM, //
				ELayoutSubVariant.TOP_LEFT_BOTTOM_RIGHT, //
				ELayoutSubVariant.TOP_RIGHT_BOTTOM_LEFT, //
				ELayoutSubVariant.TOP_RIGHT_TOP_LEFT, //
				ELayoutSubVariant.BOTTOM_RIGHT_BOTTOM_LEFT //
		);
		emptySubVariants = Arrays.asList(ELayoutSubVariant.NONE);
	}

	public static List<ELayoutVariant> circularVariants;
	public static List<ELayoutVariant> geoGridVariants;
	public static List<ELayoutVariant> randomGridVariants;
	public static List<ELayoutVariant> materialGridVariants;
	public static List<ELayoutSubVariant> alternateVariants;
	public static List<ELayoutSubVariant> emptySubVariants;

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
