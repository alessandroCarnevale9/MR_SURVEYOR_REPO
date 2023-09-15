package model.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import model.bean.Manager;

public interface ManagerDAO {
	
	boolean checkManager(Manager manager) throws SQLException;
	Collection<String> getAssignedRoles(Manager manager) throws SQLException;
	List<String> getOrderManagerNames() throws SQLException;
}
