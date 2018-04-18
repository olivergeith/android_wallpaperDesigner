
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

    public GifSaverTaskWithDialog(final Activity parentActivity, final List<Bitmap> aniBitmaps) {
        this.aniBitmaps = aniBitmaps;
        dialog = new ProgressDialog(parentActivity);
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
    }

    @Override
    public void settingProgress(final int p) {
        publishProgress(p + 1);
    }

    @Override
    protected Integer doInBackground(final Integer... params) {
        if (dialog != null) {
            dialog.show();
        }
        if (!aniBitmaps.isEmpty()) {
            BitmapFileIO.saveGifNew(aniBitmaps, this);
        }
        return 0;
    }

    @Override
    protected void onPostExecute(final Integer i) {
        if (dialog != null) {
            dialog.cancel();
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
