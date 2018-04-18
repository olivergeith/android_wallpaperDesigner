
package de.geithonline.wallpaperdesigner;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
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
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import de.geithonline.wallpaperdesigner.bitmapdrawer.BmpRenderer;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;
import de.geithonline.wallpaperdesigner.settings.DesignIO;
import de.geithonline.wallpaperdesigner.settings.PreferenceIO;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.tasks.BitmapAndSetttingsSaverTask;
import de.geithonline.wallpaperdesigner.tasks.BitmapGeneratorTask;
import de.geithonline.wallpaperdesigner.tasks.BitmapSaverTask;
import de.geithonline.wallpaperdesigner.tasks.DesignsLoaderTask;
import de.geithonline.wallpaperdesigner.tasks.GifSaverTaskWithDialog;
import de.geithonline.wallpaperdesigner.tasks.WallpaperSettingTask;
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
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

    public synchronized void setWallpaper() {
        final WallpaperSettingTask task = new WallpaperSettingTask(drawer, this);
        task.execute();
    }

    public synchronized void generate() {
        drawer = new BmpRenderer(Settings.getSelectedMainLayout(), Settings.getSelectedMainLayoutVariante());
        final BitmapGeneratorTask task = new BitmapGeneratorTask(wallpaperView, aniBitmaps, this, drawer);
        task.execute();
    }

    public synchronized void updateDrawer() {
        final DesignsLoaderTask task = new DesignsLoaderTask(mDrawerList, this);
        task.execute();
    }

    public synchronized void saveGif() {
        if (Settings.isCreateGif()) {
            final GifSaverTaskWithDialog task = new GifSaverTaskWithDialog(this, aniBitmaps);
            task.execute();
            Toaster.showInfoToast(this, "Gifs are saved to: " + StorageHelper.getWallpaperGifDir());
        }
    }

    public synchronized void save() {
        final BitmapSaverTask task = new BitmapSaverTask(drawer, this);
        task.execute();
        Toaster.showInfoToast(this, "Wallpapers are saved to: " + StorageHelper.getWallpaperImagesDir());
    }

    public synchronized void saveWithSettings() {
        final BitmapAndSetttingsSaverTask task = new BitmapAndSetttingsSaverTask(drawer, this);
        task.execute();
        updateDrawer();
    }

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
            final File imageFile =
                    BitmapFileIO.saveBitmap2ExternalStorageAsJPG(bitmap, StorageHelper.getDataDir(), "wpd_tmp.jpg", Settings.getJpgCompression());

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
