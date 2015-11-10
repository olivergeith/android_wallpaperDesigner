package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.CircularRaster.CIRCLE_TYPE;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class RasterFactory {

	private static final Map<String, LayoutProperties> layoutProperties = new HashMap<String, LayoutProperties>();

	static {

		// new LayoutProperties(anzahlPatterns, blurring, overlap, upsideDown, randomstartwinkel)
		layoutProperties.put("Random Layout", new LayoutProperties(true, true, false, false, false, //
				new CharSequence[] { "Random" }));
		layoutProperties.put("Geometric Grid", new LayoutProperties(false, true, true, true, false, //
				new CharSequence[] { "Book", "Book Reverse", "Tower", "Center", "Random" }));

		layoutProperties.put("Hex Grid", new LayoutProperties(false, true, true, true, false, //
				new CharSequence[] { "Book", "Book Reverse", "Tower", "Center", "Random" }));

		layoutProperties.put("Diagonal Grid", new LayoutProperties(false, true, true, true, false, //
				new CharSequence[] { "Book", "Book Reverse", "Tower", "Center", "Random" }));

		layoutProperties.put("Material Grid", new LayoutProperties(false, true, true, false, false, //
				new CharSequence[] { "Book", "Book Reverse", "Tower", "Center", "Random" }));
		layoutProperties.put("Circular", new LayoutProperties(false, true, true, false, true, //
				new CharSequence[] { "Inner to Outer", "Outer to Inner", "Top to Bottom", "Left to Right", "Center", "Tower", "Random" }));
		layoutProperties.put("Spiral", new LayoutProperties(false, true, true, false, true, //
				new CharSequence[] { "Inner to Outer", "Outer to Inner", "Top to Bottom", "Left to Right", "Center", "Tower", "Random" }));

		layoutProperties.put("Half Circle", new LayoutProperties(false, true, true, false, true, //
				new CharSequence[] { "Inner to Outer", "Outer to Inner", "Top to Bottom", "Left to Right", "Center", "Tower", "Random" }));
	}

	public static AbstractRaster getRaster(final String layout, final String variante, final int width, final int height, final int patternRadius,
			final float overlap) {
		final String key = layout + " (" + variante + ")";
		Log.i("Layout", "Layout = " + key);
		switch (key) {
		default:
		case "Geometric Grid (Random)":
			return new GeometricRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM, Settings.isUpsideDown());
		case "Geometric Grid (Book)":
			return new GeometricRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK, Settings.isUpsideDown());
		case "Geometric Grid (Book Reverse)":
			return new GeometricRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK_REVERSE, Settings.isUpsideDown());
		case "Geometric Grid (Tower)":
			return new GeometricRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER, Settings.isUpsideDown());
		case "Geometric Grid (Center)":
			return new GeometricRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER, Settings.isUpsideDown());

		case "Random Layout":
		case "Random Layout (None)":
		case "Random Layout (Random)":
			return new RandomRaster(width, height, patternRadius, overlap);

		case "Hex Grid (Random)":
			return new HexagonalRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM, Settings.isUpsideDown());
		case "Hex Grid (Book)":
			return new HexagonalRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK, Settings.isUpsideDown());
		case "Hex Grid (Book Reverse)":
			return new HexagonalRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK_REVERSE, Settings.isUpsideDown());
		case "Hex Grid (Tower)":
			return new HexagonalRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER, Settings.isUpsideDown());
		case "Hex Grid (Center)":
			return new HexagonalRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER, Settings.isUpsideDown());

		case "Diagonal Grid (Random)":
			return new DiagonalRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM, Settings.isUpsideDown());
		case "Diagonal Grid (Book)":
			return new DiagonalRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK, Settings.isUpsideDown());
		case "Diagonal Grid (Book Reverse)":
			return new DiagonalRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK_REVERSE, Settings.isUpsideDown());
		case "Diagonal Grid (Tower)":
			return new DiagonalRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER, Settings.isUpsideDown());
		case "Diagonal Grid (Center)":
			return new DiagonalRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER, Settings.isUpsideDown());

		case "Spiral (Random)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM, CIRCLE_TYPE.SPIRAL);
		case "Spiral (Outer to Inner)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.OUTER, CIRCLE_TYPE.SPIRAL);
		case "Spiral (Inner to Outer)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.INNER, CIRCLE_TYPE.SPIRAL);
		case "Spiral (Top to Bottom)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.TOPMOST, CIRCLE_TYPE.SPIRAL);
		case "Spiral (Left to Right)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.LEFTMOST, CIRCLE_TYPE.SPIRAL);
		case "Spiral (Center)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER, CIRCLE_TYPE.SPIRAL);
		case "Spiral (Tower)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER, CIRCLE_TYPE.SPIRAL);

		case "Circular (Random)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case "Circular (Inner to Outer)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.INNER, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case "Circular (Outer to Inner)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.OUTER, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case "Circular (Top to Bottom)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.TOPMOST, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case "Circular (Left to Right)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.LEFTMOST, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case "Circular (Center)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);
		case "Circular (Tower)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER, CIRCLE_TYPE.FULL_RANDOM_STARWINKEL);

		case "Half Circle (Random)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM, CIRCLE_TYPE.HALF);
		case "Half Circle (Inner to Outer)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.INNER, CIRCLE_TYPE.HALF);
		case "Half Circle (Outer to Inner)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.OUTER, CIRCLE_TYPE.HALF);
		case "Half Circle (Top to Bottom)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.TOPMOST, CIRCLE_TYPE.HALF);
		case "Half Circle (Left to Right)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.LEFTMOST, CIRCLE_TYPE.HALF);
		case "Half Circle (Center)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER, CIRCLE_TYPE.HALF);
		case "Half Circle (TOWER)":
			return new CircularRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER, CIRCLE_TYPE.HALF);

		case "Material Grid (Book)":
			return new MaterialRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK);
		case "Material Grid (Book Reverse)":
			return new MaterialRaster(width, height, patternRadius, overlap, RasterPositioning.BOOK_REVERSE);
		case "Material Grid (Random)":
			return new MaterialRaster(width, height, patternRadius, overlap, RasterPositioning.RANDOM);
		case "Material Grid (Tower)":
			return new MaterialRaster(width, height, patternRadius, overlap, RasterPositioning.TOWER);
		case "Material Grid (Center)":
			return new MaterialRaster(width, height, patternRadius, overlap, RasterPositioning.CENTER);

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
