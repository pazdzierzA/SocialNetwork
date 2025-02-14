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

import com.solvd.socialnetwork.daos.IGroupMemberDAO;
import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.connectionpools.ConnectionPool;

public class GroupMemberDAO extends AbstractMySQLDAO<GroupMember> implements IGroupMemberDAO {
	private final static Logger logger = LogManager.getLogger(GroupMemberDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Group_Members WHERE id = ?";
	public final static String INSERT = "INSERT INTO Group_Members (role, group_id, user_id) VALUES (?, ?, ?)";
	public final static String UPDATE = "UPDATE Group_Members SET role =?, group_id = ?, user_id= ?  WHERE id = ?";
	public final static String REMOVE_BY_ID = "DELETE FROM Group_Members WHERE id = ?";
	public final static String GET_BY_ROLE = "SELECT * FROM Group_Members WHERE role = ?";
	public final static String GET_USERS_BY_GROUP_ID = "SELECT u.* FROM Users u LEFT JOIN GroupMembers gm ON u.id = gm.user_id WHERE gm.group_id = ?";

	@Override
	public GroupMember getById(Long id) {
		GroupMember groupMembers = new GroupMember();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						groupMembers = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving gorup with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return groupMembers;
	}

	@Override
	public Integer save(GroupMember entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getUserRole().getRole());
				statement.setLong(2, entity.getGroupId());
				statement.setLong(3, entity.getUserId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving group member failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error saving group member : {}", e.getMessage(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity.getId().intValue();
	}

	@Override
	public Integer update(GroupMember entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getUserRole().getRole());
				statement.setLong(2, entity.getGroupId());
				statement.setLong(3, entity.getUserId());
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
				logger.error("Error removing group member with id {}: {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}

	}

	@Override
	public List<GroupMember> getByUserRole(UserRole userRole) {
		List<GroupMember> membersWithRole = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setString(1, userRole.getRole());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						membersWithRole.add(getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving friendship with type: " + userRole, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return membersWithRole;
	}

	@Override
	public List<User> getUsersByGroupId(Long id) {
		List<User> groupMembers = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_USERS_BY_GROUP_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						UserDAO userDao = new UserDAO();
						groupMembers.add(userDao.getMappedEntity(resultSet));
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving users from group with id: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return groupMembers;
	}

	@Override
	protected GroupMember getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		UserRole userRole = UserRole.valueOf(resultSet.getString("role").toUpperCase());
		Long groupId = resultSet.getLong("group_id");
		Long userId = resultSet.getLong("group_id");
		return new GroupMember(id, userRole, groupId, userId);
	}

}
