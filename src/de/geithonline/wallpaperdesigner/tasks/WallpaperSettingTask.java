
package de.geithonline.wallpaperdesigner.tasks;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;

public class WallpaperSettingTask extends AsyncTask<Void, Void, Integer> {

    private final IBmpRenderer drawer;
    private final Activity activity;
    private final ProgressDialog dialog;

    public WallpaperSettingTask(final IBmpRenderer drawer, final Activity activity) {
        dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Setting Wallpaper...");
        dialog.show();
        this.drawer = drawer;
        this.activity = activity;
    }

    @Override
    protected Integer doInBackground(final Void... params) {
        final Bitmap bitmap = drawer.getBitmap();
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(activity);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onPostExecute(final Integer i) {
        dialog.cancel();
        dialog.dismiss();
        activity.finish();
    }
}
