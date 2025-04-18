package com.car_rental.oops.test;

import com.car_rental.oops.dao.ICarLeaseManagementDaoImpl;
import com.car_rental.oops.entity.Lease;
import com.car_rental.oops.exception.LeaseNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RetriveleaseTest {

    private static ICarLeaseManagementDaoImpl leaseDao;

    @BeforeAll
    public static void setUp() {
        leaseDao = new ICarLeaseManagementDaoImpl();
    }

    @Test
    public void testGetLeaseById_Success() {
        
        int leaseID = 1;

        try {
            Lease lease = leaseDao.getLeaseById(leaseID);
            assertNotNull(lease, "Lease should not be null");
            assertEquals(leaseID, lease.getLease_id(), "Lease ID should match");
        } catch (LeaseNotFoundException e) {
            fail("Lease with ID " + leaseID + " should exist");
        }
    }

    @Test
    public void testGetLeaseById_Failure() {
        int leaseID = 999;

        assertThrows(LeaseNotFoundException.class, () -> {
            leaseDao.getLeaseById(leaseID);
        }, "LeaseNotFoundException should be thrown for non-existing Lease ID");
    }
}
