package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Bus;
import com.masai.entities.Passenger;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;

public class PassengerServiceImpl implements PassengerService {

	@Override
	public boolean login(String email, String password, Map<String, Passenger> passengers) throws InvalidDetailsException {
		if (passengers.containsKey(email) ) {
			
			if(passengers.get(email).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
			
		} else {
			throw new InvalidDetailsException("you have not sign up yet, please signup");
		}
	}

	@Override
	public void signUp(Passenger pas, Map<String, Passenger> customers) throws DuplicateDataException {
		
		if (customers.containsKey(pas.getEmailId())) {
			throw new DuplicateDataException("Passenger already exists , please login");
		} else {

			customers.put(pas.getEmailId(), pas);

		}
		
	}

	
	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Passenger> passengers) {
		
		Passenger psng = passengers.get(email);

		psng.setWalletBalance(psng.getWalletBalance() + amount);

		passengers.put(email, psng);

		return true;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Passenger> passengers) {
		Passenger psng = passengers.get(email);

		return psng.getWalletBalance();
	}

	@Override
	public Passenger viewPassengerDetails(String email, Map<String, Passenger> passengers) {
		
		if (passengers.containsKey(email)) {

			return passengers.get(email);

		}

		return null;
	}
	
	@Override
	public List<Passenger> viewAllPassengers(Map<String, Passenger> passengers) throws ProductException {
		
		List<Passenger> list = null;

		if (passengers != null && passengers.size() > 0) {
			Collection<Passenger> coll = passengers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new ProductException("No Passenger are there");
		}

		return list;
	}

	@Override
	public boolean bookTicket(int busId, int noOfSeat, String email, Map<Integer, Bus> bus,
			Map<String, Passenger> passengers, List<Transaction> transactions) throws InvalidDetailsException, ProductException {
		if (bus.size() == 0)
			throw new ProductException("Bus list is empty");

		if (bus.containsKey(busId)) {

			Bus bss = bus.get(busId);

			if (bss.getTotalSeat() >= noOfSeat) {

				Passenger psng = passengers.get(email);

				double ticketPrice = noOfSeat * bss.getPrice();

				if (psng.getWalletBalance() >= ticketPrice) {
					psng.setWalletBalance(psng.getWalletBalance() - ticketPrice);

					bss.setTotalSeat(bss.getTotalSeat() - noOfSeat);

					bus.put(busId, bss);

					Transaction tr = new Transaction(psng.getUsername(), email, busId, bss.getBusName(), noOfSeat, bss.getPrice(),
							bss.getPrice() * noOfSeat, LocalDate.now());

					transactions.add(tr);

				} else {
					throw new InvalidDetailsException("wallet balance is not sufficient");
				}

			} else {
				throw new InvalidDetailsException("no of seats are not suffiecient");
			}

		} else {
			throw new InvalidDetailsException("Bus not available with id: " + busId);
		}

		return false;
	}



}
