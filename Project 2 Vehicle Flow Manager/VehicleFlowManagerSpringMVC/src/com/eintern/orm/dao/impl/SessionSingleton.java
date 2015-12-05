package com.eintern.orm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionSingleton {

	private static Session session = null;

	private SessionSingleton() {
		
		if (session != null) {
			System.out.println("\nObject already created...");
		}
	}

	public static Session getInstance() {
		
		if (session == null) {
			//System.out.println("Hi");
			SessionFactory factory = new Configuration().configure().buildSessionFactory();

			session = factory.openSession();
			//System.out.println("Bye");
		}
		return session;
	}
	
	public static boolean closeSession(){
		
		session.close();
		return true;
	}
}
