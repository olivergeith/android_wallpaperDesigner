package de.geithonline.wallpaperdesigner.settings;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.geithonline.wallpaperdesigner.R;

public class CustomAdapter extends BaseAdapter {
	Context context;
	private List<SavedPreference> preferenceList;

	public void setPreferenceList(final List<SavedPreference> preferenceList) {
		this.preferenceList = preferenceList;
	}

	private static LayoutInflater inflater = null;

	public CustomAdapter(final Activity mainActivity, final List<SavedPreference> preferences) {
		context = mainActivity;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		preferenceList = preferences;
	}

	@Override
	public int getCount() {
		return preferenceList.size();
	}

	@Override
	public Object getItem(final int position) {
		return preferenceList.get(position).getPreferenceFile();
	}

	@Override
	public long getItemId(final int position) {
		return position;
	}

	public class Holder {
		TextView textview;
		ImageView imgView;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		final Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.custom_adapter2, null);
		holder.textview = (TextView) rowView.findViewById(R.id.textView1);
		holder.imgView = (ImageView) rowView.findViewById(R.id.imageView1);
		final String text = SettingsIO.stripTimestamp(preferenceList.get(position).getPreferenceFile().getName());
		holder.textview.setText(text);
		// Wenn Bild vorhanden nur bild anzeigen
		if (preferenceList.get(position).getBitmap() != null) {
			holder.imgView.setImageBitmap(preferenceList.get(position).getBitmap());
			// holder.textview.setVisibility(View.INVISIBLE);
		} else {
			// sonst nur text
			holder.textview.setVisibility(View.VISIBLE);
			// holder.imgView.setVisibility(View.INVISIBLE);
		}
		// rowView.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(final View v) {
		// Toast.makeText(context, "You Clicked Position " + position,
		// Toast.LENGTH_LONG).show();
		// }
		// });
		return rowView;
	}

}