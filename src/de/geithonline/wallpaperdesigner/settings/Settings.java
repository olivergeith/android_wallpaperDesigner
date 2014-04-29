package de.geithonline.wallpaperdesigner.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.BatteryManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBitmapDrawer;

public class Settings {
	public static SharedPreferences prefs;
	private static Context context;
	private static String style = "aaa";
	private static IBitmapDrawer bitmapDrawer;
	public static final int ANIMATION_STYLE_0_TO_100 = 1;
	public static final int ANIMATION_STYLE_0_TO_LEVEL = 2;

	public static boolean isCharging = false;
	public static boolean isChargeUSB = false;
	public static boolean isChargeAC = false;
	public static int battTemperature = -1;
	public static int battHealth = -1;
	public static int battVoltage = -1;
	public static int iconSize;
	private static Bitmap defaultlogo;
	private static Bitmap mask;
	private static String maskname = "000";
	private static Bitmap maskicon;

	public static final int BATT_STATUS_STYLE_TEMP_VOLT_HEALTH = 0;
	public static final int BATT_STATUS_STYLE_TEMP_VOLT = 1;
	public static final int BATT_STATUS_STYLE_TEMP = 2;
	public static final int BATT_STATUS_STYLE_VOLT = 3;

	public static boolean isKeepAspectRatio() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("keepAspectRatio", true);
	}

	public static boolean isScaleTransparent() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("scale_numbers_transparent", true);
	}

	public static int getScaleColor() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("scale_color", R.integer.COLOR_WHITE);
		return col;
	}

	public static int getBattStatusColor() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("status_color", R.integer.COLOR_WHITE);
		return col;
	}

	public static boolean isShowStatus() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("show_status", false);
	}

	private static int getStatusStyle() {
		if (prefs == null) {
			return 0;
		}
		final int stat = Integer.valueOf(prefs.getString("battStatusStyle", "0"));
		return stat;
	}

	public static String getBattStatusCompleteShort() {
		switch (getStatusStyle()) {
		case BATT_STATUS_STYLE_VOLT:
			return "Battery: " + (float) (battVoltage / 10) / 100 + "V";
		case BATT_STATUS_STYLE_TEMP:
			return "Battery: " + (float) battTemperature / 10 + "°C";
		case BATT_STATUS_STYLE_TEMP_VOLT:
			return "Battery: " + (float) battTemperature / 10 + "°C, " + (float) (battVoltage / 10) / 100 + "V";
		default:
		case BATT_STATUS_STYLE_TEMP_VOLT_HEALTH:
			return "Battery: health " + getHealthText(battHealth) + ", " + (float) battTemperature / 10 + "°C, " + (float) (battVoltage / 10) / 100 + "V";
		}
	}

	public static String getBattTemperatureString() {
		return "Temperature is " + (float) battTemperature / 10 + " °C";
	}

	public static String getBattHealthString() {
		return "Health is " + getHealthText(battHealth);
	}

	public static String getBattVoltageString() {
		return "Voltage is " + (float) battVoltage / 1000 + "V";
	}

	private static String getHealthText(final int health) {
		switch (health) {
		case BatteryManager.BATTERY_HEALTH_GOOD:
			return "good";
		case BatteryManager.BATTERY_HEALTH_OVERHEAT:
			return "overheat";
		case BatteryManager.BATTERY_HEALTH_DEAD:
			return "dead";
		case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
			return "overvoltage";
		case BatteryManager.BATTERY_HEALTH_COLD:
			return "cold";
		case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
			return "failure";

		default:
			return "unknown";
		}
	}

	public static boolean isShowRand() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("show_rand", true);
	}

	public static boolean isShowNumber() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("show_number", true);
	}

	public static int getNumberColor() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("number_color", R.integer.COLOR_WHITE);
		return col;
	}

	public static int getChargeStatusColor() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("chargestatus_color", R.integer.COLOR_WHITE);
		return col;
	}

	public static int getChargeColor() {
		if (prefs == null) {
			return R.integer.COLOR_GREEN;
		}
		final int col = prefs.getInt("charge_color", R.integer.COLOR_GREEN);
		return col;
	}

	public static boolean isUseChargeColors() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("charge_colors_enable", false);
	}

	public static boolean isShowChargeState() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("charge_enable", true);
	}

	public static String getChargingText() {
		String text;
		if (isChargeUSB) {
			text = "Charging on USB";
		} else if (isChargeAC) {
			text = "Charging on AC";
		} else {
			text = "Charging...";
		}
		return text;
	}

	public static int getVerticalPositionOffset(final boolean isPortrait) {
		final int defVal = 0;
		if (prefs == null) {
			return defVal;
		}
		if (isPortrait) {
			return Integer.valueOf(prefs.getString("vertical_position", "0"));
		} else {
			return Integer.valueOf(prefs.getString("vertical_position_landscape", "0"));
		}
	}

	public static boolean isAnimationEnabled() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("animation_enable", true);
	}

	public static boolean isShowZeiger() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("show_zeiger", true);
	}

	public static int getAnimationDelaý() {
		if (prefs == null) {
			return 50;
		}
		final int thr = Integer.valueOf(prefs.getString("animation_delay", "50"));
		return thr;
	}

	public static int getAnimationDelaýOnCurrentLevel() {
		if (prefs == null) {
			return 2500;
		}
		final int thr = Integer.valueOf(prefs.getString("animation_delay_level", "2500"));
		return thr;
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

	public static int getAnimationStyle() {
		if (prefs == null) {
			return 1;
		}
		final int size = Integer.valueOf(prefs.getString("animationStyle", "1"));
		return size;
	}

	public static boolean isCenteredBattery() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("centerBattery", true);
	}

	public static int getFontSize() {
		if (prefs == null) {
			return 100;
		}
		final int size = Integer.valueOf(prefs.getString("fontsize", "100"));
		return size;
	}

	public static int getFontSize100() {
		if (prefs == null) {
			return 100;
		}
		final int size = Integer.valueOf(prefs.getString("fontsize100", "100"));
		return size;
	}

	public static boolean isColoredNumber() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("colored_numbers", false);
	}

	public static boolean isGradientColors() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("gradient_colors", false);
	}

	public static boolean isGradientColorsMid() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("gradient_colors_mid", false);
	}

	public static int getMidThreshold() {
		if (prefs == null) {
			return 30;
		}
		final int thr = Integer.valueOf(prefs.getString("threshold_mid", "30"));
		return thr;
	}

	public static int getLowThreshold() {
		if (prefs == null) {
			return 10;
		}
		final int thr = Integer.valueOf(prefs.getString("threshold_low", "10"));
		return thr;
	}

	public static int getBattColor() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("battery_color", R.integer.COLOR_WHITE);
		return col;
	}

	public static int getZeigerColor() {
		if (prefs == null) {
			return R.integer.COLOR_WHITE;
		}
		final int col = prefs.getInt("color_zeiger", R.integer.COLOR_WHITE);
		return col;
	}

	public static boolean isZeigerSRCIN() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("zeiger_srcin", false);
	}

	public static float getZeigerLength() {
		if (prefs == null) {
			return 1.0f;
		}
		final float size = prefs.getFloat("zeiger_length", 1.0f);
		return size;
	}

	public static int getBattColorMid() {
		if (prefs == null) {
			return R.integer.COLOR_ORANGE;
		}
		final int col = prefs.getInt("battery_color_mid", R.integer.COLOR_ORANGE);
		return col;
	}

	public static int getBattColorLow() {
		if (prefs == null) {
			return R.integer.COLOR_RED;
		}
		final int col = prefs.getInt("battery_color_low", R.integer.COLOR_RED);
		return col;
	}

	// #####################################################################################
	// Styles
	// #####################################################################################
	public static IBitmapDrawer getBatteryStyle() {
		// wenns den drawer noch nicht gibt, oder der style sich geändert hat
		if (bitmapDrawer == null || !style.equals(getStyle())) {
			// getting Style from Settings
			style = getStyle();
			// returning the right Style
			bitmapDrawer = DrawerManager.getDrawer(style);
			return bitmapDrawer;
		}
		return bitmapDrawer;
	}

	public static String getStyle() {
		if (prefs == null) {
			return "LogoV1";
		}
		return prefs.getString("batt_style", "LogoV1");
	}

	// #####################################################################################
	// Custom Background
	// #####################################################################################
	public static boolean isLoadCustomBackground() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("customBackground", true);
	}

	/**
	 * @return Bitmap or null...
	 */
	private static Bitmap getCustomImageSampled(final String filePath, final int reqWidth, final int reqHeight) {
		if (!filePath.equals("aaa")) {
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			options.inDither = false; // Disable Dithering mode
			options.inPurgeable = true; // Tell to gc that whether it needs free
										// memory, the Bitmap can be cleared
			options.inInputShareable = true; // Which kind of reference will be
												// used to recover the Bitmap
												// data after being clear, when
												// it will be used in the future
			options.inTempStorage = new byte[32 * 1024];
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, options);
			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			final Bitmap b = BitmapFactory.decodeFile(filePath, options);
			return b;
		}
		return null;
	}

	/**
	 * @return Bitmap or null...
	 */
	private static Bitmap getCustomResourceSampled(final int resourceID, final int reqWidth, final int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inDither = false; // Disable Dithering mode
		options.inPurgeable = true; // Tell to gc that whether it needs free
									// memory, the Bitmap can be cleared
		options.inInputShareable = true; // Which kind of reference will be
											// used to recover the Bitmap
											// data after being clear, when
											// it will be used in the future
		options.inTempStorage = new byte[32 * 1024];
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), resourceID, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		final Bitmap b = BitmapFactory.decodeResource(context.getResources(), resourceID, options);
		return b;
	}

	public static int calculateInSampleSize(final BitmapFactory.Options options, final int reqWidth, final int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}
		Log.i("GEITH", "Samplesize =" + inSampleSize);
		return inSampleSize;
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
			return false;
		}
		return prefs.getBoolean("gradientBackground", false);
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

	public static int getIconSize() {
		return iconSize;
	}

	private static int getDisplayWidth(final Context context) {
		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		if (metrics.widthPixels < metrics.heightPixels) {
			return metrics.widthPixels;
		} else {
			return metrics.heightPixels;
		}
	}

	public static float getPortraitResizeFactor() {
		if (prefs == null) {
			return 1.0f;
		}
		return prefs.getFloat("resizePortrait", 1.0f);
	}

	public static float getLandscapeResizeFactor() {
		if (prefs == null) {
			return 1.0f;
		}
		return prefs.getFloat("resizeLandscape", 1.0f);
	}

	public static float getChargeStatusRadiusFactor() {
		if (prefs == null) {
			return 0.8f;
		}
		final float size = prefs.getFloat("chargestatus_radius", 0.8f);
		return size;
	}

	public static float getBattStatusRadiusFactor() {
		if (prefs == null) {
			return 1.0f;
		}
		final float size = prefs.getFloat("battstatus_radius", 1.0f);
		return size;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++
	// logo and more
	// ++++++++++++++++++++++++++++++++++++++++++++++++++
	public static boolean useCustomLogo() {
		if (prefs == null) {
			return true;
		}
		return prefs.getBoolean("customLogo", false);
	}

	public static Bitmap getDefaultLogoForDrawer(final int size) {
		return Bitmap.createScaledBitmap(defaultlogo, size, size, true);
	}

	public static String getCustomLogoFilePath() {
		if (prefs == null) {
			return "aaa";
		}
		final String filePath = prefs.getString("logoPicker", "aaa");
		return filePath;
	}

	/**
	 * @return Bitmap or null...
	 */
	public static Bitmap getCustomLogoSampled(final int reqWidth, final int reqHeight) {
		final Bitmap defaultBmp = getDefaultLogoForDrawer(reqWidth);
		if (useCustomLogo()) {
			final String filePath = getCustomLogoFilePath();
			if (filePath != null && !filePath.equals("aaa")) {
				final Bitmap b = getCustomImageSampled(filePath, reqWidth, reqHeight);
				if (b != null) {
					final Bitmap bOut = Bitmap.createScaledBitmap(b, reqWidth, reqHeight, true);
					if (!bOut.equals(b)) {
						b.recycle();
					}
					return bOut;
				}
			}
		}
		return defaultBmp;
	}

	public static String getMaskName() {
		if (prefs == null) {
			return "0";
		}
		final String name = prefs.getString("maskList", "0");
		return name;
	}

	public static boolean isMaskLogo() {
		if (prefs == null) {
			return false;
		}
		return !getMaskName().endsWith("No Mask");
	}

	public static float getLogoBackgroundBrightness() {
		if (prefs == null) {
			return 1.0f;
		}
		return prefs.getFloat("logo_background_brightness", 1.0f);
	}

	public static float getLogoHue() {
		if (prefs == null) {
			return 0.5f;
		}
		return prefs.getFloat("logo_hue", 0.5f);
	}

	public static Bitmap getLogoMaskCached(final int bWidth, final int bHeight) {
		if (prefs == null) {
			mask = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.maskstar), bWidth, bHeight, true);
		} else {
			if (!maskname.equals(getMaskName())//
					|| bWidth != mask.getWidth() //
					|| bHeight != mask.getHeight()) {
				maskname = getMaskName();
				mask = getMask(maskname, bWidth, bHeight);
			}
		}
		return mask;
	}

	public static Bitmap getLogoMaskIconCached(final String name, final int bWidth, final int bHeight) {
		maskicon = getMask(name, bWidth, bHeight);
		return maskicon;
	}

	private static Bitmap getMask(final String name, final int bWidth, final int bHeight) {
		Bitmap bm;
		int resid = 0;
		switch (name) {
		default:
		case "No Mask":
			resid = R.drawable.masknon;
			break;
		case "Battery 1":
			resid = R.drawable.maskbattery;
			break;
		case "Battery 2":
			resid = R.drawable.maskbattery2;
			break;
		case "Battery 3":
			resid = R.drawable.maskbattery3;
			break;
		case "Battery 4":
			resid = R.drawable.maskbattery4;
			break;
		case "Star":
			resid = R.drawable.maskstar;
			break;
		case "Rounded Square":
			resid = R.drawable.maskroundedrect;
			break;
		case "Cicle":
			resid = R.drawable.maskcircle;
			break;
		case "Flower":
			resid = R.drawable.maskflower;
			break;
		case "Flower 2":
			resid = R.drawable.maskflower2;
			break;
		case "Flower 3":
			resid = R.drawable.maskflower3;
			break;
		case "Gear 1":
			resid = R.drawable.maskgear1;
			break;
		case "Gear 2":
			resid = R.drawable.maskgear2;
			break;
		case "Gear 3":
			resid = R.drawable.maskgear3;
			break;
		case "Gear 4":
			resid = R.drawable.maskgear4;
			break;
		case "Saw":
			resid = R.drawable.masksaw;
			break;
		case "Saw 2":
			resid = R.drawable.masksaw2;
			break;
		case "Saw 3":
			resid = R.drawable.masksaw3;
			break;
		case "Splash":
			resid = R.drawable.masksplash;
			break;
		case "Square 1":
			resid = R.drawable.masksquare;
			break;
		case "Square 2":
			resid = R.drawable.masksquare2;
			break;
		case "Sun 1":
			resid = R.drawable.masksun1;
			break;
		case "Sun 2":
			resid = R.drawable.masksun2;
			break;
		case "Sun 3":
			resid = R.drawable.masksun3;
			break;
		case "Ring 1":
			resid = R.drawable.maskring1;
			break;
		case "Ring 2":
			resid = R.drawable.maskring2;
			break;
		case "Ring 3":
			resid = R.drawable.maskring3;
			break;
		case "Wheel":
			resid = R.drawable.maskwheel;
			break;
		case "Heart":
			resid = R.drawable.maskheart;
			break;
		case "Heart filled":
			resid = R.drawable.maskheart2;
			break;
		case "Puzzle":
			resid = R.drawable.maskpuzzle;
			break;
		case "Rom Omni":
			resid = R.drawable.maskomni;
			break;
		case "Rom CyanogenMOD":
			resid = R.drawable.maskcanogenmod;
			break;
		case "Rom Pacman":
			resid = R.drawable.maskpackman;
			break;
		case "Rom AOKP":
			resid = R.drawable.maskaokp;
			break;
		case "Easter-Egg":
			resid = R.drawable.maskeasteregg;
			break;
		}
		bm = Bitmap.createScaledBitmap(getCustomResourceSampled(resid, bWidth, bWidth), bWidth, bHeight, true);
		return bm;
	}

	public static boolean isFlip() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("flip", false);
	}

	public static boolean isReColorBitmap() {
		if (prefs == null) {
			return false;
		}
		return prefs.getBoolean("recolor", false);
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
	public static void initPrefs(final SharedPreferences preferences, final Context context) {
		Settings.context = context;
		prefs = preferences;
		if (prefs.getBoolean("firstrun", true)) {
			Log.i("GEITH", "FirstRun --> initializing the SharedPreferences with some colors...");
			prefs.edit().putBoolean("firstrun", false).commit();
			// init colors
			prefs.edit().putInt("scale_color", Color.WHITE).commit();
			prefs.edit().putInt("status_color", Color.WHITE).commit();
			prefs.edit().putInt("charge_color", Color.GREEN).commit();
			prefs.edit().putInt("battery_color", Color.WHITE).commit();
			prefs.edit().putInt("background_color", Color.DKGRAY).commit();
			prefs.edit().putInt("battery_color_mid", Color.YELLOW).commit();
			prefs.edit().putInt("battery_color_low", Color.RED).commit();
			prefs.edit().putInt("color_zeiger", Color.WHITE).commit();
			prefs.edit().putBoolean("show_status", false).commit();
			prefs.edit().putInt("number_color", Color.WHITE).commit();
			prefs.edit().putInt("status_color", Color.WHITE).commit();
			prefs.edit().putInt("chargestatus_color", Color.WHITE).commit();
		}
		iconSize = Math.round(getDisplayWidth(context) * 0.15f);
		defaultlogo = BitmapFactory.decodeResource(context.getResources(), R.drawable.sun1);
		mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.maskstar);
	}

}
