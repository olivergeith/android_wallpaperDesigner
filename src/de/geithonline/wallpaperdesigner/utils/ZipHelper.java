package de.geithonline.wallpaperdesigner.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class ZipHelper {
	public static void unzip(final String zipFile, String outPath) {
		if (!outPath.endsWith(File.separator)) {
			outPath = outPath + File.separator;
		}
		try (final FileInputStream fin = new FileInputStream(zipFile); final ZipInputStream zis = new ZipInputStream(fin)) {
			ZipEntry entry = null;

			while ((entry = zis.getNextEntry()) != null) {
				Log.v("Decompress", "Unzipping " + entry.getName());
				final File f = new File(outPath, entry.getName());
				final String canonicalPath = f.getCanonicalPath();
				if (!canonicalPath.startsWith(outPath)) {
					throw new SecurityException("Illegal Path in Zipfile: " + canonicalPath);
				}
				if (entry.isDirectory()) {
					if (!f.exists()) {
						f.mkdirs();
					}
				} else {
					int size;
					final byte[] buffer = new byte[2048];

					final FileOutputStream fos = new FileOutputStream(f);
					final BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

					while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, size);
					}
					bos.flush();
					bos.close();
				}
			}

		} catch (final Exception e) {
			Log.e("Decompress", "unzip", e);
		}

	}

	public static void unzipSettings(final Activity activity, final Context context) {
		Toaster.showInfoToast(activity, "Extracting Sample Settings!");
		// final int settingsID =
		// de.geithonline.wallpaperdesigner.R.raw.settings;
		// unzipResourceRawFiles(context, settingsID);
	}

	public static void unzipResourceRawFiles(final Context context, final int fileResID) {
		InputStream stream;

		context.getFilesDir().mkdirs();
		final String outPath = StorageHelper.getDesignsDir();
		Log.i("Raw Unzipping", "unzipping Design to " + outPath);

		try {
			stream = context.getResources().openRawResource(fileResID);
			if (stream == null) {
				throw new RuntimeException("Cannot load " + fileResID + " file from raw folder");
			}

			final ZipInputStream zis = new ZipInputStream(stream);
			ZipEntry entry;

			while ((entry = zis.getNextEntry()) != null) {
				final File f = new File(outPath, entry.getName());
				final String canonicalPath = f.getCanonicalPath();
				if (!canonicalPath.startsWith(outPath)) {
					throw new SecurityException("Illegal Path in Zipfile: " + canonicalPath);
				}

				if (entry.isDirectory()) {
					if (!f.exists()) {
						f.mkdirs();
					}
				} else {
					int size;
					final byte[] buffer = new byte[2048];
					final FileOutputStream fos = new FileOutputStream(f);
					final BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

					while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, size);
					}
					bos.flush();
					bos.close();
				}
			}
		} catch (final SecurityException e) {
			Log.i("Security", e.getMessage());

		} catch (final IOException e) {
			throw new RuntimeException("Cannot unzip '" + fileResID + "'", e);
		}
	}

	// private static void zipFolder(String inputFolderPath, String outZipPath)
	// {
	// try {
	// FileOutputStream fos = new FileOutputStream(outZipPath);
	// ZipOutputStream zos = new ZipOutputStream(fos);
	// File srcFile = new File(inputFolderPath);
	// File[] files = srcFile.listFiles();
	// Log.d("", "Zip directory: " + srcFile.getName());
	// for (int i = 0; i < files.length; i++) {
	// Log.d("", "Adding file: " + files[i].getName());
	// byte[] buffer = new byte[1024];
	// FileInputStream fis = new FileInputStream(files[i]);
	// zos.putNextEntry(new ZipEntry(files[i].getName()));
	// int length;
	// while ((length = fis.read(buffer)) > 0) {
	// zos.write(buffer, 0, length);
	// }
	// zos.closeEntry();
	// fis.close();
	// }
	// zos.close();
	// } catch (IOException ioe) {
	// Log.e("", ioe.getMessage());
	// }
	// }

	/**
	 * 
	 * Zips a file at a location and places the resulting zip file at the toLocation Example: zipFileAtPath("downloads/myfolder", "downloads/myFolder.zip");
	 */
	public static boolean zipFiles(final List<String> files, final String toLocation) {
		final int BUFFER = 2048;
		try {

			BufferedInputStream origin = null;
			final FileOutputStream dest = new FileOutputStream(toLocation);
			final ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

			for (final String sourcePath : files) {
				final byte data[] = new byte[BUFFER];
				final FileInputStream fi = new FileInputStream(sourcePath);
				origin = new BufferedInputStream(fi, BUFFER);
				final ZipEntry entry = new ZipEntry(getLastPathComponent(sourcePath));
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
			}
			out.close();
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Zips a file at a location and places the resulting zip file at the toLocation Example: zipFileAtPath("downloads/myfolder", "downloads/myFolder.zip");
	 */
	public static boolean zipFileAtPath(final String sourcePath, final String toLocation) {
		// ArrayList<String> contentList = new ArrayList<String>();
		final int BUFFER = 2048;

		final File sourceFile = new File(sourcePath);
		try {
			BufferedInputStream origin = null;
			final FileOutputStream dest = new FileOutputStream(toLocation);
			final ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			if (sourceFile.isDirectory()) {
				zipSubFolder(out, sourceFile, sourceFile.getParent().length());
			} else {
				final byte data[] = new byte[BUFFER];
				final FileInputStream fi = new FileInputStream(sourcePath);
				origin = new BufferedInputStream(fi, BUFFER);
				final ZipEntry entry = new ZipEntry(getLastPathComponent(sourcePath));
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
			}
			out.close();
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 
	 * Zips a subfolder
	 */

	private static void zipSubFolder(final ZipOutputStream out, final File folder, final int basePathLength) throws IOException {

		final int BUFFER = 2048;

		final File[] fileList = folder.listFiles();
		BufferedInputStream origin = null;
		for (final File file : fileList) {
			if (file.isDirectory()) {
				zipSubFolder(out, file, basePathLength);
			} else {
				final byte data[] = new byte[BUFFER];
				final String unmodifiedFilePath = file.getPath();
				final String relativePath = unmodifiedFilePath.substring(basePathLength);
				Log.i("ZIP SUBFOLDER", "Relative Path : " + relativePath);
				final FileInputStream fi = new FileInputStream(unmodifiedFilePath);
				origin = new BufferedInputStream(fi, BUFFER);
				final ZipEntry entry = new ZipEntry(relativePath);
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}
		}
	}

	/*
	 * gets the last path component
	 * 
	 * Example: getLastPathComponent("downloads/example/fileToZip"); Result: "fileToZip"
	 */
	private static String getLastPathComponent(final String filePath) {
		final String[] segments = filePath.split("/");
		final String lastPathComponent = segments[segments.length - 1];
		return lastPathComponent;
	}

}
