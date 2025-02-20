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

import com.solvd.socialnetwork.daos.ISavedPostDAO;
import com.solvd.socialnetwork.models.SavedPost;
import com.solvd.socialnetwork.utils.connectionpools.ConnectionPool;

public class SavedPostDAO extends AbstractMySQLDAO<SavedPost> implements ISavedPostDAO {
	private final static Logger logger = LogManager.getLogger(SavedPostDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Saved_Posts WHERE id = ?";
	public final static String INSERT = "INSERT INTO Saved_Posts  (post_id, user_id ) VALUES (?, ?)";
	public final static String UPDATE = "UPDATE Saved_Posts  SET post_id =?,user_id = ?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Saved_Posts  WHERE id = ?";
	public final static String GET_BY_USER_ID = "SELECT * FROM Saved_Posts WHERE user_id = ?";
	@Override
	public SavedPost getById(Long id) {
		SavedPost savedPost = new SavedPost();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						 savedPost = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving notification with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return savedPost;
	}

	@Override
	public Integer save(SavedPost entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setLong(1, entity.getPostId());
				statement.setLong(2, entity.getUserId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving saved post failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving saved post : {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity.getId().intValue();
	}

	@Override
	public Integer update(SavedPost entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setLong(1, entity.getPostId());
				statement.setLong(2, entity.getUserId());
				statement.setLong(3, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, any saved post found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating  saved post with id {}: {}", entity.getId(), e);
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
				logger.error("Error removing saved post with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}

	}

	@Override
	public List<SavedPost> getByUserId(Long id) {
		List<SavedPost> savedPosts = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_USER_ID)) {
				statement.setLong(1, id) ;
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						savedPosts.add(getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving saved post with id: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return savedPosts;
	}

	@Override
	protected SavedPost getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long postId = resultSet.getLong("post_id");
		Long userId = resultSet.getLong("user_id");
		return new SavedPost(id, postId, userId);
	}

}
