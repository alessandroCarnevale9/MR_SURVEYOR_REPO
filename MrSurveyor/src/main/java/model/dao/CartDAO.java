package model.dao;

import java.sql.SQLException;

import model.bean.Cart;

public interface CartDAO {
	void addProducts(Cart cart, int enduserID) throws SQLException;
	Cart retrieveCartProducts(int enduserID) throws SQLException;
	void deleteProducts(int enduserID) throws SQLException;
}
