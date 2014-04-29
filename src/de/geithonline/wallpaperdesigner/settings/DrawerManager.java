package de.geithonline.wallpaperdesigner.settings;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import de.geithonline.wallpaperdesigner.bitmapdrawer.BitmapDrawerLogoV1;
import de.geithonline.wallpaperdesigner.bitmapdrawer.BitmapDrawerLogoV2;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBitmapDrawer;

public class DrawerManager {
	private static Map<String, IBitmapDrawer> drawer = new HashMap<String, IBitmapDrawer>();
	private static Map<String, Bitmap> iconCache = new HashMap<String, Bitmap>();

	static {
		drawer.put("LogoV1", new BitmapDrawerLogoV1());
		drawer.put("LogoV2", new BitmapDrawerLogoV2());
	}

	public static IBitmapDrawer getDrawer(final String name) {
		IBitmapDrawer d = drawer.get(name);
		if (d == null) {
			d = drawer.get("LogoV1");
		}
		return d;
	}

	public static Bitmap getIconForDrawer(final String name, final int size) {
		Bitmap b = iconCache.get(name);
		if (b == null) {
			final IBitmapDrawer drawer = getDrawer(name);
			b = drawer.drawIcon(66, size);
			iconCache.put(name, b);
		}
		return b;
	}

}
