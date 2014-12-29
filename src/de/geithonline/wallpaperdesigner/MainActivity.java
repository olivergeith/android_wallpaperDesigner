package de.geithonline.wallpaperdesigner;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IWPStyle;
import de.geithonline.wallpaperdesigner.bitmapdrawer.layout.LayoutManagerV2;
import de.geithonline.wallpaperdesigner.settings.CustomAdapter;
import de.geithonline.wallpaperdesigner.settings.PreferenceIO;
import de.geithonline.wallpaperdesigner.settings.SavedDesign;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.DebugHelper;
import de.geithonline.wallpaperdesigner.utils.ShakeEventListener;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;

/**
 * @author Oliver
 * 
 */
@SuppressWarnings("deprecation")
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
	private TextView setWallButton;
	private ShareActionProvider mShareActionProvider;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext(), this);
		setTheme(Settings.getTheme());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// prefs.registerOnSharedPreferenceChangeListener(this);
		wallpaperView = (TouchImageView) findViewById(R.id.wallpaperview);

		// Drawer einbinden!
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			@Override
			public void onDrawerClosed(final View view) {
				super.onDrawerClosed(view);
				// getActionBar().setTitle(getTitle());
				invalidateOptionsMenu();
			}

			/** Called when a drawer has settled in a completely open state. */
			@Override
			public void onDrawerOpened(final View drawerView) {
				super.onDrawerOpened(drawerView);
				// getActionBar().setTitle("Choose Design");
				if (SettingsIO.isDesignListNeedsReload()) {
					updateDrawer();
				}
				invalidateOptionsMenu();
			}
		};
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Log.i("Loading Settings ", "from " + position);
				if (position >= 0) {
					final File file = (File) mDrawerList.getAdapter().getItem(position);
					final String filename = file.getName();
					if (filename != null) {
						PreferenceIO.loadPreferencesFromFile(MainActivity.this, prefs, filename);
						generate();
					}
				}
				mDrawerLayout.closeDrawers();
			}
		});

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

		setWallButton = (TextView) findViewById(R.id.saveButton);
		setWallButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				setWallpaper();
			}
		});

		initSensors();
		generate();
		updateDrawer();
	}

	@Override
	protected void onPostCreate(final Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(final Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
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
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
		super.onDestroy();
	}

	public synchronized void setWallpaper() {
		final WallpaperSettingTask task = new WallpaperSettingTask();
		task.execute();
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setMessage("Setting Wallpaper...");
		dialog.show();
	}

	public synchronized void generate() {
		final BitmapWorkerTask task = new BitmapWorkerTask(wallpaperView);
		task.execute();
		dialog = new ProgressDialog(this);
		dialog.setCancelable(false);
		dialog.setMessage("Rendering...");
		dialog.setIndeterminate(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setProgress(0);
		dialog.show();
	}

	public synchronized void updateDrawer() {
		final DesignsLoaderTask task = new DesignsLoaderTask(mDrawerList);
		task.execute();
	}

	public synchronized void save() {
		final BitmapSaverTask task = new BitmapSaverTask();
		task.execute();
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setMessage("Saving Image...");
		dialog.show();
		Toaster.showInfoToast(this, "Wallpapers are saved to: " + StorageHelper.getExternalStorageImages());
	}

	public synchronized void saveWithSettings() {
		final BitmapAndSetttingsSaverTask task = new BitmapAndSetttingsSaverTask();
		task.execute();
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setMessage("Saving Image and Settings...");
		dialog.show();
		Toaster.showInfoToast(this, "Wallpapers are saved to: " + StorageHelper.getExternalStorageImages());
		updateDrawer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onMenuOpened(int, android.view.Menu)
	 * 
	 * Wird gebraucht um im overflow Menu die Icons anzuzeigen!!!
	 */
	@Override
	public boolean onMenuOpened(final int featureId, final Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					final Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (final NoSuchMethodException e) {
					Log.e("ERROR", "onMenuOpened", e);
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);

		// Locate MenuItem with ShareActionProvider
		final MenuItem shareItem = menu.findItem(R.id.menu_item_share);
		// Fetch and store ShareActionProvider
		mShareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
		// if (mShareActionProvider != null) {
		// final Intent shareIntent = new Intent();
		// // shareIntent.setAction(Intent.ACTION_SEND);
		// // shareIntent.setType("text/plain");
		// // // shareIntent.putExtra(Intent.EXTRA_SUBJECT,
		// "Shared from the Wallpaper Designer");
		// // shareIntent.putExtra(Intent.EXTRA_TEXT,
		// "https://play.google.com/store/apps/details?id=de.geithonline.wallpaperdesigner");
		// shareIntent.setAction(Intent.ACTION_SEND);
		// // shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		// shareIntent.setType("image/jpeg");
		// mShareActionProvider.setShareIntent(shareIntent);
		// }
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		final int id = item.getItemId();
		Log.i("Geith", "id=" + id);
		if (id == R.id.action_save) {
			save();
			return true;
		}
		if (id == R.id.action_save_settings) {
			saveWithSettings();
			return true;
		}
		if (id == R.id.menu_item_share) {
			shareImage();
			return true;
		}
		if (id == R.id.action_generate) {
			generate();
			return true;
		}
		if (id == R.id.action_settings) {
			startSettings();
			return true;
		}

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	// Call to update the share intent
	private void shareImage() {
		// Save tmp image
		final Bitmap bitmap = drawer.getBitmap();
		if (bitmap != null) {
			final File imageFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(bitmap,
					StorageHelper.getExternalStorage(), "wpd_tmp.jpg", Settings.getJpgCompression());

			final Uri uri = Uri.fromFile(imageFile);
			// Uri.parse("file://" + imageFile.getAbsolutePath());
			Log.i("URI", "Uri = " + uri);
			final Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, Settings.getShareSubject());
			shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			shareIntent.setType("image/jpeg");
			shareIntent.putExtra(Intent.EXTRA_TEXT, Settings.getShareText());

			// startActivity(Intent.createChooser(shareIntent, "Share to..."));
			if (mShareActionProvider != null) {
				mShareActionProvider.setShareIntent(shareIntent);
			}
		}
	}

	/**
	 * Startet den Settings Dialog
	 */
	private void startSettings() {
		final Intent intent = new Intent(PreferencesActivity.class.getCanonicalName());
		startActivityForResult(intent, REQUEST_CODE_PREFERENCES);
	}

	// ##########################################################
	public class BitmapWorkerTask extends AsyncTask<Integer, Integer, Bitmap> {
		private final WeakReference<TouchImageView> imageViewReference;

		public BitmapWorkerTask(final TouchImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<TouchImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(final Integer... params) {
			drawer = LayoutManagerV2.getDrawer(Settings.getSelectedMainLayout(),
					Settings.getSelectedMainLayoutVariante());
			// drawer.recycleBitmap();
			Log.i("Geith",
					"Drawing " + Settings.getSelectedMainLayout() + " (" + Settings.getSelectedMainLayoutVariante()
							+ ")");

			final Bitmap bitmap = drawer.drawBitmap(this);
			return bitmap;
		}

		public void settingMax(final int max) {
			if (dialog != null) {
				dialog.setMax(max);
			}
		}

		public void settingProgress(final int p) {
			publishProgress(p);

		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(final Bitmap bitmap) {
			if (imageViewReference != null && bitmap != null) {
				final TouchImageView imageView = imageViewReference.get();
				if (imageView != null) {
					final int w = bitmap.getWidth();
					final int h = bitmap.getHeight();
					// if bitmap ist too big for open GL GL_MAX_TEXTURE_SIZE
					final int maxTexturesize = 2048;
					if (w <= maxTexturesize && h <= maxTexturesize) {
						imageView.setImageBitmap(bitmap);
					} else {
						Log.i("SCALING Image for view", "Image bigger than GL_MAX_TEXTURE_SIZE -> resizing it");
						if (w > h) {
							final int nh = bitmap.getHeight() * maxTexturesize / bitmap.getWidth();
							final Bitmap scaled = Bitmap.createScaledBitmap(bitmap, maxTexturesize, nh, true);
							imageView.setImageBitmap(scaled);
						} else {
							final int nw = bitmap.getWidth() * maxTexturesize / bitmap.getHeight();
							final Bitmap scaled = Bitmap.createScaledBitmap(bitmap, nw, maxTexturesize, true);
							imageView.setImageBitmap(scaled);
						}
					}
					imageView.fit2Screen();

				}
			}
			if (dialog != null) {
				dialog.cancel();
				if (Settings.isDebugging()) {
					shakeHint.setText(DebugHelper.getMemoryInfo());
				}
			}
		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			// Log.d("ANDRO_ASYNC", "Prograss Bitmap " + values[0]);
			if (dialog != null) {
				dialog.setProgress(values[0]);
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
				drawer.save(getApplicationContext(), false);
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
	public class DesignsLoaderTask extends AsyncTask<Void, Void, List<SavedDesign>> {
		private final WeakReference<ListView> weekListViewRef;

		public DesignsLoaderTask(final ListView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			weekListViewRef = new WeakReference<ListView>(imageView);
		}

		// Load List background.
		@Override
		protected List<SavedDesign> doInBackground(final Void... params) {
			final List<SavedDesign> savedPreferencesList = SettingsIO.getSavedPreferencesList();
			return savedPreferencesList;
		}

		// Once complete.....
		@Override
		protected void onPostExecute(final List<SavedDesign> savedPreferencesList) {
			if (weekListViewRef != null && savedPreferencesList != null) {
				final ListView list = weekListViewRef.get();
				if (list != null) {
					list.setAdapter(new CustomAdapter(MainActivity.this, savedPreferencesList));
				}
			}
			if (dialog != null) {
				dialog.cancel();
			}
		}
	}

	// ##########################################################
	class BitmapAndSetttingsSaverTask extends AsyncTask<Void, Void, Integer> {

		public BitmapAndSetttingsSaverTask() {
		}

		@Override
		protected Integer doInBackground(final Void... params) {
			if (drawer != null) {
				drawer.save(getApplicationContext(), true);
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
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
	}

}
