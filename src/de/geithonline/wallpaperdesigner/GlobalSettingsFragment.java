package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class GlobalSettingsFragment extends PreferenceFragment {

	private ListPreference sortOrder;
	private Preference imageFormat;
	private EditTextPreference shareText;
	private EditTextPreference shareSubject;
	private Preference jpgCompression;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_globalsettings);

		jpgCompression = findPreference("jpgCompression");

		shareText = (EditTextPreference) findPreference("shareText");
		shareText.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleShareTextChanged((String) newValue);
				return true;
			}

		});
		shareSubject = (EditTextPreference) findPreference("shareSubject");
		shareSubject.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(final Preference preference, final Object newValue) {
				handleShareSubjectChanged((String) newValue);
				return true;
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

		String pref = Settings.prefs.getString("shareSubject", "");
		if (pref.isEmpty()) {
			Log.i("GLOBAL Settings", "shareSubject uses default");
			Settings.prefs.edit().putString("shareSubject", Settings.DEFAULT_SHARE_SUBJECT).apply();
		}
		pref = Settings.prefs.getString("shareText", "");
		if (pref.isEmpty()) {
			Log.i("GLOBAL Settings", "shareText uses default");
			Settings.prefs.edit().putString("shareText", Settings.DEFAULT_SHARE_TEXT).apply();
		}
		handleShareSubjectChanged(Settings.getShareSubject());
		handleShareTextChanged(Settings.getShareText());
	}

	protected void handleShareSubjectChanged(String newValue) {
		if (newValue == null || newValue.isEmpty()) {
			Log.i("GLOBAL Settings", "shareSubject is empty");
			newValue = Settings.DEFAULT_SHARE_SUBJECT;
			Log.i("GLOBAL Settings", "shareSubject uses default:" + newValue);
		}
		shareSubject.setSummary(newValue);
	}

	protected void handleShareTextChanged(String newValue) {
		if (newValue == null || newValue.isEmpty()) {
			Log.i("GLOBAL Settings", "shareText is empty");
			newValue = Settings.DEFAULT_SHARE_TEXT;
			Log.i("GLOBAL Settings", "shareText uses default:" + newValue);
		}
		shareText.setSummary(newValue);
	}

	private void handleSortOrder(final String newValue) {
		sortOrder.setSummary(newValue);
	}

	private void handleImageFormat(final String newValue) {
		imageFormat.setSummary(newValue);
		jpgCompression.setEnabled(newValue.equals("jpg"));
	}
}
