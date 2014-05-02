package de.geithonline.wallpaperdesigner.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import de.geithonline.wallpaperdesigner.R;

public class Settings {
	public static SharedPreferences prefs;
	private static Context context;

	// ###################################################################
	// Options

	public static boolean isDropShadow() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("dropShadow", true);
	}

	public static boolean isRandomizeAlpha() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("randomizeAlpha", true);
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

	private static int getBackgroundColor1() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt("color_plain_bgrnd", R.integer.COLOR_BLACK);
		return col;
	}

	private static int getBackgroundColor2() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("color2_plain_bgrnd", R.integer.COLOR_WHITE);
		return col;
	}

	public static Paint getBackgroundPaint(final int width, final int height) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(new LinearGradient(0, 0, 0, height, getBackgroundColor1(), getBackgroundColor2(), Shader.TileMode.MIRROR));
		return paint;
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
		}
	}
}
