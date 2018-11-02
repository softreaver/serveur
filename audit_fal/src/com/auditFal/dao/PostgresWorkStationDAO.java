package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.WorkStation;

public class PostgresWorkStationDAO extends WorkStationDAO {

    // @formatter:off
	private static final String SQL_CREATE 				= "INSERT INTO workstations (name, lowerName, active) VALUES (?, ?, ?)";
	private static final String SQL_FIND_BY_LOWER_NAME 		= "SELECT * FROM workstations WHERE lowername = ?";
	private static final String SQL_UPDATE 				= "UPDATE workstations SET name = ?, lowerName = ?, active = ? WHERE id = ?";
	private static final String SQL_FIND_BY_ID 			= "SELECT * FROM workstations WHERE id = ?";
	private static final String SQL_GET_ALL				= "SELECT * FROM workstations";
	// @formatter:on

    @Override
    public Long create(Connection connection, WorkStation workStation) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet autoGeneratedKey = null;

	try {
	    String name = workStation.getName();
	    String lowerName = workStation.getLowerName();
	    Boolean active = workStation.getActive();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_CREATE, true, name, lowerName, active);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la création de l'item, aucune ligne ajoutée dans la table.");

	    /* Retrieve the autoGenerated key after insertion */
	    autoGeneratedKey = preparedStatement.getGeneratedKeys();
	    if (autoGeneratedKey.next())
		return autoGeneratedKey.getLong(1);
	    else
		throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(autoGeneratedKey);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public WorkStation find(Connection connection, WorkStation workStation) throws DAOException {
	return findByLowerName(connection, workStation.getLowerName());
    }

    @Override
    public WorkStation findById(Connection connection, Long id) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_ID, false, id);
	    result = preparedStatement.executeQuery();
	    if (result.next())
		return WorkStation.parseResultSet(result);
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
    public WorkStation findByLowerName(Connection connection, String lowerName) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_LOWER_NAME, false, lowerName);
	    result = preparedStatement.executeQuery();
	    if (result.next())
		return WorkStation.parseResultSet(result);
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
    public void update(Connection connection, WorkStation workStation) throws DAOException {
	PreparedStatement preparedStatement = null;
	try {
	    Long id = workStation.getId();
	    String name = workStation.getName();
	    String lowerName = workStation.getLowerName();
	    Boolean active = workStation.getActive();
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_UPDATE, false, name, lowerName, active,
		    id);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la mise à jour de l'item, aucune ligne modifiée dans la table.");

	} catch (Exception e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public ArrayList<WorkStation> getAll(Connection connection) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;
	ArrayList<WorkStation> workStations = new ArrayList<>();

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_GET_ALL, false);
	    result = preparedStatement.executeQuery();

	    while (result.next())
		workStations.add(WorkStation.parseResultSet(result));

	    return workStations;
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

}
