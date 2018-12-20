package com.iton.vaadinmongosample;

public class Address {
	private long empId;
	
private String street,city,state,addressId;;
public long getEmpId() {
	return empId;
}
public void setEmpId(long empId) {
	this.empId = empId;
}
public String getAddressId() {
	return addressId;
}
public void setAddressId(String addressId) {
	this.addressId = addressId;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

}
