package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialnetwork.daos.IPostDAO;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.services.connectionpools.ConnectionPool;

public class PostDAO extends AbstractMySQLDAO<Post> implements IPostDAO{
	private final static Logger logger = LogManager.getLogger(PostDAO.class.getName());
	public final static String UPDATE_LIKE_QUANTITY = "UPDATE Posts SET like_quantity = like_quantity + 1 WHERE id = ?";
	public final static String GET_BY_ID = "SELECT * FROM Posts WHERE id = ?";
	public final static String INSERT = "INSERT INTO Posts (text, title, like_quantity,comment_quantity, creator_id ) VALUES (?, ?, ?,?,?)";
	public final static String UPDATE = "UPDATE Posts SET text =?, title = ?, like_quantity= ? comment_quantity =? creator_id =?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Posts WHERE id = ?";
	@Override
	public Post getById(Long id) {
		Post post = new Post();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						post = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving post with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return post;
	}
	@Override
	public Integer save(Post entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getTitle());
				statement.setInt(3, entity.getLikeQuantity());
				statement.setInt(4, entity.getCommentQuantity());
				statement.setLong(5, entity.getCreatorId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving postfailed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving post: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity.getId().intValue();
	}
	@Override
	public Integer update(Post entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getTitle());
				statement.setInt(3, entity.getLikeQuantity());
				statement.setInt(4, entity.getCommentQuantity());
				statement.setLong(5, entity.getCreatorId());
				statement.setLong(6, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no post found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating post with id {}: {}", entity.getId(), e);
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
				logger.error("Error removing post with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}

		
	}
	@Override
	public void incrementLikeQuantity(Long id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE_LIKE_QUANTITY)) {
				statement.setLong(1, id);
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no post found with id: " + id);
				}
			} catch (SQLException e) {
				logger.error("Error updating post's like quantity with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		
	}
	
	@Override
	protected Post getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String text = resultSet.getString("text");
		String title = resultSet.getString("title");
		Integer likeQuantity = resultSet.getInt("like_quantity");
		Integer commentQuantity = resultSet.getInt("comment_quantity");
		Long creatorId = resultSet.getLong("creator_id");
		return new Post(id,text,title,likeQuantity,commentQuantity,creatorId);
	}
}
