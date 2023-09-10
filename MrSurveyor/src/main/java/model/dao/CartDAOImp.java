package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import model.bean.Cart;
import model.bean.CartProduct;

public class CartDAOImp implements CartDAO {
	
	private static final String CART_PRODUCT_TABLE = "cart_product";
	
	private static final String INSERT_CART_PRODUCTS = "INSERT INTO "+CART_PRODUCT_TABLE+" (cart_product_id, cart_product_name, end_user_id, cart_product_image_path, cart_product_price, cart_product_quantity, cart_product_max_quantity) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String RETRIEVE_CART_PRODUCTS = "SELECT * FROM "+CART_PRODUCT_TABLE+" WHERE end_user_id = ?";
	
	private static final String DELETE_CART_PRODUCTS = "DELETE FROM "+CART_PRODUCT_TABLE+" WHERE end_user_id=?";
	
	public CartDAOImp(DataSource ds) {
		CartDAOImp.ds = ds;
	}
	
	@Override
	public void addProducts(Cart cart, int enduserID) throws SQLException {
		if(enduserID <= 0)
			return;
		
		Collection<CartProduct> cartProducts = cart.getProducts();
		
		if(cartProducts != null && !cartProducts.isEmpty()) {
			
			Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        
	        try {
	        	connection = ds.getConnection();
	        	
	        	connection.setAutoCommit(false);
	        	
	        	preparedStatement = connection.prepareStatement(INSERT_CART_PRODUCTS);
	        	
	        	for(CartProduct product : cartProducts) {
	        		preparedStatement.setInt(1, (int)product.getId());
	        		preparedStatement.setString(2, product.getName());
	                preparedStatement.setInt(3, enduserID);
	                preparedStatement.setString(4, product.getImagePath());
	                preparedStatement.setDouble(5, product.getPrice());
	                preparedStatement.setInt(6, product.getQuantity());
	                preparedStatement.setInt(7, product.getMaxQuantity());
	                
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
	public Cart retrieveCartProducts(int enduserID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<CartProduct> cartProducts = new ArrayList<CartProduct>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_CART_PRODUCTS);
			
			preparedStatement.setInt(1, enduserID);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				CartProduct cartProduct = new CartProduct(0);
				
				cartProduct.setId(rs.getLong("cart_product_id"));
				cartProduct.setName(rs.getString("cart_product_name"));
				cartProduct.setImagePath(rs.getString("cart_product_image_path"));
				cartProduct.setPrice(rs.getDouble("cart_product_price"));
				cartProduct.setQuantity(rs.getInt("cart_product_quantity"));
				cartProduct.setMaxQuantity(rs.getInt("cart_product_max_quantity"));
				
				cartProducts.add(cartProduct);
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
		
		return new Cart(cartProducts);
	}

	@Override
	public void deleteProducts(int enduserID) throws SQLException {
		
		if(enduserID <= 0)
			return;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
        	connection = ds.getConnection();
        	preparedStatement = connection.prepareStatement(DELETE_CART_PRODUCTS);
        	
        	preparedStatement.setInt(1, enduserID);
        	
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
	
	private static DataSource ds;
}
