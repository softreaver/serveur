package com.auditFal.dao;

import java.sql.Connection;

import com.auditFal.beans.WorkStation;

public abstract class WorkStationDAO {
	
	public abstract Long create(Connection connection, WorkStation workStation) throws DAOException;
	public abstract WorkStation findById(Connection connection, Long id) throws DAOException;
}
