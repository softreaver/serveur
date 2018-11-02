package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.WorkSituation;

public class PostgresWorkSituationDAO extends WorkSituationDAO {

    private static final String SQL_GET_ALL = "SELECT * FROM WorkSituations";

    @Override
    public ArrayList<WorkSituation> getAll(Connection connection) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;
	ArrayList<WorkSituation> workSituations = new ArrayList<>();

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_GET_ALL, false);
	    result = preparedStatement.executeQuery();

	    while (result.next())
		workSituations.add(WorkSituation.parseResultSet(result));

	    return workSituations;
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

}
