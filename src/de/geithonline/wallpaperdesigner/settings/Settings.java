package de.geithonline.wallpaperdesigner.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class Settings {
	public static final String NUMBER_OF_LEAFS = "numberOfLeafs";
	public static final String RANDOM_LEAF_COUNT = "randomLeafCount";
	public static final String PATTERN_DROPSHADOW_DARKNESS_ADJUST = "dropShadowDarknessAdjust";
	public static final String PATTERN_OUTLINE_DARKNESS_ADJUST = "outlineDarknessAdjust";
	public static final String PATTERN_OUTLINE_NEVER_TRANSPARENT = "outlineNeverTransparent";
	public static final String PATTERN_TEXT = "textPattern";
	public static final String PATTERN_TEXT_DRAW_STYLE = "textDrawStyle";
	public static final String PATTERN_FILLED_OPTION = "filledOption";
	public static final String PATTERN_RANDOM_ROTATE = "randomRotate";
	public static final String PATTERN_OUTLINE = "outline";
	public static final String PATTERN_GLOSSY = "glossy";
	public static final String PATTERN_PATTERN_PICKER = "pattern_patternPicker";
	public static final String PATTERN_PATTERN_VARIANT_PICKER = "pattern_patternVariantPicker";
	public static final String PATTERN_ANZAHL_PATTERNS = "anzahlPatterns";
	public static final String PATTERN_DROPSHADOW_TYPE = "pattern_dropShadowType";
	public static final String PATTERN_DROPSHADOW_COLOR = "colorDropShadow";
	public static final String PATTERN_BLUR = "blurPatterns";
	public static SharedPreferences prefs;
	private static Context context;

	// ###################################################################
	// Other Stuff
	public static SORT_ORDER getSortOrderForSavedSettings() {
		// return "Random Layout";
		final String sort = getSortOrder();
		switch (sort) {
		default:
		case "Last Modified":
			return SORT_ORDER.LAST_MODIFIED;
		case "Last Modified (Descending)":
			return SORT_ORDER.LAST_MODIFIED_DESCENDING;
		case "Alphabetically":
			return SORT_ORDER.ALPHA;
		}
	}

	public static String getSortOrder() {
		// return "Random Layout";
		if (prefs == null) {
			return "Last Modified";
		}
		final String sort = prefs.getString("sortOrder", "Last Modified");
		return sort;
	}

	// ###################################################################
	// Options Layout Selection

	public static String getSelectedMainLayout() {
		// return "Random Layout";
		if (prefs == null) {
			return "Random Layout";
		}
		return prefs.getString("mainlayouts", "Random Layout");
	}

	public static String getSelectedMainLayoutVariante() {
		// return "Random Layout";
		if (prefs == null) {
			return "None";
		}
		return prefs.getString("mainlayoutVariants", "None");
	}

	public static int getAnzahlPatterns() {
		if (prefs == null) {
			return 1000;
		}
		if (isDebugging()) {
			return 0;
		}
		return prefs.getInt(PATTERN_ANZAHL_PATTERNS, 1000);
	}

	public static float getOverlapping() {
		if (prefs == null) {
			return 0.5f;
		}
		return prefs.getInt("overlapping", 50) / 100f;
	}

	public static boolean isBlurPatterns() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(PATTERN_BLUR, false);
	}

	public static boolean isUpsideDown() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("upsideDown", false);
	}

	// ###################################################################
	// Options Pattern Style

	public static String getSelectedPattern() {
		if (prefs == null) {
			return "Virus Attack";
		}
		return prefs.getString(PATTERN_PATTERN_PICKER, "Virus Attack");
	}

	public static String getSelectedPatternVariant() {
		if (prefs == null) {
			return "none";
		}
		return prefs.getString(PATTERN_PATTERN_VARIANT_PICKER, "V1");
	}

	public static float getPatternSizeFactor() {
		if (prefs == null) {
			return 1.0f;
		}
		return prefs.getInt("patternSizeAdjust", 100) / 100f;
	}

	public static float getPatternMinSizeFactor() {
		if (prefs == null) {
			return 0.1f;
		}
		return prefs.getInt("patternMinSizeAdjust", 10) / 100f;
	}

	public static String getFilledOption() {
		if (prefs == null) {
			return "Not filled";
		}
		return prefs.getString(PATTERN_FILLED_OPTION, "Not filled");
	}

	// ###################################################################
	// Rotating

	public static String getRotationStyle() {
		if (prefs == null) {
			return "Fixed";
		}
		return prefs.getString("rotatingStyle", "Fixed");
	}

	public static boolean isRandomRotate() {
		return getRotationStyle().equals("Random");
	}

	@Deprecated
	public static int getRotationDegrees(final int randomMin, final int randomMax) {
		if (isRandomRotate()) {
			return Randomizer.getRandomInt(randomMin - 1, randomMax);
		}
		return getFixedRotationDegrees();
	}

	public static int getFixedRotationDegrees() {
		if (prefs == null) {
			return 0;
		}
		return prefs.getInt("rotationDegrees", 0);
	}

	// ###################################################################
	// Special Settings

	public static boolean isGlossy() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(PATTERN_GLOSSY, false);
	}

	public static boolean isOutline() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean(PATTERN_OUTLINE, true);
	}

	public static boolean isOutlineNeverTransparent() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(PATTERN_OUTLINE_NEVER_TRANSPARENT, false);
	}

	public static boolean isCustomOutlineColor() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("customOutlineColor", false);
	}

	public static int getCustomOutlineColor() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt("colorOutline", R.integer.COLOR_BLACK);
		return col;
	}

	public static int getOutlineDarkness() {
		if (prefs == null) {
			return -48;
		}
		return prefs.getInt(PATTERN_OUTLINE_DARKNESS_ADJUST, -48);
	}

	public static int getDropShadowDarkness() {
		if (prefs == null) {
			return -48;
		}
		return prefs.getInt(PATTERN_DROPSHADOW_DARKNESS_ADJUST, -48);
	}

	public static String getText() {
		if (prefs == null) {
			return "The Wallpaper Designer";
		}
		return prefs.getString(PATTERN_TEXT, "The Wallpaper Designer");
	}

	public static String getTextDrawStyle() {
		if (prefs == null) {
			return "Round";
		}
		return prefs.getString(PATTERN_TEXT_DRAW_STYLE, "Round");
	}

	// ###################################################################
	// Options Pattern Style (DropShadow)
	public static String getDropShadowType() {
		if (prefs == null) {
			return "Darker";
		}
		return prefs.getString(PATTERN_DROPSHADOW_TYPE, "Darker");
	}

	public static boolean isDropShadow() {
		return !getDropShadowType().equals("No");
	}

	public static int getDropShadowColor() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt("colorDropShadow", R.integer.COLOR_BLACK);
		return col;
	}

	public static float getDropShadowRadiusAdjustment() {
		if (prefs == null) {
			return 1f;
		}
		return prefs.getInt("dropShadowRadiusAdjustment", 100) / 100f;
	}

	// ###################################################################
	// Options Pattern Style (Alpha)
	// public static boolean isRandomizeAlpha() {
	// return getRandomizeAlphaRange() != 0;
	// }
	//
	// public static int getRandomizeAlphaRange() {
	// if (prefs == null) {
	// return 128;
	// }
	// return prefs.getInt("randomizeAlphaRangeInt", 128);
	// }

	public static int getMinOpacity() {
		if (prefs == null) {
			return 225;
		}
		return prefs.getInt("minOpacity", 225);
	}

	public static int getMaxOpacity() {
		if (prefs == null) {
			return 255;
		}
		return prefs.getInt("maxOpacity", 255);
	}

	// ###################################################################
	// Options Pattern Style (Color)
	public static boolean isRandomizeColors() {
		return getRandomizeColorRange() != 0;
	}

	public static int getRandomizeColorRange() {
		if (prefs == null) {
			return 12;
		}
		return prefs.getInt("randomizeColorRangeInt", 12);
	}

	public static boolean isRandomizeBrightness() {
		return getRandomizeColorBrighnessRange() != 0;
	}

	public static int getRandomizeColorBrighnessRange() {
		if (prefs == null) {
			return 12;
		}
		return prefs.getInt("randomizeColorBrightnessRangeInt", 12);
	}

	// ###################################################################
	// Wallpater Size
	public static int getAnzahlFlowerLeafs(final int randomMin, final int randomMax) {
		if (isRandomLeafCount()) {
			return Randomizer.getRandomInt(randomMin - 1, randomMax);
		}
		if (prefs == null) {
			return 5;
		}
		return prefs.getInt(NUMBER_OF_LEAFS, 5);
	}

	private static boolean isRandomLeafCount() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(RANDOM_LEAF_COUNT, false);
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
			return "4-Color Gradient from corners";
		}
		return prefs.getString("gradientDirection", "4-Color Gradient from corners");
	}

	public static int getAnzahlGradientColors() {
		if (prefs == null) {
			return 4;
		}
		return Integer.parseInt(prefs.getString("anzColors", "4"));
	}

	// Background Color
	public static boolean is4ColorGradient(final String test) {
		if (prefs == null) {
			return false;
		}
		return test.equalsIgnoreCase("4-Color Gradient from corners") //
				|| test.equalsIgnoreCase("4-Colors in corners")//
				|| test.equalsIgnoreCase("4 Color Sweep Gradient");
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
			return false;
		}
		return prefs.getBoolean("dynamicColoring", false);
	}

	// ###################################################################
	// General stuff
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
			prefs.edit().putInt("color_plain_bgrnd", 0xff820000).commit();
			prefs.edit().putInt("color2_plain_bgrnd", 0xfff2e518).commit();
			prefs.edit().putInt("color3_plain_bgrnd", 0xff008200).commit();
			prefs.edit().putInt("color4_plain_bgrnd", 0xff014f81).commit();
			prefs.edit().putString("gradientDirection", "4-Color Gradient from corners").commit();
			prefs.edit().putBoolean("dynamicColoring", false).commit();

			prefs.edit().putString("sortOrder", "Last Modified").commit();
			prefs.edit().putString("stylePicker", "Patterns").commit();
			prefs.edit().putString(PATTERN_PATTERN_PICKER, "Virus Attack").commit();
			prefs.edit().putInt(PATTERN_ANZAHL_PATTERNS, 1000).commit();

			prefs.edit().putBoolean(PATTERN_BLUR, false).commit();
			prefs.edit().putBoolean("dropShadow", true).commit();
			prefs.edit().putBoolean("randomizeDropShadowColor", true).commit();
			prefs.edit().putInt("colorDropShadow", Color.BLACK).commit();

			prefs.edit().putString("bWidth", "2560").commit();
			prefs.edit().putString("bHeight", "1600").commit();
			prefs.edit().putString("sizeSelection", "2560x1600").commit();

			prefs.edit().putInt("randomizeColorRangeInt", 12).commit();
			prefs.edit().putInt("randomizeColorBrightnessRangeInt", 12).commit();
			prefs.edit().putInt("minOpacity", 225).commit();
			prefs.edit().putInt("maxOpacity", 255).commit();
			prefs.edit().putInt("dropShadowRadiusAdjustment", 100).commit();

			prefs.edit().putInt(PATTERN_DROPSHADOW_DARKNESS_ADJUST, -88).commit();
			prefs.edit().putInt(PATTERN_OUTLINE_DARKNESS_ADJUST, +40).commit();

			prefs.edit().putInt("colorOutline", Color.BLACK).commit();
			prefs.edit().putBoolean(PATTERN_OUTLINE, true).commit();
			prefs.edit().putString("rotatingStyle", "Fixed").commit();

		}
	}

}
