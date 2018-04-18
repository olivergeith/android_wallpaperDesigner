
package de.geithonline.wallpaperdesigner.tasks;

import java.util.List;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;

// ##########################################################
@Deprecated
public class GifSaverTask extends AsyncTask<Integer, Integer, Integer> {
    private final ProgressDialog dialog;
    private final List<Bitmap> aniBitmaps;

    public GifSaverTask(final ProgressDialog dialog, final List<Bitmap> aniBitmaps) {
        this.dialog = dialog;
        this.aniBitmaps = aniBitmaps;
        this.dialog.setIndeterminate(false);
        this.dialog.setMax(aniBitmaps.size());
    }

    public void settingProgress(final int p) {
        publishProgress(p + 1);
    }

    @Override
    protected Integer doInBackground(final Integer... params) {
        if (!aniBitmaps.isEmpty()) {
            BitmapFileIO.saveGif(aniBitmaps, this);
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
