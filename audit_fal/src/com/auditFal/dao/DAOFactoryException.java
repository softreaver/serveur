package com.auditFal.dao;

public class DAOFactoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/*
     * Constructors
     */
    public DAOFactoryException( String message ) {
        super( message );
    }

    public DAOFactoryException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOFactoryException( Throwable cause ) {
        super( cause );
    }
}
