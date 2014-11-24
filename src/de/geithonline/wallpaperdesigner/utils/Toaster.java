package de.geithonline.wallpaperdesigner.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.geithonline.android.basics.preferences.R;

public class Toaster {

	private static final int TYPE_ERROR = 0;
	private static final int TYPE_INFO = 1;

	public static void showInfoToast(final Activity activity, final String msg) {
		showToast(TYPE_INFO, activity, msg);
	}

	public static void showErrorToast(final Activity activity, final String msg) {
		showToast(TYPE_ERROR, activity, msg);
	}

	private static void showToast(final int typ, final Activity activity, final String msg) {
		final LayoutInflater inflater = activity.getLayoutInflater();
		final View layout = inflater.inflate(R.layout.info_toast_layout, (ViewGroup) activity.findViewById(R.id.toast_layout_root));
		final ImageView image = (ImageView) layout.findViewById(R.id.image);

		if (typ == TYPE_ERROR) {
			image.setImageResource(android.R.drawable.ic_dialog_alert);
		} else {
			image.setImageResource(android.R.drawable.ic_dialog_info);
		}
		final TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(msg);

		final Toast toast = new Toast(activity.getApplicationContext());
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}
}
