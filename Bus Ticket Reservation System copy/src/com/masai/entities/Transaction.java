package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable{
	
	private String username;
	private String email;
	private int busId;
	private String busName;
	private int noOfSeat;
	private double price;
	private double total;
	private LocalDate dt;
	
	public Transaction() {
		
	}
	
	public String getbName() {
		return busName;
	}


	public void setbName(String busName) {
		this.busName = busName;
	}


	public Transaction(String username, String email, int busId, String busName, int noOfSeat, double price,
			double total, LocalDate dt) {
		super();
		this.username = username;
		this.email = email;
		this.busId = busId;
		this.busName = busName;
		this.noOfSeat = noOfSeat;
		this.price = price;
		this.total = total;
		this.dt = dt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "Transaction [username=" + username + ", email=" + email + ", busId=" + busId + ", busName=" + busName
				+ ", noOfSeat=" + noOfSeat + ", price=" + price + ", total=" + total + ", dt=" + dt + "]";
	}
	
	
	

}
