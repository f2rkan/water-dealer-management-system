package com.otomasyon.util;

public class Kayitlar {
	
	private int memberID;
	private String user_name;
	private String password;
	private int authority;
	
	public Kayitlar(int memberID, String user_name, String password, int authority) {
		super();
		this.memberID = memberID;
		this.user_name = user_name;
		this.password = password;
		this.authority = authority;
	}

	public int getmemberID() {
		return memberID;
	}

	public void setmemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	
}
