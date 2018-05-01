package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.Arrays;
import java.util.List;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;

public class RasterFactory {

	public static List<ELayoutVariant> circularVariants = Arrays.asList( //
			ELayoutVariant.INNER, //
			ELayoutVariant.OUTER, //
			ELayoutVariant.TOPMOST, //
			ELayoutVariant.BOTTOMMOST, //
			ELayoutVariant.LEFTMOST, //
			ELayoutVariant.RIGHTMOST, //
			ELayoutVariant.TOP_LEFT_2_BOTTOM_RIGHT, //
			ELayoutVariant.TOP_RIGHT_2_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.ALTERNATING_V2, //
			ELayoutVariant.ALTERNATING_TOP_LEFT_BOTTOM_RIGHT, //
			ELayoutVariant.ALTERNATING_TOP_RIGHT_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING_TOP_RIGHT_TOP_LEFT, //
			ELayoutVariant.ALTERNATING_BOTTOM_RIGHT_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING_LEFT_RIGHT, //
			ELayoutVariant.ALTERNATING_TOP_BOTTOM, //
			ELayoutVariant.CENTER, //
			ELayoutVariant.DUO_CENTER, //
			ELayoutVariant.TOWER, //
			ELayoutVariant.TRISTEP, //
			ELayoutVariant.QUADSTEP, //
			ELayoutVariant.DUO_STEP_INNER_2_OUTER, //
			ELayoutVariant.DUO_STEP_OUTER_2_INNER, //
			ELayoutVariant.RANDOM //
	);

	public static List<ELayoutVariant> geoGridVariants = Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.INNER, //
			ELayoutVariant.OUTER, //
			ELayoutVariant.TOPMOST, //
			ELayoutVariant.BOTTOMMOST, //
			ELayoutVariant.LEFTMOST, //
			ELayoutVariant.RIGHTMOST, //
			ELayoutVariant.TOP_LEFT_2_BOTTOM_RIGHT, //
			ELayoutVariant.TOP_RIGHT_2_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.ALTERNATING_V2, //
			ELayoutVariant.ALTERNATING_TOP_LEFT_BOTTOM_RIGHT, //
			ELayoutVariant.ALTERNATING_TOP_RIGHT_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING_TOP_RIGHT_TOP_LEFT, //
			ELayoutVariant.ALTERNATING_BOTTOM_RIGHT_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING_LEFT_RIGHT, //
			ELayoutVariant.ALTERNATING_TOP_BOTTOM, //
			ELayoutVariant.CENTER, //
			ELayoutVariant.DUO_CENTER, //
			ELayoutVariant.TOWER, //
			ELayoutVariant.TRISTEP, //
			ELayoutVariant.QUADSTEP //
	);

	public static List<ELayoutVariant> randomGridVariants = Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.INNER, //
			ELayoutVariant.OUTER, //
			ELayoutVariant.TOPMOST, //
			ELayoutVariant.BOTTOMMOST, //
			ELayoutVariant.LEFTMOST, //
			ELayoutVariant.RIGHTMOST, //
			ELayoutVariant.TOP_LEFT_2_BOTTOM_RIGHT, //
			ELayoutVariant.TOP_RIGHT_2_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.ALTERNATING_V2, //
			ELayoutVariant.ALTERNATING_LEFT_RIGHT, //
			ELayoutVariant.ALTERNATING_TOP_BOTTOM, //
			ELayoutVariant.ALTERNATING_TOP_LEFT_BOTTOM_RIGHT, //
			ELayoutVariant.ALTERNATING_TOP_RIGHT_BOTTOM_LEFT, //
			ELayoutVariant.ALTERNATING_TOP_RIGHT_TOP_LEFT, //
			ELayoutVariant.ALTERNATING_BOTTOM_RIGHT_BOTTOM_LEFT //
	);

	public static List<ELayoutVariant> materialGridVariants = Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.TOPMOST, //
			ELayoutVariant.BOTTOMMOST, //
			ELayoutVariant.TOWER, //
			ELayoutVariant.CENTER //
	);

	public static AbstractRaster getRaster(final ELayout layout, final ELayoutVariant layoutVariant, final int width, final int height, final int patternRadius,
			final float overlap) {
		Log.i("Layout", "Layout = " + layout);
		switch (layout) {
			default:
			case RANDOM:
				return new RandomRaster(width, height, patternRadius, overlap, layoutVariant);
			case GEOMETRIC_GRID:
				return new GeometricRaster(width, height, patternRadius, overlap, layoutVariant);
			case CIRCULAR_GRID:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, CIRCLE_TYPE.CIRCLE);
			case CIRCULAR_GRID_ADJUSTABLE_CENTER:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, CIRCLE_TYPE.CIRCLE_ADJUSTABLE_CENTER);
			case DIAGONAL_GRID:
				return new DiagonalRaster(width, height, patternRadius, overlap, layoutVariant);
			case HALF_CIRCLE_GRID:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, CIRCLE_TYPE.CIRCLE_CENTER_BOTTOM);
			case HEX_GRID:
				return new HexagonalRaster(width, height, patternRadius, overlap, layoutVariant);
			case MATERIAL_GRID:
				return new MaterialRaster(width, height, patternRadius, overlap, layoutVariant);
			case SPIRAL_GRID:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, CIRCLE_TYPE.SPIRAL);
			case SPIRAL_GRID_ADJUSTABLE_CENTER:
				return new CircularRaster(width, height, patternRadius, overlap, layoutVariant, CIRCLE_TYPE.SPIRAL_ADJUSTABLE_CENTER);
		}
	}
}
