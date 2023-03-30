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

		BusService prodService = new BusServiceImpl();
		PassengerService cusService = new PassengerServiceImpl();
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
					String added = adminAddBus(sc, buses, prodService);
					System.out.println(added);
					break;
				case 2:

					adminViewAllBuses(buses, prodService);
					break;
				case 3:

					adminDeleteBus(sc, buses, prodService);
					break;
				case 4:

					String upt = adminUpdateBusDetails(sc, buses, prodService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllCustomers(passengers, cusService);

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

	
	public static String adminAddBus(Scanner sc, Map<Integer, Bus> buses, BusService prodService) {

		String str = null;
		System.out.println("please enter the bus details");
		System.out.println("Enter the bus name");
		String name = sc.next();
		System.out.println("Enter the product qty");
		int qty = sc.nextInt();
		System.out.println("Enter the product price/piece");
		double price = sc.nextDouble();
		System.out.println("Enter the product category");
		String cate = sc.next();

		Bus prod = new Bus(IDGeneration.generateId(), name, qty, cate, price, cate, cate, cate);

		str = prodService.addProduct(prod, passengers);// considering all details are valid

		return str;

	}

	public static void adminViewAllProducts(Map<Integer, Product> products, ProductService prodService)
			throws ProductException {
		prodService.viewAllProducts(products);
	}

	public static void adminDeleteProduct(Scanner sc, Map<Integer, Product> products, ProductService prodService)
			throws ProductException {

		System.out.println("please enter the id of product to be deleted");
		int id = sc.nextInt();
		prodService.deleteProduct(id, products);
	}

	public static String adminUpdateProduct(Scanner sc, Map<Integer, Product> products, ProductService prodService)
			throws ProductException {
		String result = null;
		System.out.println("please enter the id of the product which is to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");

		System.out.println("Enter the product name");
		String name = sc.next();

		System.out.println("Enter the  product qty");
		int qty = sc.nextInt();

		System.out.println("Enter the product price/piece");
		double price = sc.nextDouble();

		System.out.println("Enter the product category");
		String cate = sc.next();

		Product update = new Product(id, name, qty, price, cate);

		result = prodService.updateProduct(id, update, products);
		return result;
	}

	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
			throws ProductException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
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

	// customer functionality
	public static void passengerFunctionality(Scanner sc, Map<String, Passenger> passengers,
			Map<Integer, Bus> products, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		PassengerService cusService = new PassengerServiceImpl();
		BusService prodService = new BusServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();

		// Customer login
		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		passengerLogin(email,pass, passengers, cusService);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all products");
				System.out.println("Press 2 to buy a product");
				System.out.println("Press 3 to add money to a wallet");
				System.out.println("Press 4 view wallet balance");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 view my transactions");
				System.out.println("Press 7 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					passengerViewAllBuses(products, prodService);
					break;
				case 2:
					String result = passengerBuyTicket(sc, email, products, passengers, transactions, pasService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = passengerAddMoneyToWallet(sc, email, passengers, cusService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = passengerViewWalletBalance(email, passengers, cusService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					passengerViewMyDetails(email, passengers, cusService);
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
		
		Passenger pas = new Passenger(email, name, pass, address, balance);

		PassengerService cusService = new PassengerServiceImpl();
		cusService.signUp(pas, passengers);
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

		return "You have successfully bought the product";

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
						" '1' --> Admin login , '2' --> Passenger login , "
						+ "'3' for Passenger signup, '0' for exit");
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, buses, passengers, transactions);
					break;
				case 2:
					passengerFunctionality(sc, passengers, buses, transactions);
					break;

				case 3:
					passengerSignup(sc, passengers);
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
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Product.ser"));
				poos.writeObject(buses);
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
				coos.writeObject(passengers);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(transactions);

			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
		}

	}

}
