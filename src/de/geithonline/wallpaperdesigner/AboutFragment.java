package de.geithonline.wallpaperdesigner;

import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.SettingsDownloader;

public class AboutFragment extends PreferenceFragment {

	private ListPreference sortOrder;
	private Preference unzipSettings;
	private Preference unzipSettingsV2;
	private Preference imageFormat;

	// private SeekBarPreference jpgCompression;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_about);

		// jpgCompression = (SeekBarPreference) findPreference("jpgCompression");
		// jpgCompression.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
		//
		// @Override
		// public boolean onPreferenceChange(final Preference preference, final Object newValue) {
		// handleJpgCompression((int) newValue);
		// return true;
		// }
		//
		// });

		unzipSettings = findPreference("unzipSettings");
		unzipSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsDownloader.startDownloadFile1(getActivity());
				return false;
			}

		});
		unzipSettingsV2 = findPreference("unzipSettingsV2");
		unzipSettingsV2.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsDownloader.startDownloadFile2(getActivity());
				return false;
			}

		});

		sortOrder = (ListPreference) findPreference("sortOrder");
		sortOrder.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleSortOrder((String) newValue);
				return true;
			}

		});
		imageFormat = findPreference("imageFormat");
		imageFormat.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleImageFormat((String) newValue);
				return true;
			}

		});
		handleImageFormat(Settings.getImageFormat());
		handleSortOrder(Settings.getSortOrder());

		getIdOfCurrentWidget(savedInstanceState);
		setSpecialThings();
	}

	// protected void handleJpgCompression(int newValue) {
	//
	// }

	private void handleSortOrder(final String newValue) {
		sortOrder.setSummary(newValue);
	}

	private void handleImageFormat(final String newValue) {
		imageFormat.setSummary(newValue);
	}

	private int getIdOfCurrentWidget(final Bundle savedInstanceState) {

		final Bundle extras = getActivity().getIntent().getExtras();

		int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
		if (extras != null) {
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		// If we have a Widget...we switch preferences to its preferences
		if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
			// final PreferenceManager prefMgr = getPreferenceManager();
			// final String prefsName = "Widget" + widgetId;
			// prefMgr.setSharedPreferencesName(prefsName);
			// prefMgr.setSharedPreferencesMode(PreferenceActivity.MODE_PRIVATE);
		}
		return widgetId;
	}

	private void setSpecialThings() {
		final Preference proBox = findPreference("premium");

		if (Settings.isPremium()) {
			proBox.setTitle("This is the Premium Version");
			proBox.setIcon(R.drawable.icon_premium);
		} else {
			proBox.setTitle("This is the Free Version");
			proBox.setIcon(R.drawable.icon);
		}
	}

}
