package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.masai.utility.IDGeneration;

public class Bus implements Serializable{
	
	private int id;
	private String busName;
	private String destination;
	private String busType;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int totalSeat;
	private double price;
	
	public Bus() {
		super();
	}

	public Bus(String busName, int totalSeat, String busType, double price, String destination) {
		super();
		this.id = IDGeneration.generateId();
		this.busName = busName;
		this.totalSeat = totalSeat;
		this.busType = busType;
		this.price = price;
		this.departureTime = LocalDateTime.now();
		this.arrivalTime = LocalDateTime.now();
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", busName=" + busName + ", destination=" + destination + ", busType=" + busType
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", totalSeat=" + totalSeat
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrivalTime, busName, busType, departureTime, destination, id, price, totalSeat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		return Objects.equals(arrivalTime, other.arrivalTime) && Objects.equals(busName, other.busName)
				&& Objects.equals(busType, other.busType) && Objects.equals(departureTime, other.departureTime)
				&& Objects.equals(destination, other.destination) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& totalSeat == other.totalSeat;
	}



	
	
	
	

}
