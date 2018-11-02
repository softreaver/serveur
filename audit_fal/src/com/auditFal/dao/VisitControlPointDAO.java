package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.VisitControlPoint;

public abstract class VisitControlPointDAO {

    public abstract void create(Connection connection, VisitControlPoint visitControlPoint, Long id_visits)
	    throws DAOException;

    public abstract void update(Connection connection, VisitControlPoint visitControlPoint, Long id_visits)
	    throws DAOException;

    public abstract ArrayList<VisitControlPoint> findByVisitId(Connection connection, Long visitId) throws DAOException;

    public abstract void deleteByVisitNumber(Connection connection, Long id_visits) throws DAOException;

    public abstract void delete(Connection connection, VisitControlPoint visitControlPoint, Long visitNumber)
	    throws DAOException;
}
