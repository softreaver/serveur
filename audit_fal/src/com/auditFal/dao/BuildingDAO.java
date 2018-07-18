package com.auditFal.dao;

import java.sql.Connection;

import com.auditFal.beans.Building;


public abstract class BuildingDAO {
	public abstract Long create(Connection connection, Building building) throws DAOException;
	public abstract Building find(Connection connection, Building building) throws DAOException;
	public abstract Building findById(Connection connection, Long id) throws DAOException;
	public abstract Building findByLowerName(Connection connection, String lowerName) throws DAOException;
	public abstract void update(Connection connection, Building building) throws DAOException;
}
