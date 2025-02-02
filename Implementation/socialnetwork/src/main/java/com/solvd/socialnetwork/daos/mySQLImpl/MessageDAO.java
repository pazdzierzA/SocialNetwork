package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialnetwork.daos.IMessageDAO;
import com.solvd.socialnetwork.models.Message;
import com.solvd.socialnetwork.services.ConnectionPool;

public class MessageDAO extends AbstractMySQLDAO<Message> implements IMessageDAO{
	private final static Logger logger = LogManager.getLogger(MessageDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Messages WHERE id = ?";
	public final static String INSERT = "INSERT INTO Messages (text, to_user_id, from_user_id ) VALUES (?, ?, ?)";
	public final static String UPDATE = "UPDATE Messages SET text =?, to_user_id = ?,  from_user_id= ?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Messages WHERE id = ?";
	public final static String GET_BY_TO_USER_ID = "SELECT * FROM Messages WHERE to_user_id= ?";

	@Override
	public Message getById(Long id) {
		Message message = new Message();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						message = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving message with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return message;
	}

	@Override
	public Message save(Message entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getText());
				statement.setLong(3, entity. getRecipientId());
				statement.setLong(3, entity. getSenderId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving message failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving message: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public Message update(Message entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getText());
				statement.setLong(3, entity. getRecipientId());
				statement.setLong(3, entity. getSenderId());
				statement.setLong(4, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no message found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating message with id {}: {}", entity.getId(), e);
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
				logger.error("Error removing message with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		
	}

	@Override
	public List<Message> getByUserId(Long id) {
		List<Message> messages = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1,id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						messages.add(getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving message with user id : " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return messages;
	}


	@Override
	protected Message getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String text = resultSet.getString("text");
		Long recipientId = resultSet.getLong("to_user_id");
		Long senderId = resultSet.getLong("from_user_id");
		return new Message(id,text,recipientId,senderId);
	}
}
