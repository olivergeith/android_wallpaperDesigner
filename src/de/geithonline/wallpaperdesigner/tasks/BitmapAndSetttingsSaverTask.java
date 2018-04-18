
package de.geithonline.wallpaperdesigner.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;

// ##########################################################
public class BitmapAndSetttingsSaverTask extends AsyncTask<Void, Void, Integer> {

    private final ProgressDialog dialog;
    private final IBmpRenderer drawer;
    private final Context context;

    public BitmapAndSetttingsSaverTask(final IBmpRenderer drawer, final Activity context) {
        dialog = new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Saving Image and Settings...");
        this.drawer = drawer;
        this.context = context.getApplicationContext();
        dialog.show();
    }

    @Override
    protected Integer doInBackground(final Void... params) {
        if (drawer != null) {
            Log.i("SAVE", "Saving Design");
            drawer.save(context, true);
        }
        return 0;
    }

    @Override
    protected void onPostExecute(final Integer i) {
        dialog.cancel();
        dialog.dismiss();
    }

}
