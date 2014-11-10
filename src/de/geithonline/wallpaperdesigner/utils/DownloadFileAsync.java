package de.geithonline.wallpaperdesigner.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

class DownloadFileAsync extends AsyncTask<String, String, String> {

	public static void startDownload(final String url, final String destinationFileName) {
		// final String url = "http://farm1.static.flickr.com/114/298125983_0e4bf66782_b.jpg";
		new DownloadFileAsync().execute(url, destinationFileName);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(final String... aurl) {
		int count;

		try {

			final URL url = new URL(aurl[0]);
			final String destinationFileName = aurl[1];
			final URLConnection conexion = url.openConnection();
			conexion.connect();

			final int lenghtOfFile = conexion.getContentLength();
			Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

			final InputStream input = new BufferedInputStream(url.openStream());
			final String localFile = StorageHelper.getExternalStorage() + File.separator + destinationFileName;
			final OutputStream output = new FileOutputStream(localFile);

			final byte data[] = new byte[1024];

			long total = 0;

			while ((count = input.read(data)) != -1) {
				total += count;
				publishProgress("" + (int) ((total * 100) / lenghtOfFile));
				output.write(data, 0, count);
			}

			output.flush();
			output.close();
			input.close();
			return localFile;
		} catch (final Exception e) {
			Log.e("ANDRO_ASYNC", "Error downloading File " + aurl[0]);
		}
		return null;

	}

	@Override
	protected void onProgressUpdate(final String... progress) {
		Log.d("ANDRO_ASYNC", progress[0]);
	}

	@Override
	protected void onPostExecute(final String localFile) {

	}
}
