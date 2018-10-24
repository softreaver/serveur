package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.VisitControlPoint;

public abstract class VisitControlPointDAO {

    public abstract void create(Connection connection, VisitControlPoint visitControlPoint, Long visitId)
	    throws DAOException;

    public abstract void update(Connection connection, VisitControlPoint visitControlPoint, Long visitId)
	    throws DAOException;

    public abstract ArrayList<VisitControlPoint> find(Connection connection, VisitControlPoint visitControlPoint,
	    Long visitId) throws DAOException;

    public abstract void delete(Connection connection, VisitControlPoint visitControlPoint, Long visitId)
	    throws DAOException;
}
