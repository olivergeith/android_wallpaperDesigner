package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;

public class PreferenceIO {

	private static final String LOG_TAG = "PreferenceIO";
	public static final String MARKER = " (+++)_";
	private static List<String> ignoreKeys = new ArrayList<>();
	private static List<String> colorKeys = new ArrayList<>();

	private static List<String> sizeKeys = new ArrayList<>();

	static {
		ignoreKeys.add(Settings.KEY_MUIMERP);
		ignoreKeys.add(Settings.KEY_SORT_ORDER);
		ignoreKeys.add(Settings.KEY_IMAGE_FORMAT);
		ignoreKeys.add(Settings.KEY_JPG_COMPRESSION);
		ignoreKeys.add(Settings.KEY_HEX_VALUES);
		ignoreKeys.add(Settings.KEY_SHOW_SET_WALLPAPER_BUTTON);
		ignoreKeys.add(Settings.KEY_SHOW_RENDERING_PROCESS);
		ignoreKeys.add(Settings.KEY_RENDERING_PROCESS_FRAMES);
		ignoreKeys.add(Settings.KEY_SHARE_SUBJECT);
		ignoreKeys.add(Settings.KEY_SHARE_TEXT);
		ignoreKeys.add(Settings.KEY_APP_THEME);
		ignoreKeys.add("debug");

		colorKeys.add(Settings.KEY_COLOR1);
		colorKeys.add(Settings.KEY_COLOR2);
		colorKeys.add(Settings.KEY_COLOR3);
		colorKeys.add(Settings.KEY_COLOR4);
		// colorKeys.add(Settings.KEY_COLOR_GRADIENT_DIRECTION);
		// colorKeys.add(Settings.KEY_COLORS_ANZAHL);
		// colorKeys.add(Settings.KEY_RANDOMIZE_COLOR_RANGE_INT);
		// colorKeys.add(Settings.KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT);
		// colorKeys.add(Settings.KEY_RANDOMIZE_SATURATION_RANGE);
		// colorKeys.add(Settings.KEY_COLOR_RANDOMIZING_TYPE);

		sizeKeys.add(Settings.KEY_B_HEIGHT);
		sizeKeys.add(Settings.KEY_B_WIDTH);
		sizeKeys.add(Settings.KEY_SIZE_SELECTION);
	}

	/**
	 * @param activity
	 * @param prefs
	 * @param filename
	 * @param onlyColors
	 *            when true only colors are loaded
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, ?> loadPreferencesFromFile(final Activity activity, final SharedPreferences prefs, final String filename,
			final boolean onlyColors) {
		Log.i(LOG_TAG, "Loading Settings from " + filename);
		final File file = new File(getSettingsDir(), filename);
		if (file.exists()) {
			try {
				// Reading file
				final FileInputStream fi = new FileInputStream(file);
				final ObjectInputStream o = new ObjectInputStream(fi);
				final Map<String, ?> settings = (Map<String, ?>) o.readObject();
				o.close();
				fi.close();
				// Looping over Map
				final Set<String> keySet = settings.keySet();
				// erstmal alles schreiben...
				for (final Map.Entry<String, ?> entry : settings.entrySet()) {
					if (onlyColors) {
						if (colorKeys.contains(entry.getKey())) {
							writeEntry(entry, prefs, keySet);
						}
					} else {
						writeEntry(entry, prefs, keySet);
					}
				}
				// ... dann reparieren
				if (!onlyColors) {
					repairSomeDefaultValues(prefs, keySet);
					repairColorRandomizing(prefs, keySet);
					repairLayoutPicker(prefs, keySet);
					repairRandomRotate(prefs, keySet);
				}
				Toaster.showInfoToast(activity, "Design/Colors restored from " + stripTimestamp(filename));
				return settings;
			} catch (final IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Log.e(LOG_TAG, "Loading Settings - File not exists! " + filename);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private static void repairColorRandomizing(final SharedPreferences prefs, final Set<String> keyset) {
		// Spezialbehandlung für altes color Randomizing
		if (!keyset.contains(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE)) {

			// Ok... we loaded an old KeySet...need to fix it
			// getting some old values
			final int oldColorRange = Settings.getOldRandomizeColorRange();
			final int oldBrighnessRange = Settings.getOldRandomizeColorBrighnessRange();
			final int oldSaturationRange = Settings.getOldRandomizeSaturationRange();
			Log.i("repairColorRandomizing", "oldColorRange     :" + oldColorRange);
			Log.i("repairColorRandomizing", "oldBrighnessRange :" + oldBrighnessRange);
			Log.i("repairColorRandomizing", "oldSaturationRange:" + oldSaturationRange);

			final Editor editor = prefs.edit();

			editor.putInt(ColorRandOptions.KEY_COLOR_MIN_BRIGHTNESS_RANGE, -oldBrighnessRange);
			editor.putInt(ColorRandOptions.KEY_COLOR_MAX_BRIGHTNESS_RANGE, oldBrighnessRange);
			editor.putInt(ColorRandOptions.KEY_COLOR_MIN_SATURATION_RANGE, -oldSaturationRange);
			editor.putInt(ColorRandOptions.KEY_COLOR_MAX_SATURATION_RANGE, oldSaturationRange);

			switch (Settings.getOldColorRandomizingString()) {
			case "full RGB":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "full RGB");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;

			case "only RED":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only RED");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;
			case "pull RED":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only RED");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, 0);
				break;
			case "push RED":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only RED");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, 0);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);

				break;

			case "only GREEN":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only GREEN");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;
			case "pull GREEN":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only GREEN");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, 0);
				break;
			case "push GREEN":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only GRENN");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, 0);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;

			case "only BLUE":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only BLUE");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;
			case "pull BLUE":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only BLUE");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, 0);
				break;
			case "push BLUE":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "only BLUE");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, 0);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;

			case "hue":
				editor.putString(ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "hue");
				editor.putInt(ColorRandOptions.KEY_COLOR_MIN_COLOR_RANGE, -oldColorRange);
				editor.putInt(ColorRandOptions.KEY_COLOR_MAX_COLOR_RANGE, oldColorRange);
				break;
			}
			editor.commit();

			deleteKeyFromPref(prefs, Settings.OLD_KEY_COLOR_RANDOMIZING_TYPE);
			deleteKeyFromPref(prefs, Settings.OLD_KEY_RANDOMIZE_COLOR_BRIGHTNESS_RANGE_INT);
			deleteKeyFromPref(prefs, Settings.OLD_KEY_RANDOMIZE_COLOR_RANGE_INT);
			deleteKeyFromPref(prefs, Settings.OLD_KEY_RANDOMIZE_SATURATION_RANGE);
		}
	}

	private static void deleteKeyFromPref(final SharedPreferences prefs, final String key) {
		Log.i("Deleting key from pref", key);
		prefs.edit().remove(key).commit();
	}

	private static void repairRandomRotate(final SharedPreferences prefs, final Set<String> keySet) {
		// Spezialbehandlung für alten randomRotate
		if (keySet.contains("randomRotate") && !keySet.contains("rotatingStyle")) {
			final boolean val = prefs.getBoolean("randomRotate", true);
			Log.i(LOG_TAG, " old randomRotate found --> " + val + ")");
			if (val == true) {
				prefs.edit().putString("rotatingStyle", "Random").commit();
				Log.i(LOG_TAG, "randomRotate found - Putting ---> Random");
			} else {
				prefs.edit().putString("rotatingStyle", "Fixed").commit();
				Log.i(LOG_TAG, "randomRotate found - Putting ---> fixed");
			}
		}
		deleteKeyFromPref(prefs, "randomRotate");
	}

	private static void repairLayoutPicker(final SharedPreferences prefs, final Set<String> keySet) {
		// Spezialbehandlung für alten LayoutPicker
		if (keySet.contains("layoutPicker") && !keySet.contains(Settings.KEY_MAINLAYOUTS)) {
			final String val = prefs.getString("layoutPicker", "Random Layout");
			Log.i(LOG_TAG, " old layoutPicker found --> " + val + ")");
			final int pos = val.indexOf(" (");
			if (pos > 0) {
				final String mainLayout = val.substring(0, pos);
				if (mainLayout.startsWith("Geo")) {
					prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Geometric Grid").commit();
					Log.i(LOG_TAG, "layoutPicker found - Putting --> Geometric Grid");
				} else if (mainLayout.startsWith("Circular")) {
					prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Circular").commit();
					Log.i(LOG_TAG, "layoutPicker found - Putting --> Circular");
				} else if (mainLayout.startsWith("Half")) {
					prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Half Circle").commit();
					Log.i(LOG_TAG, "layoutPicker found - Putting --> Half Circle");
				}
				final int posEnd = val.indexOf(")");
				final String mainLayoutVariante = val.substring(pos + 2, posEnd);
				prefs.edit().putString(Settings.KEY_MAINLAYOUT_VARIANTS, mainLayoutVariante).commit();
				Log.i(LOG_TAG, "layoutPicker found - Putting Variante --> " + mainLayoutVariante);
			} else {
				prefs.edit().putString(Settings.KEY_MAINLAYOUTS, "Random Layout").commit();
				prefs.edit().putString(Settings.KEY_MAINLAYOUT_VARIANTS, "Random").commit();
			}
		}
		deleteKeyFromPref(prefs, "layoutPicker");
	}

	@SuppressWarnings("deprecation")
	private static void repairSomeDefaultValues(final SharedPreferences prefs, final Set<String> keySet) {
		if (!keySet.contains(Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT)) {
			setDefaultBooleanValue(prefs, Settings.KEY_SAME_BACKGROUND_AS_PATTERN_GRADIENT, true);
		}
		if (!keySet.contains(Settings.KEY_DROP_SHADOW_OFFSET_X)) {
			setDefaultIntValue(prefs, Settings.KEY_DROP_SHADOW_OFFSET_X, 0);
			setDefaultIntValue(prefs, Settings.KEY_DROP_SHADOW_OFFSET_Y, 0);
		}
		if (!keySet.contains(Settings.KEY_GLOSSY_REFLECTION_STYLE)) {
			setDefaultStringValue(prefs, Settings.KEY_GLOSSY_REFLECTION_STYLE, "Diagonal");
		}
		if (!keySet.contains(Settings.KEY_GLOSSY_GLOW_STYLE)) {
			setDefaultStringValue(prefs, Settings.KEY_GLOSSY_GLOW_STYLE, "Center");
		}
		if (!keySet.contains(Settings.KEY_OUTLINE_THICKNESS_ADJUST)) {
			setDefaultIntValue(prefs, Settings.KEY_OUTLINE_THICKNESS_ADJUST, 100);
		}
		if (!keySet.contains(Settings.KEY_OUTLINE_THICKNESS_LIMIT)) {
			setDefaultIntValue(prefs, Settings.KEY_OUTLINE_THICKNESS_LIMIT, 3);
		}
		if (!keySet.contains(Settings.OLD_KEY_COLOR_RANDOMIZING_TYPE)) {
			setDefaultStringValue(prefs, ColorRandOptions.KEY_COLOR_RANDOMIZING_TYPE, "full RGB");
		}
		if (!keySet.contains(Settings.KEY_LIMIT_2_CANVAS)) {
			setDefaultStringValue(prefs, Settings.KEY_LIMIT_2_CANVAS, "small tolerance");
		}
		if (!keySet.contains(Settings.KEY_PATTERN_BLUR)) {
			setDefaultBooleanValue(prefs, Settings.KEY_PATTERN_BLUR, false);
		}
		if (!keySet.contains(Settings.KEY_COLOR_REPEATS)) {
			setDefaultIntValue(prefs, Settings.KEY_COLOR_REPEATS, 1);
		}
		if (!keySet.contains(Settings.KEY_REVERSE_COLORS)) {
			setDefaultBooleanValue(prefs, Settings.KEY_REVERSE_COLORS, false);
		}
		if (!keySet.contains(Settings.KEY_CORNER_GRADIENT_LEVELS)) {
			setDefaultIntValue(prefs, Settings.KEY_CORNER_GRADIENT_LEVELS, 100);
		}
		if (!keySet.contains(Settings.KEY_CORNER_REPEATS)) {
			setDefaultIntValue(prefs, Settings.KEY_CORNER_REPEATS, 1);
		}
		if (!keySet.contains(Settings.KEY_TORNADO_CENTER_POINT_X)) {
			setDefaultIntValue(prefs, Settings.KEY_TORNADO_CENTER_POINT_X, 50);
			setDefaultIntValue(prefs, Settings.KEY_TORNADO_CENTER_POINT_Y, 50);
		}
		if (!keySet.contains(Settings.KEY_RADIUS_TYPE)) {
			setDefaultStringValue(prefs, Settings.KEY_RADIUS_TYPE, "Random");
		}
	}

	private static void setDefaultStringValue(final SharedPreferences prefs, final String key, final String value) {
		Log.i(LOG_TAG, "Key not contained-> setting it to default: " + key + " = " + value);
		prefs.edit().putString(key, value).commit();
	}

	private static void setDefaultIntValue(final SharedPreferences prefs, final String key, final int value) {
		Log.i(LOG_TAG, "Key not contained-> setting it to default: " + key + " = " + value);
		prefs.edit().putInt(key, value).commit();
	}

	private static void setDefaultBooleanValue(final SharedPreferences prefs, final String key, final boolean value) {
		Log.i(LOG_TAG, "Key not contained-> setting it to default: " + key + " = " + value);
		prefs.edit().putBoolean(key, value).commit();
	}

	@SuppressWarnings("rawtypes")
	private static void writeEntry(final Entry<String, ?> entry, final SharedPreferences prefs, final Set<String> keyset) {
		Log.i(LOG_TAG, "Writing back preferences: " + entry.getKey() + " --> " + entry.getValue().toString() + " ("
				+ entry.getValue().getClass().getSimpleName() + ")");
		final String key = entry.getKey();
		// ein paar Keys nicht lesen!
		if (ignoreKeys.contains(key)) {
			Log.i(LOG_TAG, " - Ignoring key: " + key);
			return;
		}

		if (Settings.isRestoreSizeFromDesign() == false) {
			if (sizeKeys.contains(key)) {
				Log.i(LOG_TAG, " - Ignoring size-Key: " + key);
				return;
			}
		}

		final Class cl = entry.getValue().getClass();
		if (cl.getSimpleName().equalsIgnoreCase("Integer")) {
			prefs.edit().putInt(key, (Integer) entry.getValue()).commit();
		} else if (cl.getSimpleName().equalsIgnoreCase("Boolean")) {
			prefs.edit().putBoolean(key, (Boolean) entry.getValue()).commit();
		} else if (cl.getSimpleName().equalsIgnoreCase("String")) {
			prefs.edit().putString(key, (String) entry.getValue()).commit();
		}
	}

	private static File getSettingsDir() {
		// Ordner anlegen fals nicht vorhanden
		final File dir = new File(StorageHelper.getDesignsDir());
		dir.mkdirs();
		return dir;
	}

	public static String stripTimestamp(final String filename) {
		String out;
		final int pos = filename.indexOf(MARKER);
		if (pos == -1) {
			// EXtension nicht gefunden
			out = filename;
		} else {
			out = filename.substring(0, pos);
		}
		return out;
	}

}
