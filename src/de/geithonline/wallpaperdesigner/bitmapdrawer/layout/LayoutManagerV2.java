package de.geithonline.wallpaperdesigner.bitmapdrawer.layout;

import java.util.HashMap;
import java.util.Map;

import de.geithonline.wallpaperdesigner.bitmapdrawer.IWPStyle;
import de.geithonline.wallpaperdesigner.bitmapdrawer.WPStyleRandomPatterns;
import de.geithonline.wallpaperdesigner.bitmapdrawer.WPStyleRasteredPatterns;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory.RasterPositioning;

public class LayoutManagerV2 {

	private static Map<String, IWPStyle> drawer = new HashMap<String, IWPStyle>();

	private static final Map<String, LayoutProperties> layoutProperties = new HashMap<String, LayoutProperties>();
	static {
		drawer.put("Random Layout", new WPStyleRandomPatterns());
		drawer.put("Geometric Grid (Book)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_BOOK));
		drawer.put("Geometric Grid (Book Reverse)", new WPStyleRasteredPatterns(
				RasterPositioning.GEOMETRIC_BOOK_REVERSE));
		drawer.put("Geometric Grid (Tower)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_TOWER));
		drawer.put("Geometric Grid (Center)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_CENTER));
		drawer.put("Geometric Grid (Random)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_RANDOM));
		drawer.put("Circular (Inner to Outer)", new WPStyleRasteredPatterns(RasterPositioning.CIRCULAR_INNER));
		drawer.put("Circular (Outer to Inner)", new WPStyleRasteredPatterns(RasterPositioning.CIRCULAR_OUTER));
		drawer.put("Circular (Random)", new WPStyleRasteredPatterns(RasterPositioning.CIRCULAR_RANDOM));
		drawer.put("Half Circle (Inner to Outer)", new WPStyleRasteredPatterns(RasterPositioning.HALF_CIRCULAR_INNER));
		drawer.put("Half Circle (Outer to Inner)", new WPStyleRasteredPatterns(RasterPositioning.HALF_CIRCULAR_OUTER));
		drawer.put("Half Circle (Random)", new WPStyleRasteredPatterns(RasterPositioning.HALF_CIRCULAR_RANDOM));
		drawer.put("Material Grid (Book)", new WPStyleRasteredPatterns(RasterPositioning.MATERIAL_BOOK));
		drawer.put("Material Grid (Book Reverse)", new WPStyleRasteredPatterns(RasterPositioning.MATERIAL_BOOK_REVERSE));
		drawer.put("Material Grid (Tower)", new WPStyleRasteredPatterns(RasterPositioning.MATERIAL_TOWER));
		drawer.put("Material Grid (Random)", new WPStyleRasteredPatterns(RasterPositioning.MATERIAL_RANDOM));
		drawer.put("Material Grid (Center)", new WPStyleRasteredPatterns(RasterPositioning.MATERIAL_CENTER));

		// new LayoutProperties(anzahlPatterns, blurring, overlap, upsideDown)
		layoutProperties.put("Random Layout", new LayoutProperties(true, true, false, false, //
				null));
		layoutProperties.put("Geometric Grid", new LayoutProperties(false, false, true, true, //
				new CharSequence[] { "Book", "Book Reverse", "Tower", "Center", "Random" }));

		layoutProperties.put("Material Grid", new LayoutProperties(false, false, true, false, //
				new CharSequence[] { "Tower", "Random", "Center" }));
		layoutProperties.put("Circular", new LayoutProperties(false, false, true, true, //
				new CharSequence[] { "Inner to Outer", "Outer to Inner", "Random" }));

		layoutProperties.put("Half Circle", new LayoutProperties(false, false, true, true, //
				new CharSequence[] { "Inner to Outer", "Outer to Inner", "Random" }));
	}

	public static IWPStyle getDrawer(final String layout, final String variante) {
		String key;
		if (variante == null) {
			key = layout;
		} else {
			key = layout + " (" + variante + ")";
		}
		IWPStyle d = drawer.get(key);
		if (d == null) {
			d = drawer.get("Random Layout");
		}
		return d;
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
