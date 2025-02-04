package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialnetwork.daos.ILikePostDAO;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.services.connectionpools.ConnectionPool;

public class LikePostDAO extends AbstractMySQLDAO<LikePost> implements ILikePostDAO {
	private final static Logger logger = LogManager.getLogger(LikePostDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Likes WHERE id = ?";
	public final static String GET_BY_POST_ID = "SELECT * FROM Likes WHERE postId = ?";
	public final static String INSERT = "INSERT INTO Likes (post_id, user_id) VALUES (?, ?)";
	public final static String UPDATE = "UPDATE Likes SET  post_id = ?, user_id =?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Likes WHERE id = ?";
	@Override
	public LikePost getById(Long id) {
		LikePost likePost = new LikePost();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						likePost = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving like with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return likePost;
	}

	@Override
	public LikePost save(LikePost entity) {
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
					throw new IllegalStateException("Saving like failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving like: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public LikePost update(LikePost entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setLong(1, entity.getPostId());
				statement.setLong(2, entity.getUserId());;
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
	public LikePost getByPostId(Long id) {
		LikePost likePost = new LikePost();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_POST_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						likePost = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving like with post ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return likePost;
	}
	
	@Override
	protected LikePost getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long postId = resultSet.getLong("post_id");
		Long userId = resultSet.getLong("user_id");
		return new LikePost(id,postId,userId);
	}





}
