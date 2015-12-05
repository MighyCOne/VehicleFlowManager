package com.eintern.orm.dao;


import java.util.List;

public interface HibernateGenericDAO<T> {
	@SuppressWarnings("rawtypes")
	public List<T> retrieveAll(Class c);
	public boolean insert(T t);
	public boolean delete(Class<T> c, int entityId);
	public T retrieveById(Class<T> c, int entityId);
	public T retrieveByUsername(Class<T> c, String somename);
	boolean update(T t);
}