package com.car_rental.oops.test;

import com.car_rental.oops.dao.ICarLeaseManagementDaoImpl;
import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.util.DatabaseConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateLeaseTest {

    private static ICarLeaseManagementDaoImpl leaseDao;

    @BeforeAll
    public static void setUp() {
        leaseDao = new ICarLeaseManagementDaoImpl();
    }

    @Test
    @Order(1)
    public void testCreateLease_Success() {
        int customerID = 1;
        int vehicleID = 2;
        Date startDate = Date.valueOf("2025-04-17");
        Date endDate = Date.valueOf("2025-04-20");
        String type = "dailylease";

        Lease lease = leaseDao.createLease(customerID, vehicleID, startDate, endDate, type);

        assertNotNull(lease, "Lease should not be null after creation");
        assertEquals(customerID, lease.getCustomer_id());
        assertEquals(vehicleID, lease.getVehicle_id());
        assertEquals(startDate, lease.getStartdate());
        assertEquals(endDate, lease.getEnddate());
        assertEquals(type, lease.getType());
    }

    @AfterEach
    public void cleanup() {
        String deleteSql = "DELETE FROM lease WHERE startdate = ? AND enddate = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteSql)) {

            stmt.setDate(1, Date.valueOf("2025-04-17"));
            stmt.setDate(2, Date.valueOf("2025-04-20"));
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Cleanup failed: " + e.getMessage());
        }
    }
}
