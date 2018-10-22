package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Entity;

public abstract class EntityDAO {
    public abstract Entity find(Connection connection, Entity entity) throws DAOException;

    public abstract Entity findById(Connection connection, Long id) throws DAOException;

    public abstract ArrayList<Entity> getAll(Connection connection) throws DAOException;
}
