
package de.geithonline.wallpaperdesigner.settings;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.settings.ColorRandOptions.COLOR_RANDOMIZING_TYPE;
import de.geithonline.wallpaperdesigner.settings.specialoptions.CircularMazeOptions;
import de.geithonline.wallpaperdesigner.settings.specialoptions.EyeOptions;
import de.geithonline.wallpaperdesigner.settings.specialoptions.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.settings.specialoptions.TailOptions.TailRotationType;
import de.geithonline.wallpaperdesigner.settings.specialoptions.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.specialoptions.TailOptionsLine;
import de.geithonline.wallpaperdesigner.shapes.composed.ComposedPathColoringType;
import de.geithonline.wallpaperdesigner.shapes.composed.ESinusObjectsSizingType;
import de.geithonline.wallpaperdesigner.shapes.composed.ESinusObjectsType;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper.SORT_ORDER;
import de.geithonline.wallpaperdesigner.utils.Randomizer;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.ZipHelper;

public class Settings {
	public static final String KEY_CREATE_GIF_LENGTH = "createGifLength";
	public static final String KEY_CREATE_GIF_QUALITY = "createGifQuality";
	public static final String KEY_CREATE_GIF_SIZE = "createGifSize";
	public static final String KEY_CREATE_GIF = "createGif";
	public static final String KEY_EXPERT_MODE = "expertMode";
	public static final String KEY_INCREMENTING_DEGREES_ADDING = "incrementingDegreesAdding";
	public static final String KEY_RANDOM_DEGREES_ADDING = "randomDegreesAdding";
	public static final String KEY_COUNTER_CLOCKWISE = "counterClockwise";
	public static final String KEY_BACKGROUND_PICKER = "backgroundPicker";
	public static final String KEY_FLIP_RANDOM_LEFT_RIGHT = "flipRandomLeftRight";
	public static final String KEY_FLIP_RANDOM_UP_DOWN = "flipRandomUpDown";
	public static final String KEY_SCENE_PERCENTAGE_OF_CIRCLES = "scenePercentageOfCircles";
	public static final String KEY_CLOSED_SINE_TRAIL = "closedSineTrail";
	public static final String KEY_COLORFUL_DRAWING = "colorfulDrawing";
	public static final String KEY_TAIL_OPTION = "tailOption2";
	public static final String KEY_ROTATING_STYLE = "rotatingStyle";
	public static final String KEY_COLOR_OUTLINE = "colorOutline";
	public static final String KEY_DROP_SHADOW_RADIUS_ADJUSTMENT = "dropShadowRadiusAdjustment";
	public static final String KEY_MAX_OPACITY = "maxOpacity";
	public static final String KEY_MIN_OPACITY = "minOpacity";
	public static final String KEY_PATTERN_MIN_SIZE_ADJUST = "patternMinSizeAdjust";
	public static final String KEY_RENDERING_PROCESS_FRAMES = "renderingProcessFrames";
	public static final String KEY_SHOW_RENDERING_PROCESS = "showRenderingProcess";
	public static final String KEY_TORNADO_CENTER_POINT_Y = "tornadoCenterPointY";
	public static final String KEY_TORNADO_CENTER_POINT_X = "tornadoCenterPointX";
	public static final String KEY_SWEEP_CENTER_POINT_Y = "sweepCenterPointY";
	public static final String KEY_SWEEP_CENTER_POINT_X = "sweepCenterPointX";
	public static final String KEY_CORNER_REPEATS = "cornerRepeats";
	public static final String KEY_CORNER_GRADIENT_LEVELS = "cornerGradientLevels";
	public static final String KEY_RENDER_ON_APP_STARTUP = "renderOnAppStartup";
	public static final String KEY_B_HEIGHT = "bHeight";
	public static final String KEY_B_WIDTH = "bWidth";
	public static final String KEY_SIZE_SELECTION = "sizeSelection";
	public static final String KEY_RESTORE_WALLPAPER_SIZE = "restoreWallpaperSize";
	public static final String KEY_REVERSE_COLORS = "reverseColors";
	public static final String KEY_COLOR_REPEATS = "colorRepeats";
	public static final String KEY_TORNADO_RINGS = "tornadoRings";
	public static final String KEY_TORNADO_ARMS = "tornadoArms";
	@Deprecated
	public static final String OLD_KEY_RANDOMIZE_SATURATION_RANGE = "randomizeSaturationRange";
	public static final String KEY_SHOW_SET_WALLPAPER_BUTTON = "showSetWallpaperButton";
	public static final String KEY_LIMIT_2_CANVAS = "limit2Canvas";
	@Deprecated
	public static final String OLD_KEY_COLOR_RANDOMIZING_TYPE = "colorRandomizingType";
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
	@Deprecated
	public static final String OLD_KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT = "randomizeColorBrightnessRangeInt";
	@Deprecated
	public static final String OLD_KEY_RANDOMIZE_COLOR_RANGE_INT = "randomizeColorRangeInt";
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
	public static final String KEY_PERCENTAGE_OF_OUTLINE_ONLY = "percentageOfOutlineOnly";
	public static final String KEY_PATTERN_GLOSSY = "glossy";
	public static final String KEY_PATTERN_PATTERN_PICKER = "pattern_patternPicker";
	public static final String KEY_PATTERN_PATTERN_VARIANT_PICKER = "pattern_patternVariantPicker";
	public static final String KEY_PATTERN_ANZAHL_PATTERNS = "anzahlPatterns";
	public static final String KEY_PATTERN_DROPSHADOW_TYPE = "pattern_dropShadowType";
	public static final String KEY_PATTERN_DROPSHADOW_COLOR = "colorDropShadow";
	public static final String KEY_PATTERN_BLUR = "blurPatterns";
	public static final String KEY_RADIUS_TYPE = "radiusType";

	public static SharedPreferences prefs;
	private static boolean superuser = false;

	public enum IMAGE_OUTPUT_FORMAT {
		PNG, JPG;

		public static IMAGE_OUTPUT_FORMAT enumForName(final String name) {
			switch (name) {
			default:
			case "jpg":
				return IMAGE_OUTPUT_FORMAT.JPG;
			case "png":
				return IMAGE_OUTPUT_FORMAT.PNG;
			}
		}

	}

	public enum GLOSSY_REFLECTIONS_STYLE {
		SMALL_OVAL, BIG_OVAL, DIAGONAL, TOP_LEFT, NONE, DIAGONAL_CURVED, DIAGONAL_45GRAD, CURVED_FROM_TOP, DIAGONAL_FLIPPED, DIAGONAL_45GRAD_FLIPPED, TOP_GLOW, BOTTOM_GLOW, DIAGONAL_CURVED_V2, TOP_LEFT_V2, FULL_OVAL;

		public static GLOSSY_REFLECTIONS_STYLE enumForName(final String name) {
			switch (name) {
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
			case "Full Oval":
				return GLOSSY_REFLECTIONS_STYLE.FULL_OVAL;
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
	}

	public enum GLOSSY_GLOW_STYLE {
		CENTER, HORIZONTAL, VERTICAL, VERTICAL_WHITE, VERTICAL_RAINBOW;

		public static GLOSSY_GLOW_STYLE enumForName(final String name) {
			switch (name) {
			default:
			case "Center":
				return GLOSSY_GLOW_STYLE.CENTER;
			case "Horizontal":
				return GLOSSY_GLOW_STYLE.HORIZONTAL;
			case "Vertical":
				return GLOSSY_GLOW_STYLE.VERTICAL;
			case "Vertical Rainbow":
				return GLOSSY_GLOW_STYLE.VERTICAL_RAINBOW;
			case "Vertical (White)":
				return GLOSSY_GLOW_STYLE.VERTICAL_WHITE;
			}
		}
	}

	public enum DROP_SHADOW_TYPE {
		NO, RANDOM, OPPOSITE, DARKER, SELECT;

		public static DROP_SHADOW_TYPE enumForName(final String name) {
			switch (name) {
			default:
			case "No":
				return DROP_SHADOW_TYPE.NO;
			case "Random":
				return DROP_SHADOW_TYPE.RANDOM;
			case "Opposite":
				return DROP_SHADOW_TYPE.OPPOSITE;
			case "Darker":
				return DROP_SHADOW_TYPE.DARKER;
			case "Select":
				return DROP_SHADOW_TYPE.SELECT;
			}
		}
	}

	public enum CANVAS_LIMT {
		strict, small, wide, no_limit, double_wide, small_inset, wide_inset;

		public static CANVAS_LIMT enumForName(final String name) {
			switch (name) {
			default:
			case "small tolerance":
				return CANVAS_LIMT.small;
			case "wide tolerance":
				return CANVAS_LIMT.wide;
			case "double wide tolerance":
				return CANVAS_LIMT.double_wide;
			case "strict":
				return CANVAS_LIMT.strict;
			case "small inset":
				return CANVAS_LIMT.small_inset;
			case "wide inset":
				return CANVAS_LIMT.wide_inset;
			case "no limit":
				return CANVAS_LIMT.no_limit;
			}
		}
	}

	public enum RADIUS_TYPE {
		random, increasing, dependingOnBlurrStage_decreasing, decreasing, dependingOnBlurrStage_increasing;

		public static RADIUS_TYPE enumForName(final String name) {
			switch (name) {
			default:
			case "random":
			case "Random":
				return RADIUS_TYPE.random;
			case "Increasing":
				return RADIUS_TYPE.increasing;
			case "Decreasing":
				return RADIUS_TYPE.decreasing;
			case "Depending on BlurrStage (increasing)":
				return RADIUS_TYPE.dependingOnBlurrStage_increasing;
			case "Depending on BlurrStage (decreasing)":
				return RADIUS_TYPE.dependingOnBlurrStage_decreasing;
			}
		}
	}

	public static final String DEFAULT_SHARE_SUBJECT = "Shared from the Wallpaper Designer";
	public static final String DEFAULT_SHARE_TEXT = "Created with 'The Wallpaper Designer' : https://play.google.com/store/apps/details?id=de.geithonline.wallpaperdesigner";

	// Conveniance
	// #####################################################################
	private static boolean readBooleanPref(final String key, final boolean defaultValue) {
		if (prefs == null) {
			return defaultValue;
		}
		return prefs.getBoolean(key, defaultValue);
	}

	private static int readIntegerPref(final String key, final int defaultValue) {
		if (prefs == null) {
			return defaultValue;
		}
		return prefs.getInt(key, defaultValue);
	}

	private static String readStringPref(final String key, final String defaultValue) {
		if (prefs == null) {
			return defaultValue;
		}
		return prefs.getString(key, defaultValue);
	}

	// ##################################################################################

	public static String getShareText() {
		String p = readStringPref(KEY_SHARE_TEXT, DEFAULT_SHARE_TEXT);
		if (p.isEmpty()) {
			p = DEFAULT_SHARE_TEXT;
			Settings.prefs.edit().putString(KEY_SHARE_TEXT, Settings.DEFAULT_SHARE_TEXT).apply();
		}
		return p;
	}

	public static String getShareSubject() {
		String p = readStringPref(KEY_SHARE_SUBJECT, DEFAULT_SHARE_SUBJECT);
		if (p.isEmpty()) {
			p = DEFAULT_SHARE_SUBJECT;
			Settings.prefs.edit().putString(KEY_SHARE_SUBJECT, Settings.DEFAULT_SHARE_SUBJECT).apply();
		}
		return p;
	}

	public static int getJpgCompression() {
		return readIntegerPref(KEY_JPG_COMPRESSION, 95);
	}

	public static IMAGE_OUTPUT_FORMAT getImageOutputFormat() {
		return IMAGE_OUTPUT_FORMAT.enumForName(getImageFormatString());
	}

	public static String getImageFormatString() {
		return readStringPref(KEY_IMAGE_FORMAT, "jpg");
	}

	public static COLOR_RANDOMIZING_TYPE getColorRandomizingType() {
		return COLOR_RANDOMIZING_TYPE.enumForName(getOldColorRandomizingString());
	}

	@Deprecated
	public static String getOldColorRandomizingString() {
		return readStringPref(OLD_KEY_COLOR_RANDOMIZING_TYPE, "full RGB");
	}

	public static CANVAS_LIMT getCanvasLimitType() {
		return CANVAS_LIMT.enumForName(getCanvasLimitString());
	}

	public static String getCanvasLimitString() {
		return readStringPref(KEY_LIMIT_2_CANVAS, "small tolerance");
	}

	// ###################################################################
	// Other Stuff

	public static String getAppTheme() {
		return readStringPref(KEY_APP_THEME, "Dark");
	}

	public static int getTheme() {
		switch (getAppTheme()) {
		default:
		case "Gray (orange)":
			return R.style.Theme_BatteryLWPGrayOrange;
		case "Blue":
			return R.style.Theme_BatteryLWP;
		case "Brown":
			return R.style.Theme_BatteryLWPBrown;
		case "Lime":
			return R.style.Theme_BatteryLWPLime;
		case "Pink":
			return R.style.Theme_BatteryLWPPink;
		case "Orange":
			return R.style.Theme_BatteryLWPOrange;
		case "Purple":
			return R.style.Theme_BatteryLWPPurple;
		case "Gray (pink)":
			return R.style.Theme_BatteryLWPGrayPink;
		case "Gray (blue)":
			return R.style.Theme_BatteryLWPGrayBlue;
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
		return readStringPref(KEY_SORT_ORDER, "Timestamp in Filename");
	}

	// ###################################################################
	// Options Layout Selection

	public static String getSelectedMainLayout() {
		return readStringPref(KEY_MAINLAYOUTS, "Random Layout");
	}

	public static String getSelectedMainLayoutVariante() {
		return readStringPref(KEY_MAINLAYOUT_VARIANTS, "Random");
	}

	public static int getAnzahlPatterns() {
		return readIntegerPref(KEY_PATTERN_ANZAHL_PATTERNS, 1000);
	}

	public static float getOverlapping() {
		return readIntegerPref("overlapping", 50) / 100f;
	}

	public static float getCenterPointY() {
		return readIntegerPref("centerPointY", 50) / 100f;
	}

	public static float getCenterPointX() {
		return readIntegerPref("centerPointX", 50) / 100f;
	}

	public static float getRotationCenterPointY() {
		return readIntegerPref("rotationCenterPointY", 50) / 100f;
	}

	public static float getRotationCenterPointX() {
		return readIntegerPref("rotationCenterPointX", 50) / 100f;
	}

	public static float getTornadoCenterPointY() {
		return readIntegerPref(KEY_TORNADO_CENTER_POINT_Y, 50) / 100f;
	}

	public static float getTornadoCenterPointX() {
		return readIntegerPref(KEY_TORNADO_CENTER_POINT_X, 50) / 100f;
	}

	public static float getSweepCenterPointY() {
		return readIntegerPref(KEY_SWEEP_CENTER_POINT_Y, 50) / 100f;
	}

	public static float getSweepCenterPointX() {
		return readIntegerPref(KEY_SWEEP_CENTER_POINT_X, 50) / 100f;
	}

	public static boolean isBlurPatterns() {
		return readBooleanPref(KEY_PATTERN_BLUR, false);
	}

	public static int getBlurrAmount1() {
		return readIntegerPref(KEY_BLURR_AMOUNT_1, 50);
	}

	public static int getBlurrAmount2() {
		return readIntegerPref(KEY_BLURR_AMOUNT_2, 20);
	}

	public static int getBlurrAmount3() {
		return readIntegerPref(KEY_BLURR_AMOUNT_3, 8);
	}

	public static int getBlurrStage1() {
		return readIntegerPref(KEY_BLURR_STAGE_1, 60);
	}

	public static int getBlurrStage2() {
		return readIntegerPref(KEY_BLURR_STAGE_2, 70);
	}

	public static int getBlurrStage3() {
		return readIntegerPref(KEY_BLURR_STAGE_3, 80);
	}

	public static boolean isRandomStartwinkel() {
		return readBooleanPref("ramdomStartWinkel", true);
	}

	public static boolean isCounterClockwise() {
		return readBooleanPref(KEY_COUNTER_CLOCKWISE, false);
	}

	// ###################################################################
	// Options Pattern Style

	public static String getSelectedPattern() {
		return readStringPref(KEY_PATTERN_PATTERN_PICKER, "Virus Attack");
	}

	public static String getSelectedPatternVariant() {
		return readStringPref(KEY_PATTERN_PATTERN_VARIANT_PICKER, "V1");
	}

	public static float getPatternSizeFactor() {
		return readIntegerPref("patternSizeAdjust", 100) / 100f;
	}

	public static float getPatternMinSizeFactor() {
		return readIntegerPref(KEY_PATTERN_MIN_SIZE_ADJUST, 10) / 100f;
	}

	public static String getFilledOption() {
		return readStringPref(KEY_PATTERN_FILLED_OPTION, "Filled");
	}

	public static String getTailOption() {
		return readStringPref(KEY_TAIL_OPTION, "With Tail");
	}

	private final static CubeOptions cubeOptions = new CubeOptions();

	public static CubeOptions getCubeOptions() {
		cubeOptions.minLength = readIntegerPref("CubeOptions.minLength", 10) / 10f;
		cubeOptions.maxLength = readIntegerPref("CubeOptions.maxLength", 30) / 10f;
		cubeOptions.brightnessSide1 = readIntegerPref("CubeOptions.brightnessSide1", -36);
		cubeOptions.brightnessSide2 = readIntegerPref("CubeOptions.brightnessSide2", -24);
		cubeOptions.individualDropShadow = readBooleanPref("CubeOptions.individualDropShadow", false);
		return cubeOptions;
	}

	private final static CircularMazeOptions circularMazeOptions = new CircularMazeOptions();

	public static CircularMazeOptions getCircularMazeOptions() {
		circularMazeOptions.anzArcs = readIntegerPref("CMazeOptions.anzArcs", 15);
		circularMazeOptions.minSweep = readIntegerPref("CMazeOptions.minSweep", 90);
		circularMazeOptions.maxSweep = readIntegerPref("CMazeOptions.maxSweep", 270);
		circularMazeOptions.minThickness = readIntegerPref("CMazeOptions.minThickness", 2) / 100f;
		circularMazeOptions.maxThickness = readIntegerPref("CMazeOptions.maxThickness", 20) / 100f;
		circularMazeOptions.minBrightness = readIntegerPref("CMazeOptions.minBrightness", -48);
		circularMazeOptions.maxBrightness = readIntegerPref("CMazeOptions.maxBrightness", 48);
		circularMazeOptions.minSegments = readIntegerPref("CMazeOptions.minSegments", 1);
		circularMazeOptions.maxSegments = readIntegerPref("CMazeOptions.maxSegments", 1);
		circularMazeOptions.coloringType = ComposedPathColoringType.enumForName(readStringPref("CMazeOptions.coloringType", "Normal"));
		circularMazeOptions.outlineShift = readIntegerPref("CMazeOptions.outlineShift", 0);
		return circularMazeOptions;
	}

	// ************************************************************************

	private final static TailOptionsLine lineOptions = new TailOptionsLine();
	private final static EyeOptions eyeOptions = new EyeOptions();

	public static EyeOptions getEyeOptions() {
		eyeOptions.minBrightness = readIntegerPref("EyeOptions.minBrightness", 60);
		eyeOptions.maxBrightness = readIntegerPref("EyeOptions.maxBrightness", 90);
		eyeOptions.minAnzEyes = readIntegerPref("EyeOptions.minAnzEyes", 4);
		eyeOptions.maxAnzEyes = readIntegerPref("EyeOptions.maxAnzEyes", 5);
		return eyeOptions;
	}

	public static TailOptionsLine getTailOptionsLine() {
		lineOptions.anzTails = readIntegerPref("TailOptionsLine.anzTails", 25);
		lineOptions.tailRotationType = TailRotationType.enumForName(readStringPref("TailOptionsLine.tailRotationType", "Even"));
		lineOptions.sinusAmplitudeType = SinusAmplitudeType.enumForName(readStringPref("TailOptionsLine.sinusAmplitudeType", "Normal"));

		lineOptions.randomFlip = readBooleanPref("TailOptionsLine.randomFlip", false);
		lineOptions.outline = readBooleanPref("TailOptionsLine.outline", true);
		lineOptions.colorful = readBooleanPref("TailOptionsLine.colorful", true);

		lineOptions.minAmplitude = readIntegerPref("TailOptionsLine.minAmplitude", 1) / 10f;
		lineOptions.maxAmplitude = readIntegerPref("TailOptionsLine.maxAmplitude", 3) / 10f;

		lineOptions.minSinusRepeats = readIntegerPref("TailOptionsLine.minSinusRepeats", 1);
		lineOptions.maxSinusRepeats = readIntegerPref("TailOptionsLine.maxSinusRepeats", 3);

		lineOptions.minLength = readIntegerPref("TailOptionsLine.minLength", 25) / 10f;
		lineOptions.maxLength = readIntegerPref("TailOptionsLine.maxLength", 50) / 10f;

		lineOptions.minBrightness = readIntegerPref("TailOptionsLine.minBrightness", 40);
		lineOptions.maxBrightness = readIntegerPref("TailOptionsLine.maxBrightness", 90);

		lineOptions.inset = readIntegerPref("TailOptionsLine.inset", 10) / 10f;
		return lineOptions;
	}

	private final static TailOptionsBubbles bubbleOptions = new TailOptionsBubbles();

	public static TailOptionsBubbles getTailOptionsBubbles() {
		bubbleOptions.anzTails = readIntegerPref("TailOptionsBubbles.anzTails", 6);
		bubbleOptions.tailRotationType = TailRotationType.enumForName(readStringPref("TailOptionsBubbles.tailRotationType", "Even"));

		bubbleOptions.sinusAmplitudeType = SinusAmplitudeType.enumForName(readStringPref("TailOptionsBubbles.sinusAmplitudeType", "Normal"));
		bubbleOptions.sinusObjectsSizingType = ESinusObjectsSizingType.enumForName(readStringPref("TailOptionsBubbles.sinusObjectSizingType", "Decreasing"));
		bubbleOptions.sinusObjectType = ESinusObjectsType.enumForNameUsedInQualle(readStringPref("TailOptionsBubbles.sinusObjectType", "Bubble"));
		// Log.i("SizingType", "=" + options.sinusObjectsSizingType);
		bubbleOptions.randomFlip = readBooleanPref("TailOptionsBubbles.randomFlip", false);
		bubbleOptions.outline = false;
		bubbleOptions.colorful = readBooleanPref("TailOptionsBubbles.colorful", true);

		bubbleOptions.minAmplitude = readIntegerPref("TailOptionsBubbles.minAmplitude", 1) / 10f;
		bubbleOptions.maxAmplitude = readIntegerPref("TailOptionsBubbles.maxAmplitude", 3) / 10f;

		bubbleOptions.minSinusRepeats = readIntegerPref("TailOptionsBubbles.minSinusRepeats", 1);
		bubbleOptions.maxSinusRepeats = readIntegerPref("TailOptionsBubbles.maxSinusRepeats", 3);

		bubbleOptions.minLength = readIntegerPref("TailOptionsBubbles.minLength", 25) / 10f;
		bubbleOptions.maxLength = readIntegerPref("TailOptionsBubbles.maxLength", 50) / 10f;

		bubbleOptions.minBrightness = readIntegerPref("TailOptionsBubbles.minBrightness", 40);
		bubbleOptions.maxBrightness = readIntegerPref("TailOptionsBubbles.maxBrightness", 90);

		bubbleOptions.bubbleRadius = readIntegerPref("TailOptionsBubbles.bubbleRadius", 10) / 100f;

		bubbleOptions.percentOfBubblesToDraw = readIntegerPref("TailOptionsBubbles.percentOfBubblesToDraw", 100);
		return bubbleOptions;
	}

	public static boolean getFilledBoolean() {
		boolean filled;
		switch (Settings.getFilledOption()) {
		default:
		case "Not filled":
			filled = false;
			break;
		case "Filled":
			filled = true;
			break;
		case "Randomly mixed":
			filled = Randomizer.getRandomBoolean();
			break;
		}
		return filled;
	}

	public static boolean getTailBoolean() {
		boolean filled;
		switch (Settings.getTailOption()) {
		case "No Tail":
			filled = false;
			break;
		default:
		case "With Tail":
			filled = true;
			break;
		case "Randomly mixed":
			filled = Randomizer.getRandomBoolean();
			break;
		}
		return filled;
	}

	public static boolean isColorfulDrawing() {
		return readBooleanPref(KEY_COLORFUL_DRAWING, false);
	}

	public static boolean isClosedSineTrail() {
		return readBooleanPref(KEY_CLOSED_SINE_TRAIL, false);
	}

	public static int getScenePercentageOfCircles() {
		return readIntegerPref(KEY_SCENE_PERCENTAGE_OF_CIRCLES, 5);
	}

	// ###################################################################
	// Rotating

	public static String getRotationStyle() {
		return readStringPref(KEY_ROTATING_STYLE, "Fixed");
	}

	public static boolean isRandomLeftRightFlipping() {
		return readBooleanPref(KEY_FLIP_RANDOM_LEFT_RIGHT, false);
	}

	public static boolean isRandomUpDownFlipping() {
		return readBooleanPref(KEY_FLIP_RANDOM_UP_DOWN, false);
	}

	public static boolean isRandomDegreesAdding() {
		return getRandomDegreesAddingAmount() != 0;
	}

	public static int getRandomDegreesAddingAmount() {
		return readIntegerPref(KEY_RANDOM_DEGREES_ADDING, 0);
	}

	public static float getIncrementingDegreesAddingAmount() {
		return readIntegerPref(KEY_INCREMENTING_DEGREES_ADDING, 0) * 0.1f;
	}

	public static boolean isRandomRotate() {
		return getRotationStyle().equals("Random");
	}

	public static int getFixedRotationDegrees() {
		return readIntegerPref("rotationDegrees", 0);
	}

	public static int getrandomRangeDegrees() {
		return readIntegerPref("randomRange", 180);
	}

	// ###################################################################
	// Special Settings Glossy

	public static boolean isGlossy() {
		return readBooleanPref(KEY_PATTERN_GLOSSY, false);
	}

	public static int getGlossyCenterGlowBrighness() {
		return readIntegerPref(KEY_GLOSSY_CENTER_GLOW_BRIGHTNESS, 64);
	}

	public static int getGlossyCenterGlowOuterDarkness() {
		return readIntegerPref(KEY_GLOSSY_CENTER_GLOW_OUTER_DARKNESS, -32);
	}

	public static int getGlossyReflectionBrightness() {
		return readIntegerPref(KEY_GLOSSY_REFLECTION_BRIGHTNESS, 64);
	}

	public static GLOSSY_REFLECTIONS_STYLE getGlossyReflectionStyle() {
		return GLOSSY_REFLECTIONS_STYLE.enumForName(getGlossyReflectionStyleString());
	}

	public static String getGlossyReflectionStyleString() {
		return readStringPref(KEY_GLOSSY_REFLECTION_STYLE, "Diagonal");
	}

	public static GLOSSY_GLOW_STYLE getGlossyGlowStyle() {
		return GLOSSY_GLOW_STYLE.enumForName(getGlossyGlowStyleString());
	}

	public static String getGlossyGlowStyleString() {
		return readStringPref(KEY_GLOSSY_GLOW_STYLE, "Center");
	}

	// ###################################################################
	// RadiusType
	public static String getRadiusTypeString() {
		return readStringPref(KEY_RADIUS_TYPE, "Random");
	}

	public static RADIUS_TYPE getRadiusType() {
		return RADIUS_TYPE.enumForName(getRadiusTypeString());
	}

	// ###################################################################
	// Special Settings
	public static boolean isOutline() {
		return readBooleanPref(KEY_PATTERN_OUTLINE, true);
	}

	public static boolean isOutlineNeverTransparent() {
		return readBooleanPref(KEY_PATTERN_OUTLINE_NEVER_TRANSPARENT, false);
	}

	public static boolean isOutlineOnly() {
		if (isOutline()) {
			return Randomizer.getRandomBooleanTrueInPercentOfCases(Settings.getPercentageOfOutlineOnly());
		} else {
			return false;
		}
	}

	public static int getPercentageOfOutlineOnly() {
		return readIntegerPref(KEY_PERCENTAGE_OF_OUTLINE_ONLY, 0);
	}

	public static boolean isCustomOutlineColor() {
		return readBooleanPref("customOutlineColor", false);
	}

	public static int getCustomOutlineColor() {
		return readIntegerPref(KEY_COLOR_OUTLINE, R.integer.COLOR_BLACK);
	}

	public static int getOutlineDarkness() {
		return readIntegerPref(KEY_PATTERN_OUTLINE_DARKNESS_ADJUST, -48);
	}

	public static float getOutlineThicknessAdjustment() {
		return readIntegerPref(KEY_OUTLINE_THICKNESS_ADJUST, 100) / 100f;
	}

	public static int getOutlineThicknessLimit() {
		return readIntegerPref(KEY_OUTLINE_THICKNESS_LIMIT, 3);
	}

	public static int getDropShadowDarkness() {
		return readIntegerPref(KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST, -48);
	}

	public static int getDropShadowOffsetX() {
		return readIntegerPref(KEY_DROP_SHADOW_OFFSET_X, 0);
	}

	public static int getDropShadowOffsetY() {
		return readIntegerPref(KEY_DROP_SHADOW_OFFSET_Y, 0);
	}

	public static String getText() {
		return readStringPref(KEY_PATTERN_TEXT, "The Wallpaper Designer");
	}

	public static String getTextDrawStyle() {
		return readStringPref(KEY_PATTERN_TEXT_DRAW_STYLE, "Round");
	}

	// ###################################################################
	// Options Pattern Style (DropShadow)
	private static String getDropShadowTypeString() {
		return readStringPref(KEY_PATTERN_DROPSHADOW_TYPE, "Darker");
	}

	public static boolean isDropShadow() {
		return !getDropShadowTypeString().equals("No");
	}

	public static DROP_SHADOW_TYPE getDropShadowType() {
		return DROP_SHADOW_TYPE.enumForName(getDropShadowTypeString());
	}

	public static int getDropShadowColor() {
		return readIntegerPref(KEY_PATTERN_DROPSHADOW_COLOR, R.integer.COLOR_BLACK);
	}

	public static float getDropShadowRadiusAdjustment() {
		return readIntegerPref(KEY_DROP_SHADOW_RADIUS_ADJUSTMENT, 100) / 100f;
	}

	// ###################################################################
	public static int getMinOpacity() {
		return readIntegerPref(KEY_MIN_OPACITY, 225);
	}

	public static int getMaxOpacity() {
		return readIntegerPref(KEY_MAX_OPACITY, 255);
	}

	// ###################################################################
	// Options Pattern Style (Color)

	@Deprecated
	public static int getOldRandomizeColorRange() {
		return readIntegerPref(OLD_KEY_RANDOMIZE_COLOR_RANGE_INT, 12);
	}

	private static final ColorRandOptions colorRandomizingOptions = new ColorRandOptions();

	public static ColorRandOptions getColorRandomizingOptions() {
		colorRandomizingOptions.randomizingType = COLOR_RANDOMIZING_TYPE.enumForName(readStringPref(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "full RGB"));
		colorRandomizingOptions.minColorRange = readIntegerPref(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -32);
		colorRandomizingOptions.maxColorRange = readIntegerPref(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, 32);
		colorRandomizingOptions.minBrightnessRange = readIntegerPref(ColorRandOptions.KEY_COLOR_MIN_BRIGHTNESS_RANGE, -32);
		colorRandomizingOptions.maxBrightnessRange = readIntegerPref(ColorRandOptions.KEY_COLOR_MAX_BRIGHTNESS_RANGE, 32);
		colorRandomizingOptions.minSaturationRange = readIntegerPref(ColorRandOptions.KEY_COLOR_MIN_SATURATION_RANGE, 0);
		colorRandomizingOptions.maxSaturationRange = readIntegerPref(ColorRandOptions.KEY_COLOR_MAX_SATURATION_RANGE, 0);
		return colorRandomizingOptions;
	}

	@Deprecated
	public static int getOldRandomizeColorBrighnessRange() {
		return readIntegerPref(OLD_KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT, 12);
	}

	@Deprecated
	public static int getOldRandomizeSaturationRange() {
		return readIntegerPref(OLD_KEY_RANDOMIZE_SATURATION_RANGE, 0);
	}

	public static int getCornerGradientLevels() {
		return readIntegerPref(KEY_CORNER_GRADIENT_LEVELS, 100);
	}

	public static int getCornerRepeats() {
		return readIntegerPref(KEY_CORNER_REPEATS, 1);
	}

	// ###################################################################
	// Wallpater Size
	public static int getAnzahlFlowerLeafs(final int randomMin, final int randomMax) {
		if (isRandomLeafCount()) {
			return Randomizer.getRandomInt(randomMin, randomMax);
		}
		return readIntegerPref(KEY_NUMBER_OF_LEAFS, 5);
	}

	private static boolean isRandomLeafCount() {
		return readBooleanPref(KEY_RANDOM_LEAF_COUNT, false);
	}

	// ###################################################################
	// Wallpater Size

	public static String getSizeSelection() {
		return readStringPref(KEY_SIZE_SELECTION, "2560x1600");
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

	/**
	 * securely read number from String
	 *
	 * @param key
	 * @param defValue
	 * @param maxLength
	 * @return
	 */
	private static int readNumberFromSettings(final String key, final int defValue, final int maxLength) {
		// some idiot was typing 100000000 :-)
		String w = readStringPref(key, "" + defValue);
		// some idiot was typing 100000000 :-)
		if (w.length() > maxLength) {
			w = "" + defValue;
		}
		try {
			final int val = Integer.valueOf(w);
			return val;
		} catch (final NumberFormatException e) {
			return defValue;
		}
	}

	public static int getCustomWidth() {
		return readNumberFromSettings(KEY_B_WIDTH, 2560, 4);
	}

	public static int getCustomHeight() {
		return readNumberFromSettings(KEY_B_HEIGHT, 1600, 4);
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
		return readBooleanPref(KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT, true);
	}

	public static String getGradientDirection() {
		return readStringPref(KEY_COLOR_GRADIENT_DIRECTION, "4-Color Gradient from corners");
	}

	public static int getAnzahlGradientColors() {
		return Integer.parseInt(readStringPref(KEY_COLORS_ANZAHL, "4"));
	}

	public static boolean isReverseColors() {
		return readBooleanPref(KEY_REVERSE_COLORS, false);
	}

	public static boolean is4ColorGradient(final String test) {
		return test.startsWith("4");
	}

	public static boolean isLinearGradient(final String test) {
		return (test.startsWith("Linear") || test.startsWith("Radial") || test.startsWith("Sweep Gradient")) && !test.equals("Sweep Gradient (++)");
	}

	public static boolean isSweepGradientPlusPlus(final String test) {
		return test.equals("Sweep Gradient (++)");
	}

	public static boolean is4ColorCornerGradient(final String test) {
		return test.equals("4-Color Gradient from corners");
	}

	public static boolean is4ColorCorner(final String test) {
		return test.equals("4-Colors in corners");
	}

	public static int getColorRepeats() {
		return readIntegerPref(KEY_COLOR_REPEATS, 1);
	}

	public static boolean isTornadoGradient(final String test) {
		return test.startsWith("4-Color Tornado");
	}

	public static boolean isCustomBackground(final String test) {
		return test.startsWith("Custom Image");
	}

	public static int getTornadoArms() {
		return readIntegerPref(KEY_TORNADO_ARMS, 1);
	}

	public static int getTornadoRings() {
		return readIntegerPref(KEY_TORNADO_RINGS, 1) + 1;
	}

	public static int getPatternColor1() {
		return readIntegerPref(KEY_COLOR1, R.integer.COLOR_BLACK);
	}

	public static int getPatternColor2() {
		return readIntegerPref(KEY_COLOR2, R.integer.COLOR_WHITE);
	}

	public static int getPatternColor3() {
		return readIntegerPref(KEY_COLOR3, Color.BLUE);
	}

	public static int getPatternColor4() {
		return readIntegerPref(KEY_COLOR4, Color.YELLOW);
	}

	public static int getBackGrndColor1() {
		return readIntegerPref(KEY_BGRND_COLOR1, Color.BLACK);
	}

	public static int getBackGrndColor2() {
		return readIntegerPref(KEY_BGRND_COLOR2, Color.BLACK);
	}

	public static boolean isHexValueEnabled() {
		return readBooleanPref(KEY_HEX_VALUES, true);
	}

	public static boolean isExpertMode() {
		return readBooleanPref(KEY_EXPERT_MODE, false);
	}

	public static boolean isShowSetWallpaperButton() {
		return readBooleanPref(KEY_SHOW_SET_WALLPAPER_BUTTON, false);
	}

	public static boolean isRestoreSizeFromDesign() {
		return readBooleanPref(KEY_RESTORE_WALLPAPER_SIZE, true);
	}

	public static boolean isRenderingOnSettingsExit() {
		return readBooleanPref(KEY_RENDER_ON_SETTINGS_EXIT, true);
	}

	public static boolean isShowRenderingProcess() {
		return readBooleanPref(KEY_SHOW_RENDERING_PROCESS, false);
	}

	public static boolean isCreateGif() {
		return isShowRenderingProcess() && readBooleanPref(KEY_CREATE_GIF, false);
	}

	public static int getGifLength() {
		return readIntegerPref(KEY_CREATE_GIF_LENGTH, 3) * 1000; // Milli seconds
	}

	public static int getGifSize() {
		return readIntegerPref(KEY_CREATE_GIF_SIZE, 500);
	}

	public static int getGifQuality() {
		return readIntegerPref(KEY_CREATE_GIF_QUALITY, 10);
	}

	public static int getRenderingProcessFrames() {
		return Math.max(1, readIntegerPref(KEY_RENDERING_PROCESS_FRAMES, 10));
	}

	public static boolean isRenderingOnStartingApp() {
		return readBooleanPref(KEY_RENDER_ON_APP_STARTUP, true);
	}

	// ###################################################################
	// General stuff
	public static boolean isDebuggingMessages() {
		return readBooleanPref("debug2", false);
	}

	public static boolean isDebugging() {
		return readBooleanPref("debug", false);
	}

	public static boolean isPremium() {
		return readBooleanPref(KEY_MUIMERP, false);
	}

	// private static boolean readSuperUser(final Activity activity) {
	// final File file = new File(activity.getFilesDir(), "superuser.txt");
	// superuser = file.exists();
	// return superuser;
	// }

	public static boolean isSuperUser(final Activity activity) {
		final File file = new File(activity.getFilesDir(), "superuser.txt");
		superuser = file.exists();
		return superuser || StorageHelper.globalSuperUserExists();
	}

	// private static final int CURRENT_SETTINGS_VERSION = 2;

	/**
	 * Initializes some preferences on first run with defaults
	 *
	 * @param preferences
	 */
	public static void initPrefs(final SharedPreferences preferences, final Context ctx, final Activity activity) {
		prefs = preferences;
		if (readBooleanPref("firstrun", true)) {
			Log.i("GEITH", "FirstRun --> initializing the SharedPreferences with some colors...");
			prefs.edit().putBoolean("firstrun", false).commit();
			// init colors
			prefs.edit().putInt(KEY_COLOR1, 0xff005ea3).commit();
			prefs.edit().putInt(KEY_COLOR2, 0xff008d7e).commit();
			prefs.edit().putInt(KEY_COLOR3, 0xffffa000).commit();
			prefs.edit().putInt(KEY_COLOR4, 0xffa8003e).commit();
			prefs.edit().putString(KEY_COLOR_GRADIENT_DIRECTION, "Sweep Gradient (++)").commit();
			prefs.edit().putBoolean(Settings.KEY_REVERSE_COLORS, false).commit();
			prefs.edit().putInt(Settings.KEY_COLOR_REPEATS, 3).commit();

			prefs.edit().putString(KEY_SORT_ORDER, "Timestamp in Filename").commit();
			prefs.edit().putString(KEY_PATTERN_PATTERN_PICKER, "Fish").commit();
			prefs.edit().putString(KEY_PATTERN_PATTERN_VARIANT_PICKER, "V2").commit();
			prefs.edit().putInt(KEY_PATTERN_ANZAHL_PATTERNS, 1000).commit();
			prefs.edit().putString(KEY_PATTERN_FILLED_OPTION, "Filled").commit();

			prefs.edit().putBoolean(KEY_PATTERN_BLUR, false).commit();
			prefs.edit().putInt(KEY_PATTERN_DROPSHADOW_COLOR, Color.BLACK).commit();
			prefs.edit().putInt(KEY_PATTERN_MIN_SIZE_ADJUST, 50).commit();
			prefs.edit().putString(KEY_B_WIDTH, "1920").commit();
			prefs.edit().putString(KEY_B_HEIGHT, "1200").commit();
			prefs.edit().putString(KEY_SIZE_SELECTION, "1920x1200").commit();

			prefs.edit().putString(OLD_KEY_COLOR_RANDOMIZING_TYPE, "hue").commit();
			prefs.edit().putInt(OLD_KEY_RANDOMIZE_COLOR_RANGE_INT, 16).commit();
			prefs.edit().putInt(OLD_KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT, 24).commit();
			prefs.edit().putInt(KEY_MIN_OPACITY, 128).commit();
			prefs.edit().putInt(KEY_MAX_OPACITY, 255).commit();
			prefs.edit().putInt(KEY_DROP_SHADOW_RADIUS_ADJUSTMENT, 200).commit();

			prefs.edit().putInt(KEY_PATTERN_DROPSHADOW_DARKNESS_ADJUST, -104).commit();
			prefs.edit().putInt(KEY_PATTERN_OUTLINE_DARKNESS_ADJUST, +40).commit();

			prefs.edit().putInt(KEY_COLOR_OUTLINE, Color.BLACK).commit();
			prefs.edit().putBoolean(KEY_PATTERN_OUTLINE, true).commit();
			prefs.edit().putString(KEY_ROTATING_STYLE, "Random").commit();
			prefs.edit().putString(KEY_IMAGE_FORMAT, "jpg").commit();
			prefs.edit().putInt(KEY_JPG_COMPRESSION, 95).commit();
			prefs.edit().putBoolean(KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT, false).commit();
			prefs.edit().putInt(KEY_BLURR_AMOUNT_1, 40).commit();
			prefs.edit().putInt(KEY_BLURR_AMOUNT_2, 20).commit();
			prefs.edit().putInt(KEY_BLURR_AMOUNT_3, 10).commit();
			prefs.edit().putInt(KEY_BLURR_STAGE_1, 60).commit();
			prefs.edit().putInt(KEY_BLURR_STAGE_2, 70).commit();
			prefs.edit().putInt(KEY_BLURR_STAGE_3, 80).commit();
		}
	}

	public static void inflateAssets(final Context ctx, final boolean force) {
		if (readBooleanPref("assetsInflated", false) == false || force == true) {
			Log.i("GEITH", "Inflating Designs ...");
			ZipHelper.unzipResourceRawFiles(ctx, R.raw.circular_maze_design1);
			ZipHelper.unzipResourceRawFiles(ctx, R.raw.brick3d_design1);
			ZipHelper.unzipResourceRawFiles(ctx, R.raw.squares_design1);
			prefs.edit().putBoolean("assetsInflated", true).commit();
		}
	}

	public static String getCustomBackgroundFilePath() {
		if (prefs == null) {
			return "aaa";
		}
		final String filePath = prefs.getString(KEY_BACKGROUND_PICKER, "aaa");
		return filePath;
	}

	public static void setCustomBackgroundFilePath(final String filePath) {
		final SharedPreferences.Editor editor = prefs.edit();
		editor.putString(KEY_BACKGROUND_PICKER, filePath);
		editor.commit();
	}

}
