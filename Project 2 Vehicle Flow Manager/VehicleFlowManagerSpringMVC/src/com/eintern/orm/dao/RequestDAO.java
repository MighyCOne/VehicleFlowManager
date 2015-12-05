package com.eintern.orm.dao;

import java.util.List;

public interface RequestDAO<T> {

	List<T> retrieveRequestsByStatus(Class<T> c, String status);

	List<T> retrieveVehiclesNotAtLocation(Class<T> c, int locId);

}
