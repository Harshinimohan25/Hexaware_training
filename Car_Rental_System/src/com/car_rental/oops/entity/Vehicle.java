package com.car_rental.oops.entity;

public class Vehicle {
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private double dailyrate;
	private String status;
	private int  passengercapacity;
	private int enginecapacity;
	
public Vehicle(  ) {
	super();
	this.vehicle_id=vehicle_id;
	this.make=make;
	this.model=model;
	this.year=year;
	this.dailyrate=dailyrate;
	this.status=status;
	this.passengercapacity=passengercapacity;
	this.enginecapacity=enginecapacity;
}
//getters and setters
public int getVehicle_id() {
	return vehicle_id;
}

public void setVehicle_id(int vehicle_id) {
	this.vehicle_id = vehicle_id;
}

public String getMake() {
	return make;
}

public void setMake(String make) {
	this.make = make;
}

public String getModel() {
	return model;
}

public void setModel(String model) {
	this.model = model;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}

public double getDailyrate() {
	return dailyrate;
}

public void setDailyrate(double dailyrate) {
	this.dailyrate = dailyrate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public int getPassengercapacity() {
	return passengercapacity;
}

public void setPassengercapacity(int passengercapacity) {
	this.passengercapacity = passengercapacity;
}

public int getEnginecapacity() {
	return enginecapacity;
}

public void setEnginecapacity(int enginecapacity) {
	this.enginecapacity = enginecapacity;
}
@Override
public String toString() {
    return "Vehicle ID: " + vehicle_id +
           ", Make: " + make +
           ", Model: " + model +
           ", Year: " + year +
           ", Daily Rate: " + dailyrate +
           ", Status: " + status +
           ", Passenger Capacity: " + passengercapacity +
           ", Engine Capacity: " + enginecapacity;
}

}


//
//vehicleID (Primary Key)
//• make
//• model
//• year
//• dailyRate
//• status (available, notAvailable)
//• passengerCapacity
//• engineCapacity 