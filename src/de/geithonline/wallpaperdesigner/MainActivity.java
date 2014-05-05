package de.geithonline.wallpaperdesigner;

import java.io.File;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IWPStyle;
import de.geithonline.wallpaperdesigner.bitmapdrawer.StyleManager;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ShakeEventListener;
import de.geithonline.wallpaperdesigner.utils.Toaster;

/**
 * @author Oliver
 * 
 */
public class MainActivity extends Activity /* implements OnSharedPreferenceChangeListener */{
	// Konstanten
	private static final int REQUEST_CODE_PREFERENCES = 1;
	private ProgressDialog dialog;
	private TouchImageView wallpaperView;
	private IWPStyle drawer;
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext());
		// prefs.registerOnSharedPreferenceChangeListener(this);
		wallpaperView = (TouchImageView) findViewById(R.id.wallpaperview);
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);

		initSensors();

		generate();

		if (savedInstanceState == null) {
		}
	}

	// @Override
	// public void onOptionsMenuClosed(final Menu menu) {
	// Log.i("MENU", "Settings closed");
	// generate();
	// super.onOptionsMenuClosed(menu);
	// }

	// @Override
	// public void onWindowFocusChanged(final boolean hasFocus) {
	// if (hasFocus) {
	// Log.i("MENU", "HAS Fokus");
	// generate();
	// }
	// super.onWindowFocusChanged(hasFocus);
	// }

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		if (requestCode == REQUEST_CODE_PREFERENCES) {
			Log.i("MENU", "Coming beack from Settings...generating...");
			generate();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		dialog.dismiss();
		dialog = null;
		super.onDestroy();
	}

	public synchronized void generate() {
		final BitmapWorkerTask task = new BitmapWorkerTask(wallpaperView);
		task.execute();
		if (dialog != null) {
			dialog.setMessage("Rendering...");
			dialog.show();
		}
	}

	public synchronized void save() {
		final BitmapSaverTask task = new BitmapSaverTask();
		task.execute();
		if (dialog != null) {
			dialog.setMessage("Saving...");
			dialog.show();
		}
		String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
		extStorageDirectory += File.separator + "Pictures" + File.separator + "WallpaperDesigner" + File.separator;
		Toaster.showInfoToast(this, "Wallpaters are saved to: " + extStorageDirectory);
	}

	// @Override
	// public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
	// generate();
	// }

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

	/**
	 * @author Oliver Worker Task for generating images
	 */
	class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
		private final WeakReference<TouchImageView> imageViewReference;

		public BitmapWorkerTask(final TouchImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<TouchImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(final Integer... params) {
			drawer = StyleManager.getDrawer(Settings.getSelectedStyle());
			// drawer.recycleBitmap();
			Log.i("Geith", "Drawing " + Settings.getSelectedStyle());
			final Bitmap bitmap = drawer.drawBitmap();
			return bitmap;
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(final Bitmap bitmap) {
			if (imageViewReference != null && bitmap != null) {
				final TouchImageView imageView = imageViewReference.get();
				if (imageView != null) {
					imageView.setImageBitmap(bitmap);
					imageView.fit2Screen();
				}
			}
			if (dialog != null) {
				dialog.cancel();
			}
		}
	}

	/**
	 * @author Oliver Worker Task for saving images
	 */
	class BitmapSaverTask extends AsyncTask<Void, Void, Integer> {

		public BitmapSaverTask() {
		}

		@Override
		protected Integer doInBackground(final Void... params) {
			if (drawer != null) {
				drawer.save(getApplicationContext());
			}
			return 0;
		}

		@Override
		protected void onPostExecute(final Integer i) {
			if (dialog != null) {
				dialog.cancel();
			}
		}

	}

	// ##########################################################
	// for shaking
	public void initSensors() {
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorListener = new ShakeEventListener();
		mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

			@Override
			public void onShake() {
				generate();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
	}

}
