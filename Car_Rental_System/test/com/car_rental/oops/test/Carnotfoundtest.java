package com.car_rental.oops.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.car_rental.oops.dao.ICarManagementRepositoryDaoImpl;
import com.car_rental.oops.exception.CarNotFoundException;

public class Carnotfoundtest {

    private ICarManagementRepositoryDaoImpl carDao;

    @BeforeEach
    public void setup() {
        carDao = new ICarManagementRepositoryDaoImpl();
    }

    @Test
    public void testFindCarByIdThrowsExceptionForInvalidId() {
        int invalidCarId = -9999; 
        assertThrows(CarNotFoundException.class, () -> {
            carDao.findCarById(invalidCarId);
        });
    }
}
