package com.inteligenciadigital.chucknorris.datasource;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CategoryRemoteDataSource {

	public interface ListCategoriesCallback {
		void onSuccess(List<String> response);

		void onError(String message);

		void onComplete();
	}

	public void findAll(ListCategoriesCallback callback) {
		new CategoryTask(callback).execute();
	}

	private static class CategoryTask extends AsyncTask<Void, Void, List<String>> {

		private String errorMessage;
		private final ListCategoriesCallback categoriesCallback;

		private CategoryTask(ListCategoriesCallback categoriesCallback) {
			this.categoriesCallback = categoriesCallback;
		}

		@Override
		protected List<String> doInBackground(Void... voids) {
			List<String> response = new ArrayList<>();

			HttpURLConnection urlConnection = null;

			try {
				URL url = new URL(EndPoint.GET_CATEGORIES);
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setReadTimeout(2000);
				urlConnection.setConnectTimeout(2000);

				int responseCode = urlConnection.getResponseCode();
				if (responseCode > 400) {
					throw new IOException("Erro na comunicação do servidor");
				}

				InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
				JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));

				jsonReader.beginArray();
				while (jsonReader.hasNext()) {
					response.add(jsonReader.nextString());
				}
				jsonReader.endArray();

			} catch (MalformedURLException e) {
				errorMessage = e.getMessage();
			} catch (IOException e) {
				errorMessage = e.getMessage();
			}
			return response;
		}

		@Override
		protected void onPostExecute(List<String> strings) {
			if (errorMessage != null) {
				Log.i("TESTE", errorMessage);
				this.categoriesCallback.onError(errorMessage);
			} else {
				Log.i("TESTE", strings.toString());
				this.categoriesCallback.onSuccess(strings);
			}
			this.categoriesCallback.onComplete();
		}
	}
}
