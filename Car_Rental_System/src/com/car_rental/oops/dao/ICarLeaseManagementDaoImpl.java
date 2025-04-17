package com.car_rental.oops.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.exception.LeaseNotFoundException;
import com.car_rental.oops.util.DatabaseConnection;

public class ICarLeaseManagementDaoImpl implements ICarLeaseManagementDao {

	@Override
	public Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate, String type) {
	    String sql = "INSERT INTO lease (vehicle_id, customer_id, startdate, enddate, type) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        psmt.setInt(1, vehicleID);
	        psmt.setInt(2, customerID);
	        psmt.setDate(3, startDate);
	        psmt.setDate(4, endDate);
	        psmt.setString(5, type);

	        psmt.executeUpdate();

	        ResultSet rs = psmt.getGeneratedKeys();
	        if (rs.next()) {
	            int leaseID = rs.getInt(1);

	            return new Lease(leaseID, vehicleID, customerID, startDate, endDate, type);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error creating lease: " + e.getMessage());
	    }

	    return null;
	}



    @Override
    public void returnCar(int leaseID) {
        String sql = "UPDATE lease SET type = 'returned' WHERE lease_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, leaseID);
            int rows = psmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Car returned successfully for Lease ID: " + leaseID);
            } else {
                System.out.println("Lease not found with ID: " + leaseID);
            }

        } catch (SQLException e) {
            System.err.println("Error returning car: " + e.getMessage());
        }
    }

    @Override
    public List<Lease> listActiveLeases() {
        List<Lease> leases = new ArrayList<>();
        String sql = "SELECT * FROM lease WHERE type IN ('dailylease', 'monthlylease')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                leases.add(mapLease(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error listing active leases: " + e.getMessage());
        }
        return leases;
    }

    @Override
    public List<Lease> listLeaseHistory() {
        List<Lease> leases = new ArrayList<>();
        String sql = "SELECT * FROM lease";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                leases.add(mapLease(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching lease history: " + e.getMessage());
        }
        return leases;
    }

    public Lease getLeaseById(int leaseID) throws LeaseNotFoundException {
        String sql = "SELECT * FROM lease WHERE lease_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, leaseID);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                return mapLease(rs);
            } else {
                throw new LeaseNotFoundException("Lease ID " + leaseID + " not found.");
            }

        } catch (SQLException e) {
            System.err.println("Error fetching lease by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Lease getLeaseById(int leaseID, int carId) throws LeaseNotFoundException {
        String sql = "SELECT * FROM lease WHERE lease_id = ? AND vehicle_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, leaseID);
            psmt.setInt(2, carId);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                return mapLease(rs);
            } else {
                throw new LeaseNotFoundException("Lease not found for ID: " + leaseID + " and carId: " + carId);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching lease by ID and carId: " + e.getMessage());
        }
        return null;
    }

    private Lease mapLease(ResultSet rs) throws SQLException {
        int leaseID = rs.getInt("lease_id");
        int vehicleID = rs.getInt("vehicle_id");
        int customerID = rs.getInt("customer_id");
        Date startDate = rs.getDate("startdate");
        Date endDate = rs.getDate("enddate");
        String type = rs.getString("type");

        return new Lease(leaseID, vehicleID, customerID, startDate, endDate, type); 
    }

}
