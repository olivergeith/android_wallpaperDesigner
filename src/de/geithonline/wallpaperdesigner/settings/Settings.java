package de.geithonline.wallpaperdesigner.settings;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class Settings {
	public static final String KEY_COLOR_REPEATS = "colorRepeats";
	public static final String KEY_TORNADO_RINGS = "tornadoRings";
	public static final String KEY_TORNADO_ARMS = "tornadoArms";
	public static final String KEY_RANDOMIZE_SATURATION_RANGE = "randomizeSaturationRange";
	public static final String KEY_SHOW_SET_WALLPAPER_BUTTON = "showSetWallpaperButton";
	public static final String KEY_LIMIT_2_CANVAS = "limit2Canvas";
	public static final String KEY_COLOR_RANDOMIZING_TYPE = "colorRandomizingType";
	public static final String KEY_BLURR_STAGE_1 = "blurrStage1";
	public static final String KEY_BLURR_STAGE_2 = "blurrStage2";
	public static final String KEY_BLURR_STAGE_3 = "blurrStage3";
	public static final String KEY_BLURR_AMOUNT_1 = "blurrAmount1";
	public static final String KEY_BLURR_AMOUNT_2 = "blurrAmount2";
	public static final String KEY_BLURR_AMOUNT_3 = "blurrAmount3";
	public static final String KEY_OUTLINE_THICKNESS_LIMIT = "outlineThicknessLimit";
	public static final String KEY_OUTLINE_THICKNESS_ADJUST = "outlineThicknessAdjust";
	public static final String KEY_GLOSSY_GLOW_STYLE = "glowStyle";
	public static final String KEY_GLOSSY_REFLECTION_STYLE = "reflectionStyle";
	public static final String KEY_DROP_SHADOW_OFFSET_Y = "dropShadowOffsetY";
	public static final String KEY_DROP_SHADOW_OFFSET_X = "dropShadowOffsetX";
	public static final String KEY_BGRND_COLOR2 = "bgrnd_color2";
	public static final String KEY_BGRND_COLOR1 = "bgrnd_color1";
	public static final String KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT = "sameBackgroundAsPatternGradient";
	public static final String KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT = "randomizeColorBrightnessRangeInt";
	public static final String KEY_RANDOMIZE_COLOR_RANGE_INT = "randomizeColorRangeInt";
	public static final String KEY_RENDER_ON_SETTINGS_EXIT = "renderOnSettingsExit";
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
	private static boolean superuser = false;

	// private static Context context;

	public enum IMAGE_OUTPUT_FORMAT {
		PNG, JPG;
	}

	public enum GLOSSY_REFLECTIONS_STYLE {
		SMALL_OVAL, BIG_OVAL, DIAGONAL, TOP_LEFT, NONE, DIAGONAL_CURVED, DIAGONAL_45GRAD, CURVED_FROM_TOP, DIAGONAL_FLIPPED, DIAGONAL_45GRAD_FLIPPED, TOP_GLOW, BOTTOM_GLOW, DIAGONAL_CURVED_V2, TOP_LEFT_V2;
	}

	public enum GLOSSY_GLOW_STYLE {
		CENTER, HORIZONTAL;
	}

	public enum COLOR_RANDOMIZING_TYPE {
		FULL_RGB, ONLY_RED, ONLY_GREEN, ONLY_BLUE, HUE, PUSH_RED, PUSH_GREEN, PUSH_BLUE;
	}

	public enum CANVAS_LIMT {
		strict, small, wide, no_limit;
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

	public static COLOR_RANDOMIZING_TYPE getColorRandomizingType() {
		switch (getColorRandomizing()) {
		default:
		case "full RGB":
			return COLOR_RANDOMIZING_TYPE.FULL_RGB;
		case "only RED":
			return COLOR_RANDOMIZING_TYPE.ONLY_RED;
		case "only GREEN":
			return COLOR_RANDOMIZING_TYPE.ONLY_GREEN;
		case "only BLUE":
			return COLOR_RANDOMIZING_TYPE.ONLY_BLUE;
		case "hue":
			return COLOR_RANDOMIZING_TYPE.HUE;
		case "push RED":
			return COLOR_RANDOMIZING_TYPE.PUSH_RED;
		case "push GREEN":
			return COLOR_RANDOMIZING_TYPE.PUSH_GREEN;
		case "push BLUE":
			return COLOR_RANDOMIZING_TYPE.PUSH_BLUE;
		}
	}

	public static String getColorRandomizing() {
		if (prefs == null) {
			return "full RGB";
		}
		final String type = prefs.getString(KEY_COLOR_RANDOMIZING_TYPE, "full RGB");
		return type;
	}

	public static CANVAS_LIMT getCanvasLimitType() {
		switch (getCanvasLimit()) {
		default:
		case "small tolerance":
			return CANVAS_LIMT.small;
		case "wide tolerance":
			return CANVAS_LIMT.wide;
		case "strict":
			return CANVAS_LIMT.strict;
		case "no limit":
			return CANVAS_LIMT.no_limit;
		}
	}

	public static String getCanvasLimit() {
		if (prefs == null) {
			return "small tolerance";
		}
		final String type = prefs.getString(KEY_LIMIT_2_CANVAS, "small tolerance");
		return type;
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
			return "Random";
		}
		return prefs.getString(KEY_MAINLAYOUT_VARIANTS, "Random");
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

	public static int getBlurrAmount1() {
		if (prefs == null) {
			return 50;
		}
		return prefs.getInt(KEY_BLURR_AMOUNT_1, 50);
	}

	public static int getBlurrAmount2() {
		if (prefs == null) {
			return 20;
		}
		return prefs.getInt(KEY_BLURR_AMOUNT_2, 20);
	}

	public static int getBlurrAmount3() {
		if (prefs == null) {
			return 8;
		}
		return prefs.getInt(KEY_BLURR_AMOUNT_3, 8);
	}

	public static int getBlurrStage1() {
		if (prefs == null) {
			return 60;
		}
		return prefs.getInt(KEY_BLURR_STAGE_1, 60);
	}

	public static int getBlurrStage2() {
		if (prefs == null) {
			return 70;
		}
		return prefs.getInt(KEY_BLURR_STAGE_2, 70);
	}

	public static int getBlurrStage3() {
		if (prefs == null) {
			return 80;
		}
		return prefs.getInt(KEY_BLURR_STAGE_3, 80);
	}

	public static boolean isRandomStartwinkel() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("ramdomStartWinkel", true);
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
	// Special Settings Glossy

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

	public static GLOSSY_REFLECTIONS_STYLE getGlossyReflectionStyle() {
		switch (getGlossyReflectionStyleString()) {
		default:
		case "Diagonal":
			return GLOSSY_REFLECTIONS_STYLE.DIAGONAL;
		case "Diagonal (flipped)":
			return GLOSSY_REFLECTIONS_STYLE.DIAGONAL_FLIPPED;
		case "Diagonal 45°":
			return GLOSSY_REFLECTIONS_STYLE.DIAGONAL_45GRAD;
		case "Diagonal 45° (flipped)":
			return GLOSSY_REFLECTIONS_STYLE.DIAGONAL_45GRAD_FLIPPED;
		case "Diagonal (curved)":
			return GLOSSY_REFLECTIONS_STYLE.DIAGONAL_CURVED;
		case "Diagonal (curved) V2":
			return GLOSSY_REFLECTIONS_STYLE.DIAGONAL_CURVED_V2;
		case "Curved from top":
			return GLOSSY_REFLECTIONS_STYLE.CURVED_FROM_TOP;
		case "Topleft":
			return GLOSSY_REFLECTIONS_STYLE.TOP_LEFT;
		case "Topleft V2":
			return GLOSSY_REFLECTIONS_STYLE.TOP_LEFT_V2;
		case "Big Oval":
			return GLOSSY_REFLECTIONS_STYLE.BIG_OVAL;
		case "Small Oval":
			return GLOSSY_REFLECTIONS_STYLE.SMALL_OVAL;
		case "Top Glow":
			return GLOSSY_REFLECTIONS_STYLE.TOP_GLOW;
		case "None":
			return GLOSSY_REFLECTIONS_STYLE.NONE;
		}
	}

	public static String getGlossyReflectionStyleString() {
		if (prefs == null) {
			return "Diagonal";
		}
		final String style = prefs.getString(KEY_GLOSSY_REFLECTION_STYLE, "Diagonal");
		return style;
	}

	public static GLOSSY_GLOW_STYLE getGlossyGlowStyle() {
		switch (getGlossyGlowStyleString()) {
		default:
		case "Center":
			return GLOSSY_GLOW_STYLE.CENTER;
		case "Horizontal":
			return GLOSSY_GLOW_STYLE.HORIZONTAL;
		}
	}

	public static String getGlossyGlowStyleString() {
		if (prefs == null) {
			return "Center";
		}
		final String style = prefs.getString(KEY_GLOSSY_GLOW_STYLE, "Center");
		return style;
	}

	// ###################################################################
	// Special Settings
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

	public static float getOutlineThicknessAdjustment() {
		if (prefs == null) {
			return 1f;
		}
		return prefs.getInt(KEY_OUTLINE_THICKNESS_ADJUST, 100) / 100f;
	}

	public static int getOutlineThicknessLimit() {
		if (prefs == null) {
			return 3;
		}
		return prefs.getInt(KEY_OUTLINE_THICKNESS_LIMIT, 3);
	}

	public static int getDropShadowDarkness() {
		if (prefs == null) {
			return -48;
		}
		return prefs.getInt(KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST, -48);
	}

	public static int getDropShadowOffsetX() {
		if (prefs == null) {
			return 0;
		}
		return prefs.getInt(KEY_DROP_SHADOW_OFFSET_X, 0);
	}

	public static int getDropShadowOffsetY() {
		if (prefs == null) {
			return 0;
		}
		return prefs.getInt(KEY_DROP_SHADOW_OFFSET_Y, 0);
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

	public static boolean isRandomizeSaturation() {
		return getRandomizeSaturationRange() != 0;
	}

	public static int getRandomizeSaturationRange() {
		if (prefs == null) {
			return 0;
		}
		return prefs.getInt(KEY_RANDOMIZE_SATURATION_RANGE, 0);
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
			return getCustomWidth();
		default:
			return getWidthFromSizeString(s);
		}
	}

	public static int getCustomWidth() {
		return Integer.valueOf(prefs.getString("bWidth", "2560"));
	}

	public static int getCustomHeight() {
		return Integer.valueOf(prefs.getString("bHeight", "1600"));
	}

	public static int getHeight() {
		final String s = getSizeSelection();
		switch (s) {
		case "customSize":
			return getCustomHeight();
		default:
			return getHeightFromSizeString(s);
		}
	}

	private static int getWidthFromSizeString(final String s) {
		final String w = s.substring(0, s.indexOf("x"));
		return Integer.parseInt(w);
	}

	private static int getHeightFromSizeString(final String s) {
		final String h = s.substring(s.indexOf("x") + 1);
		return Integer.parseInt(h);
	}

	// ###################################################################
	// Background Color

	public static boolean isSameGradientAsPatterns() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean(KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT, true);
	}

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
		return test.startsWith("4");
	}

	public static boolean isLinearGradient(final String test) {
		return test.startsWith("Linear") || test.startsWith("Radial") || test.startsWith("Sweep Gradient (++)");
	}

	public static int getColorRepeats() {
		if (prefs == null) {
			return 1;
		}
		return prefs.getInt(KEY_COLOR_REPEATS, 1);
	}

	public static boolean isTornadoGradient(final String test) {
		return test.startsWith("4-Color Tornado");
	}

	public static int getTornadoArms() {
		if (prefs == null) {
			return 1;
		}
		return prefs.getInt(KEY_TORNADO_ARMS, 1);
	}

	public static int getTornadoRings() {
		if (prefs == null) {
			return 2;
		}
		return prefs.getInt(KEY_TORNADO_RINGS, 1) + 1;
	}

	public static int getPatternColor1() {
		if (prefs == null) {
			return R.integer.COLOR_BLACK;
		}
		final int col = prefs.getInt(KEY_COLOR1, R.integer.COLOR_BLACK);
		return col;
	}

	public static int getPatternColor2() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt(KEY_COLOR2, R.integer.COLOR_WHITE);
		return col;
	}

	public static int getPatternColor3() {
		if (prefs == null) {
			return Color.BLUE;
		}
		final int col = prefs.getInt(KEY_COLOR3, Color.BLUE);
		return col;
	}

	public static int getPatternColor4() {
		if (prefs == null) {
			return Color.YELLOW;
		}
		final int col = prefs.getInt(KEY_COLOR4, Color.YELLOW);
		return col;
	}

	public static int getBackGrndColor1() {
		if (prefs == null) {
			return Color.DKGRAY;
		}
		final int col = prefs.getInt(KEY_BGRND_COLOR1, Color.DKGRAY);
		return col;
	}

	public static int getBackGrndColor2() {
		if (prefs == null) {
			return Color.BLACK;
		}
		final int col = prefs.getInt(KEY_BGRND_COLOR2, Color.BLACK);
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

	public static boolean isShowSetWallpaperButton() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean(KEY_SHOW_SET_WALLPAPER_BUTTON, true);
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

	// private static boolean readSuperUser(final Activity activity) {
	// final File file = new File(activity.getFilesDir(), "superuser.txt");
	// superuser = file.exists();
	// return superuser;
	// }

	public static boolean isSuperUser(final Activity activity) {
		final File file = new File(activity.getFilesDir(), "superuser.txt");
		superuser = file.exists();
		return superuser;
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
			prefs.edit().putBoolean(KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT, true).commit();
			prefs.edit().putInt(KEY_BLURR_AMOUNT_1, 80).commit();
			prefs.edit().putInt(KEY_BLURR_AMOUNT_2, 20).commit();
			prefs.edit().putInt(KEY_BLURR_AMOUNT_3, 8).commit();
			prefs.edit().putInt(KEY_BLURR_STAGE_1, 60).commit();
			prefs.edit().putInt(KEY_BLURR_STAGE_2, 70).commit();
			prefs.edit().putInt(KEY_BLURR_STAGE_3, 80).commit();
		}
	}

}
