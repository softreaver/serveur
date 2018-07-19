package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auditFal.beans.Entity;

public class PostgresEntityDAO extends EntityDAO {
	private static final String SQL_FIND_BY_LOWER_NAME = "SELECT * FROM entities WHERE lowername = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM entities WHERE id = ?";


	@Override
	public Entity find(Connection connection, Entity entity) throws DAOException {
		return this.findByLowerName(connection, entity.getLowerName());
	}
	
	@Override
	public Entity findById(Connection connection, Long id) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_ID, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				return Entity.parseResultSet(result);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			DAOUtils.closeResultSet(result);
			DAOUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Entity findByLowerName(Connection connection, String lowerName) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_LOWER_NAME, false, lowerName);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				return Entity.parseResultSet(result);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			DAOUtils.closeResultSet(result);
			DAOUtils.closeStatement(preparedStatement);
		}
	}

}
