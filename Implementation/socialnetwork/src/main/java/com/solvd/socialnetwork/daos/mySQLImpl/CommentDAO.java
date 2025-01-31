package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.services.ConnectionPool;

public class CommentDAO implements ICommentDAO {
	public final static String GET_BY_ID = "SELECT * FROM Comments WHERE id = ?";
	public final static String GET_BY_AUTHOR_ID = "SELECT * FROM Comments WHERE author_id =?";
	public final static String GET_BY_POST_ID = "SELECT * FROM Comments WHERE post_id =?";

	@Override
	public Comment getById(Long commentId) {
		Comment comment = new Comment();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, commentId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					comment.setId(resultSet.getLong("id"));
					comment.setText(resultSet.getString("text"));
					comment.setAuthorId(resultSet.getLong("author_id"));
					comment.setPostId(resultSet.getLong("post_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return comment;
	}

	@Override
	public List<Comment> getByAuthorId(Long id) {
		List<Comment> comments = new ArrayList<>();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_AUTHOR_ID);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					comments.add(getMappedComment(resultSet));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return comments;
	}

	@Override
	public List<Comment> getByPostId(Long id) {
		List<Comment> comments = new ArrayList<>();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_POST_ID);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					comments.add(getMappedComment(resultSet));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return comments;
	}

	@Override
	public Comment save(Comment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment update(Comment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	private Comment getMappedComment(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String text = resultSet.getString("text");
		Long authorId = resultSet.getLong("author_id");
		Long postId = resultSet.getLong("post_id");
		return new Comment(id, text, authorId, postId);
	}

}
