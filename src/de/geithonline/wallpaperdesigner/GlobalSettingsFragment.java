
package de.geithonline.wallpaperdesigner;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class GlobalSettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    private Preference imageFormat;
    private EditTextPreference shareText;
    private EditTextPreference shareSubject;
    private Preference jpgCompression;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_globalsettings);
        Settings.prefs.registerOnSharedPreferenceChangeListener(this);

        jpgCompression = findPreference(Settings.KEY_JPG_COMPRESSION);

        shareText = (EditTextPreference) findPreference(Settings.KEY_SHARE_TEXT);
        shareText.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handleShareTextChanged((String) newValue);
                return true;
            }

        });
        shareSubject = (EditTextPreference) findPreference(Settings.KEY_SHARE_SUBJECT);
        shareSubject.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handleShareSubjectChanged((String) newValue);
                return true;
            }

        });

        imageFormat = findPreference(Settings.KEY_IMAGE_FORMAT);
        imageFormat.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                handleImageFormat((String) newValue);
                return true;
            }

        });
        handleImageFormat(Settings.getImageFormatString());
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

    private void handleImageFormat(final String newValue) {
        imageFormat.setSummary(newValue);
        jpgCompression.setEnabled(newValue.equals("jpg"));
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        switch (key) {
            case Settings.KEY_APP_THEME:
                final Activity activity = getActivity();
                if (activity != null) {
                    getActivity().setTheme(Settings.getTheme());
                    getActivity().recreate();
                }
                break;

            default:
                break;
        }

    }

}
