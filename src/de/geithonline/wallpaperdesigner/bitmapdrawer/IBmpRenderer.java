
package de.geithonline.wallpaperdesigner.bitmapdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import de.geithonline.wallpaperdesigner.tasks.AsyncTaskWithProgress;

public interface IBmpRenderer {

    public Bitmap drawBitmap(AsyncTaskWithProgress task);

    public Bitmap drawBitmap(final int width, final int height);

    public void recycleBitmap();

    public void save(Context context, final boolean withSettings);

    public void saveSmallImageAndSettings(final Context context);

    public Bitmap getBitmap();

}
