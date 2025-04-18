package com.car_rental.oops.test;

import com.car_rental.oops.dao.ICarManagementRepositoryDaoImpl;
import com.car_rental.oops.entity.Vehicle;
import com.car_rental.oops.exception.CarNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Addcartest {

    @Test
    void testAddCar() {
        ICarManagementRepositoryDaoImpl dao = new ICarManagementRepositoryDaoImpl();

        Vehicle newVehicle = new Vehicle();
        newVehicle.setVehicle_id(1001); 
        newVehicle.setMake("Toyota");
        newVehicle.setModel("Corolla");
        newVehicle.setYear(2020);
        newVehicle.setDailyrate(50.0);
        newVehicle.setStatus("available");
        newVehicle.setPassengercapacity(5);
        newVehicle.setEnginecapacity(1800);

        dao.addCar(newVehicle);

        try {
            Vehicle retrievedVehicle = dao.findCarById(1001);
            assertNotNull(retrievedVehicle);
            assertEquals(newVehicle.getVehicle_id(), retrievedVehicle.getVehicle_id());
            assertEquals(newVehicle.getMake(), retrievedVehicle.getMake());
            assertEquals(newVehicle.getModel(), retrievedVehicle.getModel());
            assertEquals(newVehicle.getYear(), retrievedVehicle.getYear());
            assertEquals(newVehicle.getDailyrate(), retrievedVehicle.getDailyrate());
            assertEquals(newVehicle.getStatus(), retrievedVehicle.getStatus());
            assertEquals(newVehicle.getPassengercapacity(), retrievedVehicle.getPassengercapacity());
            assertEquals(newVehicle.getEnginecapacity(), retrievedVehicle.getEnginecapacity());
        } catch (CarNotFoundException e) {
            fail("Car not found after adding to the database");
        }
    }
}
