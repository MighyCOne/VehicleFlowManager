package com.eintern.orm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Table(name="P2Request")
@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reqId;
	@NotEmpty
	private String status;
	private String req_date;
	private String last_update_date;
	private int shipping_location_id;
	@ManyToOne
	@JoinColumn(name = "locId")
	private Location location;
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public int getShipping_location_id() {
		return shipping_location_id;
	}
	public void setShipping_location_id(int shipping_location_id) {
		this.shipping_location_id = shipping_location_id;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String toString(){
		
		return "{\"reqId\":"+"\""+getReqId()+"\""+",\"shipping_location_id\":"+"\""+getShipping_location_id()+"\""+",\"current_location_id\":"+"\""+location.getLocId()+"\""+"}";
		
	}
	
}
