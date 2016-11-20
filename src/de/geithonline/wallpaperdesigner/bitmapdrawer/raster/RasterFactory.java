package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class RasterFactory {

	private static final Map<String, LayoutProperties> layoutProperties = new HashMap<>();

	static {

		// new LayoutProperties(anzahlPatterns, blurring, overlap, upsideDown,
		// randomstartwinkel)
		layoutProperties.put("Random Layout", new LayoutProperties(true, true, false, false, false, //
				new CharSequence[] { "Random", "Inner to Outer", "Outer to Inner", "Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", }));
		layoutProperties.put("Geometric Grid", //
				new LayoutProperties(false, true, true, true, false, //
						new CharSequence[] { "Random", "Book", "Book Reverse", //
								"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
								"Inner to Outer", "Outer to Inner" }));

		layoutProperties.put("Hex Grid",
				new LayoutProperties(false, true, true, true, false, //
						new CharSequence[] { "Random", "Book", "Book Reverse", //
								"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
								"Inner to Outer", "Outer to Inner" }));

		layoutProperties.put("Diagonal Grid",
				new LayoutProperties(false, true, true, true, false, //
						new CharSequence[] { "Random", "Book", "Book Reverse", //
								"Center", "DuoCenter", "Tower", "TriStep", "QuadStep", //
								"Inner to Outer", "Outer to Inner" }));

		layoutProperties.put("Material Grid", new LayoutProperties(false, true, true, false, false, //
				new CharSequence[] { "Book", "Book Reverse", "Tower", "Center", "Random" }));
		layoutProperties.put("Circular Adjustable Center",
				new LayoutProperties(false, true, true, false, true, true, //
						new CharSequence[] { "Inner to Outer", "Outer to Inner", //
								"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
								"DuoCenter", "Center", //
								"Tower", "TriStep", "QuadStep", "Random" }));
		layoutProperties.put("Circular",
				new LayoutProperties(false, true, true, false, true, //
						new CharSequence[] { "Inner to Outer", "Outer to Inner", //
								"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
								"DuoCenter", "Center", //
								"Tower", "TriStep", "QuadStep", "Random" }));

		layoutProperties.put("Spiral Adjustable Center",
				new LayoutProperties(false, true, true, true, true, true, //
						new CharSequence[] { "Inner to Outer", "Outer to Inner", //
								"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
								"DuoCenter", "Center", //
								"Tower", "TriStep", "QuadStep", "Random" }));

		layoutProperties.put("Spiral",
				new LayoutProperties(false, true, true, true, true, //
						new CharSequence[] { "Inner to Outer", "Outer to Inner", //
								"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
								"DuoCenter", "Center", //
								"Tower", "TriStep", "QuadStep", "Random" }));

		layoutProperties.put("Half Circle",
				new LayoutProperties(false, true, true, false, true, //
						new CharSequence[] { "Inner to Outer", "Outer to Inner", //
								"Top to Bottom", "Bottom to Top", "Left to Right", "Right to Left", //
								"Center", //
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
			return new GeometricRaster(width, height, patternRadius, overlap, rPos, Settings.isUpsideDown());
		case "Random Layout":
			return new RandomRaster(width, height, patternRadius, overlap, rPos);

		case "Hex Grid":
			return new HexagonalRaster(width, height, patternRadius, overlap, rPos, Settings.isUpsideDown());

		case "Diagonal Grid":
			return new DiagonalRaster(width, height, patternRadius, overlap, rPos, Settings.isUpsideDown());

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

	public static boolean hasLayoutUpsideDown(final String layout) {
		final LayoutProperties p = layoutProperties.get(layout);
		if (p == null) {
			return false;
		}
		return p.hasUpsideDown();
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
