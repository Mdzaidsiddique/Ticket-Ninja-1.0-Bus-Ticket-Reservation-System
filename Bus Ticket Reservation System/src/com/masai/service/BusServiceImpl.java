package com.masai.service;

import java.util.Map;

import com.masai.entities.Bus;
import com.masai.exceptions.ProductException;

public class BusServiceImpl implements BusService{

	@Override
	public String addBus(Bus bus, Map<Integer, Bus> buses) {
		//have to add bus with some details like name, destination, busType etc
		buses.put(bus.getId(), bus);

		return "Bus added successfully";
	}
	
	
	@Override
	public void viewAllBuses(Map<Integer, Bus> buses) throws ProductException {
		
		if (buses != null && buses.size() > 0) {
			for (Map.Entry<Integer, Bus> me : buses.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			throw new ProductException("Product List is empty");
		}
		
	}

	@Override
	public void deleteBus(int busId, Map<Integer, Bus> buses) throws ProductException {
		
		if (buses != null && buses.size() > 0) {

			if (buses.containsKey(busId)) {
				buses.remove(busId);
				System.out.println("Bus deleted successfully");

			} else {
				throw new ProductException("Bus details not found");
			}

		} else {
			throw new ProductException("There are no bus in the list");
		}
		
	}

	@Override
	public String updateBusDetails(int busId, Bus bus, Map<Integer, Bus> buses) throws ProductException {
		

		if (buses != null && buses.size() > 0) {

			if (buses.containsKey(busId)) {
				buses.put(busId, bus);
				return "Bus details has successfully updated";
			} else {
				throw new ProductException("Bus details are not found");
			}

		} else {
			throw new ProductException("There are no bus in the list");
		}
	}

}
