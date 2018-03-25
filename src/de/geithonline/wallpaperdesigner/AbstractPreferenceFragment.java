package de.geithonline.wallpaperdesigner;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

public class AbstractPreferenceFragment extends PreferenceFragment {

	protected void addOrRemoveFromMainScreen(final Preference preference, final boolean visible) {
		addOrRemoveFromScreen(getPreferenceScreen(), preference, visible);
	}

	protected void addOrRemoveFromScreen(final PreferenceScreen screen, final Preference preference, final boolean visible) {
		if (visible) {
			screen.addPreference(preference);
		} else {
			screen.removePreference(preference);
		}
	}

}
