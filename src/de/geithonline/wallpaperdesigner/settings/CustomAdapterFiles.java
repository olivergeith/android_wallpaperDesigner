package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.utils.DebugHelper;

public class CustomAdapterFiles extends BaseAdapter {
	Context context;
	private List<File> fileList;

	public void setFileList(final List<File> fileList) {
		this.fileList = fileList;
	}

	private static LayoutInflater inflater = null;

	public CustomAdapterFiles(final Activity mainActivity, final List<File> fileList) {
		context = mainActivity;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.fileList = fileList;
	}

	@Override
	public int getCount() {
		return fileList.size();
	}

	@Override
	public Object getItem(final int position) {
		return fileList.get(position);
	}

	@Override
	public long getItemId(final int position) {
		return position;
	}

	public class Holder {
		TextView fileNameView;
		TextView fileSizeView;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		final Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.custom_adapter_filelist, null);
		holder.fileNameView = (TextView) rowView.findViewById(R.id.filename);
		holder.fileSizeView = (TextView) rowView.findViewById(R.id.filesize);
		final String text = fileList.get(position).getName();
		final String size = DebugHelper.humanReadableByteCount(fileList.get(position).length(), true);
		holder.fileNameView.setText(text);
		holder.fileSizeView.setText(size);
		return rowView;
	}

}