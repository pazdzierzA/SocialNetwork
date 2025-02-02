package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialnetwork.daos.IUserDAO;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.ConnectionPool;

public class UserDAO extends AbstractMySQLDAO<User> implements IUserDAO{
	private final static Logger logger = LogManager.getLogger(UserDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Users WHERE id = ?";
	public final static String INSERT = "INSERT INTO Users (login, email, password, first_name, last_name, birth_date) VALUES (?, ?, ?, ?, ?, ?)";
	public final static String UPDATE = "UPDATE Users SET login = ?, email = ?, password = ?, first_name = ?, last_name = ?, birth_date = ? WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Users WHERE id = ?";
	public final static String GET_BY_EMAIL = "SELECT * FROM Users WHERE email = ?";
	public final static String GET_BY_LOGIN = "SELECT * FROM Users WHERE login = ?";
	public final static String GET_BY_FIRST_NAME = "SELECT * FROM Users WHERE first_name = ?";
	public final static String GET_BY_LAST_NAME = "SELECT * FROM Users WHERE last_name = ?";
	public final static String GET_BY_BIRTH_DATE = "SELECT * FROM Users WHERE birth_date = ?";

	@Override
	public User getById(Long id) {
		return getUserByParameter(GET_BY_ID, id);

	}
	
	@Override
	public User getByLogin(String login) {
		return getUserByParameter(GET_BY_LOGIN, login);
	}

	@Override
	public User getByEmail(String email) {
		 return getUserByParameter(GET_BY_EMAIL, email);
	}

	@Override
	public List<User> getByFirstName(String firstName) {
		return getUsersByParameter(GET_BY_FIRST_NAME, firstName);
	}


	@Override
	public List<User> getByLastName(String lastName) {
		return getUsersByParameter(GET_BY_LAST_NAME, lastName);
	}

	@Override
	public List<User> getByBirthDate(LocalDate birthDate) {
		return getUsersByParameter(GET_BY_BIRTH_DATE, birthDate);
	}

	@Override
	public User save(User entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getLogin());
				statement.setString(2, entity.getEmail());
				statement.setString(3,entity.getPassword());
				statement.setString(4, entity.getFirstName());
				statement.setString(5, entity.getLastName());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving user failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving user: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public User update(User entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getLogin());
				statement.setString(2, entity.getEmail());
				statement.setString(3,entity.getPassword());
				statement.setString(4, entity.getFirstName());
				statement.setString(5, entity.getLastName());
				statement.setDate(6, Date.valueOf(entity.getBirthDate()));
				statement.setLong(7, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no like found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating like with id {}: {}", entity.getId(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public void removeById(Long id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(REMOVE_BY_ID)) {
				statement.setLong(1, id);
				statement.executeUpdate();
			} catch (SQLException e) {
				logger.error("Error removing like with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		
	}

	@Override
	protected User getMappedEntity(ResultSet resultSet) throws SQLException {
		User user = new User();
	    user.setId(resultSet.getLong("id"));
	    user.setLogin(resultSet.getString("login"));
	    user.setEmail(resultSet.getString("email"));
	    user.setPassword(resultSet.getString("password"));
	    user.setFirstName(resultSet.getString("first_name"));
	    user.setLastName(resultSet.getString("last_name"));
	    user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
	    return user;
	}
	
	private User getUserByParameter(String query, Object parameter) {
	    User user = null;
	    Connection connection = null;
	    try {
	        connection = ConnectionPool.getInstance().getConnection();
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setObject(1, parameter);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    user = getMappedEntity(resultSet);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        logger.error("Error retrieving user", e);
	    } finally {
	        ConnectionPool.getInstance().releaseConnection(connection);
	    }
	    return user;
	}

	private List<User> getUsersByParameter(String query, Object parameter) {
	    List<User> users = new ArrayList<>();
	    Connection connection = null;
	    try {
	        connection = ConnectionPool.getInstance().getConnection();
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setObject(1, parameter);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    users.add(getMappedEntity(resultSet));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        logger.error("Error retrieving users", e);
	    } finally {
	        ConnectionPool.getInstance().releaseConnection(connection);
	    }
	    return users;
	}


}
