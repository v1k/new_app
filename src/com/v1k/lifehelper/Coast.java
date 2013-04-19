package com.v1k.lifehelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Coast {
	private long cId;
	private String cName;
	private float cCoast;
	private Date cDate;
	private SimpleDateFormat dateFormat;
	
	public Coast(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	}
	
	public long getId(){
		return this.cId;
	}
	
	public String getName(){
		return this.cName;
	}
	
	public float getCoast(){
		return this.cCoast;
	}
	
	public Date getDate(){
		return this.cDate;
	}
	
	public String getDateInString(){
		return dateFormat.format(this.cDate);
	}
	
	public void setId(long id){
		this.cId = id;
	}
	
	public void setName(String name){
		this.cName = name;
	}
	
	public void setCoast(float coast){
		this.cCoast = coast;
	}
	
	public void setDate(Date date){
		this.cDate = date;
	}
	
	public void setDate (String date_str){
		this.cDate = parseDateFromString(date_str);
	}
	
	private Date parseDateFromString(String str){
		Date date;
	    try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			date = null;
		}
	    return date;
	}
}
