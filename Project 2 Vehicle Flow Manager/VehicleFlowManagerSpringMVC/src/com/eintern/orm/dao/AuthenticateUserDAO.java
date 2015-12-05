package com.eintern.orm.dao;

public interface AuthenticateUserDAO<T> {

	boolean authenticateUser(String theusername, String thepassword, Class<T> c);


}
