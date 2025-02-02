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

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.services.ConnectionPool;

public class CommentDAO extends AbstractMySQLDAO<Comment> implements ICommentDAO {
	private final static Logger logger = LogManager.getLogger(CommentDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Comments WHERE id = ?";
	public final static String GET_BY_AUTHOR_ID = "SELECT * FROM Comments WHERE author_id =?";
	public final static String GET_BY_POST_ID = "SELECT * FROM Comments WHERE post_id =?";
	public final static String REMOVE_BY_ID = "DELETE FROM Comments WHERE id =?";
	public final static String UPDATE = "UPDATE Comments SET text =?, author_id = ?,post_id =? WHERE id=? ";
	public final static String INSERT = "INSERT INTO Comments (text,author_id,post_id) VALUES (?,?,?)";

	@Override
	public Comment getById(Long commentId) {
		Comment comment = new Comment();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, commentId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						comment = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving comment with ID: " + commentId, e);

			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return comment;
	}

	@Override
	public List<Comment> getByAuthorId(Long id) {
		List<Comment> comments = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();

			try (PreparedStatement statement = connection.prepareStatement(GET_BY_AUTHOR_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						comments.add(getMappedEntity(resultSet));
					}
				}

			} catch (SQLException e) {

				logger.error("Error retrieving comment with author ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return comments;
	}

	@Override
	public List<Comment> getByPostId(Long id) {
		List<Comment> comments = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();

			try (PreparedStatement statement = connection.prepareStatement(GET_BY_POST_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						comments.add(getMappedEntity(resultSet));
					}
				}

			} catch (SQLException e) {

				logger.error("Error retrieving comment with post ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return comments;
	}

	@Override
	public Comment save(Comment entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getText());
				statement.setLong(2, entity.getAuthorId());
				statement.setLong(3, entity.getPostId());
				Integer affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						while (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving comment failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error save comment with id {} : {}", entity.getId(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public Comment update(Comment entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getText());
				statement.setLong(2, entity.getAuthorId());
				statement.setLong(3, entity.getPostId());
				statement.setLong(4, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no comment found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error upgrade connection with id {} : {}", entity.getId(), e);
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
				logger.error("Error removing connection with id {} : {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}

	@Override
	protected Comment getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String text = resultSet.getString("text");
		Long authorId = resultSet.getLong("author_id");
		Long postId = resultSet.getLong("post_id");
		return new Comment(id, text, authorId, postId);
		
	}

}
