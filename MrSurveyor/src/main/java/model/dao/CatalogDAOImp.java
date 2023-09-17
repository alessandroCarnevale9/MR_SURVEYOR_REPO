package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.bean.Category;
import model.bean.Manager;
import model.bean.Manager.Role;
import model.bean.Product;
import model.bean.Subcategory;

public class CatalogDAOImp implements CatalogDAO {

	private static final String CATEGORY_TABLE = "category";
	private static final String SUBCATEGORY_TABLE = "subcategory";
	private static final String PRODUCT_TABLE = "product";
	private static final String HAS_CATEGORY = "has_category_product";
	private static final String HAS_SUBCATEGORY = "has_subcategory_product";
	
	private static final String RETRIEVE_ALL = "SELECT * FROM " + CATEGORY_TABLE;
	
	private static final String RETRIEVE_BY_CATEGORY = "SELECT * "
			+ "FROM " + PRODUCT_TABLE +" AS P, " + HAS_CATEGORY +" AS C "
			+ "WHERE P.product_id = C.product_id AND C.category_name=?";
	
	private static final String RETRIEVE_SUBCATEGORY_BY_CATEGORY = "SELECT S.subcategory_name "
			+ "FROM "+SUBCATEGORY_TABLE+" AS S, "+HAS_SUBCATEGORY+" AS HS "
			+ "WHERE HS.subcategory_id=S.subcategory_id AND HS.product_id = ? AND S.category_name = ?";
	
	private static final String RETRIEVE_PRODUCT_BY_SUBCATEGORY_RELATED_TO_CATEGORY = "SELECT * "
			+ "FROM product AS P "
			+ "WHERE P.product_id "
			+ "IN (SELECT P.product_id "
			+ "FROM product AS P, subcategory AS S, has_subcategory_product AS HS "
			+ "WHERE P.product_id=HS.product_id AND HS.subcategory_id=S.subcategory_id AND S.subcategory_name=? AND S.category_name=?)";
	
	private static final String RETRIEVE_PRODUCT_BY_ID = "SELECT * FROM "+PRODUCT_TABLE+" WHERE product_id=?";
	
	private static final String RETRIEVE_FIRST_SUBCATEGORY = "SELECT * "
			+ "FROM subcategory AS S, has_subcategory_product AS HS "
			+ "WHERE S.subcategory_id=HS.subcategory_id AND HS.product_id=?";
	
	private static final String SEARCH_PRODUCT = "SELECT * FROM product WHERE product_name LIKE ?";
	
	private static final String RETRIEVE_ROOT_CATEGORY = "SELECT category_name FROM "+SUBCATEGORY_TABLE+" WHERE subcategory_name=?";
	
	private static final String RETRIVE_CATEGORY_BY_PRD_ID = "SELECT category_name FROM "+HAS_CATEGORY+" WHERE product_id=?";
	
	public CatalogDAOImp(DataSource ds) {
		CatalogDAOImp.ds = ds;
	}
	
	@Override
	public Collection<Category> retrieveAllCategories() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Category> categories = new LinkedList<Category>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_ALL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				Category categoryBean = new Category();
				
				categoryBean.setName(rs.getString("category_name"));
				categoryBean.setPathImage(rs.getString("image_path"));
				categoryBean.setDescription(rs.getString("category_description"));
				
				categories.add(categoryBean);
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
		
		return categories;
	}
	
	@Override
	public String retrieveSubcategoryByCategoryName(long productID, String categoryName) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String subcategory = "";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_SUBCATEGORY_BY_CATEGORY);
			preparedStatement.setLong(1, productID);
			preparedStatement.setString(2, categoryName);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
				subcategory = rs.getString("subcategory_name");
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		
		return subcategory;
	}
	
	@Override
	public Collection<Product> retrieveProductsByCategory(String categoryName) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<Product>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_BY_CATEGORY);
			preparedStatement.setString(1, categoryName);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Product productBean = new Product();
				
				productBean.setId(rs.getLong("product_id"));
				productBean.setImagePath(rs.getString("product_image_path"));
				productBean.setName(rs.getString("product_name"));
				productBean.setPrice(rs.getDouble("product_price"));
				productBean.setQuantity(rs.getInt("product_quantity"));
				productBean.setDescription(rs.getString("product_description"));
				productBean.setCatalogManager(new Manager(rs.getString("manager_username"), "", Role.CATALOG_MANAGER));
				
				products.add(productBean);
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
		
		return products;
	}

	@Override
	public Collection<Product> retrieveProductsBySubcategory(String subcategory, String rootCategory) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<Product>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_PRODUCT_BY_SUBCATEGORY_RELATED_TO_CATEGORY);
			
			preparedStatement.setString(1, subcategory);
			preparedStatement.setString(2, rootCategory);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Product productBean = new Product();
				
				productBean.setId(rs.getLong("product_id"));
				productBean.setImagePath(rs.getString("product_image_path"));
				productBean.setName(rs.getString("product_name"));
				productBean.setPrice(rs.getDouble("product_price"));
				productBean.setQuantity(rs.getInt("product_quantity"));
				productBean.setDescription(rs.getString("product_description"));
				productBean.setCatalogManager(new Manager(rs.getString("manager_username"), "", Role.CATALOG_MANAGER));
				
				products.add(productBean);
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
		
		return products;
	}
	
	@Override
	public Product retrieveProductById(int productId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Product product = new Product();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_PRODUCT_BY_ID);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				product.setId(rs.getLong("product_id"));
				product.setImagePath(rs.getString("product_image_path"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getDouble("product_price"));
				product.setQuantity(rs.getInt("product_quantity"));
				product.setDescription(rs.getString("product_description"));
				product.setCatalogManager(new Manager(rs.getString("manager_username"), "", Role.CATALOG_MANAGER));
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
		
		return product;
	}

	@Override
	public Subcategory retrieveFirstSubcategory(int productId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Subcategory subcategory = new Subcategory();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(RETRIEVE_FIRST_SUBCATEGORY);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				subcategory.setId(rs.getInt("subcategory_id"));
				subcategory.setName(rs.getString("subcategory_name"));
				subcategory.setDescription(rs.getString("subcategory_description"));
				subcategory.setRootCategory(new Category(rs.getString("category_name")));
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
		
		return subcategory;
	}

	@Override
	public Collection<Product> searchProducts(String param) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<Product>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(SEARCH_PRODUCT);
			
			param += "%";
			preparedStatement.setString(1, param);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Product productBean = new Product();
				
				productBean.setId(rs.getLong("product_id"));
				productBean.setImagePath(rs.getString("product_image_path"));
				productBean.setName(rs.getString("product_name"));
				productBean.setPrice(rs.getDouble("product_price"));
				productBean.setQuantity(rs.getInt("product_quantity"));
				productBean.setDescription(rs.getString("product_description"));
				productBean.setCatalogManager(new Manager(rs.getString("manager_username"), "", Role.CATALOG_MANAGER));
				
				products.add(productBean);
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
		
		return products;
	}

	@Override
	public Category getRootCategory(String subcategoryName) throws SQLException {
		
		Category category = new Category();
		
		if(subcategoryName != null && !subcategoryName.trim().equals("")) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(RETRIEVE_ROOT_CATEGORY);
				preparedStatement.setString(1, subcategoryName);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs.next())
					category.setName(rs.getString("category_name"));
			
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
		
		return category;
	}
	
	
	@Override
	public Category getCategoryById(int productId) throws SQLException {
		
		Category category = new Category();
		
		if(productId > 0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(RETRIVE_CATEGORY_BY_PRD_ID);
				preparedStatement.setInt(1, productId);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs.next())
					category.setName(rs.getString("category_name"));
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
		
		return category;
	}

	private static DataSource ds;
}
