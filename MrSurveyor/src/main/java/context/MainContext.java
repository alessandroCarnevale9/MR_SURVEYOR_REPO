package context;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {

	private static Logger logger = Logger.getLogger("MyLog");
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		logger.info("Startup web application");
	
		ServletContext servletContext = sce.getServletContext();
		
		DataSource ds = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/MrSurveyorDB");
			
			try {
				// testo se riesco a connettermi al DB
				Connection testConnection = null;
				
				try {
				testConnection = ds.getConnection();
				
				// Stampo il nome del DBMS al quale mi sto connettendo accedendo ai metadati
				DatabaseMetaData metaData = (DatabaseMetaData)testConnection.getMetaData();
				
				logger.info("DBMS name: "+metaData.getDatabaseProductName());
				logger.info("DBMS version: "+metaData.getDatabaseProductVersion());
				
				} finally {
					if(testConnection != null)
						testConnection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		servletContext.setAttribute("DataSource", ds); /* Qualsiasi servlet, accedendo
		al contesto e leggendo l'attributo DataSource, riceverà il DataSource */
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Shutdown web application");
	}
}
