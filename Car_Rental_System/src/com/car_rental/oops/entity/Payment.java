package com.car_rental.oops.entity;
import java.util.*;

public class Payment {
	private int paymentid;
	
	private int  lease_id;
	 private Date  paymentdate ;
	
	 private double amount;
	 Payment(int paymentid,int lease_id,Date paymentdate,double amount ){
		 this.amount=amount;
		 this.lease_id=lease_id;
		 this.paymentid=paymentid;
		 this.paymentdate=paymentdate;
	 }
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public int getLease_id() {
		return lease_id;
	}
	public void setLease_id(int lease_id) {
		this.lease_id = lease_id;
	}
	public Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	 

}
