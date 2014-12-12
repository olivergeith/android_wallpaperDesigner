package de.geithonline.wallpaperdesigner;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import de.geithonline.wallpaperdesigner.settings.SettingsIO;
import de.geithonline.wallpaperdesigner.settings.cardview.SavedDesignAdapter;

public class DesignDisplay extends Activity {

	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private SavedDesignAdapter mAdapter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design_display);
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		// specify an adapter (see also next example)
		mAdapter = new SavedDesignAdapter(SettingsIO.getSavedPreferencesList(), R.layout.design_card, this);
		mRecyclerView.setAdapter(mAdapter);

	}
}
