package com.eintern.orm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "P2Credentials")
@Entity
public class Credentials {

	@NotEmpty
	@Id
	private String username;
	
	@Size(min=3)
	@NotEmpty
	private String password;
	
	@OneToOne(mappedBy="credentials",cascade=CascadeType.ALL)
	private Employee employee;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
