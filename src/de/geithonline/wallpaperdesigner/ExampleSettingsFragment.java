package de.geithonline.wallpaperdesigner;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import de.geithonline.wallpaperdesigner.settings.DesignIO;
import de.geithonline.wallpaperdesigner.settings.Settings;

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
	// private Preference backupALLDesignsForUpload;
	// private Preference backupOneDesignsForUpload;
	private Preference shareOneDesign;
	private Preference unzipSharedSettings;
	private Preference publishOneDesign;

	// private Preference designHowto;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_example_settings);

		unzipSettings = findPreference("unzipSettings");
		unzipSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsUserView.class);
				intent.putExtra("Url", WPDUrls.LIST_URL_FREE_PACKS);
				intent.putExtra("Title", "Free Example-Design-Packs");
				intent.putExtra("premiumUsersOnly", false);
				startActivityForResult(intent, 1);
				return false;
			}
		});
		unzipSettingsPremium = findPreference("unzipSettingsPremium");
		unzipSettingsPremium.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsUserView.class);
				intent.putExtra("Url", WPDUrls.LIST_URL_PREMIUM_PACKS);
				intent.putExtra("Title", "Premium Example-Design-Packs");
				intent.putExtra("premiumUsersOnly", true);
				startActivityForResult(intent, 1);
				return false;
			}
		});

		unzipUserSettings = findPreference("unzipUserSettings");
		unzipUserSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsUserView.class);
				intent.putExtra("Url", WPDUrls.LIST_URL_FEATURED_DESIGNS);
				intent.putExtra("Title", "Featured Designs");
				intent.putExtra("premiumUsersOnly", false);
				startActivityForResult(intent, 1);
				return false;
			}
		});
		unzipSharedSettings = findPreference("unzipSharedSettings");
		unzipSharedSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				final Intent intent = new Intent(getActivity(), ExampleSettingsUserView.class);
				intent.putExtra("Url", WPDUrls.LIST_URL_COMMUNITY_DESIGNS);
				intent.putExtra("Title", "Community Designs");
				intent.putExtra("premiumUsersOnly", false);
				startActivityForResult(intent, 1);
				return false;
			}
		});

		deleteSettings = findPreference("deleteSettings");
		deleteSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.deleteDesignTheFancyWay(getActivity());
				return false;
			}
		});
		deleteALLDesigns = findPreference("deleteALLDesigns");
		deleteALLDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.deleteALLDesigns(getActivity());
				return false;
			}
		});

		backupALLDesigns = findPreference("backupALLDesigns");
		backupALLDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				return false;
			}
		});

		backupALLDesignsInManyZips = findPreference("backupALLDesignsInManyZips");
		backupALLDesignsInManyZips.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				// SettingsIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				DesignIO.saveAllDesignsToManyZips(getActivity());
				return false;
			}
		});

		restoreDesigns = findPreference("restoreDesigns");
		restoreDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.restoreDesignsFromZip(getActivity(), false);
				return false;
			}
		});

		shareALLDesigns = findPreference("shareALLDesignsViaEmail");
		shareALLDesigns.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.saveAllDesignsToZipAndMail(getActivity(), true, false);
				return false;
			}
		});

		mailSettings = findPreference("mailSettings");
		mailSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.eMailDesignTheFancyWay(getActivity(), Settings.prefs);
				return false;
			}
		});

		zipOneDesign = findPreference("zipOneDesign");
		zipOneDesign.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.backupDesign(getActivity());
				return false;
			}
		});

		shareOneDesign = findPreference("shareOneDesign");
		shareOneDesign.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				// SettingsIO.saveAllDesignsToZipAndMail(getActivity(), false, false);
				DesignIO.shareDesign(getActivity());
				return false;
			}
		});

		// backupALLDesignsForUpload = findPreference("backupALLDesignsForUpload");
		// backupALLDesignsForUpload.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		// @Override
		// public boolean onPreferenceClick(final Preference preference) {
		// DesignIO.saveAllDesignsForUpload(getActivity());
		// return false;
		// }
		// });
		//
		// backupOneDesignsForUpload = findPreference("backupOneDesignsForUpload");
		// backupOneDesignsForUpload.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		// @Override
		// public boolean onPreferenceClick(final Preference preference) {
		// DesignIO.backupDesignToUploadDir(getActivity());
		// return false;
		// }
		// });

		publishOneDesign = findPreference("publishOneDesign");
		publishOneDesign.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DesignIO.publishDesign(getActivity());
				return false;
			}
		});

		// bestimmte Menüs gibt es nur als Superuser
		if (!Settings.isSuperUser(getActivity())) {
			getPreferenceScreen().removePreference(publishOneDesign);
			// getPreferenceScreen().removePreference(backupOneDesignsForUpload);
			// getPreferenceScreen().removePreference(backupALLDesignsForUpload);
		}

	}
}
