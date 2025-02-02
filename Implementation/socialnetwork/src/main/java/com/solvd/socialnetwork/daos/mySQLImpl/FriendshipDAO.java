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

import com.solvd.socialnetwork.daos.IFriendshipDAO;
import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;
import com.solvd.socialnetwork.services.ConnectionPool;

public class FriendshipDAO extends AbstractMySQLDAO<Friendship> implements IFriendshipDAO {
	private final static Logger logger = LogManager.getLogger(FriendshipDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Friendships WHERE id = ?";
	public final static String INSERT = "INSERT INTO Friendships (status, type, user_id, friend_id) VALUES (?, ?, ?, ?)";
	public final static String UPDATE = "UPDATE Friendships SET status = ?, type = ?, user_id = ?, friend_id = ? WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Friendships WHERE id = ?";
	public final static String GET_BY_STATUS = "SELECT * FROM Friendships WHERE status = ?";
	public final static String GET_BY_TYPE = "SELECT * FROM Friendships WHERE type = ?";

	@Override
	public Friendship getById(Long id) {

		Friendship friendship = new Friendship();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						friendship.setId(resultSet.getLong("id"));
						friendship.setStatus(FriendshipStatus.valueOf(resultSet.getString("status").toUpperCase()));
						friendship.setType(FriendshipType.valueOf(resultSet.getString("type").toUpperCase()));
						friendship.setUserId(resultSet.getLong("user_id"));
						friendship.setFriendId(resultSet.getLong("friend_id"));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving friendship with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return friendship;
	}

	@Override
	public Friendship save(Friendship entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getStatus().getStatus());
				statement.setString(2, entity.getType().getType());
				statement.setLong(3, entity.getUserId());
				statement.setLong(4, entity.getFriendId());

				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving friendship failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving friendship: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public Friendship update(Friendship entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getStatus().getStatus());
				statement.setString(2, entity.getType().getType());
				statement.setLong(3, entity.getUserId());
				statement.setLong(4, entity.getFriendId());
				statement.setLong(5, entity.getId());

				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no friendship found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating friendship with id {}: {}", entity.getId(), e);
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
				logger.error("Error removing friendship with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}

	@Override
	public List<Friendship> getByStatus(FriendshipStatus status) {
		List<Friendship> friendships = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();

			try (PreparedStatement statement = connection.prepareStatement(GET_BY_STATUS)) {
				statement.setString(1, status.getStatus());
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next())
						friendships.add(getMappedEntity(resultSet));
				}
			} catch (SQLException e) {
				logger.error("Error retrieving friendship by status: " + status, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return friendships;
	}

	@Override
	public List<Friendship> getByType(FriendshipType type) {
		List<Friendship> friendships = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setString(1, type.getType());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						friendships.add(getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving friendship with type: " + type, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return friendships;

	}

	@Override
	protected Friendship getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		FriendshipStatus status = FriendshipStatus.valueOf(resultSet.getString("status").toUpperCase());
		FriendshipType type = FriendshipType.valueOf(resultSet.getString("type").toUpperCase());
		Long userId = resultSet.getLong("user_id");
		Long friendId = resultSet.getLong("friend_id");
		return new Friendship(id, status, type, userId, friendId);
	}
}
