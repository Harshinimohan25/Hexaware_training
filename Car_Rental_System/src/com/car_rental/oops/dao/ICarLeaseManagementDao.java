package com.car_rental.oops.dao;

import java.sql.Date;
import java.util.List;

import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.exception.LeaseNotFoundException;

public interface ICarLeaseManagementDao {
	Lease createLease(int customerID, int carID, Date startDate, Date endDate, String leaseType);

	void returnCar(int leaseID);
	List<Lease> listActiveLeases();
	 List<Lease> listLeaseHistory();


	 Lease getLeaseById(int leaseID, int carId) throws LeaseNotFoundException;
	    Lease getLeaseById(int leaseID) throws LeaseNotFoundException;




}
