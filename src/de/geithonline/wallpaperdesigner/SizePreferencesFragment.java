
package de.geithonline.wallpaperdesigner;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;

/**
 * This fragment shows the preferences for the first header.
 */
public class SizePreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    private ListPreference sizeSelection;
    private EditTextPreference bWidth;
    private EditTextPreference bHeight;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_size);

        Settings.prefs.registerOnSharedPreferenceChangeListener(this);

        sizeSelection = (ListPreference) findPreference("sizeSelection");
        bWidth = (EditTextPreference) findPreference("bWidth");
        bHeight = (EditTextPreference) findPreference("bHeight");
        final String selectSize = Settings.getSizeSelection();
        sizeSelection.setSummary(selectSize);
        bWidth.setSummary("" + Settings.getCustomWidth());
        bHeight.setSummary("" + Settings.getCustomHeight());
        bWidth.setEnabled(selectSize.equals("customSize"));
        bHeight.setEnabled(selectSize.equals("customSize"));

        sizeSelection.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                final String s = (String) newValue;
                bWidth.setEnabled(s.equals("customSize"));
                bHeight.setEnabled(s.equals("customSize"));
                return true;
            }
        });
        bWidth.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                bWidth.setSummary((String) newValue);
                return true;
            }
        });
        bHeight.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                bHeight.setSummary((String) newValue);
                return true;
            }
        });

        enableProFeatures();
    }

    private void enableProFeatures() {}

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        switch (key) {
            case "sizeSelection":
            case "bWidth":
            case "bHeight":
                // handleSizeSettings();
                break;

            default:
                break;
        }

    }

}
