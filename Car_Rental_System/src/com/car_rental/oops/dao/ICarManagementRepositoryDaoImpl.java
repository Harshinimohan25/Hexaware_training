package com.car_rental.oops.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_rental.oops.entity.Vehicle;
import com.car_rental.oops.exception.CarNotFoundException;
import com.car_rental.oops.util.DatabaseConnection;

public class ICarManagementRepositoryDaoImpl implements ICarManagementRepositoryDao {

    public void addCar(Vehicle vehicle) {
        String sql = "INSERT INTO vehicle (vehicle_id, make, model, year, dailyrate, status, passengercapacity, enginecapacity) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connect = DatabaseConnection.getConnection();
             PreparedStatement psmt = connect.prepareStatement(sql)) {

            psmt.setInt(1, vehicle.getVehicle_id());
            psmt.setString(2, vehicle.getMake());
            psmt.setString(3, vehicle.getModel());
            psmt.setInt(4, vehicle.getYear());
            psmt.setDouble(5, vehicle.getDailyrate());
            psmt.setString(6, vehicle.getStatus());
            psmt.setInt(7, vehicle.getPassengercapacity());
            psmt.setDouble(8, vehicle.getEnginecapacity());
            psmt.executeUpdate();

            System.out.println("Vehicle added successfully.");

        } catch (SQLException e) {
            System.err.println("Failed to add vehicle: " + e.getMessage());
        }
    }

    public void removeCar(Vehicle vehicle) {
        String sql = "DELETE FROM vehicle WHERE vehicle_id = ?";
        try (Connection connect = DatabaseConnection.getConnection();
             PreparedStatement psmt = connect.prepareStatement(sql)) {

            psmt.setInt(1, vehicle.getVehicle_id());
            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Vehicle removed successfully.");
            } else {
                System.out.println("No vehicle found with ID: " + vehicle.getVehicle_id());
            }

        } catch (SQLException e) {
            System.err.println("Failed to remove vehicle: " + e.getMessage());
        }
    }


    @Override
    public List<Vehicle> listAvailableCars() {
        List<Vehicle> availableCars = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE status = 'available'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicle_id(rs.getInt("vehicle_id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setDailyrate(rs.getDouble("dailyrate"));
                vehicle.setStatus(rs.getString("status"));
                vehicle.setPassengercapacity(rs.getInt("passengercapacity"));
                vehicle.setEnginecapacity(rs.getInt("enginecapacity"));
                availableCars.add(vehicle);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching available cars: " + e.getMessage());
        }

        return availableCars;
    }

    @Override
    public List<Vehicle> listRentedCars() {
        List<Vehicle> rentedCars = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE status = 'rented'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicle_id(rs.getInt("vehicle_id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setDailyrate(rs.getDouble("dailyrate"));
                vehicle.setStatus(rs.getString("status"));
                vehicle.setPassengercapacity(rs.getInt("passengercapacity"));
                vehicle.setEnginecapacity(rs.getInt("enginecapacity"));
                rentedCars.add(vehicle);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching rented cars: " + e.getMessage());
        }

        return rentedCars;
    }

    @Override
    public Vehicle findCarById(int carId) throws CarNotFoundException{
        Vehicle vehicle = null;
        String sql = "SELECT * FROM vehicle WHERE vehicle_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicle_id(rs.getInt("vehicle_id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setDailyrate(rs.getDouble("dailyrate"));
                vehicle.setStatus(rs.getString("status"));
                vehicle.setPassengercapacity(rs.getInt("passengercapacity"));
                vehicle.setEnginecapacity(rs.getInt("enginecapacity"));
            } else {
                throw new CarNotFoundException("Car ID " + vehicle + " not found.");
            }

        } catch (SQLException e) {
            System.err.println("Error finding vehicle: " + e.getMessage());
        }

        return vehicle;
    }

	

	

	

	

	
}
