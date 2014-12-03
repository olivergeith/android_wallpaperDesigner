package de.geithonline.wallpaperdesigner;

import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;

public class PreferencesActivity extends PreferenceActivity {

	private BillingManager billingManager;
	public static SharedPreferences prefs;

	@Override
	protected boolean isValidFragment(final String fragmentName) {
		Log.i("GEITH", "isValidFragment Called for " + fragmentName);

		return AboutFragment.class.getName().equals(fragmentName) //
				|| SizePreferencesFragment.class.getName().equals(fragmentName) //
				|| StylePreferencesFragment.class.getName().equals(fragmentName) //
				|| LayoutPreferencesFragment.class.getName().equals(fragmentName) //
				|| GlobalSettingsFragment.class.getName().equals(fragmentName) //
				|| ColorPreferencesFragment.class.getName().equals(fragmentName);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// initialize Settings if not already done
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext(), this);

		billingManager = new BillingManager(this);
		final boolean isPremium = billingManager.isPremium();

		// A View because there might be another button (billing)
		final LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ll.setGravity(Gravity.CENTER);

		// Add a button to the header list.
		if (!isPremium) {
			final Button button = billingManager.getButton();
			ll.addView(button);
		}
		// ll.addView(getSaveSettingsButton());
		ll.addView(getLoadSettingsButton());
		ll.addView(getDeleteSettingsButton());

		// set view with buttons to the list footer
		setListFooter(ll);
	}

	// private Button getSaveSettingsButton() {
	// final Button button = new Button(PreferencesActivity.this);
	// button.setText("Backup Preferences...");
	// button.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(final View v) {
	// SettingsIO.savePreferences(PreferencesActivity.this, Settings.prefs);
	// }
	// });
	// return button;
	// }

	private Button getLoadSettingsButton() {
		final Button button = new Button(PreferencesActivity.this);
		button.setText("Restore Settings...");
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				SettingsIO.loadPreferencesTheFancyWay(PreferencesActivity.this, Settings.prefs);
			}
		});
		return button;
	}

	private Button getDeleteSettingsButton() {
		final Button button = new Button(PreferencesActivity.this);
		button.setText("Delete Settings...");
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				SettingsIO.deletePreferencesTheFancyWay(PreferencesActivity.this);
			}
		});
		return button;
	}

	/**
	 * Populate the activity with the top-level headers.
	 */
	@Override
	public void onBuildHeaders(final List<Header> target) {
		super.onBuildHeaders(target);
		loadHeadersFromResource(R.xml.preferences_header, target);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		billingManager.onDestroy();
	}

}