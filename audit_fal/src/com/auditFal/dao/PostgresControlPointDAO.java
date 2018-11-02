package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.ControlPoint;

public class PostgresControlPointDAO extends ControlPointDAO {

    private static final String SQL_GET_ALL = "SELECT * FROM controlpoints";

    @Override
    public ArrayList<ControlPoint> getAll(Connection connection) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;
	ArrayList<ControlPoint> controlPoints = new ArrayList<>();

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_GET_ALL, false);
	    result = preparedStatement.executeQuery();

	    while (result.next())
		controlPoints.add(ControlPoint.parseResultSet(result));

	    return controlPoints;
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }
}
