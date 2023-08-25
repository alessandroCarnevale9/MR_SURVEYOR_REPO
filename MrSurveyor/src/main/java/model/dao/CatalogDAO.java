package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.bean.Category;

public interface CatalogDAO {
	
	Collection<Category> retrieveAllCategories() throws SQLException;
}
