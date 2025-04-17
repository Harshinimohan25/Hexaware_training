package com.car_rental.oops.entity;

public class Customer {
	private int customer_id ;
	 private String firstname; 
	 private String lastname; 
	 private String email ;
	 private String phonenumber;
	 public Customer() {
	    }
	 
public Customer( int customer_id,String firstname, String  lastname ,String email ,String phonenumber ){
	this.customer_id=customer_id;
	this.firstname=firstname;
	this.lastname=lastname;
	this.email=email;
	this.phonenumber=phonenumber;
}

public int getCustomer_id() {
	return customer_id;
}

public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhonenumber() {
	return phonenumber;
}

public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
@Override
public String toString() {
    return "Customer{" +
           "ID=" + customer_id +
           ", First Name='" + firstname + '\'' +
           ", Last Name='" + lastname + '\'' +
           ", Email='" + email + '\'' +
           ", Phone='" + phonenumber + '\'' +
           '}';
}


}
