package com.car_rental.oops.dao;

import java.util.List;

import com.car_rental.oops.entity.Vehicle;
import com.car_rental.oops.exception.CarNotFoundException;

public interface  ICarManagementRepositoryDao {
	 void addCar(Vehicle vehicle);
	 void removeCar(Vehicle vehicle_id);
	List<Vehicle> listAvailableCars();
	List<Vehicle> listRentedCars() ;
	Vehicle findCarById(int carID)throws CarNotFoundException;
	

}





