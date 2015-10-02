package de.geithonline.wallpaperdesigner.settings;

import java.io.File;

import android.graphics.Bitmap;

public class Design {
	private final Bitmap bitmap;
	private final File preference;
	private final File bmpFile;

	public Design(final Bitmap bitmap, final File preference, final File bmpFile) {
		super();
		this.bitmap = bitmap;
		this.preference = preference;
		this.bmpFile = bmpFile;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public File getPreferenceFile() {
		return preference;
	}

	public File getBmpFile() {
		return bmpFile;
	}

}
