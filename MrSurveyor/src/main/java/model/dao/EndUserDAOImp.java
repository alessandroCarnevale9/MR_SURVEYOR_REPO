package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.bean.Address;
import model.bean.RegisteredEndUser;

public class EndUserDAOImp implements EndUserDAO {

	private static final String ENDUSER_TABLE = "end_user";

	private static final String CREATE_ENDUSER = "INSERT INTO " + ENDUSER_TABLE
			+ " (end_user_name, end_user_surname, end_user_email, end_user_password,"
			+ " end_user_region, end_user_province, end_user_city, end_user_street,"
			+ " end_user_cap, end_user_house_number)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_EMAIL = "SELECT * " + "FROM " + ENDUSER_TABLE
			+ " WHERE end_user_email = ?;";
	
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT end_user_id " + "FROM " + ENDUSER_TABLE
			+ " WHERE end_user_email = ? AND end_user_password = ?;";
	
	private static final String UPDATE_ENDUSER = "UPDATE "+ENDUSER_TABLE+" SET "+
	"end_user_name = ?,"
	+ "end_user_surname = ?,"
	+ "end_user_password = ? "
	+ "WHERE end_user_id = ?";
	
	private static final String UPDATE_ADDRESS = "UPDATE "+ENDUSER_TABLE+" SET "
			+ "end_user_region = ?,"
			+ "end_user_province = ?,"
			+ "end_user_city = ?,"
			+ "end_user_street = ?,"
			+ "end_user_cap = ?,"
			+ "end_user_house_number = ?"
			+ " WHERE end_user_id = ?";
	
	public EndUserDAOImp(DataSource ds) {
		EndUserDAOImp.ds = ds;
	}

	@Override
	public void addEndUser(RegisteredEndUser endUser) throws SQLException {

		if(endUser == null)
			return;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(CREATE_ENDUSER);
			preparedStatement.setString(1, endUser.getName());
			preparedStatement.setString(2, endUser.getSurname());
			preparedStatement.setString(3, endUser.getEmail());
			preparedStatement.setString(4, endUser.getPassword());
			preparedStatement.setString(5, endUser.getAddress().getRegion());
			preparedStatement.setString(6, endUser.getAddress().getProvince());
			preparedStatement.setString(7, endUser.getAddress().getCity());
			preparedStatement.setString(8, endUser.getAddress().getStreet());
			preparedStatement.setString(9, endUser.getAddress().getCap());
			preparedStatement.setInt(10, endUser.getAddress().getHouseNumber());

			preparedStatement.executeUpdate();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public void setEndUser(RegisteredEndUser endUser) throws SQLException {
		
		if(endUser != null) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(UPDATE_ENDUSER);
				
				preparedStatement.setString(1, endUser.getName());
				preparedStatement.setString(2, endUser.getSurname());
				preparedStatement.setString(3, endUser.getPassword());
				preparedStatement.setInt(4, (int)endUser.getId());
				
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

	@Override
	public boolean exists(RegisteredEndUser endUser) throws SQLException {

		if (endUser == null)
			return false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
			preparedStatement.setString(1, endUser.getEmail());

			ResultSet rs = preparedStatement.executeQuery();
			return rs.next(); // false if there are no rows in the result set
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public boolean checkEndUser(String userEmail, String userPassword) throws SQLException {
		
		if(userEmail == null || userPassword == null || userEmail.trim().equals("") ||
				userPassword.trim().equals(""))
			return false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
			preparedStatement.setString(1, userEmail);
			preparedStatement.setString(2, userPassword);
			
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next(); // false if there are no rows in the result set
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally {
				if(connection != null)
					connection.close();
			}
		}
	}

	@Override
	public RegisteredEndUser getRegisteredEndUser(String email) throws SQLException {
		
		RegisteredEndUser endUser = new RegisteredEndUser();
		
		if (email != null) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
				preparedStatement.setString(1, email);

				ResultSet rs = preparedStatement.executeQuery();
				Address address = new Address();
				if(rs.next()) {
					
					endUser.setId(rs.getInt("end_user_id"));
					endUser.setName(rs.getString("end_user_name"));
					endUser.setSurname(rs.getString("end_user_surname"));
					endUser.setEmail(rs.getString("end_user_email"));
					endUser.setPassword(rs.getString("end_user_password"));
					
					address.setRegion(rs.getString("end_user_region"));
					address.setProvince(rs.getString("end_user_province"));
					address.setCity(rs.getString("end_user_city"));
					address.setStreet(rs.getString("end_user_street"));
					address.setCap(rs.getString("end_user_cap"));
					address.setHouseNumber(rs.getInt("end_user_house_number"));
					
					endUser.setAddress(address);
				}
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}
		
		return endUser;
	}
	
	@Override
	public void setAddress(int enduserID, Address address) throws SQLException {
		
		if(address.isValidAddress()) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
				
				preparedStatement.setString(1, address.getRegion());
				preparedStatement.setString(2, address.getProvince());
				preparedStatement.setString(3, address.getCity());
				preparedStatement.setString(4, address.getStreet());
				preparedStatement.setString(5, address.getCap());
				preparedStatement.setInt(6, address.getHouseNumber());
				
				preparedStatement.setInt(7, enduserID);
				
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
	
	private static DataSource ds;
}
