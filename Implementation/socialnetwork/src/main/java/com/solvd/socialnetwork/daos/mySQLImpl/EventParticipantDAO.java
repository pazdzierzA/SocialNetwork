package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.models.EventParticipant;
import com.solvd.socialnetwork.services.ConnectionPool;

public class EventParticipantDAO implements IEventParticipantDAO {
	public final static String GET_BY_ID = "SELECT * FROM EventParticipant WHERE id = ?";
	@Override
	public EventParticipant getById(Long id) {
		EventParticipant participant = new EventParticipant();
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					participant.setId(resultSet.getLong("id"));
					participant.setEventId(resultSet.getLong("eventId"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
		return participant;
	}

	@Override
	public EventParticipant save(EventParticipant entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventParticipant update(EventParticipant entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventParticipant> getByEventId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
