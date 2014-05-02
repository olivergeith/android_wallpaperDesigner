package de.geithonline.wallpaperdesigner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import de.geithonline.wallpaperdesigner.bitmapdrawer.DrawerManager;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IDrawer;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {
	// Konstanten
	private static final int REQUEST_CODE_PREFERENCES = 1;
	private final Handler handler = new Handler();
	private ImageView wallpaperView;
	private IDrawer drawer;
	private Bitmap bitmap;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext());
		prefs.registerOnSharedPreferenceChangeListener(this);
		wallpaperView = (ImageView) findViewById(R.id.wallpaperview);
		generate();

		if (savedInstanceState == null) {
		}
	}

	private final Runnable drawRunner = new Runnable() {
		@Override
		public void run() {
			realGenerate();
		}
	};

	private final Runnable saveRunner = new Runnable() {
		@Override
		public void run() {
			realSave();
		}
	};

	public synchronized void generate() {
		handler.removeCallbacks(drawRunner);
		handler.post(drawRunner);
	}

	public synchronized void save() {
		handler.removeCallbacks(saveRunner);
		handler.post(saveRunner);
	}

	protected void realSave() {
		if (drawer != null) {
			drawer.save(getApplicationContext());
		}

	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		generate();
	}

	protected void realGenerate() {
		drawer = DrawerManager.getDrawer("Stars");
		bitmap = drawer.drawBitmap();
		wallpaperView.setImageBitmap(bitmap);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		final int id = item.getItemId();
		Log.i("Geith", "id=" + id);
		if (id == R.id.action_settings) {
			startSettings();
			return true;
		}
		if (id == R.id.action_generate) {
			generate();
			return true;
		}
		if (id == R.id.action_save) {
			save();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Startet den Settings Dialog
	 */
	private void startSettings() {
		final Intent intent = new Intent(PreferencesActivity.class.getCanonicalName());
		startActivityForResult(intent, REQUEST_CODE_PREFERENCES);
	}

}
