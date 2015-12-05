package com.eintern.orm.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="P2Vehicle")
@Entity
public class Vehicle implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vehicleId;
	private String vehicleName;
	
	@ManyToOne
	@JoinColumn(name = "locId")
	private Location location;
	
	@OneToMany(mappedBy="vehicle",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Request> requests;
	
	public Vehicle() {
		vehicleId = 0;
		vehicleName = null;
		location = null;
		requests = null;
	}
	
	
	public Vehicle(int vehicleId, String vehicleName, Location location, Set<Request> requests) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.location = location;
		this.requests = requests;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Set<Request> getRequests() {
		return requests;
	}
	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}
	
	
}
