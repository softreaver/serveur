package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.WorkStation;

public abstract class WorkStationDAO {
	
	public abstract Long create(Connection connection, WorkStation workStation) throws DAOException;
	
	public abstract WorkStation find(Connection connection, WorkStation workStation) throws DAOException;
	
	public abstract WorkStation findById(Connection connection, Long id) throws DAOException;
	
	public abstract WorkStation findByLowerName(Connection connection, String lowerName) throws DAOException;
	
	public abstract ArrayList<WorkStation> getAll(Connection connection) throws DAOException;
	
	public abstract void update(Connection connection, WorkStation workStation) throws DAOException;
}
