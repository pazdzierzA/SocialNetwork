package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.models.Event;
import com.solvd.socialnetwork.services.ConnectionPool;

public class EventDAO implements IEventDAO {
	public final static String GET_BY_ID = "SELECT * FROM Event WHERE id = ?";
	public final static String GET_BY_NAME = "SELECT * FROM Comments WHERE name = ?";
	public final static String GET_BY_CREATOR_ID = "SELECT * FROM Comments WHERE creator_id = ?";
	public final static String GET_BY_LOCATION = "SELECT * FROM Comments WHERE location = ?";

	@Override
	public Event getById(Long id) {
		Event event = new Event();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					event.setId(resultSet.getLong("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return event;
	}

	@Override
	public List<Event> getByCreatorId(Long id) {
		List<Event> events = new ArrayList<>();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_CREATOR_ID);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					events.add(getMappedEvent(resultSet));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return events;
	}

	@Override
	public List<Event> getByName(String name) {
		List<Event> events = new ArrayList<>();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_NAME);
			statement.setString(1, name);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					events.add(getMappedEvent(resultSet));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return events;
	}
	
	@Override
	public List<Event> getByLocation(String location) {
		List<Event> events = new ArrayList<>();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_NAME);
			statement.setString(1, location);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					events.add(getMappedEvent(resultSet));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return events;
	}

	@Override
	public Event save(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event update(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	private Event getMappedEvent(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String location = resultSet.getString("location");
		LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
		LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
		Long creatorId = resultSet.getLong("creator_id");

		return new Event(id, name, location, startDate, endDate, creatorId);
	}

}
