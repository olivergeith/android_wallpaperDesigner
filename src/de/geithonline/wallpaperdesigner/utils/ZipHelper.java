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
	public static void unzip(final String zipFile, String unzipLocation) {
		if (!unzipLocation.endsWith(File.separator)) {
			unzipLocation = unzipLocation + File.separator;
		}
		try {
			final FileInputStream fin = new FileInputStream(zipFile);
			final ZipInputStream zin = new ZipInputStream(fin);
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null) {
				Log.v("Decompress", "Unzipping " + ze.getName());

				if (ze.isDirectory()) {
					dirChecker(ze.getName(), unzipLocation);
				} else {
					final FileOutputStream fout = new FileOutputStream(unzipLocation + ze.getName());
					for (int c = zin.read(); c != -1; c = zin.read()) {
						fout.write(c);
					}
					zin.closeEntry();
					fout.close();
				}

			}
			zin.close();
		} catch (final Exception e) {
			Log.e("Decompress", "unzip", e);
		}

	}

	private static void dirChecker(final String dir, final String unzipLocation) {
		final File f = new File(unzipLocation + dir);

		if (!f.isDirectory()) {
			f.mkdirs();
		}
	}

	public static void unzipSettings(final Activity activity, final Context context) {
		Toaster.showInfoToast(activity, "Extracting Sample Settings!");
		final int settingsID = de.geithonline.wallpaperdesigner.R.raw.settings;
		unzipResourceRawFiles(context, settingsID);
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
