package com.auditFal.dao;

import java.sql.Connection;

import com.auditFal.beans.Action;
import com.auditFal.beans.VisitControlPoint;

public abstract class ActionDAO {
    public abstract void create(Connection connection, Action action, Long controlPointId, Long visitId)
	    throws DAOException;

    public abstract void update(Connection connection, Action action, Long controlPointId, Long visitId)
	    throws DAOException;

    public abstract Action find(Connection connection, VisitControlPoint visitControlPoint) throws DAOException;

    public abstract void delete(Connection connection, Action action, Long controlPointId, Long visitId)
	    throws DAOException;
}
