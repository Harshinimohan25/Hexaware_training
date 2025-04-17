package com.car_rental.oops.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.car_rental.oops.exception.CustomerNotFoundException;
import com.car_rental.oops.entity.Customer;
import com.car_rental.oops.util.DatabaseConnection;

public class ICustomermanagementRepositoryDaoImpl implements ICustomerManagementRepositoryDao {

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, firstname, lastname, email, phonenumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connect = DatabaseConnection.getConnection();
             PreparedStatement pmt = connect.prepareStatement(sql)) {

            pmt.setInt(1, customer.getCustomer_id());
            pmt.setString(2, customer.getFirstname());
            pmt.setString(3, customer.getLastname());
            pmt.setString(4, customer.getEmail());
            pmt.setString(5, customer.getPhonenumber());

            pmt.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    @Override
    public void removeCustomer(int customerID) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, customerID);
            int rows = psmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Customer removed successfully.");
            } else {
                System.out.println("No customer found with ID: " + customerID);
            }
        } catch (SQLException e) {
            System.err.println("Error removing customer: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhonenumber(rs.getString("phonenumber"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            System.err.println("Error listing customers: " + e.getMessage());
        }

        return customers;
    }

    @Override
    public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, customerID);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhonenumber(rs.getString("phonenumber"));
                return customer;
            } else {
                throw new CustomerNotFoundException("Customer with ID " + customerID + " not found.");
            }

        } catch (SQLException e) {
            throw new CustomerNotFoundException("Database error while finding customer: " + e.getMessage());
        }
        }
    }
    
