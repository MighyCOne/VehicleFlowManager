package com.eintern.orm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.eintern.orm.dao.AuthenticateUserDAO;

//@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AuthenticateUserDAOImpl<T> implements AuthenticateUserDAO<T> {

	//cannot inject because seesionFactory isnt a bean
	SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public boolean authenticateUser(String theusername, String thepassword, Class <T> c) {
		Criteria criteria= getSessionFactory()
				.getCurrentSession()
				.createCriteria(c)
				.add(Restrictions.eq("username", theusername))
				.add(Restrictions.eq("password", thepassword));
						
		List data = criteria.list();

		System.out.println(data);

		if (data.size() == 0)
			return false;
		else
			return true;
	}
}
