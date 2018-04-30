package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;

public class RasterFactory {

	private static final Map<String, LayoutProperties> layoutProperties = new HashMap<>();

	private static CharSequence circularVariants[] = new CharSequence[] { //
			RasterPositioning.INNER.getName(), //
			RasterPositioning.OUTER.getName(), //
			RasterPositioning.TOPMOST.getName(), //
			RasterPositioning.BOTTOMMOST.getName(), //
			RasterPositioning.LEFTMOST.getName(), //
			RasterPositioning.RIGHTMOST.getName(), //
			RasterPositioning.TOP_LEFT_2_BOTTOM_RIGHT.getName(), //
			RasterPositioning.TOP_RIGHT_2_BOTTOM_LEFT.getName(), //
			RasterPositioning.ALTERNATING.getName(), //
			RasterPositioning.ALTERNATING_TOP_LEFT_BOTTOM_RIGHT.getName(), //
			RasterPositioning.ALTERNATING_TOP_RIGHT_BOTTOM_LEFT.getName(), //
			RasterPositioning.CENTER.getName(), //
			RasterPositioning.DUO_CENTER.getName(), //
			RasterPositioning.TOWER.getName(), //
			RasterPositioning.TRISTEP.getName(), //
			RasterPositioning.QUADSTEP.getName(), //
			RasterPositioning.DUO_STEP_INNER_2_OUTER.getName(), //
			RasterPositioning.DUO_STEP_OUTER_2_INNER.getName(), //
			RasterPositioning.RANDOM.getName() //
	};

	private static CharSequence geoGridVariants[] = new CharSequence[] { //
			RasterPositioning.RANDOM.getName(), //
			RasterPositioning.INNER.getName(), //
			RasterPositioning.OUTER.getName(), //
			RasterPositioning.TOPMOST.getName(), //
			RasterPositioning.BOTTOMMOST.getName(), //
			RasterPositioning.LEFTMOST.getName(), //
			RasterPositioning.RIGHTMOST.getName(), //
			RasterPositioning.TOP_LEFT_2_BOTTOM_RIGHT.getName(), //
			RasterPositioning.TOP_RIGHT_2_BOTTOM_LEFT.getName(), //
			RasterPositioning.ALTERNATING.getName(), //
			RasterPositioning.ALTERNATING_TOP_LEFT_BOTTOM_RIGHT.getName(), //
			RasterPositioning.ALTERNATING_TOP_RIGHT_BOTTOM_LEFT.getName(), //
			RasterPositioning.CENTER.getName(), //
			RasterPositioning.DUO_CENTER.getName(), //
			RasterPositioning.TOWER.getName(), //
			RasterPositioning.TRISTEP.getName(), //
			RasterPositioning.QUADSTEP.getName() //
	};

	private static CharSequence randomGridVariants[] = new CharSequence[] { //
			RasterPositioning.RANDOM.getName(), //
			RasterPositioning.INNER.getName(), //
			RasterPositioning.OUTER.getName(), //
			RasterPositioning.TOPMOST.getName(), //
			RasterPositioning.BOTTOMMOST.getName(), //
			RasterPositioning.LEFTMOST.getName(), //
			RasterPositioning.RIGHTMOST.getName(), //
			RasterPositioning.TOP_LEFT_2_BOTTOM_RIGHT.getName(), //
			RasterPositioning.TOP_RIGHT_2_BOTTOM_LEFT.getName(), //
			RasterPositioning.ALTERNATING.getName(), //
			RasterPositioning.ALTERNATING_TOP_LEFT_BOTTOM_RIGHT.getName(), //
			RasterPositioning.ALTERNATING_TOP_RIGHT_BOTTOM_LEFT.getName() //
	};

	private static CharSequence materialGridVariants[] = new CharSequence[] { //
			RasterPositioning.RANDOM.getName(), //
			RasterPositioning.TOPMOST.getName(), //
			RasterPositioning.BOTTOMMOST.getName(), //
			RasterPositioning.TOWER.getName(), //
			RasterPositioning.CENTER.getName() //
	};

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
		final RasterPositioning rPos = RasterPositioning.getEnumForName(variante);
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
