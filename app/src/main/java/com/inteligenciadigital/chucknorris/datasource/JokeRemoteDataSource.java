package com.inteligenciadigital.chucknorris.datasource;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.inteligenciadigital.chucknorris.models.Joke;
import com.inteligenciadigital.chucknorris.presentation.JokePresenter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JokeRemoteDataSource {

	public interface JokeCallback {
		void onSuccess(Joke response);

		void onError(String message);

		void onComplete();
	}

	public void findJokeBy(JokeCallback callback, String category) {
		new JokeTask(callback, category).execute();
	}

	private static class JokeTask extends AsyncTask<Void, Void, Joke> {

		private final JokeCallback jokeCallback;
		private final String catrgory;
		private String errorMessage;

		public JokeTask(JokeCallback callback, String category) {
			this.jokeCallback = callback;
			this.catrgory = category;
		}

		@Override
		protected Joke doInBackground(Void... voids) {

			Joke joke = null;

			HttpURLConnection urlConnection;

			try {
				String endpoint = String.format("%s?category=%s", EndPoint.GET_JOKE, this.catrgory);
				URL url = new URL(endpoint);
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setReadTimeout(2000);
				urlConnection.setConnectTimeout(2000);

				int responseCode = urlConnection.getResponseCode();
				if (responseCode > 400)
					throw new IOException("Erro na comunicação do servidor");

				InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
				JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));


				jsonReader.beginObject();

				String iconUrl = null;
				String value = null;

				while (jsonReader.hasNext()) {
					JsonToken token = jsonReader.peek();

					if (token == JsonToken.NAME) {
						String name = jsonReader.nextName();
						if (name.equals("category"))
							jsonReader.skipValue();
						else if (name.equals("icon_url"))
							iconUrl = jsonReader.nextString();
						else if (name.equals("value"))
							value = jsonReader.nextString();
						else
							jsonReader.skipValue();
					}
				}

				joke = new Joke(iconUrl, value);

			} catch (MalformedURLException e) {
				errorMessage = e.getMessage();
			} catch (IOException e) {
				errorMessage = e.getMessage();
			}
			return joke;
		}

		@Override
		protected void onPostExecute(Joke joke) {
			if (errorMessage != null)
				this.jokeCallback.onError(errorMessage);
			else
				this.jokeCallback.onSuccess(joke);
			this.jokeCallback.onComplete();
		}
	}
}
