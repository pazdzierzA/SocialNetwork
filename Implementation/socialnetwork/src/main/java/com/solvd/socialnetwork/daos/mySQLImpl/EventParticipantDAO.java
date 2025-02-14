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

import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.enums.ParticipantStatus;
import com.solvd.socialnetwork.models.EventParticipant;
import com.solvd.socialnetwork.services.connectionpools.ConnectionPool;

public class EventParticipantDAO extends AbstractMySQLDAO<EventParticipant>implements IEventParticipantDAO {
	private final static Logger logger = LogManager.getLogger(EventParticipantDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Events_Participants WHERE id = ?";
	public final static String INSERT = "INSERT INTO Events_Participants (status,event_id,user_id) VALUES (?,?,?)";
	public final static String UPDATE = "UPDATE Events_Participants SET status =?, event_id =?, user_id =? WHERE id=? ";
	public final static String REMOVE_BY_ID = "DELETE FROM Events_Participants WHERE id =?";
	public final static String GET_BY_EVENT_ID = "SELECT * FROM Events_Participants WHERE event_id = ?";

	@Override
	public EventParticipant getById(Long id) {
		EventParticipant participant = new EventParticipant();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						participant = getMappedEntity(resultSet);
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving event participant with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return participant;
	} 

	@Override
	public Integer save(EventParticipant entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getStatus().getStatus());
				statement.setLong(2, entity.getEventId());
				statement.setLong(3, entity.getUserId());
				Integer affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						while (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving event participant failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error save event participant with id {} : {}", entity.getId(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity.getId().intValue();
	}

	@Override
	public Integer update(EventParticipant entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getStatus().getStatus());
				statement.setLong(2, entity.getEventId());
				statement.setLong(3, entity.getUserId());
				statement.setLong(4, entity.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no notification found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error upgrade event participant with id {} : {}", entity.getId(), e);
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
				logger.error("Error removing event participant with id {} : {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}

	}

	@Override
	public List<EventParticipant> getByEventId(Long id) {
		List<EventParticipant> eventParticipants = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();

			try (PreparedStatement statement = connection.prepareStatement(GET_BY_EVENT_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						eventParticipants.add(getMappedEntity(resultSet));
					}
				}

			} catch (SQLException e) {

				logger.error("Error retrieving event participants from event ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return eventParticipants;
	}

	@Override
	protected EventParticipant getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		ParticipantStatus status = ParticipantStatus.valueOf(resultSet.getString("status").toUpperCase());
		Long eventId = resultSet.getLong("event_id");
		Long userId = resultSet.getLong("user_id");

		return new EventParticipant(id, status, eventId, userId);
	}

}
