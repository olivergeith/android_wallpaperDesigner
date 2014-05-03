package de.geithonline.wallpaperdesigner.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import de.geithonline.wallpaperdesigner.R;

public class Settings {
	public static SharedPreferences prefs;
	private static Context context;

	// ###################################################################
	// Options

	public static String getSelectedStyle() {
		if (prefs == null) {
			return "Patterns";
		}
		return prefs.getString("stylePicker", "Patterns");
	}

	public static String getSelectedPattern() {
		if (prefs == null) {
			return "Stars";
		}
		return prefs.getString("patternPicker", "Stars");
	}

	public static boolean isDropShadow() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("dropShadow", true);
	}

	public static boolean isRandomizeDropShadowColors() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("randomizeDropShadowColor", true);
	}

	public static int getDropShadowColor() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt("colorDropShadow", R.integer.COLOR_BLACK);
		return col;
	}

	public static boolean isRandomizeAlpha() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("randomizeAlpha", true);
	}

	public static int getRandomizeAlphaRange() {
		if (prefs == null) {
			return 128;
		}
		return Integer.valueOf(prefs.getString("randomizeAlphaRange", "128"));
	}

	// ###################################################################

	public static boolean isRandomizeColors() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("randomizeColor", false);
	}

	public static int getRandomizeColorRange() {
		if (prefs == null) {
			return 64;
		}
		return Integer.valueOf(prefs.getString("randomizeColorRange", "64"));
	}

	// ###################################################################
	// Wallpater Size

	public static String getSizeSelection() {
		if (prefs == null) {
			return "2560x1600";
		}
		return prefs.getString("sizeSelection", "2560x1600");
	}

	public boolean isCustomSize() {
		final String s = getSizeSelection();
		return s.equals("customSize");
	}

	public static int getWidth() {
		final String s = getSizeSelection();
		switch (s) {
		case "customSize":
			return getBWidth();
		default:
		case "2560x1600":
			return 2560;
		case "1920x1200":
			return 1920;
		case "1920x1080":
			return 1920;
		case "1280x720":
			return 1280;
		case "1024x768":
			return 1024;
		case "960x800":
			return 960;
		case "640x480":
			return 640;
		}
	}

	public static int getBWidth() {
		return Integer.valueOf(prefs.getString("bWidth", "2560"));
	}

	public static int getBHeight() {
		return Integer.valueOf(prefs.getString("bHeight", "1600"));
	}

	public static int getHeight() {
		final String s = getSizeSelection();
		switch (s) {
		case "customSize":
			return getBHeight();
		default:
		case "2560x1600":
			return 1600;
		case "1920x1200":
			return 1200;
		case "1920x1080":
			return 1080;
		case "1280x720":
			return 720;
		case "1024x768":
			return 768;
		case "960x800":
			return 800;
		case "640x480":
			return 480;
		}
	}

	// ###################################################################
	// Background Color
	public static String getGradientDirection() {
		if (prefs == null) {
			return "top-bottom";
		}
		return prefs.getString("gradientDirection", "top-bottom");
	}

	// Background Color
	public static boolean is4ColorGradient(final String test) {
		if (prefs == null) {
			return false;
		}
		return test.equalsIgnoreCase("4-Color Gradient from corners");
	}

	public static int getBackgroundColor1() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt("color_plain_bgrnd", R.integer.COLOR_BLACK);
		return col;
	}

	public static int getBackgroundColor2() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("color2_plain_bgrnd", R.integer.COLOR_WHITE);
		return col;
	}

	public static int getBackgroundColor3() {
		if (prefs == null) {
			return Color.BLUE;
		}
		final int col = prefs.getInt("color3_plain_bgrnd", Color.BLUE);
		return col;
	}

	public static int getBackgroundColor4() {
		if (prefs == null) {
			return Color.YELLOW;
		}
		final int col = prefs.getInt("color4_plain_bgrnd", Color.YELLOW);
		return col;
	}

	public static boolean isDynamicColoring() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("dynamicColoring", true);
	}

	public static DisplayMetrics getDisplayMetrics() {
		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	public static boolean isDebuggingMessages() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("debug2", false);
	}

	public static boolean isDebugging() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("debug", false);
	}

	public static boolean isPremium() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("muimerp", false);
	}

	/**
	 * Initializes some preferences on first run with defaults
	 * 
	 * @param preferences
	 */
	public static void initPrefs(final SharedPreferences preferences, final Context ctx) {
		context = ctx;
		prefs = preferences;
		if (prefs.getBoolean("firstrun", true)) {
			Log.i("GEITH", "FirstRun --> initializing the SharedPreferences with some colors...");
			prefs.edit().putBoolean("firstrun", false).commit();
			// init colors
			prefs.edit().putInt("color_plain_bgrnd", Color.RED).commit();
			prefs.edit().putInt("color2_plain_bgrnd", Color.YELLOW).commit();
			prefs.edit().putInt("color3_plain_bgrnd", Color.GREEN).commit();
			prefs.edit().putInt("color4_plain_bgrnd", Color.BLUE).commit();
		}
	}

}
