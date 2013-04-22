package com.v1k.lifehelper;

public class MyList {
	
	private long lId;
	private String lTitle;
	
	public long getId(){
		return this.lId;
	}
	
	public String getTitle(){
		return this.lTitle;
	}
	
	public void setId(long id){
		this.lId = id;
	}
	
	public void setTitle(String title){
		this.lTitle = title;
	}
	
	@Override
	  public String toString() {
	    return this.lTitle;
	  }

}
