package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

}
