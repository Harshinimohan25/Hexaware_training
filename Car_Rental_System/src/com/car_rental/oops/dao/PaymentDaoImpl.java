package com.car_rental.oops.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.util.DatabaseConnection;

public class PaymentDaoImpl implements PaymentDao {

    public void recordPayment(Lease lease, int paymentId, double amount) {
        String sql = "INSERT INTO payment (paymentid, lease_id, paymentdate, amount) VALUES (?, ?, CURRENT_DATE, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, paymentId);             
            pstmt.setInt(2, lease.getLease_id());    
            pstmt.setDouble(3, amount);               

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Payment recorded successfully.");
            } else {
                System.out.println("Payment record failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error while recording payment: " + e.getMessage());
        }
    }

	
}
