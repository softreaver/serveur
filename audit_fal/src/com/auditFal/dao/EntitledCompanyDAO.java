package com.auditFal.dao;

import java.sql.Connection;

import com.auditFal.beans.EntitledCompany;

public abstract class EntitledCompanyDAO {

	public abstract Long create(Connection connection, EntitledCompany entitledCompany) throws DAOException;
	public abstract EntitledCompany find(Connection connection, EntitledCompany entitledCompany) throws DAOException;
	public abstract EntitledCompany findById(Connection connection, Long id) throws DAOException;
	public abstract EntitledCompany findByLowerName(Connection connection, String lowerName) throws DAOException;
	public abstract void update(Connection connection, EntitledCompany entitledCompany) throws DAOException;
}
