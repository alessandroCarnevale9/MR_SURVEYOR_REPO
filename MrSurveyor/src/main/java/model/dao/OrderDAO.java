package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.bean.Order;
import model.bean.RegisteredEndUser;

public interface OrderDAO {
	int addEndUserOrder(Order enduserOrder, RegisteredEndUser enduser) throws SQLException;
	void addOrderProducts(Order enduserOrder) throws SQLException;
	void assingOrder(String orderManagerName, int toManageID) throws SQLException;
	Collection<Order> getOrders(int endUserID) throws SQLException;
	Collection<Order> getOrdersToManage(String orderManagerName) throws SQLException;
	void updateManagedOrder(Order order) throws SQLException;
}
