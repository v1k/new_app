package com.v1k.lifehelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSources {
	
	private SQLiteDatabase database;
	private DBAdapter dbAdapter;
	private String [] taskColumns = {DBAdapter.COL_TASK_TITLE,
			DBAdapter.COL_TASK_BODY, DBAdapter.COL_TASK_DATE,
			DBAdapter.COL_TASK_WEEKDAYS, DBAdapter.COL_TASK_ALARM};
	
	public DataSources(Context context){
		dbAdapter = new DBAdapter(context);
	}
	
	public void open() throws SQLException{
		database = dbAdapter.getWritableDatabase();
	}
	
	public void clos() {
		dbAdapter.close();
	}
	
	public Task createTask(String title, String body, 
			Date date, String[] weekdays, boolean alarmed){
		ContentValues values = new ContentValues();
		values.put(DBAdapter.COL_TASK_TITLE, title);
		values.put(DBAdapter.COL_TASK_BODY, body);
		values.put(DBAdapter.COL_TASK_WEEKDAYS, weekdays.toString());
		values.put(DBAdapter.COL_TASK_DATE, date.toString());
		values.put(DBAdapter.COL_TASK_ALARM, alarmed);
		long insertId = database.insert(DBAdapter.TABLE_TASKS, null, values);
		
		Cursor cursor = database.query(DBAdapter.TABLE_TASKS, taskColumns, 
				DBAdapter.COL_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Task newTask = cursorToTask(cursor);
		cursor.close();
		return newTask;
	}
	
	public void deleteTask(Task task){
		long id = task.getId();
		System.out.println("Task delete with id: " + id);
		database.delete(DBAdapter.TABLE_TASKS, DBAdapter.COL_ID + " = " + id, null);
	}
	
	public List<Task> getAllTasks(){
		List<Task> tasks = new ArrayList <Task>();
		
		Cursor cursor = database.query(DBAdapter.TABLE_TASKS, taskColumns, 
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Task task = cursorToTask(cursor);
			tasks.add(task);
			cursor.moveToNext();
		}
		cursor.close();
		return tasks;
	}

	private Task cursorToTask(Cursor cursor) {
		Task task = new Task();
		task.setId(cursor.getLong(0));
		task.setTitle(cursor.getString(1));
		task.setBody(cursor.getString(2));
		task.setDate(cursor.getString(3));
		task.setWeekDays(cursor.getString(4));
		task.setAlarm(cursor.getInt(5) > 0);
		return task;
	}

}
