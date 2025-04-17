package com.car_rental.oops.dao;

import java.util.List;

import com.car_rental.oops.entity.Customer;
import com.car_rental.oops.exception.CustomerNotFoundException;

public interface ICustomerManagementRepositoryDao {
	
	void  addCustomer(Customer customer);
	 void removeCustomer(int customerID);
	List<Customer>listCustomers();
	
	Customer findCustomerById(int customerID) throws CustomerNotFoundException;

//	• Customer Management
//	1. addCustomer(Customer customer)
//	parameter : Customer
//	return type : void
//	2. void removeCustomer(int customerID)
//	parameter : CustomerID
//	return type : void
//	3. listCustomers()
//	parameter : NIL
//	return type : list of customer
//	4. findCustomerById(int customerID)
//	parameter : CustomerID
//	return type : Customer


}
