
package de.geithonline.wallpaperdesigner.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;

public class BitmapSaverTask extends AsyncTask<Void, Void, Integer> {

    private final ProgressDialog dialog;
    private final IBmpRenderer drawer;
    private final Context context;

    public BitmapSaverTask(final IBmpRenderer drawer, final Activity context) {
        dialog = new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Saving Image...");
        this.drawer = drawer;
        this.context = context.getApplicationContext();
        dialog.show();
    }

    @Override
    protected Integer doInBackground(final Void... params) {
        if (drawer != null) {
            drawer.save(context, false);
        }
        return 0;
    }

    @Override
    protected void onPostExecute(final Integer i) {
        dialog.cancel();
    }

}
