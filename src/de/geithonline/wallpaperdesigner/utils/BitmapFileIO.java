
package de.geithonline.wallpaperdesigner.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.tasks.AsyncTaskWithProgress;

public class BitmapFileIO {

    /**
     * @param bitmap
     *            the Bitmap to save
     * @param filename
     *            the filename
     */
    public static File saveBitmap2ExternalStorage(final Bitmap bitmap, final String dir, final String filename) {

        OutputStream outStream = null;
        // Ordner anlegen fal snicht vorhanden
        final File out = new File(dir);
        out.mkdirs();
        Log.i("GEITH", "Writing Bitmap to " + dir + filename);
        final File file = new File(dir, filename);
        try {
            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (final Exception e) {}
        return file;
    }

    /**
     * @param bitmap
     *            the Bitmap to save
     * @param filename
     *            the filename
     */
    public static File saveBitmap2ExternalStorageAsJPG(final Bitmap bitmap, final String dir, final String filename, final int compression) {

        OutputStream outStream = null;
        // Ordner anlegen fal snicht vorhanden
        final File out = new File(dir);
        out.mkdirs();
        Log.i("GEITH", "Writing Jpg to " + dir + "/" + filename);
        final File file = new File(dir, filename);
        try {
            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, compression, outStream);
            outStream.flush();
            outStream.close();
        } catch (final Exception e) {}
        return file;
    }

    /**
     * @return Bitmap or null...
     */
    public static Bitmap loadBitmapAnScale(final String filePath, final int width, final int height) {
        if (!filePath.equals("aaa")) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inDither = false;
            // options.inPurgeable = true; // deprecated since Lollipop
            // options.inInputShareable = true; // deprecated since Lollipop
            options.inTempStorage = new byte[32 * 1024];
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, width, height);
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            final Bitmap b = BitmapFactory.decodeFile(filePath, options);
            if (b != null) {
                final Bitmap bOut = Bitmap.createScaledBitmap(b, width, height, true);
                if (!bOut.equals(b)) {
                    b.recycle();
                }
                return bOut;
            }
        }
        return null;
    }

    /**
     * @return Bitmap or null...
     */
    public static Bitmap loadBitmap(final String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }

    private static int calculateInSampleSize(final BitmapFactory.Options options, final int reqWidth, final int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        Log.i("GEITH", "Samplesize =" + inSampleSize);
        return inSampleSize;
    }

    private static void saveAnimatedGif(final ByteArrayOutputStream bos, final String path) {
        FileOutputStream outStream = null;
        try {
            Log.i("GIF", "saving Animated Gif to " + path);
            outStream = new FileOutputStream(path);
            outStream.write(bos.toByteArray());
            outStream.close();
        } catch (final Exception e) {
            Log.e("GIF", "Error Saving Gif", e);
        }
    }

    public static void saveGif(final List<Bitmap> aniBitmaps, final AsyncTaskWithProgress task, final Activity activity) {
        // Create final animated gif
        if (aniBitmaps != null && !aniBitmaps.isEmpty()) {
            int delay = (Settings.getGifLength()) / aniBitmaps.size();
            if (delay < 10) {
                delay = 10;
            }
            final AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            encoder.start(bos);
            encoder.setQuality(Settings.getGifQuality());
            encoder.setRepeat(0);
            encoder.addFrame(aniBitmaps.get(aniBitmaps.size() - 1));
            encoder.setDelay(delay);
            int i = 0;
            for (final Bitmap bmp : aniBitmaps) {
                if (task != null) {
                    task.settingProgress(i, "");
                }
                encoder.addFrame(bmp);
                encoder.setDelay(delay);
                i++;
            }
            encoder.setDelay(2000); // Last Frame Delay!
            encoder.addFrame(aniBitmaps.get(aniBitmaps.size() - 1));
            encoder.setDelay(2000); // Last Frame Delay!

            final String gifFilePath = StorageHelper.getWallpaperGifDir() + "WPD_AnimatedGif_" + FileIOHelper.getTimeStampForFile() + ".gif";
            saveAnimatedGif(bos, gifFilePath);
            encoder.finish();
            for (final Bitmap bmp : aniBitmaps) {
                bmp.recycle();
            }
            MediaScannerHelper.rescanMedia(activity, gifFilePath);
        }
    }

}
