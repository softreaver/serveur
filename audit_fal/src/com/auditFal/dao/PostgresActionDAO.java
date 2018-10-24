package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.auditFal.beans.Action;
import com.auditFal.beans.VisitControlPoint;

public class PostgresActionDAO extends ActionDAO {

    // @formatter:off
    private static final String SQL_CREATE 	= "INSERT INTO actions VALUES (?, ?, ?, ?, ?, ?)"; /* todo | realclosingdate | wantedclosingdate | id_entities | id_controlpoints | id_visits */
    private static final String SQL_FIND	= "SELECT * FROM actions WHERE id_visits = ?, id_controlpoints = ?, id_entities = ?";
    private static final String SQL_UPDATE	= "UPDATE actions SET id WHERE id_visits = ?, id_controlpoints = ?, id_entities = ?";
    private static final String SQL_DELETE	= "DELETE FROM actions WHERE id_visits = ?, id_controlpoints = ?, id_entities = ?";
    // @formatter:on

    @Override
    public void create(Connection connection, Action action, Long controlPointId, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    String todo = action.getToDo();
	    Integer realclosingdate = action.getRealClosingDate();
	    Integer wantedclosingdate = action.getWantedClosingDate();
	    Long id_entities = action.getIdResponsableEntity();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_CREATE, false, todo, realclosingdate,
		    wantedclosingdate, id_entities, controlPointId, visitId);

	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException(
			"Échec de la création du point de controle, aucune ligne ajoutée dans la table.");
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public void update(Connection connection, Action action, Long controlPointId, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    String todo = action.getToDo();
	    Integer realclosingdate = action.getRealClosingDate();
	    Integer wantedclosingdate = action.getWantedClosingDate();
	    Long id_entities = action.getIdResponsableEntity();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_CREATE, false, todo, realclosingdate,
		    wantedclosingdate, id_entities, controlPointId, visitId);

	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException(
			"Échec de la création du point de controle, aucune ligne ajoutée dans la table.");
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public Action find(Connection connection, VisitControlPoint visitControlPoint) throws DAOException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void delete(Connection connection, Action action, Long controlPointId, Long visitId) throws DAOException {
	// TODO Auto-generated method stub

    }

}
