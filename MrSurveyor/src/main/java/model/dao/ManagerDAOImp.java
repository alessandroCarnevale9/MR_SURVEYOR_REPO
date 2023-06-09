package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.bean.Manager;
import model.bean.Manager.Role;

public class ManagerDAOImp implements ManagerDAO {

	private static final String MANAGER_TABLE = "manager";
	
	private static final String SELECT_MANAGER = "SELECT manager_username"
			+ " FROM "+MANAGER_TABLE+
			" WHERE manager_username = ? AND manager_password = ? AND manager_role = ?;";
	
	public ManagerDAOImp(DataSource ds) {
		ManagerDAOImp.ds = ds;
	}
	
	@Override
	public boolean checkManager(Manager manager) throws SQLException {
		
		if(manager == null)
			return false;
		
		String managerUsername = manager.getUsername();
		String managerPassword = manager.getPassword();
		Manager.Role managerRole = manager.getRole();
		if(managerUsername == null || managerPassword == null || managerRole == null ||
				managerUsername.trim().equals("") || managerPassword.trim().equals(""))
			return false;
		
		String managerRoleStr = null;
		
		if(managerRole != Role.CATALOG_MANAGER && managerRole != Role.ORDER_MANAGER)
			return false;
		
		else if(managerRole.equals(Role.CATALOG_MANAGER))
			managerRoleStr = "catalog_manager";
		
		else
			managerRoleStr = "order_manager";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_MANAGER);
			preparedStatement.setString(1, managerUsername);
			preparedStatement.setString(2, managerPassword);
			preparedStatement.setString(3, managerRoleStr);
			
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next(); // false if there are no rows in the result set
		
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
	}

	private static DataSource ds;
}
