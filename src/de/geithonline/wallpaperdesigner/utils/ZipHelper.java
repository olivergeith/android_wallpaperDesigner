package de.geithonline.wallpaperdesigner.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class ZipHelper {
	public static void unzip(final String zipFile, String outPath) {
		if (!outPath.endsWith(File.separator)) {
			outPath = outPath + File.separator;
		}
		try {
			final FileInputStream fin = new FileInputStream(zipFile);
			final ZipInputStream zis = new ZipInputStream(fin);
			ZipEntry entry = null;

			while ((entry = zis.getNextEntry()) != null) {
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

	public static void unzipSettings(final Activity activity, final Context context) {
		Toaster.showInfoToast(activity, "Extracting Sample Settings!");
		// final int settingsID = de.geithonline.wallpaperdesigner.R.raw.settings;
		// unzipResourceRawFiles(context, settingsID);
	}

	public static void unzipResourceRawFiles(final Context context, final int fileResID) {
		InputStream stream;

		context.getFilesDir().mkdirs();
		final String outPath = StorageHelper.getExternalStorageSettings();

		try {
			stream = context.getResources().openRawResource(fileResID);
			if (stream == null) {
				throw new RuntimeException("Cannot load " + fileResID + " file from raw folder");
			}

			final ZipInputStream zis = new ZipInputStream(stream);
			ZipEntry entry;

			while ((entry = zis.getNextEntry()) != null) {
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

		} catch (final IOException e) {
			throw new RuntimeException("Cannot unzip '" + fileResID + "'", e);
		}

	}

}
