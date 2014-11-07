package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.HashMap;
import java.util.Map;

import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.RasterFactory.RasterPositioning;

public class LayoutManager {
	private static Map<String, IWPStyle> drawer = new HashMap<String, IWPStyle>();

	static {
		drawer.put("Random Layout", new WPStyleRandomPatterns());

		drawer.put("Geometric Layout (Book)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_BOOK));
		drawer.put("Geometric Layout (Book Reverse)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_BOOK_REVERSE));
		drawer.put("Geometric Layout (Tower)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_TOWER));
		drawer.put("Geometric Layout (Random)", new WPStyleRasteredPatterns(RasterPositioning.GEOMETRIC_RANDOM));

		drawer.put("Circular Layout (Inner to Outer)", new WPStyleRasteredPatterns(RasterPositioning.CIRCULAR_INNER));
		drawer.put("Circular Layout (Outer to Inner)", new WPStyleRasteredPatterns(RasterPositioning.CIRCULAR_OUTER));
		drawer.put("Circular Layout (Random)", new WPStyleRasteredPatterns(RasterPositioning.CIRCULAR_RANDOM));

	}

	public static IWPStyle getDrawer(final String name) {
		IWPStyle d = drawer.get(name);
		if (d == null) {
			d = drawer.get("Random Layout");
		}
		return d;
	}

	public static boolean supportsAnzahlPatterns(final String layout) {
		if (layout.equalsIgnoreCase("Random Layout")) {
			return true;
		}
		return false;
	}

	public static boolean supportsBlurring(final String layout) {
		if (layout.equalsIgnoreCase("Random Layout")) {
			return true;
		}
		return false;
	}

	public static boolean supportsOverLay(final String layout) {
		if (layout.contains("Geometric") || layout.contains("Circular")) {
			return true;
		}
		return false;
	}

}
