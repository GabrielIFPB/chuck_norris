package com.inteligenciadigital.chucknorris;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

	private static final int UI_ANIMATION_DELAY = 300;
	private final Handler mHideHandler = new Handler();
	private View mContentView;

	private final Runnable mHidePart2Runnable = new Runnable() {
		@SuppressLint("InlinedApi")
		@Override
		public void run() {
			mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

			delayShow(2000);
		}
	};

	private final Runnable mShowPart2Runnable = new Runnable() {
		@Override
		public void run() {
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
		}
	};

	private final Runnable mShowRunnable = new Runnable() {
		@Override
		public void run() {
			show();
		}
	};

	private final Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			hide();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		mContentView = findViewById(R.id.fullscreen_content);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		delayedHide(100);
	}

	private void hide() {
		mHideHandler.removeCallbacks(mShowPart2Runnable);
		mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
	}

	private void show() {
		// Show the system bar
		mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

		// Schedule a runnable to display UI elements after a delay
		mHideHandler.removeCallbacks(mHidePart2Runnable);
		mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
	}

	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}

	private void delayShow(int delayMillis) {
		mHideHandler.removeCallbacks(mShowRunnable);
		mHideHandler.postDelayed(mShowRunnable, delayMillis);
	}
}
