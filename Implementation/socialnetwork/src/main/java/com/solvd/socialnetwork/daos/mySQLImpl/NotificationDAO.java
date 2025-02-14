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

import com.solvd.socialnetwork.daos.INotificationDAO;
import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;
import com.solvd.socialnetwork.services.connectionpools.ConnectionPool;

public class NotificationDAO extends AbstractMySQLDAO<Notification> implements INotificationDAO {
	private final static Logger logger = LogManager.getLogger(NotificationDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Notifications WHERE id = ?";
	public final static String INSERT = "INSERT INTO Notifications (text, type, user_id ) VALUES (?, ?, ?)";
	public final static String UPDATE = "UPDATE Notificatiosn SET text =?, type = ?,  user_id= ?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Notifications WHERE id = ?";
	public final static String GET_BY_TYPE = "SELECT * FROM Notifications WHERE type= ?";

	@Override
	public Notification getById(Long id) {
		Notification notification = new Notification();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						notification = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving notification with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return notification;
	}

	@Override
	public Integer save(Notification entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getType().getName());
				statement.setLong(3, entity.getUserId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving notification failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving notification: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity.getId().intValue();
	}

	@Override
	public Integer update(Notification entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getType().getName());
				statement.setLong(3, entity.getUserId());
				statement.setLong(4, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no notification found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating notification with id {}: {}", entity.getId(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity.getId().intValue();
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
				logger.error("Error removing notification with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}

	}

	@Override
	public List<Notification> getByType(NotificationType type) {
		List<Notification> notifications = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setString(1, type.getName());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						notifications.add(getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving notification with type: " + type, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return notifications;
	}

	@Override
	protected Notification getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String text = resultSet.getString("text");
		NotificationType type = NotificationType.valueOf(resultSet.getString("type").toUpperCase());
		Long userId = resultSet.getLong("user_id");
		return new Notification(id, text, type, userId);
	}

}
