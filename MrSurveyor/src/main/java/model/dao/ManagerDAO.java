package model.dao;

import java.sql.SQLException;

import model.bean.Manager;

public interface ManagerDAO {
	
	boolean checkManager(Manager manager) throws SQLException;
}
