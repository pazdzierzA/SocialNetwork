package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;
import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.models.Event;

public class EventDAO extends AbstractMyBatisDAO implements IEventDAO {
    private IEventDAO eventDAO;

    @Override
    public Event getById(Long id) {
        return execute(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            return eventDAO.getById(id);
        });
    }

    @Override
    public Integer save(Event entity) {
        return execute(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            return eventDAO.save(entity);
        });
    }

    @Override
    public Integer update(Event entity) {
        return execute(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            return eventDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            eventDAO.removeById(id);
        });
    }

    @Override
    public List<Event> getByName(String name) {
        return execute(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            return eventDAO.getByName(name);
        });
    }

    @Override
    public List<Event> getByCreatorId(Long id) {
        return execute(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            return eventDAO.getByCreatorId(id);
        });

    }

    @Override
    public List<Event> getByLocation(String location) {
        return execute(session -> {
            eventDAO = getMapper(IEventDAO.class, session);
            return eventDAO.getByLocation(location);
        });
    }
}
