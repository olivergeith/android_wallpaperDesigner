package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.HashMap;
import java.util.Map;

import de.geithonline.wallpaperdesigner.bitmapdrawer.GeometricRaster.POSITIONING;

public class LayoutManager {
	private static Map<String, IWPStyle> drawer = new HashMap<String, IWPStyle>();

	static {
		drawer.put("Random Layout", new WPStyleRandomPatterns());
		drawer.put("Geometric Layout (Book)", new WPStyleGeometricPatterns(POSITIONING.BOOK));
		drawer.put("Geometric Layout (Book Reverse)", new WPStyleGeometricPatterns(POSITIONING.BOOK_REVERSE));
		drawer.put("Geometric Layout (Tower)", new WPStyleGeometricPatterns(POSITIONING.TOWER));
		drawer.put("Geometric Layout (Random)", new WPStyleGeometricPatterns(POSITIONING.RANDOM));
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
		if (layout.contains("Geometric")) {
			return true;
		}
		return false;
	}

}
