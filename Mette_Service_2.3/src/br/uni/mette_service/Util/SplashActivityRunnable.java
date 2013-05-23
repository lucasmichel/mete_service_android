package br.uni.mette_service.Util;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivityRunnable extends Activity implements Runnable {
	private final int DELAY = 5000;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash);

		Handler handler = new Handler();
		handler.postDelayed(this, DELAY);
	}

	public void run() {
		Intent intent = new Intent(getApplicationContext(), LogarAndroidActivity.class);
		startActivity(intent);
		finish();

	}
}
