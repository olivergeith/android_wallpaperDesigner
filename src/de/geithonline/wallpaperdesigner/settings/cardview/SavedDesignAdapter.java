package de.geithonline.wallpaperdesigner.settings.cardview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import de.geithonline.wallpaperdesigner.R;
import de.geithonline.wallpaperdesigner.settings.SavedDesign;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;

/**
 * Created by Trey Robinson on 8/3/14. Copyright 2014 MindMine LLC.
 */
public class SavedDesignAdapter extends RecyclerView.Adapter<SavedDesignAdapter.ViewHolder> {

	private final List<SavedDesign> countries;
	private final int rowLayout;
	private final Context mContext;

	public SavedDesignAdapter(final List<SavedDesign> countries, final int rowLayout, final Context context) {
		this.countries = countries;
		this.rowLayout = rowLayout;
		mContext = context;
	}

	@Override
	public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
		final View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		final SavedDesign design = countries.get(i);
		final String text = SettingsIO.stripTimestamp(design.getPreferenceFile().getName());

		viewHolder.cardText.setText(text);
		viewHolder.cardImage.setImageBitmap(design.getBitmap());
	}

	@Override
	public int getItemCount() {
		return countries == null ? 0 : countries.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView cardText;
		public ImageView cardImage;

		public ViewHolder(final View itemView) {
			super(itemView);
			cardText = (TextView) itemView.findViewById(R.id.cardText);
			cardImage = (ImageView) itemView.findViewById(R.id.cardImage);
		}

	}
}
