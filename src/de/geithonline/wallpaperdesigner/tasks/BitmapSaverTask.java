package de.geithonline.wallpaperdesigner.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import de.geithonline.wallpaperdesigner.bitmapdrawer.IBmpRenderer;

public class BitmapSaverTask extends AsyncTask<Void, Void, Integer> {

	private final ProgressDialog dialog;
	private final IBmpRenderer drawer;
	private final Context context;

	public BitmapSaverTask(final ProgressDialog dialog, final IBmpRenderer drawer, final Context context) {
		this.dialog = dialog;
		this.drawer = drawer;
		this.context = context;
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
		if (dialog != null) {
			dialog.cancel();
		}
	}

}
