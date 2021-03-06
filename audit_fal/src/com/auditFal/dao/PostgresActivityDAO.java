package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.Activity;

public class PostgresActivityDAO extends ActivityDAO {

    private static final String SQL_CREATE = "INSERT INTO activities (name, lowerName, active) VALUES (?, ?, ?)";
    private static final String SQL_FIND_BY_LOWER_NAME = "SELECT * FROM activities WHERE lowername = ?";
    private static final String SQL_UPDATE = "UPDATE activities SET name = ?, lowerName = ?, active = ? WHERE id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM activities WHERE id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM activities";

    @Override
    public Long create(Connection connection, Activity activity) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet ResultSetAutoGeneratedKey = null;

	try {
	    String name = activity.getName();
	    String lowerName = activity.getLowerName();
	    Boolean active = activity.getActive();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_CREATE, true, name, lowerName, active);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la création de l'item, aucune ligne ajoutée dans la table.");

	    /* Retrieve the autoGenerated key after insertion */
	    ResultSetAutoGeneratedKey = preparedStatement.getGeneratedKeys();
	    if (ResultSetAutoGeneratedKey.next())
		return ResultSetAutoGeneratedKey.getLong(1);
	    else
		throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(ResultSetAutoGeneratedKey);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public Activity find(Connection connection, Activity activity) throws DAOException {
	return findByLowerName(connection, activity.getLowerName());
    }

    @Override
    public Activity findById(Connection connection, Long id) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_ID, false, id);
	    result = preparedStatement.executeQuery();
	    if (result.next())
		return Activity.parseResultSet(result);
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
    public Activity findByLowerName(Connection connection, String lowerName) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND_BY_LOWER_NAME, false, lowerName);
	    result = preparedStatement.executeQuery();
	    if (result.next())
		return Activity.parseResultSet(result);
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
    public void update(Connection connection, Activity activity) throws DAOException {
	PreparedStatement preparedStatement = null;
	try {
	    Long id = activity.getId();
	    String name = activity.getName();
	    String lowerName = activity.getLowerName();
	    Boolean active = activity.getActive();
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_UPDATE, false, name, lowerName, active,
		    id);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la mise à jour de l'item, aucune ligne modifiée dans la table.");

	} catch (Exception e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public ArrayList<Activity> getAll(Connection connection) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;
	ArrayList<Activity> activities = new ArrayList<>();

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_GET_ALL, false);
	    result = preparedStatement.executeQuery();

	    while (result.next())
		activities.add(Activity.parseResultSet(result));

	    return activities;
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }
}
