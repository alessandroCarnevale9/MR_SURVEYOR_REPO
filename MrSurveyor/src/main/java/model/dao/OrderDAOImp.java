package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.bean.Order;
import model.bean.Order.State;
import model.bean.Product;
import model.bean.RegisteredEndUser;

public class OrderDAOImp implements OrderDAO {

	private static final String USER_ORDER_TABLE = "user_order";
	private static final String ORDER_PRD_TABLE = "order_product";
	
	private static final String INSERT_USR_ORDER = "INSERT INTO "+USER_ORDER_TABLE+" (order_date, total_price, order_state, end_user_id, order_address) "
			+ "VALUES (?, ?, ?, ?, ?);";
	
	private static final String INSERT_ORD_PRD = "INSERT INTO "+ORDER_PRD_TABLE+" (order_product_name, order_id, order_product_image_path, order_product_price, order_product_quantity) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
	private static final String SET_ORDER_MANAGER = "UPDATE "+ USER_ORDER_TABLE+" SET manager_username = ? WHERE order_id = ?";
	
	private static final String RETRIEVE_ORDERS_END_USER = "SELECT * FROM "+USER_ORDER_TABLE+" WHERE end_user_id = ?";
	
	private static final String RETRIEVE_ORDER_PRODUCTS = "SELECT * FROM "+ORDER_PRD_TABLE+" WHERE order_id = ?";
	
	private static final String RETRIEVE_ORDERS_ORDER_MANAGER = "SELECT * FROM "+USER_ORDER_TABLE+" WHERE manager_username = ?";
	
	private static final String UPDATE_MANAGED_ORDER = "UPDATE "+USER_ORDER_TABLE+" SET shipment_date = ?,courier_name = ?,tracking_number = ?,order_state = 'sent',manager_username = NULL WHERE order_id = ?";
	
	public OrderDAOImp(DataSource ds) {
		OrderDAOImp.ds = ds;
	}
	
	@Override
	public int addEndUserOrder(Order enduserOrder, RegisteredEndUser enduser) throws SQLException {
		
		int generatedOrderId = 0;
		
		if (enduserOrder != null && enduser != null) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet generatedKeys = null;

		    try {
		        connection = ds.getConnection();
		        preparedStatement = connection.prepareStatement(INSERT_USR_ORDER, Statement.RETURN_GENERATED_KEYS);

		        preparedStatement.setDate(1, new java.sql.Date(enduserOrder.getOrderDate().getTime()));
		        preparedStatement.setDouble(2, enduserOrder.getTotalPrice());
		        preparedStatement.setString(3, "to_send");
		        preparedStatement.setInt(4, (int) enduser.getId());
		        preparedStatement.setString(5, enduserOrder.getOrderAddress());
		        
		        int affectedRows = preparedStatement.executeUpdate();

		        if (affectedRows == 0) {
		            throw new SQLException("L'inserimento non è riuscito, nessuna riga è stata modificata.");
		        }

		        generatedKeys = preparedStatement.getGeneratedKeys();
		        
		        if (generatedKeys.next()) {
		            generatedOrderId = generatedKeys.getInt(1); // Ottieni l'ID generato

		        } else {
		            throw new SQLException("L'ID generato non è disponibile.");
		        }
		    } finally {
		        try {
		            if (generatedKeys != null) {
		                generatedKeys.close();
		            }
		        } finally {
		            try {
		                if (preparedStatement != null) {
		                    preparedStatement.close();
		                }
		            } finally {
		                if (connection != null) {
		                    connection.close();
		                }
		            }
		        }
		    }
		}
		
		return generatedOrderId;
	}

	@Override
	public void addOrderProducts(Order enduserOrder) throws SQLException {
		
		Collection<Product> orderProducts = enduserOrder.getOrderProducts();
		
		if(orderProducts != null && !orderProducts.isEmpty()) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				
				connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(INSERT_ORD_PRD);
				
				for(Product p : orderProducts) {
					preparedStatement.setString(1, p.getName());
					preparedStatement.setInt(2, (int)enduserOrder.getId());
					preparedStatement.setString(3, p.getImagePath());
					preparedStatement.setDouble(4, p.getPrice());
					preparedStatement.setInt(5, p.getQuantity());
					
					preparedStatement.executeUpdate();
				}
				
				connection.commit();
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
	}

	@Override
	public void assingOrder(String orderManagerName, int toManageID) throws SQLException {
		if(orderManagerName != null && !orderManagerName.trim().equals("") && toManageID > 0) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(SET_ORDER_MANAGER);
				
				preparedStatement.setString(1, orderManagerName);
				preparedStatement.setInt(2, toManageID);
				
				preparedStatement.executeUpdate();
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
	}
	
	private Collection<Product> retriveOrderProducts(int orderID) throws SQLException {
		
		Collection<Product> products = new LinkedList<Product>();
		
		if(orderID > 0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(RETRIEVE_ORDER_PRODUCTS);
				
				preparedStatement.setInt(1, orderID);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				
				while(rs.next()) {
					Product p = new Product();
					
					p.setName(rs.getString("order_product_name"));
					p.setImagePath(rs.getString("order_product_image_path"));
					p.setPrice(rs.getDouble("order_product_price"));
					p.setQuantity(rs.getInt("order_product_quantity"));
					
					products.add(p);
				}
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
		return products;
	}
	
	@Override
	public Collection<Order> getOrders(int endUserID) throws SQLException {
	
		Collection<Order> orders = new LinkedList<Order>();
		if(endUserID > 0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(RETRIEVE_ORDERS_END_USER);
				preparedStatement.setInt(1, endUserID);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Order order = new Order();
					
					int orderID = rs.getInt("order_id");
					
					order.setId(orderID);
					order.setOrderDate(rs.getDate("order_date"));
					order.setShipmentDate(rs.getDate("shipment_date"));
					order.setTotalPrice(rs.getDouble("total_price"));
					order.setCourierName(rs.getString("courier_name"));
					order.setTrackingNumber(rs.getString("tracking_number"));
					order.setOrderAddress(rs.getString("order_address"));
					
					String orderState = rs.getString("order_state");
					if(orderState.equals("to_send"))
						order.setState(State.TO_SEND);
					else
						order.setState(State.SENT);
					
					Collection<Product> orderProducts = this.retriveOrderProducts(orderID);
					for(Product p : orderProducts)
						order.addOrderProduct(p);
					
					orders.add(order);
				}
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
		
		return orders;
	}
	
	@Override
	public Collection<Order> getOrdersToManage(String orderManagerName) throws SQLException {
		
		Collection<Order> orders = new LinkedList<Order>();
		
		if(orderManagerName != null && !orderManagerName.trim().equals("")) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(RETRIEVE_ORDERS_ORDER_MANAGER);
				preparedStatement.setString(1, orderManagerName);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Order order = new Order();
					
					order.setId(rs.getInt("order_id"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setShipmentDate(rs.getDate("shipment_date"));
					order.setTotalPrice(rs.getDouble("total_price"));
					order.setCourierName(rs.getString("courier_name"));
					order.setTrackingNumber(rs.getString("tracking_number"));
					order.setOrderAddress(rs.getString("order_address"));
					
					String orderState = rs.getString("order_state");
					if(orderState.equals("to_send"))
						order.setState(State.TO_SEND);
					else
						order.setState(State.SENT);
					
					RegisteredEndUser enduser = new RegisteredEndUser();
					enduser.setId(rs.getInt("end_user_id"));
					order.setEndUser(enduser);
					
					orders.add(order);
				}
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
		
		return orders;
	}
	
	@Override
	public void updateManagedOrder(Order order) throws SQLException {
		
		if(order != null) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(UPDATE_MANAGED_ORDER);
				
				preparedStatement.setDate(1, new java.sql.Date(order.getShipmentDate().getTime()));
				preparedStatement.setString(2, order.getCourierName());
				preparedStatement.setString(3, order.getTrackingNumber());
				preparedStatement.setInt(4, (int)order.getId());
				
				preparedStatement.executeUpdate();
				
			} finally {
				try {
					if(preparedStatement != null)
						preparedStatement.close();
				} finally {
					if(connection != null)
						connection.close();
				}
			}
		} else {
			System.out.println("EHHH");
		}
	}

	private static DataSource ds;
}
