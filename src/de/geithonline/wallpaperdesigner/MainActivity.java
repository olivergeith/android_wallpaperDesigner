package de.geithonline.wallpaperdesigner;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IWPStyle;
import de.geithonline.wallpaperdesigner.bitmapdrawer.StyleManager;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.ShakeEventListener;
import de.geithonline.wallpaperdesigner.utils.Toaster;

/**
 * @author Oliver
 * 
 */
public class MainActivity extends Activity {
	// Konstanten
	private static final int REQUEST_CODE_PREFERENCES = 1;
	private ProgressDialog dialog;
	private TouchImageView wallpaperView;
	private IWPStyle drawer;
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;
	private TextView shakeHint;
	private TextView settingsButton;
	private TextView saveButton;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext());
		// prefs.registerOnSharedPreferenceChangeListener(this);
		wallpaperView = (TouchImageView) findViewById(R.id.wallpaperview);
		shakeHint = (TextView) findViewById(R.id.shakeHint);
		shakeHint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				generate();
			}
		});
		settingsButton = (TextView) findViewById(R.id.settingsButton);
		settingsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				startSettings();
			}
		});

		saveButton = (TextView) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				setWallpaper();
			}
		});

		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);

		initSensors();

		generate();

		if (savedInstanceState == null) {
		}
	}

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

	public synchronized void setWallpaper() {
		final WallpaperSettingTask task = new WallpaperSettingTask();
		task.execute();
		if (dialog != null) {
			dialog.setMessage("Setting Wallpaper...");
			dialog.show();
		}
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
		// if (id == R.id.action_settings) {
		// startSettings();
		// return true;
		// }
		// if (id == R.id.action_generate) {
		// generate();
		// return true;
		// }
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

	// ##########################################################
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

	private void exit() {
		finish();
	}

	// ##########################################################
	class WallpaperSettingTask extends AsyncTask<Void, Void, Integer> {

		public WallpaperSettingTask() {
		}

		@Override
		protected Integer doInBackground(final Void... params) {
			final Bitmap bitmap = wallpaperView.getBitmap();
			final WallpaperManager wallpaperManager = WallpaperManager.getInstance(MainActivity.this);
			try {
				wallpaperManager.setBitmap(bitmap);
			} catch (final IOException e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		protected void onPostExecute(final Integer i) {
			if (dialog != null) {
				dialog.cancel();
			}
			exit();
		}
	}

	// ##########################################################
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
