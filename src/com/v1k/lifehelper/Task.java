package com.v1k.lifehelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {
	private long tId;
	private Date tDate;
	private SimpleDateFormat iso8601Format;
	private String tTitle;
	private String tBody;
	private int[] tWeekDays = {0, 0, 0, 0, 0, 0, 0};
	private boolean tAlarm = false;
	
	public Task(){
		iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	}
	
	public void setId(long id){
		this.tId = id;
	}
	
	public void setDate (Date date){
		this.tDate = date;
	}
	
	public void setDate (String date_str){
		this.tDate = parseDateFromString(date_str);
	}
	
	public void setTitle (String title){
		this.tTitle = title;
	}
	
	public void setBody(String body){
		this.tBody = body;
	}
	
	public void setWeekDays(int [] weekdays){
		this.tWeekDays = weekdays;
	}
	
	public void setWeekDays(String weekdays_str){
		int [] weekdays = {};
		String [] wkd;
		wkd = weekdays_str.split(";");
		for (int i = 0; i < wkd.length; i++)
			weekdays[i] = Integer.valueOf(i);
		this.tWeekDays = weekdays;
	}
	
	public void setAlarm(boolean alarm){
		this.tAlarm = alarm;
	}
	
	public long getId(){
		return this.tId;
	}
	
	public String getTitle(){
		return this.tTitle;
	}
	
	public String getBody(){
		return this.tBody;
	}
	
	public Date getDate(){
		return this.tDate;
	}
	
	public String getDateInString(){
		return iso8601Format.format(this.tDate);
	}
	
	public int [] getWeekDays(){
		return this.tWeekDays;
	}
	
	public boolean isAlarmed(){
		return this.tAlarm;
	}
	
	private Date parseDateFromString(String str){
		Date date;
	    try {
			date = iso8601Format.parse(str);
		} catch (ParseException e) {
			date = null;
		}
	    return date;
	}
}
