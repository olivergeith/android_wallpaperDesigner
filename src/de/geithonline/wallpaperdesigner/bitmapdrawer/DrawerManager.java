package de.geithonline.wallpaperdesigner.bitmapdrawer;

import java.util.HashMap;
import java.util.Map;

public class DrawerManager {
	private static Map<String, IDrawer> drawer = new HashMap<String, IDrawer>();

	static {
		drawer.put("Bubbles", new Bubbles());
	}

	public static IDrawer getDrawer(final String name) {
		IDrawer d = drawer.get(name);
		if (d == null) {
			d = drawer.get("Bubbles");
		}
		return d;
	}
}
