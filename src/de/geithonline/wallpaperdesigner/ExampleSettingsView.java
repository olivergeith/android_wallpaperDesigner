package de.geithonline.wallpaperdesigner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settingsdownloader.SettingsDownloader;
import de.geithonline.wallpaperdesigner.utils.Alerter;

public class ExampleSettingsView extends Activity {

	private static final String SETTINGS_URL = "http://olivergeith.bplaced.com/settingslist.html";
	private static final String PREMIUM_SETTINGS_URL = "http://olivergeith.bplaced.com/settingslist_premium.html";
	private WebView web;
	private boolean premium;

	protected String getURL(final boolean premium) {
		if (premium) {
			return PREMIUM_SETTINGS_URL;
		}
		return SETTINGS_URL;
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		final Intent intent = getIntent();
		premium = intent.getExtras().getBoolean("Premium");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example_settings_view);
		onCreate(getURL(premium));
	}

	protected void onCreate(final String url) {
		if (premium) {
			setTitle(getTitle() + " (Premium)");
		}
		web = (WebView) findViewById(R.id.webView);
		web.setWebViewClient(new LinkInterceptor());
		web.loadUrl(url);
		final WebSettings settings = web.getSettings();
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		// settings.setBuiltInZoomControls(true);
		// web.setInitialScale(150);
		web.getSettings().setSupportZoom(true);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.example_settings_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		// final int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	private class LinkInterceptor extends WebViewClient {
		public LinkInterceptor() {
		}

		@Override
		public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
			// Zeigen wie die Premium Settings? Und sind wir NICHT Premium User?
			// Dann Downloaden wir nicht ;-)
			// Aber geben eine Messagebox aus!
			if (premium && !Settings.isPremium()) {
				Alerter.alertInfo(ExampleSettingsView.this, "Sorry! This is only downloadable for Premium Users!");
				return true;
			}
			Log.i("Webview", "Url Clicked is:" + url);
			SettingsDownloader.startDownloadFile(ExampleSettingsView.this, url);
			return true;
		}

		@Override
		public void onPageFinished(final WebView view, final String url) {
			super.onPageFinished(view, url);
		}
	}

}
