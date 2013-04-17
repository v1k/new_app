package com.v1k.lifehelper;

import java.util.Date;

public class Task {
	private Date tDate;
	private String tTitle;
	private String tBody;
	private boolean tAlarm = false;
	
	public void setDate (Date date){
		this.tDate = date;
	}
	
	public void setTitle (String title){
		this.tTitle = title;
	}
	
	public void setBody(String body){
		this.tBody = body;
	}
	
	public void setAlarm(boolean alarm){
		this.tAlarm = alarm;
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
	
	public boolean isAlarmed(){
		return this.tAlarm;
	}
}
