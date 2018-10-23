package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.VisitControlPoint;

public abstract class VisitControlPointDAO {
    public abstract ArrayList<VisitControlPoint> getControlPointByVisitNumber(Connection connection, Long visitNumber)
	    throws DAOException;
}
