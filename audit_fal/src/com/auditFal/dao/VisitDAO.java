package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Visit;

public abstract class VisitDAO {
    public abstract Long create(Connection connection, Visit visit) throws DAOException;

    public abstract ArrayList<Visit> getAll(Connection connection) throws DAOException;

    public abstract void update(Connection connection, Visit visit) throws DAOException;

    public abstract ArrayList<Visit> findByDate(Connection connection, Long fromDate, Long toDate) throws DAOException;
}
