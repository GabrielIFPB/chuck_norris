package com.inteligenciadigital.chucknorris.datasource;

import com.inteligenciadigital.chucknorris.models.Joke;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChuckNorrisAPI {

	static final String BASE_URL = "https://api.chucknorris.io/";

	@GET("jokes/categories")
	Call<List<String>> findAll();

	@GET("jokes/random")
	Call<Joke> findRandomBy(@Query("category") String category);
}
