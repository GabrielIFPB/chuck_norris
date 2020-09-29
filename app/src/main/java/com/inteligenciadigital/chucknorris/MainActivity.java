package com.inteligenciadigital.chucknorris;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inteligenciadigital.chucknorris.datasource.CategoryRemoteDataSource;
import com.inteligenciadigital.chucknorris.models.CategoryItem;
import com.inteligenciadigital.chucknorris.presentation.CategoryPresenter;
import com.xwray.groupie.GroupAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private GroupAdapter adapter;
	private CategoryPresenter presenter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
		);

		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		RecyclerView recyclerView = findViewById(R.id.recycler_view_main);

		this.adapter = new GroupAdapter();
		recyclerView.setAdapter(this.adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		CategoryRemoteDataSource dataSource = new CategoryRemoteDataSource();
		this.presenter = new CategoryPresenter(this, dataSource);
		this.presenter.requestAll();
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
		if (this.progressDialog != null)
			this.progressDialog.hide();
	}

	public void showCategories(List<CategoryItem> categoryItems) {
		this.adapter.addAll(categoryItems);
		this.adapter.notifyDataSetChanged();
	}

	public void showFailure(String message) {
		this.toast(message);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
			drawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.nav_home) {

		}

		DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
		drawerLayout.closeDrawer(GravityCompat.START);

		return false;
	}

	private void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}