package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String end_date;


    public TodoItem(String category, String title, String desc, String date, String endDate) {
    	this.category = category;
    	this.title = title;
    	this.desc = desc;
    	this.current_date = date;
    	this.end_date = endDate;
    }
    
    public TodoItem(String category, String title, String desc, String end_date){
    	this.category = category;
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.end_date = end_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    
    public String getCategry() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + current_date + "##" + end_date + "\n";
    }
    
    public String toFindString() {
    	return category+title+desc+current_date+end_date;
    }
}

