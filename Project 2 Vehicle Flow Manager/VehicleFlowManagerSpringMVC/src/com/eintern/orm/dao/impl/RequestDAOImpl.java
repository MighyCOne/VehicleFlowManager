package com.eintern.orm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.eintern.orm.dao.RequestDAO;

public class RequestDAOImpl<T> implements RequestDAO<T>{
	
	SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<T> retrieveRequestsByStatus(Class<T> c, String status){
		try {
			Criteria criteria = getSessionFactory()
					.getCurrentSession()
					.createCriteria(c)
					.add(Restrictions.like("status", status)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);;
			List<T> data = criteria.list();

			return data;
		} catch (HibernateException he) {
			he.printStackTrace();
			return null;
		}
	}
	@Override
	public List<T> retrieveVehiclesNotAtLocation(Class<T> c, int locId){
		
		try {
			//Add also cannot be a Lot
			Criteria criteria = getSessionFactory()
					.getCurrentSession()
					.createCriteria(c)
					.add(Restrictions.not(Restrictions.like("location.locId",locId))).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);;
			List<T> data = criteria.list();

			return data;
		} catch (HibernateException he) {
			he.printStackTrace();
			System.out.println("Hi");
			return null;
		}
		
	}
	//NEEDS TESTING. DO NOT USE UNTIL I KNOW IT WORKS
	/*public List<T> retrieveListOfWhateverFromDataBase(Class<T> c, String sqlFieldName,T t){
		try {
			Criteria criteria = getSessionFactory()
					.getCurrentSession()
					.createCriteria(c)
					.add(Restrictions.like(sqlFieldName,t));
			List<T> data = criteria.list();

			return data;
		} catch (HibernateException he) {
			he.printStackTrace();
			return null;
		}
		
	}*/
}
