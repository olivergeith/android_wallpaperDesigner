
package de.geithonline.wallpaperdesigner.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class BitmapHelper {

    public static Bitmap rotate(final Bitmap bitmap, final float winkel) {
        final Matrix matrix = new Matrix();
        matrix.postRotate(winkel);
        final Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return rotated;
    }

    public static Bitmap flip(final Bitmap src) {
        final Matrix m = new Matrix();
        m.preScale(1, -1);
        final Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);
        return dst;
    }

    public static Bitmap crop(final Bitmap sourceBitmap, final Rect rect) {
        final Bitmap cropped = Bitmap.createBitmap(sourceBitmap, rect.left, rect.top, rect.width(), rect.height());
        return cropped;
    }

    public static Drawable resizeToIcon(final Bitmap bitmap, final int width, final int height) {
        final Bitmap b = Bitmap.createScaledBitmap(bitmap, width, height, true);
        final Drawable d = new BitmapDrawable(Resources.getSystem(), b);
        return d;
    }

    public static Drawable bitmapToIcon(final Bitmap bitmap) {
        final Drawable d = new BitmapDrawable(Resources.getSystem(), bitmap);
        return d;
    }

    public static Drawable resizeToIcon64(final Bitmap bitmap) {
        return resizeToIcon(bitmap, 64, 64);
    }

    public static Drawable resizeToIcon128(final Bitmap bitmap) {
        return resizeToIcon(bitmap, 128, 128);
    }

    public static Bitmap convertToAlphaMask(final Bitmap b) {
        final Bitmap a = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ALPHA_8);
        final Canvas c = new Canvas(a);
        c.drawBitmap(b, 0.0f, 0.0f, null);
        return a;
    }

    /**
     * @param inBitmap
     *            needs to be recycled outside!!!
     * @param brightness
     *            -100 bis 100
     * @param contrast
     *            -100 bis 100
     * @param saturation
     *            -100 bis 100
     * @param hue
     *            -180 bis 180
     * @return
     */
    public static Bitmap getColorFilteredBitmap(final Bitmap inBitmap, final int brightness, final int contrast, final int saturation, final int hue) {
        final Bitmap outBitmap = Bitmap.createBitmap(inBitmap.getWidth(), inBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas c = new Canvas(outBitmap);
        final Paint paint = new Paint();
        paint.setColorFilter(ColorFilterGenerator.adjustColor(brightness, contrast, saturation, hue));
        c.drawBitmap(inBitmap, 0, 0, paint);
        return outBitmap;
    }

    public static Bitmap getMaskedBitmap(final Bitmap source, final Bitmap mask) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            options.inMutable = true;
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap;
        if (source.isMutable()) {
            bitmap = source;
        } else {
            bitmap = source.copy(Bitmap.Config.ARGB_8888, true);
            source.recycle();
        }
        bitmap.setHasAlpha(true);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mask, 0, 0, paint);
        // mask.recycle();
        return bitmap;
    }

    public static Bitmap scallToWidth(final Bitmap bitmap, final int width) {
        final int w = bitmap.getWidth();
        final int h = bitmap.getHeight();
        final int dw = width;
        final int dh = h * dw / w;
        final Bitmap small = Bitmap.createScaledBitmap(bitmap, dw, dh, true);
        return small;
    }

    public static Bitmap tintBitmap(final Bitmap bitmap, final Paint paint, final int color) {
        paint.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_IN));
        final Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmapResult);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmapResult;
    }

    public static Bitmap tintBitmap(final Bitmap bitmap, final int color) {
        final Paint paint = new Paint();
        return tintBitmap(bitmap, paint, color);
    }

    /**
     * @param res
     *            Activity/Context.getResources()
     * @param id
     *            R.drawable.app_logo
     * @return
     */
    public static Bitmap getBitmapFromResource(final Resources res, final int id) {
        final Bitmap bm = BitmapFactory.decodeResource(res, id);
        return bm;
    }

}
