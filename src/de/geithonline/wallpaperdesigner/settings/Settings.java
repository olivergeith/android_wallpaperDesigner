package de.geithonline.wallpaperdesigner.settings;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import de.geithonline.wallpaperdesigner.R;
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
	public static final String PATTERN_ANZAHL_PATTERNS = "anzahlPatterns";
	public static final String PATTERN_DROPSHADOW_TYPE = "pattern_dropShadowType";
	public static final String PATTERN_DROPSHADOW_COLOR = "colorDropShadow";
	public static final String PATTERN_BLUR = "blurPatterns";
	public static SharedPreferences prefs;
	private static Context context;
	private final static List<String> textStyles = new ArrayList<String>();
	private final static List<String> glossyStyles = new ArrayList<String>();
	private final static List<String> leafsStyles = new ArrayList<String>();
	private final static List<String> filledOptionStyles = new ArrayList<String>();
	private final static List<String> randomRotateStyles = new ArrayList<String>();
	private final static List<String> outlineStyles = new ArrayList<String>();

	static {
		textStyles.add("Custom Text");
		textStyles.add("Numbers");

		glossyStyles.add("Stars");
		glossyStyles.add("Hearts");
		glossyStyles.add("Bubbles");
		glossyStyles.add("Smiley");
		glossyStyles.add("PacMan");

		leafsStyles.add("Flowers");
		leafsStyles.add("FlowersV3");
		leafsStyles.add("FlowersV4");
		leafsStyles.add("FlowersV5");
		leafsStyles.add("Flower Bucket");
		leafsStyles.add("Stars");
		leafsStyles.add("Shells");
		leafsStyles.add("Shells V2");
		leafsStyles.add("Shells V3");
		leafsStyles.add("Shells V4");
		leafsStyles.add("Shells V5");
		leafsStyles.add("Shells V6");
		leafsStyles.add("Mixed Shells");

		filledOptionStyles.add("Gears");
		filledOptionStyles.add("Saw");
		filledOptionStyles.add("Triangles");
		filledOptionStyles.add("Squares");
		filledOptionStyles.add("Pentagon");
		filledOptionStyles.add("Hexagon");
		filledOptionStyles.add("Octagon");
		filledOptionStyles.add("Bubble Flowers");
		filledOptionStyles.add("FlowersV3");
		filledOptionStyles.add("FlowersV4");
		filledOptionStyles.add("FlowersV5");
		filledOptionStyles.add("Flower Bucket");
		filledOptionStyles.add("Virus Attack V3");
		filledOptionStyles.add("Smiley");
		filledOptionStyles.add("PacMan");
		filledOptionStyles.add("Rings");
		filledOptionStyles.add("Ufo");
		filledOptionStyles.add("UfoV2");
		filledOptionStyles.add("Rocket");
		filledOptionStyles.add("Space");
		filledOptionStyles.add("Hedgehog");
		filledOptionStyles.add("Sun");
		filledOptionStyles.add("Shells V2");
		filledOptionStyles.add("XmasTrees");
		filledOptionStyles.add("Star Circles");

		randomRotateStyles.add("Stars");
		randomRotateStyles.add("Hexagon");
		randomRotateStyles.add("Squares");
		randomRotateStyles.add("Rectangles");
		randomRotateStyles.add("Rectangles (rounded)");
		randomRotateStyles.add("Pentagon");
		randomRotateStyles.add("Octagon");
		randomRotateStyles.add("Triangles");
		randomRotateStyles.add("Crop Circles");
		randomRotateStyles.add("Shells");
		randomRotateStyles.add("Shells V2");
		randomRotateStyles.add("Shells V3");
		randomRotateStyles.add("Shells V4");
		randomRotateStyles.add("Shells V5");
		randomRotateStyles.add("Shells V6");
		randomRotateStyles.add("Mixed Shells");
		randomRotateStyles.add("Virus Attack V6");
		randomRotateStyles.add("FlowersV3");
		randomRotateStyles.add("FlowersV4");
		randomRotateStyles.add("FlowersV5");
		randomRotateStyles.add("Flower Bucket");
		randomRotateStyles.add("Android");
		randomRotateStyles.add("Rocket");
		randomRotateStyles.add("Deathstars");
		randomRotateStyles.add("Ufo");
		randomRotateStyles.add("UfoV2");
		randomRotateStyles.add("Space");
		randomRotateStyles.add("Skulls");
		randomRotateStyles.add("Hearts");
		randomRotateStyles.add("Pillows");
		randomRotateStyles.add("Clouds");
		randomRotateStyles.add("Rectangles");
		randomRotateStyles.add("Rectangles (rounded)");
		randomRotateStyles.add("PacMan");
		randomRotateStyles.add("Smiley");
		randomRotateStyles.add("Sailboat");
		randomRotateStyles.add("Sailboat2");
		randomRotateStyles.add("Anchor");
		randomRotateStyles.add("Lighthouse");
		randomRotateStyles.add("Marina");
		randomRotateStyles.add("Hedgehog");
		randomRotateStyles.add("Sun");
		randomRotateStyles.add("Lemons");
		randomRotateStyles.add("XmasTrees");
		randomRotateStyles.add("Star Circles");

		outlineStyles.add("Sailboat");
		outlineStyles.add("Sailboat2");
		outlineStyles.add("Anchor");
		outlineStyles.add("Lighthouse");
		outlineStyles.add("Marina");
		outlineStyles.add("Skulls");
		outlineStyles.add("Ufo");
		outlineStyles.add("UfoV2");
		outlineStyles.add("Rocket");
		outlineStyles.add("Space");
		outlineStyles.add("Deathstars");
		outlineStyles.add("Triangles");
		outlineStyles.add("Squares");
		outlineStyles.add("Rectangles");
		outlineStyles.add("Rectangles (rounded)");
		outlineStyles.add("Pentagon");
		outlineStyles.add("Hexagon");
		outlineStyles.add("Octagon");
		outlineStyles.add("Stars");
		outlineStyles.add("Hearts");
		outlineStyles.add("Bubbles");
		outlineStyles.add("Pillows");
		outlineStyles.add("Rings");
		outlineStyles.add("Gears");
		outlineStyles.add("Saw");
		outlineStyles.add("Roses");
		outlineStyles.add("Clouds");
		outlineStyles.add("Flowers");
		outlineStyles.add("FlowersV3");
		outlineStyles.add("FlowersV4");
		outlineStyles.add("FlowersV5");
		outlineStyles.add("Flower Bucket");
		outlineStyles.add("Virus Attack");
		outlineStyles.add("Virus Attack V2");
		outlineStyles.add("Virus Attack V3");
		outlineStyles.add("Virus Attack V4");
		outlineStyles.add("Virus Attack V5");
		outlineStyles.add("Virus Attack V6");
		outlineStyles.add("Bubble Flowers");
		outlineStyles.add("Letters");
		outlineStyles.add("Dandelion");
		outlineStyles.add("Crop Circles");

		outlineStyles.add("Shells");
		outlineStyles.add("Shells V2");
		outlineStyles.add("Shells V3");
		outlineStyles.add("Shells V4");
		outlineStyles.add("Shells V5");
		outlineStyles.add("Shells V6");
		outlineStyles.add("Mixed Shells");

		outlineStyles.add("PacMan");
		outlineStyles.add("Smiley");
		outlineStyles.add("Android");
		outlineStyles.add("Hedgehog");
		outlineStyles.add("Sun");
		outlineStyles.add("Lemons");
		outlineStyles.add("XmasTrees");
		outlineStyles.add("Star Circles");
	}

	// ###################################################################
	// Options Style Selection

	public static String getSelectedStyle() {
		return "Patterns";
		// return prefs.getString("stylePicker", "Patterns");
	}

	// ###################################################################
	// Options Pattern Style

	public static String getSelectedPattern() {
		if (prefs == null) {
			return "Virus Attack";
		}
		return prefs.getString(PATTERN_PATTERN_PICKER, "Virus Attack");
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

	public static boolean hasPatternTextOption(final String pattern) {
		return textStyles.contains(pattern);
	}

	public static boolean hasPatternGlossyEffect(final String pattern) {
		return glossyStyles.contains(pattern);
	}

	public static boolean hasNumberOfLeafsOption(final String pattern) {
		return leafsStyles.contains(pattern);
	}

	public static boolean hasPatternFilledOption(final String pattern) {
		return filledOptionStyles.contains(pattern);
	}

	public static boolean hasPatternRandomRotate(final String pattern) {
		return randomRotateStyles.contains(pattern);
	}

	public static boolean hasPatternOutlineEffect(final String pattern) {
		return outlineStyles.contains(pattern);
	}

	public static String getFilledOption() {
		if (prefs == null) {
			return "Not filled";
		}
		return prefs.getString(PATTERN_FILLED_OPTION, "Not filled");
	}

	public static boolean isRandomRotate() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(PATTERN_RANDOM_ROTATE, false);
	}

	// ###################################################################
	// Wallpater Size
	public static int getRotationDegrees(final int randomMin, final int randomMax) {
		if (isRandomRotate()) {
			return Randomizer.getRandomInt(randomMin - 1, randomMax);
		}
		if (prefs == null) {
			return 0;
		}
		return prefs.getInt("rotationDegrees", 0);
	}

	public static boolean isBlurPatterns() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean(PATTERN_BLUR, false);
	}

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
			return 192;
		}
		return prefs.getInt("minOpacity", 128);
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
			return 32;
		}
		return prefs.getInt("randomizeColorRangeInt", 32);
	}

	public static boolean isRandomizeBrightness() {
		return getRandomizeColorBrighnessRange() != 0;
	}

	public static int getRandomizeColorBrighnessRange() {
		if (prefs == null) {
			return 32;
		}
		return prefs.getInt("randomizeColorBrightnessRangeInt", 32);
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

			prefs.edit().putInt("randomizeColorRangeInt", 32).commit();
			// prefs.edit().putInt("randomizeAlphaRangeInt", 30).commit();
			prefs.edit().putInt("minOpacity", 125).commit();
			prefs.edit().putInt("maxOpacity", 255).commit();

			prefs.edit().putInt(PATTERN_DROPSHADOW_DARKNESS_ADJUST, -56).commit();
			prefs.edit().putInt(PATTERN_OUTLINE_DARKNESS_ADJUST, +32).commit();

			prefs.edit().putInt("colorOutline", Color.BLACK).commit();
			prefs.edit().putBoolean(PATTERN_OUTLINE, true).commit();
		}
	}

}
