package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.bean.Category;
import model.bean.Product;
import model.bean.Subcategory;

public interface CatalogDAO {
	
	Collection<Category> retrieveAllCategories() throws SQLException;
	String retrieveSubcategoryByCategoryName(long productID, String categoryName) throws SQLException;
	Collection<Product> retrieveProductsByCategory(String categoryName) throws SQLException;
	Collection<Product> retrieveProductsBySubcategory(String subcategory, String rootCategory) throws SQLException;
	Product retrieveProductById(int productId) throws SQLException;
	Subcategory retrieveFirstSubcategory(int productId) throws SQLException;
}
