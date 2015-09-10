package de.geithonline.wallpaperdesigner.settingsdownloader;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SettingsUploader {

	public static String shareURL = "http://olivergeith.bplaced.net/shareddesigns/upload.php";
	public static String publishURL = "http://olivergeith.bplaced.net/publisheddesigns/upload.php";

	public static void upload(final Activity activity, final String pathToFile, final String url) {
		final RequestParams params = new RequestParams();

		final File image1 = new File(pathToFile);
		Log.i("Upload", "Uploading " + pathToFile);
		try {
			params.put("image1", image1); // image 1 is the key(it uses key-value pair)
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final AsyncHttpClient client = new AsyncHttpClient();
		client.setBasicAuth("olivergeith_upload", "upload");
		client.post("http://olivergeith.bplaced.net/shareddesigns/upload.php", params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(final int arg0, final Header[] arg1, final byte[] arg2) {
				Log.i("Upload", "OK");

			}

			@Override
			public void onFailure(final int arg0, final Header[] arg1, final byte[] arg2, final Throwable arg3) {
				Log.i("Upload", "Not OK");
			}
		});
	}
}
