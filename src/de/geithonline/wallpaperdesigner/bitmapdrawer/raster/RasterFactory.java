package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;

public class RasterFactory {

	private static final Map<String, LayoutProperties> layoutProperties = new HashMap<>();

	private static List<ELayoutVariant> circularVariants = Arrays.asList( //
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

	private static List<ELayoutVariant> geoGridVariants = Arrays.asList( //
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

	private static List<ELayoutVariant> randomGridVariants = Arrays.asList( //
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

	private static List<ELayoutVariant> materialGridVariants = Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.TOPMOST, //
			ELayoutVariant.BOTTOMMOST, //
			ELayoutVariant.TOWER, //
			ELayoutVariant.CENTER //
	);

	static {
		// new LayoutProperties(anzahlPatterns, blurring, overlap, counterClockwise, randomstartwinkel)
		layoutProperties.put("Random Layout", new LayoutProperties(true, true, false, false, false, randomGridVariants));
		layoutProperties.put("Geometric Grid", new LayoutProperties(false, true, true, false, false, geoGridVariants));
		layoutProperties.put("Hex Grid", new LayoutProperties(false, true, true, false, false, geoGridVariants));
		layoutProperties.put("Diagonal Grid", new LayoutProperties(false, true, true, false, false, geoGridVariants));
		layoutProperties.put("Material Grid", new LayoutProperties(false, false, true, false, false, materialGridVariants));
		layoutProperties.put("Circular", new LayoutProperties(false, true, true, true, true, circularVariants));
		layoutProperties.put("Circular Adjustable Center", new LayoutProperties(false, true, true, true, true, true, circularVariants));
		layoutProperties.put("Spiral Adjustable Center", new LayoutProperties(false, true, true, true, true, true, circularVariants));
		layoutProperties.put("Spiral", new LayoutProperties(false, true, true, true, true, circularVariants));
		layoutProperties.put("Half Circle", new LayoutProperties(false, true, true, true, true, circularVariants));
	}

	public static AbstractRaster getRaster(final String layout, final String variante, final int width, final int height, final int patternRadius,
			final float overlap) {

		final String key = layout;
		final ELayoutVariant rPos = ELayoutVariant.getEnumForName(variante);
		Log.i("Layout", "Layout = " + key);
		switch (key) {
			default:
			case "Geometric Grid":
				return new GeometricRaster(width, height, patternRadius, overlap, rPos);
			case "Random Layout":
				return new RandomRaster(width, height, patternRadius, overlap, rPos);

			case "Hex Grid":
				return new HexagonalRaster(width, height, patternRadius, overlap, rPos);

			case "Diagonal Grid":
				return new DiagonalRaster(width, height, patternRadius, overlap, rPos);

			case "Spiral":
				return new CircularRaster(width, height, patternRadius, overlap, rPos, CIRCLE_TYPE.SPIRAL);

			case "Spiral Adjustable Center":
				return new CircularRaster(width, height, patternRadius, overlap, rPos, CIRCLE_TYPE.SPIRAL_ADJUSTABLE_CENTER);

			case "Circular":
				return new CircularRaster(width, height, patternRadius, overlap, rPos, CIRCLE_TYPE.CIRCLE);

			case "Circular Adjustable Center":
				return new CircularRaster(width, height, patternRadius, overlap, rPos, CIRCLE_TYPE.CIRCLE_ADJUSTABLE_CENTER);

			case "Half Circle":
				return new CircularRaster(width, height, patternRadius, overlap, rPos, CIRCLE_TYPE.CIRCLE_CENTER_BOTTOM);

			case "Material Grid":
				return new MaterialRaster(width, height, patternRadius, overlap, rPos);

		}
	}

	public static boolean hasLayoutAnzahlPattern(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasAnzahlPatterns();
	}

	public static boolean hasLayoutBlurring(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasBlurring();
	}

	public static boolean hasLayoutRandomStartwinkel(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasRandomStartWinkel();
	}

	public static boolean hasLayoutOverlap(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasOverlap();
	}

	public static boolean hasCounterClockwise(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasCounterClockwise();
	}

	public static boolean hasLayoutAdjustableCenter(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasAdjustableCenter();
	}

	public static boolean hasLayoutVariants(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasVariants();
	}

	public static CharSequence[] getLayoutVariants(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return null;
		}
		final CharSequence[] variants = p.getVariants();
		return variants;
	}

}
