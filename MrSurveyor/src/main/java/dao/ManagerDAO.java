package dao;

import java.sql.SQLException;

import model.Manager;

public interface ManagerDAO {
	
	boolean checkManager(Manager manager) throws SQLException;
}
