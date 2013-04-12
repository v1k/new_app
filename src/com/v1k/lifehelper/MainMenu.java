package com.v1k.lifehelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {
	
	private Button calendarButton;
	private Button tasksButton;
	private Button coastsButton;
	private Button listsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		calendarButton = (Button) findViewById (R.id.calendarButton);
		tasksButton = (Button) findViewById (R.id.tasksButton);
		coastsButton = (Button) findViewById (R.id.coastsButton);
		listsButton = (Button) findViewById (R.id.listsButton);
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void startCalendar(View view){
		Intent i = new Intent(this, CalendarActivity.class);
		startActivity(i);
	}
	
	public void startTasks(View view){
		Intent i = new Intent(this, TasksActivity.class);
		startActivity(i);
	}
	
	public void startCoasts(View view){
		Intent i = new Intent(this, CoastsActivity.class);
		startActivity(i);
	}
	
	public void startLists(View view){
		Intent i = new Intent(this, ListsActivity.class);
		startActivity(i);
	}
}
