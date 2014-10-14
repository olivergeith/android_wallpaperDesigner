package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.HashMap;
import java.util.Map;

public class StyleManager {
	private static Map<String, IWPStyle> drawer = new HashMap<String, IWPStyle>();

	static {
		drawer.put("Patterns", new WPStyleRandomPatterns());
		drawer.put("GeometricPatterns", new WPStyleGeometricPatterns());
	}

	public static IWPStyle getDrawer(final String name) {
		IWPStyle d = drawer.get(name);
		if (d == null) {
			d = drawer.get("Patterns");
		}
		return d;
	}
}
