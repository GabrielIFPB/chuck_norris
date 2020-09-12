package com.inteligenciadigital.chucknorris;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.inteligenciadigital.chucknorris.models.CategoryItem;
import com.xwray.groupie.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private GroupAdapter adapter;

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

		this.populateItens();
	}

	private void populateItens() {
		List<CategoryItem> itens = new ArrayList<>();
		itens.add(new CategoryItem("Cat1", 0xFF00FFFF));
		itens.add(new CategoryItem("Cat2", 0xFF00FFaF));
		itens.add(new CategoryItem("Cat3", 0xFF00FFbF));
		itens.add(new CategoryItem("Cat4", 0xFF00FFFF));
		itens.add(new CategoryItem("Cat5", 0xFF00eFFF));
		itens.add(new CategoryItem("Cat6", 0xFF00FcFF));
		itens.add(new CategoryItem("Cat7", 0xFF00FFFF));
		itens.add(new CategoryItem("Cat8", 0xFF00FFFF));
		itens.add(new CategoryItem("Cat9", 0xFF00FFFF));
		itens.add(new CategoryItem("Cat10", 0xFF00FFFF));
		itens.add(new CategoryItem("Cat11", 0xFF00FFFF));

		this.adapter.addAll(itens);
		this.adapter.notifyDataSetChanged();
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
}