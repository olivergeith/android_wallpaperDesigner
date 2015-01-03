package de.geithonline.wallpaperdesigner.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class Settings {
	public static final String KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT = "randomizeColorBrightnessRangeInt";
	public static final String KEY_RANDOMIZE_COLOR_RANGE_INT = "randomizeColorRangeInt";
	private static final String KEY_RENDER_ON_SETTINGS_EXIT = "renderOnSettingsExit";
	public static final String KEY_COLORS_ANZAHL = "anzColors";
	public static final String KEY_COLOR_GRADIENT_DIRECTION = "gradientDirection";
	public static final String KEY_COLOR4 = "color4_plain_bgrnd";
	public static final String KEY_COLOR3 = "color3_plain_bgrnd";
	public static final String KEY_COLOR2 = "color2_plain_bgrnd";
	public static final String KEY_COLOR1 = "color_plain_bgrnd";
	public static final String KEY_GLOSSY_REFLECTION_BRIGHTNESS = "glossyReflectionBrightness";
	public static final String KEY_GLOSSY_CENTER_GLOW_OUTER_DARKNESS = "glossyCenterGlowOuterDarkness";
	public static final String KEY_GLOSSY_CENTER_GLOW_BRIGHTNESS = "glossyCenterGlowBrightness";
	public static final String KEY_MUIMERP = "muimerp";
	public static final String KEY_HEX_VALUES = "hexValues";
	public static final String KEY_APP_THEME = "appTheme";
	public static final String KEY_MAINLAYOUT_VARIANTS = "mainlayoutVariants";
	public static final String KEY_MAINLAYOUTS = "mainlayouts";
	public static final String KEY_SORT_ORDER = "sortOrder";
	public static final String KEY_IMAGE_FORMAT = "imageFormat";
	public static final String KEY_JPG_COMPRESSION = "jpgCompression";
	public static final String KEY_SHARE_SUBJECT = "shareSubject";
	public static final String KEY_SHARE_TEXT = "shareText";
	public static final String KEY_NUMBER_OF_LEAFS = "numberOfLeafs";
	public static final String KEY_RANDOM_LEAF_COUNT = "randomLeafCount";
	public static final String KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST = "dropShadowDarknessAdjust";
	public static final String KEY_PATTERN_OUTLINE_DARKNESS_ADJUST = "outlineDarknessAdjust";
	public static final String KEY_PATTERN_OUTLINE_NEVER_TRANSPARENT = "outlineNeverTransparent";
	public static final String KEY_PATTERN_TEXT = "textPattern";
	public static final String KEY_PATTERN_TEXT_DRAW_STYLE = "textDrawStyle";
	public static final String KEY_PATTERN_FILLED_OPTION = "filledOption";
	public static final String KEY_PATTERN_RANDOM_ROTATE = "randomRotate";
	public static final String KEY_PATTERN_OUTLINE = "outline";
	public static final String KEY_PATTERN_GLOSSY = "glossy";
	public static final String KEY_PATTERN_PATTERN_PICKER = "pattern_patternPicker";
	public static final String KEY_PATTERN_PATTERN_VARIANT_PICKER = "pattern_patternVariantPicker";
	public static final String KEY_PATTERN_ANZAHL_PATTERNS = "anzahlPatterns";
	public static final String KEY_PATTERN_DROPSHADOW_TYPE = "pattern_dropShadowType";
	public static final String KEY_PATTERN_DROPSHADOW_COLOR = "colorDropShadow";
	public static final String KEY_PATTERN_BLUR = "blurPatterns";
	public static SharedPreferences prefs;

	// private static Context context;

	public enum IMAGE_OUTPUT_FORMAT {
		PNG, JPG;
	}

	public static final String DEFAULT_SHARE_SUBJECT = "Shared from the Wallpaper Designer";
	public static final String DEFAULT_SHARE_TEXT = "Created with 'The Wallpaper Designer' : https://play.google.com/store/apps/details?id=de.geithonline.wallpaperdesigner";

	public static String getShareText() {
		if (prefs == null) {
			return DEFAULT_SHARE_TEXT;
		}
		String p = prefs.getString(KEY_SHARE_TEXT, DEFAULT_SHARE_TEXT);
		if (p.isEmpty()) {
			p = DEFAULT_SHARE_TEXT;
			Settings.prefs.edit().putString(KEY_SHARE_TEXT, Settings.DEFAULT_SHARE_TEXT).apply();
		}
		return p;
	}

	public static String getShareSubject() {
		if (prefs == null) {
			return DEFAULT_SHARE_SUBJECT;
		}
		String p = prefs.getString(KEY_SHARE_SUBJECT, DEFAULT_SHARE_SUBJECT);
		if (p.isEmpty()) {
			p = DEFAULT_SHARE_SUBJECT;
			Settings.prefs.edit().putString(KEY_SHARE_SUBJECT, Settings.DEFAULT_SHARE_SUBJECT).apply();
		}
		return p;
	}

	public static int getJpgCompression() {
		if (prefs == null) {
			return 95;
		}
		return prefs.getInt(KEY_JPG_COMPRESSION, 95);
	}

	public static IMAGE_OUTPUT_FORMAT getImageOutputFormat() {
		switch (getImageFormat()) {
		default:
		case "jpg":
			return IMAGE_OUTPUT_FORMAT.JPG;
		case "png":
			return IMAGE_OUTPUT_FORMAT.PNG;
		}
	}

	public static String getImageFormat() {
		if (prefs == null) {
			return "jpg";
		}
		final String imageFormat = prefs.getString(KEY_IMAGE_FORMAT, "jpg");
		return imageFormat;
	}

	// ###################################################################
	// Other Stuff

	public static String getAppTheme() {
		if (prefs == null) {
			return "Dark";
		}
		final String s = prefs.getString(KEY_APP_THEME, "Dark");
		return s;
	}

	public static int getTheme() {
		switch (getAppTheme()) {
		default:
		case "Dark":
			return R.style.AppThemeDark;
		case "Light":
			return R.style.AppThemeLight;
		}
	}

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
		case "Timestamp in Filename":
			return SORT_ORDER.FILENAME_TIMESTAMP;
		}
	}

	public static String getSortOrder() {
		// return "Random Layout";
		if (prefs == null) {
			return "Timestamp in Filename";
		}
		final String sort = prefs.getString(KEY_SORT_ORDER, "Timestamp in Filename");
		return sort;
	}

	// ###################################################################
	// Options Layout Selection

	public static String getSelectedMainLayout() {
		// return "Random Layout";
		if (prefs == null) {
			return "Random Layout";
		}
		return prefs.getString(KEY_MAINLAYOUTS, "Random Layout");
	}

	public static String getSelectedMainLayoutVariante() {
		// return "Random Layout";
		if (prefs == null) {
			return "None";
		}
		return prefs.getString(KEY_MAINLAYOUT_VARIANTS, "None");
	}

	public static int getAnzahlPatterns() {
		if (prefs == null) {
			return 1000;
		}
		// if (isDebugging()) {
		// return 0;
		// }
		return prefs.getInt(KEY_PATTERN_ANZAHL_PATTERNS, 1000);
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
		return prefs.getBoolean(KEY_PATTERN_BLUR, false);
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
		return prefs.getString(KEY_PATTERN_PATTERN_PICKER, "Virus Attack");
	}

	public static String getSelectedPatternVariant() {
		if (prefs == null) {
			return "none";
		}
		return prefs.getString(KEY_PATTERN_PATTERN_VARIANT_PICKER, "V1");
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
			return "Filled";
		}
		return prefs.getString(KEY_PATTERN_FILLED_OPTION, "Filled");
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
		return prefs.getBoolean(KEY_PATTERN_GLOSSY, false);
	}

	public static int getGlossyCenterGlowBrighness() {
		if (prefs == null) {
			return 64;
		}
		return prefs.getInt(KEY_GLOSSY_CENTER_GLOW_BRIGHTNESS, 64);
	}

	public static int getGlossyCenterGlowOuterDarkness() {
		if (prefs == null) {
			return -32;
		}
		return prefs.getInt(KEY_GLOSSY_CENTER_GLOW_OUTER_DARKNESS, -32);
	}

	public static int getGlossyReflectionBrightness() {
		if (prefs == null) {
			return 64;
		}
		return prefs.getInt(KEY_GLOSSY_REFLECTION_BRIGHTNESS, 64);
	}

	public static boolean isOutline() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean(KEY_PATTERN_OUTLINE, true);
	}

	public static boolean isOutlineNeverTransparent() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(KEY_PATTERN_OUTLINE_NEVER_TRANSPARENT, false);
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
		return prefs.getInt(KEY_PATTERN_OUTLINE_DARKNESS_ADJUST, -48);
	}

	public static int getDropShadowDarkness() {
		if (prefs == null) {
			return -48;
		}
		return prefs.getInt(KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST, -48);
	}

	public static String getText() {
		if (prefs == null) {
			return "The Wallpaper Designer";
		}
		return prefs.getString(KEY_PATTERN_TEXT, "The Wallpaper Designer");
	}

	public static String getTextDrawStyle() {
		if (prefs == null) {
			return "Round";
		}
		return prefs.getString(KEY_PATTERN_TEXT_DRAW_STYLE, "Round");
	}

	// ###################################################################
	// Options Pattern Style (DropShadow)
	public static String getDropShadowType() {
		if (prefs == null) {
			return "Darker";
		}
		return prefs.getString(KEY_PATTERN_DROPSHADOW_TYPE, "Darker");
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
		return prefs.getInt(KEY_RANDOMIZE_COLOR_RANGE_INT, 12);
	}

	public static boolean isRandomizeBrightness() {
		return getRandomizeColorBrighnessRange() != 0;
	}

	public static int getRandomizeColorBrighnessRange() {
		if (prefs == null) {
			return 12;
		}
		return prefs.getInt(KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT, 12);
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
		return prefs.getInt(KEY_NUMBER_OF_LEAFS, 5);
	}

	private static boolean isRandomLeafCount() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(KEY_RANDOM_LEAF_COUNT, false);
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
		return prefs.getString(KEY_COLOR_GRADIENT_DIRECTION, "4-Color Gradient from corners");
	}

	public static int getAnzahlGradientColors() {
		if (prefs == null) {
			return 4;
		}
		return Integer.parseInt(prefs.getString(KEY_COLORS_ANZAHL, "4"));
	}

	// Background Color
	public static boolean is4ColorGradient(final String test) {
		if (prefs == null) {
			return false;
		}
		return test.equalsIgnoreCase("4-Color Gradient from corners") //
				|| test.equalsIgnoreCase("4-Colors in corners")//
				|| test.equalsIgnoreCase("4 Color Sweep Gradient (Half Arch)")//
				|| test.equalsIgnoreCase("4 Color Sweep Gradient");
	}

	public static int getBackgroundColor1() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt(KEY_COLOR1, R.integer.COLOR_BLACK);
		return col;
	}

	public static int getBackgroundColor2() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt(KEY_COLOR2, R.integer.COLOR_WHITE);
		return col;
	}

	public static int getBackgroundColor3() {
		if (prefs == null) {
			return Color.BLUE;
		}
		final int col = prefs.getInt(KEY_COLOR3, Color.BLUE);
		return col;
	}

	public static int getBackgroundColor4() {
		if (prefs == null) {
			return Color.YELLOW;
		}
		final int col = prefs.getInt(KEY_COLOR4, Color.YELLOW);
		return col;
	}

	public static boolean isDynamicColoring() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("dynamicColoring", false);
	}

	public static boolean isHexValueEnabled() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(KEY_HEX_VALUES, false);
	}

	public static boolean isRenderingOnSettingsExit() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean(KEY_RENDER_ON_SETTINGS_EXIT, true);
	}

	// ###################################################################
	// General stuff
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
		return prefs.getBoolean(KEY_MUIMERP, false);
	}

	// private static final int CURRENT_SETTINGS_VERSION = 2;

	/**
	 * Initializes some preferences on first run with defaults
	 * 
	 * @param preferences
	 */
	public static void initPrefs(final SharedPreferences preferences, final Context ctx, final Activity activity) {
		// context = ctx;
		prefs = preferences;
		// if (prefs.getInt("sampleSettingsVersion", 0) <
		// CURRENT_SETTINGS_VERSION) {
		// Log.i("GEITH", "Unzipping Sample Settings...");
		// ZipHelper.unzipSettings(activity, ctx);
		// prefs.edit().putInt("sampleSettingsVersion",
		// CURRENT_SETTINGS_VERSION).commit();
		// } else {
		// Log.i("GEITH", "Sample Settings already unzipped...Nothing to do!");
		// }
		if (prefs.getBoolean("firstrun", true)) {
			Log.i("GEITH", "FirstRun --> initializing the SharedPreferences with some colors...");
			prefs.edit().putBoolean("firstrun", false).commit();
			// init colors
			prefs.edit().putInt(KEY_COLOR1, 0xffffc700).commit();
			prefs.edit().putInt(KEY_COLOR2, 0xff329a00).commit();
			prefs.edit().putInt(KEY_COLOR3, 0xff0060af).commit();
			prefs.edit().putInt(KEY_COLOR4, 0xffcd0077).commit();
			prefs.edit().putString(KEY_COLOR_GRADIENT_DIRECTION, "4-Color Gradient from corners").commit();
			prefs.edit().putBoolean("dynamicColoring", false).commit();

			prefs.edit().putString(KEY_SORT_ORDER, "Timestamp in Filename").commit();
			prefs.edit().putString(KEY_PATTERN_PATTERN_PICKER, "Geometrical Shapes").commit();
			prefs.edit().putString(KEY_PATTERN_PATTERN_VARIANT_PICKER, "Square (rounded)").commit();
			prefs.edit().putInt(KEY_PATTERN_ANZAHL_PATTERNS, 1000).commit();
			prefs.edit().putString(KEY_PATTERN_FILLED_OPTION, "Filled").commit();

			prefs.edit().putBoolean(KEY_PATTERN_BLUR, false).commit();
			prefs.edit().putInt("colorDropShadow", Color.BLACK).commit();
			prefs.edit().putInt("patternMinSizeAdjust", 50).commit();
			prefs.edit().putString("bWidth", "1920").commit();
			prefs.edit().putString("bHeight", "1200").commit();
			prefs.edit().putString("sizeSelection", "1920x1200").commit();

			prefs.edit().putInt(KEY_RANDOMIZE_COLOR_RANGE_INT, 20).commit();
			prefs.edit().putInt(KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT, 24).commit();
			prefs.edit().putInt("minOpacity", 225).commit();
			prefs.edit().putInt("maxOpacity", 255).commit();
			prefs.edit().putInt("dropShadowRadiusAdjustment", 250).commit();

			prefs.edit().putInt(KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST, -104).commit();
			prefs.edit().putInt(KEY_PATTERN_OUTLINE_DARKNESS_ADJUST, +40).commit();

			prefs.edit().putInt("colorOutline", Color.BLACK).commit();
			prefs.edit().putBoolean(KEY_PATTERN_OUTLINE, true).commit();
			prefs.edit().putString("rotatingStyle", "Random").commit();
			prefs.edit().putString(KEY_IMAGE_FORMAT, "jpg").commit();
			prefs.edit().putInt(KEY_JPG_COMPRESSION, 95).commit();
		}
	}

}
