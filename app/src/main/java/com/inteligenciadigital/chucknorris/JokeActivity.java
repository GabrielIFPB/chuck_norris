package com.inteligenciadigital.chucknorris;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inteligenciadigital.chucknorris.datasource.JokeRemoteDataSource;
import com.inteligenciadigital.chucknorris.models.Joke;
import com.inteligenciadigital.chucknorris.presentation.JokePresenter;
import com.squareup.picasso.Picasso;

public class JokeActivity extends AppCompatActivity {

	static final String CATEGORY_KEY = "category_key";

	private ProgressDialog progressDialog;

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

				JokeRemoteDataSource dataSource = new JokeRemoteDataSource();
				JokePresenter presenter = new JokePresenter(this, dataSource);
				presenter.findJokeBy(category);

				FloatingActionButton fab = findViewById(R.id.fab);
				fab.setOnClickListener(view -> {
					presenter.findJokeBy(category);
				});
			}
		}
	}

	public void showJoke(Joke joke) {
		TextView textJoke = this.findViewById(R.id.text_joke);
		textJoke.setText(joke.getValue());

		ImageView imageView = this.findViewById(R.id.img_icon);
		Picasso.get().load(joke.getIconUrl()).into(imageView);
	}

	public void showFailure(String message) {
		this.toast(message);
	}

	public void showProgressBar() {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setMessage(getString(R.string.loading));
			this.progressDialog.setIndeterminate(true);
			this.progressDialog.setCancelable(false);
		}
		this.progressDialog.show();
	}

	public void hideProgressBar() {
		if (this.progressDialog != null) {
			this.progressDialog.hide();
			this.progressDialog.cancel();
		}
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

	@Override
	public boolean onSupportNavigateUp() {
//		this.verificaRequisicaoAtiva();
		return false;
	}

	private void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}