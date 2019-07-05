package com.focuseddeveloper.beans;

public class Users {
	
	public static final String ACCESS_ADMIN = "ADMIN";
	public static final String ACCESS_MEMBER = "MEMBER";
	public static final String ACCESS_GUEST = "GUEST";
	
	private int ID;
	private String access;
	private String email;
	private String password;
	private String name;
	
	public Users() {
		ID = 0;
		access = "Guest";
		email = "NA";
		password = "NA";
		name = "Guest";
	}
	
	public Users(int ID, String access, String email, String password, String name) {
		this.ID = ID;
		this.access = access;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
