package com.v1k.lifehelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CoastsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coasts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coasts, menu);
		return true;
	}

}
