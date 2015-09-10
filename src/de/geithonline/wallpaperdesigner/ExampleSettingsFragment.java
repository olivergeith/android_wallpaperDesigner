package de.geithonline.wallpaperdesigner;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;

public class ExampleSettingsFragment extends PreferenceFragment {

	private Preference shareALLDesigns;
	private Preference restoreDesigns;
	private Preference backupALLDesigns;
	private Preference backupALLDesignsInManyZips;
	private Preference deleteALLDesigns;
	private Preference deleteSettings;
	private Preference unzipSettings;
	private Preference unzipSettingsPremium;
	private Preference mailSettings;
	private Preference zipOneDesign;
	private Preference unzipUserSettings;
	private Preference backupALLDesignsForUpload;
	private Preference backupOneDesignsForUpload;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_example_settings);

		unzipSettings = findPreference("unzipSettings");
		unzipSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsView.class);
				intent.putExtra("Premium", false);
				startActivityForResult(intent, 1);
				return false;
			}
		});
		unzipSettingsPremium = findPreference("unzipSettingsPremium");
		unzipSettingsPremium.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsView.class);
				intent.putExtra("Premium", true);
				startActivityForResult(intent, 1);
				return false;
			}
		});

		unzipUserSettings = findPreference("unzipUserSettings");
		unzipUserSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsUserView.class);
				intent.putExtra("Premium", false);
				startActivityForResult(intent, 1);
				return false;
			}
		});

		deleteSettings = findPreference("deleteSettings");
		deleteSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.deleteDesignTheFancyWay(getActivity());
				return false;
			}
		});
		deleteALLDesigns = findPreference("deleteALLDesigns");
		deleteALLDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.deleteALLDesigns(getActivity());
				return false;
			}
		});

		backupALLDesigns = findPreference("backupALLDesigns");
		backupALLDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				return false;
			}
		});

		backupALLDesignsInManyZips = findPreference("backupALLDesignsInManyZips");
		backupALLDesignsInManyZips.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				// SettingsIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				SettingsIO.saveAllDesignsToManyZips(getActivity());
				return false;
			}
		});

		restoreDesigns = findPreference("restoreDesigns");
		restoreDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.restoreDesignsFromZip(getActivity(), false);
				return false;
			}
		});

		shareALLDesigns = findPreference("shareALLDesignsViaEmail");
		shareALLDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.saveAllDesignsToZipAndMail(getActivity(), true, false);
				return false;
			}
		});

		mailSettings = findPreference("mailSettings");
		mailSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.eMailDesignTheFancyWay(getActivity(), Settings.prefs);
				return false;
			}
		});

		zipOneDesign = findPreference("zipOneDesign");
		zipOneDesign.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				SettingsIO.zipDesignTheFancyWay(getActivity(), Settings.prefs, false);
				return false;
			}
		});

		backupALLDesignsForUpload = findPreference("backupALLDesignsForUpload");
		backupALLDesignsForUpload.setEnabled(Settings.isDebugging());
		backupALLDesignsForUpload.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				// SettingsIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				SettingsIO.saveAllDesignsForUpload(getActivity());
				return false;
			}
		});

		backupOneDesignsForUpload = findPreference("backupOneDesignsForUpload");
		backupOneDesignsForUpload.setEnabled(Settings.isDebugging());
		backupOneDesignsForUpload.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				// SettingsIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				SettingsIO.zipDesignTheFancyWay(getActivity(), Settings.prefs, true);
				return false;
			}
		});

	}
}
