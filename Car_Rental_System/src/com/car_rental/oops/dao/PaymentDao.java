package com.car_rental.oops.dao;

import com.car_rental.oops.entity.Lease;

public interface PaymentDao {
	void recordPayment( Lease lease, int paymentId, double amount);
}
