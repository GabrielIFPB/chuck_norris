package com.inteligenciadigital.chucknorris.presentation;

import com.inteligenciadigital.chucknorris.JokeActivity;
import com.inteligenciadigital.chucknorris.datasource.JokeRemoteDataSource;
import com.inteligenciadigital.chucknorris.models.Joke;

public class JokePresenter implements JokeRemoteDataSource.JokeCallback {
	
	private final JokeActivity view;
	private final JokeRemoteDataSource dataSource;

	public JokePresenter(JokeActivity jokeActivity, JokeRemoteDataSource dataSource) {
		this.view = jokeActivity;
		this.dataSource = dataSource;
	}

	public void findJokeBy(String category) {
		this.view.showProgressBar();
		this.dataSource.findJokeBy(this, category);
	}

	@Override
	public void onSuccess(Joke response) {
		this.view.showJoke(response);
	}

	@Override
	public void onError(String message) {
		this.view.showFailure(message);
	}

	@Override
	public void onComplete() {
		this.view.hideProgressBar();
	}
}
