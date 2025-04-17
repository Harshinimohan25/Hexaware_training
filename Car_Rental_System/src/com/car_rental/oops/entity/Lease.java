package com.car_rental.oops.entity;
import java.util.*;

import java.sql.Date;

public class Lease {
    private int lease_id;
    private int vehicle_id;
    private int customer_id;
    private Date startdate;
    private Date enddate;
    private String type; 


    public Lease(int lease_id, int vehicle_id, int customer_id, Date startdate, Date enddate, String type) {
        this.lease_id = lease_id;
        this.vehicle_id = vehicle_id;
        this.customer_id = customer_id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.type = type;
    }

    public Lease() {
		// TODO Auto-generated constructor stub
	}

	public int getLease_id() {
        return lease_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public String getType() {
        return type;
    }


	@Override
	public String toString() {
	    return "Lease{" +
	            "lease_id=" + lease_id +
	            ", vehicle_id=" + vehicle_id +
	            ", customer_id=" + customer_id +
	            ", startdate=" + startdate +
	            ", enddate=" + enddate +
	            ", type=" + type +
	            '}';
	}

	public void setLease_id(int lease_id) {
		this.lease_id = lease_id;
	}

	

	


}
