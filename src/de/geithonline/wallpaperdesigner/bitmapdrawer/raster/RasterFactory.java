package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;

public class RasterFactory {

	private static final Map<String, LayoutProperties> layoutProperties = new HashMap<>();

	static {

		// new LayoutProperties(anzahlPatterns, blurring, overlap, counterClockwise, randomstartwinkel)
		layoutProperties.put("Random Layout", new LayoutProperties(true, true, false, false, false, //
				new CharSequence[] { //
						"Random", //
						"Inner to Outer", "Outer to Inner", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left" }));

		layoutProperties.put("Geometric Grid", new LayoutProperties(false, true, true, false, false, //
				new CharSequence[] { //
						"Random", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"Inner to Outer", "Outer to Inner" }));

		layoutProperties.put("Hex Grid", new LayoutProperties(false, true, true, false, false, //
				new CharSequence[] { //
						"Random", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"Inner to Outer", "Outer to Inner" }));

		layoutProperties.put("Diagonal Grid", new LayoutProperties(false, true, true, false, false, //
				new CharSequence[] { //
						"Random", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"Inner to Outer", "Outer to Inner" }));

		layoutProperties.put("Material Grid", new LayoutProperties(false, true, true, false, false, //
				new CharSequence[] { "Top to Bottom", "Bottom to Top", "Tower", "Center", "Random" }));

		layoutProperties.put("Circular Adjustable Center", new LayoutProperties(false, true, true, true, true, true, //
				new CharSequence[] { //
						"Inner to Outer", "Outer to Inner", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"DuoStep Inner2Outer", "DuoStep Outer2Inner", //
						"Random" }));

		layoutProperties.put("Circular", new LayoutProperties(false, true, true, true, true, //
				new CharSequence[] { //
						"Inner to Outer", "Outer to Inner", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"DuoStep Inner2Outer", "DuoStep Outer2Inner", //
						"Random" }));

		layoutProperties.put("Spiral Adjustable Center", new LayoutProperties(false, true, true, true, true, true, //
				new CharSequence[] { //
						"Inner to Outer", "Outer to Inner", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"DuoStep Inner2Outer", "DuoStep Outer2Inner", //
						"Random" }));

		layoutProperties.put("Spiral", new LayoutProperties(false, true, true, true, true, //
				new CharSequence[] { //
						"Inner to Outer", "Outer to Inner", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"DuoStep Inner2Outer", "DuoStep Outer2Inner", //
						"Random" }));

		layoutProperties.put("Half Circle", new LayoutProperties(false, true, true, true, true, //
				new CharSequence[] { //
						"Inner to Outer", "Outer to Inner", //
						"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
						"Top-Left to Bottom-Right", "Top-Right to Bottom-Left", //
						"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
						"DuoStep Inner2Outer", "DuoStep Outer2Inner", //
						"Random" }));
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
