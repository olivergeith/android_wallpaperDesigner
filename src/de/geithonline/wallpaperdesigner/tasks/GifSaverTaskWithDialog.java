
package de.geithonline.wallpaperdesigner.tasks;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;

// ##########################################################
public class GifSaverTaskWithDialog extends AsyncTask<Integer, Integer, Integer> implements AsyncTaskWithProgress {
    private final ProgressDialog dialog;
    private final List<Bitmap> aniBitmaps;
    private final Activity activity;

    public GifSaverTaskWithDialog(final Activity activity, final List<Bitmap> aniBitmaps) {
        this.activity = activity;
        this.aniBitmaps = aniBitmaps;
        dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setMessage("Saving Animated Gif \n(" + aniBitmaps.size() + " Frames)");
        dialog.setCancelable(false);
        dialog.setMax(aniBitmaps.size());
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            // Set a click listener for progress dialog cancel button
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                // dismiss the progress dialog
                dialog.dismiss();
                cancel(true);
            }
        });
        dialog.show();
    }

    @Override
    public void settingProgress(final int p, final String message) {
        publishProgress(p + 1);
    }

    @Override
    protected Integer doInBackground(final Integer... params) {
        if (!aniBitmaps.isEmpty()) {
            BitmapFileIO.saveGif(aniBitmaps, this, activity);
        }
        return 0;
    }

    @Override
    protected void onPostExecute(final Integer i) {
        dialog.cancel();
        dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(final Integer... values) {
        // Log.d("ANDRO_ASYNC", "Prograss Bitmap " + values[0]);
        dialog.setProgress(values[0]);
    }

    @Override
    public void settingMax(final int max) {
        dialog.setMax(max);
    }

}
