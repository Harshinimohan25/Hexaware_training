package com.car_rental.oops.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.exception.LeaseNotFoundException;
import com.car_rental.oops.util.DatabaseConnection;
import com.car_rental.oops.dao.ICarLeaseManagementDao;
import com.car_rental.oops.dao.ICarLeaseManagementDaoImpl;

public class LeaseTest {

	private ICarLeaseManagementDao leaseDao;

	@BeforeEach
	public void setUp() {
		leaseDao = new ICarLeaseManagementDaoImpl(); 
	}

	@Test
	
	
	public void testCreateLease_Success() throws LeaseNotFoundException {
	    int customerId = 3001;
	    int carId = 2001;
	    Date startDate = Date.valueOf(LocalDate.now());
	    Date endDate = Date.valueOf(LocalDate.now().plusDays(7));
	    String leaseType = "dailylease";

	    Lease createdLease = leaseDao.createLease(customerId, carId, startDate, endDate, leaseType);
	    assertNotNull(createdLease);

	    Lease retrievedLease = leaseDao.getLeaseById(createdLease.getLease_id());
	    assertNotNull(retrievedLease);
	    assertEquals(carId, retrievedLease.getVehicle_id());
	    assertEquals(customerId, retrievedLease.getCustomer_id());
	    assertEquals(leaseType, retrievedLease.getType()); // ✅ This will now pass
	}

	@Test
	public void testDatabaseConnection() {
		assertDoesNotThrow(() -> {
			try (Connection conn = DatabaseConnection.getConnection()) {
				assertNotNull(conn);
				System.out.println("Database connection established successfully.");
			}
		});
	}
}
