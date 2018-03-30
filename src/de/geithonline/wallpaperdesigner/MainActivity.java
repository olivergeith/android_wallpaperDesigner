
package de.geithonline.wallpaperdesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
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
import de.geithonline.wallpaperdesigner.bitmapdrawer.BmpRenderer;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;
import de.geithonline.wallpaperdesigner.settings.CustomAdapter;
import de.geithonline.wallpaperdesigner.settings.Design;
import de.geithonline.wallpaperdesigner.settings.DesignIO;
import de.geithonline.wallpaperdesigner.settings.PreferenceIO;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.tasks.BitmapAndSetttingsSaverTask;
import de.geithonline.wallpaperdesigner.tasks.BitmapSaverTask;
import de.geithonline.wallpaperdesigner.tasks.GifSaverTask;
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.AnimatedGifEncoder;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.BitmapHelper;
import de.geithonline.wallpaperdesigner.utils.DebugHelper;
import de.geithonline.wallpaperdesigner.utils.ShakeEventListener;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;
import de.geithonline.wallpaperdesigner.utils.Toaster;

/**
 * @author Oliver
 * 
 */
public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {
	// Konstanten
	private static final int REQUEST_CODE_PREFERENCES = 1;
	private static final int MY_PERMISSIONS_REQUEST_INT = 99;
	private ProgressDialog dialog;
	private TouchImageView wallpaperView;
	private IBmpRenderer drawer;
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;
	private TextView shakeHint;
	private TextView settingsButton;
	private TextView setWallButton;
	private ShareActionProvider mShareActionProvider;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private final List<Bitmap> aniBitmaps = new ArrayList<>();
	private MenuItem gifMenuItem;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Settings.initPrefs(prefs, getApplicationContext(), this);
		Settings.prefs.registerOnSharedPreferenceChangeListener(this);
		setTheme(Settings.getTheme());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		// prefs.registerOnSharedPreferenceChangeListener(this);
		wallpaperView = (TouchImageView) findViewById(R.id.wallpaperview);

		// Drawer einbinden!
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
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
				if (DesignIO.isDesignListNeedsReload()) {
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
						PreferenceIO.loadPreferencesFromFile(MainActivity.this, prefs, filename, false);
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
		if (!Settings.isShowSetWallpaperButton()) {
			setWallButton.setVisibility(View.INVISIBLE);
		}
		setWallButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				setWallpaper();
			}
		});

		initSensors();
		requestPermission();
	}

	/**
	 * Requests the {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} permission. If an additional rationale should be displayed, the user has to
	 * launch the request from a SnackBar that includes additional information.
	 */
	private void requestPermission() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			// Permission has not been granted and must be requested.
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				// Provide an additional rationale to the user if the permission was not granted
				// and the user would benefit from additional context for the use of the permission.
				// Display a SnackBar with a button to request the missing permission.
				Alerter.alertYesNo(MainActivity.this,
						"We need access to external storage to save, restore and download designs...please grant this permission!", "Permission Request",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(final DialogInterface dialog, final int which) {
								ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
										MY_PERMISSIONS_REQUEST_INT);
							}
						}, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog, final int which) {
								closeOnNotGrantedPermission();
							}

						});

			} else {
				ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, MY_PERMISSIONS_REQUEST_INT);
			}
		} else {
			startup();
		}
	}

	private void closeOnNotGrantedPermission() {
		Alerter.alertOneButton(this, "Closing App... we need this permission without it the App is useless ;-)", "Wallpaper Designer", "Close",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						finish();
					}
				});
	}

	@Override
	public void onRequestPermissionsResult(final int requestCode, final String permissions[], final int[] grantResults) {
		switch (requestCode) {
		case MY_PERMISSIONS_REQUEST_INT: {
			// If request is cancelled, the result arrays are empty.
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				startup();

			} else {
				closeOnNotGrantedPermission();
			}
			return;
		}

		// other 'case' lines to check for other
		// permissions this app might request
		}
	}

	private void startup() {
		if (Settings.isRenderingOnStartingApp()) {
			generate();
		}
		Settings.inflateAssets(this, false);
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
			if (Settings.isRenderingOnSettingsExit()) {
				generate();
			}
			if (!Settings.isShowSetWallpaperButton()) {
				setWallButton.setVisibility(View.INVISIBLE);
			}
			if (gifMenuItem != null) {
				if (!Settings.isCreateGif()) {
					gifMenuItem.setVisible(false);
				} else {
					gifMenuItem.setVisible(true);
				}
			}
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
		dialog = new ProgressDialog(this);
		dialog.setMessage("Rendering Background...");
		dialog.setIndeterminate(true);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		final BitmapWorkerTask task = new BitmapWorkerTask(wallpaperView);
		task.execute();
		addCancleButtton(dialog, task);
		if (Settings.isShowRenderingProcess()) {
			dialog.getWindow().setGravity(Gravity.BOTTOM);
		}
		dialog.show();
	}

	public synchronized void updateDrawer() {
		final DesignsLoaderTask task = new DesignsLoaderTask(mDrawerList);
		task.execute();
	}

	public synchronized void saveGif() {
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(false);
		dialog.setMessage("Saving Animated Gif \n(" + aniBitmaps.size() + " Frames)");
		dialog.setCancelable(false);
		final GifSaverTask task = new GifSaverTask(dialog, aniBitmaps);
		task.execute();
		addCancleButtton(dialog, task);
		dialog.show();
		Toaster.showInfoToast(this, "Gifs are saved to: " + StorageHelper.getWallpaperGifDir());
	}

	private void addCancleButtton(final ProgressDialog dialog, final AsyncTask<?, ?, ?> task) {
		dialog.setCancelable(false);
		// ############################################################
		// this will enable cancle on back button!
		// ############################################################
		// dialog.setCancelable(true);
		// dialog.setOnCancelListener(new OnCancelListener() {
		//
		// @Override
		// public void onCancel(final DialogInterface dialog) {
		// task.cancel(true);
		// }
		// });
		// ############################################################
		// Put a cancel button in progress dialog
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
			// Set a click listener for progress dialog cancel button
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				// dismiss the progress dialog
				dialog.dismiss();
				task.cancel(true);
			}
		});
	}

	public synchronized void save() {
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setMessage("Saving Image...");
		final BitmapSaverTask task = new BitmapSaverTask(dialog, drawer, getApplicationContext());
		task.execute();
		dialog.show();
		Toaster.showInfoToast(this, "Wallpapers are saved to: " + StorageHelper.getWallpaperImagesDir());
	}

	public synchronized void saveWithSettings() {
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setMessage("Saving Image and Settings...");
		final BitmapAndSetttingsSaverTask task = new BitmapAndSetttingsSaverTask(dialog, drawer, getApplicationContext());
		task.execute();
		dialog.show();
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
	public boolean onPrepareOptionsMenu(final Menu menu) {
		Log.i("GIF", "onPrepareOptionsMenu");
		if (!Settings.isCreateGif()) {
			gifMenuItem.setVisible(false);
		} else {
			gifMenuItem.setVisible(true);
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);

		gifMenuItem = menu.findItem(R.id.action_save_gif);
		// Locate MenuItem with ShareActionProvider
		final MenuItem shareItem = menu.findItem(R.id.menu_item_share);
		// Fetch and store ShareActionProvider
		mShareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
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
		if (id == R.id.action_save_gif) {
			saveGif();
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
			final File imageFile = BitmapFileIO.saveBitmap2ExternalStorageAsJPG(bitmap, StorageHelper.getDataDir(), "wpd_tmp.jpg",
					Settings.getJpgCompression());

			try {
				final String photoUri = MediaStore.Images.Media.insertImage(getContentResolver(), imageFile.getAbsolutePath(), null, null);
				final Intent shareIntent = ShareCompat.IntentBuilder.from(this)//
						.setText(Settings.getShareText())//
						.setSubject(Settings.getShareSubject())//
						.setType("image/jpeg")//
						.setStream(Uri.parse(photoUri))//
						.getIntent();//
				// .setPackage("com.google.android.apps.plus");
				if (mShareActionProvider != null) {
					mShareActionProvider.setShareIntent(shareIntent);
				}

			} catch (final FileNotFoundException e) {
				Log.e("URI", "Uri = " + imageFile.getAbsolutePath());
				e.printStackTrace();
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
		private Bitmap bitmap;
		private String message = "Rendering...";

		AnimatedGifEncoder encoder = null;
		ByteArrayOutputStream bos;

		public BitmapWorkerTask(final TouchImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(final Integer... params) {
			aniBitmaps.clear();
			drawer = new BmpRenderer(Settings.getSelectedMainLayout(), Settings.getSelectedMainLayoutVariante());
			// drawer.recycleBitmap();
			Log.i("Geith", "Drawing " + Settings.getSelectedMainLayout() + " (" + Settings.getSelectedMainLayoutVariante() + ")");
			final Bitmap bitmap = drawer.drawBitmap(this);
			return bitmap;
		}

		public void settingMax(final int max) {
			if (dialog != null) {
				dialog.setMax(max);
			}
		}

		public void settingBitmap(final Bitmap bitmap) {
			this.bitmap = bitmap;
		}

		public void settingProgress(final int p, final Bitmap bitmap, final String message) {
			this.message = message;
			this.bitmap = bitmap;
			publishProgress(p + 1);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(final Bitmap bitmap) {
			showBitmap(bitmap);
			if (dialog != null) {
				dialog.cancel();
				if (Settings.isDebugging()) {
					shakeHint.setText(DebugHelper.getMemoryInfo());
				}
			}
		}

		private void showBitmap(final Bitmap bitmap) {
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
						// Log.i("SCALING Image for view", "Image bigger than GL_MAX_TEXTURE_SIZE -> resizing it");
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
			if (Settings.isCreateGif()) {
				aniBitmaps.add(BitmapHelper.scallToWidth(bitmap, Settings.getGifSize()));
			}
		}

		@Override
		protected void onCancelled(final Bitmap result) {
			showBitmap(result);
			super.onCancelled(result);
		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			// Log.d("ANDRO_ASYNC", "Prograss Bitmap " + values[0]);
			if (dialog != null) {
				if (dialog.isIndeterminate()) {
					dialog.setIndeterminate(false);
				}
				dialog.setMessage(message);
				dialog.setProgress(values[0]);
			}

			// if (Settings.isShowRenderingProcess() //
			// && values[0].intValue() % Settings.getRenderingProcessFrames() == 0) {
			// showBitmap(bitmap);
			// }
			if (Settings.isShowRenderingProcess() //
					&& Settings.isShowProgressBitmap(dialog.getMax(), values[0].intValue())) {
				showBitmap(bitmap);
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
	public class DesignsLoaderTask extends AsyncTask<Void, Void, List<Design>> {
		private final WeakReference<ListView> weekListViewRef;

		public DesignsLoaderTask(final ListView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			weekListViewRef = new WeakReference<>(imageView);
		}

		// Load List background.
		@Override
		protected List<Design> doInBackground(final Void... params) {
			final List<Design> savedPreferencesList = DesignIO.getSavedPreferencesList();
			return savedPreferencesList;
		}

		// Once complete.....
		@Override
		protected void onPostExecute(final List<Design> savedPreferencesList) {
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

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		switch (key) {
		case Settings.KEY_APP_THEME:
			setTheme(Settings.getTheme());
			recreate();
			break;

		default:
			break;
		}

	}

}
