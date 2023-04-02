package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Bus;
import com.masai.entities.Passenger;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;
import com.masai.service.BusService;
import com.masai.service.BusServiceImpl;
import com.masai.service.PassengerService;
import com.masai.service.PassengerServiceImpl;
import com.masai.service.TransactionService;
import com.masai.service.TransactionServiceImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IDGeneration;

public class Main {

	// admin functionality
	private static void adminFunctionality(Scanner sc, Map<Integer, Bus> buses, Map<String, Passenger> passengers,
			List<Transaction> transactions) throws InvalidDetailsException, ProductException, TransactionException {
		// admin login

		adminLogin(sc);

		BusService busService = new BusServiceImpl();
		PassengerService pasService = new PassengerServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 to add new bus");
				System.out.println("Press 2 to view all the buses");
				System.out.println("Press 3 to delete the bus");
				System.out.println("Press 4 to update the bus details");
				System.out.println("Press 5 to view all passengers");
				System.out.println("Press 6 to view all transactions");
				System.out.println("Press 7 to log out");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddBus(sc, buses, busService);
					System.out.println(added);
					break;

				case 2:

					adminViewAllBus(buses, busService);
					break;
				case 3:

					adminDeleteProduct(sc, buses, busService);
					break;
				case 4:

					String upt = adminUpdateBus(sc, buses, busService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllPassengers(passengers, pasService);

					break;
				case 6:
					adminViewAllTransactions(transactions, trnsactionService);
					break;
				case 7:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}



	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {

			System.out.println("login successfully ");
		} else {
			throw new InvalidDetailsException("Wrong Credentials");
		}
	}

	
	public static String adminAddBus(Scanner sc, Map<Integer, Bus> buses, BusService busService) {

		String str = null;
		System.out.println("please enter the bus details");
		
		System.out.println("Enter the bus name");
		String name = sc.next();
		
		System.out.println("Enter seat size");
		int totalSeat = sc.nextInt();
		
		System.out.println("Enter bus type");
		String busType = sc.next();
		
		System.out.println("Enter the ticket price");
		double price = sc.nextDouble();
		
		System.out.println("Enter destination");
		String destination = sc.next();
		
		
		Bus bus = new Bus(name, totalSeat, busType, price, destination);

		str = busService.addBus(bus, buses);

		return str;

	}

	public static void adminViewAllBus(Map<Integer, Bus> bus, BusService busService)
			throws ProductException {
		busService.viewAllBuses(bus);
	}

	public static void adminDeleteProduct(Scanner sc, Map<Integer, Bus> buses, BusService busService)
			throws ProductException {

		System.out.println("please enter the bus id to be deleted");
		int id = sc.nextInt();
		busService.deleteBus(id, buses);
	}

	public static String adminUpdateBus(Scanner sc, Map<Integer, Bus> buses, BusService busService)
			throws ProductException {
		String result = null;
		System.out.println("please enter the bus id of the bus which is to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");

		System.out.println("please enter the bus details");
		
		System.out.println("Enter the bus name");
		String name = sc.next();
		
		System.out.println("Enter seat size");
		int totalSeat = sc.nextInt();
		
		System.out.println("Enter bus type");
		String busType = sc.next();
		
		System.out.println("Enter the ticket price");
		double price = sc.nextDouble();
		
		System.out.println("Enter destination");
		String destination = sc.next();

		Bus update = new Bus(name, totalSeat, busType, price, destination);

		result = busService.updateBusDetails(id, update, buses);
		return result;
	}

	public static void adminViewAllPassengers(Map<String, Passenger> passengers, PassengerService pasService)
			throws ProductException {
		List<Passenger> list = pasService.viewAllPassengers(passengers);

		for (Passenger c : list) {
			System.out.println(c);
		}
	}

	public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService trnsactionService)
			throws TransactionException {
		List<Transaction> allTransactions = trnsactionService.viewAllTransactions(transactions);

		for (Transaction tr : allTransactions) {
			System.out.println(tr);
		}

	}

	// passenger functionality
	public static void passengerFunctionality(Scanner sc, Map<String, Passenger> passengers,
			Map<Integer, Bus> buses, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		PassengerService pasService = new PassengerServiceImpl();
		BusService busService = new BusServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();

		// passenger login
		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		passengerLogin(email,pass, passengers, pasService);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all buses");
				System.out.println("Press 2 to book a ticket");
				System.out.println("Press 3 to add money to a wallet");
				System.out.println("Press 4 view wallet balance");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 view my transactions");
				System.out.println("Press 7 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					passengerViewAllBuses(buses, busService);
					break;
				case 2:
					String result = passengerBuyTicket(sc, email, buses, passengers, transactions, pasService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = passengerAddMoneyToWallet(sc, email, passengers, pasService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = passengerViewWalletBalance(email, passengers, pasService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					passengerViewMyDetails(email, passengers, pasService);
					break;
				case 6:
					passengersViewPassengerTransactions(email, transactions, trnsactionService);
					break;
				case 7:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void passengerSignup(Scanner sc, Map<String, Passenger> passengers) throws DuplicateDataException {
		System.out.println("please enter the following details in order to Signup");
		System.out.println("please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		
		
		//have to look into it
		Passenger pas = new Passenger(email, name, pass, address, balance);

		PassengerService pasService = new PassengerServiceImpl();
		pasService.signUp(pas, passengers);
		System.out.println("Passenger has sign up Succefully !");

	}

	
	public static void passengerLogin(String email,String pass, Map<String, Passenger> passengers, PassengerService pasService)
			throws InvalidDetailsException {
		pasService.login(email, pass, passengers);
		System.out.println("Passenger has login successfully");

	}
	
	

	public static void passengerViewAllBuses(Map<Integer, Bus> buses, BusService busService)
			throws ProductException {
		busService.viewAllBuses(buses);
	}

	
	public static String passengerBuyTicket(Scanner sc, String email, Map<Integer, Bus> buses,
			Map<String, Passenger> passengers, List<Transaction> transactions, PassengerService pasService)
			throws InvalidDetailsException, ProductException {
		System.out.println("Please enter the bus id");
		int id = sc.nextInt();
		System.out.println("How many ticket would you like to book?");
		int qty = sc.nextInt();
		pasService.bookTicket(id, qty, email, buses, passengers, transactions);

		return "You have successfully book the ticket";

	}
	

	public static String passengerAddMoneyToWallet(Scanner sc, String email, Map<String, Passenger> passengers,
			PassengerService pasService) {
		System.out.println("Please enter the amount");
		double money = sc.nextDouble();
		boolean added = pasService.addMoneyToWallet(money, email, passengers);

		return "Amount: " + money + " successfully added to your wallet";
	}
	
	

	public static double passengerViewWalletBalance(String email, Map<String, Passenger> passengers,
			PassengerService pasService) {
		double walletBalance = pasService.viewWalletBalance(email, passengers);
		return walletBalance;
	}
	
	

	public static void passengerViewMyDetails(String email, Map<String, Passenger> passengers,
			PassengerService cusService) {
		Passenger psng = cusService.viewPassengerDetails(email, passengers);
		System.out.println("Name : " + psng.getUsername());
		System.out.println("Mobile No : " + psng.getMobileNo());
		System.out.println("Email Id: " + psng.getEmailId());
		System.out.println("Wallet Balance : " + psng.getWalletBalance());
	}
	
	

	public static void passengersViewPassengerTransactions(String email, List<Transaction> transactions,
			TransactionService trnsactionService) throws TransactionException {
		List<Transaction> myTransactions = trnsactionService.viewPassengerTransactions(email, transactions);

		for (Transaction tr : myTransactions) {
			System.out.println(tr);
		}
	}
	
	
	
	
	
	
	

	
	//main method();
	public static void main(String[] args) {
        
		Map<Integer, Bus> buses = FileExists.productFile();
		Map<String, Passenger> passengers = FileExists.customerFile();
		List<Transaction> transactions = FileExists.transactionFile();

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome , to Bus Ticket Reservation System...!");

		try {

			int preference = 0;
			do {
				System.out.println("Please enter your preference, " + 
						" '1' --> Admin login , '2' -->  Passenger signup , "
						+ "'3' for Passenger login, '0' for exit");
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, buses, passengers, transactions);
					break;
				case 2:
					passengerSignup(sc, passengers);
					break;

				case 3:
					passengerFunctionality(sc, passengers, buses, transactions);
					break;

				case 0:
					System.out.println("successfully exited from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Option Selection");
				}

			}

			while (preference != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			try {
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Bus.ser"));
				boos.writeObject(buses);
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Person.ser"));
				poos.writeObject(passengers);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(transactions);

			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
		}

	}

}
