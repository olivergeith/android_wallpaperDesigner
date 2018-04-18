
package de.geithonline.wallpaperdesigner.tasks;

import java.lang.ref.WeakReference;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import de.geithonline.wallpaperdesigner.settings.CustomAdapter;
import de.geithonline.wallpaperdesigner.settings.Design;
import de.geithonline.wallpaperdesigner.settings.DesignIO;

public class DesignsLoaderTask extends AsyncTask<Void, Void, List<Design>> {
    private final WeakReference<ListView> weekListViewRef;
    private final Activity activity;

    public DesignsLoaderTask(final ListView imageView, final Activity activity) {
        this.activity = activity;
        // Use a WeakReference to ensure the ImageView can be garbage
        // collected
        weekListViewRef = new WeakReference<>(imageView);
    }

    // Load List background.
    @Override
    protected List<Design> doInBackground(final Void... params) {
        final List<Design> savedPreferencesList = DesignIO.getSavedPreferencesList();
        return savedPreferencesList;
    }

    // Once complete.....
    @Override
    protected void onPostExecute(final List<Design> savedPreferencesList) {
        if (weekListViewRef != null && savedPreferencesList != null) {
            final ListView list = weekListViewRef.get();
            if (list != null) {
                list.setAdapter(new CustomAdapter(activity, savedPreferencesList));
            }
        }
    }
}
