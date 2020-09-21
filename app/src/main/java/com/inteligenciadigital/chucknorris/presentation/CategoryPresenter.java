package com.inteligenciadigital.chucknorris.presentation;

import android.os.Handler;

import com.inteligenciadigital.chucknorris.MainActivity;
import com.inteligenciadigital.chucknorris.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter {

	private MainActivity mainActivity;

	private static List<CategoryItem> itens = new ArrayList<>();
	static {
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
	}

	public CategoryPresenter(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public void requestAll() {
		// chamar um servidor HTTP
		this.mainActivity.showProgressBar();
		this.request();
	}

	public void onSuccess(List<CategoryItem> itens) {
		this.mainActivity.showCategories(itens);
	}

	public void onError(String message) {
		this.mainActivity.showFailure(message);
	}

	public void onComplete() {
		this.mainActivity.hideProgressBar();
	}

	private void request() {
		new Handler().postDelayed(() -> {
			try {
				onSuccess(itens);
//				throw new Exception("Falha ao buscar categorias");
			} catch (Exception e) {
				onError(e.getMessage());
			} finally {
				onComplete();
			}
		}, 5000);
	}
}
