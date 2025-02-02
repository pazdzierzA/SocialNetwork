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

import com.solvd.socialnetwork.daos.IGroupDAO;
import com.solvd.socialnetwork.enums.GroupType;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.services.ConnectionPool;

public class GroupDAO extends AbstractMySQLDAO<Group> implements IGroupDAO {
	private final static Logger logger = LogManager.getLogger(GroupDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Groups WHERE id = ?";
	public final static String INSERT = "INSERT INTO Groups (name, type, creator_id) VALUES (?, ?, ?)";
	public final static String UPDATE = "UPDATE Groups SET name =?, type = ?,  creator_id= ?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Groups WHERE id = ?";
	public final static String GET_BY_NAME_ID = "SELECT * FROM Groups WHERE name = ?";
	@Override
	public Group getById(Long id) {
		Group group = new Group();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						group = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving group with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return group;
	}
	
	@Override
	public List<Group> getByName(String name) {
		List<Group> groups = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setString(1,name);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						groups.add(getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving group with type: " + name, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return groups;
	}

	@Override
	public Group save(Group entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getGroupName());
				statement.setString(2,entity.getGroupType().getType());
				statement.setLong(3, entity.getGroupCreatorId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving group failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving group: {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public Group update(Group entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getGroupName());
				statement.setString(2,entity.getGroupType().getType());
				statement.setLong(3, entity.getGroupCreatorId());
				statement.setLong(4, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no gorup found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error updating group with id {}: {}", entity.getId(), e);
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
				logger.error("Error removing group with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		
	}

	@Override
	protected Group getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String groupName = resultSet.getString("name");
		GroupType groupType = GroupType.valueOf(resultSet.getString("type").toUpperCase());
		Long creatorId = resultSet.getLong("creator_id");
		return new Group(id,groupName,groupType,creatorId);
	}



}
