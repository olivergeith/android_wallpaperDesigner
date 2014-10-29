package de.geithonline.wallpaperdesigner.settings;

import java.io.File;

import android.graphics.Bitmap;

public class SavedPreference {
	private final Bitmap bitmap;
	private final File preference;

	public SavedPreference(final Bitmap bitmap, final File preference) {
		super();
		this.bitmap = bitmap;
		this.preference = preference;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public File getPreferenceFile() {
		return preference;
	}

}
