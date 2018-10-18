package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Post;

public abstract class PostDAO {

    public abstract Long create(Connection connection, Post post) throws DAOException;

    public abstract Post find(Connection connection, Post post) throws DAOException;

    public abstract Post findById(Connection connection, Long id) throws DAOException;

    public abstract Post findByLowerName(Connection connection, String lowerName) throws DAOException;

    public abstract void update(Connection connection, Post post) throws DAOException;

    public abstract ArrayList<Post> getAll(Connection connection) throws DAOException;
}
