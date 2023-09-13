package model.dao;

import java.sql.SQLException;

import model.bean.Manager;
import model.bean.Order;
import model.bean.RegisteredEndUser;

public interface OrderDAO {
	int addEndUserOrder(Order enduserOrder, RegisteredEndUser enduser) throws SQLException;
	void addOrderProducts(Order enduserOrder) throws SQLException;
	void assingOrder(Order toManage, Manager orderManager) throws SQLException; // fare una SELECT per gli order manager
}
