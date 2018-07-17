package com.auditFal.dao;

public class DAOConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/*
     * constructors
     */
    public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
