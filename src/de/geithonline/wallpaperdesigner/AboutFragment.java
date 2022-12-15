package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class AboutFragment extends PreferenceFragment {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_about);
	}
}
