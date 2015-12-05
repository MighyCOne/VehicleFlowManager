package com.eintern.orm.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="P2Location")
@Entity
public class Location implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int locId;
	private String locName;
	private String locType;
	//Don't want the vehicles to disappear if the location is deleted, so no cascade. Does this work?
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL)
	private Set<Vehicle> vehicles;
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Employee> employees;
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL)
	private Set<Request> requests;
	
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getLocType() {
		return locType;
	}
	public void setLocType(String locType) {
		this.locType = locType;
	}
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public Set<Request> getRequests() {
		return requests;
	}
	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}
	
}
