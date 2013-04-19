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
	private String [] taskColumns = {DBAdapter.COL_ID,
			DBAdapter.COL_TASK_TITLE, DBAdapter.COL_TASK_BODY, 
			DBAdapter.COL_TASK_DATE, DBAdapter.COL_TASK_WEEKDAYS, 
			DBAdapter.COL_TASK_ALARM};
	private String [] listColumns = {DBAdapter.COL_ID, 
			DBAdapter.COL_LIST_TITLE};
	private String [] coastColumns = {DBAdapter.COL_ID, 
			DBAdapter.COL_COAST_NAME, DBAdapter.COL_COAST_COAST, 
			DBAdapter.COL_COAST_DATE};
	
	public DataSources(Context context){
		dbAdapter = new DBAdapter(context);
	}
	
	public void open() throws SQLException{
		database = dbAdapter.getWritableDatabase();
	}
	
	public void close() {
		dbAdapter.close();
	}
	
	public Task createTask(String title, String body, 
			Date date, int[] weekdays, boolean alarmed){
		ContentValues values = new ContentValues();
		values.put(DBAdapter.COL_TASK_TITLE, title);
		values.put(DBAdapter.COL_TASK_BODY, body);
		String forWeekDays = "";
		for(int i = 0; i < weekdays.length;)
			forWeekDays = forWeekDays + weekdays[i] + ";"; 
		values.put(DBAdapter.COL_TASK_WEEKDAYS, weekdays.toString());
		SimpleDateFormat iso8601Format = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		values.put(DBAdapter.COL_TASK_DATE, iso8601Format.format(date));
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
	
	public MyList createMyList(String title){
		ContentValues values = new ContentValues();
		values.put(DBAdapter.COL_LIST_TITLE, title);
		long insertId = database.insert(DBAdapter.TABLE_LISTS, null, values);
		
		Cursor cursor = database.query(DBAdapter.TABLE_LISTS, listColumns, 
				DBAdapter.COL_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		MyList newList = cursorToList(cursor);
		cursor.close();
		return newList;
	}
	
	public void deleteMyList(MyList list){
		long id = list.getId();
		System.out.println("List delete with id: " + id);
		database.delete(DBAdapter.TABLE_LISTS, DBAdapter.COL_ID + " = " + id, null);
	}
	
	public List<MyList> getAllMyLists(){
		List<MyList> lists = new ArrayList <MyList>();
		
		Cursor cursor = database.query(DBAdapter.TABLE_LISTS, listColumns, 
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			MyList list = cursorToList(cursor);
			lists.add(list);
			cursor.moveToNext();
		}
		cursor.close();
		return lists;
	}

	private MyList cursorToList(Cursor cursor) {
		MyList list = new MyList();
		list.setId(cursor.getLong(0));
		list.setTitle(cursor.getString(1));
		return list;
	}
	
	public Coast createCoast(String name, long coast, Date date){
		ContentValues values = new ContentValues();
		values.put(DBAdapter.COL_COAST_NAME, name);
		values.put(DBAdapter.COL_COAST_COAST, String.valueOf(coast));
		SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		values.put(DBAdapter.COL_TASK_DATE, dateFormat.format(date));
		long insertId = database.insert(DBAdapter.TABLE_COASTS, null, values);
		
		Cursor cursor = database.query(DBAdapter.TABLE_COASTS, coastColumns, 
				DBAdapter.COL_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Coast newCoast = cursorToCoast(cursor);
		cursor.close();
		return newCoast;
	}
	
	public void deleteCoast(Coast coast){
		long id = coast.getId();
		System.out.println("Coast delete with id: " + id);
		database.delete(DBAdapter.TABLE_COASTS, DBAdapter.COL_ID + " = " + id, null);
	}
	
	public List<Coast> getAllCoasts(){
		List<Coast> coasts = new ArrayList <Coast>();
		
		Cursor cursor = database.query(DBAdapter.TABLE_COASTS, coastColumns, 
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Coast coast = cursorToCoast(cursor);
			coasts.add(coast);
			cursor.moveToNext();
		}
		cursor.close();
		return coasts;
	}

	private Coast cursorToCoast(Cursor cursor) {
		Coast coast = new Coast();
		coast.setId(cursor.getLong(0));
		coast.setName(cursor.getString(1));
		coast.setCoast(cursor.getFloat(2));
		coast.setDate(cursor.getString(3));
		return coast;
	}
}
