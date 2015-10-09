package de.geithonline.wallpaperdesigner.settingsdownloader;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.util.Log;
import de.geithonline.wallpaperdesigner.utils.Toaster;

@SuppressWarnings("deprecation")
public class SettingsUploader {

	public static void upload(final Activity activity, final String pathToFile, final String url) {
		final RequestParams params = new RequestParams();

		final File image1 = new File(pathToFile);
		Log.i("Upload", "Uploading " + pathToFile + " to " + url);
		try {
			params.put("image1", image1); // image 1 is the key(it uses key-value pair)
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}

		final AsyncHttpClient client = new AsyncHttpClient();
		// client.setBasicAuth("olivergeith_upload", "upload");

		client.post(url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(final int arg0, final Header[] arg1, final byte[] arg2) {
				Log.i("Upload", "OK");
				Toaster.showInfoToast(activity, "Design " + image1.getName() + " uploaded successfully!!");
			}

			@Override
			public void onFailure(final int arg0, final Header[] arg1, final byte[] arg2, final Throwable arg3) {
				Log.i("Upload", "Not OK: " + arg3.getMessage());
				Toaster.showErrorToast(activity, "Error uploading design: " + arg3.getMessage());
			}
		});
	}
}
