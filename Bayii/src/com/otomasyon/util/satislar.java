package com.otomasyon.util;

import java.sql.*;
public class satislar {
	private int salesID, type;
	private String user, about_selling;
	private double about_price;
	private Date about_date;
	
	public satislar(int salesID, int type, String user, String about_selling, double about_price, Date about_date) {
		
		this.salesID = salesID;
		this.type = type;
		this.user = user;
		this.about_selling = about_selling;
		this.about_price = about_price;
		this.about_date = about_date;
	}
	public int getSalesID() {
		return salesID;
	}
	public void setSalesID(int salesID) {
		this.salesID = salesID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAbout_selling() {
		return about_selling;
	}
	public void setAbout_selling(String about_selling) {
		this.about_selling = about_selling;
	}
	public double getAbout_price() {
		return about_price;
	}
	public void setAbout_price(double about_price) {
		this.about_price = about_price;
	}
	public Date getAbout_date() {
		return about_date;
	}
	public void setAbout_date(Date about_date) {
		this.about_date = about_date;
	}
	
	
}
