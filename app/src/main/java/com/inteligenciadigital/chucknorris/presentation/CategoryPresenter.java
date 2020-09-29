package com.inteligenciadigital.chucknorris.presentation;

import com.inteligenciadigital.chucknorris.Colors;
import com.inteligenciadigital.chucknorris.MainActivity;
import com.inteligenciadigital.chucknorris.datasource.CategoryRemoteDataSource;
import com.inteligenciadigital.chucknorris.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements CategoryRemoteDataSource.ListCategoriesCallback {

	private final CategoryRemoteDataSource dataSource;
	private MainActivity mainActivity;

	public CategoryPresenter(MainActivity mainActivity , CategoryRemoteDataSource remoteDataSource) {
		this.mainActivity = mainActivity;
		this.dataSource = remoteDataSource;
	}

	public void requestAll() {
		// chamar um servidor HTTP
		this.mainActivity.showProgressBar();
		this.dataSource.findAll(this);
	}

	@Override
	public void onSuccess(List<String> response) {
		List<CategoryItem> categoryItems = new ArrayList<>();
		for (String val: response) {
			categoryItems.add(new CategoryItem(val, Colors.randomColor()));
		}
		this.mainActivity.showCategories(categoryItems);
	}

	@Override
	public void onError(String message) {
		this.mainActivity.showFailure(message);
	}

	@Override
	public void onComplete() {
		this.mainActivity.hideProgressBar();
	}
}
