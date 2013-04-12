package com.v1k.lifehelper;

import java.util.Date;

public class Task {
	private Date tDate;
	private String tTitle;
	private String tBody;
	private boolean tAlarm = false;
	
	public Task (String body){
		this.tBody = body;
	}
	
	public Task (Date date, String body){
		this.tDate = date;
		this.tBody = body;
	}
	
	public Task (String title, String body){
		this.tTitle = title;
		this.tBody = body;
	}
	
	public Task (Date date, String title, String body){
		this.tDate = date;
		this.tTitle = title;
		this.tBody = body;
	}
	
	public Task (Date date, String title, String body, boolean alarmed){
		this.tDate = date;
		this.tTitle = title;
		this.tBody = body;
		this.tAlarm = true;
	}
	
	public void editDate (Date date){
		this.tDate = date;
	}
	
	public void editTitle (String title){
		this.tTitle = title;
	}
	
	public void editBody(String body){
		this.tBody = body;
	}
	
	
}
