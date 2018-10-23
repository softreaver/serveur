package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.VisitControlPoint;

public class PostgresVisitControlPointDAO extends VisitControlPointDAO {

    @Override
    public ArrayList<VisitControlPoint> getControlPointByVisitNumber(Connection connection, Long visitNumber)
	    throws DAOException {

	return null;
    }

}
