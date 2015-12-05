package com.eintern.service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.eintern.orm.dao.HibernateGenericDAO;
import com.eintern.orm.dao.impl.HibernateGenericDAOImpl;
import com.eintern.orm.entity.Vehicle;

//Creating a web service: 1.create a New Web Service Project from Oracle web server (in other). 2. create a package with a java class? inside 3. create a method and use the 2 annotations, make the method return what the client will get, and run the program in your weblogic server
@WebService
public class VehicleInventoryWs {

	@WebMethod
	public ArrayList<Vehicle> storageUnitService() {
		HibernateGenericDAO<Vehicle> dao = new HibernateGenericDAOImpl<Vehicle>();
		ArrayList<Vehicle> list =(ArrayList<Vehicle>) dao.retrieveAll(Vehicle.class);

		return list;
	}
}
