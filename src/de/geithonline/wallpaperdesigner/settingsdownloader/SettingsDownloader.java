package de.geithonline.wallpaperdesigner.settingsdownloader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.util.Log;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;
import de.geithonline.wallpaperdesigner.utils.Alerter;
import de.geithonline.wallpaperdesigner.utils.StorageHelper;

public class SettingsDownloader extends AsyncTask<String, String, String> {

	private static Activity activi;
	private static ProgressDialog dialog;
	private static String msg;
	private String errorMessage = "";

	public static void startDownloadFile(final Activity activity, final String url) {
		String name;
		if (url.contains(".com")) {
			name = url.substring(url.indexOf(".com") + 5);
		} else if (url.contains(".net")) {
			name = url.substring(url.indexOf(".net") + 5);
		} else {
			name = url.substring(url.indexOf("/") + 1);
		}
		Alerter.alertYesNo(activity, "Download " + name + " ?", "Download Example Designs", new OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				startDownload(activity, url, "settings.zip");
			}
		});
	}

	private static void startDownload(final Activity activity, final String url, final String destinationFileName) {
		activi = activity;
		dialog = new ProgressDialog(activity);
		dialog.setIndeterminate(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setProgressNumberFormat(null);
		msg = "Downloading \n" + destinationFileName;
		dialog.setMessage(msg);
		dialog.setProgress(0);
		dialog.setMax(100);
		dialog.setCancelable(false);
		dialog.show();

		new SettingsDownloader().execute(url, destinationFileName);
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
			if (lenghtOfFile == -1) {
				dialog.setIndeterminate(true);
				dialog.setProgressPercentFormat(null); // % ausbelenden
			}
			Log.i("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

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
			Log.e("ANDRO_ASYNC", "Error downloading File " + e.getMessage());
			errorMessage = "Error with File " + aurl[0] + " " + e.getMessage();
			if (dialog != null) {
				dialog.cancel();
			}
		}
		return null;

	}

	@Override
	protected void onProgressUpdate(final String... progress) {
		// Log.d("ANDRO_ASYNC", progress[0]);
		if (progress[0].startsWith("-")) {
			final int p = Integer.parseInt(progress[0].substring(1));
			dialog.setMessage(msg + "\n" + p / 100 / 1000 + "kB");
		} else {
			final int p = Integer.parseInt(progress[0]);
			dialog.setProgress(p);
		}
	}

	@Override
	protected void onPostExecute(final String localFile) {
		if (localFile != null) {
			Log.i("ANDRO_ASYNC", "File downloaded: " + localFile);
			final String outPath = StorageHelper.getExternalStorageSettings();
			dialog.setMessage("Unzipping " + localFile);
			unzip(localFile, outPath);
			dialog.setMessage("Done !");
			if (dialog != null) {
				dialog.cancel();
			}
			Alerter.alertInfo(activi,
					"Example-Designs downloaded successfully!!!\n\nHint: Swipe from the left on Main-Screen to reveal the drawer with all your designs!");
			SettingsIO.setDesignListNeedsReload(true);
		} else {
			if (dialog != null) {
				dialog.cancel();
			}
			Alerter.alertError(activi, "Error downloading Example-Designs!!!\n\n" + errorMessage + "\n\nInternet connection available?");
			// Toaster.showErrorToast(activi, "Error downloading Example-Settings!!!\n" + errorMessage);
		}
	}

	public static void unzip(final String zipFile, String outPath) {
		if (!outPath.endsWith(File.separator)) {
			outPath = outPath + File.separator;
		}
		try {
			final FileInputStream fin = new FileInputStream(zipFile);
			final ZipInputStream zis = new ZipInputStream(fin);
			ZipEntry entry = null;

			while ((entry = zis.getNextEntry()) != null) {
				dialog.setMessage("Unzipping " + entry.getName());
				Log.v("Decompress", "Unzipping " + entry.getName());
				if (entry.isDirectory()) {
					final File f = new File(outPath, entry.getName());
					if (!f.exists()) {
						f.mkdirs();
					}
				} else {
					int size;
					final byte[] buffer = new byte[2048];

					final File f = new File(outPath, entry.getName());
					final FileOutputStream fos = new FileOutputStream(f);
					final BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

					while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, size);
					}
					bos.flush();
					bos.close();
				}
			}

			zis.close();
			fin.close();
		} catch (final Exception e) {
			Log.e("Decompress", "unzip", e);
		}

	}

}
