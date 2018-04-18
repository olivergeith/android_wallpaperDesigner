
package de.geithonline.wallpaperdesigner.tasks;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import de.geithonline.wallpaperdesigner.TouchImageView;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.AnimatedGifEncoder;
import de.geithonline.wallpaperdesigner.utils.BitmapHelper;

public class BitmapGeneratorTask extends AsyncTask<Integer, Integer, Bitmap> implements AsyncTaskWithProgress {
    private final WeakReference<TouchImageView> imageViewReference;
    private Bitmap bitmap;
    private String message = "Rendering...";

    AnimatedGifEncoder encoder = null;
    ByteArrayOutputStream bos;
    private final List<Bitmap> aniBitmaps;
    private final IBmpRenderer drawer;

    private final Activity activity;
    private final ProgressDialog dialog;

    public BitmapGeneratorTask(final TouchImageView imageView, final List<Bitmap> aniBitmaps, final Activity activity, final IBmpRenderer drawer) {
        this.drawer = drawer;
        this.activity = activity;
        this.aniBitmaps = aniBitmaps;
        // Use a WeakReference to ensure the ImageView can be garbage
        // collected
        imageViewReference = new WeakReference<>(imageView);
        this.aniBitmaps.clear();
        dialog = new ProgressDialog(this.activity);
        dialog.setMessage("Rendering Background...");
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            // Set a click listener for progress dialog cancel button
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                // dismiss the progress dialog
                dialog.dismiss();
                cancel(true);
            }
        });
        if (Settings.isShowRenderingProcess()) {
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
        dialog.show();
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(final Integer... params) {
        // drawer.recycleBitmap();
        Log.i("Geith", "Drawing " + Settings.getSelectedMainLayout() + " (" + Settings.getSelectedMainLayoutVariante() + ")");
        final Bitmap bitmap = drawer.drawBitmap(this);
        return bitmap;
    }

    public IBmpRenderer getDrawer() {
        return drawer;
    }

    @Override
    public void settingMax(final int max) {
        if (dialog != null) {
            dialog.setMax(max);
        }
    }

    public void settingBitmap(final Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void settingProgress(final int p, final String message) {
        this.message = message;
        bitmap = drawer.getBitmap();
        publishProgress(p + 1);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(final Bitmap bitmap) {
        showBitmap(bitmap);
        if (dialog != null) {
            dialog.cancel();
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
