package com.inteligenciadigital.chucknorris.datasource;

public class EndPoint {
	static final String BASE_URL = "https://api.chucknorris.io/";
	static final String GET_CATEGORIES = BASE_URL + "jokes/categories";
	static final String GET_JOKE = BASE_URL + "jokes/random"; // ?category={category}
}
