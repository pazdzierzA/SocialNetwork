package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialnetwork.daos.IStoryDAO;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.services.ConnectionPool;

public class StoryDAO extends AbstractMySQLDAO<Story> implements IStoryDAO{
	private final static Logger logger = LogManager.getLogger(StoryDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Stories WHERE id = ?";
	public final static String INSERT = "INSERT INTO Stories (text, pictureStoryUrl,storyCreatorId ) VALUES (?, ?,?)";
	public final static String UPDATE = "UPDATE Stories  SET post_id =?,user_id = ?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Stories  WHERE id = ?";
	public final static String REMOVE_BY_STORY_CREATOR_ID = "SELECT * FROM Stories WHERE story_creator_id = ?";
	
	@Override
	public Story getById(Long id) {
		Story story = new Story();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						story = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving gorup with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return story;
	}
	@Override
	public Story save(Story entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getPictureStoryUrl());
				statement.setLong(3, entity.getStoryCreatorId());
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
	public Story update(Story entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getPictureStoryUrl());
				statement.setLong(3, entity.getStoryCreatorId());
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
				logger.error("Error removing story with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		
	}
	@Override
	public void removeByStoryCreatorId(Long id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(REMOVE_BY_STORY_CREATOR_ID)) {
				statement.setLong(1, id);
				statement.executeUpdate();
			} catch (SQLException e) {
				logger.error("Error removing story with creator id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		
	}
	
	@Override
	protected Story getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String text = resultSet.getString("text");
		String pictureStoryUrl = resultSet.getString("picture_story_url");
		Long storyCreatorId = resultSet.getLong("story_cretor_id");
		return new Story(id,text,pictureStoryUrl,storyCreatorId);
	}
}
