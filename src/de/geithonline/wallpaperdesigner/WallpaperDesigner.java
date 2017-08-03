package de.geithonline.wallpaperdesigner;

import android.app.Application;
import android.content.Context;

public class WallpaperDesigner extends Application {
	private static WallpaperDesigner instance;

	public static WallpaperDesigner getInstance() {
		return instance;
	}

	public static Context getContext() {
		return instance;
		// or return instance.getApplicationContext();
	}

	@Override
	public void onCreate() {
		instance = this;
		super.onCreate();
	}
}
