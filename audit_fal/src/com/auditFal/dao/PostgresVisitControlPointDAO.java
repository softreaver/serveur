package com.auditFal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.VisitControlPoint;

public class PostgresVisitControlPointDAO extends VisitControlPointDAO {

    // @formatter:off
    private static final String SQL_CREATE 	= "INSERT INTO visits_controlpoints VALUES (?, ?, ?, ?, ?, ?)"; /* id_visits | id_controlpoints | id_entities | conformity | newriskfactor (not used yet) | commentary */
    private static final String SQL_FIND	= "SELECT * FROM visits_controlpoints WHERE id_visits = ?, id_controlpoints = ?, id_entities = ?";
    private static final String SQL_UPDATE	= "UPDATE visits_controlpoints SET id WHERE id_visits = ?, id_controlpoints = ?, id_entities = ?";
    private static final String SQL_DELETE	= "DELETE FROM visits_controlpoints WHERE id_visits = ?, id_controlpoints = ?, id_entities = ?";
    // @formatter:on

    @Override
    public ArrayList<VisitControlPoint> find(Connection connection, VisitControlPoint visitControlPoint, Long visitId)
	    throws DAOException {
	ArrayList<VisitControlPoint> visitControlPoints = new ArrayList<>();

	PreparedStatement preparedStatement = null;
	ResultSet result = null;

	try {
	    Long id_controlpoints = visitControlPoint.getIdControlPoints();
	    Long id_entities = visitControlPoint.getIdEntities();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_FIND, false, visitId, id_controlpoints,
		    id_entities);
	    result = preparedStatement.executeQuery();
	    while (result.next())
		visitControlPoints.add(VisitControlPoint.parseResultSet(result));

	    return visitControlPoints;
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeResultSet(result);
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public void create(Connection connection, VisitControlPoint visitControlPoint, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    Long id_controlPoint = visitControlPoint.getIdControlPoints();
	    Long id_entities = visitControlPoint.getIdEntities();
	    Integer conformity = visitControlPoint.getConformity();
	    String commentary = visitControlPoint.getCommentary();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_CREATE, false, visitId, id_controlPoint,
		    id_entities, conformity, /* newRiskFactor not use yet */null, commentary);
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
    public void update(Connection connection, VisitControlPoint visitControlPoint, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    Long id_controlPoint = visitControlPoint.getIdControlPoints();
	    Long id_entities = visitControlPoint.getIdEntities();
	    Integer conformity = visitControlPoint.getConformity();
	    String commentary = visitControlPoint.getCommentary();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_UPDATE, false, conformity, null,
		    commentary, visitId, id_controlPoint, id_entities);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la modification du point de controle");
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

    @Override
    public void delete(Connection connection, VisitControlPoint visitControlPoint, Long visitId) throws DAOException {
	PreparedStatement preparedStatement = null;

	try {
	    Long id_controlPoint = visitControlPoint.getIdControlPoints();
	    Long id_entities = visitControlPoint.getIdEntities();

	    preparedStatement = DAOUtils.initPreparedStatement(connection, SQL_DELETE, false, visitId, id_controlPoint,
		    id_entities);
	    int sqlStatus = preparedStatement.executeUpdate();

	    if (sqlStatus == 0)
		throw new DAOException("Échec de la suppression du point de controle");
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DAOUtils.closeStatement(preparedStatement);
	}
    }

}
