package com.auditFal.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DAOInitialization implements ServletContextListener {

	public static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory          daoFactory;

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        ServletContext servletContext = event.getServletContext();
        this.daoFactory = PostgresDAOFactory.getInstance();
        servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
    }
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
}
