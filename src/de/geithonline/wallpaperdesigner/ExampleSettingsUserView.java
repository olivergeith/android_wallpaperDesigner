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

public class ExampleSettingsUserView extends Activity {

	private WebView web;

	protected String getURL() {
		final Intent intent = getIntent();
		return intent.getExtras().getString("Url");
	}

	protected String getTitleLine() {
		final Intent intent = getIntent();
		return intent.getExtras().getString("Title");
	}

	protected boolean isPremiumUsersOnly() {
		final Intent intent = getIntent();
		return intent.getExtras().getBoolean("premiumUsersOnly");
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example_settings_view);
		onCreate(getURL());
	}

	protected void onCreate(final String url) {
		setTitle(getTitleLine());
		web = (WebView) findViewById(R.id.webView);
		web.setWebViewClient(new LinkInterceptor());
		web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
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
			if (isPremiumUsersOnly() && !Settings.isPremium()) {
				Alerter.alertInfo(ExampleSettingsUserView.this, "Sorry! This is only downloadable for Premium Users!");
				return true;
			}
			Log.i("Webview", "Url Clicked is:" + url);
			SettingsDownloader.startDownloadFile(ExampleSettingsUserView.this, url);
			return true;
		}

		@Override
		public void onPageFinished(final WebView view, final String url) {
			super.onPageFinished(view, url);
		}
	}

}
