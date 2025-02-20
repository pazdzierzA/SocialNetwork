package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.models.EventParticipant;

public class EventParticipantDAO extends AbstractMyBatisDAO implements IEventParticipantDAO {
    private IEventParticipantDAO eventParticipantDAO;

    @Override
    public EventParticipant getById(Long id) {
        return execute(session -> {
            eventParticipantDAO = getMapper(IEventParticipantDAO.class, session);
            return eventParticipantDAO.getById(id);
        });
    }

    @Override
    public Integer save(EventParticipant entity) {
        return execute(session -> {
            eventParticipantDAO = getMapper(IEventParticipantDAO.class, session);
            return eventParticipantDAO.save(entity);
        });
    }

    @Override
    public Integer update(EventParticipant entity) {
        return execute(session -> {
            eventParticipantDAO = getMapper(IEventParticipantDAO.class, session);
            return eventParticipantDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
         executeVoid(session -> {
            eventParticipantDAO  = getMapper(IEventParticipantDAO.class, session);
            eventParticipantDAO .removeById(id);
        });
      

    }

    @Override
    public List<EventParticipant> getByEventId(Long id) {
        return execute(session -> {
            eventParticipantDAO = getMapper(IEventParticipantDAO.class, session);
            return eventParticipantDAO.getByEventId(id);
        });

    }

}
