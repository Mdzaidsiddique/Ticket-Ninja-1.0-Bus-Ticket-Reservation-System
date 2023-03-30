package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.entities.Bus;
import com.masai.entities.Passenger;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;

public interface PassengerService {
	
	public boolean login(String email,String password, Map<String, Passenger> passengers) throws InvalidDetailsException;

	public void signUp(Passenger pas, Map<String, Passenger> customers) throws DuplicateDataException;

	
	public boolean bookTicket(int busId, int noOfSeat, String email, Map<Integer, Bus> bus,
			Map<String, Passenger> passengers, List<Transaction> transactions)
			throws InvalidDetailsException, ProductException;

	public boolean addMoneyToWallet(double amount, String email, Map<String, Passenger> passengers);

	public double viewWalletBalance(String email, Map<String, Passenger> passengers);

	public Passenger viewPassengerDetails(String email, Map<String, Passenger> passengers);

	public List<Passenger> viewAllPassengers(Map<String, Passenger> passengers) throws ProductException;
	
}
