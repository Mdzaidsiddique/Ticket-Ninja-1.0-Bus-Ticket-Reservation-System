package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.entities.Bus;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;

public interface BusService {

	public String addBus(Bus bus, Map<Integer, Bus> buses);

	public void viewAllBuses(Map<Integer, Bus> buses) throws ProductException;

	public void deleteBus(int busId, Map<Integer, Bus> buses) throws ProductException;

	public String updateBusDetails(int busId, Bus bus, Map<Integer, Bus> buses) throws ProductException;

	
	
}
