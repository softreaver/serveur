package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.Entity;

public class PostgresEntityDAO extends EntityDAO {
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM entities WHERE name = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM entities WHERE id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM entities";

    @Override
    public Entity find(Connection connection, Entity entity) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_NAME, false, entity.getName());
	    result = preparedStatement.executeQuery();
	    if (result.next())
		return Entity.parseResultSet(result);
	    else
		return null;
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public Entity findById(Connection connection, Long id) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_ID, false, id);
	    result = preparedStatement.executeQuery();
	    if (result.next())
		return Entity.parseResultSet(result);
	    else
		return null;
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public ArrayList<Entity> getAll(Connection connection) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;
	ArrayList<Entity> entities = new ArrayList<>();

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_GET_ALL, false);
	    result = preparedStatement.executeQuery();

	    while (result.next())
		entities.add(Entity.parseResultSet(result));

	    return entities;
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }
}
