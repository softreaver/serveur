package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Activity;

public abstract class ActivityDAO {
	
	public abstract Long create(Connection connection, Activity activity) throws DAOException;
	
	public abstract Activity find(Connection connection, Activity activity) throws DAOException;
	
	public abstract Activity findById(Connection connection, Long id) throws DAOException;
	
	public abstract Activity findByLowerName(Connection connection, String lowerName) throws DAOException;
	
	public abstract ArrayList<Activity> getAll(Connection connection) throws DAOException;
	
	public abstract void update(Connection connection, Activity activity) throws DAOException;
}
