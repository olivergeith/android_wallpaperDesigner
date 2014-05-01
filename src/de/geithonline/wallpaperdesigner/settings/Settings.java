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

	public static boolean isDropShadow() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("dropShadow", false);
	}

	public static boolean isRandomizeAlpha() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("randomizeAlpha", false);
	}

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

	private static boolean isGradientBackground() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("gradientBackground", true);
	}

	public static Paint getWallpaperBackgroundPaint(final int width, final int height) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		if (isGradientBackground()) {
			paint.setShader(new LinearGradient(0, 0, 0, height, getBackgroundColor1(), getBackgroundColor2(), Shader.TileMode.MIRROR));
		} else {
			paint.setColor(getBackgroundColor1());
		}
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
			prefs.edit().putInt("color_plain_bgrnd", Color.BLACK).commit();
			prefs.edit().putInt("color2_plain_bgrnd", Color.WHITE).commit();
		}
	}
}
