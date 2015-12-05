package com.eintern.orm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.eintern.orm.dao.HibernateGenericDAO;

public class HibernateGenericDAOImpl<T> implements HibernateGenericDAO<T> {

	protected Session session;

	public HibernateGenericDAOImpl() {
		session = SessionSingleton.getInstance();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> retrieveAll(Class c) {
		try {
			Criteria criteria = session.createCriteria(c);
			List<T> data = criteria.list();
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
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException he) {
			he.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(T t){
	try {
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
		return true;
	} catch (HibernateException he) {
		he.printStackTrace();
		return false;
	}
}

	@Override
	public boolean delete(Class<T> c, int entityId) {
		try {
			session.beginTransaction();
			session.delete(retrieveById(c, entityId));
			session.getTransaction().commit();
			return true;
		} catch (HibernateException he) {
			he.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T retrieveById(Class<T> c, int entityId) {
		try {
			T t = (T) session.get(c, entityId);
			System.out.println(t);
			return t;
		} catch (HibernateException he) {
			he.printStackTrace();
			return null;
		}
	}
}