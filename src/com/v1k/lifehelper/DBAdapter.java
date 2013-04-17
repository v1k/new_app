package com.v1k.lifehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBAdapter extends SQLiteOpenHelper{
	
	public static final String TABLE_TASKS = "tasks";
	public static final String COL_ID = "_id";
	public static final String COL_TASK_TITLE = "title";
	public static final String COL_TASK_BODY = "body";
	public static final String COL_TASK_DATE = "date";
	public static final String COL_TASK_WEEKDAYS = "weekdays";
	public static final String COL_TASK_ALARM = "alarm";

	public static final String TABLE_LISTS = "lists";
	public static final String COL_LIST_TITLE = "title";

	public static final String TABLE_COASTS = "coasts";
	public static final String COL_COAST_NAME = "name";
	public static final String COL_COAST_COAST = "coast";
	public static final String COL_COAST_DATE = "date";
	
	private static final String DATABASE_NAME = "lifehelper.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String CREATE_TABLE_TASKS = "create table "
			+ TABLE_TASKS + "(" + COL_ID 
			+ " integer primary key autoincrement, " + COL_TASK_TITLE
			+ " text not null, " + COL_TASK_BODY
			+ " text, " + COL_TASK_DATE
			+ " text, " + COL_TASK_WEEKDAYS
			+ " text, " + COL_TASK_ALARM
			+ " integer not null);";
	
	private static final String CREATE_TABLE_LISTS = "create table "
			+ TABLE_LISTS + "(" + COL_ID
			+ " integer primary key autoincrement, " + COL_LIST_TITLE
			+ " text not null);";
	
	private static final String CREATE_TABLE_COASTS = "create table "
			+ TABLE_COASTS + "(" + COL_ID
			+ " integer primary key autoincrement, " + COL_COAST_NAME
			+ " text not null," + COL_COAST_COAST
			+ " text not null," + COL_COAST_DATE
			+ " text not null);";
	
	public DBAdapter(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_TASKS);
		db.execSQL(CREATE_TABLE_LISTS);
		db.execSQL(CREATE_TABLE_COASTS);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBAdapter.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COASTS);
		onCreate(db);
		
	}
}
