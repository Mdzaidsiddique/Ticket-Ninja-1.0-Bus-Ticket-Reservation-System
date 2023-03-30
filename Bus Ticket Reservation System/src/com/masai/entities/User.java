package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	
	private String username;
	private String password;
	private String mobileNo;
	private String emailId;
	
	public User() {
		super();
	}

	public User(String username, String password, String mobileNo, String emailId) {
		super();
		this.username = username;
		this.password = password;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", mobileNo=" + mobileNo + ", emailId="
				+ emailId + "]";
	}

	
	
	
	

}
