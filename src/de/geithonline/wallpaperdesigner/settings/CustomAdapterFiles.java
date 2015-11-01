package de.geithonline.wallpaperdesigner.settings;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.utils.BitmapFileIO;
import de.geithonline.wallpaperdesigner.utils.FileIOHelper;

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
		final String text = fileList.get(position).getName();
		holder.textview.setText(text);

		final File zip = fileList.get(position);
		final String jpgFilename = FileIOHelper.replaceExtension(zip.getAbsolutePath(), DesignIO.EXTENSION_ZIP, DesignIO.EXTENSION_JPG);
		Log.i("CustomAdapterFiles", "jpgFilename = " + jpgFilename);
		final Bitmap bmp = BitmapFileIO.loadBitmap(jpgFilename);

		if (bmp != null) {
			holder.imgView.setImageBitmap(bmp);
		} else {
			Log.i("CustomAdapterFiles", "BMP was null");
		}

		return rowView;
	}

}