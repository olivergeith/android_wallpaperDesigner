package de.geithonline.wallpaperdesigner;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class AboutFragment extends PreferenceFragment {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_about);

		setSpecialThings();
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
