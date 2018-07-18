package com.auditFal.dao;

import java.sql.Connection;

import com.auditFal.beans.Entity;

public abstract class EntityDAO {
	public abstract Long create(Connection connection, Entity entity) throws DAOException;
	public abstract Entity find(Connection connection, Entity entity) throws DAOException;
	public abstract Entity findById(Connection connection, Long id) throws DAOException;
	public abstract Entity findByLowerName(Connection connection, String lowerName) throws DAOException;
	public abstract void update(Connection connection, Entity entity) throws DAOException;
}
