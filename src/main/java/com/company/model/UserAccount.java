package com.company.model;

public class UserAccount {
	private int user_id;
	private String user_email;
	private String user_password;
	private String user_name;
	private int user_type;
	public UserAccount() {
		this.user_id = 0;
		this.user_email = "";
		this.user_password = "";
		this.user_name = "";
		this.user_type = 0;
	}
	public UserAccount(String user_email, String user_password, String user_name) {
		this.user_id = 0;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_type = 0;
	}
	public UserAccount(int user_id, String user_email, String user_password, String user_name, int user_type) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_type = user_type;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String type() {
		if (this.user_type == 0) {
			return "Default user";
		}
		if (this.user_type == 1) {
			return "Moderator";
		}
		if (this.user_type == 2) {
			return "Administrator";
		}
		else {
			return "unknown";
		}
	}
}
