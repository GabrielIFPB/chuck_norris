package com.inteligenciadigital.chucknorris.models;

import com.google.gson.annotations.SerializedName;

public class Joke {

	@SerializedName("icon_url")
	private final String iconUrl;

	@SerializedName("value")
	private final String value;

	public Joke(String iconUrl, String value) {
		this.iconUrl = iconUrl;
		this.value = value;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getValue() {
		return value;
	}
}
