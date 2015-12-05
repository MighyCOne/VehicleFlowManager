package com.eintern.orm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.eintern.orm.dao.HibernateGenericDAO;

public class HibernateGenericDAOImpl<T> implements HibernateGenericDAO<T> {

	SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> retrieveAll(Class c) {
		try {
			Criteria criteria = 
					getSessionFactory()
					.getCurrentSession()
					.createCriteria(c).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List data=criteria.list();

			if (data.size() != 0) {
				for (int x = 0; x < data.size(); x++) {
					System.out.println(data.get(x));
				}

			} else {
				System.out.println("no data");
			}
			return data;
		} catch (HibernateException he) {
			he.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(T t) {
		try {
			getSessionFactory().getCurrentSession().save(t);
			return true;
		} catch (HibernateException he) {
			he.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(T t){
	try {
		getSessionFactory().getCurrentSession().update(t);
		return true;
	} catch (HibernateException he) {
		he.printStackTrace();
		return false;
	}
}

	@Override
	public boolean delete(Class<T> c, int entityId) {
		try {
			getSessionFactory().getCurrentSession().delete(retrieveById(c, entityId));
			return true;
		} catch (HibernateException he) {
			he.printStackTrace();
			return false;
		}
	}

	@Override
	public T retrieveById(Class<T> c, int entityId) {
		try {
			T t = (T) getSessionFactory().getCurrentSession().get(c, entityId);
			System.out.println(t);
			return t;
		} catch (HibernateException he) {
			he.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T retrieveByUsername(Class<T> c, String somename) {

		try {
			T t = (T) getSessionFactory().getCurrentSession().get(c, somename);
			return t;
		} catch (HibernateException he) {
			he.printStackTrace();
			return null;
		}

	}
}