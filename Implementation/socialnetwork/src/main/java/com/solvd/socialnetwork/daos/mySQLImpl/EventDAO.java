package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.models.Event;
import com.solvd.socialnetwork.services.connectionpools.ConnectionPool;

public class EventDAO  extends AbstractMySQLDAO<Event> implements IEventDAO {
	private final static Logger logger = LogManager.getLogger(EventDAO.class.getName());
	public final static String GET_BY_ID = "SELECT * FROM Events WHERE id = ?";
	public final static String GET_BY_NAME = "SELECT * FROM Events WHERE name = ?";
	public final static String GET_BY_CREATOR_ID = "SELECT * FROM Events WHERE creator_id = ?";
	public final static String GET_BY_LOCATION = "SELECT * FROM Events WHERE location = ?";
	public final static String INSERT = "INSERT INTO Events (name,location,start_date,end_date,creator_id) VALUES (?,?,?,?,?)";
	public final static String UPDATE = "UPDATE Events SET name =?, location =?, start_date =?, end_date =?, creator_id =? WHERE id=? ";
	public final static String REMOVE_BY_ID = "DELETE FROM Events WHERE id =?";

	@Override
	public Event getById(Long id) {
		Event event = new Event();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						event.setId(resultSet.getLong("id"));
						event.setName(resultSet.getString("name"));
						event.setLocation(resultSet.getString("location"));
						event.setStartDate(resultSet.getDate("start_date").toLocalDate());
						event.setEndDate(resultSet.getDate("end_date").toLocalDate());
					}
				}
			} catch (SQLException e) {
				logger.error("Error retrieving comment with ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return event;
	}

	@Override
	public List<Event> getByCreatorId(Long id) {
		List<Event> events = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();

			try (PreparedStatement statement = connection.prepareStatement(GET_BY_CREATOR_ID)) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						events.add(getMappedEntity(resultSet));
					}
				}

			} catch (SQLException e) {

				logger.error("Error retrieving event  with creator ID: " + id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return events;
	}

	@Override
	public List<Event> getByName(String name) {
		List<Event> events = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_NAME)) {
				statement.setString(1, name);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						events.add(getMappedEntity(resultSet));
					}
				}

			} catch (SQLException e) {

				logger.error("Error retrieving event with name:" + name, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return events;
	}

	@Override
	public List<Event> getByLocation(String location) {
		List<Event> events = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(GET_BY_NAME)) {
				statement.setString(1, location);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						events.add(getMappedEntity(resultSet));
					}
				}

			} catch (SQLException e) {

				logger.error("Error retrieving event from location:" + location, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return events;
	}

	@Override
	public Event save(Event entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getLocation());
				statement.setDate(3, Date.valueOf(entity.getStartDate()));
				statement.setDate(4, Date.valueOf(entity.getEndDate()));
				statement.setLong(5, entity.getCreatorId());
				Integer affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						while (resultSet.next()) {
							entity.setId(resultSet.getLong(1));
						}
					}
				} else {
					throw new IllegalStateException("Saving event failed, no rows affected.");
				}
			} catch (SQLException e) {
				logger.error("Error save event with id {} : {}", entity.getId(), e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return entity;
	}

	@Override
	public Event update(Event entity) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getName());
				statement.setDate(3, Date.valueOf(entity.getStartDate()));
				statement.setDate(4, Date.valueOf(entity.getEndDate()));
				statement.setLong(5, entity.getCreatorId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
					throw new IllegalStateException("Update failed, no notification found with id: " + entity.getId());
				}
			} catch (SQLException e) {
				logger.error("Error upgrade event with id {} : {}", entity.getId(), e);
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
				logger.error("Error removing event with id {} : {}", id, e);
			}
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}


	@Override
	protected Event getMappedEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String location = resultSet.getString("location");
		LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
		LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
		Long creatorId = resultSet.getLong("creator_id");

		return new Event(id, name, location, startDate, endDate, creatorId);
	}

}
