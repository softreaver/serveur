package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auditFal.beans.Action;
import com.auditFal.beans.VisitControlPoint;

public class PostgresActionDAO extends ActionDAO {

    // @formatter:off
    private static final String SQL_CREATE 			= "INSERT INTO actions VALUES (?, ?, ?, ?, ?, ?)"; /* todo | realclosingdate | wantedclosingdate | id_entities | id_controlpoints | id_visits */
    private static final String SQL_FIND			= "SELECT * FROM actions WHERE id_visits = ? AND id_controlpoints = ?";
    private static final String SQL_UPDATE			= "UPDATE actions SET todo = ?, realclosingdate = ?, wantedclosingdate = ?, id_entities = ? WHERE id_visits = ? AND id_controlpoints = ?";
    private static final String SQL_DELETE			= "DELETE FROM actions WHERE id_visits = ? AND id_controlpoints = ?";
    private static final String SQL_DELETE_BY_VISIT_NUMBER	= "DELETE FROM actions WHERE id_visits = ?";
    // @formatter:on

    @Override
    public void create(Connection connection, Action action, Long controlPointId, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    String todo = action.getToDo();
	    Long realclosingdate = action.getRealClosingDate();
	    Long wantedclosingdate = action.getWantedClosingDate();
	    Long id_entities = action.getIdResponsableEntity();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_CREATE, false, todo, realclosingdate,
		    wantedclosingdate, id_entities, controlPointId, visitId);

	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la création de l'action, aucune ligne ajoutée dans la table.");

	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public void update(Connection connection, Action action, Long controlPointId, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    if (action == null) {
		preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_DELETE, false, visitId,
			controlPointId);
		int sqlStatus = preparedStatement.executeUpdate();

		if (sqlStatus == 0)
		    throw new DAOException("Échec de la suppression de l'action");
	    } else {
		String todo = action.getToDo();
		Long realclosingdate = action.getRealClosingDate();
		Long wantedclosingdate = action.getWantedClosingDate();
		Long id_entities = action.getIdResponsableEntity();

		preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_UPDATE, false, todo, realclosingdate,
			wantedclosingdate, id_entities, visitId, controlPointId);

		int sqlStatus = preparedStatement.executeUpdate();

		if (sqlStatus == 0)
		    try {
			create(connection, action, controlPointId, visitId);
		    } catch (Exception e) {
			throw new DAOException("Échec de la création de l'action, aucune ligne ajoutée dans la table.");
		    }
	    }
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public Action find(Connection connection, VisitControlPoint visitControlPoint) throws DAOException {
	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    Long id_visits = visitControlPoint.getIdVisits();
	    Long id_controlpoints = visitControlPoint.getIdControlPoints();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND, false, id_visits,
		    id_controlpoints);

	    result = preparedStatement.executeQuery();
	    if (result.next())
		return Action.parseResultSet(result);
	    else
		return null;

	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public void deleteByVisitNumber(Connection connection, Long visitNumber) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_DELETE_BY_VISIT_NUMBER, false,
		    visitNumber);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la suppression de l'action");
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public void delete(Connection connection, Long controlPointsId, Long visitNumber) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_DELETE, false, visitNumber,
		    controlPointsId);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la suppression de l'action");
	} catch (SQLException e) {
	    throw new DAOException(e.getMessage());
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }
}
