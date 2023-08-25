package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.bean.Category;
import model.bean.Product;

public interface CatalogDAO {
	
	Collection<Category> retrieveAllCategories() throws SQLException;
	Collection<Product> retrieveProductsByCategory(String categoryName) throws SQLException;
}
