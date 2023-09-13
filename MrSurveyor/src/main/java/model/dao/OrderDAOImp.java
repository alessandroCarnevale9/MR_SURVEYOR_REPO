package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.sql.DataSource;

import model.bean.Manager;
import model.bean.Order;
import model.bean.Product;
import model.bean.RegisteredEndUser;

public class OrderDAOImp implements OrderDAO {

	private static final String USER_ORDER_TABLE = "user_order";
	private static final String ORDER_PRD_TABLE = "order_product";
	
	private static final String INSERT_USR_ORDER = "INSERT INTO "+USER_ORDER_TABLE+" (order_date, total_price, order_state, end_user_id) "
			+ "VALUES (?, ?, ?, ?);";
	
	private static final String INSERT_ORD_PRD = "INSERT INTO "+ORDER_PRD_TABLE+" (order_product_name, order_id, order_product_image_path, order_product_price, order_product_quantity) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
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
	public void assingOrder(Order toManage, Manager orderManager) throws SQLException {

	}
	
	private static DataSource ds;
}
