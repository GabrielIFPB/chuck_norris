package com.inteligenciadigital.chucknorris.models;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.inteligenciadigital.chucknorris.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class CategoryItem extends Item<ViewHolder> {

	private final String categoryName;
	private final int bgColor;

	public CategoryItem(String categoryName, int bgColor) {
		this.categoryName = categoryName;
		this.bgColor = bgColor;
	}

	@Override
	public void bind(@NonNull ViewHolder viewHolder, int position) {
		TextView titleCategory = viewHolder.itemView.findViewById(R.id.title_category);
		titleCategory.setText(this.categoryName);
		viewHolder.itemView.setBackgroundColor(this.bgColor);
	}

	@Override
	public int getLayout() {
		return R.layout.card_category;
	}
}
