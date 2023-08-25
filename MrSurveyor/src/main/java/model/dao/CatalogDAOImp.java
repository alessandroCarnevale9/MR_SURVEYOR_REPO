package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.bean.Category;

public class CatalogDAOImp implements CatalogDAO {
	
	private static final String CATEGORY_TABLE = "category";
	
	private static final String RETRIEVE_ALL = "SELECT * FROM " + CATEGORY_TABLE;

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
	
	private static DataSource ds;
}
