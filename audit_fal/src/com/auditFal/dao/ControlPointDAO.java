package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.ControlPoint;

public abstract class ControlPointDAO {

    public abstract ArrayList<ControlPoint> getAll(Connection connection) throws DAOException;

}
