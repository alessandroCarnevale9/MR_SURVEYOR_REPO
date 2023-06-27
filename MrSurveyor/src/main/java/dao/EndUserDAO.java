package dao;

import java.sql.SQLException;

import model.RegisteredEndUser;

public interface EndUserDAO {
	
	void addEndUser(RegisteredEndUser endUser) throws SQLException;
	boolean exists(RegisteredEndUser endUser) throws SQLException;
	boolean checkEndUser(String userEmail, String userPassword) throws SQLException;
}
