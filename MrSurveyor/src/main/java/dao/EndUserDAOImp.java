package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.RegisteredEndUser;

public class EndUserDAOImp implements EndUserDAO {

	private static final String ENDUSER_TABLE = "end_user";

	private static final String CREATE_ENDUSER = "INSERT INTO " + ENDUSER_TABLE
			+ " (end_user_name, end_user_surname, end_user_email, end_user_password,"
			+ " end_user_region, end_user_province, end_user_city, end_user_street,"
			+ " end_user_cap, end_user_house_number)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_EMAIL = "SELECT end_user_id " + "FROM " + ENDUSER_TABLE
			+ " WHERE end_user_email = ?;";

	public EndUserDAOImp(DataSource ds) {
		EndUserDAOImp.ds = ds;
	}

	@Override
	public void addEndUser(RegisteredEndUser endUser) throws SQLException {

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

	private static DataSource ds;

}
