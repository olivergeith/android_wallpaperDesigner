package de.geithonline.wallpaperdesigner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import de.geithonline.wallpaperdesigner.bitmapdrawer.DrawerManager;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IDrawer;
import de.geithonline.wallpaperdesigner.settings.Settings;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {
	// Konstanten
	private static final int REQUEST_CODE_PREFERENCES = 1;
	private ImageView wallpaperView;
	private Button generateButton;
	private Button saveButton;
	private IDrawer drawer;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext());
		prefs.registerOnSharedPreferenceChangeListener(this);

		wallpaperView = (ImageView) findViewById(R.id.wallpaperview);
		generateButton = (Button) findViewById(R.id.generatebutton);
		generateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				generate();
			}
		});
		saveButton = (Button) findViewById(R.id.savebutton);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				save();
			}
		});
		generate();

		if (savedInstanceState == null) {
		}
	}

	protected void save() {
		if (drawer != null) {
			drawer.save(getApplicationContext());
		}

	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		generate();
	}

	protected void generate() {
		drawer = DrawerManager.getDrawer("Bubbles");

		// final int randomNum = (int) Math.ceil(Math.random() * 100);
		final Bitmap bitmap = drawer.drawBitmap();
		wallpaperView.setImageBitmap(bitmap);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
