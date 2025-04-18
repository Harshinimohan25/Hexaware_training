package com.car_rental.oops.test;

import com.car_rental.oops.dao.ICarLeaseManagementDaoImpl;
import com.car_rental.oops.exception.LeaseNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeasenotfoundTest {

    private ICarLeaseManagementDaoImpl leaseDao;

    @BeforeEach
    public void setUp() {
        leaseDao = new ICarLeaseManagementDaoImpl();
    }

    @Test
    public void testGetLeaseById_throwsLeaseNotFoundException_whenLeaseIdNotFound() {
        int invalidLeaseId = 999999;

        LeaseNotFoundException exception = assertThrows(LeaseNotFoundException.class, () -> {
            leaseDao.getLeaseById(invalidLeaseId);
        });

        assertEquals("Lease ID " + invalidLeaseId + " not found.", exception.getMessage());
    }

    @Test
    public void testGetLeaseByIdAndCarId_throwsLeaseNotFoundException_whenLeaseIdOrCarIdNotFound() {
        int invalidLeaseId = 999999;
        int invalidCarId = 888888;

        LeaseNotFoundException exception = assertThrows(LeaseNotFoundException.class, () -> {
            leaseDao.getLeaseById(invalidLeaseId, invalidCarId);
        });

        assertEquals("Lease not found for ID: " + invalidLeaseId + " and carId: " + invalidCarId, exception.getMessage());
    }
}
