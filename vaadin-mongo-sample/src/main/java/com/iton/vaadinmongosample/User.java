package com.iton.vaadinmongosample;

import java.util.Date;

public class User {
	private String id;
public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
private String date;
public String getDate() {
	return date;
}
private long empId;
public long getEmpId() {
	return empId;
}

public void setEmpId(long empId) {
	this.empId = empId;
}

public void setDate(String string) {
	this.date = string;
}
private String name,salary;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSalary() {
	return salary;
}

public void setSalary(String salary) {
	this.salary = salary;
}

}
