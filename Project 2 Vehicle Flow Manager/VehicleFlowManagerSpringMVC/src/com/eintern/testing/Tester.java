package com.eintern.testing;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.eintern.orm.dao.HibernateGenericDAO;
import com.eintern.orm.entity.Credentials;
import com.eintern.orm.entity.Employee;
import com.eintern.orm.entity.Location;
import com.eintern.orm.entity.Vehicle;

public class Tester implements TesterInterface{

	public static void dropTables() {
		// Wont work without the hibernate.config?
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.createSQLQuery("DROP TABLE P2CREDENTIALS").executeUpdate();
		session.createSQLQuery("DROP TABLE P2EMPLOYEE").executeUpdate();
		session.createSQLQuery("DROP TABLE P2LOCATION").executeUpdate();
		session.createSQLQuery("DROP TABLE P2REQUEST").executeUpdate();
		session.createSQLQuery("DROP TABLE P2VEHICLE").executeUpdate();
	}
	@Override
	public void aopTest(){
		System.out.println("Aop Body.");
	}

	public void insertW1(HibernateGenericDAO genDaoImpl) {
		Location location1 = new Location();

		Credentials cred1 = new Credentials();
		cred1.setUsername("a");
		cred1.setPassword("123");

		Credentials cred2 = new Credentials();
		cred2.setUsername("b");
		cred2.setPassword("321");

		Employee employee1 = new Employee();
		employee1.setEmpName("Anderson");
		employee1.setEmpType("Warehouse Manager");
		employee1.setLocation(null);
		employee1.setCredentials(cred1);
		employee1.setEmpEmail("a@gmail.com");
		employee1.setLocation(location1);

		Employee employee2 = new Employee();
		employee2.setEmpName("Ben");
		employee2.setEmpType("Warehouse Employee");
		employee2.setLocation(null);
		employee2.setCredentials(cred2);
		employee2.setEmpEmail("b@gmail.com");
		employee2.setLocation(location1);

		/*
		 * Employee employee3= new Employee(); employee3.setEmpName("Cally");
		 * employee3.setEmpType("Lot Employee"); employee3.setLocation(null);
		 * employee3.setCredentials(cred3);
		 */

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setLocation(null);
		vehicle1.setRequests(null);
		vehicle1.setVehicleName("v1");
		vehicle1.setLocation(location1);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setLocation(null);
		vehicle2.setRequests(null);
		vehicle2.setVehicleName("v2");
		vehicle2.setLocation(location1);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setLocation(null);
		vehicle3.setRequests(null);
		vehicle3.setVehicleName("v3");
		vehicle3.setLocation(location1);

		Vehicle vehicle4 = new Vehicle();
		vehicle4.setLocation(null);
		vehicle4.setRequests(null);
		vehicle4.setVehicleName("v4");
		vehicle4.setLocation(location1);

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee1);
		employees.add(employee2);

		Set<Vehicle> vehicles = new HashSet<Vehicle>();
		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		vehicles.add(vehicle3);
		vehicles.add(vehicle4);


		location1.setEmployees(employees);
		location1.setLocName("Reston, VA");
		location1.setLocType("Warehouse");
		// location1.setRequests(null);
		location1.setVehicles(vehicles);

		// location.setEmployees(employees);
		genDaoImpl.insert(location1);

	}

	public void insertW2(HibernateGenericDAO genDaoImpl) {
		Location location1 = new Location();

		Credentials cred1 = new Credentials();
		cred1.setUsername("c");
		cred1.setPassword("123");

		Credentials cred2 = new Credentials();
		cred2.setUsername("d");
		cred2.setPassword("321");

		/*
		 * Credentials cred3= new Credentials(); cred.setUsername("c");
		 * cred.setPassword("abc");
		 */

		Employee employee1 = new Employee();
		employee1.setEmpName("Coran");
		employee1.setEmpType("Warehouse Manager");
		employee1.setLocation(null);
		employee1.setCredentials(cred1);
		employee1.setEmpEmail("cjrcoley@yahoo.com");
		employee1.setLocation(location1);

		Employee employee2 = new Employee();
		employee2.setEmpName("Danny");
		employee2.setEmpType("Warehouse Employee");
		employee2.setLocation(null);
		employee2.setCredentials(cred2);
		employee2.setEmpEmail("dannyluong293@utexas.edu");
		employee2.setLocation(location1);

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setLocation(null);
		vehicle1.setRequests(null);
		vehicle1.setVehicleName("v5");
		vehicle1.setLocation(location1);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setLocation(null);
		vehicle2.setRequests(null);
		vehicle2.setVehicleName("v6");
		vehicle2.setLocation(location1);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setLocation(null);
		vehicle3.setRequests(null);
		vehicle3.setVehicleName("v7");
		vehicle3.setLocation(location1);

		Vehicle vehicle4 = new Vehicle();
		vehicle4.setLocation(null);
		vehicle4.setRequests(null);
		vehicle4.setVehicleName("v8");
		vehicle4.setLocation(location1);

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee1);
		employees.add(employee2);

		Set<Vehicle> vehicles = new HashSet<Vehicle>();
		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		vehicles.add(vehicle3);
		vehicles.add(vehicle4);

		
		location1.setEmployees(employees);
		location1.setLocName("Dallas, TX");
		location1.setLocType("Warehouse");
		// location1.setRequests(null);
		location1.setVehicles(vehicles);

		// location.setEmployees(employees);
		genDaoImpl.insert(location1);

	}

	public void insertL1(HibernateGenericDAO genDaoImpl) {

		Location location1 = new Location();

		Credentials cred1 = new Credentials();
		cred1.setUsername("k");
		cred1.setPassword("123");

		Credentials cred2 = new Credentials();
		cred2.setUsername("f");
		cred2.setPassword("321");

		Employee employee1 = new Employee();
		employee1.setEmpName("Keyun");
		employee1.setEmpType("Lot Employee");
		employee1.setLocation(null);
		employee1.setCredentials(cred1);
		employee1.setEmpEmail("keyundick@gmail.com");
		employee1.setLocation(location1);

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setLocation(null);
		vehicle1.setRequests(null);
		vehicle1.setVehicleName("v8");
		vehicle1.setLocation(location1);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setLocation(null);
		vehicle2.setRequests(null);
		vehicle2.setVehicleName("v9");
		vehicle2.setLocation(location1);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setLocation(null);
		vehicle3.setRequests(null);
		vehicle3.setVehicleName("v10");
		vehicle3.setLocation(location1);

		Vehicle vehicle4 = new Vehicle();
		vehicle4.setLocation(null);
		vehicle4.setRequests(null);
		vehicle4.setVehicleName("v11");
		vehicle4.setLocation(location1);

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee1);

		Set<Vehicle> vehicles = new HashSet<Vehicle>();
		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		vehicles.add(vehicle3);
		vehicles.add(vehicle4);

		location1.setEmployees(employees);
		location1.setLocName("Raleigh, NC");
		location1.setLocType("Lot");
		// location1.setRequests(null);
		location1.setVehicles(vehicles);

		// location.setEmployees(employees);
		genDaoImpl.insert(location1);

	}
}
