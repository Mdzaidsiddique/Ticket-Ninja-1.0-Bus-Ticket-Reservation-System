package com.masai.service;

import java.util.ArrayList;
import java.util.List;

import com.masai.entities.Transaction;
import com.masai.exceptions.TransactionException;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public List<Transaction> viewPassengerTransactions(String email, List<Transaction> transactions) throws TransactionException {
		
		List<Transaction> myTransactions = new ArrayList<>();

		boolean flag = false;
		for (Transaction tr : transactions) {
			if (tr.getEmail().equals(email)) {

				myTransactions.add(tr);

				flag = true;
			}
		}
		if (!flag) {
			throw new TransactionException("There no transaction happens yet!");
		}

		return myTransactions;

	}

	@Override
	public List<Transaction> viewAllTransactions(List<Transaction> transactions) throws TransactionException {
		
		if(transactions != null && transactions.size()>0) {
			return transactions;
		}
		else {
			throw new TransactionException("There no transaction happens till now!");
		}
	}

}
