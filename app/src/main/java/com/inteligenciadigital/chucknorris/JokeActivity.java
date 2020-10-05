package com.inteligenciadigital.chucknorris;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class JokeActivity extends AppCompatActivity {

	static final String CATEGORY_KEY = "category_key";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_joke);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (this.getIntent().getExtras() != null) {
			String category = this.getIntent().getExtras().getString(CATEGORY_KEY);

			if (this.getSupportActionBar() != null) {
				this.getSupportActionBar().setTitle(category);
				this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			}

			Log.i("TESTE", category);
		}

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(view -> {
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				this.finish();
				return true;
			default:
				return true;
		}
	}
}