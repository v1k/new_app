package com.v1k.lifehelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TasksDataSource {
	
	private SQLiteDatabase database;
	private DBAdapter dbAdapter;
	private String [] taskColumns = {DBAdapter.COL_TASK_TITLE,
			DBAdapter.COL_TASK_BODY, DBAdapter.COL_TASK_DATE,
			DBAdapter.COL_TASK_WEEKDAYS, DBAdapter.COL_TASK_ALARM};
	
	public TasksDataSource(Context context){
		dbAdapter = new DBAdapter(context);
	}
	
	public void open() throws SQLException{
		database = dbAdapter.getWritableDatabase();
	}
	
	public void clos() {
		dbAdapter.close();
	}
	
	public Task createTask(Task task){
		
	}
}
