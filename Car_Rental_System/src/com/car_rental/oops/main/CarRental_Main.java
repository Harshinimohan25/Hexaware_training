package com.car_rental.oops.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.car_rental.oops.dao.ICarLeaseManagementDao;
import com.car_rental.oops.dao.ICarLeaseManagementDaoImpl;
import com.car_rental.oops.dao.ICarManagementRepositoryDao;
import com.car_rental.oops.dao.ICarManagementRepositoryDaoImpl;
import com.car_rental.oops.dao.ICustomerManagementRepositoryDao;
import com.car_rental.oops.dao.ICustomermanagementRepositoryDaoImpl;
import com.car_rental.oops.dao.PaymentDaoImpl;
import com.car_rental.oops.entity.Customer;
import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.entity.Vehicle;
import com.car_rental.oops.exception.CarNotFoundException;
import com.car_rental.oops.exception.CustomerNotFoundException;

public class CarRental_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ICarManagementRepositoryDao carRepo = new ICarManagementRepositoryDaoImpl();
        ICustomerManagementRepositoryDao customerDao = new ICustomermanagementRepositoryDaoImpl();
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        ICarLeaseManagementDao leaseDao = new ICarLeaseManagementDaoImpl();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Car Rental Management System =====");
            System.out.println("1. Car Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Lease Management");
            System.out.println("4. Payment Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    carManagementMenu(sc, carRepo);
                    break;
                case 2:
                    customerManagementMenu(sc, customerDao);
                    break;
                case 3:
                    leaseManagementMenu(sc, leaseDao);
                    break;
                case 4:
                    paymentManagementMenu(sc, paymentDao);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting system. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }

    private static void carManagementMenu(Scanner sc, ICarManagementRepositoryDao carRepo) {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Car Management Menu -----");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. List Available Cars");
            System.out.println("4. List Rented Cars");
            System.out.println("5. Find Car By ID");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Vehicle newCar = new Vehicle();
                    System.out.print("Enter vehicle ID: ");
                    newCar.setVehicle_id(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter make: ");
                    newCar.setMake(sc.nextLine());
                    System.out.print("Enter model: ");
                    newCar.setModel(sc.nextLine());
                    System.out.print("Enter year: ");
                    newCar.setYear(sc.nextInt());
                    System.out.print("Enter daily rate: ");
                    newCar.setDailyrate(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Enter status (available/rented): ");
                    newCar.setStatus(sc.nextLine());
                    System.out.print("Enter passenger capacity: ");
                    newCar.setPassengercapacity(sc.nextInt());
                    System.out.print("Enter engine capacity: ");
                    newCar.setEnginecapacity(sc.nextInt());

                    carRepo.addCar(newCar);
                    break;

                case 2:
                    System.out.print("Enter vehicle ID to remove: ");
                    Vehicle removeCar = new Vehicle();
                    removeCar.setVehicle_id(sc.nextInt());
                    carRepo.removeCar(removeCar);
                    break;

                case 3:
                    List<Vehicle> availableCars = carRepo.listAvailableCars();
                    System.out.println("Available Cars:");
                    for (Vehicle v : availableCars) {
                        System.out.println(v);
                    }
                    break;

                case 4:
                    List<Vehicle> rentedCars = carRepo.listRentedCars();
                    System.out.println("Rented Cars:");
                    for (Vehicle v : rentedCars) {
                        System.out.println(v);
                    }
                    break;

                case 5:
                    System.out.print("Enter Car ID to search: ");
                    int carId = sc.nextInt();
                    try {
                        Vehicle foundCar = carRepo.findCarById(carId);
                        System.out.println("Car Found: " + foundCar);
                    } catch (CarNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void customerManagementMenu(Scanner sc, ICustomerManagementRepositoryDao customerDao) {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Customer Management Menu -----");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. List Customers");
            System.out.println("4. Find Customer By ID");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter First Name: ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    Customer newCustomer = new Customer(id, firstName, lastName, email, phone);
                    customerDao.addCustomer(newCustomer);
                    break;

                case 2:
                    System.out.print("Enter Customer ID to remove: ");
                    int removeId = sc.nextInt();
                    customerDao.removeCustomer(removeId);
                    break;

                case 3:
                    List<Customer> customers = customerDao.listCustomers();
                    System.out.println("Customer List:");
                    for (Customer c : customers) {
                        System.out.println(c);
                    }
                    break;

                case 4:
                    System.out.print("Enter Customer ID to find: ");
                    int searchId = sc.nextInt();
                    try {
                        Customer found = customerDao.findCustomerById(searchId);
                        System.out.println("Customer Found: " + found);
                    } catch (CustomerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void leaseManagementMenu(Scanner sc, ICarLeaseManagementDao leaseDao) {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== Car Lease Management Menu =====");
            System.out.println("1. Create Lease");
            System.out.println("2. Return Car");
            System.out.println("3. List Active Leases");
            System.out.println("4. Lease History");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
               
                    System.out.print("Enter Customer ID: ");
                    int customerId = sc.nextInt();
                    System.out.print("Enter Vehicle ID: ");
                    int vehicleId = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String startDateStr = sc.nextLine();
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    String endDateStr = sc.nextLine();

                    String type = "";
                    while (true) {
                        System.out.print("Enter Lease Type (dailylease/monthlylease): ");
                        type = sc.nextLine().trim().toLowerCase();
                        if (type.equals("dailylease") || type.equals("monthlylease")) {
                            break;
                        } else {
                            System.out.println("Invalid lease type! Please enter 'dailylease' or 'monthlylease'.");
                        }
                    }

                    try {
                        Date startDate = Date.valueOf(startDateStr);
                        Date endDate = Date.valueOf(endDateStr);

                        Lease newLease = leaseDao.createLease(customerId, vehicleId, startDate, endDate, type);
                        if (newLease != null) {
                            System.out.println("Lease created successfully: " + newLease);
                        } else {
                            System.out.println("Failed to create lease.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Lease ID to return: ");
                    int leaseId = sc.nextInt();
                    leaseDao.returnCar(leaseId);
                    break;

                case 3:
                    List<Lease> activeLeases = leaseDao.listActiveLeases();
                    System.out.println("----- Active Leases -----");
                    for (Lease lease : activeLeases) {
                        System.out.println(lease);
                    }
                    break;

                case 4:
                    List<Lease> leaseHistory = leaseDao.listLeaseHistory();
                    System.out.println("----- Lease History -----");
                    for (Lease lease : leaseHistory) {
                        System.out.println(lease);
                    }
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void paymentManagementMenu(Scanner sc, PaymentDaoImpl paymentDao) {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Payment Management Menu -----");
            System.out.println("1. Record Payment");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Payment ID: ");
                    int paymentId = sc.nextInt();
                    System.out.print("Enter Lease ID: ");
                    int leaseId = sc.nextInt();
                    System.out.print("Enter Payment Amount: ");
                    double amount = sc.nextDouble();

                    Lease lease = new Lease();
                    lease.setLease_id(leaseId);

                    paymentDao.recordPayment(lease, paymentId, amount);
                    break;

                case 2:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
