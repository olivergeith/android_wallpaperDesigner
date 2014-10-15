package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.HashMap;
import java.util.Map;

public class LayoutManager {
	private static Map<String, IWPStyle> drawer = new HashMap<String, IWPStyle>();

	static {
		drawer.put("Random Layout", new WPStyleRandomPatterns());
		drawer.put("Geometric Layout", new WPStyleGeometricPatterns(false));
		drawer.put("Geometric Random Layout", new WPStyleGeometricPatterns(true));
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
