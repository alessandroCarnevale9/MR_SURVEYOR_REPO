package model.dao;

import java.sql.SQLException;

import model.bean.Address;
import model.bean.RegisteredEndUser;

public interface EndUserDAO {
	
	void addEndUser(RegisteredEndUser endUser) throws SQLException;
	void setEndUser(RegisteredEndUser endUser) throws SQLException;
	boolean exists(RegisteredEndUser endUser) throws SQLException;
	boolean checkEndUser(String userEmail, String userPassword) throws SQLException;
	RegisteredEndUser getRegisteredEndUser(String email) throws SQLException;
	void setAddress(int enduserID, Address address) throws SQLException;
}
